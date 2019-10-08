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

import br.com.sysagrega.controller.Qualificadores.QualificadorUsuario;
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
public class UsuarioBean implements Serializable {
 
	private static final long serialVersionUID = 1L;

	
	@Inject
	private IUsuarioService usuarioService; 
	
	@Inject
	private PerfilService perfilService;
	
	@Produces
	@QualificadorUsuario
	private IUsuario usuario;
	
	private List<Perfil> perfis;
	private List<Usuario> usuarios;
	
	private Boolean pesquisar = true;
	private Boolean visualizar = false;
	private Boolean editar = false;
	private Boolean viewUsuario = false;
	
	private Long idUsuario;
	private Long idPerfil;
	private String filtroLogin; 
	private String filtroPerfil ;
	private String filtroNome ;
	
	private Boolean verUsuario = false;
	private Boolean verPerfil = false;
	private Boolean verAssociacao = false;
	private Boolean novo = false;
	private String senhaNova;
	
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
      
	if(usuario == null){
		usuario = new Usuario();
		usuario.setPerfil(new Perfil());
	}
	visualizar = false;
	editar = false;
	pesquisar = true;
	FacesContext context = FacesContext.getCurrentInstance();
	Map<String, String> requestParams = context.getExternalContext().getRequestParameterMap();
	
	if (requestParams.containsKey("verUsuario")) {
		verUsuario = Boolean.parseBoolean(requestParams.get("verUsuario"));
		verPerfil = false;
		verAssociacao = false;
	}
	
	if (requestParams.containsKey("verPerfil")) {
		verPerfil = Boolean.parseBoolean(requestParams.get("verPerfil"));
		verUsuario = false;
		verAssociacao = false;
	}
	
	if (requestParams.containsKey("verAssociacao")) {
		verAssociacao = Boolean.parseBoolean(requestParams.get("verAssociacao"));
		verUsuario = false;
		verPerfil = false;
	}

	// carregar valores
	perfis = perfilService.getAll();
	
