package br.com.sysagrega.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.sysagrega.controller.Qualificadores.QualificadorServico;
import br.com.sysagrega.model.IServico;
import br.com.sysagrega.model.Enums.TipoPagina;
import br.com.sysagrega.model.imp.Area;
import br.com.sysagrega.model.imp.Servico;
import br.com.sysagrega.model.imp.TipoServico;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.service.IAreaService;
import br.com.sysagrega.service.IServicoService;
import br.com.sysagrega.service.ITipoServicoService;
import br.com.sysagrega.service.IUsuarioService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ServicoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Produces
	@QualificadorServico
	private IServico servico;

	@Inject
	private IServicoService servicoService;
	
	@Inject
	private IAreaService areaService;
	
	@Inject
	private ITipoServicoService tipoServicoService;
	
	@Inject
	private IUsuarioService usuarioService; 
	
	private boolean viewServico;
	private boolean disableCity;
	private String filtroDescricao;
	private Long filtroTipoServico;
	private Long filtroArea;
	private Long idTipoServico;
	private Long idArea;
	private List<Servico> servicos;
	private List<Area> areas;
	private Boolean pesquisar = true;
	private Boolean visualizar = false;
	private Boolean editar = false;
	private Boolean verNegocio = false;
	private Boolean novo = false;
	
	
	//Enums
	private List<TipoServico> tipoServicos;
	
	private Usuario usuarioLogado;
	// Manages Propertys
	@ManagedProperty(value="#{menuFaces}")
	private MenuBean menuBean;
	
	@PostConstruct
	public void inicializar() {
		
		  SecurityContext contextString = SecurityContextHolder.getContext();
		  	if (contextString instanceof SecurityContext) {
		  Authentication authentication = contextString.getAuthentication();
		  if (authentication instanceof Authentication) {
			  usuarioLogado = usuarioService.getUserPerfilByLogin(authentication.getName());
		  	}
	     }
		
		servicos = new ArrayList<>();
		tipoServicos = tipoServicoService.getAllTipoServicos();
		areas = areaService.getAllAreas();
		
		// Carregando lista
		if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.CONSULTA_SERVICO)) {
			servicos = new ArrayList<>();
			
		} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.EDIT_SERVICO)) {
			
			this.servico = FacesUtil.getServicoSession();
			
		} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.NOVO_SERVICO)) {

			limparObjeto();
			
		} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.VISUALIZAR_SERVICO)) {
			this.servico = FacesUtil.getServicoSession();
			viewServico = true;
		}
		
		visualizar = false;
		editar = false;
		pesquisar = true;
		novo = false;
	}

	public void limparObjeto() {
		this.servico = new Servico();
		disableCity = true;
	}

	public void salvarServico() {

		try {
			//this.servico.setTipoServico(tipoServicoService.getTipoServicoById(this.servico.getTipoServico().getId()));
			//this.servico.setArea(areaService.getAreaById(this.servico.getArea().getId()));
			this.servico.setDataInclusao(new Date());
			this.servico.setUsuarioAtualizacao(usuarioLogado);
			this.servico.setAtivo(true);
			this.servicoService.salvar(this.servico);
			limparObjeto();
			FacesUtil.addInfoMessage("Registro salvo com sucesso.");
			isNewServico();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		visualizar = false;
		editar = false;
		pesquisar = true;
		novo= false;
	}

	/**
	 * Metodo realiza atualização de um objeto cliente
	 * 
	 * @param cliente
	 * @return cliente
	 * @author JOAO
	 */
	public void atualizarServico() {
		
		try {
			this.servico.setUsuarioAtualizacao(usuarioLogado);
			this.servico.setAtivo(true);
			this.servicoService.salvar(this.servico);
			FacesUtil.addInfoMessage("Registro alterado com sucesso.");
			limparObjeto();
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		servicos = new ArrayList<>();
		visualizar = false;
		editar = false;
		pesquisar = true;
		novo= false;
	
	}
	
	/**
	 * Método realiza a exclusão de um objeto servico
	 */
	
	public void excluirServico() {
		
		try {
			this.servico.setDataAtualizacao(new Date());
			this.servico.setUsuarioAtualizacao(usuarioLogado);
			this.servico.setAtivo(false);
			this.servicoService.atualizarServico(this.servico);
			FacesUtil.addInfoMessage("Registro excluido com sucesso!");
			filtrarServico();
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void preExcluir(Servico servico){
		this.servico = servico;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluir').show();");
	}


	public void redirectEditServico(Servico servico) {
		this.servico = servico;
			
		FacesUtil.addParamSession(TipoPagina.EDIT_SERVICO);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("servico", this.servico);
		
		isEditServico();
		disableCity = true;
		visualizar = false;
		editar = true;
		pesquisar = false;
		novo= false;
		viewServico = false;
	}
	
	/**
	 * metodo para redirecionar para pagina correta.
	 
	 */
	
	public void novoNegocio(){
		servico = new Servico();
		servico.setTipoServico(new TipoServico());
		servico.setArea(new Area());
		
		novo= true;
		pesquisar = false;
		editar = false;
		visualizar = false;
		viewServico = false;
	}
	
	public void voltar(){
		if(servico == null){
			servico = new Servico();
		}
		novo= false;
		pesquisar = true;
		viewServico = false;
	}
	
	
	
	public String redirectConsultar() {
		FacesUtil.addParamSession(TipoPagina.NOVO_SERVICO);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		isNewServico();
	return "consultar_servico";
	}
	
	
	/**
	 * Método redireciona para tela de visualização do cliente
	 * @return editar_servico
	 */
		
	public void visualizarServico(Servico servico) {
		this.servico = servico;
		if(this.servico != null) 
			
		FacesUtil.addParamSession(TipoPagina.VISUALIZAR_SERVICO);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("servico", this.servico);
		
		visualizar = true;
		pesquisar = false;
		novo = false;
		editar = false;
		viewServico = true;
	}
	
	/**
	 * Método valida se é uma edição do objeto servico
	 * @return Boolean
	 */
	public Boolean isEditServico() {
		if(servico == null){
			return true;
		}
		return this.servico.isExistente() && !viewServico;
		
	}
	
	/**
	 * Método valida se é um novo objeto cliente
	 * @return Boolean
	 */
	public Boolean isNewServico() {
		
		if(servico == null){
			return true;
		}
		return this.servico.isNovo();
		
	}
	
	public void filtrarServico() {
		servicos = new ArrayList<>();
		servicos = this.servicoService.getServicoByFilter(this.filtroTipoServico,this.filtroArea, this.filtroDescricao);
		
		if(servicos == null || servicos.isEmpty()){
			FacesUtil.addErrorMessage("O filtro não retornou nenhum registro.");	
		}
	}
	
	/**
	 * Método carrega os enums para o objeto cliente
	 */
	public boolean isViewServico() {
		return viewServico;
	}

	public boolean isDisableCity() {
		return disableCity;
	}

	public String getFiltroDescricao() {
		return filtroDescricao;
	}

	public Long getFiltroTipoServico() {
		return filtroTipoServico;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public IServico getServico() {
		return servico;
	}

	public List<TipoServico> getTipoServicos() {
		return tipoServicos;
	}

	public void setViewServico(boolean viewServico) {
		this.viewServico = viewServico;
	}

	public void setDisableCity(boolean disableCity) {
		this.disableCity = disableCity;
	}

	public void setFiltroDescricao(String filtroDescricao) {
		this.filtroDescricao = filtroDescricao;
	}

	public void setFiltroTipoServico(Long filtroTipoServico) {
		this.filtroTipoServico = filtroTipoServico;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public void setServico(IServico servico) {
		this.servico = servico;
	}

	public void setTipoServicos(List<TipoServico> tipoServicos) {
		this.tipoServicos = tipoServicos;
	}

	public Long getIdTipoServico() {
		return idTipoServico;
	}

	public void setIdTipoServico(Long idTipoServico) {
		this.idTipoServico = idTipoServico;
	}

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public Long getFiltroArea() {
		return filtroArea;
	}

	public void setFiltroArea(Long filtroArea) {
		this.filtroArea = filtroArea;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public MenuBean getMenuBean() {
		return menuBean;
	}

	public void setMenuBean(MenuBean menuBean) {
		this.menuBean = menuBean;
	}

	public Boolean getPesquisar() {
		return pesquisar;
	}

	public void setPesquisar(Boolean pesquisar) {
		this.pesquisar = pesquisar;
	}

	public Boolean getVisualizar() {
		return visualizar;
	}

	public void setVisualizar(Boolean visualizar) {
		this.visualizar = visualizar;
	}

	public Boolean getEditar() {
		return editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public Boolean getVerNegocio() {
		return verNegocio;
	}

	public void setVerNegocio(Boolean verNegocio) {
		this.verNegocio = verNegocio;
	}

	public Boolean getNovo() {
		return novo;
	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}
	
}
