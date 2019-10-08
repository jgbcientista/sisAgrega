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
import javax.servlet.http.HttpSession;

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
import br.com.sysagrega.model.Enums.ETipoDespesa;
import br.com.sysagrega.model.Enums.ETipoEntidade;
import br.com.sysagrega.model.Enums.ETipoProposta;
import br.com.sysagrega.model.Enums.ETipoUnidade;
import br.com.sysagrega.model.Enums.TipoPagina;
import br.com.sysagrega.model.imp.Anexo;
import br.com.sysagrega.model.imp.Area;
import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.model.imp.Contrato;
import br.com.sysagrega.model.imp.CustoAdministrativo;
import br.com.sysagrega.model.imp.CustoAdministrativoHistorico;
import br.com.sysagrega.model.imp.CustoBdiComissao;
import br.com.sysagrega.model.imp.CustoDeslocamento;
import br.com.sysagrega.model.imp.CustoDeslocamentoHistorico;
import br.com.sysagrega.model.imp.CustoExecucao;
import br.com.sysagrega.model.imp.CustoExecucaoHistorico;
import br.com.sysagrega.model.imp.CustoOperacional;
import br.com.sysagrega.model.imp.CustoOperacionalHistorico;
import br.com.sysagrega.model.imp.CustoPlanilhaFinanceira;
import br.com.sysagrega.model.imp.CustoPlanilhaTecnica;
import br.com.sysagrega.model.imp.CustoSeguranca;
import br.com.sysagrega.model.imp.CustoSegurancaHistorico;
import br.com.sysagrega.model.imp.Parcela;
import br.com.sysagrega.model.imp.PlanilhaTecEquipamento;
import br.com.sysagrega.model.imp.Projeto;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.model.imp.PropostaHistorico;
import br.com.sysagrega.model.imp.Servico;
import br.com.sysagrega.model.imp.TipoDespesa;
import br.com.sysagrega.model.imp.TipoItemProposta;
import br.com.sysagrega.model.imp.TipoServico;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.service.IAnexoService;
import br.com.sysagrega.service.IAreaService;
import br.com.sysagrega.service.IClienteService;
import br.com.sysagrega.service.IContratoService;
import br.com.sysagrega.service.ICustoAdministrativoHistoricoService;
import br.com.sysagrega.service.ICustoAdministrativoService;
import br.com.sysagrega.service.ICustoBDIComissaoService;
import br.com.sysagrega.service.ICustoDeslocamentoHistoricoService;
import br.com.sysagrega.service.ICustoDeslocamentoService;
import br.com.sysagrega.service.ICustoExecucaoHistoricoService;
import br.com.sysagrega.service.ICustoExecucaoService;
import br.com.sysagrega.service.ICustoOperacionalHistoricoService;
import br.com.sysagrega.service.ICustoOperacionalService;
import br.com.sysagrega.service.ICustoPlanilhaFinanceiraService;
import br.com.sysagrega.service.ICustoPlanilhaTecnicaService;
import br.com.sysagrega.service.ICustoSegurancaHistoricoService;
import br.com.sysagrega.service.ICustoSegurancaService;
import br.com.sysagrega.service.IPlanilhaTecEquipamentoService;
import br.com.sysagrega.service.IPropostaService;
import br.com.sysagrega.service.IServicoService;
import br.com.sysagrega.service.ITipoDespesaService;
import br.com.sysagrega.service.ITipoItemPropostaService;
import br.com.sysagrega.service.ITipoServicoService;
import br.com.sysagrega.service.IUsuarioService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.Constante;
import br.com.sysagrega.util.RelatorioUtil;
import br.com.sysagrega.util.jsf.FacesUtil;
import br.com.sysagrega.util.jsf.TipoItemValor;

