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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.GregorianCalendar;

import br.com.sysagrega.controller.Qualificadores.QualificadorProposta;
import br.com.sysagrega.model.IProjeto;
import br.com.sysagrega.model.Enums.DominioStatusContrato;
import br.com.sysagrega.model.Enums.ETipoEntidade;
import br.com.sysagrega.model.imp.Aditivo;
import br.com.sysagrega.model.imp.Anexo;
import br.com.sysagrega.model.imp.Contrato;
import br.com.sysagrega.model.imp.Faturamento;
import br.com.sysagrega.model.imp.Parcela;
import br.com.sysagrega.model.imp.Projeto;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.model.imp.Situacao;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.service.IAditivoService;
import br.com.sysagrega.service.IAnexoService;
import br.com.sysagrega.service.IContratoService;
import br.com.sysagrega.service.IFaturamentoService;
import br.com.sysagrega.service.IProjetoService;
import br.com.sysagrega.service.IPropostaService;
import br.com.sysagrega.service.ISituacaoService;
import br.com.sysagrega.service.IUsuarioService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.Constante;
import br.com.sysagrega.util.EnviarEmail;
import br.com.sysagrega.util.jsf.FacesUtil;
import br.com.sysagrega.util.jsf.TipoItemValor;

@Named
@ViewScoped
public class ContratoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IContratoService contratoService;
	@Inject
	private IUsuarioService usuarioService; 
	@Inject
	private IPropostaService propostaService; 
	@Inject
	private IFaturamentoService faturamentoService;
	@Inject 
	private IProjetoService projetoService;
	@Inject
	private IAditivoService aditivoService;
	@Inject
	private ISituacaoService situacaoService;
	@Inject
	private IAnexoService anexoService;
	
	@Produces
	@QualificadorProposta
	private Contrato contrato;
	private Aditivo aditivo;
	private List<Parcela> listParcelaContrato;
	private List<Contrato> listContratos;
	private List<Aditivo> listarAditivos;
	private List<Faturamento> listParcelasContratoProjetos;
	private List<TipoItemValor> listarTipoItemValor;
	private List<Situacao> comboSituacoes;
	private List<String> comboStatusContrato;
	private Boolean pesquisar = false;
	private Boolean editar = false;
	private Boolean novo = false;
	private Boolean renderedAvista = null;
	private Boolean visualizar= false;
	private Long filtroSituacao;
	private Usuario usuarioLogado;
	private String filtroNrContrato;
	private String filtroStatus;
	

	private String filtroNomeCliente;
	private Date filtroDtIncial;
	private Date filtroDtFinal;
	private Boolean viewContrato = false;
	private List<Proposta> listProposta;
	private Boolean entraEditarVisualizar = false;
	
	
	// Manages Propertys
	@ManagedProperty(value="#{menuFaces}")
	private MenuBean menuBean;

	@PostConstruct
	public void inicializar() {
		usuarioLogado = obtenUsuarioLogado();
		contrato = new Contrato();
		aditivo = new Aditivo();
		listaPropostas();
		listStatusContrato(); 
	}
	
	public void listStatusContrato(){
		comboStatusContrato = new ArrayList<>();
		for (DominioStatusContrato item : DominioStatusContrato.values()) {
			comboStatusContrato.add(item.getDescricao());
		}
	}
	

	//Obten usuário logado
	public Usuario obtenUsuarioLogado(){
		SecurityContext contextString = SecurityContextHolder.getContext();
		if (contextString instanceof SecurityContext) {
			Authentication authentication = contextString.getAuthentication();
			if (authentication instanceof Authentication) {
				return usuarioService.getUserPerfilByLogin(authentication.getName());
			}
		}
		return null;
	}
	
	public void cadastrarContrato(){
		
		novo = true;
		viewContrato = false;
		pesquisar = true;
		editar = false;
		contrato = new Contrato();
		listContratos = new ArrayList<>();
		listParcelaContrato = new ArrayList<Parcela>();
		listParcelasContratoProjetos = new ArrayList<Faturamento>();
		
	}
	
	public void filtrarContratos() {
		listContratos = new ArrayList<>();
		try {
			listContratos = this.contratoService.getContratoByFilter(filtroNrContrato, filtroNomeCliente, filtroStatus, 
					filtroDtIncial, filtroDtFinal);
			if(listContratos == null || listContratos.isEmpty()){
				FacesUtil.addErrorMessage("Não foi encontrou nenhum resultado para a pesquisa.");	
			}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void visualizarContrato(Contrato contrato){
		this.contrato = contrato;
		pesquisar = true;
		editar = false;
		viewContrato = true;
		entraEditarVisualizar = false;
		comboSituacoes = situacaoService.getAll();
		//filtroSituacao = ESituacao.QUITADO.getFlag();
		listParcelaContrato = new ArrayList<Parcela>();
		listParcelasContratoProjetos = new ArrayList<Faturamento>();
		listaPropostas();
		obterListaAditivos(contrato);
		liberaFormaPagamento();
		verificaFormadePagamento(contrato);
		preencherListaAnexos();
	}
	

	public void verificaFormadePagamento(Contrato contrato) {
		if("Projeto".equals(this.contrato.getFormaPagamento())){
			/*List<Faturamento> listFaturamento = faturamentoService.getByContrato(this.contrato.getId());
			if(listFaturamento != null){
				listParcelasContratoProjetos = listFaturamento;
			}*/
		//	obterParcelasBySituacao();
		}else{
		if(contrato != null && contrato.getParcelas() != null){
			for (Parcela item : contrato.getParcelas()) {
				Parcela parcelaContrato = new Parcela();
				parcelaContrato.setId(item.getId());
				parcelaContrato.setNrParcela(item.getNrParcela());
				parcelaContrato.setDataVencimento(item.getDataVencimento());
				parcelaContrato.setValorParcela(item.getValorParcela());
				parcelaContrato.setObservacoesParcela(item.getObservacoesParcela());
				listParcelaContrato.add(parcelaContrato);
			}
		}
		}
	}

	public void obterListaAditivos(Contrato contrato) {
		if(contrato != null && contrato.getId() != null){
			Aditivo filtro = new Aditivo();
			filtro.setContrato(contrato);
			listarAditivos = aditivoService.getByContrato(filtro);
		}
	}
	
	public String obtemNumeroProjeto(Long id){
		IProjeto projeto = new Projeto();
		projeto = projetoService.getById(id);
		return projeto.getNumeroProjetoAgrega();
	}
	
	public void cancelar(){
		pesquisar = false;
		viewContrato = false;
		contrato = new Contrato();
		listContratos = new ArrayList<>();
		listProposta = new ArrayList<Proposta>();
		filtrarContratos();
		listaArquivosAnexados = new ArrayList<FileUploadEvent>();
		anexos = new ArrayList<Anexo>();
		fileUploadEvent = null;
		excluirAnexo = new Anexo();
		file = null;
	}
	
	
	public Double calcularValorComAditivos(){
		Double valor = 0.0;
		
		if(listarAditivos != null && !listarAditivos.isEmpty()){
			for (Aditivo item : listarAditivos) {
				valor = valor + item.getValor();
			}
		}else{
			listarAditivos = new ArrayList<>();
		}
		return valor;
	}

	//Edita contrato
	public void editContrato(Contrato contrato){
		this.contrato = contrato;
		viewContrato = false;
		pesquisar = true;
		editar = true;
		entraEditarVisualizar = false;
		comboSituacoes = situacaoService.getAll();
		obterListaAditivos(contrato);
		listaPropostas();
			
		listParcelaContrato = new ArrayList<Parcela>();
		listParcelasContratoProjetos = new ArrayList<Faturamento>();
		listaPropostas();
		obterListaAditivos(contrato);
		liberaFormaPagamento();
		verificaFormadePagamento(contrato);
		preencherListaAnexos();
	}
	
	public String concatNome(String param){
		String nome = param;
		if(nome.length() > 30){
			nome = nome.substring(0, 30)+"...";
		}
		return nome;
	}
			
	public void preExcluir(Contrato contrato){
		this.contrato = new Contrato();
		this.contrato = contrato;
		
		RequestContext context = RequestContext.getCurrentInstance();
		if(this.contrato.getStatusContrato().equals("Execução")){
			context.execute("PF('BloqueiExcluirContrato').show();");
		}else{
			context.execute("PF('PreExcluirContrato').show();");
		}
	}
	
	public void excluirContrato(){
		try{
			contratoService.excluirContrato(this.contrato);
			FacesUtil.addInfoMessage("Contrato Excluído com sucesso.");
			filtrarContratos();
	
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void preExcluirAditivo(Aditivo aditivo){
		try{
			this.aditivo = aditivo;
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('PreExcluirAditivo').show();");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void excluirAditivo(){
		try{
			if(aditivo != null){
				aditivoService.remover(aditivo);
				FacesUtil.addInfoMessage("Aditivo Excluído com sucesso.");
			}
			if(contrato != null && contrato.getId() != null){
				Aditivo  filtro = new Aditivo();
				filtro.setContrato(contrato);
				listarAditivos = aditivoService.getByContrato(filtro);	
			}
	
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	
	//Lista as propostas finalizadas relizadas na precificação
	public void listaPropostas(){
		listProposta = new ArrayList<Proposta>(); 
		if(viewContrato || editar){
			listProposta = propostaService.getAllPropostas();
		}else{
			for (Proposta item : propostaService.getAllPropostas()) {
				if("Contratada".equals(item.getStatusProposta())){
					listProposta.add(item);
				}
			}
		}
	}

	//Preenche os dados que vem da proposta na tela
	public void preecheDadosProposta(Proposta proposta){
		String nrContrato = contrato.getProposta().getNumeroProposta().replace("AC-", "").replace(".", "");
		nrContrato = nrContrato.substring(0, nrContrato.indexOf("/"));
		contrato.setNrContrato(nrContrato);
		contrato.setVlrContrato(contrato.getProposta().getValorTotalDaProposta());
	}
	
	//Lista as parcelas do contrato
	public void listParcelas(){
		if(entraEditarVisualizar){
			Parcela parcelas;
			listParcelaContrato = new ArrayList<Parcela>();
			Date dataparcela = new Date();
			dataparcela = contrato.getDataVencimento();
			
			for (int i = 0; i < contrato.getQuantidadeParcela(); i++) {
				parcelas = new Parcela();
				parcelas.setDataVencimento(dataparcela);
				parcelas.setValorParcela(contrato.getValorParcela());
				parcelas.setObservacoesParcela(contrato.getObservacoesParcelas());
				parcelas.setNrParcela(i+1);
				dataparcela = somaDias(dataparcela, 1);
				listParcelaContrato.add(parcelas);
			}
		}
		entraEditarVisualizar = true;
	}
	
	//Edita e confirma uma lista dinamicamente a lista de parcelas
   public void onRowEdit(RowEditEvent event) {
	   FacesMessage msg;
	   if(viewContrato){
		   msg = new FacesMessage("Parcela nº " + Integer.toString(((Parcela) event.getObject()).getNrParcela()), " somente vizualização");
	   }else{
		   msg = new FacesMessage("Parcela nº " + Integer.toString(((Parcela) event.getObject()).getNrParcela()), " Editada com sucesso");
	   }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
   //Cancela a edição de parcelas do contrato dinamicamente
	 public void onRowCancel(RowEditEvent event) {
		FacesMessage msg;
		if(viewContrato){
			msg = new FacesMessage("Parcela nº " + Integer.toString(((Parcela) event.getObject()).getNrParcela()), " somente vizualização");
		}else{
			msg = new FacesMessage("Edicação da parcela nº " + Integer.toString(((Parcela) event.getObject()).getNrParcela())," Cancelada");
		}
			FacesContext.getCurrentInstance().addMessage(null, msg);
    }
		
	public Date somaDias(Date data, int dias) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		cal.add(Calendar.MONTH, dias);
		return cal.getTime();
	}
	
	public void statusContratoAtual(String status){
		this.contrato.setStatusContrato(status);
		this.contrato.setDataAtualizacao(new Date());
		this.contrato.setUsuarioAtualizacao(usuarioLogado);
		
		if(status.equalsIgnoreCase("Cancelado")){
			this.contrato.setDataCancelamento(new Date());
		}else{
			this.contrato.setDataDistrato(new Date());
		}
		
		
		contratoService.salvar(contrato);
		listProposta = new ArrayList<Proposta>(); 
		pesquisar = false;
		
		FacesUtil.addInfoMessage("Distrato realizado com sucesso.");
	}
			
	//Salvar Contrato
	public void salvar(Long tipo){
		try{
			if(contrato.getId() == null){
			 contrato.setDataInclusao(new Date());
			 contrato.setStatusContrato("Vigente");
			 contrato.getProposta().setStatusProposta("Contratada");
			 contrato.setNomeCliente(contrato.getProposta().getCliente().getNome());
			}
			contrato.setDataAtualizacao(new Date());
			contrato.setUsuarioAtualizacao(usuarioLogado);
			
			if(listParcelaContrato!= null){
				Set<Parcela> parcelasContrato = new HashSet<>();
				parcelasContrato.addAll(listParcelaContrato);
				contrato.setParcelas(parcelasContrato);
			}else{
				contrato.setParcelas(new HashSet<>());
			}
			
			String assunto = "[Integra Informa - Alteração contrato: "+contrato.getNrContrato()+ " ]";
			String corpo = "Prezado, \n\n Os dados do contrato nº "+contrato.getNrContrato()+ " foi alterado pelo usuário(a) "+usuarioLogado.getNome()+".\n\n"
			+ " Atenciosamente,\n\n Sistema Integra \n\n";
			String destinatario = "adeliaguimaraes@agregaconsultores.com.br";
			
			contratoService.salvar(contrato);
			listProposta = new ArrayList<Proposta>(); 
			pesquisar = true;
			EnviarEmail.enviarEmail(assunto, corpo, destinatario);
			FacesUtil.addInfoMessage("Contrato salva com sucesso.");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void salvarAditivo(){
		try{
		
		if(aditivo.getValor() == null){
			//RequestContext context = RequestContext.getCurrentInstance();
			//context.execute("PF('dialogAditivo').show();");
			FacesUtil.addErrorMessage("O campo valor é obrigatório.");
			return;
		}
		aditivo.setAtivo(true);
		aditivo.setContrato(contrato);
		aditivo.setUsuarioAtualizacao(usuarioLogado);
		aditivoService.salvar(aditivo);
		aditivo = new Aditivo();
		visualizar = false;
		
		if(contrato != null && contrato.getId() != null){
			Aditivo  filtro = new Aditivo();
			filtro.setContrato(contrato);
			listarAditivos = aditivoService.getByContrato(filtro);	
		}
		FacesUtil.addInfoMessage("Aditivo salvo com sucesso.");

	} catch (NegocioException e) {
		FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	private Boolean liberaFormaPagto = false;
	public void liberaFormaPagamento(){
		if(this.contrato.getNrContrato() != null && this.contrato.getVlrContrato() != null && this.contrato.getDataAssinatura() != null){
			setLiberaFormaPagto(true);
		}else{
			this.contrato.setFormaPagamento("");
			this.contrato.setValorParcela(null);
			setLiberaFormaPagto(false);
			listParcelas();
		}
	}
	
	public void formaPagamento(){
		if(this.contrato.getFormaPagamento().equals("Avista")){
			renderedAvista = false;
			this.contrato.setQuantidadeParcela(1);
			this.contrato.setValorParcela(this.contrato.getVlrContrato());
			this.contrato.setDataVencimento(this.contrato.getDataAssinatura());
		}else if(this.contrato.getFormaPagamento().equals("Parcelado")){
			renderedAvista = true;
			//this.contrato.setQuantidadeParcela(0);
		}else if(this.contrato.getFormaPagamento().equals("Projeto")){
			renderedAvista = false;
			this.contrato.setQuantidadeParcela(0);
			this.contrato.setValorParcela(null);
			this.contrato.setDataVencimento(null);
			/*if(this.contrato.getSaldoContrato() == null){
				this.contrato.setSaldoContrato(this.contrato.getVlrContrato());
			}*/
		}
		listParcelas();
	}
	
	/**
	 * Metodos Gets e Sets
	 * @return
	 */
	
	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
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

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public Boolean getPesquisar() {
		return pesquisar;
	}

	public void setPesquisar(Boolean pesquisar) {
		this.pesquisar = pesquisar;
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

	public Boolean getViewContrato() {
		return viewContrato;
	}

	public void setViewContrato(Boolean viewContrato) {
		this.viewContrato = viewContrato;
	}

	public List<Proposta> getListProposta() {
		return listProposta;
	}

	public void setListProposta(List<Proposta> listProposta) {
		this.listProposta = listProposta;
	}

	public List<Parcela> getListParcelaContrato() {
		return listParcelaContrato;
	}

	public void setListParcelaContrato(List<Parcela> listParcelaContrato) {
		this.listParcelaContrato = listParcelaContrato;
	}

	public String getFiltroNrContrato() {
		return filtroNrContrato;
	}

	public void setFiltroNrContrato(String filtroNrContrato) {
		this.filtroNrContrato = filtroNrContrato;
	}

	public String getFiltroNomeCliente() {
		return filtroNomeCliente;
	}

	public void setFiltroNomeCliente(String filtroNomeCliente) {
		this.filtroNomeCliente = filtroNomeCliente;
	}

	public List<Contrato> getListContratos() {
		return listContratos;
	}

	public void setListContratos(List<Contrato> listContratos) {
		this.listContratos = listContratos;
	}
	public Boolean getRenderedAvista() {
		return renderedAvista;
	}

	public void setRenderedAvista(Boolean renderedAvista) {
		this.renderedAvista = renderedAvista;
	}

	public Boolean getLiberaFormaPagto() {
		return liberaFormaPagto;
	}

	public void setLiberaFormaPagto(Boolean liberaFormaPagto) {
		this.liberaFormaPagto = liberaFormaPagto;
	}

	public List<Faturamento> getListParcelasContratoProjetos() {
		return listParcelasContratoProjetos;
	}

	public void setListParcelasContratoProjetos(List<Faturamento> listParcelasContratoProjetos) {
		this.listParcelasContratoProjetos = listParcelasContratoProjetos;
	}

	public List<TipoItemValor> getListarTipoItemValor() {
		return listarTipoItemValor;
	}

	public void setListarTipoItemValor(List<TipoItemValor> listarTipoItemValor) {
		this.listarTipoItemValor = listarTipoItemValor;
	}
	public Long getFiltroSituacao() {
		return filtroSituacao;
	}

	public void setFiltroSituacao(Long filtroSituacao) {
		this.filtroSituacao = filtroSituacao;
	}

	public List<Situacao> getComboSituacoes() {
		return comboSituacoes;
	}

	public void setComboSituacoes(List<Situacao> comboSituacoes) {
		this.comboSituacoes = comboSituacoes;
	}

	public List<Aditivo> getListarAditivos() {
		return listarAditivos;
	}

	public void setListarAditivos(List<Aditivo> listarAditivos) {
		this.listarAditivos = listarAditivos;
	}

	public Aditivo getAditivo() {
		return aditivo;
	}

	public void setAditivo(Aditivo aditivo) {
		this.aditivo = aditivo;
	}

	public Boolean getVisualizar() {
		return visualizar;
	}

	public void setVisualizar(Boolean visualizar) {
		this.visualizar = visualizar;
	}

	public Boolean getEntraEditarVisualizar() {
		return entraEditarVisualizar;
	}

	public void setEntraEditarVisualizar(Boolean entraEditarVisualizar) {
		this.entraEditarVisualizar = entraEditarVisualizar;
	}

	public Date getFiltroDtIncial() {
		return filtroDtIncial;
	}

	public void setFiltroDtIncial(Date filtroDtIncial) {
		this.filtroDtIncial = filtroDtIncial;
	}

	public Date getFiltroDtFinal() {
		return filtroDtFinal;
	}

	public void setFiltroDtFinal(Date filtroDtFinal) {
		this.filtroDtFinal = filtroDtFinal;
	}


	public List<String> getComboStatusContrato() {
		return comboStatusContrato;
	}

	public void setComboStatusContrato(List<String> comboStatusContrato) {
		this.comboStatusContrato = comboStatusContrato;
	}

	public String getFiltroStatus() {
		return filtroStatus;
	}

	public void setFiltroStatus(String filtroStatus) {
		this.filtroStatus = filtroStatus;
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
			if (this.excluirAnexo != null && this.excluirAnexo.getId() != null) {
				anexoService.excluir(excluirAnexo);
				if(obterCaminhoAnexo() != null){
					File deletarArquivoDiretorio = new File(new StringBuilder(obterCaminhoAnexo()).append(File.separator).append(this.excluirAnexo.getDescricao()).toString());
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
							itemTemp.setIdEntidade(this.contrato.getId());
							itemTemp.setTipo(ETipoEntidade.CONTRATO.getFlag());
							anexos.add(itemTemp);
						}
					}
					 
				}
			}
		
			FacesUtil.addInfoMessage("Anexo excluído com sucesso.");
	}
	
	public static String obterCaminhoAnexo() {
		return "C:\\DADOS_SISTEMA\\AGREGA\\ANEXOS\\CONTRATO\\";
	}
	
	public List<Anexo> preencherListaAnexos() {
		if(this.contrato != null && this.contrato.getId() != null){
			return anexos = anexoService.getByIdByTipo(ETipoEntidade.CONTRATO.getFlag(), this.contrato.getId(), null);
		}
		return null;
	}
	
		
	public void visualizaAnexo(Projeto item){
		if(item != null){
			preencherListaAnexos();
		}
	}
	
	public void arquivoUpload(FileUploadEvent uploadedFile) {
		  try {
			
			if(anexoService.getByIdByTipo(ETipoEntidade.CONTRATO.getFlag(), this.contrato.getId(), uploadedFile.getFile().getFileName()).size() >0){
				FacesUtil.addErrorMessage("Não é possível salvar o anexo "+ uploadedFile.getFile().getFileName() +". O mesmo já existe no banco de dados.");
				return;
			}
		    File file = new File(Constante.CONTRATO, uploadedFile.getFile().getFileName());
		 
		    OutputStream out = new FileOutputStream(file);
		    out.write(uploadedFile.getFile().getContents());
		    out.close();
		    
		    if (listaArquivosAnexados == null) {
				listaArquivosAnexados = new ArrayList<>();
			}

			listaArquivosAnexados.add(uploadedFile);
		 
			Anexo anexo = new Anexo();
			anexo.setDescricao(uploadedFile.getFile().getFileName());

			if (anexos == null) {
				anexos = new ArrayList<>();
			}
			getAnexos().add(anexo);
			salvarListaAnexos();

		    
		    
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
					if(anexoService.getByIdByTipo(ETipoEntidade.CONTRATO.getFlag(), this.contrato.getId(), arquivoAnexo.getFile().getFileName()).size()==0){
						anexo = new Anexo();
						anexo.setDescricao(arquivoAnexo.getFile().getFileName());
						anexo.setIdEntidade(this.contrato.getId());
						anexo.setTipo(ETipoEntidade.CONTRATO.getFlag());
						anexoService.salvar(anexo);
					}
					
					RequestContext context = RequestContext.getCurrentInstance();
					context.update("tabelaListaAnexoSelecionado");
					FacesUtil.addInfoMessage("O arquivos anexado e salva com sucesso!");
			
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
	
	public FileUploadEvent getFileUploadEvent() {
		arquivoUpload(fileUploadEvent);
		return fileUploadEvent;
	}

	public void setFileUploadEvent(FileUploadEvent fileUploadEvent) {
		this.fileUploadEvent = fileUploadEvent;
	}
	
	public List<Anexo> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<Anexo> anexos) {
		this.anexos = anexos;
	}
	
	public List<FileUploadEvent> getListaArquivosAnexados() {
		return listaArquivosAnexados;
	}

	public void setListaArquivosAnexados(List<FileUploadEvent> listaArquivosAnexados) {
		this.listaArquivosAnexados = listaArquivosAnexados;
	}
	
	public Anexo getExcluirAnexo() {
		return excluirAnexo;
	}

	public void setExcluirAnexo(Anexo excluirAnexo) {
		this.excluirAnexo = excluirAnexo;
	}
	
	
}
