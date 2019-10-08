package br.com.sysagrega.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import br.com.sysagrega.controller.Qualificadores.QualificadorPerfil;
import br.com.sysagrega.model.IPerfil;
import br.com.sysagrega.model.IUsuario;
import br.com.sysagrega.model.Enums.TipoPagina;
import br.com.sysagrega.model.imp.Perfil;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.service.IUsuarioService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.service.imp.PerfilService;
import br.com.sysagrega.util.jsf.FacesUtil;
 
@Named
@ViewScoped
public class PerfilBean implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@Inject
	private PerfilService perfilService;
	
    @Inject
	private IUsuarioService usuarioService; 

    
	@Produces
	@QualificadorPerfil
	private IPerfil perfil;
	
	private List<Perfil> perfis;
	private List<Usuario> usuarios;
	
	private Boolean pesquisar = true;
	private Boolean visualizar = false;
	private Boolean editar = false;
	private Boolean viewPerfil = false;
	
	// tela de perfil
	private Long idUsuario;
	private Long idPerfil;
	private String filtroSigla;
	
	// tela de associacao
	private String filtroLogin ;
	private String filtroNome;
	private String filtroPerfil;
	private List<Usuario> perfisDeUsuarios;
	
	// associacoa
	
	private Long filtroUsuario;
	
	private Boolean verUsuario = false;
	private Boolean verPerfil = false;
	private Boolean verAssociacao = false;
	private Boolean novo = false;
	private Boolean novaAssociacao = false;
	private Boolean viewPerfilAssociacao = false;

	private Usuario usuarioLogado;
	// Manages Propertys
	@ManagedProperty(value="#{menuFaces}")
	private MenuBean menuBean;
	
	private Usuario usuario;
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
		
	if(perfil == null){
		perfil = new Perfil();
	}
	visualizar = false;
	editar = false;
	pesquisar = true;
	
	FacesContext context = FacesContext.getCurrentInstance();
	Map<String, String> requestParams = context.getExternalContext().getRequestParameterMap();
	
	if (requestParams.containsKey("verUsuario")) {
		verUsuario = Boolean.parseBoolean(requestParams.get("verUsuario"));
	}
	
	if (requestParams.containsKey("verPerfil")) {
		verPerfil = Boolean.parseBoolean(requestParams.get("verPerfil"));
	}
	
	if (requestParams.containsKey("verAssociacao")) {
		verUsuario = Boolean.parseBoolean(requestParams.get("verAssociacao"));
		perfis = perfilService.getAll();
		usuarios = usuarioService.getAll();
	}
	
	if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.CONSULTA_PERFIL)) {
		perfis = new ArrayList<>();
		
	} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.EDIT_PERFIL)) {
		this.perfil = FacesUtil.getPerfilSession();
		
	} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.NOVO_PERFIL)) {

		limparObjeto();
		
	} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.VISUALIZAR_PERFIL)) {
		this.perfil = FacesUtil.getPerfilSession();
		viewPerfil = true;
	}
	
	}
	
	public void buscar() {
		perfis = new ArrayList<>();
		perfis = this.perfilService.getByFilter(filtroNome);
		
		if(perfis == null || perfis.isEmpty()){
			FacesUtil.addErrorMessage("O filtro não retornou nenhum registro.");	
		}
	}
	
	
	public void buscarAssociacao() {
		perfisDeUsuarios = new ArrayList<>();
		perfisDeUsuarios = this.usuarioService.getByFilter(filtroLogin, filtroNome, filtroPerfil);
		
		if(perfisDeUsuarios == null || perfisDeUsuarios.isEmpty()){
			FacesUtil.addErrorMessage("O filtro não retornou nenhum registro.");	
		}
	}
	
	public void criarNovaAssociacao(){
		
		perfis = perfilService.getAll();
		usuarios = usuarioService.getAll();
		
		if(perfil == null){
			perfil = new Perfil();
		}
		
		idPerfil = null;
		idUsuario = null;
		
		viewPerfilAssociacao = false;
		novaAssociacao= true;
	}
	
	public void editarNovaAssociacao(Usuario usuario){
		
		perfis = perfilService.getAll();
		usuarios = usuarioService.getAll();
		
		idPerfil = usuario.getPerfil().getId();
		idUsuario = usuario.getId();
		
		if(perfil == null){
			perfil = new Perfil();
		}
		novaAssociacao= true;
		viewPerfilAssociacao = false;
	}
	
	public void visualizarNovaAssociacao(Usuario usuario){
		
		perfis = perfilService.getAll();
		usuarios = usuarioService.getAll();
		
		idPerfil = usuario.getPerfil().getId();
		idUsuario = usuario.getId();
		
		if(perfil == null){
			perfil = new Perfil();
		}
		novaAssociacao= true;
		viewPerfilAssociacao = true;
	}
	
	
	public void cancelarNovaAssociacao(){
		
		if(perfil == null){
			perfil = new Perfil();
		}
		
		idPerfil = null;
		idUsuario = null;
		
		novaAssociacao= false;
	}
	
	public void novoPerfil(){
		
		// carregar valores
		perfis = perfilService.getAll();
		usuarios = usuarioService.getAll();
		perfil = new Perfil();
		viewPerfil = false;
		novo= true;
	}
	
	public void cancelarNovo(){
		perfis = null;
		usuarios = null;
		perfil = null;
		
		if(perfil == null){
			perfil = new Perfil();
		}
		novo= false;
	}
	
	public void associar(){
		
		if(idUsuario == null){
			FacesUtil.addErrorMessage("O campos Usuário e Perfil são obrigatórios.");
			return;
		}
		
		IUsuario usuarioSelecioando =  usuarioService.getById(idUsuario);
		
		if(idPerfil != null && usuarioSelecioando != null){
			usuarioSelecioando.setPerfil(perfilService.getById(idPerfil));
			usuarioSelecioando.setAtivo(true);
			usuarioService.salvar(usuarioSelecioando);
			usuarioSelecioando = new Usuario();
			perfisDeUsuarios = this.usuarioService.getByFilter(filtroLogin, filtroNome, filtroPerfil);
			
			FacesUtil.addInfoMessage("Operação realizada com sucesso.");
		}else{
			FacesUtil.addErrorMessage("O campos Usuário e Perfil são obrigatórios.");	
		}
		
	}
	
	public void redirectEdit(Perfil perfil) {
		this.perfil = perfil;
		
		if(this.perfil != null) {
			
			FacesUtil.addParamSession(TipoPagina.EDIT_PERFIL);
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("perfil", this.perfil);
			
			isEdit();
			viewPerfil = false;
			visualizar = false;
			editar = true;
			pesquisar = false;
			novo = true;
			
		} else {
			FacesUtil.addErrorMessage("Favor selecionar um perfil!");
			return;
			
		}
	}	
	
	public void visualizar(Perfil perfil) {
		this.perfil = perfil;
		if(this.perfil != null) {
			
			FacesUtil.addParamSession(TipoPagina.VISUALIZAR_PROF);
			viewPerfil = true;
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("perfil", this.perfil);
			novo = true;
			
		} else {
			
			FacesUtil.addErrorMessage("Favor selecionar um profissional!");
			return;
			
		}
		
		
	}
	
	public String atualizar() {
		
		try {
			this.perfil.setDataAtualizacao(new Date());
			this.perfil.setUsuarioAtualizacao(usuarioLogado);
			this.perfil.setAtivo(true);
			this.perfilService.atualizar(this.perfil);
			limparObjeto();
			perfis = this.perfilService.getByFilter(filtroNome);
			FacesUtil.addInfoMessage("Registro alterado com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
			
		}
		return "consultar_perfil";
	}
	
	public void preExcluir(Perfil perfil){
		this.perfil = perfil;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluir').show();");
	}
	
	public void preExcluirAssociacao(Usuario usuario){
		this.usuario = usuario;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluir').show();");
	}
	
	public void excluir() {
		try {
			this.perfil.setDataAtualizacao(new Date());
			this.perfil.setUsuarioAtualizacao(usuarioLogado);
			this.perfil.setAtivo(false);
			this.perfilService.atualizar(this.perfil);

			perfis = new ArrayList<>();
			perfis = this.perfilService.getByFilter(filtroNome);
			
			FacesUtil.addInfoMessage("Registro excluido com sucesso!");
			
		} catch (Exception e) {
			
			FacesUtil.addErrorMessage(e.getMessage());
			
		}
	}
	
	public void excluirAssociacao() {
		try {
			this.usuario.setDataAtualizacao(new Date());
			this.usuario.setUsuarioAtualizacao(usuarioLogado);
			this.usuario.setAtivo(false);
			this.usuarioService.atualizar(this.usuario);

			perfisDeUsuarios = this.usuarioService.getByFilter(filtroLogin, filtroNome, filtroPerfil);
			
			FacesUtil.addInfoMessage("Registro excluido com sucesso!");
			
		} catch (Exception e) {
			
			FacesUtil.addErrorMessage(e.getMessage());
			
		}
	}
	
	
	public String salvar() {
		try {
			this.perfil.setDataInclusao(new Date());
			this.perfil.setUsuarioAtualizacao(usuarioLogado);
			this.perfil.setAtivo(true);
			this.perfilService.salvar(this.perfil);
			limparObjeto();
			perfis = this.perfilService.getByFilter(filtroNome);
			FacesUtil.addInfoMessage("Registro salvo com sucesso.");
		} catch (NegocioException e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}
		
		visualizar = false;
		editar = false;
		pesquisar = true;
		
		return "consultar_perfil";
	}
	public Boolean isEdit() {
		if(perfil == null){
			return true;
		}
		return this.perfil.isExistente() && !viewPerfil;
		
	}
	
	public String redirectNovo() {
		FacesUtil.addParamSession(TipoPagina.NOVO_PERFIL);
		isNew();
		visualizar = false;
		editar = true;
		pesquisar = false;
	
	return "cadastro_perfil";
}

	public String redirectConsultar() {
		FacesUtil.addParamSession(TipoPagina.NOVO_PERFIL);
		isNew();
		visualizar = false;
		editar = false;
		pesquisar = true;
	return "consultar_perfil";
	}


	public Boolean isNew() {
		
		if(perfil == null){
			return true;
		}
		return this.perfil.isNovo();
		
	}
	
	public void limparObjeto() {
		this.perfil = new Perfil();
		pesquisar = true;
		visualizar = false;
		editar = false;
		
	}

	public IPerfil getPerfil() {
		return perfil;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public Boolean getPesquisar() {
		return pesquisar;
	}

	public Boolean getVisualizar() {
		return visualizar;
	}

	public Boolean getEditar() {
		return editar;
	}

	public Boolean getViewPerfil() {
		return viewPerfil;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setPerfil(IPerfil perfil) {
		this.perfil = perfil;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public void setPesquisar(Boolean pesquisar) {
		this.pesquisar = pesquisar;
	}

	public void setVisualizar(Boolean visualizar) {
		this.visualizar = visualizar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public void setViewPerfil(Boolean viewPerfil) {
		this.viewPerfil = viewPerfil;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Boolean getVerUsuario() {
		return verUsuario;
	}

	public Boolean getVerPerfil() {
		return verPerfil;
	}

	public Boolean getVerAssociacao() {
		return verAssociacao;
	}

	public void setVerUsuario(Boolean verUsuario) {
		this.verUsuario = verUsuario;
	}

	public void setVerPerfil(Boolean verPerfil) {
		this.verPerfil = verPerfil;
	}

	public void setVerAssociacao(Boolean verAssociacao) {
		this.verAssociacao = verAssociacao;
	}

	public Boolean getNovo() {
		return novo;
	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

	public Long getFiltroUsuario() {
		return filtroUsuario;
	}

	public void setFiltroUsuario(Long filtroUsuario) {
		this.filtroUsuario = filtroUsuario;
	}

	public Boolean getNovaAssociacao() {
		return novaAssociacao;
	}

	public void setNovaAssociacao(Boolean novaAssociacao) {
		this.novaAssociacao = novaAssociacao;
	}

	public String getFiltroLogin() {
		return filtroLogin;
	}

	public void setFiltroLogin(String filtroLogin) {
		this.filtroLogin = filtroLogin;
	}

	public String getFiltroPerfil() {
		return filtroPerfil;
	}

	public void setFiltroPerfil(String filtroPerfil) {
		this.filtroPerfil = filtroPerfil;
	}

	public List<Usuario> getPerfisDeUsuarios() {
		return perfisDeUsuarios;
	}

	public void setPerfisDeUsuarios(List<Usuario> perfisDeUsuarios) {
		this.perfisDeUsuarios = perfisDeUsuarios;
	}

	public Boolean getViewPerfilAssociacao() {
		return viewPerfilAssociacao;
	}

	public void setViewPerfilAssociacao(Boolean viewPerfilAssociacao) {
		this.viewPerfilAssociacao = viewPerfilAssociacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getFiltroSigla() {
		return filtroSigla;
	}

	public void setFiltroSigla(String filtroSigla) {
		this.filtroSigla = filtroSigla;
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
	
}
