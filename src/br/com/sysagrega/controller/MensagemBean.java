package br.com.sysagrega.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.sysagrega.controller.Qualificadores.QualificadorRecurso;
import br.com.sysagrega.model.IMensagem;
import br.com.sysagrega.model.imp.Mensagem;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.service.IMensagemService;
import br.com.sysagrega.service.IRecursoService;
import br.com.sysagrega.service.IUsuarioService;
import br.com.sysagrega.service.imp.ProfissionalService;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class MensagemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IMensagemService mensagemService;
	
	@Inject
	private ProfissionalService profissionalService;
	
	@Inject
	private IUsuarioService usuarioService; 
	
	@Produces
	@QualificadorRecurso
	private IMensagem mensagem;

	private boolean viewRecurso;
	
	
	private List<Mensagem> listaMensagesByUsuario;
	private Usuario usuarioLogado;

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
		  // chama o metodo para obter as mensagens.
		  obterMensagens();
 
	}
	/*
	 * Obtem as menagens por login de usuario.
	 */
	public void obterMensagens(){
		if(usuarioLogado == null){
			 SecurityContext contextString = SecurityContextHolder.getContext();
			  	if (contextString instanceof SecurityContext) {
			  Authentication authentication = contextString.getAuthentication();
			  if (authentication instanceof Authentication) {
				  usuarioLogado = usuarioService.getUserPerfilByLogin(authentication.getName());
			  	}
		     }
		}
		Mensagem filtro = new Mensagem();
		filtro.setProfissional(profissionalService.obterProfissionalByLogin(usuarioLogado));
		listaMensagesByUsuario = mensagemService.getByFilter(filtro);
		
		/*if(listaMensagesByUsuario != null && listaMensagesByUsuario.size()>0){
			FacesUtil.addInfoMessage("Mensagens atualizadas com sucesso.");
		}else{
			//FacesUtil.addErrorMessage("Você não possui mensagens novas");	
		}*/
	}
	
	public void atualizar(Mensagem resulte){
		resulte.setLida(true);
		mensagemService.atualizar(resulte);
		FacesUtil.addInfoMessage("Mensagem lida com sucesso.");
		
	}
	
	public boolean isViewRecurso() {
		return viewRecurso;
	}

	public void setViewRecurso(boolean viewRecurso) {
		this.viewRecurso = viewRecurso;
	}

	public List<Mensagem> getListaMensagesByUsuario() {
		return listaMensagesByUsuario;
	}

	public void setListaMensagesByUsuario(List<Mensagem> listaMensagesByUsuario) {
		this.listaMensagesByUsuario = listaMensagesByUsuario;
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

	public IMensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(IMensagem mensagem) {
		this.mensagem = mensagem;
	}

 
	
}
