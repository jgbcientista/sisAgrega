package br.com.sysagrega.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.sysagrega.model.imp.Anexo;
import br.com.sysagrega.service.IAnexoService;
import br.com.sysagrega.util.jsf.FacesUtil;

public class AnexoUtil {
	/*
	 * INICIA OS METODOS DE ANEXO.NAO ESQUECER DE COLOCAR QUANDO FOR CADASTRAR UM NOVO, EDITAR E VISUALIZAR
	 * CRIAR NO C:arquivos
	 */
	@Inject
	private static IAnexoService anexoService;
	
	static String VAR_AMBIENTE_DADOS_SISTEMA = "DADOS_SISTEMA";
	
	
	
	public static void excluirAnexo(Anexo excluirAnexo, String  caminhoAnexo, Long tipoEntidade, Long idEntidade, List<FileUploadEvent> listaArquivosAnexados, List<Anexo> anexos) {
		if (excluirAnexo != null && excluirAnexo.getId() != null) {
			anexoService.excluir(excluirAnexo);
			if(obterCaminhoAnexo(caminhoAnexo) != null){
				File deletarArquivoDiretorio = new File(new StringBuilder(obterCaminhoAnexo(caminhoAnexo)).append(File.separator).append(excluirAnexo.getDescricao()).toString());
				deletarArquivoDiretorio.delete();
			}
			preencherListaAnexos(tipoEntidade, idEntidade);
		}else{
			if(listaArquivosAnexados != null){
				
				 List<FileUploadEvent> aux = listaArquivosAnexados;
				 listaArquivosAnexados = new ArrayList<>();
				 anexos = new ArrayList<>();
				 for (FileUploadEvent fileUploadEvent : aux) {
					if(!fileUploadEvent.getFile().getFileName().equals(excluirAnexo.getDescricao())){
						listaArquivosAnexados.add(fileUploadEvent);
						Anexo itemTemp = new Anexo();
						itemTemp.setDescricao(fileUploadEvent.getFile().getFileName());
						itemTemp.setIdEntidade(idEntidade);
						itemTemp.setTipo(tipoEntidade);
						anexos.add(itemTemp);
					}
				}
				 
			}
		}
		FacesUtil.addInfoMessage("Anexo excluído com sucesso.");
	}
	
	
	/**
	 * @param caminhoAnexo Ex:C:\\DADOS_SISTEMA\\AGREGA\\ANEXOS\\CLIENTE\\
	 * @return 
	 */
	public static String obterCaminhoAnexo(String caminhoAnexo) {
		return caminhoAnexo;
	}
	
	/**
	 * 
	 * @param tipo = Ex: ETipoEntidade.CLIENTE.getFlag()
	 * @param idEntidade = cliente.getId()
	 * @return
	 */
	public static List<Anexo> preencherListaAnexos(Long tipoEntidade, Long idEntidade) {
		return anexoService.getByIdByTipo(tipoEntidade, idEntidade, null);
		
	}
	
	public static void arquivoUpload(FileUploadEvent uploadedFile,Long tipoEntidade, Long idEntidade, List<FileUploadEvent> listaArquivosAnexados, List<Anexo> anexos) {
		  try {
			
			if(anexoService.getByIdByTipo(tipoEntidade, idEntidade, uploadedFile.getFile().getFileName()).size() >0){
				FacesUtil.addErrorMessage("Não é possível salvar o anexo "+ uploadedFile.getFile().getFileName() +". O mesmo já existe no banco de dados.");
				return;
			}
		    File file = new File(Constante.CLIENTE, uploadedFile.getFile().getFileName());
		 
		    OutputStream out = new FileOutputStream(file);
		    out.write(uploadedFile.getFile().getContents());
		    out.close();
		    
		    if (listaArquivosAnexados == null) {
				listaArquivosAnexados = new ArrayList<>();
			}

			listaArquivosAnexados.add(uploadedFile);
		 
			Anexo anexo = new Anexo();
			anexo.setDescricao(uploadedFile.getFile().getFileName());

			if (anexos == null) {
				anexos = new ArrayList<>();
			}
			anexos.add(anexo);
			
		    RequestContext context = RequestContext.getCurrentInstance();
			context.update("tabelaListaAnexoSelecionado");
			FacesUtil.addInfoMessage("O arquivo " + uploadedFile.getFile() + " foi salvo!");
		    
		  } catch(IOException e) {
		    FacesContext.getCurrentInstance().addMessage(
		              null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		  }
		 
		}
	
	public static void salvarListaAnexos(Long tipoEntidade, Long idEntidade, List<FileUploadEvent> listaArquivosAnexados, List<Anexo> anexos) {
		if (listaArquivosAnexados != null) {
			for (FileUploadEvent arquivoAnexo : listaArquivosAnexados) {
				Anexo anexo = null;
				try {
					if(anexoService.getByIdByTipo(tipoEntidade, idEntidade, arquivoAnexo.getFile().getFileName()).size()==0){
						anexo = new Anexo();
						anexo.setDescricao(arquivoAnexo.getFile().getFileName());
						anexo.setIdEntidade(idEntidade);
						anexo.setTipo(tipoEntidade);
						anexoService.salvar(anexo);
					}
				} catch (Exception e) {
					FacesUtil.addErrorMessage("Não é possível salvar o anexo.");
				}
			
			}
		}
		preencherListaAnexos(tipoEntidade, idEntidade);
	}
	
	public static StreamedContent downloadAnexo(String arquivo, String caminhoAnexo, StreamedContent file) { 
		try {
			String caminho = obterCaminhoAnexo(caminhoAnexo) + arquivo;
			FileInputStream stream = new FileInputStream(caminho);
			file = new DefaultStreamedContent(stream, caminho, arquivo);
		} catch (FileNotFoundException e) {
			FacesUtil.addErrorMessage("Anexo não encontrado.");
			return null;
		}
		return file;
	}
			
}
