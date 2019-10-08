package br.com.sysagrega.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.sysagrega.controller.vo.PropostaMesVO;
import br.com.sysagrega.controller.vo.RelatorioClienteVO;
import br.com.sysagrega.model.Enums.EAnoPesquisa;
import br.com.sysagrega.model.Enums.ETipoRelatorioProposta;
import br.com.sysagrega.model.imp.Area;
import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.model.imp.TipoServico;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.service.IAreaService;
import br.com.sysagrega.service.IClienteService;
import br.com.sysagrega.service.IPropostaService;
import br.com.sysagrega.service.ITipoServicoService;
import br.com.sysagrega.service.IUsuarioService;
import br.com.sysagrega.util.DateUtil;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class RelatorioPropostaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IClienteService clienteService;
	@Inject
	private IPropostaService propostaService;
	@Inject
	private ITipoServicoService tipoServicoService;
	@Inject
	private IAreaService areaService;
	@Inject
	private IUsuarioService usuarioService;

	
	private Boolean pesquisar;
	private Boolean resultado;
	private List<Cliente> listClientes;
	private RelatorioClienteVO filtro;
	private List<PropostaMesVO> listPropostaMes;
	private List<String> comboTipoRelatorioProposta;
	private List<String> comboAnoPesquisa;
	private List<TipoServico> listTipoServicos;
	private List<Area> listAreas;
	
	private Usuario usuarioLogado;
	// Manages Propertys
	@ManagedProperty(value = "#{menuFaces}")
	private MenuBean menuBean;
  
	
	@PostConstruct
	public void inicializar() {
		pesquisar = true;
		resultado = false;
		listClientes = clienteService.getClienteByAtivo();
		filtro = new RelatorioClienteVO();
		listPropostaMes = new ArrayList<>();
		listTipoServicos = tipoServicoService.getAllTipoServicos();
		listAreas = areaService.getAllAreas();
		
		comboTipoRelatorioProposta = new ArrayList<>();
		for (ETipoRelatorioProposta item : ETipoRelatorioProposta.values()) {
			comboTipoRelatorioProposta.add(item.getDescricao());
		}
		
		comboAnoPesquisa = new ArrayList<>();
		for (EAnoPesquisa item : EAnoPesquisa.values()) {
			comboAnoPesquisa.add(item.getDescricao());
		}
		
		// Informacoes do usuario logado.
		SecurityContext contextString = SecurityContextHolder.getContext();
		if (contextString instanceof SecurityContext) {
			Authentication authentication = contextString.getAuthentication();
			if (authentication instanceof Authentication) {
				usuarioLogado = usuarioService.getUserPerfilByLogin(authentication.getName());
			}
		}
				
	}
	
	public void limpar(){
		listPropostaMes = new ArrayList<>();
		filtro = new RelatorioClienteVO();
		resultado = false;
	}
	
	public void pesquisarPropostas(){
		if(usuarioLogado.getLogin().equalsIgnoreCase("Adelia") || usuarioLogado.getLogin().equalsIgnoreCase("Agrega")){
			resultado = true;
			listPropostaMes = new ArrayList<>();
			Integer anoFiltro = null;
			
			if(filtro.getAnoRelatorio() != null){
				anoFiltro = filtro.getAnoRelatorio();
			}else{
				FacesUtil.addErrorMessage("Por favor, selecione um ano.");
				return;
			}
			criarLista(anoFiltro);
		}else{
			FacesUtil.addErrorMessage("Você não tem perimissão para pesquisar esse relatório.");
			return;
		}
	}
	
	public String concatNome(String param){
		String nome = param;
		if(nome.length() > 20){
			nome = nome.substring(0, 20)+"...";
		}
		return nome;
	}
	
	public String concatProjeto(String param){
		String nome = param;
		if(nome.length() > 25){
			nome = nome.substring(0, 25)+"...";
		}
		return nome;
	}
	
	public void verificaUsuarioLogado(){
		
	}
	
	public void criarLista(Integer anoFiltro){
		List<Proposta> listProposta;
		if(this.filtro != null){
			listProposta = propostaService.getObterListPropostaRelatorioByFilter(filtro);
		}else{
			listProposta = propostaService.getAllPropostas();
		}
		
		PropostaMesVO mesJan = new PropostaMesVO("Janeiro");
		PropostaMesVO mesFev = new PropostaMesVO("Fevereiro");
		PropostaMesVO mesMar = new PropostaMesVO("Março");
		PropostaMesVO mesAbr = new PropostaMesVO("Abril");
		PropostaMesVO mesMai = new PropostaMesVO("Maio");
		PropostaMesVO mesJun = new PropostaMesVO("Junho");
		PropostaMesVO mesJul = new PropostaMesVO("Julho");
		PropostaMesVO mesAgo = new PropostaMesVO("Agosto");
		PropostaMesVO mesSet = new PropostaMesVO("Setembro");
		PropostaMesVO mesOut = new PropostaMesVO("Outubro");
		PropostaMesVO mesNov = new PropostaMesVO("Novembro");
		PropostaMesVO mesDez = new PropostaMesVO("Dezembro");
		
		if(listProposta != null){
			
			for (Proposta proposta : listProposta) {
				Integer mes = DateUtil.getMonthFromDate(proposta.getDataInclusao());
    			Integer ano = DateUtil.getYearsFromDateFormated(proposta.getDataInclusao());
    			
    			if(anoFiltro.equals(ano)){
    				if(mes.equals(1)){
    					mesJan.getListPropostas().add(proposta);
    				}else if(mes.equals(2)){
    					mesFev.getListPropostas().add(proposta);
    				}else if(mes.equals(3)){
    					mesMar.getListPropostas().add(proposta);
    				}else if(mes.equals(4)){
    					mesAbr.getListPropostas().add(proposta);
    				}else if(mes.equals(5)){
    					mesMai.getListPropostas().add(proposta);
    				}else if(mes.equals(6)){
    					mesJun.getListPropostas().add(proposta);
    				}else if(mes.equals(7)){
    					mesJul.getListPropostas().add(proposta);
    				}else if(mes.equals(8)){
    					mesAgo.getListPropostas().add(proposta);
    				}else if(mes.equals(9)){
    					mesSet.getListPropostas().add(proposta);
    				}else if(mes.equals(10)){
    					mesOut.getListPropostas().add(proposta);
    				}else if(mes.equals(11)){
    					mesNov.getListPropostas().add(proposta);
    				}else if(mes.equals(12)){
    					mesDez.getListPropostas().add(proposta);
        			}
    			}
			}
			if(mesJan.getListPropostas() != null && !mesJan.getListPropostas().isEmpty()){
				mesJan.setTotalVlrProposta(calularValorTotalProposta(mesJan.getListPropostas()));
				mesJan.setTotalQdtProposta(mesJan.getListPropostas().size());
				listPropostaMes.add(mesJan);
			}
			if(mesFev.getListPropostas() != null && !mesFev.getListPropostas().isEmpty()){
				mesFev.setTotalVlrProposta(calularValorTotalProposta(mesFev.getListPropostas()));
				mesFev.setTotalQdtProposta(mesFev.getListPropostas().size());
				listPropostaMes.add(mesFev);
			}
			if(mesMar.getListPropostas() != null && !mesMar.getListPropostas().isEmpty()){
				mesMar.setTotalVlrProposta(calularValorTotalProposta(mesMar.getListPropostas()));
				mesMar.setTotalQdtProposta(mesMar.getListPropostas().size());
				listPropostaMes.add(mesMar);
			}
			if(mesAbr.getListPropostas() != null && !mesAbr.getListPropostas().isEmpty()){
				mesAbr.setTotalVlrProposta(calularValorTotalProposta(mesAbr.getListPropostas()));
				mesAbr.setTotalQdtProposta(mesAbr.getListPropostas().size());
				listPropostaMes.add(mesAbr);
			}
			if(mesMai.getListPropostas() != null && !mesMai.getListPropostas().isEmpty()){
				mesMai.setTotalVlrProposta(calularValorTotalProposta(mesMai.getListPropostas()));
				mesMai.setTotalQdtProposta(mesMai.getListPropostas().size());
				listPropostaMes.add(mesMai);
			}
			if(mesJun.getListPropostas() != null && !mesJun.getListPropostas().isEmpty()){
				mesJun.setTotalVlrProposta(calularValorTotalProposta(mesJun.getListPropostas()));
				mesJun.setTotalQdtProposta(mesJun.getListPropostas().size());
				listPropostaMes.add(mesJun);
			}
			if(mesJul.getListPropostas() != null && !mesJul.getListPropostas().isEmpty()){
				mesJul.setTotalVlrProposta(calularValorTotalProposta(mesJul.getListPropostas()));
				mesJul.setTotalQdtProposta(mesJul.getListPropostas().size());
				listPropostaMes.add(mesJul);
			}
			if(mesAgo.getListPropostas() != null && !mesAgo.getListPropostas().isEmpty()){
				mesAgo.setTotalVlrProposta(calularValorTotalProposta(mesAgo.getListPropostas()));
				mesAgo.setTotalQdtProposta(mesAgo.getListPropostas().size());
				listPropostaMes.add(mesAgo);
			}
			if(mesSet.getListPropostas() != null && !mesSet.getListPropostas().isEmpty()){
				mesSet.setTotalVlrProposta(calularValorTotalProposta(mesSet.getListPropostas()));
				mesSet.setTotalQdtProposta(mesSet.getListPropostas().size());
				listPropostaMes.add(mesSet);
			}
			if(mesOut.getListPropostas() != null && !mesOut.getListPropostas().isEmpty()){
				mesOut.setTotalVlrProposta(calularValorTotalProposta(mesOut.getListPropostas()));
				mesOut.setTotalQdtProposta(mesOut.getListPropostas().size());
				listPropostaMes.add(mesOut);
			}
			if(mesNov.getListPropostas() != null && !mesNov.getListPropostas().isEmpty()){
				mesNov.setTotalVlrProposta(calularValorTotalProposta(mesNov.getListPropostas()));
				mesNov.setTotalQdtProposta(mesNov.getListPropostas().size());
				listPropostaMes.add(mesNov);
			}
			if(mesDez.getListPropostas() != null && !mesDez.getListPropostas().isEmpty()){
				mesDez.setTotalVlrProposta(calularValorTotalProposta(mesDez.getListPropostas()));
				mesDez.setTotalQdtProposta(mesDez.getListPropostas().size());
				listPropostaMes.add(mesDez);
			}
		}
	}
	
	private Double calularValorTotalProposta(List<Proposta> proposta){
		double sum = 0;
	       for(Proposta s : proposta) {
	    	   double total = s.getValorTotalComBdiComissao()+((s.getValorTotalComBdiComissao()) / (1 - s.getImpostos()) - (s.getValorTotalComBdiComissao()))-(s.getDesconto());
	    	   if(total > 0){
	    		   sum += total;
	    	   }
	       }
	     return sum;
	}
	
	
	public Boolean getPesquisar() {
		return pesquisar;
	}
	
	public void setPesquisar(Boolean pesquisar) {
		this.pesquisar = pesquisar;
	}

	public List<Cliente> getListClientes() {
		return listClientes;
	}


	public void setListClientes(List<Cliente> listClientes) {
		this.listClientes = listClientes;
	}

	public RelatorioClienteVO getFiltro() {
		return filtro;
	}

	public void setFiltro(RelatorioClienteVO filtro) {
		this.filtro = filtro;
	}

	public List<PropostaMesVO> getListPropostaMes() {
		return listPropostaMes;
	}

	public void setListPropostaMes(List<PropostaMesVO> listPropostaMes) {
		this.listPropostaMes = listPropostaMes;
	}

	public List<String> getComboTipoRelatorioProposta() {
		return comboTipoRelatorioProposta;
	}

	public void setComboTipoRelatorioProposta(List<String> comboTipoRelatorioProposta) {
		this.comboTipoRelatorioProposta = comboTipoRelatorioProposta;
	}

	public Boolean getResultado() {
		return resultado;
	}

	public void setResultado(Boolean resultado) {
		this.resultado = resultado;
	}

	public List<String> getComboAnoPesquisa() {
		return comboAnoPesquisa;
	}

	public void setComboAnoPesquisa(List<String> comboAnoPesquisa) {
		this.comboAnoPesquisa = comboAnoPesquisa;
	}

	public List<Area> getListAreas() {
		return listAreas;
	}

	public void setListAreas(List<Area> listAreas) {
		this.listAreas = listAreas;
	}

	public List<TipoServico> getListTipoServicos() {
		return listTipoServicos;
	}

	public void setListTipoServicos(List<TipoServico> listTipoServicos) {
		this.listTipoServicos = listTipoServicos;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	 
}