@Named
@ViewScoped
public class PropostaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private IPropostaService propostaService;
	
	@Inject
	private IUsuarioService usuarioService; 

	@Inject
	private ITipoDespesaService tipoDespesaService;
	
	@Inject
	private IClienteService clienteService;
	
	@Inject
	private ICustoExecucaoService custoExecucaoService;
	
	@Inject
	private ICustoDeslocamentoService custoDeslocamentoService;
	
	@Inject
	private IContratoService contratoService;
	
	@Inject
	private ICustoOperacionalService custoOperacionalService;
	
	@Inject
	private ICustoSegurancaService custoSegurancaService;
	
	@Inject
	private ICustoAdministrativoService custoAdministrativoService;

	@Inject
	private ICustoBDIComissaoService custoBDIComissaoService;
	
	@Inject
	private ITipoItemPropostaService tipoItemPropostaService;
	
	@Inject
	private ICustoPlanilhaFinanceiraService custoPlanilhaFinanceiraService;
	
	@Inject
	private ICustoPlanilhaTecnicaService custoPlanilhaTecnicaService;
	
	@Inject
	private IPlanilhaTecEquipamentoService planilhaTecEquipamentoService;
	
	@Inject
	private ITipoServicoService tipoServicoService;
	
	@Inject
	private IAreaService areaService;
	
	@Inject
	private IServicoService servicoService;
	
	@Inject
	private ICustoAdministrativoHistoricoService custoAdministrativoHistoricoService;
	
	@Inject
	private ICustoExecucaoHistoricoService custoExecucaoHistoricoService;
	
	@Inject
	private ICustoDeslocamentoHistoricoService custoDeslocamentoHistoricoService;
	
	@Inject
	private ICustoSegurancaHistoricoService custoSegurancaHistoricoService;
	
	@Inject
	private ICustoOperacionalHistoricoService custoOperacionalHistoricoService;
	
	@Inject
	private IAnexoService anexoService;
	
	@Produces
	@QualificadorProposta
	private Proposta proposta;
	private Long filtroIdCliente;

	private List<Area> areas;
	private List<TipoServico> tipoServicos;
	private List<Servico> servicos;
	private List<PropostaHistorico> historicos;
	private List<TipoDespesa> tipoDespesas;
	private List<Cliente> clientes;
	private List<String> tiposUnidades;
	
	private List<Proposta> comboNumeroPropostas;
	private List<Proposta> propostas;
	private List<TipoItemProposta> tipoItensPropostaFinanceira;
	private List<TipoItemProposta> tipoItensPropostaTecnica;
	private List<TipoItemProposta> tipoItensPropostaTecnicaFinanceira;
	private List<CustoExecucao> listExecucaoTemp;
	private List<CustoDeslocamento> listDeslocamentoTemp;
	private List<CustoOperacional> listOperacionalTemp;
	private List<CustoSeguranca> listSegurancaTemp;
	private List<CustoAdministrativo> listAdministrativoTemp;
	private List<CustoPlanilhaFinanceira> listarCustoPlanilhaFinanceira;
	private List<CustoPlanilhaFinanceira> listCustoPlanFinanceiraTemp;
	private List<CustoPlanilhaTecnica> listCustoEquipeTecnica;
	private List<PlanilhaTecEquipamento> listPlanilhaTecEquipamento;
	
	private String filtroNumeroProposta;
	private String filtroCliente;
	private String descricaoTipoItem;
	private String descricaoTipoItemFinanceiro;
	private String descricaoTipoItemTecFinan;
	private String tituloTipoItem;
	private String tituloTipoItemFinanceiro;
	private String tituloTipoItemTecFinan;
	private String idQuantidadeItem;
	private String labelTipoProposta;
	private String labelBotaoSalvarPrecificacao = "Salvar Precificação";
	private String labelBotaoCriarProposta = "Criar Proposta";
	private String statusProposta;// = "Pendente";
	private String tipoAtualizacao = "";
	private String labelBotaoAdicionar = "Adicionar";
	private String labelBotaoAdicionarItemProposta = "Adicionar";
	private String labelBotaoAdicionarItemPropostaTec = "Adicionar";
	private String nomeSalvar;
	
	private Long tipoDespesa;
	private Date filtroDataInicial;
	private Date filtroDataFinal;
	
	private CustoPlanilhaTecnica custoPlanilhaEquipeTecnica;
	private CustoPlanilhaTecnica custoPlanilhaEquipeTecnicaExcluir;
	private PlanilhaTecEquipamento planilhaTecEquipamento;
	private PlanilhaTecEquipamento planilhaTecEquipamentoExcluir;
	private CustoPlanilhaFinanceira custoPlanilhaFinanceira;
	private CustoPlanilhaFinanceira custoPlanilhaFinanceiraExcluir;
	private CustoAdministrativo custoAdministrativo;
	private CustoSeguranca custoSeguranca;
	private CustoDeslocamento custoDeslocamento;
	private CustoOperacional custoOperacional;
	private CustoExecucao custoExecucao;
	
	private Long idCliente;
	private Long tipoExclusaoDespesa = 0L;
	private TipoItemValor tipoItemValor;
	private TipoItemProposta tipoItemProposta;
	
	private Double valorBDI;
	private Double valorComissao;
	private Double valorISS;
	private Double valorConfins;
	private Double valorPis;
	private Double valorCSLL;
	private Double valorIr;
	private Double valorImpostos;
	
	private boolean viewProposta;
	private Boolean voltar;
	private Boolean novaProposta = false;
	private Boolean pesquisar = true;
	private Boolean visualizar = false;
	private Boolean editar = false;
	private Boolean novaPrecificacao = false;
	private Boolean visualizarHistorico = false;
	private Boolean excluirAtualizada = false;
	private Boolean excluirItemProposta =false;
	private Boolean ativaCombo = true;
	private Boolean btAtivo = false;
	private Boolean validaMsg = true;
	private Boolean inseriPlanFinancera = false;
	private Boolean inseriPlanFinanceraTec = false;
	private Boolean inseriPlanEquipeTec = false;
	private Boolean inseriPlanEquipamento = false;
	private Boolean habilitaIrPara;
	private Usuario usuarioLogado;
	private Boolean desabilitaCampo = false;
	private PropostaHistorico propostaHistorico;
	private Boolean acaoVisualHistorico = false;

	private List<CustoAdministrativoHistorico> listCustoAdmHistory;
	private List<CustoExecucaoHistorico> listCustoExecucaoHistory;
	private List<CustoSegurancaHistorico> listCustoSegurancaHistory;
	private List<CustoDeslocamentoHistorico> listDeslocamentoHistory;
	private List<CustoOperacionalHistorico> listOperacionalHistory;
	
	private Boolean visualizarProTec = false;
	private Boolean visualizarProFinanc = false;
	private Boolean visualizarPrTecFinac = false;
	private Boolean propostaTecEmitida = true;
	private Boolean propostaFinaceiraEmitida = true;
	private Boolean propostaTecFinancEmitida = true;
	private Long tipoProposta;
	private TipoItemValor tipoItemValorGenerica;
	
	//Contrato
	private List<Parcela> listParcelaContrato;
	private Contrato contrato;
	private Boolean liberaFormaPagto = false;
	
	// Manages Propertys
	@ManagedProperty(value="#{menuFaces}")
	private MenuBean menuBean;
	
	@PostConstruct
	public void inicializar() {
		listCustoAdmHistory = new ArrayList<>();
		listCustoExecucaoHistory = new ArrayList<>();
		listCustoSegurancaHistory = new ArrayList<>();
		listDeslocamentoHistory = new ArrayList<>();
		listOperacionalHistory = new ArrayList<>();
		contrato = new Contrato();

		// Informacoes do usuario logado.
		  SecurityContext contextString = SecurityContextHolder.getContext();
		  	if (contextString instanceof SecurityContext) {
		  Authentication authentication = contextString.getAuthentication();
		  if (authentication instanceof Authentication) {
			  usuarioLogado = usuarioService.getUserPerfilByLogin(authentication.getName());
		  	}
	     }
		 
	  	if(proposta == null){
			proposta = new Proposta();
			proposta.setCliente(new Cliente());
			proposta.setDataInclusao(new Date(1L));
		}
		
		tiposUnidades = new ArrayList<>();
		for (ETipoUnidade tipo : ETipoUnidade.values()) {
			tiposUnidades.add(tipo.getDescricao());
		}
		
		clientes = clienteService.getClienteByAtivo();
		tipoDespesas = tipoDespesaService.getAllTipoDespesas();
		comboNumeroPropostas = this.propostaService.getAllPropostas();
		custoPlanilhaFinanceira = new CustoPlanilhaFinanceira();
		custoPlanilhaEquipeTecnica =  new CustoPlanilhaTecnica();
		planilhaTecEquipamento =  new PlanilhaTecEquipamento();
		tipoItemProposta = new TipoItemProposta();
		tipoItemValor = new TipoItemValor();
		
		listExecucaoTemp = new ArrayList<CustoExecucao>();
		listDeslocamentoTemp = new ArrayList<CustoDeslocamento>();
		listOperacionalTemp = new ArrayList<CustoOperacional>();
		listSegurancaTemp = new ArrayList<CustoSeguranca>();
		listAdministrativoTemp = new ArrayList<CustoAdministrativo>();
		listCustoPlanFinanceiraTemp = new ArrayList<CustoPlanilhaFinanceira>();
		
		//equipe Tecnica
		listCustoEquipeTecnica = new ArrayList<>();
		
		//carrega plan equipamento
		listPlanilhaTecEquipamento =  new ArrayList<>();
				
		if(this.proposta != null && this.proposta.getId() !=null){
			tipoItensPropostaFinanceira = tipoItemPropostaService.getByPropostaId(this.proposta.getId(), ETipoProposta.FINANCEIRA.getFlag());
			tipoItensPropostaTecnica = tipoItemPropostaService.getByPropostaId(this.proposta.getId(), ETipoProposta.TECNICA.getFlag());
			tipoItensPropostaTecnicaFinanceira = tipoItemPropostaService.getByPropostaId(this.proposta.getId(), ETipoProposta.TECN_FINANCE.getFlag());
		}else{
			tipoItensPropostaFinanceira = new ArrayList<TipoItemProposta>();
			tipoItensPropostaTecnica = new ArrayList<TipoItemProposta>();
			tipoItensPropostaTecnicaFinanceira = new ArrayList<TipoItemProposta>();
		}
		tipoServicos = tipoServicoService.getAllTipoServicos();
		servicos = new ArrayList<>();
		areas = areaService.getAllAreas();
		labelTipoProposta ="";
		voltar = true;
		visualizar = false;
		editar = false;
		pesquisar = true;
		novaProposta = false;
		novaPrecificacao = false;
		setPropostaHistorico(new PropostaHistorico());
		
		verificaSeInseriPlanfinanceira();
		verificaSeInseriPlanfinanceiraTec();
		verificaSeInseriEquipeTecnica();
		verificaSeInseriEquipamento();
	}

	public void filtrarServico() {
		if(this.proposta.getIdServicoNegocio() !=null && this.proposta.getIdArea() !=null){
			servicos = new ArrayList<>();
			servicos = this.servicoService.getServicoByFilter(this.proposta.getIdServicoNegocio(),this.proposta.getIdArea(), null);
		}
	}
	
	// Calcula o BDI
	public void calcularBDIComissao(){
		double zerabdi = 0;
		if(this.proposta.getId() == null){
			FacesUtil.addErrorMessage("Por favor, salve a proposta antes de adicionar itens.");
			return;
		}
		if((proposta.getPercentualBDI() != null && proposta.getPercentualBDI() >=0) || (proposta.getPercentualComissao() != null && proposta.getPercentualComissao() >=0 ) ){
			if(proposta.getPercentualBDI() != null && proposta.getPercentualBDI() >=0 && proposta.getPercentualComissao() == null){
				if(proposta.getValorTotalSemBdiComissao() !=null && proposta.getPercentualBDI() != null){
					this.proposta.setValorTotalComBdiComissao(proposta.getValorTotalSemBdiComissao() +(proposta.getValorTotalSemBdiComissao() * (proposta.getPercentualBDI()/100)));
				} 
			}
			if(proposta.getPercentualComissao() != null && proposta.getPercentualComissao() >=0 && proposta.getPercentualBDI() == null){
				if(proposta.getValorTotalSemBdiComissao() !=null && proposta.getPercentualComissao() != null){
					this.proposta.setValorTotalComBdiComissao(proposta.getValorTotalSemBdiComissao() +(proposta.getValorTotalSemBdiComissao() * (proposta.getPercentualComissao()/100)));
				} 
			}
			if(proposta.getPercentualComissao() != null && proposta.getPercentualComissao() >=0 && proposta.getPercentualBDI() != null && proposta.getPercentualBDI() >=0){
				if(proposta.getValorTotalSemBdiComissao() !=null && proposta.getPercentualBDI() != null){
					Double bdiMaisComissao =proposta.getValorTotalSemBdiComissao() +(proposta.getValorTotalSemBdiComissao() * (proposta.getPercentualBDI()/100))+ proposta.getValorTotalSemBdiComissao() * (proposta.getPercentualComissao()/100);
					proposta.setValorTotalComBdiComissao(bdiMaisComissao);
				}
			} 
		}else{
			this.proposta.setValorTotalComBdiComissao(zerabdi);
		}
	}
	
	//Verifica qual tipo de proposta foi selecionada
	public void verificarTipoItem(){
		tipoItemProposta = new TipoItemProposta();
	}
	
	//Adiciona os ítens na proposta financeira
	public void adicionarEquipeTecnica(){
		custoPlanilhaEquipeTecnica.setProposta(proposta);
		if(custoPlanilhaFinanceira != null){
			custoPlanilhaEquipeTecnica = custoPlanilhaTecnicaService.salvar(custoPlanilhaEquipeTecnica);
		}
		if(proposta.getListarCustoEquipeTecnica() == null){
			proposta.setListarCustoEquipeTecnica(new ArrayList<>());
		}
		proposta.getListarCustoEquipeTecnica().add(custoPlanilhaEquipeTecnica);
		
		carregaEquipeTecnica();
		if(btAtivo){
			excluirDespesas();
			btAtivo = false;
		}
		custoPlanilhaEquipeTecnica = new CustoPlanilhaTecnica();
		
	}
	
	//Adiciona Equipamento Proposta Técnica
		public void adicionarEquipamento(){
			planilhaTecEquipamento.setProposta(proposta);
			if(planilhaTecEquipamento != null){
				planilhaTecEquipamento = planilhaTecEquipamentoService.salvar(planilhaTecEquipamento);
			}
			if(proposta.getListarPlanilhaEquipamento() == null){
				proposta.setListarPlanilhaEquipamento(new ArrayList<>());
			}
			proposta.getListarPlanilhaEquipamento().add(planilhaTecEquipamento);
			
			carregaPlanEquipamento();
			if(btAtivo){
				excluirDespesas();
				btAtivo = false;
			}
			desabilitaCampo =false;
			planilhaTecEquipamento = new PlanilhaTecEquipamento();
		}
	
	
	public void adicionarTipoItemHH(){
		if(custoPlanilhaFinanceira.getQuantidade()!= null && custoPlanilhaFinanceira.getQuantidade().equals(0L) &&
				custoPlanilhaFinanceira.getValorTotal()==null){
			FacesUtil.addErrorMessage("Por favor, preencha a quantidade de horas.");
			return;
		}else if (custoPlanilhaFinanceira.getQuantidade()== null && custoPlanilhaFinanceira.getValorTotal()==null){
			FacesUtil.addErrorMessage("Por favor, preencha a quantidade de horas.");
			return;
		}
		if(custoPlanilhaFinanceira.getValorUnitario() != null && custoPlanilhaFinanceira.getValorUnitario().equals(0L)
				&& custoPlanilhaFinanceira.getValorTotal() == null){
			FacesUtil.addErrorMessage("Por favor, preencha o valor de cada hora.");
			return;
		}else if (custoPlanilhaFinanceira.getValorUnitario() == null && custoPlanilhaFinanceira.getValorTotal()==null){
			FacesUtil.addErrorMessage("Por favor, preencha o valor de cada hora.");
			return;
		}
		
		if(custoPlanilhaFinanceira.getValorUnitario() != null && custoPlanilhaFinanceira.getQuantidade()!= null ){
			custoPlanilhaFinanceira.setValorTotal(custoPlanilhaFinanceira.getValorUnitario()*custoPlanilhaFinanceira.getQuantidade());
		}
		custoPlanilhaFinanceira.setProposta(proposta);
		custoPlanilhaFinanceira = custoPlanilhaFinanceiraService.salvar(custoPlanilhaFinanceira);
		custoPlanilhaFinanceira.setValorTotalByProposta(calculoValorTotalCustoPlanilhaFinanceira());
		
		if(proposta.getListarCustoPlanilhaFinanceira() == null){
			proposta.setListarCustoPlanilhaFinanceira(new ArrayList<>());
		}
		
		proposta.getListarCustoPlanilhaFinanceira().add(custoPlanilhaFinanceira);
		calcularValorTotalCustoPlanilhaFinanceira();
		
		carregaPlanilhaFinanceira();
		if(btAtivo){
			excluirDespesas();
			btAtivo = false;
		}
		custoPlanilhaFinanceira = new CustoPlanilhaFinanceira();
	}
	
	//Verifica se insere a planilha financeira;
	public void verificaSeInseriPlanfinanceira(){
		if (proposta.getInserirPlanFinan() != null && proposta.getInserirPlanFinan()){
			inseriPlanFinancera =true;
		}else{
			inseriPlanFinancera =false;
		}
	}
	
	//Verifica se insere a planilha financeira;
	public void verificaSeInseriPlanfinanceiraTec(){
		if (proposta.getInserirPlanCom() != null && proposta.getInserirPlanCom()){
			inseriPlanFinanceraTec =true;
		}else{
			inseriPlanFinanceraTec =false;
		}
	}
	
	//Verifica se insere a planilha financeira;
	public void verificaSeInseriEquipeTecnica(){
		if (proposta.getInserirPlanTec() != null && proposta.getInserirPlanTec() ){
			inseriPlanEquipeTec =true;
		}else{
			inseriPlanEquipeTec =false;
		}
	}
	
	//Verifica se insere a planilha financeira;
	public void verificaSeInseriEquipamento(){
		if (proposta.getInserirPlanTecEquipamento() != null && proposta.getInserirPlanTecEquipamento() ){
			inseriPlanEquipamento =true;
		}else{
			inseriPlanEquipamento =false;
		}
	}
	
	//Verifica se insere a planilha financeira;
	public void desabilitaCampos(){
		if (planilhaTecEquipamento.getItem() != null && planilhaTecEquipamento.getItem() ){
			desabilitaCampo =true;
		}else{
			desabilitaCampo =false;
		}
	}
	
	
	
	public double calculoDesconto(){
		Double totalComDesconto = calculoValorTotalCustoPlanilhaFinanceira();
		if(this.proposta.getDescPlanFin() != null){
			totalComDesconto = totalComDesconto - proposta.getDescPlanFin();
		}
		this.proposta.setVlrNegTotalPlanFinanceira(totalComDesconto);
		return totalComDesconto;
	}
	
	public void adicionarTipoItem(){
		validaMsg = false;
		if(excluirAtualizada){
			if(tipoAtualizacao.equals(ETipoDespesa.CUSTO_EXECUCAO.getDescricao())){
				excluirCustoExecucao();
			}else if(tipoAtualizacao.equals(ETipoDespesa.CUSTO_DESLOCAMENTO.getDescricao())){
				excluirCustoDeslocamento();
			}else if(tipoAtualizacao.equals(ETipoDespesa.CUSTO_DESPESA_OPERACIONAL.getDescricao())){
				excluirCustoOperacional();
			}else if(tipoAtualizacao.equals(ETipoDespesa.CUSTO_DESPESA_SEGURANCA.getDescricao())){
				excluirCustoSeguranca();
			}else if(tipoAtualizacao.equals(ETipoDespesa.CUSTO_DESPESA_ADMINISTRATIVA.getDescricao())){
				excluirCustoAdministrativo();
			}
			excluirAtualizada = false;
			tipoAtualizacao = "";
			labelBotaoAdicionar = "Adicionar";
			btAtivo = false;
		}
				
		if("".equals(tipoItemValor.getDescricao())){
			FacesUtil.addErrorMessage("Por favor, preencha a descrição do custo.");
			return;	
		}
		
		if(tipoItemValor != null && tipoItemValor.getTipoDespesa()== null){
			FacesUtil.addErrorMessage("Por favor, selecione o tipo de despesa.");
			return;	
		}
		
		if(tipoItemValor != null && tipoItemValor.getQuantidade() == null){
			FacesUtil.addErrorMessage("Por favor, preencha a quantidade de itens.");
			return;	
		}
		
		if(tipoItemValor.getValorUnitario()== null){
			FacesUtil.addErrorMessage("Por favor, preencha o campo valor unitário.");
			return;	
		}
		
		if(tipoItemValor.getTipoDespesa().equals(ETipoDespesa.CUSTO_EXECUCAO.getFlag())){
			somarValorExecucao();
		}
		else if(tipoItemValor.getTipoDespesa().equals(ETipoDespesa.CUSTO_DESLOCAMENTO.getFlag())){
			somarValorCustosDeslocamento();
		}
		else if(tipoItemValor.getTipoDespesa().equals(ETipoDespesa.CUSTO_DESPESA_OPERACIONAL.getFlag())){
			somarValorCustosOperacionais();
		}
		
		else if(tipoItemValor.getTipoDespesa().equals(ETipoDespesa.CUSTO_DESPESA_SEGURANCA.getFlag())){
			somarValorCustosSeguranca();
		}
		else if(tipoItemValor.getTipoDespesa().equals(ETipoDespesa.CUSTO_DESPESA_ADMINISTRATIVA.getFlag())){
			somarValorCustosAdministrativo();
		}
		
		calcularValorTotalComImpostos();
		tipoItemValor = new TipoItemValor();
		validaMsg = false;
		salvarPrecificacao();
		editProposta();
	}
	
	// soma valor total de execução
	public void somarValorExecucao() {
		double totalPorItem = 0;
		if(tipoItemValor.getTipoDespesa().equals(ETipoDespesa.CUSTO_EXECUCAO.getFlag())){
			if(tipoItemValor != null){
				custoExecucao = new CustoExecucao();
				custoExecucao.setDescricao(tipoItemValor.getDescricao());
				custoExecucao.setValorUnitario(tipoItemValor.getValorUnitario());
				custoExecucao.setQuantidade(tipoItemValor.getQuantidade());
				custoExecucao.setObservacoes(tipoItemValor.getObservacao());
				custoExecucao.setValorTotal(tipoItemValor.getQuantidade()*tipoItemValor.getValorUnitario());
				custoExecucao.setProposta(this.proposta);
				custoExecucao.setTpUnidadeMedida(tipoItemValor.getTpUnidadeMedida());
				custoExecucao.setAtivo(true);
				listExecucaoTemp.add(custoExecucao);
			}
			if(proposta.getCustos()== null){
				proposta.setCustos(new HashSet<CustoExecucao>()); 
			}
			Set<CustoExecucao> list = new HashSet<CustoExecucao>(custoExecucaoService.getByPropostaId(proposta.getId()));
			proposta.setCustos(list);
		}
		
			if (custoExecucao.getQuantidade() > 0) {
				totalPorItem += custoExecucao.getValorUnitario() * custoExecucao.getQuantidade();
			}
		this.custoExecucao.setValorTotal(totalPorItem);
		calcularValorTotalCustosExecucao();
		custoExecucao = new CustoExecucao();
	}
	
	// Soma valor total deslocamento
	public void somarValorCustosDeslocamento() {
		double totalPorItem = 0;
		if(tipoItemValor.getTipoDespesa().equals(ETipoDespesa.CUSTO_DESLOCAMENTO.getFlag())){
			if(tipoItemValor != null){
				custoDeslocamento = new CustoDeslocamento();
				custoDeslocamento.setDescricao(tipoItemValor.getDescricao());
				custoDeslocamento.setValorUnitario(tipoItemValor.getValorUnitario());
				custoDeslocamento.setQuantidade(tipoItemValor.getQuantidade());
				custoDeslocamento.setObservacoes(tipoItemValor.getObservacao());
				custoDeslocamento.setProposta(this.proposta);
				custoDeslocamento.setAtivo(true);
				custoDeslocamento.setValorTotal(tipoItemValor.getQuantidade()*tipoItemValor.getValorUnitario());
				custoDeslocamento.setTpUnidadeMedida(tipoItemValor.getTpUnidadeMedida());
				listDeslocamentoTemp.add(custoDeslocamento);
			}
			if(proposta.getDespesasDeslocamentos()== null){
				proposta.setDespesasDeslocamentos(new HashSet<CustoDeslocamento>()); 
			}
			Set<CustoDeslocamento> list = new HashSet<CustoDeslocamento>(custoDeslocamentoService.getByPropostaId(proposta.getId()));
			proposta.setDespesasDeslocamentos(list);
		}
		
		if (this.custoDeslocamento.getQuantidade() > 0) {
			totalPorItem += this.custoDeslocamento.getValorUnitario() * this.custoDeslocamento.getQuantidade();
		}
		this.custoDeslocamento.setValorTotal(totalPorItem);
		calcularValorTotalCustosDeslocamento();
		custoDeslocamento = new CustoDeslocamento();
	}

	//Soma valor total Operacionais
	public void somarValorCustosOperacionais() {
		double totalPorItem = 0;
		if(tipoItemValor.getTipoDespesa().equals(ETipoDespesa.CUSTO_DESPESA_OPERACIONAL.getFlag())){
			if(tipoItemValor != null){
				custoOperacional = new CustoOperacional();
				custoOperacional.setDescricao(tipoItemValor.getDescricao());
				custoOperacional.setValorUnitario(tipoItemValor.getValorUnitario());
				custoOperacional.setQuantidade(tipoItemValor.getQuantidade());
				custoOperacional.setObservacoes(tipoItemValor.getObservacao());
				custoOperacional.setProposta(this.proposta);
				custoOperacional.setTpUnidadeMedida(tipoItemValor.getTpUnidadeMedida());
				custoOperacional.setValorTotal(tipoItemValor.getQuantidade()*tipoItemValor.getValorUnitario());
				custoOperacional.setAtivo(true);
				custoOperacional.setTpUnidadeMedida(tipoItemValor.getTpUnidadeMedida());
				listOperacionalTemp.add(custoOperacional);
			}
			if(proposta.getDespesasOperacionais()== null){
				proposta.setDespesasOperacionais(new HashSet<CustoOperacional>()); 
			}
			Set<CustoOperacional> list = new HashSet<CustoOperacional>(custoOperacionalService.getByPropostaId(proposta.getId()));
			proposta.setDespesasOperacionais(list);
		}

		if (this.custoOperacional.getQuantidade() > 0) {
			totalPorItem += this.custoOperacional.getValorUnitario() * this.custoOperacional.getQuantidade();
		}
		this.custoOperacional.setValorTotal(totalPorItem);
		calcularValorTotalCustosOperacionais();
		custoOperacional = new CustoOperacional();
	}

	//Soma Custos com Seguranca
	public void somarValorCustosSeguranca() {
		double totalPorItem = 0;
		if(tipoItemValor.getTipoDespesa().equals(ETipoDespesa.CUSTO_DESPESA_SEGURANCA.getFlag())){
			if(tipoItemValor != null){
				custoSeguranca = new CustoSeguranca();
				custoSeguranca.setDescricao(tipoItemValor.getDescricao());
				custoSeguranca.setValorUnitario(tipoItemValor.getValorUnitario());
				custoSeguranca.setQuantidade(tipoItemValor.getQuantidade());
				custoSeguranca.setObservacoes(tipoItemValor.getObservacao());
				custoSeguranca.setProposta(this.proposta);
				custoSeguranca.setTpUnidadeMedida(tipoItemValor.getTpUnidadeMedida());
				custoSeguranca.setValorTotal(tipoItemValor.getQuantidade()*tipoItemValor.getValorUnitario());
				custoSeguranca.setAtivo(true);
				custoSeguranca.setTpUnidadeMedida(tipoItemValor.getTpUnidadeMedida());
				listSegurancaTemp.add(custoSeguranca);
			}
			
			if(proposta.getDespesasSeguranca()== null){
				proposta.setDespesasSeguranca(new HashSet<CustoSeguranca>()); 
			}
			Set<CustoSeguranca> list = new HashSet<CustoSeguranca>(custoSegurancaService.getByPropostaId(proposta.getId()));
			proposta.setDespesasSeguranca(list);
		}

		if (this.custoSeguranca.getQuantidade() > 0) {
			totalPorItem += this.custoSeguranca.getValorUnitario() * this.custoSeguranca.getQuantidade();
		}
		this.custoSeguranca.setValorTotal(totalPorItem);
		calcularValorTotalCustosSeguranca();
		custoSeguranca = new CustoSeguranca();
	}

	
	//Soma valor total administrativo
	public void somarValorCustosAdministrativo() {
		double totalPorItem = 0;
		if(tipoItemValor.getTipoDespesa().equals(ETipoDespesa.CUSTO_DESPESA_ADMINISTRATIVA.getFlag())){
			if(tipoItemValor != null){
				custoAdministrativo = new CustoAdministrativo();
				custoAdministrativo.setDescricao(tipoItemValor.getDescricao());
				custoAdministrativo.setValorUnitario(tipoItemValor.getValorUnitario());
				custoAdministrativo.setQuantidade(tipoItemValor.getQuantidade());
				custoAdministrativo.setObservacoes(tipoItemValor.getObservacao());
				custoAdministrativo.setProposta(this.proposta);
				custoAdministrativo.setTpUnidadeMedida(tipoItemValor.getTpUnidadeMedida());
				custoAdministrativo.setValorTotal(tipoItemValor.getQuantidade()*tipoItemValor.getValorUnitario());
				custoAdministrativo.setAtivo(true);
				custoAdministrativo.setTpUnidadeMedida(tipoItemValor.getTpUnidadeMedida());
				listAdministrativoTemp.add(custoAdministrativo);
			}
			
			if(proposta.getDespesasAdministrativas()== null){
				proposta.setDespesasAdministrativas(new HashSet<CustoAdministrativo>()); 
			}
			
			Set<CustoAdministrativo> list = new HashSet<CustoAdministrativo>(custoAdministrativoService.getByPropostaId(proposta.getId()));
			proposta.setDespesasAdministrativas(list);
		}

		if (this.custoAdministrativo.getQuantidade() > 0) {
			totalPorItem += this.custoAdministrativo.getValorUnitario() * this.custoAdministrativo.getQuantidade();
		}
		
		this.custoAdministrativo.setValorTotal(totalPorItem);
		calcularValorTotalCustosAdministrativos();
		custoAdministrativo = new CustoAdministrativo();
	}
	
	public void calcularValorTotalCustosBdiComissao() {
		this.proposta.setValorTotalCustosBdiComissoes(this.proposta.getCalculoValorTotalCustosBdiComissao());
		calcularValorTotalAposBdiComissao();
	}

	private void calcularValorTotalAposBdiComissao() {
		double totalComBdiComissao = 0;
		if (this.proposta.getPercentualBDI() != null && this.proposta.getPercentualBDI() > 0 && proposta.getValorTotalDaProposta() != null) {
			totalComBdiComissao = proposta.getValorTotalSemBdiComissao() * proposta.getPercentualBDI()/100;
		}else{
			if(proposta.getValorTotalSemBdiComissao() != null){
				totalComBdiComissao = proposta.getValorTotalSemBdiComissao();
			}
		}
		calcularBDIComissao();
		calcularValorTotalComImpostos();
	}
	
	//Calcular valor total Execução
	public void calcularValorTotalCustosExecucao() {
		this.proposta.setValorTotalCustosExecucao(calculoValorTotalCustosExecucao());
		calcularValorTotalCustosSemBdi();
	}
	
	// Realiza a soma de todos os custos de execução
	public double calculoValorTotalCustosExecucao() {
		double totalCustoExecucao = 0;
		if (listExecucaoTemp != null && !listExecucaoTemp.isEmpty()) {
			for (CustoExecucao list : listExecucaoTemp) {
				totalCustoExecucao += list.getValorTotal();
			}
		}
		return totalCustoExecucao;
	}
	
	//Calcular valor total Deslocamento
	public void calcularValorTotalCustosDeslocamento() {
		this.proposta.setValorTotalCustosDesclocamento(calculoValorTotalCustosDeslocamento());
		calcularValorTotalCustosSemBdi();
	}
	
	// Realiza a soma de todos os custos de Deslocamento
	public double calculoValorTotalCustosDeslocamento() {
		double totalCustosDeslocamento = 0;
		if (listDeslocamentoTemp  != null && !listDeslocamentoTemp.isEmpty()) {
			for (CustoDeslocamento list : listDeslocamentoTemp ) {
				totalCustosDeslocamento += list.getValorTotal();
			}
		}
		return totalCustosDeslocamento;
	}
	
	//Calcular valor total Operacional
	public void calcularValorTotalCustosOperacionais() {
		this.proposta.setValorTotalCustosOperacionais(calculoValorTotalCustosOperacional());
		calcularValorTotalCustosSemBdi();
	}
	
	// Realiza a soma de todos os custo Operacional
	public double calculoValorTotalCustosOperacional() {
		double totalCustosOperacional = 0;
		if (listOperacionalTemp  != null && !listOperacionalTemp.isEmpty()) {
			for (CustoOperacional list : listOperacionalTemp ) {
				totalCustosOperacional += list.getValorTotal();
			}
		}
		return totalCustosOperacional;
	}
	
	//Calcular valor total Segurança
	public void calcularValorTotalCustosSeguranca() {
		this.proposta.setValorTotalCustosSeguranca(calculoValorTotalCustosSeguranca());
		calcularValorTotalCustosSemBdi();
	}
	
	// Realiza a soma de todos os custo Segurança
	public double calculoValorTotalCustosSeguranca() {
		double totalCustosSeguranca = 0;
		if (listSegurancaTemp   != null && !listSegurancaTemp.isEmpty()) {
			for (CustoSeguranca list : listSegurancaTemp  ) {
				totalCustosSeguranca += list.getValorTotal();
			}
		}
		return totalCustosSeguranca;
	}

	//Calcular valor total Administrativo
	public void calcularValorTotalCustosAdministrativos() {
		this.proposta.setValorTotalCustosAdministrativos(calculoValorTotalCustosAdministrativos());
		calcularValorTotalCustosSemBdi();
	}
	
	// Realiza a soma de todos os custo Administrativo
	public double calculoValorTotalCustosAdministrativos() {
		double totalCustosAdministrativos = 0;
		if (listAdministrativoTemp != null && !listAdministrativoTemp.isEmpty()) {
			for (CustoAdministrativo list : listAdministrativoTemp   ) {
				totalCustosAdministrativos += list.getValorTotal();
			}
		}
		return totalCustosAdministrativos;
	}
	
	//Calcular valor total Planilha Financeira
	public void calcularValorTotalCustoPlanilhaFinanceira() {
		this.proposta.setValorTotalCustoPlanilhaFinanceira(calculoValorTotalCustoPlanilhaFinanceira());
		calcularValorTotalCustosSemBdi();
	}
	
	// Realiza a soma de todos os custo Planilha Financeira
	public Double calculoValorTotalCustoPlanilhaFinanceira() {
		double totalCustosPlanFinanceira = 0;
		if (listCustoPlanFinanceiraTemp != null && !listCustoPlanFinanceiraTemp.isEmpty()) {
			for (CustoPlanilhaFinanceira item : listCustoPlanFinanceiraTemp) {
				totalCustosPlanFinanceira += item.getValorTotal();
			}
		}
		return totalCustosPlanFinanceira;
	}
	
	//** fim do bloco de calculos**
	
	public void editarItensProposta(TipoItemProposta tipoItemProposta, Long tipo ){
		this.tipoItemProposta = new TipoItemProposta();
		if(tipo.equals(1L)){
			tituloTipoItemFinanceiro = tipoItemProposta.getTitulo();
			descricaoTipoItemFinanceiro = tipoItemProposta.getDescricao();
			setLabelBotaoAdicionarItemProposta("Atualizar");
		}else if (tipo.equals(2L)){
			setLabelBotaoAdicionarItemPropostaTec("Atualizar");
			tituloTipoItem = tipoItemProposta.getTitulo();
			descricaoTipoItem = tipoItemProposta.getDescricao();
		}else if (tipo.equals(3L)){
			setLabelBotaoAdicionarItemPropostaTec("Atualizar");
			tituloTipoItemTecFinan = tipoItemProposta.getTitulo();
			descricaoTipoItemTecFinan = tipoItemProposta.getDescricao();
		}
		this.tipoItemProposta = tipoItemProposta;
		if(this.tipoItemProposta != null){
			excluirItemProposta = true;
		}
	}
	
	public void preExcluirItemProposta(TipoItemProposta tipoItemProposta){
		this.tipoItemProposta = new TipoItemProposta();
		this.tipoItemProposta = tipoItemProposta;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluirItem').show();");
	}
	
	//Exclui itens da Proposta (Técnica-Comercial ou Técnica e financeira)
	public void excluirTipoItemProposta(){
		if(this.tipoItemProposta != null && this.tipoItemProposta.getId() != null){
			tipoItemPropostaService.remover(this.tipoItemProposta);
			
			if(proposta != null && proposta.getId() !=null){
				tipoItensPropostaFinanceira = tipoItemPropostaService.getByPropostaId(proposta.getId(),ETipoProposta.FINANCEIRA.getFlag());
				tipoItensPropostaTecnica = tipoItemPropostaService.getByPropostaId(this.proposta.getId(), ETipoProposta.TECNICA.getFlag());
				tipoItensPropostaTecnicaFinanceira = tipoItemPropostaService.getByPropostaId(this.proposta.getId(), ETipoProposta.TECN_FINANCE.getFlag());
				proposta.setListarTipoItemPropostaFinanceira(tipoItensPropostaFinanceira);
				proposta.setListarTipoItemPropostaTecnica(tipoItensPropostaTecnica);
				proposta.setListarTipoItemPropostaTecFinanceira(tipoItensPropostaTecnicaFinanceira);
			}else{
				tipoItensPropostaFinanceira = new ArrayList<TipoItemProposta>();
				tipoItensPropostaTecnica = new ArrayList<TipoItemProposta>();
				tipoItensPropostaTecnicaFinanceira = new ArrayList<TipoItemProposta>();
			}
			
			if(excluirItemProposta){
				setLabelBotaoAdicionarItemProposta("Adicionar");
				setLabelBotaoAdicionarItemPropostaTec("Adicionar");
				FacesUtil.addInfoMessage("Item Atualizado com sucesso.");
			}else{
				FacesUtil.addInfoMessage("Item excluído com sucesso.");
			}
			
		}
	} 
	
 	//Inicio Bloco de Exclusão de despesas
 	//Pré exclusão Despesa Execução
 	public void preExcluirDespesaExcecucao(CustoExecucao custoExecucao, Long valor){
		this.custoExecucao = custoExecucao;
		this.tipoExclusaoDespesa = valor;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluirDespesa').show();");
	}

 	
 	//Exclusão Despesa Execução
 	public void excluirCustoExecucao(){
		
		listExecucaoTemp.remove(custoExecucao);
		custoExecucaoService.excluir(custoExecucao);
		calcularValorTotalCustosExecucao();
		
		if(!btAtivo){
			FacesUtil.addInfoMessage("Despesa de execução excluída com sucesso.");
		}
		validaMsg = false;
	}
 	
	public void editarCustoExecucao(CustoExecucao custoExecucao){
		tipoItemValor = new TipoItemValor();
		tipoItemValor.setObservacao(custoExecucao.getObservacoes());
		tipoItemValor.setValorUnitario(custoExecucao.getValorUnitario());
		tipoItemValor.setQuantidade(custoExecucao.getQuantidade());
		tipoItemValor.setTipoDespesa(ETipoDespesa.CUSTO_EXECUCAO.getFlag());
		tipoItemValor.setTpUnidadeMedida(custoExecucao.getTpUnidadeMedida());
		
		this.custoExecucao = custoExecucao;
		
		if(this.custoExecucao != null){
			excluirAtualizada = true;
			labelBotaoAdicionar = "Atualizar";
			btAtivo = true;
			tipoAtualizacao = ETipoDespesa.CUSTO_EXECUCAO.getDescricao();
		}
	}
	
	
	//Pré exclusão Despesa Deslocamento
 	public void preExcluirDespesaDeslocamento(CustoDeslocamento custoDeslocamento, Long valor){
		this.custoDeslocamento = custoDeslocamento;
		this.tipoExclusaoDespesa = valor;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluirDespesa').show();");
	}
	
 	//Exclusão Despesa Deslocamento
 	public void excluirCustoDeslocamento(){
		
		listDeslocamentoTemp.remove(custoDeslocamento);
		custoDeslocamentoService.excluir(custoDeslocamento);
		calcularValorTotalCustosDeslocamento();
		
		if(!btAtivo){
			FacesUtil.addInfoMessage("Despesa de deslocamento excluído com sucesso.");
		}
		validaMsg = false;
	}
 	
 	public void editarPlanilhaFinan(CustoPlanilhaFinanceira custoPlanilha){
 		custoPlanilhaFinanceira = new CustoPlanilhaFinanceira();
 		custoPlanilhaFinanceira.setDescricao(custoPlanilha.getDescricao());
 		custoPlanilhaFinanceira.setQuantidade(custoPlanilha.getQuantidade());
 		custoPlanilhaFinanceira.setValorUnitario(custoPlanilha.getValorUnitario());
 		custoPlanilhaFinanceira.setValorTotal(custoPlanilha.getValorTotal());
 		custoPlanilhaFinanceiraExcluir = new CustoPlanilhaFinanceira();
 		this.custoPlanilhaFinanceiraExcluir = custoPlanilha;
		this.tipoExclusaoDespesa = 6L;
		btAtivo = true;
	}
 	
 	//Editar Equipe Tecnica
 	public void editarEquipeTecnica(CustoPlanilhaTecnica equipe){
 		custoPlanilhaEquipeTecnica = new CustoPlanilhaTecnica();
 		custoPlanilhaEquipeTecnica.setDescricao(equipe.getDescricao());
 		custoPlanilhaEquipeTecnica.setQuantitativo(equipe.getQuantitativo());
 		custoPlanilhaEquipeTecnica.setUnidade(equipe.getUnidade());
 		custoPlanilhaEquipeTecnicaExcluir = new CustoPlanilhaTecnica();
 		this.custoPlanilhaEquipeTecnicaExcluir = equipe;
		this.tipoExclusaoDespesa = 7L;
		btAtivo = true;
	}
 	
	//Editar Equipe Tecnica
 	public void editarEquipamento(PlanilhaTecEquipamento equipamento){
 		planilhaTecEquipamento = new PlanilhaTecEquipamento();
 		planilhaTecEquipamento.setDescricao(equipamento.getDescricao());
 		planilhaTecEquipamento.setEquipeAdm(equipamento.getEquipeAdm());
 		planilhaTecEquipamento.setEquipeCampo(equipamento.getEquipeCampo());
 		planilhaTecEquipamento.setItem(equipamento.getItem());
 		planilhaTecEquipamentoExcluir = new PlanilhaTecEquipamento();
 		this.planilhaTecEquipamentoExcluir = equipamento;
		this.tipoExclusaoDespesa = 8L;
		btAtivo = true;
	}
 	
 	public void editarCustoDeslocamento(CustoDeslocamento custoDeslocamento){
		tipoItemValor = new TipoItemValor();
		tipoItemValor.setObservacao(custoDeslocamento.getObservacoes());
		tipoItemValor.setValorUnitario(custoDeslocamento.getValorUnitario());
		tipoItemValor.setQuantidade(custoDeslocamento.getQuantidade());
		tipoItemValor.setTipoDespesa(ETipoDespesa.CUSTO_DESLOCAMENTO.getFlag());
		tipoItemValor.setTpUnidadeMedida(custoDeslocamento.getTpUnidadeMedida());
		this.custoDeslocamento = custoDeslocamento;
		
		if(this.custoDeslocamento != null){
			excluirAtualizada = true;
			labelBotaoAdicionar = "Atualizar";
			btAtivo = true;
			tipoAtualizacao = ETipoDespesa.CUSTO_DESLOCAMENTO.getDescricao();
		}
	}
 	
 	//Pré exclusão Despesa Operacional
 	public void preExcluirDespesaOperacional(CustoOperacional custoOperacional, Long valor){
		this.custoOperacional = custoOperacional;
		this.tipoExclusaoDespesa = valor;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluirDespesa').show();");
	}
 	
	//Exclusão Despesa Operacional
 	public void excluirCustoOperacional(){
		
		listOperacionalTemp.remove(custoOperacional);
		custoOperacionalService.excluir(custoOperacional);
		calcularValorTotalCustosOperacionais();
		
		if(!btAtivo){
			FacesUtil.addInfoMessage("Despesa operacional excluído com sucesso.");
		}
		validaMsg = false;
	}
 	
 	public void editarCustoOperacional(CustoOperacional custoOperacional){
		tipoItemValor = new TipoItemValor();
		tipoItemValor.setObservacao(custoOperacional.getObservacoes());
		tipoItemValor.setValorUnitario(custoOperacional.getValorUnitario());
		tipoItemValor.setQuantidade(custoOperacional.getQuantidade());
		tipoItemValor.setTipoDespesa(ETipoDespesa.CUSTO_DESPESA_OPERACIONAL.getFlag());
		tipoItemValor.setTpUnidadeMedida(custoOperacional.getTpUnidadeMedida());
		this.custoOperacional = custoOperacional;
		
		if(this.custoOperacional != null){
			excluirAtualizada = true;
			labelBotaoAdicionar = "Atualizar";
			btAtivo = true;
			tipoAtualizacao = ETipoDespesa.CUSTO_DESPESA_OPERACIONAL.getDescricao();
		}
	}
 	
 	//Pré exclusão Despesa Segurança
 	public void preExcluirDespesaSeguranca(CustoSeguranca custoSeguranca, Long valor){
		this.custoSeguranca = custoSeguranca;
		this.tipoExclusaoDespesa = valor;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluirDespesa').show();");
	}
 	
	//Exclusão Despesa Segurança
 	public void excluirCustoSeguranca(){
		
		listSegurancaTemp.remove(custoSeguranca);
		custoSegurancaService.excluir(custoSeguranca);
		calcularValorTotalCustosSeguranca();

		if(!btAtivo){
			FacesUtil.addInfoMessage("Depesa com Segurança excluído com sucesso.");
		}
		validaMsg = false;
	}
 	
 	public void editarCustoSeguranca(CustoSeguranca custoSeguranca){
		tipoItemValor = new TipoItemValor();
		tipoItemValor.setObservacao(custoSeguranca.getObservacoes());
		tipoItemValor.setValorUnitario(custoSeguranca.getValorUnitario());
		tipoItemValor.setQuantidade(custoSeguranca.getQuantidade());
		tipoItemValor.setTipoDespesa(ETipoDespesa.CUSTO_DESPESA_SEGURANCA.getFlag());
		tipoItemValor.setTpUnidadeMedida(custoSeguranca.getTpUnidadeMedida());
		this.custoSeguranca = custoSeguranca;
		
		if(this.custoSeguranca != null){
			excluirAtualizada = true;
			labelBotaoAdicionar = "Atualizar";
			btAtivo = true;
			tipoAtualizacao = ETipoDespesa.CUSTO_DESPESA_SEGURANCA.getDescricao();
		}
	}
	
 	//Pré exclusão Despesa Administrativo
	public void preExcluirDespesaAdministrativa(CustoAdministrativo custoAdministrativo, Long valor){
		this.custoAdministrativo = custoAdministrativo;
		this.tipoExclusaoDespesa = valor;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluirDespesa').show();");
	}
	
	//Exclusão Despesa Administrativo
	public void excluirCustoAdministrativo(){
		
		listAdministrativoTemp.remove(custoAdministrativo);
		custoAdministrativoService.excluir(custoAdministrativo);
		calcularValorTotalCustosAdministrativos();
		
		if(!btAtivo){
			FacesUtil.addInfoMessage("Despesa administrativa excluído com sucesso.");
		}
		validaMsg = false;
	}
	
	public void editarCustoAdministrativo(CustoAdministrativo custoAdministrativo){
		tipoItemValor = new TipoItemValor();
		tipoItemValor.setObservacao(custoAdministrativo.getObservacoes());
		tipoItemValor.setValorUnitario(custoAdministrativo.getValorUnitario());
		tipoItemValor.setQuantidade(custoAdministrativo.getQuantidade());
		tipoItemValor.setTipoDespesa(ETipoDespesa.CUSTO_DESPESA_ADMINISTRATIVA.getFlag());
		tipoItemValor.setTpUnidadeMedida(custoAdministrativo.getTpUnidadeMedida());
		this.custoAdministrativo = custoAdministrativo;
		
		if(this.custoAdministrativo != null){
			excluirAtualizada = true;
			labelBotaoAdicionar = "Atualizar";
			btAtivo = true;
			tipoAtualizacao = ETipoDespesa.CUSTO_DESPESA_ADMINISTRATIVA.getDescricao();
		}
	}

	//Pré exclusão Despesa da Planilha Financeira
	public void preExcluirPlanFinanceira(CustoPlanilhaFinanceira custoPlanFinanceira,  Long valor){
		this.custoPlanilhaFinanceira = custoPlanFinanceira;
		this.tipoExclusaoDespesa = valor;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluirDespesa').show();");
	}
	
	//pre excluir Planilha técnica
	public void preExcluirPlanTecnica(CustoPlanilhaTecnica custoPlanTecnica,  Long valor){
		this.custoPlanilhaEquipeTecnica = custoPlanTecnica;
		this.tipoExclusaoDespesa = valor;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluirItemTec').show();");
	}
	
	//pre excluir Planilha técnica
	public void preExcluirPlanEquipamento(PlanilhaTecEquipamento planilhaTecEquipamento,  Long valor){
		this.planilhaTecEquipamento = planilhaTecEquipamento;
		this.tipoExclusaoDespesa = valor;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogExcluirEquipamento').show();");
	}
	
	//Exclusão Despesa da Planilha Financeira
	public void excluirCustoPlanFinanceira(){
		
		if(custoPlanilhaFinanceiraExcluir != null && custoPlanilhaFinanceiraExcluir.getId() != null){
			custoPlanilhaFinanceiraService.remover(custoPlanilhaFinanceiraExcluir);
			custoPlanilhaFinanceiraExcluir = new CustoPlanilhaFinanceira();
		}else{
			custoPlanilhaFinanceiraService.remover(custoPlanilhaFinanceira);
		}
		calcularValorTotalCustoPlanilhaFinanceira();
		proposta.setListarCustoPlanilhaFinanceira(listCustoPlanFinanceiraTemp);
		
		carregaPlanilhaFinanceira();
		custoPlanilhaFinanceira = new CustoPlanilhaFinanceira();
		if(!btAtivo){
			FacesUtil.addInfoMessage("Despesa da Planilha Financeira excluído com sucesso.");
		}
	}
	
	//Exclusão Despesa da Planilha Financeira
	public void excluirEquipeTecnica(){
	
		if(custoPlanilhaEquipeTecnicaExcluir != null && custoPlanilhaEquipeTecnicaExcluir.getId() != null){
			custoPlanilhaTecnicaService.remover(custoPlanilhaEquipeTecnicaExcluir);
			custoPlanilhaEquipeTecnicaExcluir = new CustoPlanilhaTecnica();
		}else{
			custoPlanilhaTecnicaService.remover(custoPlanilhaEquipeTecnica);
		}
		proposta.setListarCustoEquipeTecnica(listCustoEquipeTecnica);

		carregaEquipeTecnica();
		custoPlanilhaEquipeTecnica = new CustoPlanilhaTecnica();
		if(!btAtivo){
			FacesUtil.addInfoMessage("Item da planilha excluído com sucesso.");
		}
	}
	
	//Exclusão Despesa da Planilha Planilha equipamento
	public void excluirPlanEquipamento(){
		if(planilhaTecEquipamentoExcluir != null && planilhaTecEquipamentoExcluir.getId() != null){
			planilhaTecEquipamentoService.remover(planilhaTecEquipamentoExcluir);
			planilhaTecEquipamentoExcluir = new PlanilhaTecEquipamento();
		}else{
			planilhaTecEquipamentoService.remover(planilhaTecEquipamento);
		}
		proposta.setListarPlanilhaEquipamento(listPlanilhaTecEquipamento);

		carregaPlanEquipamento();
		planilhaTecEquipamento = new PlanilhaTecEquipamento();
		if(!btAtivo){
			FacesUtil.addInfoMessage("Item da planilha excluído com sucesso.");
		}
	}
	
	//Verifica qual tipo foi selecionado para excluir	
	public void excluirDespesas(){
		if(tipoExclusaoDespesa.equals(1L)){
			excluirCustoExecucao();
		}else if (tipoExclusaoDespesa.equals(2L)){
			excluirCustoDeslocamento();
		}else if (tipoExclusaoDespesa.equals(3L)){
			excluirCustoOperacional();
		}else if (tipoExclusaoDespesa.equals(4L)){
			excluirCustoSeguranca();
		}else if (tipoExclusaoDespesa.equals(5L)){
			excluirCustoAdministrativo();
		}else if (tipoExclusaoDespesa.equals(6L)){
			excluirCustoPlanFinanceira();
		}else if (tipoExclusaoDespesa.equals(7L)){
			excluirEquipeTecnica();
		}else if (tipoExclusaoDespesa.equals(8L)){
			excluirPlanEquipamento();
		}
	}
	
	//Fim bloco de Exclusão de despesas
	
	//Calcula o valor dos custos sem bdi
	private void calcularValorTotalCustosSemBdi() {
		double totalSemBdi = 0;
		
		totalSemBdi += calculoValorTotalCustosExecucao()
				+ calculoValorTotalCustosDeslocamento()
				+ calculoValorTotalCustosOperacional()
				+ calculoValorTotalCustosSeguranca()
				+ calculoValorTotalCustosAdministrativos();
		
		this.proposta.setValorTotalSemBdiComissao(totalSemBdi);
		calcularValorTotalAposBdiComissao();
		calcularValorTotalComImpostos();
	}
	
	//Calcula os valores de impostos
	private void calcularValorTotalComImpostos() {
		double totalComImpostos = 0;
		double taxaImposto = 0;
		if (this.proposta.getValorTotalComBdiComissao() > 0) {
			taxaImposto += this.proposta.getValorTotalComBdiComissao() / (1 - this.proposta.getImpostos()) - this.proposta.getValorTotalComBdiComissao();
			totalComImpostos += taxaImposto + this.proposta.getValorTotalComBdiComissao();
		} else {
			taxaImposto += this.proposta.getValorTotalSemBdiComissao() / (1 - this.proposta.getImpostos())- this.proposta.getValorTotalSemBdiComissao();
			totalComImpostos += taxaImposto + this.proposta.getValorTotalSemBdiComissao();
		}
		this.proposta.setValorTotalImpostos(totalComImpostos);
		this.proposta.setValorTotalDaProposta(this.proposta.getValorTotalSemBdiComissao());
	}

	public void salvarItens(Long valor){
		
		if(valor.equals(1L)){
			if("".equals(tituloTipoItemFinanceiro) ){
				FacesUtil.addErrorMessage("Por favor, preencha a título do item.");
				return;	
			}else if("".equals(descricaoTipoItemFinanceiro)){
				FacesUtil.addErrorMessage("Por favor, preencha a descrição do item.");
				return;	
			}else{
				if(this.tipoItemProposta != null && this.tipoItemProposta.getId() != null){
					this.tipoItemProposta.setTitulo(tituloTipoItemFinanceiro);
					this.tipoItemProposta.setDescricao(descricaoTipoItemFinanceiro);
				}else{
					this.tipoItemProposta = new TipoItemProposta();
					this.tipoItemProposta.setProposta(proposta);
					this.tipoItemProposta.setTitulo(tituloTipoItemFinanceiro);
					this.tipoItemProposta.setDescricao(descricaoTipoItemFinanceiro);
					this.tipoItemProposta.setAtivo(true);
					this.tipoItemProposta.setTipoProposta(1L);
				}
			}
		}else if(valor.equals(3L)){
			if("".equals(tituloTipoItemTecFinan) ){
				FacesUtil.addErrorMessage("Por favor, preencha a título do item.");
				return;	
			}else if("".equals(descricaoTipoItemTecFinan)){
				FacesUtil.addErrorMessage("Por favor, preencha a descrição do item.");
				return;	
			}else{
				if(this.tipoItemProposta != null && this.tipoItemProposta.getId() != null){
					this.tipoItemProposta.setTitulo(tituloTipoItemTecFinan);
					this.tipoItemProposta.setDescricao(descricaoTipoItemTecFinan);
				}else{
					this.tipoItemProposta = new TipoItemProposta();
					this.tipoItemProposta.setProposta(proposta);
					this.tipoItemProposta.setTitulo(tituloTipoItemTecFinan);
					this.tipoItemProposta.setDescricao(descricaoTipoItemTecFinan);
					this.tipoItemProposta.setAtivo(true);
					this.tipoItemProposta.setTipoProposta(3L);
				}
			}
		}else{
			if("".equals(tituloTipoItem)){
				FacesUtil.addErrorMessage("Por favor, preencha a título do item.");
				return;	
			}else if("".equals(descricaoTipoItem)){
				FacesUtil.addErrorMessage("Por favor, preencha a descrição do item.");
				return;	
			}else{
				if(this.tipoItemProposta != null && this.tipoItemProposta.getId() != null){
					this.tipoItemProposta.setTitulo(tituloTipoItem);
					this.tipoItemProposta.setDescricao(descricaoTipoItem);
				}else{
					this.tipoItemProposta = new TipoItemProposta();
					this.tipoItemProposta.setProposta(proposta);
					this.tipoItemProposta.setTitulo(tituloTipoItem);
					this.tipoItemProposta.setDescricao(descricaoTipoItem);
					this.tipoItemProposta.setAtivo(true);
					this.tipoItemProposta.setTipoProposta(2L);
				}
			}
		}
		
		this.tipoItemProposta = tipoItemPropostaService.salvar(this.tipoItemProposta);
		
		if(proposta != null && proposta.getId() != null){
			tipoItensPropostaFinanceira = tipoItemPropostaService.getByPropostaId(proposta.getId(), ETipoProposta.FINANCEIRA.getFlag());
			tipoItensPropostaTecnica = tipoItemPropostaService.getByPropostaId(this.proposta.getId(), ETipoProposta.TECNICA.getFlag());
			tipoItensPropostaTecnicaFinanceira = tipoItemPropostaService.getByPropostaId(this.proposta.getId(), ETipoProposta.TECN_FINANCE.getFlag());
			proposta.setListarTipoItemPropostaFinanceira(tipoItensPropostaFinanceira);
			proposta.setListarTipoItemPropostaTecnica(tipoItensPropostaTecnica);
			proposta.setListarTipoItemPropostaTecFinanceira(tipoItensPropostaTecnicaFinanceira);
		}else{
			tipoItensPropostaFinanceira = new ArrayList<TipoItemProposta>();
			tipoItensPropostaTecnica = new ArrayList<TipoItemProposta>();
			tipoItensPropostaTecnicaFinanceira = new ArrayList<TipoItemProposta>();
		}
		FacesUtil.addInfoMessage("Item salvo com sucesso.");
		
		this.tipoItemProposta = new TipoItemProposta();
		descricaoTipoItemFinanceiro = null;
		descricaoTipoItem = null;
		tituloTipoItem = null;
		tituloTipoItemFinanceiro = null;
		tituloTipoItemTecFinan = null;
		descricaoTipoItemTecFinan = null;
	}
	
	public void habilitaDesabilitaIrPara(){
		habilitaIrPara = true;
	}
	
	public void irParaProposta(){
		visualizar = false;
		editar = false;
		pesquisar = false;
		novaPrecificacao= false;
		novaProposta = true;
		voltar = true;
		propostas = new ArrayList<>();
		propostas = this.propostaService.getPropostaByFilter(this.filtroNumeroProposta, this.filtroIdCliente,
					null, this.filtroDataInicial, this.filtroDataFinal);
	}
	
	//Ir para Proposta
	public void irProposta(){
		
		//Carrega a lista planilha Financeira
		listCustoPlanFinanceiraTemp = new ArrayList<CustoPlanilhaFinanceira>();
		listCustoEquipeTecnica =  new ArrayList<>();
		listPlanilhaTecEquipamento =  new ArrayList<>();
		
		//verificar esse ponto
		listarCustoPlanilhaFinanceira = custoPlanilhaFinanceiraService.getByPropostaId(proposta.getId());
		proposta.setListarCustoPlanilhaFinanceira(listarCustoPlanilhaFinanceira);
		listCustoPlanFinanceiraTemp.addAll(listarCustoPlanilhaFinanceira);
		proposta.setValorTotalCustoPlanilhaFinanceira(calculoValorTotalCustoPlanilhaFinanceira());
		
		//Equipe técnica
		listCustoEquipeTecnica = custoPlanilhaTecnicaService.getByPropostaId(proposta.getId());
		proposta.setListarCustoEquipeTecnica(listCustoEquipeTecnica);
		
		//Plan Equipamentos
		listPlanilhaTecEquipamento = planilhaTecEquipamentoService.getByPropostaId(proposta.getId());
		proposta.setListarPlanilhaEquipamento(listPlanilhaTecEquipamento);
		
		novaProposta = true;
		novaPrecificacao = false;
		propostas = new ArrayList<>();
		verificaSeInseriPlanfinanceira();
		verificaSeInseriPlanfinanceiraTec();
		verificaSeInseriEquipeTecnica();
		verificaSeInseriEquipamento();
	}
	
	//Voltar para proposta
	public void voltarPrecificacao(){
		novaProposta = false;
		novaPrecificacao = true;
	}

	private Proposta propostaModelo;
	public void preSalvarProposta() {
		try {
			if(this.proposta != null && this.proposta.getId() == null){
				proposta.setCliente(clienteService.getClienteById(idCliente));
				proposta.setAtivo(true);
				proposta.setStatusProposta("Pendente");
				proposta.setRevisaoPrecificacao(0L);
				this.proposta = this.propostaService.preSalvar(this.proposta);
				if(propostaModelo != null ){
					salvarCopiaProposta(propostaModelo);
				}
				FacesUtil.addInfoMessage("Precificação criada com sucesso.");
				
				labelBotaoSalvarPrecificacao = "Salvar Precificação";
				labelBotaoCriarProposta = "Criar Proposta";
				propostaModelo = null;
				idCliente = null;
			}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void salvarProposta() {
		try {
			this.proposta.setCliente(clienteService.getClienteById(idCliente));
			this.proposta.setDataInclusao(new java.util.Date());
			this.proposta.setDataAtualizacao(new java.util.Date());
			this.proposta.setUsuarioAtualizacao(usuarioLogado);
			this.proposta.setAtivo(true);
			this.proposta = this.propostaService.salvar(this.proposta);
	 
			FacesUtil.addInfoMessage("Proposta salva com sucesso.");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public String concatNome(String param){
		String nome = param;
		if(nome.length() > 20){
			nome = nome.substring(0, 20)+"...";
		}
		return nome;
	}
	
	public String concatProjeto(String param){
		String nome = param;
		if(nome.length() > 27){
			nome = nome.substring(0, 27)+"...";
		}
		return nome;
	}
	
	public void salvarCopiaProposta(Proposta copiaProposta){
		try{
		
			List<CustoExecucao> listCustoExecucao = custoExecucaoService.getByPropostaId(copiaProposta.getId());
			if(listCustoExecucao != null && !listCustoExecucao.isEmpty()){
				listExecucaoTemp = custoExecucaoService.salvarCopiaExecucao(listCustoExecucao, this.proposta);
			}
			
			List<CustoAdministrativo> listCustoAdm = custoAdministrativoService.getByPropostaId(copiaProposta.getId());
			if(listCustoAdm != null && !listCustoAdm.isEmpty()){	
				listAdministrativoTemp = custoAdministrativoService.salvarCopiaCustoAdm(listCustoAdm, this.proposta);
			}
			
			List<CustoDeslocamento> listDeslocamento = custoDeslocamentoService.getByPropostaId(copiaProposta.getId());
			if(listDeslocamento != null && !listDeslocamento.isEmpty()){		
				listDeslocamentoTemp = custoDeslocamentoService.salvarCopiaCustoDeslocamento(listDeslocamento, this.proposta);
			}
			
			List<CustoSeguranca> listSeguranca = custoSegurancaService.getByPropostaId(copiaProposta.getId());
			if(listSeguranca != null && !listSeguranca.isEmpty()){
				listSegurancaTemp = custoSegurancaService.salvarCopiaCustoSeguranca(listSeguranca, this.proposta);
			}
			
			List<CustoOperacional> listOperacional = custoOperacionalService.getByPropostaId(copiaProposta.getId());
			if(listOperacional != null && !listOperacional.isEmpty()){
				listOperacionalTemp = custoOperacionalService.salvarCopiaCustoOperacional(listOperacional, copiaProposta);
			}
			
			this.proposta.setDesconto(copiaProposta.getDesconto());
			this.proposta.setPercentualBDI(copiaProposta.getPercentualBDI());
			this.proposta.setPercentualComissao(copiaProposta.getPercentualComissao());
			this.proposta.setValorTotalComBdiComissao(copiaProposta.getValorTotalComBdiComissao());
			this.proposta.setValorTotalCustosAdministrativos(copiaProposta.getValorTotalCustosAdministrativos());
			this.proposta.setValorTotalCustosDesclocamento(copiaProposta.getValorTotalCustosDesclocamento());
			this.proposta.setValorTotalCustosExecucao(copiaProposta.getValorTotalCustosExecucao());
			this.proposta.setValorTotalCustosOperacionais(copiaProposta.getValorTotalCustosOperacionais());
			this.proposta.setValorTotalCustosSeguranca(copiaProposta.getValorTotalCustosSeguranca());
			this.proposta.setValorTotalDaProposta(copiaProposta.getValorTotalDaProposta());
			this.proposta.setValorTotalImpostos(copiaProposta.getImpostos());
			this.proposta.setValorTotalPrecificacao(copiaProposta.getValorTotalPrecificacao());
			this.proposta.setValorTotalSemBdiComissao(copiaProposta.getValorTotalSemBdiComissao());
			
			this.propostaService.preSalvar(this.proposta);
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage("Erro ao tentar copiar precificação. Entre em contato com administrador - erro: "+e.getMessage());
		}
	}
	
	public void salvarPrecificacao(){
		try {
			if(this.proposta == null){
				FacesUtil.addErrorMessage("Por favor, salve a proposta antes de adicionar itens.");
				return;
			}
			proposta.setCliente(clienteService.getClienteById(idCliente));
			this.proposta.setDataAtualizacao(new java.util.Date());
			this.proposta.setUsuarioAtualizacao(usuarioLogado);
			this.proposta.setAtivo(true);
			this.propostaService.atualizarProposta(this.proposta, statusProposta);
			statusProposta = null;
			
			//Salva os itens de Execução
			if (listExecucaoTemp != null && !listExecucaoTemp.isEmpty()) {
				CustoExecucao itemDespesa;
				for (CustoExecucao item : listExecucaoTemp) {
					if(item.getId() == null){
						 itemDespesa = new CustoExecucao();
						 itemDespesa.setDescricao(item.getDescricao());
						 itemDespesa.setValorUnitario(item.getValorUnitario());
						 itemDespesa.setQuantidade(item.getQuantidade());
						 itemDespesa.setObservacoes(item.getObservacoes());
						 itemDespesa.setValorTotal(item.getQuantidade()*item.getValorUnitario());
						 itemDespesa.setProposta(this.proposta);
						 itemDespesa.setTpUnidadeMedida(item.getTpUnidadeMedida());
						 itemDespesa.setAtivo(true);
						 custoExecucaoService.salvar(itemDespesa);
					}
				}
			}
			
			//Salva os itens de Deslocamento
			if (listDeslocamentoTemp != null && !listDeslocamentoTemp.isEmpty()) {
				CustoDeslocamento itemDespesa;
				for (CustoDeslocamento item : listDeslocamentoTemp) {
					if(item.getId() == null){
						 itemDespesa = new CustoDeslocamento();
						 itemDespesa.setDescricao(item.getDescricao());
						 itemDespesa.setValorUnitario(item.getValorUnitario());
						 itemDespesa.setQuantidade(item.getQuantidade());
						 itemDespesa.setObservacoes(item.getObservacoes());
						 itemDespesa.setValorTotal(item.getQuantidade()*item.getValorUnitario());
						 itemDespesa.setProposta(this.proposta);
						 itemDespesa.setTpUnidadeMedida(item.getTpUnidadeMedida());
						 itemDespesa.setAtivo(true);
						 custoDeslocamentoService.salvar(itemDespesa);
					}
				}
			}
			
			//Salva os itens de Operacional
			if (listOperacionalTemp != null && !listOperacionalTemp.isEmpty()) {
				CustoOperacional itemDespesa;
				for (CustoOperacional item : listOperacionalTemp) {
					if(item.getId() == null){
						 itemDespesa = new CustoOperacional();
						 itemDespesa.setDescricao(item.getDescricao());
						 itemDespesa.setValorUnitario(item.getValorUnitario());
						 itemDespesa.setQuantidade(item.getQuantidade());
						 itemDespesa.setObservacoes(item.getObservacoes());
						 itemDespesa.setValorTotal(item.getQuantidade()*item.getValorUnitario());
						 itemDespesa.setProposta(this.proposta);
						 itemDespesa.setTpUnidadeMedida(item.getTpUnidadeMedida());
						 itemDespesa.setAtivo(true);
						 custoOperacionalService.salvar(itemDespesa);
					}
				}
			}
			
			//Salva os itens de Seguranca
			if (listSegurancaTemp != null && !listSegurancaTemp.isEmpty()) {
				CustoSeguranca itemDespesa;
				for (CustoSeguranca item : listSegurancaTemp) {
					if(item.getId() == null){
						 itemDespesa = new CustoSeguranca();
						 itemDespesa.setDescricao(item.getDescricao());
						 itemDespesa.setValorUnitario(item.getValorUnitario());
						 itemDespesa.setQuantidade(item.getQuantidade());
						 itemDespesa.setObservacoes(item.getObservacoes());
						 itemDespesa.setValorTotal(item.getQuantidade()*item.getValorUnitario());
						 itemDespesa.setProposta(this.proposta);
						 itemDespesa.setTpUnidadeMedida(item.getTpUnidadeMedida());
						 itemDespesa.setAtivo(true);
						 custoSegurancaService.salvar(itemDespesa);
					}
				}
			}
			
			//Salva os itens de Seguranca
			if (listAdministrativoTemp != null && !listAdministrativoTemp.isEmpty()) {
				CustoAdministrativo itemDespesa;
				for (CustoAdministrativo item : listAdministrativoTemp) {
					if(item.getId() == null){
						 itemDespesa = new CustoAdministrativo();
						 itemDespesa.setDescricao(item.getDescricao());
						 itemDespesa.setValorUnitario(item.getValorUnitario());
						 itemDespesa.setQuantidade(item.getQuantidade());
						 itemDespesa.setObservacoes(item.getObservacoes());
						 itemDespesa.setValorTotal(item.getQuantidade()*item.getValorUnitario());
						 itemDespesa.setProposta(this.proposta);
						 itemDespesa.setTpUnidadeMedida(item.getTpUnidadeMedida());
						 itemDespesa.setAtivo(true);
						 custoAdministrativoService.salvar(itemDespesa);
					}
				}
			}
			
			if(validaMsg){
				if(nomeSalvar != null && !nomeSalvar.isEmpty()){
					FacesUtil.addInfoMessage(nomeSalvar +" atualizada com sucesso.");
				}else{
					FacesUtil.addInfoMessage("Precificação atualizada com sucesso.");
				}
			}
			validaMsg = true;
			
		
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void atualizarProposta() {
		try {
			if(this.proposta == null){
				FacesUtil.addErrorMessage("Por favor, salve a proposta antes de adicionar itens.");
				return;
			}
			
			//verificar esse ponto a nova revisao
			this.proposta.setCliente(clienteService.getClienteById(idCliente));
			this.proposta.setDataAtualizacao(new java.util.Date());
			this.proposta.setUsuarioAtualizacao(usuarioLogado);
			this.proposta.setAtivo(true);
			
			if(this.proposta.getDesconto() == null){
				this.proposta.setDesconto(0.0);
			}
			
			if(this.proposta.getPercentualBDI() == null){
				this.proposta.setPercentualBDI(0.0);
			}
			
			if(this.proposta.getPercentualComissao() == null){
				this.proposta.setPercentualComissao(0.0);
			}

			nomeSalvar = "Proposta";
			salvarPrecificacao();
			nomeSalvar = null;
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void excluirProposta() {
		statusProposta = "Excluída";
		try {
			this.proposta.setDataAtualizacao(new java.util.Date());
			this.proposta.setUsuarioAtualizacao(usuarioLogado);
			this.proposta.setMotivoExclusao(motivoExclusao);
			this.proposta.setStatusProposta("Cancelada");
			this.propostaService.atualizarProposta(this.proposta, statusProposta);
			motivoExclusao = null;
			carregarTodasPropostas();
			FacesUtil.addInfoMessage("Proposta cancelada com sucesso!");

		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void preExcluir(Proposta proposta){
		this.proposta = proposta;
		RequestContext context = RequestContext.getCurrentInstance();
		
		/*if(this.proposta.getRevisaoPrecificacao() >= 1 || this.proposta.getStatusProposta().equalsIgnoreCase("Emitida")){
			context.execute("PF('dialogNaoExcluir').show();");
		}else{*/
			context.execute("PF('dialogExcluir').show();");
		/*}*/
	}
	
	public void fecharModalExlusao(){
		RequestContext.getCurrentInstance().execute("PF('dialogMotivoExclusao').hide();");
		vizualizaModalMotivo = false;
	}
	private String motivoExclusao;
	private Boolean vizualizaModalMotivo;
	
	public void modalExcluirProposta() {
		RequestContext.getCurrentInstance().execute("PF('dialogMotivoExclusao').show();");
		vizualizaModalMotivo = true;
	}
	
	public void vizualizarMotivo(Proposta proposta){
		motivoExclusao = proposta.getMotivoExclusao();
		RequestContext.getCurrentInstance().execute("PF('dialogMotivoExclusao').show();");
		vizualizaModalMotivo = false;
	}

	private void carregarTodasPropostas() {
		propostas = new ArrayList<>();
		propostas = this.propostaService.getPropostaByFilter(this.filtroNumeroProposta, this.filtroIdCliente,
				null, this.filtroDataInicial, this.filtroDataFinal);
	}
	
	public void criarNovaRevisao(){
		statusProposta = "NovaRevisao";
		editProposta();
	}
//TODO
	public void preEditarProposta(Proposta proposta){
		this.proposta = proposta;
		RequestContext context = RequestContext.getCurrentInstance();
		
		if(proposta != null && proposta.getId()!=null && proposta.getStatusProposta().equalsIgnoreCase("Contratada")){
			context.execute("PF('dialogNaoPreEditar').show();");
		/*
		}else if(proposta != null && proposta.getId()!=null && proposta.getStatusProposta().equals("Finalizada")){
			context.execute("PF('dialogPreEditar').show();");*/
		
		}else if(proposta != null && proposta.getId()!=null && proposta.getStatusProposta().equals("Emitida")){
			context.execute("PF('dialogEmissao').show();");
		}else{
			editProposta();
		}
	}
	
	public void editProposta() {
	
		if(this.proposta != null && this.proposta.getId() !=null){
							
			idCliente = this.proposta.getCliente().getId();
			tipoItensPropostaFinanceira = tipoItemPropostaService.getByPropostaId(this.proposta.getId(), ETipoProposta.FINANCEIRA.getFlag());
			tipoItensPropostaTecnica = tipoItemPropostaService.getByPropostaId(this.proposta.getId(), ETipoProposta.TECNICA.getFlag());
			tipoItensPropostaTecnicaFinanceira = tipoItemPropostaService.getByPropostaId(this.proposta.getId(), ETipoProposta.TECN_FINANCE.getFlag());
			this.proposta.setListarTipoItemPropostaFinanceira(tipoItensPropostaFinanceira);
			this.proposta.setListarTipoItemPropostaTecnica(tipoItensPropostaTecnica);
			this.proposta.setListarTipoItemPropostaTecFinanceira(tipoItensPropostaTecnicaFinanceira);
			
			//Carrega a lista planilha Financeira
			carregaPlanilhaFinanceira();
			carregaListaExecucao();
			
			//Carrega a lista Temp de Deslocamento
			carregaListaDeslocamento();
			
			//carrega filtro de serviços(tela principal)
			filtrarServico();
			
			//Carrega a lista Temp de Operacional
			listOperacionalTemp = new ArrayList<CustoOperacional>();
			Set<CustoOperacional> list3 = new HashSet<CustoOperacional>(custoOperacionalService.getByPropostaId(this.proposta.getId()));
			this.proposta.setDespesasOperacionais(list3);
			listOperacionalTemp.addAll(list3);
			
			//Carrega a lista Temp de Seguranca
			listSegurancaTemp = new ArrayList<CustoSeguranca>();
			Set<CustoSeguranca> list4 = new HashSet<CustoSeguranca>(custoSegurancaService.getByPropostaId(proposta.getId()));
			proposta.setDespesasSeguranca(list4);
			listSegurancaTemp.addAll(list4);
			
			//Carrega a lista Temp de Administrativo
			listAdministrativoTemp = new ArrayList<CustoAdministrativo>();
			Set<CustoAdministrativo> list5 = new HashSet<CustoAdministrativo>(custoAdministrativoService.getByPropostaId(proposta.getId()));
			proposta.setDespesasAdministrativas(list5);
			listAdministrativoTemp.addAll(list5);
			
			Set<CustoBdiComissao> list6 = new HashSet<CustoBdiComissao>(custoBDIComissaoService.getByPropostaId(proposta.getId()));
			proposta.setDespesasBdiComissao(list6);
		}else{
			tipoItensPropostaFinanceira = new ArrayList<TipoItemProposta>();
			listarCustoPlanilhaFinanceira = new ArrayList<CustoPlanilhaFinanceira>();
			
			//Equipe tecnica
			listCustoEquipeTecnica = new ArrayList<>();
			listPlanilhaTecEquipamento = new ArrayList<>();
		}
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("proposta", this.proposta);

			isEditProposta();
			visualizar = false;
			editar = true;
			pesquisar = false;
			novaPrecificacao= true;
			labelBotaoSalvarPrecificacao = "Atualizar";
			if("NovaRevisao".equalsIgnoreCase(statusProposta)){
				salvarPrecificacao();
			}
			novaProposta = false;
			viewProposta = false;
			statusProposta = null;
			preencherListaAnexos();
			
		/*	if("Finalizada".equalsIgnoreCase(this.proposta.getStatusProposta())){
				habilitaIrPara = false;
			}else{
				habilitaIrPara = true;
			}*/
	}

	public void carregaListaExecucao() {
		//Carrega a lista Temp de Execução
		listExecucaoTemp = new ArrayList<CustoExecucao>();
		Set<CustoExecucao> list1 = new HashSet<CustoExecucao>(custoExecucaoService.getByPropostaId(this.proposta.getId()));
		this.proposta.setCustos(list1);
		listExecucaoTemp.addAll(list1);
	}
	
	public void carregaListaDeslocamento(){
		listDeslocamentoTemp = new ArrayList<CustoDeslocamento>();
		Set<CustoDeslocamento> list2 = new HashSet<CustoDeslocamento>(custoDeslocamentoService.getByPropostaId(this.proposta.getId()));
		this.proposta.setDespesasDeslocamentos(list2);
		listDeslocamentoTemp.addAll(list2);
	}
	
	public void carregaPlanilhaFinanceira(){
		listCustoPlanFinanceiraTemp = new ArrayList<CustoPlanilhaFinanceira>();
		listarCustoPlanilhaFinanceira = custoPlanilhaFinanceiraService.getByPropostaId(this.proposta.getId());
		this.proposta.setListarCustoPlanilhaFinanceira(listarCustoPlanilhaFinanceira);
		listCustoPlanFinanceiraTemp.addAll(listarCustoPlanilhaFinanceira);
		this.proposta.setValorTotalCustoPlanilhaFinanceira(calculoValorTotalCustoPlanilhaFinanceira());
	}
	
	//carrega Equipe Tecnica
	public void carregaEquipeTecnica(){
		listCustoEquipeTecnica = new ArrayList<>();
		listCustoEquipeTecnica = custoPlanilhaTecnicaService.getByPropostaId(this.proposta.getId());
		this.proposta.setListarCustoEquipeTecnica(listCustoEquipeTecnica);
	}
	
	//carrega Equipe Tecnica
		public void carregaPlanEquipamento(){
			listPlanilhaTecEquipamento = new ArrayList<>();
			listPlanilhaTecEquipamento = planilhaTecEquipamentoService.getByPropostaId(this.proposta.getId());
			this.proposta.setListarPlanilhaEquipamento(listPlanilhaTecEquipamento);
		}
	
	public void novaProposta(){
		
		propostas = new ArrayList<>();
		propostas.clear();
		proposta = new Proposta();
		proposta.setCliente(new Cliente());
		proposta.setDataInclusao(new Date(1L));
		novaPrecificacao= true;
		novaProposta = false;
		pesquisar = false;
		editar = false;
		visualizar = false;
		viewProposta = false;
	}

	//Pré finalizar proposta
 	public void preFinalizar(){
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dialogFinalizarProposta').show();");
	}

 	//Finalizar Proposta
 	public void finalizarProposta(){
		statusProposta = "Finalizada";
		atualizarProposta();
		
		/*if(proposta == null){
			proposta = new Proposta();
		}
		novaPrecificacao= false;
		novaProposta = false;
		pesquisar = true;
		visualizar = false;
		editar = false;
		viewProposta = false;

		propostas = new ArrayList<>();
		propostas = this.propostaService.getPropostaByFilter(this.filtroNumeroProposta, this.filtroIdCliente,
					null, this.filtroDataInicial, this.filtroDataFinal);*/
	}
	
	//Volta para Pesquisa
	public void voltar(){
		if(proposta == null){
			proposta = new Proposta();
		}
		novaPrecificacao= false;
		novaProposta = false;
		pesquisar = true;
		visualizar = false;
		editar = false;
		viewProposta = false;
		listExecucaoTemp = new ArrayList<CustoExecucao>();
		listDeslocamentoTemp = new ArrayList<CustoDeslocamento>();
		listOperacionalTemp = new ArrayList<CustoOperacional>();
		listSegurancaTemp = new ArrayList<CustoSeguranca>();
		listAdministrativoTemp = new ArrayList<CustoAdministrativo>();
		listCustoPlanFinanceiraTemp = new ArrayList<CustoPlanilhaFinanceira>();
		
		//equipe tecnica
		listCustoEquipeTecnica =  new ArrayList<>();
		
		//Equipamento
		listPlanilhaTecEquipamento =  new ArrayList<>();
		
		propostas = new ArrayList<>();
		propostas = this.propostaService.getPropostaByFilter(this.filtroNumeroProposta, this.filtroIdCliente,
					null, this.filtroDataInicial, this.filtroDataFinal);
		
		listaArquivosAnexados = new ArrayList<FileUploadEvent>();
		anexos = new ArrayList<Anexo>();
		fileUploadEvent = null;
		excluirAnexo = new Anexo();
		file = null;
		
	}
	
	/*public void visualizarHistorico(PropostaHistorico proposta) {
		
	}*/
	
		
	public void visualizarProposta(Proposta proposta) {
		this.proposta = proposta;
		filtrarServico();

		FacesUtil.addParamSession(TipoPagina.VISUALIZAR_PROPOSTA);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("proposta", this.proposta);
			
		if(proposta != null && proposta.getId() !=null){
			idCliente = proposta.getCliente().getId();
			
			tipoItensPropostaFinanceira = tipoItemPropostaService.getByPropostaId(proposta.getId(),ETipoProposta.FINANCEIRA.getFlag());
			tipoItensPropostaTecnica = tipoItemPropostaService.getByPropostaId(this.proposta.getId(), ETipoProposta.TECNICA.getFlag());
			tipoItensPropostaTecnicaFinanceira = tipoItemPropostaService.getByPropostaId(this.proposta.getId(), ETipoProposta.TECN_FINANCE.getFlag());
			
			proposta.setListarTipoItemPropostaFinanceira(tipoItensPropostaFinanceira);
			proposta.setListarTipoItemPropostaTecnica(tipoItensPropostaTecnica);
			proposta.setListarTipoItemPropostaTecFinanceira(tipoItensPropostaTecnicaFinanceira);
			
			//Carrega a planilha financeira temp
			listarCustoPlanilhaFinanceira = custoPlanilhaFinanceiraService.getByPropostaId(proposta.getId());
			proposta.setListarCustoPlanilhaFinanceira(listarCustoPlanilhaFinanceira);
			listCustoPlanFinanceiraTemp.addAll(listarCustoPlanilhaFinanceira);
			proposta.setValorTotalCustoPlanilhaFinanceira(calculoValorTotalCustoPlanilhaFinanceira());
			
			listCustoEquipeTecnica = custoPlanilhaTecnicaService.getByPropostaId(proposta.getId());
			proposta.setListarCustoEquipeTecnica(listCustoEquipeTecnica);
			
			listPlanilhaTecEquipamento =  planilhaTecEquipamentoService.getByPropostaId(proposta.getId());
			proposta.setListarPlanilhaEquipamento(listPlanilhaTecEquipamento);
			
			//Carrega a lista Temp de Execução
			listExecucaoTemp = new ArrayList<CustoExecucao>();
			Set<CustoExecucao> list1 = new HashSet<CustoExecucao>(custoExecucaoService.getByPropostaId(proposta.getId()));
			proposta.setCustos(list1);
			listExecucaoTemp.addAll(list1);
			
			//Carrega a lista Temp de Deslocamento
			listDeslocamentoTemp = new ArrayList<CustoDeslocamento>();
			Set<CustoDeslocamento> list2 = new HashSet<CustoDeslocamento>(custoDeslocamentoService.getByPropostaId(proposta.getId()));
			proposta.setDespesasDeslocamentos(list2);
			listDeslocamentoTemp.addAll(list2);
			
			//Carrega a lista Temp de Operacional
			listOperacionalTemp = new ArrayList<CustoOperacional>();
			Set<CustoOperacional> list3 = new HashSet<CustoOperacional>(custoOperacionalService.getByPropostaId(proposta.getId()));
			proposta.setDespesasOperacionais(list3);
			listOperacionalTemp.addAll(list3);
			
			//Carrega a lista Temp de Seguranca
			listSegurancaTemp = new ArrayList<CustoSeguranca>();
			Set<CustoSeguranca> list4 = new HashSet<CustoSeguranca>(custoSegurancaService.getByPropostaId(proposta.getId()));
			proposta.setDespesasSeguranca(list4);
			listSegurancaTemp.addAll(list4);
			
			//Carrega a lista Temp de Administrativo
			listAdministrativoTemp = new ArrayList<CustoAdministrativo>();
			Set<CustoAdministrativo> list5 = new HashSet<CustoAdministrativo>(custoAdministrativoService.getByPropostaId(proposta.getId()));
			proposta.setDespesasAdministrativas(list5);
			listAdministrativoTemp.addAll(list5);
			
			Set<CustoBdiComissao> list6 = new HashSet<CustoBdiComissao>(custoBDIComissaoService.getByPropostaId(proposta.getId()));
			proposta.setDespesasBdiComissao(list6);
						
			calcularValorTotalAposBdiComissao();
		}else{
			tipoItensPropostaFinanceira = new ArrayList<TipoItemProposta>();
			listarCustoPlanilhaFinanceira = new ArrayList<CustoPlanilhaFinanceira>();
			
		}
		labelBotaoSalvarPrecificacao = "Atualizar Precificação";
		labelBotaoCriarProposta = "visualizar Proposta";
		pesquisar = false;
		novaPrecificacao = true;
		novaProposta = false;
		visualizar = true;
		viewProposta = true;
		preencherListaAnexos();
		
		if("Finalizada".equalsIgnoreCase(this.proposta.getStatusProposta())){
			habilitaIrPara = false;
		}else{
			habilitaIrPara = true;
		}
	}
	
	public void voltaHistorico(){
		visualizarHistorico = false;
		novaPrecificacao= false;
		novaProposta = false;
		pesquisar = true;
		visualizar = false;
		editar = false;
		viewProposta = false;
	}
	
	public void consultarHistoricoProposta(Proposta proposta) {
		this.proposta = proposta;
		visualizarHistorico = true;
		novaPrecificacao= false;
		novaProposta = false;
		pesquisar = false;
		visualizar = false;
		editar = false;
		viewProposta = false;
		visualizarHistorico = true;
		carregarHistoricoByProposta(this.proposta);
	}

	private void carregarHistoricoByProposta(Proposta propostaId) {
		historicos = new ArrayList<>();
		try {
			historicos = this.propostaService.getPropostaHistoricoByFilter(propostaId);
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Boolean isEditProposta() {
		if(proposta == null){
			return true;
		}
		return this.proposta.isExistente() && !viewProposta;
	}

	public Boolean isNewProposta() {
		if(proposta == null){
			return true;
		}
		return this.proposta.isNovo();
	}
	public String verificarClasseIconeAtivo(String status){
		if("Contratada".equals(status)){
			return "icone-splash ico-activated";
		}else if ("Não aprovada".equals(status)){
			return "icone-splash ico-disabled";
		}else if ("Emitida".equals(status)){
			return "icone-splash ico-alert";
		}else{
			return "icone-splash ico-alert";
		}
	}
	
	public String populaTitulo(String status){
		if("Contratada".equals(status)){
			return "Proposta aprovado e contratada";
		}else if ("Não aprovada".equals(status)){
			return "Proposta não aprovada e não contratada";
		}else if ("Emitida".equals(status)){
			return "Proposta em análise";
		}else{
			return "";
		}
	}
	
	//Preenche os dados que vem da proposta na tela
	public void preecheDadosProposta(){
		String nrContrato = this.contrato.getProposta().getNumeroProposta().replace("AC-", "").replace(".", "");
		nrContrato = nrContrato.substring(0, nrContrato.indexOf("/"));
		contrato.setNrContrato(nrContrato);
		Double vlrContrato = contrato.getProposta().getValorTotalComBdiComissao()+((contrato.getProposta().getValorTotalComBdiComissao()) / (1 - contrato.getProposta().getImpostos())  - (contrato.getProposta().getValorTotalComBdiComissao())-(contrato.getProposta().getDesconto()));
		contrato.setVlrContrato(vlrContrato);
		
	}
		
	public void liberaFormaPagamento(){
		if(this.contrato.getNrContrato() != null && this.contrato.getVlrContrato() != null && this.contrato.getDataAssinatura() != null){
			setLiberaFormaPagto(true);
		}else{
			this.contrato.setFormaPagamento("");
			this.contrato.setValorParcela(null);
			setLiberaFormaPagto(false);
		}
	}
		
	public void fecharModalContrato(){
		this.contrato =  new Contrato();
		RequestContext.getCurrentInstance().execute("PF('dialogContrato').hide();");
	}
	
	//Lista as parcelas do contrato
	public void listParcelas(){
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
		
	public Date somaDias(Date data, int dias) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		cal.add(Calendar.MONTH, dias);
		return cal.getTime();
	}
		
	public void salvarContrato(){
		try{
			contrato.setDataInclusao(new Date());
			contrato.setStatusContrato("Vigente");
			contrato.setNomeCliente(contrato.getProposta().getCliente().getNome());
			contrato.setDataAtualizacao(new Date());
			contrato.setUsuarioAtualizacao(usuarioLogado);
			
			if(listParcelaContrato!= null){
				Set<Parcela> parcelasContrato = new HashSet<>();
				parcelasContrato.addAll(listParcelaContrato);
				contrato.setParcelas(parcelasContrato);
			}else{
				contrato.setParcelas(new HashSet<>());
			}
			contratoService.salvar(contrato);
			
			this.proposta.setStatusProposta("Contratada");
			this.proposta.setDataAtualizacao(new Date());
			this.proposta.setUsuarioAtualizacao(usuarioLogado);
			this.propostaService.atualizarProposta(this.proposta, null);
			this.contrato =  new Contrato();
			FacesUtil.addInfoMessage("Contrato salva com sucesso.");
			pesquisarPropostas();

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
		
	public void naoAprovarProposta(){
		try{
			this.proposta.setStatusProposta("Não aprovada");
			this.proposta.setDataAtualizacao(new Date());
			this.proposta.setUsuarioAtualizacao(usuarioLogado);
			this.propostaService.atualizarProposta(this.proposta, null);
			this.contrato = new Contrato();
			FacesUtil.addInfoMessage("Proposta atualizada com sucesso.");
			
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
		
	public void aprovarContrato(){
		this.contrato.setProposta(this.proposta);
		preecheDadosProposta();
		RequestContext.getCurrentInstance().execute("PF('dialogContrato').show();");
	}
	
	public void preAprovar(Proposta proposta){
		RequestContext context = RequestContext.getCurrentInstance();
		if(!"Não aprovada".equalsIgnoreCase(proposta.getStatusProposta()) && !"Contratada".equalsIgnoreCase(proposta.getStatusProposta())){
			this.proposta = proposta;
			context.execute("PF('dialogAprovar').show();");
		}else{
			context.execute("PF('dialogFinalizada').show();");
		}
	}
		
		
	public void pesquisarPropostas() {
		propostas = new ArrayList<>();
		try {
			propostas = this.propostaService.getPropostaByFilter(this.filtroNumeroProposta, this.filtroIdCliente,
					null, this.filtroDataInicial, this.filtroDataFinal);
			if(propostas == null || propostas.isEmpty()){
				FacesUtil.addErrorMessage("O filtro não retornou nenhum registro.");	
			}
				
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	//Proposta Tecnica Ofical versão 1-0
	public void gerarPropostaTecnicaOficialVer1() {
		visualizarProTec = true;
		if(!"Emitida".equalsIgnoreCase(this.proposta.getStatusProposta())){
			this.proposta.setDtEmissPropFinc(new Date());
		}
		salvarPrecificacao();
		List<TipoItemProposta> list = new ArrayList<>();
		
		if (proposta != null ) {
			List<TipoItemValor> listarTipoItemValor = new ArrayList<>();
				tipoItemValorGenerica = new TipoItemValor();
				tipoItemValorGenerica.setProposta(proposta);
				listarTipoItemValor.add(tipoItemValorGenerica);
				tipoItemProposta.setListarTipoItemValor(listarTipoItemValor);
				tipoItemProposta.setProposta(proposta);
				list.add(tipoItemProposta);
				
				String nomeProposta  ="Tecnica_" +  proposta.getNumeroProposta();
				nomeProposta = nomeProposta.replace(".", "").replace("/", "").replace("-", "");
			
			RelatorioUtil.geraRelatorioTipoPropostaGenerica("PropostaTecnicaOfficial_Ver1-0", list, nomeProposta);
		} else {
			throw new NegocioException("Favor selecionar uma proposta!");
		}
	}
	
	//Proposta Financeira Ofical versão 1-0
	public void gerarPropostaFinanceiraOficialVer1() {
		visualizarProFinanc = true;
		
		if(!"Emitida".equalsIgnoreCase(this.proposta.getStatusProposta())){
			this.proposta.setDtEmissPropFinc(new Date());
			salvarPrecificacao();
		}
		
		List<TipoItemProposta> list = new ArrayList<>();
		
		if (proposta != null ) {
			List<TipoItemValor> listarTipoItemValor = new ArrayList<>();
				tipoItemValorGenerica = new TipoItemValor();
				tipoItemValorGenerica.setProposta(proposta);
				listarTipoItemValor.add(tipoItemValorGenerica);
				tipoItemProposta.setListarTipoItemValor(listarTipoItemValor);
				tipoItemProposta.setProposta(proposta);
				list.add(tipoItemProposta);
				
				String nomeProposta  = "Financeira_" + proposta.getNumeroProposta();
				nomeProposta = nomeProposta.replace(".", "").replace("/", "").replace("-", "");
			
			RelatorioUtil.geraRelatorioTipoPropostaGenerica("PropostaFinanceiraOfficial_Ver1-0", list, nomeProposta);
		} else {
			throw new NegocioException("Favor selecionar uma proposta!");
		}
	}
	
	public void historicoVisualizar(PropostaHistorico historico){
		this.setPropostaHistorico(historico);
		listCustoAdmHistory = custoAdministrativoHistoricoService.getByPropostaHistoricoId(historico.getId());
		listCustoExecucaoHistory = custoExecucaoHistoricoService.getByPropostaHistoricoId(historico.getId());
		listDeslocamentoHistory = custoDeslocamentoHistoricoService.getByPropostaHistoricoId(historico.getId());
		listCustoSegurancaHistory = custoSegurancaHistoricoService.getByPropostaHistoricoId(historico.getId());
		listOperacionalHistory = custoOperacionalHistoricoService.getByPropostaHistoricoId(historico.getId());
		
		acaoVisualHistorico = true;
		visualizarHistorico = false;
	}
	
	public void sairVizualizaHistorico(){
		acaoVisualHistorico = false;
		visualizarHistorico = true;

	}	
	
	//Proposta Tecnica-Financeira Ofical versão 1-0
	public void gerarPropostaTecnicaFinanceiraOficialVer1() {
		List<TipoItemProposta> list = new ArrayList<>();
		visualizarPrTecFinac =true;
		salvarPrecificacao();
		if (proposta != null ) {
			List<TipoItemValor> listarTipoItemValor = new ArrayList<>();
				tipoItemValorGenerica = new TipoItemValor();
				tipoItemValorGenerica.setProposta(proposta);
				listarTipoItemValor.add(tipoItemValorGenerica);
				tipoItemProposta.setListarTipoItemValor(listarTipoItemValor);
				tipoItemProposta.setProposta(proposta);
				list.add(tipoItemProposta);
				
				String nomeProposta  = "TecFinac_" + proposta.getNumeroProposta();
				nomeProposta = nomeProposta.replace(".", "").replace("/", "").replace("-", "");
			
			RelatorioUtil.geraRelatorioTipoPropostaGenerica("PropostaTecnicaFinanceiaOfficial_Ver1-0", list, nomeProposta);
		} else {
			throw new NegocioException("Favor selecionar uma proposta!");
		}
	}

	public Proposta getProposta() {
		return proposta;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	public boolean isViewProposta() {
		return viewProposta;
	}

	public void setViewProposta(boolean viewProposta) {
		this.viewProposta = viewProposta;
	}

	public String getFiltroNumeroProposta() {
		return filtroNumeroProposta;
	}

	public void setFiltroNumeroProposta(String filtroNumeroProposta) {
		this.filtroNumeroProposta = filtroNumeroProposta;
	}

	public List<Proposta> getPropostas() {
		return propostas;
	}

	public void setPropostas(List<Proposta> propostas) {
		this.propostas = propostas;
	}

	public CustoAdministrativo getCustoAdministrativo() {
		return custoAdministrativo;
	}
	
	public void setCustoAdministrativo(CustoAdministrativo custoAdministrativo) {
		this.custoAdministrativo = custoAdministrativo;
	}

	public CustoSeguranca getCustoSeguranca() {
		return custoSeguranca;
	}

	public void setCustoSeguranca(CustoSeguranca custoSeguranca) {
		this.custoSeguranca = custoSeguranca;
	}

	public CustoDeslocamento getCustoDeslocamento() {
		return custoDeslocamento;
	}

	public void setCustoDeslocamento(CustoDeslocamento custoDeslocamento) {
		this.custoDeslocamento = custoDeslocamento;
	}

	public CustoOperacional getCustoOperacional() {
		return custoOperacional;
	}

	public void setCustoOperacional(CustoOperacional custoOperacional) {
		this.custoOperacional = custoOperacional;
	}

	public String getFiltroCliente() {
		return filtroCliente;
	}

	public void setFiltroCliente(String filtroCliente) {
		this.filtroCliente = filtroCliente;
	}

	public Date getFiltroDataInicial() {
		return filtroDataInicial;
	}

	public void setFiltroDataInicial(Date filtroDataInicial) {
		this.filtroDataInicial = filtroDataInicial;
	}

	public Date getFiltroDataFinal() {
		return filtroDataFinal;
	}

	public void setFiltroDataFinal(Date filtroDataFinal) {
		this.filtroDataFinal = filtroDataFinal;
	}

	public List<PropostaHistorico> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(List<PropostaHistorico> historicos) {
		this.historicos = historicos;
	}

	public List<TipoDespesa> getTipoDespesas() {
		return tipoDespesas;
	}

	public void setTipoDespesas(List<TipoDespesa> tipoDespesas) {
		this.tipoDespesas = tipoDespesas;
	}

	public Long getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(Long tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public TipoItemValor getTipoItemValor() {
		return tipoItemValor;
	}

	public void setTipoItemValor(TipoItemValor tipoItemValor) {
		this.tipoItemValor = tipoItemValor;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public CustoExecucao getCustoExecucao() {
		return custoExecucao;
	}

	public void setCustoExecucao(CustoExecucao custoExecucao) {
		this.custoExecucao = custoExecucao;
	}

	public TipoItemProposta getTipoItemProposta() {
		return tipoItemProposta;
	}

	public void setTipoItemProposta(TipoItemProposta tipoItemProposta) {
		this.tipoItemProposta = tipoItemProposta;
	}

	public String getDescricaoTipoItem() {
		return descricaoTipoItem;
	}

	public void setDescricaoTipoItem(String descricaoTipoItem) {
		this.descricaoTipoItem = descricaoTipoItem;
	}

	public String getTituloTipoItem() {
		return tituloTipoItem;
	}

	public void setTituloTipoItem(String tituloTipoItem) {
		this.tituloTipoItem = tituloTipoItem;
	}

	public List<TipoItemProposta> getTipoItensPropostaFinanceira() {
		return tipoItensPropostaFinanceira;
	}

	public void setTipoItensPropostaFinanceira(
			List<TipoItemProposta> tipoItensPropostaFinanceira) {
		this.tipoItensPropostaFinanceira = tipoItensPropostaFinanceira;
	}

	public List<TipoItemProposta> getTipoItensPropostaTecnica() {
		return tipoItensPropostaTecnica;
	}

	public void setTipoItensPropostaTecnica(
			List<TipoItemProposta> tipoItensPropostaTecnica) {
		this.tipoItensPropostaTecnica = tipoItensPropostaTecnica;
	}

	public Double getValorBDI() {
		return valorBDI;
	}

	public Double getValorComissao() {
		return valorComissao;
	}

	public Double getValorISS() {
		return valorISS;
	}

	public Double getValorConfins() {
		return valorConfins;
	}

	public Double getValorPis() {
		return valorPis;
	}

	public Double getValorCSLL() {
		return valorCSLL;
	}

	public Double getValorIr() {
		return valorIr;
	}

	public Double getValorImpostos() {
		return valorImpostos;
	}

	public void setValorBDI(Double valorBDI) {
		this.valorBDI = valorBDI;
	}

	public void setValorComissao(Double valorComissao) {
		this.valorComissao = valorComissao;
	}

	public void setValorISS(Double valorISS) {
		this.valorISS = valorISS;
	}

	public void setValorConfins(Double valorConfins) {
		this.valorConfins = valorConfins;
	}

	public void setValorPis(Double valorPis) {
		this.valorPis = valorPis;
	}

	public void setValorCSLL(Double valorCSLL) {
		this.valorCSLL = valorCSLL;
	}

	public void setValorIr(Double valorIr) {
		this.valorIr = valorIr;
	}

	public void setValorImpostos(Double valorImpostos) {
		this.valorImpostos = valorImpostos;
	}

	public Boolean getVoltar() {
		return voltar;
	}

	public void setVoltar(Boolean voltar) {
		this.voltar = voltar;
	}

	public CustoPlanilhaFinanceira getCustoPlanilhaFinanceira() {
		return custoPlanilhaFinanceira;
	}

	public void setCustoPlanilhaFinanceira(
			CustoPlanilhaFinanceira custoPlanilhaFinanceira) {
		this.custoPlanilhaFinanceira = custoPlanilhaFinanceira;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<CustoPlanilhaFinanceira> getListarCustoPlanilhaFinanceira() {
		return listarCustoPlanilhaFinanceira;
	}

	public void setListarCustoPlanilhaFinanceira(
			List<CustoPlanilhaFinanceira> listarCustoPlanilhaFinanceira) {
		this.listarCustoPlanilhaFinanceira = listarCustoPlanilhaFinanceira;
	}

	public String getLabelTipoProposta() {
		return labelTipoProposta;
	}

	public void setLabelTipoProposta(String labelTipoProposta) {
		this.labelTipoProposta = labelTipoProposta;
	}

	public String getTituloTipoItemFinanceiro() {
		return tituloTipoItemFinanceiro;
	}

	public void setTituloTipoItemFinanceiro(String tituloTipoItemFinanceiro) {
		this.tituloTipoItemFinanceiro = tituloTipoItemFinanceiro;
	}

	public String getDescricaoTipoItemFinanceiro() {
		return descricaoTipoItemFinanceiro;
	}

	public void setDescricaoTipoItemFinanceiro(String descricaoTipoItemFinanceiro) {
		this.descricaoTipoItemFinanceiro = descricaoTipoItemFinanceiro;
	}
	
	public Long getFiltroIdCliente() {
		return filtroIdCliente;
	}

	public void setFiltroIdCliente(Long filtroIdCliente) {
		this.filtroIdCliente = filtroIdCliente;
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

	public Boolean getNovaPrecificacao() {
		return novaPrecificacao;
	}

	public void setNovaPrecificacao(Boolean novaPrecificacao) {
		this.novaPrecificacao = novaPrecificacao;
	}

	public List<String> getTiposUnidades() {
		return tiposUnidades;
	}

	public void setTiposUnidades(List<String> tiposUnidades) {
		this.tiposUnidades = tiposUnidades;
	}

	public Boolean getNovaProposta() {
		return novaProposta;
	}

	public void setNovaProposta(Boolean novaProposta) {
		this.novaProposta = novaProposta;
	}

	public String getLabelBotaoSalvarPrecificacao() {
		return labelBotaoSalvarPrecificacao;
	}

	public void setLabelBotaoSalvarPrecificacao(String labelBotaoSalvarPrecificacao) {
		this.labelBotaoSalvarPrecificacao = labelBotaoSalvarPrecificacao;
	}

	public String getLabelBotaoCriarProposta() {
		return labelBotaoCriarProposta;
	}

	public void setLabelBotaoCriarProposta(String labelBotaoCriarProposta) {
		this.labelBotaoCriarProposta = labelBotaoCriarProposta;
	}

	public Long getTipoExclusaoDespesa() {
		return tipoExclusaoDespesa;
	}

	public void setTipoExclusaoDespesa(Long tipoExclusaoDespesa) {
		this.tipoExclusaoDespesa = tipoExclusaoDespesa;
	}

	public List<CustoExecucao> getListExecucaoTemp() {
		return listExecucaoTemp;
	}

	public void setListExecucaoTemp(List<CustoExecucao> listExecucaoTemp) {
		this.listExecucaoTemp = listExecucaoTemp;
	}

	public List<CustoDeslocamento> getListDeslocamentoTemp() {
		return listDeslocamentoTemp;
	}

	public void setListDeslocamentoTemp(List<CustoDeslocamento> listDeslocamentoTemp) {
		this.listDeslocamentoTemp = listDeslocamentoTemp;
	}

	public List<CustoOperacional> getListOperacionalTemp() {
		return listOperacionalTemp;
	}

	public void setListOperacionalTemp(List<CustoOperacional> listOperacionalTemp) {
		this.listOperacionalTemp = listOperacionalTemp;
	}

	public List<CustoSeguranca> getListSegurancaTemp() {
		return listSegurancaTemp;
	}

	public void setListSegurancaTemp(List<CustoSeguranca> listSegurancaTemp) {
		this.listSegurancaTemp = listSegurancaTemp;
	}

	public List<CustoAdministrativo> getListAdministrativoTemp() {
		return listAdministrativoTemp;
	}

	public void setListAdministrativoTemp(List<CustoAdministrativo> listAdministrativoTemp) {
		this.listAdministrativoTemp = listAdministrativoTemp;
	}

	public List<CustoPlanilhaFinanceira> getListCustoPlanFinanceiraTemp() {
		return listCustoPlanFinanceiraTemp;
	}

	public void setListCustoPlanFinanceiraTemp(List<CustoPlanilhaFinanceira> listCustoPlanFinanceiraTemp) {
		this.listCustoPlanFinanceiraTemp = listCustoPlanFinanceiraTemp;
	}

	public Boolean getVisualizarHistorico() {
		return visualizarHistorico;
	}

	public void setVisualizarHistorico(Boolean visualizarHistorico) {
		this.visualizarHistorico = visualizarHistorico;
	}


	public List <Proposta> getComboNumeroPropostas() {
		return comboNumeroPropostas;
	}

	public void setComboNumeroPropostas(List <Proposta> comboNumeroPropostas) {
		this.comboNumeroPropostas = comboNumeroPropostas;
	}


	public String getLabelBotaoAdicionar() {
		return labelBotaoAdicionar;
	}


	public void setLabelBotaoAdicionar(String labelBotaoAdicionar) {
		this.labelBotaoAdicionar = labelBotaoAdicionar;
	}


	public String getLabelBotaoAdicionarItemProposta() {
		return labelBotaoAdicionarItemProposta;
	}


	public void setLabelBotaoAdicionarItemProposta(String labelBotaoAdicionarItemProposta) {
		this.labelBotaoAdicionarItemProposta = labelBotaoAdicionarItemProposta;
	}


	public String getLabelBotaoAdicionarItemPropostaTec() {
		return labelBotaoAdicionarItemPropostaTec;
	}


	public void setLabelBotaoAdicionarItemPropostaTec(String labelBotaoAdicionarItemPropostaTec) {
		this.labelBotaoAdicionarItemPropostaTec = labelBotaoAdicionarItemPropostaTec;
	}


	public String getStatusProposta() {
		return statusProposta;
	}


	public void setStatusProposta(String statusProposta) {
		this.statusProposta = statusProposta;
	}


	public String getTipoAtualizacao() {
		return tipoAtualizacao;
	}


	public void setTipoAtualizacao(String tipoAtualizacao) {
		this.tipoAtualizacao = tipoAtualizacao;
	}


	public TipoItemValor getTipoItemValorGenerica() {
		return tipoItemValorGenerica;
	}


	public void setTipoItemValorGenerica(TipoItemValor tipoItemValorGenerica) {
		this.tipoItemValorGenerica = tipoItemValorGenerica;
	}

	public String getIdQuantidadeItem() {
		return idQuantidadeItem;
	}

	public void setIdQuantidadeItem(String idQuantidadeItem) {
		this.idQuantidadeItem = idQuantidadeItem;
	}

	public Boolean getBtAtivo() {
		return btAtivo;
	}

	public void setBtAtivo(Boolean btAtivo) {
		this.btAtivo = btAtivo;
	}

	public List<TipoServico> getTipoServicos() {
		return tipoServicos;
	}

	public void setTipoServicos(List<TipoServico> tipoServicos) {
		this.tipoServicos = tipoServicos;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Boolean getAtivaCombo() {
		return ativaCombo;
	}

	public void setAtivaCombo(Boolean ativaCombo) {
		this.ativaCombo = ativaCombo;
	}

	public String getTituloTipoItemTecFinan() {
		return tituloTipoItemTecFinan;
	}

	public void setTituloTipoItemTecFinan(String tituloTipoItemTecFinan) {
		this.tituloTipoItemTecFinan = tituloTipoItemTecFinan;
	}

	public String getDescricaoTipoItemTecFinan() {
		return descricaoTipoItemTecFinan;
	}

	public void setDescricaoTipoItemTecFinan(String descricaoTipoItemTecFinan) {
		this.descricaoTipoItemTecFinan = descricaoTipoItemTecFinan;
	}

	public List<TipoItemProposta> getTipoItensPropostaTecnicaFinanceira() {
		return tipoItensPropostaTecnicaFinanceira;
	}

	public void setTipoItensPropostaTecnicaFinanceira(List<TipoItemProposta> tipoItensPropostaTecnicaFinanceira) {
		this.tipoItensPropostaTecnicaFinanceira = tipoItensPropostaTecnicaFinanceira;
	}

	public Boolean getInseriPlanFinancera() {
		return inseriPlanFinancera;
	}

	public void setInseriPlanFinancera(Boolean inseriPlanFinancera) {
		this.inseriPlanFinancera = inseriPlanFinancera;
	}

	public Boolean getHabilitaIrPara() {
		return habilitaIrPara;
	}

	public void setHabilitaIrPara(Boolean habilitaIrPara) {
		this.habilitaIrPara = habilitaIrPara;
	}

	public Boolean getInseriPlanFinanceraTec() {
		return inseriPlanFinanceraTec;
	}

	public void setInseriPlanFinanceraTec(Boolean inseriPlanFinanceraTec) {
		this.inseriPlanFinanceraTec = inseriPlanFinanceraTec;
	}

	public CustoPlanilhaTecnica getCustoPlanilhaEquipeTecnica() {
		return custoPlanilhaEquipeTecnica;
	}

	public void setCustoPlanilhaEquipeTecnica(CustoPlanilhaTecnica custoPlanilhaEquipeTecnica) {
		this.custoPlanilhaEquipeTecnica = custoPlanilhaEquipeTecnica;
	}

	public List<CustoPlanilhaTecnica> getListCustoEquipeTecnica() {
		return listCustoEquipeTecnica;
	}

	public void setListCustoEquipeTecnica(List<CustoPlanilhaTecnica> listCustoEquipeTecnica) {
		this.listCustoEquipeTecnica = listCustoEquipeTecnica;
	}

	public CustoPlanilhaTecnica getCustoPlanilhaEquipeTecnicaExcluir() {
		return custoPlanilhaEquipeTecnicaExcluir;
	}

	public void setCustoPlanilhaEquipeTecnicaExcluir(CustoPlanilhaTecnica custoPlanilhaEquipeTecnicaExcluir) {
		this.custoPlanilhaEquipeTecnicaExcluir = custoPlanilhaEquipeTecnicaExcluir;
	}

	public Boolean getInseriPlanEquipeTec() {
		return inseriPlanEquipeTec;
	}

	public void setInseriPlanEquipeTec(Boolean inseriPlanEquipeTec) {
		this.inseriPlanEquipeTec = inseriPlanEquipeTec;
	}

	public Boolean getInseriPlanEquipamento() {
		return inseriPlanEquipamento;
	}

	public void setInseriPlanEquipamento(Boolean inseriPlanEquipamento) {
		this.inseriPlanEquipamento = inseriPlanEquipamento;
	}

	public PlanilhaTecEquipamento getPlanilhaTecEquipamento() {
		return planilhaTecEquipamento;
	}

	public void setPlanilhaTecEquipamento(PlanilhaTecEquipamento planilhaTecEquipamento) {
		this.planilhaTecEquipamento = planilhaTecEquipamento;
	}

	public List<PlanilhaTecEquipamento> getListPlanilhaTecEquipamento() {
		return listPlanilhaTecEquipamento;
	}

	public void setListPlanilhaTecEquipamento(List<PlanilhaTecEquipamento> listPlanilhaTecEquipamento) {
		this.listPlanilhaTecEquipamento = listPlanilhaTecEquipamento;
	}

	public PlanilhaTecEquipamento getPlanilhaTecEquipamentoExcluir() {
		return planilhaTecEquipamentoExcluir;
	}

	public void setPlanilhaTecEquipamentoExcluir(PlanilhaTecEquipamento planilhaTecEquipamentoExcluir) {
		this.planilhaTecEquipamentoExcluir = planilhaTecEquipamentoExcluir;
	}

	public Boolean getDesabilitaCampo() {
		return desabilitaCampo;
	}

	public void setDesabilitaCampo(Boolean desabilitaCampo) {
		this.desabilitaCampo = desabilitaCampo;
	}

	public PropostaHistorico getPropostaHistorico() {
		return propostaHistorico;
	}

	public void setPropostaHistorico(PropostaHistorico propostaHistorico) {
		this.propostaHistorico = propostaHistorico;
	}

	public Boolean getAcaoVisualHistorico() {
		return acaoVisualHistorico;
	}

	public void setAcaoVisualHistorico(Boolean acaoVisualHistorico) {
		this.acaoVisualHistorico = acaoVisualHistorico;
	}

	public List<CustoAdministrativoHistorico> getListCustoAdmHistory() {
		return listCustoAdmHistory;
	}

	public void setListCustoAdmHistory(List<CustoAdministrativoHistorico> listCustoAdmHistory) {
		this.listCustoAdmHistory = listCustoAdmHistory;
	}

	public List<CustoExecucaoHistorico> getListCustoExecucaoHistory() {
		return listCustoExecucaoHistory;
	}

	public void setListCustoExecucaoHistory(List<CustoExecucaoHistorico> listCustoExecucaoHistory) {
		this.listCustoExecucaoHistory = listCustoExecucaoHistory;
	}

	public List<CustoSegurancaHistorico> getListCustoSegurancaHistory() {
		return listCustoSegurancaHistory;
	}

	public void setListCustoSegurancaHistory(List<CustoSegurancaHistorico> listCustoSegurancaHistory) {
		this.listCustoSegurancaHistory = listCustoSegurancaHistory;
	}

	public List<CustoDeslocamentoHistorico> getListDeslocamentoHistory() {
		return listDeslocamentoHistory;
	}

	public void setListDeslocamentoHistory(List<CustoDeslocamentoHistorico> listDeslocamentoHistory) {
		this.listDeslocamentoHistory = listDeslocamentoHistory;
	}

	public List<CustoOperacionalHistorico> getListOperacionalHistory() {
		return listOperacionalHistory;
	}

	public void setListOperacionalHistory(List<CustoOperacionalHistorico> listOperacionalHistory) {
		this.listOperacionalHistory = listOperacionalHistory;
	}
	
	public void confirmaEmissao(Long valor){
		this.tipoProposta = valor;
		RequestContext context = RequestContext.getCurrentInstance();
		if(!"Emitida".equalsIgnoreCase(this.proposta.getStatusProposta())){
			context.execute("PF('dialogEmiteProposta').show();");
		}else{
			context.execute("PF('dialogNaoPermiteEmissao').show();");
		}
	}
	
	public void emitirPropostaGerada(){
		
		this.proposta.setStatusProposta("Emitida");
		if(this.tipoProposta.equals(1L)){
			setPropostaTecEmitida(false);
			setPropostaFinaceiraEmitida(true);
			setPropostaTecFinancEmitida(false);
			
			if(this.proposta.getDtEmissPropFinc() == null){
				this.proposta.setDtEmissPropFinc(new Date());
			}
			
		}else if (this.tipoProposta.equals(2L)){
			setPropostaTecEmitida(true);
			setPropostaFinaceiraEmitida(false);
			setPropostaTecFinancEmitida(false);
			if(this.proposta.getDtEmissPropTec() == null){
				this.proposta.setDtEmissPropTec(new Date());
			}
		}else{
			setPropostaTecEmitida(false);
			setPropostaFinaceiraEmitida(false);
			setPropostaTecFinancEmitida(true);
			
			if(this.proposta.getDtEmissPropTecFinac() == null){
				this.proposta.setDtEmissPropTecFinac(new Date());
			}
		}
		this.proposta.setDtEmissaoGeral(new Date());
		viewProposta = true;
		salvarPrecificacao();
		this.tipoProposta = null;
	}
	
	

	public Boolean getPropostaTecEmitida() {
		return propostaTecEmitida;
	}

	public void setPropostaTecEmitida(Boolean propostaTecEmitida) {
		this.propostaTecEmitida = propostaTecEmitida;
	}

	public Boolean getPropostaFinaceiraEmitida() {
		return propostaFinaceiraEmitida;
	}

	public void setPropostaFinaceiraEmitida(Boolean propostaFinaceiraEmitida) {
		this.propostaFinaceiraEmitida = propostaFinaceiraEmitida;
	}

	public Boolean getPropostaTecFinancEmitida() {
		return propostaTecFinancEmitida;
	}

	public void setPropostaTecFinancEmitida(Boolean propostaTecFinancEmitida) {
		this.propostaTecFinancEmitida = propostaTecFinancEmitida;
	}

	public Boolean getVisualizarProFinanc() {
		return visualizarProFinanc;
	}

	public void setVisualizarProFinanc(Boolean visualizarProFinanc) {
		this.visualizarProFinanc = visualizarProFinanc;
	}

	public Boolean getVisualizarProTec() {
		return visualizarProTec;
	}

	public void setVisualizarProTec(Boolean visualizarProTec) {
		this.visualizarProTec = visualizarProTec;
	}

	public Boolean getVisualizarPrTecFinac() {
		return visualizarPrTecFinac;
	}

	public void setVisualizarPrTecFinac(Boolean visualizarPrTecFinac) {
		this.visualizarPrTecFinac = visualizarPrTecFinac;
	}

	public Long getTipoProposta() {
		return tipoProposta;
	}

	public void setTipoProposta(Long tipoProposta) {
		this.tipoProposta = tipoProposta;
	}

	public String getMotivoExclusao() {
		return motivoExclusao;
	}

	public void setMotivoExclusao(String motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}

	public Boolean getVizualizaModalMotivo() {
		return vizualizaModalMotivo;
	}

	public void setVizualizaModalMotivo(Boolean vizualizaModalMotivo) {
		this.vizualizaModalMotivo = vizualizaModalMotivo;
	}

	public Boolean getLiberaFormaPagto() {
		return liberaFormaPagto;
	}

	public void setLiberaFormaPagto(Boolean liberaFormaPagto) {
		this.liberaFormaPagto = liberaFormaPagto;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<Parcela> getListParcelaContrato() {
		return listParcelaContrato;
	}

	public void setListParcelaContrato(List<Parcela> listParcelaContrato) {
		this.listParcelaContrato = listParcelaContrato;
	}
	
	//Edita e confirma uma lista dinamicamente a lista de parcelas
   public void onRowEdit(RowEditEvent event) {
	   FacesMessage msg;
	   msg = new FacesMessage("Parcela nº " + Integer.toString(((Parcela) event.getObject()).getNrParcela()), " Editada com sucesso");
       FacesContext.getCurrentInstance().addMessage(null, msg);
    }
		
   //Cancela a edição de parcelas do contrato dinamicamente
	 public void onRowCancel(RowEditEvent event) {
		FacesMessage msg;
			msg = new FacesMessage("Edicação da parcela nº " + Integer.toString(((Parcela) event.getObject()).getNrParcela())," Cancelada");
			FacesContext.getCurrentInstance().addMessage(null, msg);
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
								itemTemp.setIdEntidade(this.proposta.getId());
								itemTemp.setTipo(ETipoEntidade.PROPOSTA.getFlag());
								anexos.add(itemTemp);
							}
						}
						 
					}
				}
			
				FacesUtil.addInfoMessage("Anexo excluído com sucesso.");
		}
		
		public static String obterCaminhoAnexo() {
			return "C:\\DADOS_SISTEMA\\AGREGA\\ANEXOS\\PROPOSTA\\";
		}
		
		public List<Anexo> preencherListaAnexos() {
			if(this.proposta != null && this.proposta.getId() != null){
				return anexos = anexoService.getByIdByTipo(ETipoEntidade.PROPOSTA.getFlag(), this.proposta.getId(), null);
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
				
				if(anexoService.getByIdByTipo(ETipoEntidade.PROPOSTA.getFlag(), this.proposta.getId(), uploadedFile.getFile().getFileName()).size() >0){
					FacesUtil.addErrorMessage("Não é possível salvar o anexo "+ uploadedFile.getFile().getFileName() +". O mesmo já existe no banco de dados.");
					return;
				}
			    File file = new File(Constante.PROPOSTA, uploadedFile.getFile().getFileName());
			 
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
				FacesUtil.addInfoMessage("O arquivos anexado e salva com sucesso!");
			    
			    
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
						if(anexoService.getByIdByTipo(ETipoEntidade.PROPOSTA.getFlag(), this.proposta.getId(), arquivoAnexo.getFile().getFileName()).size()==0){
							anexo = new Anexo();
							anexo.setDescricao(arquivoAnexo.getFile().getFileName());
							anexo.setIdEntidade(this.proposta.getId());
							anexo.setTipo(ETipoEntidade.PROPOSTA.getFlag());
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

		public Proposta getPropostaModelo() {
			return propostaModelo;
		}

		public void setPropostaModelo(Proposta propostaModelo) {
			this.propostaModelo = propostaModelo;
		}
		

}
