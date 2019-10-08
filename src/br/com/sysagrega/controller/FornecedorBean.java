package br.com.sysagrega.controller;

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
import org.primefaces.event.RowEditEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.sysagrega.controller.Qualificadores.QualificadorFornecedor;
import br.com.sysagrega.model.IFornecedor;
import br.com.sysagrega.model.Enums.TipoContaBancaria;
import br.com.sysagrega.model.Enums.TipoFornecedor;
import br.com.sysagrega.model.Enums.TipoPagina;
import br.com.sysagrega.model.Enums.TipoPrincipalAtividade;
import br.com.sysagrega.model.Enums.TipoRamoAtividade;
import br.com.sysagrega.model.imp.Banco;
import br.com.sysagrega.model.imp.Cidade;
import br.com.sysagrega.model.imp.Contato;
import br.com.sysagrega.model.imp.DadosBancarios;
import br.com.sysagrega.model.imp.Endereco;
import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.model.imp.Fornecedor;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.service.IBancoService;
import br.com.sysagrega.service.ICidadeService;
import br.com.sysagrega.service.IContatoService;
import br.com.sysagrega.service.IEstadoService;
import br.com.sysagrega.service.IFornecedorService;
import br.com.sysagrega.service.IUsuarioService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class FornecedorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IFornecedorService fornecedorService;
	
	@Inject
	private IEstadoService estadoService;

	@Inject
	private ICidadeService cidadeService;
	
	@Inject
	private IBancoService bancoService;
	
	@Inject
	private IUsuarioService usuarioService; 
	
	@Inject
	private IContatoService contatoService;
	
	@Produces
	@QualificadorFornecedor
	private IFornecedor fornecedor;
	
	private String filtroCnpj;
	private String filtroNomeFantasia;
	private List<Estado> estados;
	private List<Cidade> cidades;
	private List<Banco> bancos;
	private List<String> tiposConta;
	private List<Fornecedor> fornecedores;
	private String filtroTipoFornecedor;
	private String filtroRamoAtividade;
	private String filtroPrincipalAtividade;
	private String labelNomeFornecedor = "Nome";
	
	//Variaveis Boolean
	private boolean viewFornecedor;
	private boolean disableCity;
	private Boolean pessoaFisicaSelecionada = true;
	private Boolean pessoaJuridicaSelecionada = false;
	private Boolean pesquisar = true;
	private Boolean visualizar = false;
	private Boolean editar = false;
	private Boolean verFornecedor = false;
	private Boolean novo = false;
	private Boolean validaTel = false;
	
	//Enus
	
	private List<String> tipoFornecedor;
	private List<String> tipoRamoAtividade;
	private List<String> tipoPrincipalAtividade;
	private Usuario usuarioLogado;
	
	private Contato contato;
	private List<Contato> listContato;
	private List<Contato> listContatoTemp;
	private List<Contato> listExcluirContatoTemp;
	private Contato contatoFornecedor;
	private Boolean adicionarContato = true;
	

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
		
		estados = new ArrayList<>();
		cidades = new ArrayList<>();
		bancos = new ArrayList<>();
		tiposConta = new ArrayList<>();
		
		contato = new Contato();
		listContatoTemp = new ArrayList<Contato>();
		listExcluirContatoTemp = new ArrayList<Contato>();
		
		/*if(fornecedor == null){
			fornecedor = new Fornecedor();
			fornecedor.setEndereco(new Endereco());
			fornecedor.setDadosBancarios(new DadosBancarios());
		}*/
		
		//Carregar Ennum Fornecedor
		carregarEnumsFornecedor();
		
		//Carrega lista de estados
		estados = estadoService.getAllEstados();
		
		// Carrega lista de bancos
		bancos = bancoService.getAllBancos();
		
		// Carrega Tipos de conta (Enum)
		for (TipoContaBancaria tipos : TipoContaBancaria.values()) {

		tiposConta.add(tipos.getDescricao());

		}
		// Carregando lista de fornecedores
		if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.CONSULTA_FORNEC)) {
			
			carregarTodosFornecedores();
			
		} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.EDIT_FORNEC)) {
			
			this.fornecedor = FacesUtil.getFornecedorSession();
			
		} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.NOVO_FORNEC)) {

			limparObjeto();
			
		} else if(FacesUtil.getParamSession() != null && FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.VISUALIZAR_FORNEC)) {
			this.fornecedor = FacesUtil.getFornecedorSession();
			carregarCidadesPorEstado();
			viewFornecedor = true;
		}
		
		visualizar = false;
		editar = false;
		pesquisar = true;
		novo = false;
	}
	
	public void verificaCpfCnpjCadastrado(){
		Fornecedor jaExistente = new Fornecedor();
		if(this.fornecedor.getCnpjCPF() != null){
			jaExistente = fornecedorService.getFornecedorByCnpj(this.fornecedor.getCnpjCPF());
		}
		
		if(jaExistente != null){
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dialoCpfJaCadastrado').show();");
			this.fornecedor.setCnpjCPF(null);
		}
	}
	
	public void novoFornecedor(){
		listContatoTemp = new ArrayList<Contato>();
		fornecedor = new Fornecedor();
		fornecedor.setTipoFornecedor(TipoFornecedor.PESSOA_FISICA.getDescricao());
		pessoaFisicaSelecionada = true;
		pessoaJuridicaSelecionada = false;
		labelNomeFornecedor = "Nome";
		fornecedor.setEndereco(new Endereco());
		fornecedor.setDadosBancarios(new DadosBancarios());
	
		adicionarContato = true;
		novo= true;
		pesquisar = false;
		editar = false;
		visualizar = false;
		viewFornecedor = false;
	}
	
	// Adiciona contato Cliente
	public void adicionarContato() {
		Contato novoContato = new Contato();

		if (contato.getNomeContato().equals("") || contato.getNomeContato().isEmpty()) {
			FacesUtil.addErrorMessage("Por favor, informe o nome de contato.");
			return;
		}

		if ((contato.getTel1Contato().equals("") && contato.getTel2Contato().equals(""))
				|| (contato.getTel1Contato().isEmpty() && contato.getTel2Contato().isEmpty())) {
			FacesUtil.addErrorMessage("Por favor, informe um telefone de contato.");
			return;
		}

		novoContato.setNomeContato(contato.getNomeContato());
		novoContato.setTel1Contato(contato.getTel1Contato());
		novoContato.setTel2Contato(contato.getTel2Contato());
		novoContato.setSetorContato(contato.getSetorContato());
		novoContato.setEmailContato(contato.getEmailContato());
		listContatoTemp.add(novoContato);

		contato = new Contato();

	}
	public void validarTelPrincipal(){
		this.fornecedor.setTelefone(validaTelefone(this.fornecedor.getTelefone()));
	}
	
	public void validarTel1(){
		contato.setTel1Contato(validaTelefone(contato.getTel1Contato()));
	}
	
	public void validarTel2(){
		contato.setTel2Contato(validaTelefone(contato.getTel2Contato()));
	}
		
		
	//Validação de Telefone Fixo ou Celular
		public String validaTelefone(String tel){
			String valor= "";
			valor = tel;
			valor = valor.replace("(","").replace(")", "").replace("-", "").replace(" ","");
			String texto = "";
			validaTel = true;
			
			for (int i = 0; i < valor.length(); i++) {
				if (Character.isDigit(valor.charAt(i))==false){
					dialogValidaTel();
					break;
					}
			}
			
			if(valor.length() < 10){
				dialogValidaTel();
			}
			
			if(valor.length() > 11){
				dialogValidaTel();	
			}
			
			if(validaTel){
		    	if (valor.toString() != null && !valor.toString().equals("")) {
		    		texto = valor.toString();
		    		if (texto.length() == 10) {
		    			texto = "("+texto.substring(0, 2)+") " + texto.substring(2, 6) +"-" +texto.substring(6, texto.length());
		    		} else if (texto.length() == 11) {
		    			texto = "("+texto.substring(0, 2)+") " + texto.substring(2, 7) +"-" +texto.substring(7, texto.length());
		    		}
		    	}
		    	return texto;
			}
		    	return null;
		}

		public void dialogValidaTel() {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dialoTelContato').show();");
			validaTel = false;
		}
	
	public void preExcluirContato(Contato contato) {
		this.contatoFornecedor = contato;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogContatoExcluir').show();");
	}

	// Exclusão Despesa Execução
	public void excluirContato() {
		if (contatoFornecedor.getId() != null) {
			listExcluirContatoTemp.add(contatoFornecedor);
		}
		listContatoTemp.remove(contatoFornecedor);
	//	FacesUtil.addInfoMessage("Contato excluída com sucesso.");
	}
	
	public void salvarContato() {
		if (listContatoTemp != null && !listContatoTemp.isEmpty()) {
			Contato itemGravar;
			for (Contato item : listContatoTemp) {
				itemGravar = new Contato();
				if(item.getId()== null){
					itemGravar.setIdFornecedor(this.fornecedor.getId());
					itemGravar.setAtivo(true);
					itemGravar.setDataCadastro(new Date());
					itemGravar.setNomeContato(item.getNomeContato());
					itemGravar.setTel1Contato(item.getTel1Contato());
					itemGravar.setTel2Contato(item.getTel2Contato());
					itemGravar.setSetorContato(item.getSetorContato());
					itemGravar.setEmailContato(item.getEmailContato());
					contatoService.salvar(itemGravar);
				}else{
					contatoService.salvar(item);
				}
			}
			listContatoTemp = new ArrayList<Contato>();
			contato = new Contato();
		}

		// Itens a serem excluidos de Execução
		if (listExcluirContatoTemp != null && !listExcluirContatoTemp.isEmpty()) {
			for (Contato item : listExcluirContatoTemp) {
				if (item.getId() != null) {
					contatoService.excluirContato(item);
				}
			}
		}
		listContatoTemp = new ArrayList<Contato>();
		contato = new Contato();
	}
	
	private void carregarEnumsFornecedor() {
		
		tipoFornecedor = new ArrayList<>();
		tipoRamoAtividade = new ArrayList<>();
		tipoPrincipalAtividade = new ArrayList<>();
		
		//Carrrega tipos Pessoa Juridica ou Física
		for (TipoFornecedor tipo : TipoFornecedor.values()) {
			
			tipoFornecedor.add(tipo.getDescricao());
			
		}
		
		for (TipoRamoAtividade seg : TipoRamoAtividade.values()) {

			tipoRamoAtividade.add(seg.getDescricao());

		}
		
		for (TipoPrincipalAtividade seg : TipoPrincipalAtividade.values()) {

			tipoPrincipalAtividade.add(seg.getDescricao());

		}
		
	}
	
	public void verificarTipoPessoa(String tipo){
		
		if(tipo.equals(TipoFornecedor.PESSOA_FISICA.getDescricao())){
			pessoaFisicaSelecionada = true;
			labelNomeFornecedor = "Nome";
			pessoaJuridicaSelecionada = false;
		}else{
			pessoaFisicaSelecionada = false;
			labelNomeFornecedor = "Nome Fantasia";
			pessoaJuridicaSelecionada = true;
		}
	}

	private void limparObjeto() {
		
		this.fornecedor = new Fornecedor();
		this.fornecedor.setEndereco(new Endereco());
		this.fornecedor.setDadosBancarios(new DadosBancarios());
		disableCity = true;
		listContatoTemp = new ArrayList<Contato>();
		contato = new Contato();
		
	}
	
	public void carregarCidadesPorEstado() {
		if(fornecedor != null && fornecedor.getEndereco() != null && this.fornecedor.getEndereco().getEstado()!= null){
			cidades = cidadeService.getCidadesByEstadoId(this.fornecedor.getEndereco().getEstado().getId());
			disableCity = false;
		}

	}
	
	public void salvarFornecedor() {
		try {
			this.fornecedor.setDataCadastro(new Date());
			this.fornecedor.setUsuarioAtualizacao(usuarioLogado);
			this.fornecedor.setDataCadastro(new Date());
			this.fornecedor.setAtivo(true);
			
			this.fornecedor = this.fornecedorService.salvar(this.fornecedor);
			salvarContato();
			
			limparObjeto();
			
			FacesUtil.addInfoMessage("Fornecedor salvo com sucesso.");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		visualizar = false;
		editar = false;
		pesquisar = true;
		novo= false;
	}
	
	public void atualizarFornecedor() {
		try {
			this.fornecedor.setDataAtualizacao(new Date());
			this.fornecedor.setUsuarioAtualizacao(usuarioLogado);
			this.fornecedor.setAtivo(true);
			this.fornecedorService.atualizarFornecedor(this.fornecedor);
			
			salvarContato();
			
			limparObjeto();
			FacesUtil.addInfoMessage("Registro atualizado com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		listExcluirContatoTemp = new ArrayList<Contato>();
		listContatoTemp = new ArrayList<Contato>();
		
		
		fornecedores = new ArrayList<>();
		visualizar = false;
		editar = false;
		pesquisar = true;
		novo= false;
	}
	
		
	public void excluirFornecedor() {
		try {
			this.fornecedor.setDataAtualizacao(new Date());
			this.fornecedor.setUsuarioAtualizacao(usuarioLogado);
			this.fornecedor.setAtivo(false);
			this.fornecedorService.atualizarFornecedor(this.fornecedor);
			
			carregarTodosFornecedores();
			FacesUtil.addInfoMessage("Fornecedor excluido com sucesso.");
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void preExcluir(Fornecedor fornecedor){
		this.fornecedor = fornecedor;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluir').show();");
	}

	/**
	 * Método controla a renderização do combo cidades, bloqueando o mesmo,
	 * caso não tenha sido informado um estado.
	 * 
	 * @return Boolean
	 * @author Paulo
	 */
	public Boolean isDisableCidades() {

		if (fornecedor.getEndereco().getEstado() == null || fornecedor.getEndereco().getEstado().getId() == null) {

			return true;

		}

		return false;

	}

	/**
	 * Carregar todos os fornecedores cadastrados no sistema para tela de
	 * consulta
	 * @author Paulo
	 * 
	 */
	private void carregarTodosFornecedores() {
		fornecedores = new ArrayList<>();
		fornecedores = this.fornecedorService.getFornecedorByFilter(this.filtroNomeFantasia, this.filtroRamoAtividade, this.filtroPrincipalAtividade);
	}
	
	public void redirectEdit(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
		
		FacesUtil.addParamSession(TipoPagina.EDIT_FORNEC);
			
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("fornecedor", this.fornecedor);
		
		listExcluirContatoTemp = new ArrayList<Contato>();
		listContatoTemp = new ArrayList<Contato>();
		listContato = new ArrayList<Contato>();
		listContato = contatoService.getByFornecedorId(this.fornecedor.getId());
		listContatoTemp.addAll(listContato);
		
		verificarTipoPessoa(this.fornecedor.getTipoFornecedor());
		isEditFornecedor();
		
		disableCity = true;
		adicionarContato = true;
		visualizar = false;
		editar = true;
		pesquisar = false;
		novo= false;
		viewFornecedor = false;
	}	
	
	public Boolean isEditFornecedor() {
		if(fornecedor == null){
			return true;
		}
		return this.fornecedor.isExistente() && !viewFornecedor;
		
	}
	
	public String redirectEditFornecedor(Fornecedor fornecedor) {
		if(fornecedor != null) {
			
			FacesUtil.addParamSession(TipoPagina.EDIT_FORNEC);
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("fornecedor", fornecedor);
			editFornecedor();
			
		} else {
			
			FacesUtil.addErrorMessage("Favor selecionar um fornecedor!");
			return null;
			
		}
		
		return "editar_fornecedor";
	}
	
	public void voltar(){
		if(fornecedor == null){
			fornecedor = new Fornecedor();
		}
		fornecedores = new ArrayList<>();
		fornecedores.clear();
		listExcluirContatoTemp= new ArrayList<>();
		novo= false;
		pesquisar = true;
		viewFornecedor = false;
	}
	
	public String redirectNovoFornecedor() {
		FacesUtil.addParamSession(TipoPagina.NOVO_FORNEC);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		isNewFornecedor();
	return "cadastro_fornecedor";
	}
	
	public void visualizar(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
		
		FacesUtil.addParamSession(TipoPagina.VISUALIZAR);	
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("fornecedor", this.fornecedor);
		
		listExcluirContatoTemp = new ArrayList<Contato>();
		listContatoTemp = new ArrayList<Contato>();
		listContato = new ArrayList<Contato>();
		listContato = contatoService.getByFornecedorId(this.fornecedor.getId());
		listContatoTemp.addAll(listContato);
		
		verificarTipoPessoa(this.fornecedor.getTipoFornecedor());
		isEditFornecedor();
		
		visualizar = true;
		adicionarContato = false;
		pesquisar = false;
		novo = false;
		editar = false;
		viewFornecedor = true;
	}	
	
	//Edita e confirma uma lista dinamicamente a lista de parcelas
	public void onRowEdit(RowEditEvent event) {
		   FacesMessage msg;
		   if(viewFornecedor){
			   msg = new FacesMessage("Somente vizualização");
		   }else{
			   msg = new FacesMessage("Contato editado com sucesso");
		   }
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
		
	   //Cancela a edição de parcelas do contrato dinamicamente
		 public void onRowCancel(RowEditEvent event) {
			FacesMessage msg;
			if(viewFornecedor){
				msg = new FacesMessage("Contato somente vizualização");
			}else{
				msg = new FacesMessage("Edição do contato Cancelada");
			}
				FacesContext.getCurrentInstance().addMessage(null, msg);
	    }

		
	/**
	 * Método valida se é uma edição do objeto Fornecedor
	 * @param Fornecedor
	 * @return Boolean
	 * @author Paulo
	 */
	public Boolean editFornecedor() {
		if(fornecedor == null){
			return true;
		}
		return this.fornecedor.isExistente() && !viewFornecedor;
		
	}
	
	/**
	 * Método valida se é um novo objeto Fornecedor
	 * @param Fornecedor
	 * @return Boolean
	 * @author Paulo
	 */
	public Boolean isNewFornecedor() {
		if(fornecedor == null){
			return true;
		}
		return this.fornecedor.isNovo();
		
	}
	
	public void filtrarFornecedores() {
		
		fornecedores = new ArrayList<>();
		fornecedores = this.fornecedorService.getFornecedorByFilter(this.filtroNomeFantasia, this.filtroRamoAtividade, this.filtroPrincipalAtividade);
		
		if(fornecedores == null || fornecedores.isEmpty()){
			FacesUtil.addErrorMessage("O filtro não retornou nenhum registro.");	
		}
	}
	

	/**
	 * @return the estados
	 */
	public List<Estado> getEstados() {

		return estados;

	}

	/**
	 * @param estados 
	 * the estados to set
	 */
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	/**
	 * @return the cidades
	 */
	public List<Cidade> getCidades() {
		return cidades;
	}

	/**
	 * @param cidades
	 * the cidades to set
	 */
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	/**
	 * @return the Fornecedor
	 */
	public IFornecedor getFornecedor() {
		return fornecedor;
	}

	/**
	 * @param Fornecedor
	 *  the Fornecedor to set
	 */
	public void setFornecedor(IFornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	/**
	 * @return the bancos
	 */
	public List<Banco> getBancos() {
		return bancos;
	}

	/**
	 * @param bancos
	 * the bancos to set
	 */
	public void setBancos(List<Banco> bancos) {
		this.bancos = bancos;
	}
	
	/**
	 * @return the tiposConta
	 */
	public List<String> getTiposConta() {
		return tiposConta;
	}

	/**
	 * @param tiposConta
	 * the tiposConta to set
	 */
	public void setTiposConta(List<String> tiposConta) {
		this.tiposConta = tiposConta;
	}
	
	/**
	 * @return the fornecedores
	 */
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	/**
	 * @param profissionais
	 * the fornecedores to set
	 */
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	/**
	 * @return the viewFornecedor
	 */
	public boolean getViewFornecedor() {
		return viewFornecedor;
	}

	/**
	 * @param viewFornecedor the viewFornecedor to set
	 */
	public void setViewFornecedor(boolean viewFornecedor) {
		this.viewFornecedor = viewFornecedor;
	}

	/**
	 * @return the disableCity
	 */
	public boolean isDisableCity() {
		return disableCity;
	}

	/**
	 * @param disableCity the disableCity to set
	 */
	public void setDisableCity(boolean disableCity) {
		this.disableCity = disableCity;
	}

	/**
	 * @return the filtroCnpj
	 */
	public String getFiltroCnpj() {
		return filtroCnpj;
	}

	/**
	 * @param filtroCnpj the filtroCnpj to set
	 */
	public void setFiltroCnpj(String filtroCnpj) {
		this.filtroCnpj = filtroCnpj;
	}

	/**
	 * @return the filtroNomeFantasia
	 */
	public String getFiltroNomeFantasia() {
		return filtroNomeFantasia;
	}

	/**
	 * @param the filtroNomeFantasia
	 */
	public void setFiltroNomeFantasia(String filtroNomeFantasia) {
		this.filtroNomeFantasia = filtroNomeFantasia;
	}

	public Boolean getPessoaFisicaSelecionada() {
		return pessoaFisicaSelecionada;
	}

	public void setPessoaFisicaSelecionada(Boolean pessoaFisicaSelecionada) {
		this.pessoaFisicaSelecionada = pessoaFisicaSelecionada;
	}

	public Boolean getPessoaJuridicaSelecionada() {
		return pessoaJuridicaSelecionada;
	}

	public void setPessoaJuridicaSelecionada(Boolean pessoaJuridicaSelecionada) {
		this.pessoaJuridicaSelecionada = pessoaJuridicaSelecionada;
	}

	public List<String> getTipoFornecedor() {
		return tipoFornecedor;
	}

	public List<String> getTipoRamoAtividade() {
		return tipoRamoAtividade;
	}

	public List<String> getTipoPrincipalAtividade() {
		return tipoPrincipalAtividade;
	}

	public void setTipoFornecedor(List<String> tipoFornecedor) {
		this.tipoFornecedor = tipoFornecedor;
	}

	public void setTipoRamoAtividade(List<String> tipoRamoAtividade) {
		this.tipoRamoAtividade = tipoRamoAtividade;
	}

	public void setTipoPrincipalAtividade(List<String> tipoPrincipalAtividade) {
		this.tipoPrincipalAtividade = tipoPrincipalAtividade;
	}

	public String getFiltroTipoFornecedor() {
		return filtroTipoFornecedor;
	}

	public String getFiltroRamoAtividade() {
		return filtroRamoAtividade;
	}

	public String getFiltroPrincipalAtividade() {
		return filtroPrincipalAtividade;
	}

	public void setFiltroTipoFornecedor(String filtroTipoFornecedor) {
		this.filtroTipoFornecedor = filtroTipoFornecedor;
	}

	public void setFiltroRamoAtividade(String filtroRamoAtividade) {
		this.filtroRamoAtividade = filtroRamoAtividade;
	}

	public void setFiltroPrincipalAtividade(String filtroPrincipalAtividade) {
		this.filtroPrincipalAtividade = filtroPrincipalAtividade;
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

	public Boolean getVerFornecedor() {
		return verFornecedor;
	}

	public void setVerFornecedor(Boolean verFornecedor) {
		this.verFornecedor = verFornecedor;
	}

	public Boolean getNovo() {
		return novo;
	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

	public Boolean getEditar() {
		return editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public Boolean getAdicionarContato() {
		return adicionarContato;
	}

	public void setAdicionarContato(Boolean adicionarContato) {
		this.adicionarContato = adicionarContato;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Contato> getListContato() {
		return listContato;
	}

	public void setListContato(List<Contato> listContato) {
		this.listContato = listContato;
	}

	public List<Contato> getListContatoTemp() {
		return listContatoTemp;
	}

	public void setListContatoTemp(List<Contato> listContatoTemp) {
		this.listContatoTemp = listContatoTemp;
	}

	public List<Contato> getListExcluirContatoTemp() {
		return listExcluirContatoTemp;
	}

	public void setListExcluirContatoTemp(List<Contato> listExcluirContatoTemp) {
		this.listExcluirContatoTemp = listExcluirContatoTemp;
	}

	public Contato getContatoFornecedor() {
		return contatoFornecedor;
	}

	public void setContatoFornecedor(Contato contatoFornecedor) {
		this.contatoFornecedor = contatoFornecedor;
	}

	public String getLabelNomeFornecedor() {
		return labelNomeFornecedor;
	}

	public void setLabelNomeFornecedor(String labelNomeFornecedor) {
		this.labelNomeFornecedor = labelNomeFornecedor;
	}
	
	
	
	
	
}
