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

import br.com.sysagrega.controller.Qualificadores.QualificadorRecurso;
import br.com.sysagrega.model.IRecurso;
import br.com.sysagrega.model.Enums.EStatusRecurso;
import br.com.sysagrega.model.Enums.TipoPagina;
import br.com.sysagrega.model.imp.AplicacaoRecurso;
import br.com.sysagrega.model.imp.ClassificacaoRecurso;
import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.model.imp.Recurso;
import br.com.sysagrega.model.imp.TipoRecurso;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.service.IAplicacaoRecursoService;
import br.com.sysagrega.service.IClassificacaoRecursoService;
import br.com.sysagrega.service.IProfissionalService;
import br.com.sysagrega.service.IRecursoService;
import br.com.sysagrega.service.ITipoRecursoService;
import br.com.sysagrega.service.IUsuarioService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class RecursoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IRecursoService recursoService;
	
	@Inject
	private IClassificacaoRecursoService classificacaoRecursoService; 
	
	@Inject
	private ITipoRecursoService tipoRecursoService; 
	
	@Inject
	private IAplicacaoRecursoService aplicacaoRecursoService; 
	
	@Inject
	private IUsuarioService usuarioService; 
	
	@Inject
	private IProfissionalService profissionalervice; 

	@Produces
	@QualificadorRecurso
	private IRecurso recurso;

	private boolean viewRecurso;
	
	private boolean disableCity;
	
	private Date filtroValidade;
	
	private Date filtroGarantia;
	
	private String filtroFabricante;
	
	private String filtroNome;
	
	private Long filtroIdTipoRecurso;
	
	private List<Recurso> recursos;
	private List<TipoRecurso> comboTipoRecurso;
	private List<ClassificacaoRecurso> comboClassificacaoRecurso;
	private List<AplicacaoRecurso> comboAplicacaoRecurso;
	private List<Profissional> comboProfissional;
	private List<String> comboStatus;
	
	
	private Usuario usuarioLogado;

	private Boolean pesquisar = true;
	private Boolean visualizar = false;
	private Boolean editar = false;
	private Boolean verRecurso = false;
	private Boolean novo = false;
	
	
	// Manages Propertys
	@ManagedProperty(value="#{menuFaces}")
	private MenuBean menuBean;
	
	@PostConstruct
	public void inicializar() {

		// Informacoes do usuario logado.
		  SecurityContext contextString = SecurityContextHolder.getContext();
		  	if (contextString instanceof SecurityContext) {
		  Authentication authentication = contextString.getAuthentication();
		  if (authentication instanceof Authentication) {
			  usuarioLogado = usuarioService.getUserPerfilByLogin(authentication.getName());
		  	}
	     }
		
		recursos = new ArrayList<>();
		recurso = new Recurso();
		recurso.setAplicacao(new AplicacaoRecurso());
		recurso.setClassificacaoRecurso(new ClassificacaoRecurso());
		recurso.setTipoRecurso(new TipoRecurso());
		recurso.setProfissional(new Profissional());
		
		
		
		
		
		// Carregando lista
		if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.CONSULTA_RECURSO)) {
			recursos = new ArrayList<>();
			
		} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.EDIT_RECURSO)) {
			this.recurso = FacesUtil.getRecursoSession();
			
		} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.NOVO_RECURSO)) {

			limparObjeto();
			
		} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.VISUALIZAR_RECURSO)) {
			this.recurso = FacesUtil.getRecursoSession();
			viewRecurso = true;
		}
		
		comboTipoRecurso = tipoRecursoService.getAll();
		comboClassificacaoRecurso = classificacaoRecursoService.getAll();
		comboAplicacaoRecurso = aplicacaoRecursoService.getAll();
		comboProfissional = profissionalervice.getAllProfissionals();
		comboStatus = new ArrayList<>();
		for(EStatusRecurso status:EStatusRecurso.values()){
			comboStatus.add(status.getDescricao());
		}
		
		visualizar = false;
		editar = false;
		pesquisar = true;
		novo = false;
	}

	public void limparObjeto() {
		this.recurso = new Recurso();
		disableCity = true;
		
	}

	/**
	 * Metodo realiza a persistência de um objeto Recurso
	 * 
	 */
	public void salvarRecurso() {

		try {
			this.recurso.setDataInclusao(new Date());
			this.recurso.setUsuarioAtualizacao(usuarioLogado);
			this.recurso.setAtivo(true);
			this.recursoService.salvarRecurso(this.recurso);
			limparObjeto();
			FacesUtil.addInfoMessage("Registro salvo com sucesso.");
			
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		visualizar = false;
		editar = false;
		pesquisar = true;
		novo= false;
	}

	/**
	 * Metodo realiza atualização de um objeto
	 * 
	 * @param recurso
	 * @return recurso
	 * @author JOAO
	 */
	
	
	public void atualizarRecurso() {
		
		try {
			this.recurso.setDataAtualizacao(new Date());
			this.recurso.setUsuarioAtualizacao(usuarioLogado);
			this.recurso.setAtivo(true);
			this.recursoService.atualizarRecurso(this.recurso);
			limparObjeto();
			FacesUtil.addInfoMessage("Recurso alterado com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
			
		}
		recursos = new ArrayList<>();
		visualizar = false;
		editar = false;
		pesquisar = true;
		novo= false;
	}
	
	/**
	 * Método realiza a exclusão de um objeto Recurso
	 */

	
	public void excluirRecurso() {
		
		try {
			this.recurso.setDataAtualizacao(new Date());
			this.recurso.setUsuarioAtualizacao(usuarioLogado);
			this.recurso.setAtivo(false);
			this.recursoService.atualizarRecurso(this.recurso);
			carregarTodosRecursos();
			FacesUtil.addInfoMessage("Registro excluido com sucesso!");
			
		} catch (Exception e) {
			
			FacesUtil.addErrorMessage(e.getMessage());
			
		}
	}

	public void preExcluir(Recurso recurso){
		this.recurso = recurso;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluir').show();");
	}
	
	
 
	/**
	 * Carregar todos os recursos cadastrados no sistema para tela de
	 * consulta
	 * 
	 */
	private void carregarTodosRecursos() {

		recursos = new ArrayList<>();
		
		Recurso recurso = new Recurso();
		
		if(filtroFabricante != null){
			recurso.setFabricante(filtroFabricante);
		}else{
			recurso.setFabricante(null);
		}
		if(filtroValidade != null){
			recurso.setValidade(filtroValidade);
		}else{
			recurso.setValidade(null);
		}
		if(filtroNome != null){
			recurso.setNome(filtroNome);
		}else{
			recurso.setNome(null);
		}
		if(filtroIdTipoRecurso != null){
			TipoRecurso t = new TipoRecurso();
			t.setId(filtroIdTipoRecurso);
			recurso.setTipoRecurso(t);
		}else{
			recurso.setTipoRecurso(null);
		}
		recursos = this.recursoService.getRecursoByFilter(recurso);

	}
	
	/**
	 * Método redireciona para a tela de edição do objeto recurso
	 * passando o mesmo na sessão
	 * @return editar
	 */
	
	public void redirectEditRecurso(Recurso recurso) {
		this.recurso = recurso;
		
		FacesUtil.addParamSession(TipoPagina.EDIT_RECURSO);
			
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("recurso", this.recurso);
		
		isEditRecurso();
		disableCity = true;
		visualizar = false;
		editar = true;
		pesquisar = false;
		novo= false;
		viewRecurso = false;
	}
	
	/**
	 * metodo para redirecionar para pagina correta.
	 
	 */
	public void novoRecurso(){
		recursos = new ArrayList<>();
		recurso = new Recurso();
		recurso.setAplicacao(new AplicacaoRecurso());
		recurso.setClassificacaoRecurso(new ClassificacaoRecurso());
		recurso.setTipoRecurso(new TipoRecurso());
		recurso.setProfissional(new Profissional());
		
		comboStatus = new ArrayList<>();
		for(EStatusRecurso status:EStatusRecurso.values()){
			comboStatus.add(status.getDescricao());
		}
		
		novo= true;
		pesquisar = false;
		editar = false;
		visualizar = false;
		viewRecurso = false;
	}
	
	public void voltar(){
		if(recurso == null){
			recurso = new Recurso();
		}
		novo= false;
		pesquisar = true;
		viewRecurso = false;
	}
	
	
	public String redirectConsultar() {
		FacesUtil.addParamSession(TipoPagina.NOVO_RECURSO);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		isNewRecurso();
	return "consultar_recurso";
	}
	
	
	/**
	 * Método redireciona para tela de visualização do recurso
	 * @return recurso
	 */
	
	
	public void visualizarRecurso(Recurso recurso) {
		this.recurso = recurso;
			
		FacesUtil.addParamSession(TipoPagina.VISUALIZAR_RECURSO);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("recurso", this.recurso);
		
		visualizar = true;
		pesquisar = false;
		novo = false;
		editar = false;
		viewRecurso = true;
	}
	
	/**
	 * Método valida se é uma edição do objeto Recurso
	 * @return Boolean
	 */
	public Boolean isEditRecurso() {
		if(recurso == null){
			return true;
		}
		return this.recurso.isExistente() && !viewRecurso;
		
	}
	
	/**
	 * Método valida se é um novo objeto recurso
	 * @return Boolean
	 */
	public Boolean isNewRecurso() {
		
		if(recurso == null){
			return true;
		}
		return this.recurso.isNovo();
		
	}
	
	public void filtrarRecurso() {
		recursos = new ArrayList<>();
		
		Recurso recurso = new Recurso();
		
		if(filtroFabricante != null){
			recurso.setFabricante(filtroFabricante);
		}else{
			recurso.setFabricante(null);
		}
		if(filtroValidade != null){
			recurso.setValidade(filtroValidade);
		}else{
			recurso.setValidade(null);
		}
		if(filtroNome != null){
			recurso.setNome(filtroNome);
		}else{
			recurso.setNome(null);
		}
		if(filtroIdTipoRecurso != null){
			TipoRecurso t = new TipoRecurso();
			t.setId(filtroIdTipoRecurso);
			recurso.setTipoRecurso(t);
		}else{
			recurso.setTipoRecurso(null);
		}
		recursos = this.recursoService.getRecursoByFilter(recurso);
		
		
		if(recursos == null || recursos.isEmpty()){
			FacesUtil.addErrorMessage("O filtro não retornou nenhum registro.");	
		}
	}

	public IRecurso getRecurso() {
		return recurso;
	}

	public boolean isViewRecurso() {
		return viewRecurso;
	}

	public boolean isDisableCity() {
		return disableCity;
	}

	public Date getFiltroValidade() {
		return filtroValidade;
	}

	public String getFiltroFabricante() {
		return filtroFabricante;
	}

	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecurso(IRecurso recurso) {
		this.recurso = recurso;
	}

	public void setViewRecurso(boolean viewRecurso) {
		this.viewRecurso = viewRecurso;
	}

	public void setDisableCity(boolean disableCity) {
		this.disableCity = disableCity;
	}

	public void setFiltroValidade(Date filtroValidade) {
		this.filtroValidade = filtroValidade;
	}

	public void setFiltroFabricante(String filtroFabricante) {
		this.filtroFabricante = filtroFabricante;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	public Date getFiltroGarantia() {
		return filtroGarantia;
	}

	public void setFiltroGarantia(Date filtroGarantia) {
		this.filtroGarantia = filtroGarantia;
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

	public Boolean getVerRecurso() {
		return verRecurso;
	}

	public void setVerRecurso(Boolean verRecurso) {
		this.verRecurso = verRecurso;
	}

	public Boolean getNovo() {
		return novo;
	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

	public void setMenuBean(MenuBean menuBean) {
		this.menuBean = menuBean;
	}

	public List<TipoRecurso> getComboTipoRecurso() {
		return comboTipoRecurso;
	}

	public void setComboTipoRecurso(List<TipoRecurso> comboTipoRecurso) {
		this.comboTipoRecurso = comboTipoRecurso;
	}

	public List<ClassificacaoRecurso> getComboClassificacaoRecurso() {
		return comboClassificacaoRecurso;
	}

	public void setComboClassificacaoRecurso(List<ClassificacaoRecurso> comboClassificacaoRecurso) {
		this.comboClassificacaoRecurso = comboClassificacaoRecurso;
	}

	public List<AplicacaoRecurso> getComboAplicacaoRecurso() {
		return comboAplicacaoRecurso;
	}

	public void setComboAplicacaoRecurso(List<AplicacaoRecurso> comboAplicacaoRecurso) {
		this.comboAplicacaoRecurso = comboAplicacaoRecurso;
	}

	public List<Profissional> getComboProfissional() {
		return comboProfissional;
	}

	public void setComboProfissional(List<Profissional> comboProfissional) {
		this.comboProfissional = comboProfissional;
	}

	public List<String> getComboStatus() {
		return comboStatus;
	}

	public void setComboStatus(List<String> comboStatus) {
		this.comboStatus = comboStatus;
	}

	public Long getFiltroIdTipoRecurso() {
		return filtroIdTipoRecurso;
	}

	public void setFiltroIdTipoRecurso(Long filtroIdTipoRecurso) {
		this.filtroIdTipoRecurso = filtroIdTipoRecurso;
	}
	
	
}
