package br.com.sysagrega.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

import br.com.sysagrega.model.IOrdemServico;
import br.com.sysagrega.model.Enums.ETipoProposta;
import br.com.sysagrega.model.Enums.TipoArquivoRelatorio;
import br.com.sysagrega.model.imp.OrdemServico;
import br.com.sysagrega.model.imp.Projeto;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.model.imp.TipoItemProposta;
import br.com.sysagrega.util.jsf.PlanejamentoVO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Classe util que gera o relatório das três formas: passando conexão, passando
 * ResultSet e passando uma lista de objetos.
 * 
 * @author Elton
 *
 */
public class RelatorioUtil {

	private static String saida;

	/**
	 * Esse tipo de geração de relatório é útil quando a query com o banco pode
	 * mudar dinamicamente. Exemplo: utilização de um filtro.
	 * 
	 * @return String navigation rule que exibe o relatório
	 */
	public String geraRelatorioPassandoResultSet() {
		saida = null;
		String jasper = getDiretorioReal("/jasper/nome_arquivo.jasper");
		Connection conexao = null;

		try {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Sempre mando fechar a conexão, mesmo que tenha dado erro
				if (conexao != null)
					conexao.close();
			} catch (SQLException e) {

			}
		}

		return "exibeRelatorio";
	}

	/**
	 * Esse tipo de geração de relatório é uma alternativa aos outros dois.
	 * Nesse exemplo utilizo um subrelatório param mostrar essa técnica que
	 * também pode ser empregada.
	 * 
	 * @return String navigation rule que exibe o relatório
	 */
	public String geraRelatorioPassandoListaDeObjetos() {
		saida = null;
		String jasper = getDiretorioReal("/jasper/nome_relatorio.jasper");
		Connection conexao = null;

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("REPORT_CONNECTION", conexao);
			map.put("SUBREPORT_DIR", getDiretorioReal("/jasper/") + "/");
			// preenchePdf(print);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "exibeRelatorio";
	}
	
	/**
	 * Método para preencher o PDF do relatório
	 * 
	 * @param print
	 *            JasperPrint
	 * @throws JRException
	 */
	private static void preenchePdf(JasperPrint print, String nomeRelatorio) throws JRException {

		saida = InterfaceConstants.IREPORT_PATH_WIN + nomeRelatorio + TipoArquivoRelatorio.PDF.getDescricao();
		// Exporto para PDF
		JasperExportManager.exportReportToPdfFile(print, saida);

		File arquivo = new File(saida);
		int tamanho = (int) arquivo.length();

		// Obtêm o response jsf
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		try {
			ServletOutputStream output = response.getOutputStream();
			response.setContentType("application/pdf"); // tipo do conteúdo na resposta
			response.setContentLength(tamanho); // ajuda na barra de progresso
			response.setHeader("Content-Disposition", "attachment; filename=Proposta"+nomeRelatorio+".pdf");
			facesContext.responseComplete();
			//OutputStream output = response.getOutputStream();
			Files.copy(arquivo.toPath(), output); // escreve bytes no fluxo de saída
			
			output.flush();
			output.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void gerarRelatorioOS(String nomeRelatorio, List<OrdemServico> ordemServico, OrdemServico filtro) {

		String jasper = getDiretorioReal(InterfaceConstants.IREPORT_PATH_JASPER + nomeRelatorio + TipoArquivoRelatorio.JASPER.getDescricao());
		try {
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(ordemServico, false);

			Map<String, Object> param = new HashMap<String, Object>();
			
			param.put("SUBREPORT_DIR", getDiretorioReal("/jasper/"));
			param.put("RENDER_CUSTOS", true);
			param.put("filtroContrato",filtro.getContrato() == null ? "-": filtro.getContrato().getNrContrato());
			param.put("filtroOrdemServico",filtro.getNumeroOS()== null ? "-" : filtro.getNumeroOS());
			
			JasperPrint print = JasperFillManager.fillReport(jasper, param, ds);
			preenchePdf(print, nomeRelatorio);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void gerarRelatorioProjeto(String nomeRelatorio, List<Projeto> projeto, Projeto filtro) {

			String jasper = getDiretorioReal(InterfaceConstants.IREPORT_PATH_JASPER + nomeRelatorio + TipoArquivoRelatorio.JASPER.getDescricao());
			try {
				JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(projeto, false);
				Map<String, Object> param = new HashMap<String, Object>();
				
				param.put("SUBREPORT_DIR", getDiretorioReal("/jasper/"));
				param.put("RENDER_CUSTOS", true);
				param.put("filtroContrato",filtro.getContrato() == null ? "-": filtro.getContrato().getNrContrato());
				param.put("filtroTipoProjeto",filtro.getTipoProjeto());
				param.put("filtroDataFinal",filtro.getFiltroDataFinal() == null ? "-" : filtro.getFiltroDataFinal());
				param.put("filtroDataInicial",filtro.getFiltroDataInicial()== null ? "-" : filtro.getFiltroDataInicial());
				param.put("filtroProjeto",filtro.getNomeProjeto() == null? "-" : filtro.getNomeProjeto());
				param.put("filtroCliente",filtro.getCliente() == null ? "-" : filtro.getCliente().getNome());
				param.put("filtroStatus",filtro.getStatus() == null ? "-" : filtro.getStatus().getNome());
				param.put("filtroMunicipio",filtro.getMunicipio() == null ? "-" : filtro.getMunicipio().getNome());
				param.put("filtroOrdemServico",filtro.getOrdemServico()== null ? "-" : filtro.getOrdemServico().getNumeroOS());

				JasperPrint print = JasperFillManager.fillReport(jasper, param, ds);
				preenchePdf(print, nomeRelatorio);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	public static void gerarPlanejamento(String nomeRelatorio, List<PlanejamentoVO> tipoItem,  String nPlanejamento) {

		String jasper = getDiretorioReal(InterfaceConstants.IREPORT_PATH_JASPER + nomeRelatorio + TipoArquivoRelatorio.JASPER.getDescricao());
		try {
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(tipoItem, false);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("SUBREPORT_DIR", getDiretorioReal("/jasper/"));
			param.put("RENDER_CUSTOS", true);
				
			JasperPrint print = JasperFillManager.fillReport(jasper, param, ds);
			preenchePdf(print, nPlanejamento);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void geraRelatorioTipoPropostaGenerica(String nomeRelatorio, List<TipoItemProposta> tipoItem,  String nProposta) {

		String jasper = getDiretorioReal(InterfaceConstants.IREPORT_PATH_JASPER + nomeRelatorio + TipoArquivoRelatorio.JASPER.getDescricao());
		try {
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(tipoItem, false);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("SUBREPORT_DIR", getDiretorioReal("/jasper/"));
			param.put("RENDER_CUSTOS", true);
				
			JasperPrint print = JasperFillManager.fillReport(jasper, param, ds);
			preenchePdf(print, nProposta);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void geraRelatorioTipoItemProposta(String nomeRelatorio, List<TipoItemProposta> tipoItem) {

		String jasper = getDiretorioReal(InterfaceConstants.IREPORT_PATH_JASPER + nomeRelatorio + TipoArquivoRelatorio.JASPER.getDescricao());
		try {

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(tipoItem, false);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("SUBREPORT_DIR", getDiretorioReal("/jasper/"));
			param.put("RENDER_CUSTOS", true);
			JasperPrint print = JasperFillManager.fillReport(jasper, param, ds);
			preenchePdf(print, nomeRelatorio);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void geraRelatorio(String nomeRelatorio, List<Proposta> proposta) {

		String jasper = getDiretorioReal(InterfaceConstants.IREPORT_PATH_JASPER + nomeRelatorio + TipoArquivoRelatorio.JASPER.getDescricao());
		try {
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(proposta, false);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("SUBREPORT_DIR", getDiretorioReal("/jasper/"));
			
			for (Proposta iProposta : proposta) {
				if (!iProposta.getTipoProposta().equalsIgnoreCase(ETipoProposta.FINANCEIRA.getDescricao())) {
					param.put("RENDER_CUSTOS", true);
					
				} else {
					param.put("RENDER_CUSTOS", true);
				}
			}

			JasperPrint print = JasperFillManager.fillReport(jasper, param, ds);
			preenchePdf(print, nomeRelatorio);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * Relatorio de projetos
	 */
	public static void gerarReltorioProjetosSelecionados(String nomeRelatorio, List<Projeto> projetosSelecionados, IOrdemServico ordemServico) {

		String jasper = getDiretorioReal(InterfaceConstants.IREPORT_PATH_JASPER + nomeRelatorio + TipoArquivoRelatorio.JASPER.getDescricao());

		try {
			
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date data = new Date();
			DateFormat df = new SimpleDateFormat("MM/yyyy");
			String mesAno = df.format(data);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(projetosSelecionados, false);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("SUBREPORT_DIR", getDiretorioReal("/jasper/"));
			param.put("mesAno", mesAno);
				
			JasperPrint print = JasperFillManager.fillReport(jasper, param, ds);
			preenchePdf(print, nomeRelatorio);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Método para retornar o caminho completo do diretório onde se encontra o
	 * arquivo 'jasper' e o arquivo 'pdf'
	 * 
	 * @param diretorio
	 *            String diretório a ser localizado na aplicação
	 * @return String caminho completo
	 */
	private static String getDiretorioReal(String diretorio) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getServletContext().getRealPath(diretorio);
	}

	private static BufferedImage getImagePath() throws IOException {
		
		File file = new File(getDiretorioReal(InterfaceConstants.IREPORT_PATH_IMAGE));
		FileInputStream fis = new FileInputStream(file);
		
		return ImageIO.read(fis);

	}

	/**
	 * Método para retornar o nome da aplicação
	 * 
	 * @return String nome da aplicacao
	 */
	private static String getContextPath() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getServletContext().getContextPath();
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

}
