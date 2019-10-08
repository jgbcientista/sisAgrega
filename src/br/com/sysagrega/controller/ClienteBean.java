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
import java.util.Map;

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
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.sysagrega.controller.Qualificadores.QualificadorCliente;
import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.Enums.EPendencia;
import br.com.sysagrega.model.Enums.ETipoEntidade;
import br.com.sysagrega.model.Enums.TipoCliente;
import br.com.sysagrega.model.Enums.TipoIndicacao;
import br.com.sysagrega.model.Enums.TipoPagina;
import br.com.sysagrega.model.Enums.TipoPorteEmpresa;
import br.com.sysagrega.model.Enums.TipoSeguimentoCliente;
import br.com.sysagrega.model.Enums.TipoStatus;
import br.com.sysagrega.model.Enums.TipoTamanhoCliente;
import br.com.sysagrega.model.imp.Anexo;
import br.com.sysagrega.model.imp.Cidade;
import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.model.imp.Contato;
import br.com.sysagrega.model.imp.Endereco;
import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.service.IAnexoService;
import br.com.sysagrega.service.ICidadeService;
import br.com.sysagrega.service.IClienteService;
import br.com.sysagrega.service.IContatoService;
import br.com.sysagrega.service.IEstadoService;
import br.com.sysagrega.service.IUsuarioService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.Constante;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ClienteBean implements Serializable {
	/**
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IClienteService clienteService;

	@Inject
	private IEstadoService estadoService;

	@Inject
	private ICidadeService cidadeService;

	@Inject
	private IUsuarioService usuarioService;

	@Inject
	private IContatoService contatoService;
	
	@Inject
	private IAnexoService anexoService;

	@Produces
	@QualificadorCliente
	private ICliente cliente;
	@Produces
	@QualificadorCliente
	private ICliente clienteCriado;
	
	private int tamPainel;
	private String labelNomeCliente;
	private String filtroNome;
	private String filtroCnpj;
	private String filtroSeguimento;
	private String filtroTipoCliente;
	private String filtroTipoIndicacao;
	private String filtroTipoPorteEmpresa;
	private String filtroTipoStatus;
	private List<Estado> estados;
	private List<Cidade> cidades;

	// Variaveis Boolean
	private boolean viewCliente = false;
	private boolean disableCity;
	private Boolean pessoaFisicaSelecionada;
	private Boolean pessoaJuridicaSelecionada;
	private Boolean pessoaFisicaJuridicaSelecionada;

	private Boolean pesquisar = true;
	private Boolean visualizar = false;
	private Boolean editar = false;
	private Boolean verCliente = false;
	private Boolean novo = false;
	private Boolean validaTel= false;

	// Enums
	private List<String> seguimentos;
	private List<String> tamanhos;
	private List<String> tiposClientes;
	private List<String> tiposIndicacao;
	private List<String> tipoPorteEmpresa;
	private List<String> tipoStatus;
	private List<Cliente> clientes;
	private List<Cliente> clienteAutocomplete;
	private List<String> pendencias;

	private Contato contato;
	private List<Contato> listContato;
	private List<Contato> listContatoTemp;
	private List<Contato> listExcluirContatoTemp;
	private Contato contatoCliente;
	private Boolean adicionarContato = true;
	String mensagemCpfExistente = "";
	

	private Usuario usuarioLogado;
	// Manages Propertys
	@ManagedProperty(value = "#{menuFaces}")
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
		if (cliente == null) {
			cliente = new Cliente();
			cliente.setEndereco(new Endereco());
		}

		pessoaFisicaSelecionada = true;
		pessoaJuridicaSelecionada = false;
		labelNomeCliente = "Nome:";

		contato = new Contato();
		listContatoTemp = new ArrayList<Contato>();
		listExcluirContatoTemp = new ArrayList<Contato>();

		// Carrega lista de estados
		estados = estadoService.getAllEstados();
		pendencias = new ArrayList<>();

		// Carrega Tipos de conta (Enum)
		for (EPendencia tipos : EPendencia.values()) {
			pendencias.add(tipos.getDescricao());
		}

		visualizar = false;
		editar = false;
		pesquisar = true;
		novo = false;

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> requestParams = context.getExternalContext().getRequestParameterMap();

		if (requestParams.containsKey("verCliente")) {
			verCliente = Boolean.parseBoolean(requestParams.get("verCliente"));
		}

		// Carrega Enums do Objeto Cliente
		carregarEnumsClientes();
		// Carregando lista de profissionais
		if (FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.CONSULTA_CLIENTE)) {

			carregarTodosClientes();

		} else if (FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.EDIT_CLIENTE)) {

			this.cliente = FacesUtil.getClienteSession();
			carregarCidadesPorEstado();

		} else if (FacesUtil.getParamSession() != null && FacesUtil.getParamSession().equals(TipoPagina.NOVO_CLIENTE)) {

			limparObjeto();

		} else if (FacesUtil.getParamSession() != null
				&& FacesUtil.getParamSession().equals(TipoPagina.VISUALIZAR_CLIENTE)) {
			this.cliente = FacesUtil.getClienteSession();
			carregarCidadesPorEstado();
			viewCliente = true;
		}
		
	}

	public void novoCliente() {
		listContatoTemp = new ArrayList<Contato>();
		clienteCriado = new Cliente();
		cliente = new Cliente();
		cliente.setTipoCliente(TipoCliente.PESSOA_FISICA.getDescricao());
		pessoaFisicaSelecionada = true;
		pessoaJuridicaSelecionada = false;
		
		cliente.setEndereco(new Endereco());
		listaArquivosAnexados = new ArrayList<>();
		 anexos = new ArrayList<>();
		adicionarContato = true;
		novo = true;
		pesquisar = false;
		editar = false;
		visualizar = false;
		viewCliente = false;
	}
	
	public void verificaCPFCadastrado(){
		Cliente jaExistente = new Cliente();
		if(this.cliente.getCnpjCPF() != null){
			jaExistente = clienteService.getClienteByCnpj(this.cliente.getCnpjCPF(), true);
		}
		
		if(jaExistente != null){
			jaExistente.getNome();
			mensagemCpfExistente = jaExistente.getNome() +" já foi cadastrado! Digite outro CPF ou CNPJ.";
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dialoCpfJaCadastrado').show();");
			this.cliente.setCnpjCPF(null);
		}
	}

	// Adiciona contato Cliente
	public void adicionarContato() {
		Contato novoContato = new Contato();

		if (contato.getNomeContato() != null && contato.getNomeContato().equals("") || contato.getNomeContato().isEmpty()) {
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
	
	public void validarTel(){
		cliente.setTelefone(validaTelefone(cliente.getTelefone()));
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
	
	//Edita e confirma uma lista dinamicamente a lista de parcelas
	   public void onRowEdit(RowEditEvent event) {
		   FacesMessage msg;
		   if(viewCliente){
			   msg = new FacesMessage("Somente vizualização");
		   }else{
			   msg = new FacesMessage("Contato editado com sucesso");
		   }
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
		
	   //Cancela a edição de parcelas do contrato dinamicamente
		 public void onRowCancel(RowEditEvent event) {
			FacesMessage msg;
			if(viewCliente){
				msg = new FacesMessage("Contato somente vizualização");
			}else{
				msg = new FacesMessage("Edição do contato Cancelada");
			}
				FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	
	public List<String> completeText(String query) {

		clienteAutocomplete = new ArrayList<>();
		clienteAutocomplete = clienteService.getClienteByFilterNome(query);
		List<String> results = new ArrayList<String>();

		for (Cliente cliente : clienteAutocomplete) {
			results.add(cliente.getNome());
		}

		return results;
	}


	public void verificarCliente(String tipo) {

		if (tipo.equals(TipoCliente.PESSOA_FISICA.getDescricao())) {
			pessoaFisicaSelecionada = true;
			pessoaJuridicaSelecionada = false;
			labelNomeCliente = "Nome:";
		} else {
			pessoaFisicaSelecionada = false;
			pessoaJuridicaSelecionada = true;
			labelNomeCliente = "Razão Social:";
		}

	}

	public void limparObjeto() {

		this.cliente = new Cliente();
		this.cliente.setEndereco(new Endereco());
		disableCity = true;
		listContatoTemp = new ArrayList<Contato>();
		contato = new Contato();
	}

	public void carregarCidadesPorEstado() {
		if (this.cliente.getEndereco() != null && this.cliente.getEndereco().getEstado() != null) {
			cidades = cidadeService.getCidadesByEstadoId(this.cliente.getEndereco().getEstado().getId());
			disableCity = false;
		}

	}

	public void preExcluirContato(Contato contato) {
		this.contatoCliente = contato;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogContatoExcluir').show();");
	}

	// Exclusão Despesa Execução
	public void excluirContato() {
		if (contatoCliente.getId() != null) {
			listExcluirContatoTemp.add(contatoCliente);
		}
		listContatoTemp.remove(contatoCliente);
	//	FacesUtil.addInfoMessage("Contato excluída com sucesso.");
	}

	public void salvarContato() {
		if (listContatoTemp != null && !listContatoTemp.isEmpty()) {
			Contato itemGravar;
			for (Contato item : listContatoTemp) {
				itemGravar = new Contato();
				if(item.getId()== null){
					itemGravar.setIdCliente(this.cliente.getId());
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

	public void salvarCliente() {

		try {
			Cliente cli = null;
			this.cliente.setDataCadastro(new Date());
			this.cliente.setUsuarioAtualizacao(usuarioLogado);
			this.cliente.setAtivo(true);
			
			if(cliente.getCnpjCPF() != null){
				cli = clienteService.getClienteByCnpj(cliente.getCnpjCPF(), false);
			}
			if(cli != null){
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('dialoCpfJaCadastradoInativo').show();");
				return;
				
			}else{
				this.cliente = this.clienteService.salvar(this.cliente);
				salvarContato();
			}
			salvarListaAnexos();
			limparObjeto();
			filtrarCliente();
			 
			FacesUtil.addInfoMessage("Registro salvo com sucesso.");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		visualizar = false;
		editar = false;
		pesquisar = true;
		novo = false;
	}
	
	public void ativarCliente() {
		
		this.cliente.setAtivo(true);
		this.cliente = this.clienteService.salvar(this.cliente);
		visualizar = false;
		editar = false;
		pesquisar = true;
		novo = false;
		filtrarCliente();
		FacesUtil.addInfoMessage("Registro salvo com sucesso.");
	}

	/**
	 * Metodo realiza atualização de um objeto cliente
	 * 
	 * @param cliente
	 * @return cliente
	 * @author JOAO
	 */

	public void atualizarCliente() {
		try {
			this.cliente.setUsuarioAtualizacao(usuarioLogado);
			this.cliente.setDataAtualizacao(new Date());
			this.cliente.setAtivo(true);
			this.clienteService.atualizarCliente(this.cliente);
			salvarContato();

			salvarListaAnexos();
			
			
			FacesUtil.addInfoMessage("Registro alterado com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		listExcluirContatoTemp = new ArrayList<Contato>();
		listContatoTemp = new ArrayList<Contato>();
		
		clientes = new ArrayList<>();
		//visualizar = false;
		//editar = false;
		//pesquisar = true;
		//novo = false;

	}

	public void excluirCliente() {
		try {
			this.cliente.setUsuarioAtualizacao(usuarioLogado);
			this.cliente.setDataAtualizacao(new Date());
			this.cliente.setAtivo(false);
			this.clienteService.atualizarCliente(this.cliente);

			carregarTodosClientes();

			FacesUtil.addInfoMessage("Registro excluido com sucesso!");
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void carregarTodosClientes() {
		clientes = new ArrayList<>();
		clientes = this.clienteService.getClienteByFilter(this.filtroNome, this.filtroSeguimento,
				this.filtroTipoCliente);
	}
	
	
	//Edita o cadastro de cliente
	public void redirectEdit(Cliente cliente) {
		this.cliente = cliente;
		if (cliente.getTipoCliente() != null
				&& cliente.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getDescricao())) {
			pessoaFisicaSelecionada = true;
			pessoaJuridicaSelecionada = false;
			labelNomeCliente = "Nome:";
		} else {
			pessoaFisicaSelecionada = false;
			pessoaJuridicaSelecionada = true;
			labelNomeCliente = "Razão Social:";
		}
		verificarCliente(cliente.getTipoCliente());
		FacesUtil.addParamSession(TipoPagina.EDIT_CLIENTE);

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("cliente", this.cliente);

		listExcluirContatoTemp = new ArrayList<Contato>();
		listContatoTemp = new ArrayList<Contato>();
		listContato = new ArrayList<Contato>();
		listContato = contatoService.getByClienteId(this.cliente.getId());
		listContatoTemp.addAll(listContato);
		
		estados = estadoService.getAllEstados();
		carregarCidadesPorEstado();
		adicionarContato = true;
		isEditCliente();
		visualizar = false;
		editar = true;
		pesquisar = false;
		novo = false;
		viewCliente = false;
		
		preencherListaAnexos();
	}

	
	/**
	 * metodo para redirecionar para pagina correta.
	 * 
	 */

	//Volta par tela de pesquisa
	public void voltar() {
		if (cliente == null) {
			cliente = new Cliente();
		}
		
		contato = new Contato();
		clientes = new ArrayList<>();
		clientes.clear();
		listExcluirContatoTemp.clear();
		listContatoTemp.clear();
		novo = false;
		pesquisar = true;
		viewCliente = false;
	}

	//Visualizar o cadastro de cliente
	public void visualizar(Cliente cliente) {
		this.cliente = cliente;
		if (cliente.getTipoCliente() != null
				&& cliente.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getDescricao())) {
			pessoaFisicaSelecionada = true;
			pessoaJuridicaSelecionada = false;
			labelNomeCliente = "Nome:";
		} else {
			pessoaFisicaSelecionada = false;
			pessoaJuridicaSelecionada = true;
			labelNomeCliente = "Razão Social:";
		}
		
		listExcluirContatoTemp = new ArrayList<Contato>();
		listContatoTemp = new ArrayList<Contato>();
		listContato = new ArrayList<Contato>();
		listContato = contatoService.getByClienteId(this.cliente.getId());
		listContatoTemp.addAll(listContato);
		
		adicionarContato = false;
		visualizar = true;
		pesquisar = false;
		novo = false;
		editar = false;
		viewCliente = true;
		
		preencherListaAnexos();
	}

	public void preExcluir(Cliente cliente) {
		this.cliente = cliente;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluir').show();");
	}

	/**
	 * Método valida se é uma edição do objeto cliente
	 * 
	 * @return Boolean
	 * @author Elton
	 */
	public Boolean isEditCliente() {
		if (cliente == null) {
			return true;
		}
		return this.cliente.isExistente() && !viewCliente;

	}

	/**
	 * Método valida se é um novo objeto cliente
	 * 
	 * @return Boolean
	 * @author Elton
	 */
	public Boolean isNewCliente() {

		if (cliente == null) {
			return true;
		}
		return this.cliente.isNovo();

	}

	public void filtrarCliente() {
		clientes = new ArrayList<>();
		clientes = this.clienteService.getClienteByFilter(this.filtroNome, this.filtroSeguimento,
				this.filtroTipoCliente);

		if (clientes == null || clientes.isEmpty()) {
			FacesUtil.addErrorMessage("O filtro não retornou nenhum registro.");
		}
	}

	/**
	 * Método carrega os enums para o objeto cliente
	 */
	private void carregarEnumsClientes() {

		seguimentos = new ArrayList<>();
		tamanhos = new ArrayList<>();
		tiposClientes = new ArrayList<>();
		tiposIndicacao = new ArrayList<>();
		tipoPorteEmpresa = new ArrayList<>();
		tipoStatus = new ArrayList<>();

		// Carrega tipos de seguimento (Enum)
		for (TipoSeguimentoCliente seg : TipoSeguimentoCliente.values()) {

			seguimentos.add(seg.getDescricao());

		}

		// Carrega tipos tamanho
		for (TipoTamanhoCliente t : TipoTamanhoCliente.values()) {

			tamanhos.add(t.getDescricao());

		}

		// Carrrega tipos cliente
		for (TipoCliente tipo : TipoCliente.values()) {

			tiposClientes.add(tipo.getDescricao());

		}

		// Carrega tipos indicaçao
		for (TipoIndicacao indicacao : TipoIndicacao.values()) {

			tiposIndicacao.add(indicacao.getDescricao());

		}

		// Carrega tipos TipoPorte
		for (TipoPorteEmpresa porteEmpresa : TipoPorteEmpresa.values()) {

			tipoPorteEmpresa.add(porteEmpresa.getDescricao());

		}

		// Carrega tipos indicaçao
		for (TipoStatus status : TipoStatus.values()) {
			tipoStatus.add(status.getDescricao());

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
	 *            the estados to set
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
	 *            the cidades to set
	 */
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	/**
	 * @return the disableCity
	 */
	public boolean isDisableCity() {
		return disableCity;
	}

	/**
	 * @param disableCity
	 *            the disableCity to set
	 */
	public void setDisableCity(boolean disableCity) {
		this.disableCity = disableCity;
	}

	/**
	 * @return the cliente
	 */
	public ICliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(ICliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the viewCliente
	 */
	public boolean isViewCliente() {
		return viewCliente;
	}

	/**
	 * @param viewCliente
	 *            the viewCliente to set
	 */
	public void setViewCliente(boolean viewCliente) {
		this.viewCliente = viewCliente;
	}

	/**
	 * @return the filtroCnpj
	 */
	public String getFiltroCnpj() {
		return filtroCnpj;
	}

	/**
	 * @param filtroCnpj
	 *            the filtroCnpj to set
	 */
	public void setFiltroCnpj(String filtroCnpj) {
		this.filtroCnpj = filtroCnpj;
	}

	/**
	 * @return the seguimentos
	 */
	public List<String> getSeguimentos() {
		return seguimentos;
	}

	/**
	 * @param seguimentos
	 *            the seguimentos to set
	 */
	public void setSeguimentos(List<String> seguimentos) {
		this.seguimentos = seguimentos;
	}

	/**
	 * @return the tamanhos
	 */
	public List<String> getTamanhos() {
		return tamanhos;
	}

	/**
	 * @param tamanhos
	 *            the tamanhos to set
	 */
	public void setTamanhos(List<String> tamanhos) {
		this.tamanhos = tamanhos;
	}

	/**
	 * @return the tiposClientes
	 */
	public List<String> getTiposClientes() {
		return tiposClientes;
	}

	/**
	 * @param tiposClientes
	 *            the tiposClientes to set
	 */
	public void setTiposClientes(List<String> tiposClientes) {
		this.tiposClientes = tiposClientes;
	}

	/**
	 * @return the clientes
	 */
	public List<Cliente> getClientes() {
		return clientes;
	}

	/**
	 * @param clientes
	 *            the clientes to set
	 */
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	/**
	 * @return the filtroNome
	 */
	public String getFiltroNome() {
		return filtroNome;
	}

	/**
	 * @param filtroNome
	 *            the filtroNome to set
	 */
	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	/**
	 * @return the filtroSeguimento
	 */
	public String getFiltroSeguimento() {
		return filtroSeguimento;
	}

	/**
	 * @param filtroSeguimento
	 *            the filtroSeguimento to set
	 */
	public void setFiltroSeguimento(String filtroSeguimento) {
		this.filtroSeguimento = filtroSeguimento;
	}

	/**
	 * @return the filtroTipoCliente
	 */
	public String getFiltroTipoCliente() {
		return filtroTipoCliente;
	}

	/**
	 * @param filtroTipoCliente
	 *            the filtroTipoCliente to set
	 */
	public void setFiltroTipoCliente(String filtroTipoCliente) {
		this.filtroTipoCliente = filtroTipoCliente;
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

	public Boolean getPessoaFisicaJuridicaSelecionada() {
		return pessoaFisicaJuridicaSelecionada;
	}

	public void setPessoaFisicaJuridicaSelecionada(Boolean pessoaFisicaJuridicaSelecionada) {
		this.pessoaFisicaJuridicaSelecionada = pessoaFisicaJuridicaSelecionada;
	}

	public String getFiltroTipoIndicacao() {
		return filtroTipoIndicacao;
	}

	public void setFiltroTipoIndicacao(String filtroTipoIndicacao) {
		this.filtroTipoIndicacao = filtroTipoIndicacao;
	}

	public List<String> getTiposIndicacao() {
		return tiposIndicacao;
	}

	public void setTiposIndicacao(List<String> tiposIndicacao) {
		this.tiposIndicacao = tiposIndicacao;
	}

	public String getFiltroTipoPorteEmpresa() {
		return filtroTipoPorteEmpresa;
	}

	public void setFiltroTipoPorteEmpresa(String filtroTipoPorteEmpresa) {
		this.filtroTipoPorteEmpresa = filtroTipoPorteEmpresa;
	}

	public List<String> getTipoPorteEmpresa() {
		return tipoPorteEmpresa;
	}

	public void setTipoPorteEmpresa(List<String> tipoPorteEmpresa) {
		this.tipoPorteEmpresa = tipoPorteEmpresa;
	}

	public String getFiltroTipoStatus() {
		return filtroTipoStatus;
	}

	public void setFiltroTipoStatus(String filtroTipoStatus) {
		this.filtroTipoStatus = filtroTipoStatus;
	}

	public List<String> getTipoStatus() {
		return tipoStatus;
	}

	public void setTipoStatus(List<String> tipoStatus) {
		this.tipoStatus = tipoStatus;
	}

	public String getLabelNomeCliente() {
		return labelNomeCliente;
	}

	public void setLabelNomeCliente(String labelNomeCliente) {
		this.labelNomeCliente = labelNomeCliente;
	}

	public int getTamPainel() {
		return tamPainel;
	}

	public void setTamPainel(int tamPainel) {
		this.tamPainel = tamPainel;
	}

	public List<Cliente> getClienteAutocomplete() {
		return clienteAutocomplete;
	}

	public void setClienteAutocomplete(List<Cliente> clienteAutocomplete) {
		this.clienteAutocomplete = clienteAutocomplete;
	}

	public List<String> getPendencias() {
		return pendencias;
	}

	public void setPendencias(List<String> pendencias) {
		this.pendencias = pendencias;
	}

	public List<FileUploadEvent> getListaArquivosAnexados() {
		return listaArquivosAnexados;
	}

	public void setListaArquivosAnexados(List<FileUploadEvent> listaArquivosAnexados) {
		this.listaArquivosAnexados = listaArquivosAnexados;
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

	public FileUploadEvent getFileUploadEvent() {
		arquivoUpload(fileUploadEvent);
		return fileUploadEvent;
	}

	public void setFileUploadEvent(FileUploadEvent fileUploadEvent) {
		this.fileUploadEvent = fileUploadEvent;
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

	public Boolean getVerCliente() {
		return verCliente;
	}

	public void setVerCliente(Boolean verCliente) {
		this.verCliente = verCliente;
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

	public Boolean getNovo() {
		return novo;
	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

	public List<Contato> getListContatoTemp() {
		return listContatoTemp;
	}

	public void setListContatoTemp(List<Contato> listContatoTemp) {
		this.listContatoTemp = listContatoTemp;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Contato getContatoCliente() {
		return contatoCliente;
	}

	public void setContatoCliente(Contato contatoCliente) {
		this.contatoCliente = contatoCliente;
	}

	public List<Contato> getListExcluirContatoTemp() {
		return listExcluirContatoTemp;
	}

	public void setListExcluirContatoTemp(List<Contato> listExcluirContatoTemp) {
		this.listExcluirContatoTemp = listExcluirContatoTemp;
	}

	public List<Contato> getListContato() {
		return listContato;
	}

	public void setListContato(List<Contato> listContato) {
		this.listContato = listContato;
	}

	public ICliente getClienteCriado() {
		return clienteCriado;
	}

	public void setClienteCriado(ICliente clienteCriado) {
		this.clienteCriado = clienteCriado;
	}

	public Boolean getAdicionarContato() {
		return adicionarContato;
	}

	public void setAdicionarContato(Boolean adicionarContato) {
		this.adicionarContato = adicionarContato;
	}

	public String getMensagemCpfExistente() {
		return mensagemCpfExistente;
	}

	public void setMensagemCpfExistente(String mensagemCpfExistente) {
		this.mensagemCpfExistente = mensagemCpfExistente;
	}

	public Boolean getValidaTel() {
		return validaTel;
	}

	public void setValidaTel(Boolean validaTel) {
		this.validaTel = validaTel;
	}
	
	/*
	 * INICIA OS METODOS DE ANEXO.NAO ESQUECER DE COLOCAR QUANDO FOR CADASTRAR UM NOVO, EDITAR E VISUALIZAR
	 * CRIAR NO C:arquivos
	 */
	private List<FileUploadEvent> listaArquivosAnexados;
	private List<Anexo> anexos;
	private FileUploadEvent fileUploadEvent;
	private Anexo excluirAnexo;
	private StreamedContent file;
	
	
	static String VAR_AMBIENTE_DADOS_SISTEMA = "DADOS_SISTEMA"; 
	
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
							itemTemp.setIdEntidade(cliente.getId());
							itemTemp.setTipo(ETipoEntidade.CLIENTE.getFlag());
							anexos.add(itemTemp);
						}
					}
					 
				}
			}
		
			FacesUtil.addInfoMessage("Anexo excluído com sucesso.");
	}
	
	public static String obterCaminhoAnexo() {
		return "C:\\DADOS_SISTEMA\\AGREGA\\ANEXOS\\CLIENTE\\";
	}
	
	public List<Anexo> preencherListaAnexos() {
		if(cliente != null && cliente.getId() != null){
			return anexos = anexoService.getByIdByTipo(ETipoEntidade.CLIENTE.getFlag(), cliente.getId(), null);
		}
		return null;
	}
	
		
	public void visualizaAnexo(Cliente item){
		if(item != null){
			preencherListaAnexos();
		}
	}
	
	public void arquivoUpload(FileUploadEvent uploadedFile) {
		  try {
			
			if(anexoService.getByIdByTipo(ETipoEntidade.CLIENTE.getFlag(), cliente.getId(), uploadedFile.getFile().getFileName()).size() >0){
				FacesUtil.addErrorMessage("Não é possível salvar o anexo "+ uploadedFile.getFile().getFileName() +". O mesmo já existe no banco de dados.");
				return;
			}
		    File file = new File(Constante.CLIENTE, uploadedFile.getFile().getFileName());
		 
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
			
		    RequestContext context = RequestContext.getCurrentInstance();
			context.update("tabelaListaAnexoSelecionado");
			FacesUtil.addInfoMessage("O arquivo " + uploadedFile.getFile() + " foi salvo!");
		    
		  } catch(IOException e) {
		    FacesContext.getCurrentInstance().addMessage(
		              null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		  }
	}
	
	private void salvarListaAnexos() {
		if (listaArquivosAnexados != null) {
			for (FileUploadEvent arquivoAnexo : listaArquivosAnexados) {
				Anexo anexo = null;
				try {
					if(anexoService.getByIdByTipo(ETipoEntidade.CLIENTE.getFlag(), cliente.getId(), arquivoAnexo.getFile().getFileName()).size()==0){
						anexo = new Anexo();
						anexo.setDescricao(arquivoAnexo.getFile().getFileName());
						anexo.setIdEntidade(cliente.getId());
						anexo.setTipo(ETipoEntidade.CLIENTE.getFlag());
						anexoService.salvar(anexo);
					}
				} catch (Exception e) {
					FacesUtil.addErrorMessage("Não é possível salvar o anexo.");
				}
			
			}
		}
		preencherListaAnexos();
	}


	public StreamedContent downloadAnexo(String arquivo) { 
		try {
			String caminho = obterCaminhoAnexo() + arquivo;
			FileInputStream stream = new FileInputStream(caminho);
			file = new DefaultStreamedContent(stream, caminho, arquivo);
		} catch (FileNotFoundException e) {
			FacesUtil.addErrorMessage("Anexo não encontrado.");
			return null;
		}
		return file;
	}
	
	
	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}
	
	
	
		
}