	if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.CONSULTA_USUARIO)) {
		usuarios = new ArrayList<Usuario>();
		
	} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.EDIT_USUARIO)) {
		this.usuario = FacesUtil.getUsuarioSession();
		
	} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.NOVO_USUARIO)) {

		limparObjeto();
		
	} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.VISUALIZAR_USUARIO)) {
		this.usuario = FacesUtil.getUsuarioSession();
		viewUsuario = true;
	}
	
	}
	
	public void buscar() {
		usuarios = new ArrayList<>();
		usuarios = this.usuarioService.getByFilter(filtroLogin, filtroNome, filtroPerfil);
		
		if(usuarios == null || usuarios.isEmpty()){
			FacesUtil.addErrorMessage("O filtro não retornou nenhum registro.");	
		}
	}
	
	public void novoUsuario(){
		usuario = new Usuario();
		usuario.setPerfil(new Perfil());
		novo= true;
		viewUsuario = false;
	}
	
	public void cancelarNovo(){
		
		if(usuario == null){
			usuario = new Usuario();
			usuario.setPerfil(new Perfil());
		}
		novo= false;
		viewUsuario = false;
	}
	
	
	
	public void redirectEdit(Usuario usuario) {
		this.usuario = usuario;
		
		if(this.usuario != null) {
			
			FacesUtil.addParamSession(TipoPagina.EDIT_USUARIO);
			
			if(usuario == null){
				usuario = new Usuario();
				usuario.setPerfil(new Perfil());
			}
			
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("usuario", this.usuario);
			
			isEdit();
			visualizar = false;
			editar = true;
			pesquisar = false;
			novo= true;
			viewUsuario = false;
			
		} else {
			FacesUtil.addErrorMessage("Favor selecionar um usuário!");
			return ;
			
		}
	}	
	
	public String atualizar() {
		
		try {
			this.usuario.setAtivo(true);
			this.usuario.setDataAtualizacao(new Date());
			this.usuario.setUsuarioAtualizacao(usuarioLogado);
			this.usuarioService.atualizar(this.usuario);
			limparObjeto();
			usuarios = this.usuarioService.getByFilter(filtroLogin, filtroNome, filtroPerfil);
			FacesUtil.addInfoMessage("Registro alterado com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
			
		}
		return "consultar_usuario";
	}
	
	public void visualizar(Usuario usuario) {
		this.usuario = usuario;
		if(this.usuario != null) {
			
			FacesUtil.addParamSession(TipoPagina.VISUALIZAR_PROF);
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("usuario", this.usuario);
			novo = true;
			viewUsuario = true;
		} else {
			
			FacesUtil.addErrorMessage("Favor selecionar um profissional!");
			return ;
			
		}
		
		
	}
	
	
	public void preExcluir(Usuario usuario){
		this.usuario = usuario;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluir').show();");
	}
	
	public void excluir() {
		try {
			this.usuario.setDataAtualizacao(new Date());
			this.usuario.setUsuarioAtualizacao(usuarioLogado);
			this.usuario.setAtivo(false);
			this.usuarioService.atualizar(this.usuario);

			usuarios = new ArrayList<>();
			usuarios = this.usuarioService.getByFilter(filtroLogin, filtroNome, filtroPerfil);
			
			FacesUtil.addInfoMessage("Registro excluido com sucesso!");
			
		} catch (Exception e) {
			
			FacesUtil.addErrorMessage(e.getMessage());
			
		}
	}
	
	public void alterarSenha() {
		try {
			if(senhaNova != null){
				this.usuario.setSenha(senhaNova);
				this.usuario.setDataInclusao(new Date());
				this.usuarioService.salvar(this.usuario);
				usuarios = this.usuarioService.getByFilter(filtroLogin, filtroNome, filtroPerfil);
			FacesUtil.addInfoMessage("Registro salvo com sucesso.");
			}else{
				FacesUtil.addErrorMessage("Infome a nova senha!");
				return;
			}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(" Erro ao alterar a senha: " + e.getMessage());
		}
		
	}
	
	
	public void salvar() {
		try {
			List<Usuario> usuarioIncluir = usuarioService.getAll();
			Boolean jaPOssui = false;
			
			for (Usuario item : usuarioIncluir) {
				if(item.getLogin().equals(usuario.getLogin())){
					jaPOssui = true;
					break;
				}
			}
			
			if(jaPOssui){
				FacesUtil.addErrorMessage("O login informado já existe na base de dados.");
				return;
			}
			
			if(idPerfil != null || idPerfil !=0){
				this.usuario.setPerfil(perfilService.getById(idPerfil));
			}else{
				
				FacesUtil.addErrorMessage("Informe o perfil.");
				return;
			}
			//this.setU
			this.usuario.setDataInclusao(new Date());
			this.usuario.setUsuarioAtualizacao(usuarioLogado);
			this.usuario.setAtivo(true);
			
			this.usuarioService.salvar(this.usuario);
			limparObjeto();
			usuarios = this.usuarioService.getByFilter(filtroLogin, filtroNome, filtroPerfil);
			FacesUtil.addInfoMessage("Registro salvo com sucesso.");
		} catch (NegocioException e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}
		
		visualizar = false;
		editar = false;
		pesquisar = true;
		 
	}
	public Boolean isEdit() {
		if(usuario == null){
			return true;
		}
		return this.usuario.isExistente() && !viewUsuario;
		
	}
	
	public void redirectNovo() {
		FacesUtil.addParamSession(TipoPagina.NOVO_USUARIO);
		isNew();
		visualizar = false;
		editar = true;
		pesquisar = false;
		viewUsuario = false;
		novo = true;
	
}

	public void redirectConsultar() {
		FacesUtil.addParamSession(TipoPagina.NOVO_USUARIO);
		isNew();
		visualizar = false;
		editar = false;
		pesquisar = true;
		novo = false;
 
	}


	public Boolean isNew() {
		
		if(usuario == null){
			return true;
		}
		return this.usuario.isNovo();
		
	}
	
	public void limparObjeto() {
		this.usuario = new Usuario();
		idPerfil = null;
		pesquisar = true;
		visualizar = false;
		editar = false;
		
	}
	

	public IUsuario getUsuario() {
		return usuario;
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

	public void setUsuario(IUsuario usuario) {
		this.usuario = usuario;
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public Boolean getViewUsuario() {
		return viewUsuario;
	}

	public String getFiltroLogin() {
		return filtroLogin;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public String getFiltroPerfil() {
		return filtroPerfil;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public Long getIdPerfil() {
		return idPerfil;
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

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public void setViewUsuario(Boolean viewUsuario) {
		this.viewUsuario = viewUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public void setFiltroLogin(String filtroLogin) {
		this.filtroLogin = filtroLogin;
	}

	public void setFiltroPerfil(String filtroPerfil) {
		this.filtroPerfil = filtroPerfil;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
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

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
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
