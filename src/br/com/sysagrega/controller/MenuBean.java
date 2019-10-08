 package br.com.sysagrega.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.Enums.EPerfil;
import br.com.sysagrega.model.Enums.TipoPagina;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.service.IProfissionalService;
import br.com.sysagrega.service.IUsuarioService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class MenuBean implements Serializable {

	private Usuario usuario;
	private String senhaNova;
	private String senhaAtual;
	
	// Booleanos
	private Boolean diretor ;
	private Boolean secretria;
	private Boolean recepcao;
	private Boolean adm;
	private Boolean operacional;
	@Inject
	private IUsuarioService usuarioService; 
	
	@Inject
	private IProfissionalService profissionalService; 
	
	private List<String> perfis = new ArrayList<String>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public void alterarSenha() {
		try {
			RequestContext context = RequestContext.getCurrentInstance();
			if(senhaNova != null && usuario != null && senhaAtual != null){
				
				if(!senhaAtual.equals(usuario.getSenha())){
					FacesUtil.addErrorMessage("Digite sua SENHA ATUAL corretamente.");
					context.execute("PF('modalAlterarSenha').show();");
					return;
				}
				
				this.usuario.setSenha(senhaNova);
				this.usuario.setDataInclusao(new Date());
				this.usuarioService.salvar(this.usuario);
			FacesUtil.addInfoMessage("Senha alterada com sucesso.");
			}else{
				FacesUtil.addErrorMessage("Infome a nova senha!");
				context.execute("PF('modalAlterarSenha').show();");
				return;
			}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(" Erro ao alterar a senha: " + e.getMessage());
		}
		
	}
	
	public void abrirModal(){
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('modalAlterarSenha').show();");
	}
	
	
    public String logOff() {

        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        if (sessao != null) {
            sessao.invalidate();
        }

        return "login";
    }
    
    @RequestMapping(value="/logout",  method= RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
    
    private  IProfissional profissional;
    @PostConstruct
    public void init() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
            	usuario = usuarioService.getUserPerfilByLogin(authentication.getName());
            	Iterator itr = authentication.getAuthorities().iterator();
    	        while(itr.hasNext()) {
    	           Object element = itr.next();
    	           perfis.add(element.toString());
    	  }
        }
      }
        
        profissional = profissionalService.obterProfissionalByLogin(usuario);
        if(profissional == null){
        	FacesUtil.addInfoMessage("Você não possui permissão para acessar o sistema Integra. Por favor, entre em contato com o setor Administrativo.");
        	return;
        }
        
        isAdm();
        isDiretor();
        isOperacional();
        isRecepcao();
    }
   
    public boolean isDiretor(){
  		for (String perfil : getPerfis()) {
  			if(perfil.equals(EPerfil.DIRETOR.getDescricao())){
  				diretor = true;
  				return true;
  			}
  		}
  		diretor = false;
  		return false;
  	}
    
    public boolean isSecretaria(){
  		for (String perfil : getPerfis()) {
  			if(perfil.equals(EPerfil.SECRETARIA.getDescricao())){
  				secretria = true;
  				return true;
  				
  			}
  		}
  		secretria = false;
  		return false;
  	}
    
    public boolean isRecepcao(){
  		for (String perfil : getPerfis()) {
  			if(perfil.equals(EPerfil.RECEPCAO.getDescricao())){
  				recepcao = true;
  				return true;
  			}
  		}
  		recepcao = false;
  		return false;
  	}
    
    public boolean isAdm(){
  		for (String perfil : getPerfis()) {
  			if(perfil.equals(EPerfil.ADM.getDescricao())){
  				adm = true;
  				return true;
  			}
  		}
  		adm = false;
  		return false;
  	}
    
    public boolean isOperacional(){
  		for (String perfil : getPerfis()) {
  			if(perfil.equals(EPerfil.OPERACIONAL.getDescricao())){
  				operacional = true;
  				return true;
  			}
  		}
  		operacional = false;
  		return false;
  	}
    
    
    

	public String novoProfissional() {
		FacesUtil.addParamSession(TipoPagina.NOVO_PROF);
		return "cadastro_profissional";

	}

	public String consultarProfissional() {
		FacesUtil.addParamSession(TipoPagina.CONSULTA_PROF);
		return "consultar_profissional";
	}

	public String novoCliente() {
		FacesUtil.addParamSession(TipoPagina.NOVO_CLIENTE);
		return "cadastro_cliente";

	}

	public String consultarCliente() {
		FacesUtil.addParamSession(TipoPagina.CONSULTA_CLIENTE);
		return "consultar_cliente";
	}
	
	public String novoFornecedor() {
		FacesUtil.addParamSession(TipoPagina.NOVO_FORNEC);
		return "cadastro_fornecedor";

	}
	
	public String consultarFornecedor() {

		// Set parametro na sessão
		FacesUtil.addParamSession(TipoPagina.CONSULTA_FORNEC);
		return "consultar_fornecedor";
	}

	public String novaProposta() {
		FacesUtil.addParamSession(TipoPagina.NOVA_PROPOSTA);
		return "cadastro_proposta";
	}

	public String consultarProposta() {
		FacesUtil.addParamSession(TipoPagina.CONSULTA_PROPOSTA);
		return "consultar_proposta";
	}

	public String novoContrato() {
		FacesUtil.addParamSession(TipoPagina.NOVO_CONTRATO);
		return "cadastro_contrato";
	}

	public String consultarContrato() {
		FacesUtil.addParamSession(TipoPagina.CONSULTA_CONTRATO);
		return "consultar_contrato";
	}

	public void reset() {
		RequestContext.getCurrentInstance().reset("formPrincipal:layoutCentro");

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public List<String> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<String> perfis) {
		this.perfis = perfis;
	}

	public Boolean getDiretor() {
		return diretor;
	}

	public void setDiretor(Boolean diretor) {
		this.diretor = diretor;
	}

	public Boolean getSecretria() {
		return secretria;
	}

	public void setSecretria(Boolean secretria) {
		this.secretria = secretria;
	}

	public Boolean getRecepcao() {
		return recepcao;
	}

	public void setRecepcao(Boolean recepcao) {
		this.recepcao = recepcao;
	}

	public Boolean getAdm() {
		return adm;
	}

	public void setAdm(Boolean adm) {
		this.adm = adm;
	}

	public Boolean getOperacional() {
		return operacional;
	}

	public void setOperacional(Boolean operacional) {
		this.operacional = operacional;
	}

	public IProfissional getProfissional() {
		return profissional;
	}

	public void setProfissional(IProfissional profissional) {
		this.profissional = profissional;
	}
	
	
}
