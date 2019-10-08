	package br.com.sysagrega.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.sysagrega.controller.Qualificadores.QualificadorProfissional;
import br.com.sysagrega.model.IEndereco;
import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.IUsuario;
import br.com.sysagrega.model.Enums.ETipoEntidade;
import br.com.sysagrega.model.Enums.TipoColaborador;
import br.com.sysagrega.model.Enums.TipoContaBancaria;
import br.com.sysagrega.model.Enums.TipoContratacao;
import br.com.sysagrega.model.Enums.TipoDisponibilidade;
import br.com.sysagrega.model.Enums.TipoEscolaridade;
import br.com.sysagrega.model.Enums.TipoPagina;
import br.com.sysagrega.model.imp.Anexo;
import br.com.sysagrega.model.imp.Banco;
import br.com.sysagrega.model.imp.Cidade;
import br.com.sysagrega.model.imp.DadosBancarios;
import br.com.sysagrega.model.imp.Endereco;
import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.model.imp.Perfil;
import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.service.IAnexoService;
import br.com.sysagrega.service.IBancoService;
import br.com.sysagrega.service.ICidadeService;
import br.com.sysagrega.service.IEstadoService;
import br.com.sysagrega.service.IProfissionalService;
import br.com.sysagrega.service.IUsuarioService;
import br.com.sysagrega.service.imp.EnderecoService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.service.imp.PerfilService;
import br.com.sysagrega.util.Constante;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ProfissionalBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IProfissionalService profissionalService;
	@Inject
	private EnderecoService enderecoService;
	@Inject
	private IUsuarioService usuarioService; 
	@Inject
	private IEstadoService estadoService;
	@Inject
	private ICidadeService cidadeService;
	@Inject
	private IBancoService bancoService;
	@Inject
	private PerfilService perfilService;
	@Inject
	private IAnexoService anexoService;

	@Produces
	@QualificadorProfissional
	private IProfissional profissional;
	private IUsuario usuario;
	private String filtroCpf;
	private String filtroRg;
	private String filtroNome;
	private String filtroTipoColaborador;
	private String loginAcesso;
	private Anexo excluirAnexo;
	private List<Estado> estadosPj;
	private List<Estado> estados;
	private List<Cidade> cidades;
	private List<Cidade> cidadesPj;
	private List<Banco> bancos;
	private List<String> tiposConta;
	private List<String> tipoContratacao;
	private List<String> tipoColaborador;
	private List<String> tipoDisponibilidade;
	private List<String> tipoEscolaridade;
	private List<Profissional> profissionais;
	
	private boolean viewProfissional;
	private boolean disableCity;
	private boolean disableCityPj;
	private boolean exibirAssociar = false;
	private Long tipoAssociar;
	private Boolean exibirDadosAcesso;
	private Boolean pesquisar = true;
	private Boolean visualizar = false;
	private Boolean editar = false;
	private Boolean verProfissional = false;
	private Boolean novo = false;
	private Boolean pessoaJuridicaParceiro = false;
	
	private List<Anexo> anexos;
	private FileUploadEvent fileUploadEvent;
	private List<FileUploadEvent> listaArquivosAnexados;
	private StreamedContent file;
	private List<Perfil>perfis;
	private List<Usuario>usuarios;
	private Long idPerfil;
	private Long idUsuario;
	
	private Usuario usuarioLogado;
	// Manages Propertys
	@ManagedProperty(value="#{menuFaces}")
	private MenuBean menuBean;
	
	@ManagedProperty(value="#{fornecedorBean}")
	private FornecedorBean fornecedorBean;

	
	
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
		usuario = new Usuario();
		estados = new ArrayList<>();
		estadosPj = new ArrayList<>();
		cidades = new ArrayList<>();
		cidadesPj = new ArrayList<>();
		bancos = new ArrayList<>();
		tiposConta = new ArrayList<>();
		tipoAssociar = 2L;
		estados = estadoService.getAllEstados();
		estadosPj = estadoService.getAllEstados();
		bancos = bancoService.getAllBancos();
		usuarios = usuarioService.getAll();
		carregarEnumsProfissional();
		carregaContaBancos();
		listaProfissional();
		visualizar = false;
		editar = false;
		pesquisar = true;
		novo = false;
		perfis = perfilService.getAll();
	}

	private void listaProfissional() {
		if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.CONSULTA_PROF)) {
			carregarTodosProfissionais();
		} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.EDIT_PROFI)) {
			this.profissional = FacesUtil.getProfissionalSession();
			carregarCidadesPorEstado();
			carregarCidadesPorEstadoPj();
		} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.NOVO_PROF)) {
			limparObjeto();
		} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.VISUALIZAR_PROF)) {
			this.profissional = FacesUtil.getProfissionalSession();
			carregarCidadesPorEstado();
			carregarCidadesPorEstadoPj();
			viewProfissional = true;
		}
	}

	private void carregaContaBancos() {
		for (TipoContaBancaria tipos : TipoContaBancaria.values()) {
			tiposConta.add(tipos.getDescricao());
		}
	}
 	
	public void verificarTipoColaborador(){
		pessoaJuridicaParceiro = false;
		if(this.profissional.getTipoColaborador() != null && this.profissional.getTipoContratacao() != null){
			String tpColaborador = this.profissional.getTipoColaborador();
			String tpContratacao = this.profissional.getTipoContratacao();
			
			if(tpColaborador.equals(TipoColaborador.PARCEIRO.getDescricao()) && tpContratacao.equals(TipoContratacao.PJ.getDescricao())){
				pessoaJuridicaParceiro = true;
			}else{
				pessoaJuridicaParceiro = false;
			}
		}
	}
	
	private void carregarEnumsProfissional() {
		
		tipoContratacao = new ArrayList<>();
		tipoColaborador = new ArrayList<>();
		tipoDisponibilidade = new ArrayList<>();
		tipoEscolaridade = new ArrayList<>();
		
		//Carrrega contratacao
		for (TipoContratacao tipo : TipoContratacao.values()) {
			tipoContratacao.add(tipo.getDescricao());
		}
		
		for (TipoColaborador seg : TipoColaborador.values()) {
			tipoColaborador.add(seg.getDescricao());
		}
		
		for (TipoDisponibilidade seg : TipoDisponibilidade.values()) {
			tipoDisponibilidade.add(seg.getDescricao());
		}
		
		for (TipoEscolaridade seg : TipoEscolaridade.values()) {
			tipoEscolaridade.add(seg.getDescricao());
		}
		
	}
	
	private void salvarListaAnexos() {
		if (listaArquivosAnexados != null) {
			for (FileUploadEvent arquivoAnexo : listaArquivosAnexados) {
				Anexo anexo = null;
				try {
					if(anexoService.getByIdByTipo(ETipoEntidade.PROFISSIONAL.getFlag(), profissional.getId(), arquivoAnexo.getFile().getFileName()).size()==0){
						anexo = new Anexo();
						anexo.setDescricao(arquivoAnexo.getFile().getFileName());
						anexo.setIdEntidade(profissional.getId());
						anexo.setTipo(ETipoEntidade.PROFISSIONAL.getFlag());
						anexoService.salvar(anexo);
					}
				} catch (Exception e) {
					FacesUtil.addErrorMessage("Não é possível salvar o anexo.");
				}
			
			}
		}
		preencherListaAnexos();
	}
	
	public void arquivoUpload(FileUploadEvent uploadedFile) {
		  try {
			
			if(anexoService.getByIdByTipo(ETipoEntidade.PROFISSIONAL.getFlag(), profissional.getId(), uploadedFile.getFile().getFileName()).size() >0){
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Não é possível salvar o anexo "+ uploadedFile.getFile().getFileName() +". O mesmo já existe no banco de dados.", ""));
				return;
			}
		    File file = new File(Constante.PROFISSIONAL, uploadedFile.getFile().getFileName());
		 
		    OutputStream out = new FileOutputStream(file);
		    out.write(uploadedFile.getFile().getContents());
		    out.close();
			listaArquivosAnexados = new ArrayList<>();
			listaArquivosAnexados.add(uploadedFile);
		 
			Anexo anexo = new Anexo();
			anexo.setDescricao(uploadedFile.getFile().getFileName());

			if (anexos == null) {
				anexos = new ArrayList<>();
			}
			getAnexos().add(anexo);
			salvarListaAnexos();
		    RequestContext context = RequestContext.getCurrentInstance();
			context.update("tabelaListaAnexoSelecionado");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,"O arquivo " + uploadedFile.getFile() + " foi salvo!", ""));
		  } catch(IOException e) {
		    FacesContext.getCurrentInstance().addMessage(
		              null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		  }
	}
	
	public static String obterCaminhoAnexo() {
		return "C:\\DADOS_SISTEMA\\AGREGA\\ANEXOS\\PROFISSIONAL\\";
	}
	
	public StreamedContent downloadAnexo(String arquivo) { 
		try {
			String caminho = obterCaminhoAnexo() + arquivo;
			FileInputStream stream = new FileInputStream(caminho);
			file = new DefaultStreamedContent(stream, caminho, arquivo);
		} catch (FileNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Anexo não encontrado.", ""));
			return null;
		}
		return file;
	}
	
	public List<Anexo> preencherListaAnexos() {
		if(profissional != null && profissional.getId() != null){
			return anexos = anexoService.getByIdByTipo(ETipoEntidade.PROFISSIONAL.getFlag(), profissional.getId(), null);
		}
		return null;
	}
	
	public void excluirAnexo() {
		if (excluirAnexo != null && excluirAnexo.getId() != null) {
			anexoService.excluir(excluirAnexo);
			if(obterCaminhoAnexo() != null){
				File deletarArquivoDiretorio = new File(new StringBuilder(obterCaminhoAnexo()).append(File.separator).append(excluirAnexo.getDescricao()).toString());
				deletarArquivoDiretorio.delete();
			}
			preencherListaAnexos();
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
						itemTemp.setIdEntidade(profissional.getId());
						itemTemp.setTipo(ETipoEntidade.PROFISSIONAL.getFlag());
						anexos.add(itemTemp);
					}
				}
			}
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,"Anexo excluído com sucesso.", ""));
	}

	
	private void limparObjeto() {
		this.profissional = new Profissional();
		this.profissional.setEndereco(new Endereco());
		this.profissional.setEnderecoPj(new Endereco());
		this.profissional.setDadosBancarios(new DadosBancarios());
		disableCity = true;
		disableCityPj = true;
	}

	public void carregarCidadesPorEstado() {
		if(this.profissional != null && this.profissional.getEndereco() != null && this.profissional.getEndereco().getEstado() != null){
			cidades = listCidade(this.profissional.getEndereco().getEstado().getId());
			disableCity = false;
		}
	}
	
	public void carregarCidadesPorEstadoPj() {
		if(this.profissional != null && this.profissional.getEnderecoPj() != null && this.profissional.getEnderecoPj().getEstado() != null){
			cidadesPj = listCidade(this.profissional.getEnderecoPj().getEstado().getId());
			disableCityPj = false;
		}
	}
	
	public List<Cidade> listCidade(Long id){
		return cidadeService.getCidadesByEstadoId(id);
	}

	
	public void salvarProfissional() {
		this.profissional.setUsuarioAcesso(null);
		try{
			if(profissional.getAcessarIntegra()){
				if(idPerfil != null){
					this.usuario.setPerfil(perfilService.getById(idPerfil));
				}
				validaSalvaUsuarioAcesso();
			}
			this.profissional.setDataInclusao(new Date());
			this.profissional.setUsuarioAtualizacao(usuarioLogado);
			this.profissional.setAtivo(true);
			this.profissionalService.salvar(this.profissional);
			limparObjeto();
			visualizar = false;
			editar = false;
			pesquisar = true;
			novo= false;
			FacesUtil.menssagemSucesso("Profissional salvo com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.menssagemErro("Erro ao Salvar profissional - "+e.getMessage());
		}
	}

	
	public void habilitaDadosAcesso(){
		exibirDadosAcesso = false;
		if(profissional.getAcessarIntegra()!= null && profissional.getAcessarIntegra()){
			exibirDadosAcesso = true;
		}
	}
	
	public void validaSalvaUsuarioAcesso()throws NegocioException{
		salvarDadosAcesso();
		if(profissional.getUsuarioAcesso()!= null){
			//atualiza usuario
			usuario.setUsuarioAtualizacao(usuarioLogado);
			usuario.setDataAtualizacao(new Date());
			usuario = usuarioService.atualizar(usuario);
		}else{
			//novo usuario
			validaNovoUsuario();
			usuario.setUsuarioAtualizacao(usuarioLogado);
			usuario.setDataInclusao(new Date());
			usuario = usuarioService.salvar(usuario);
		}
		profissional.setUsuarioAcesso(usuarioService.getById(usuario.getId()));
	}

	private void validaNovoUsuario() {
		List<Usuario> listLoginExistentes = usuarioService.getAll();
		for (Usuario item : listLoginExistentes) {
			if((item.getLogin().equals(usuario.getLogin()))){
				usuario.setLogin(null);
				throw new NegocioException("O login informado já existe na base de dados, favor informe outro.");
			}
		}
	}

	private void salvarDadosAcesso() {
		this.usuario.setNome(profissional.getNome());
		this.usuario.setEmail(profissional.getEmail());
		this.usuario.setTelefone(profissional.getCelular());
		this.usuario.setAtivo(true);
		this.usuario.setFuncao(profissional.getFuncaoCargo());
	}

	private Boolean verificaLoginExistente(List<Usuario> usuarioIncluir, Boolean jaPossui) {
		for (Usuario item : usuarioIncluir) {
			if(item.getLogin().equals(usuario.getLogin())){
				if(profissional.getUsuarioAcesso() != null && !loginAcesso.equals(usuario.getLogin())){
					jaPossui = true;
					break;
				}
			}
		}
		return jaPossui;
	}

	public void atualizarProfissional() throws NegocioException{
		try {
			if(profissional.getAcessarIntegra()){
				if(idPerfil != null){
					this.usuario.setPerfil(perfilService.getById(idPerfil));
				}
				validaSalvaUsuarioAcesso();
			}else{
				if(profissional.getUsuarioAcesso()!= null){
					Usuario usuario = usuarioService.getById(profissional.getUsuarioAcesso().getId());
					usuario.setSenha(null);
					usuario.setPerfil(null);
					usuario.setUsuarioAtualizacao(usuarioLogado);
					usuario.setDataAtualizacao(new Date());
					usuarioService.atualizar(usuario);
				}
			}
			this.profissional.setDataAtualizacao(new Date());
			this.profissional.setUsuarioAtualizacao(usuarioLogado);
			this.profissional.setAtivo(true);
				
			if(profissional.getTipoColaborador().equals(TipoColaborador.PARCEIRO.getDescricao()) && profissional.getTipoContratacao().equals(TipoContratacao.PJ.getDescricao())){
				IEndereco enderecoPJ = enderecoService.salvar(profissional.getEnderecoPj());
				profissional.setEnderecoPj(enderecoPJ);
			}
			profissionalService.atualizarProfissional(this.profissional);
			FacesUtil.menssagemSucesso("Registro atualizado com sucesso.");
		} catch (Exception e) {
			FacesUtil.menssagemErro("Erro ao Salvar profissional - "+e.getMessage());
		}
	}
	
	public void excluirProfissional() {
		try {
			this.profissional.setDataAtualizacao(new Date());
			this.profissional.setUsuarioAtualizacao(usuarioLogado);
			this.profissional.setAtivo(false);
			this.profissionalService.atualizarProfissional(this.profissional);
			carregarTodosProfissionais();
			FacesUtil.menssagemSucesso("Profissional excluido com sucesso.");
		} catch (Exception e) {
			FacesUtil.menssagemErro("Erro ao excluir Profissional - Erro "+e.getMessage());
		}
	}
	
	public void preExcluir(Profissional profissional){
		this.profissional = profissional;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluir').show();");
	}

	public Boolean isDisableCidades() {
		if (profissional.getEndereco().getEstado() == null || profissional.getEndereco().getEstado().getId() == null) {
			return true;
		}
		return false;
	}


	private void carregarTodosProfissionais() {
		profissionais = new ArrayList<>();
		profissionais = this.profissionalService.getProfissionalByFilter(this.filtroNome, this.filtroCpf, this.filtroTipoColaborador);
	}
	
	
	public void redirectEdit(Profissional profissional) {
		this.profissional = new Profissional();
		this.profissional = profissional;
		loginAcesso = "";
		if(profissional.getUsuarioAcesso()!= null){
			loginAcesso = profissional.getUsuarioAcesso().getLogin();
		}
		
		if(this.profissional.getEnderecoPj() == null){
			this.profissional.setEnderecoPj(new Endereco());
		}
		
		FacesUtil.addParamSession(TipoPagina.EDIT_PROFI);
			
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("profissional", this.profissional);
		verificarTipoColaborador();
		isEditProfissional();
		disableCity = false;
		disableCityPj = false;
		estados = estadoService.getAllEstados();
		carregarCidadesPorEstado();
		carregarCidadesPorEstadoPj();
		visualizar = false;
		idUsuario = null;
		usuario = new Usuario();
		idPerfil = null;
		editar = true;
		pesquisar = false;
		novo= false;
		viewProfissional = false;
		tipoAssociar = 2L;
		preencherListaAnexos();
		verificaUsuarioAcesso(profissional);
		habilitaDadosAcesso();
	}
	
	public void voltar(){
		if(profissional == null){
			profissional = new Profissional();
		}
		profissionais = this.profissionalService.getProfissionalByFilter(this.filtroNome, this.filtroCpf, this.filtroTipoColaborador);
		novo= false;
		pesquisar = true;
		viewProfissional = false;
	}
	
		
	public void novoProfissional(){
		profissional = new Profissional();
		profissional.setEndereco(new Endereco());
		profissional.setEnderecoPj(new Endereco());
		profissional.setDadosBancarios(new DadosBancarios());
		profissional.setUsuarioAcesso(new Usuario());
		usuario = new Usuario();
		idPerfil = null;
		usuarios = usuarioService.getAll();
		idUsuario = null;
		novo= true;
		pesquisar = false;
		editar = false;
		visualizar = false;
		viewProfissional = false;
		habilitaDadosAcesso();
		anexos = new ArrayList<>();
		
	}
	
	public String redirectNovoProfissional() {
		FacesUtil.addParamSession(TipoPagina.NOVO_PROF);
		isNewProfissional();
		return "cadastro_profissional";
	}
	
	public void visualizar(Profissional profissional) {
		this.profissional = profissional;
		
		if(this.profissional.getEnderecoPj() == null){
			this.profissional.setEnderecoPj(new Endereco());
		}
		
		FacesUtil.addParamSession(TipoPagina.VISUALIZAR);	
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("profissional", this.profissional);
		idPerfil = null;
		usuario = new Usuario();
		idUsuario = null;
		verificarTipoColaborador();
		visualizar = true;
		pesquisar = false;
		novo = false;
		editar = false;
		viewProfissional = true;
		preencherListaAnexos();
		verificaUsuarioAcesso(profissional);
		habilitaDadosAcesso();
		tipoAssociar = 2L;
	}

	private void verificaUsuarioAcesso(Profissional profissional) {
		idPerfil = null;
		idUsuario = null;
		if(profissional.getUsuarioAcesso() != null && profissional.getUsuarioAcesso().getId() != null){
			if(profissional.getUsuarioAcesso().getPerfil()!= null){
				idPerfil = profissional.getUsuarioAcesso().getPerfil().getId(); 
			}
			usuario = profissional.getUsuarioAcesso();
			idUsuario = profissional.getUsuarioAcesso().getId();
		}
	}	
	
	/**
	 * @param profissional
	 * @return Boolean
	 * @author Elton
	 */
	public Boolean isEditProfissional() {
		if(profissional == null){
			return false;
		}
		return this.profissional.isExistente() && !viewProfissional;
	}
	
	/**
	 * @param profissional
	 * @return Boolean
	 * @author Elton
	 */
	public Boolean isNewProfissional() {
		if(profissional == null){
			return true;
		}
		return this.profissional.isNovo();
	}
	
	public void pesquisarProfissional() {
		profissionais = new ArrayList<>();
		profissionais = this.profissionalService.getProfissionalByFilter(this.filtroNome, this.filtroCpf, this.filtroTipoColaborador);
		
		if(profissionais == null || profissionais.isEmpty()){
			FacesUtil.menssagemErro("O filtro não retornou nenhum registro.");
		}
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public IProfissional getProfissional() {
		return profissional;
	}

	public void setProfissional(IProfissional profissional) {
		this.profissional = profissional;
	}

	public List<Banco> getBancos() {
		return bancos;
	}

	public void setBancos(List<Banco> bancos) {
		this.bancos = bancos;
	}

	public List<String> getTiposConta() {
		return tiposConta;
	}

	public void setTiposConta(List<String> tiposConta) {
		this.tiposConta = tiposConta;
	}

	public List<Profissional> getProfissionais() {
		return profissionais;
	}

	public void setProfissionais(List<Profissional> profissionais) {
		this.profissionais = profissionais;
	}

	public boolean getViewProfissional() {
		return viewProfissional;
	}

	public void setViewProfissional(boolean viewProfissional) {
		this.viewProfissional = viewProfissional;
	}

	public boolean isDisableCity() {
		return disableCity;
	}

	public void setDisableCity(boolean disableCity) {
		this.disableCity = disableCity;
	}
	
	public boolean isDisableCityPj() {
		return disableCityPj;
	}

	public void setDisableCityPj(boolean disableCityPj) {
		this.disableCityPj = disableCityPj;
	}

	public String getFiltroCpf() {
		return filtroCpf;
	}

	public void setFiltroCpf(String filtroCpf) {
		this.filtroCpf = filtroCpf;
	}

	public String getFiltroRg() {
		return filtroRg;
	}

	public void setFiltroRg(String filtroRg) {
		this.filtroRg = filtroRg;
	}
	public List<Anexo> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<Anexo> anexos) {
		this.anexos = anexos;
	}

	public Anexo getExcluirAnexo() {
		return excluirAnexo;
	}

	public void setExcluirAnexo(Anexo excluirAnexo) {
		this.excluirAnexo = excluirAnexo;
	}

	public List<FileUploadEvent> getListaArquivosAnexados() {
		return listaArquivosAnexados;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setListaArquivosAnexados(List<FileUploadEvent> listaArquivosAnexados) {
		this.listaArquivosAnexados = listaArquivosAnexados;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}


	public FileUploadEvent getFileUploadEvent() {
		arquivoUpload(fileUploadEvent);
		return fileUploadEvent;
	}


	public void setFileUploadEvent(FileUploadEvent fileUploadEvent) {
		this.fileUploadEvent = fileUploadEvent;
	}

	public List<String> getTipoContratacao() {
		return tipoContratacao;
	}

	public List<String> getTipoColaborador() {
		return tipoColaborador;
	}

	public void setTipoContratacao(List<String> tipoContratacao) {
		this.tipoContratacao = tipoContratacao;
	}

	public void setTipoColaborador(List<String> tipoColaborador) {
		this.tipoColaborador = tipoColaborador;
	}

	public List<String> getTipoDisponibilidade() {
		return tipoDisponibilidade;
	}

	public void setTipoDisponibilidade(List<String> tipoDisponibilidade) {
		this.tipoDisponibilidade = tipoDisponibilidade;
	}

	public List<String> getTipoEscolaridade() {
		return tipoEscolaridade;
	}

	public void setTipoEscolaridade(List<String> tipoEscolaridade) {
		this.tipoEscolaridade = tipoEscolaridade;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public String getFiltroTipoColaborador() {
		return filtroTipoColaborador;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	public void setFiltroTipoColaborador(String filtroTipoColaborador) {
		this.filtroTipoColaborador = filtroTipoColaborador;
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

	public Boolean getVerProfissional() {
		return verProfissional;
	}

	public void setVerProfissional(Boolean verProfissional) {
		this.verProfissional = verProfissional;
	}

	public Boolean getNovo() {
		return novo;
	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

	public Boolean getPessoaJuridicaParceiro() {
		return pessoaJuridicaParceiro;
	}


	public void setPessoaJuridicaParceiro(Boolean pessoaJuridicaParceiro) {
		this.pessoaJuridicaParceiro = pessoaJuridicaParceiro;
	}

	public FornecedorBean getFornecedorBean() {
		return fornecedorBean;
	}

	public void setFornecedorBean(FornecedorBean fornecedorBean) {
		this.fornecedorBean = fornecedorBean;
	}

	public List<Estado> getEstadosPj() {
		return estadosPj;
	}

	public void setEstadosPj(List<Estado> estadosPj) {
		this.estadosPj = estadosPj;
	}

	public List<Cidade> getCidadesPj() {
		return cidadesPj;
	}

	public void setCidadesPj(List<Cidade> cidadesPj) {
		this.cidadesPj = cidadesPj;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public IUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(IUsuario usuario) {
		this.usuario = usuario;
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

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean isExibirAssociar() {
		return exibirAssociar;
	}

	public void setExibirAssociar(boolean exibirAssociar) {
		this.exibirAssociar = exibirAssociar;
	}

	public Long getTipoAssociar() {
		return tipoAssociar;
	}

	public void setTipoAssociar(Long tipoAssociar) {
		this.tipoAssociar = tipoAssociar;
	}

	public Boolean getExibirDadosAcesso() {
		return exibirDadosAcesso;
	}

	public void setExibirDadosAcesso(Boolean exibirDadosAcesso) {
		this.exibirDadosAcesso = exibirDadosAcesso;
	}

	public String getLoginAcesso() {
		return loginAcesso;
	}

	public void setLoginAcesso(String loginAcesso) {
		this.loginAcesso = loginAcesso;
	}

	
}
