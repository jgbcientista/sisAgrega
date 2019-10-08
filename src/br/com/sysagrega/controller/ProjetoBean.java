package br.com.sysagrega.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ibm.icu.text.SimpleDateFormat;

import br.com.sysagrega.controller.Qualificadores.QualificadorOrdemServico;
import br.com.sysagrega.model.ICaracteristicaProjeto;
import br.com.sysagrega.model.IContrato;
import br.com.sysagrega.model.ICoordenada;
import br.com.sysagrega.model.IFaturamento;
import br.com.sysagrega.model.IOrdemServico;
import br.com.sysagrega.model.IParcelaProjeto;
import br.com.sysagrega.model.IPlanejamentos;
import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.IProjeto;
import br.com.sysagrega.model.IRegistro;
import br.com.sysagrega.model.IRetificacao;
import br.com.sysagrega.model.IVerificacaoProfissional;
import br.com.sysagrega.model.Enums.ECaracteristicaIntervencao;
import br.com.sysagrega.model.Enums.ECaracteristicaMapas;
import br.com.sysagrega.model.Enums.ECaracteristicaRelatorio;
import br.com.sysagrega.model.Enums.ECaracteristicaUnidConservacao;
import br.com.sysagrega.model.Enums.ESituacao;
import br.com.sysagrega.model.Enums.ETipoEntidade;
import br.com.sysagrega.model.Enums.ETipoProjeto;
import br.com.sysagrega.model.Enums.ETipoStatus;
import br.com.sysagrega.model.Enums.ETipoSubProjeto;
import br.com.sysagrega.model.Enums.SituacaoPlanejamento;
import br.com.sysagrega.model.imp.Anexo;
import br.com.sysagrega.model.imp.CaracteristicaProjeto;
import br.com.sysagrega.model.imp.Cidade;
import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.model.imp.Contrato;
import br.com.sysagrega.model.imp.Coordenada;
import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.model.imp.Faturamento;
import br.com.sysagrega.model.imp.MacroRegiao;
import br.com.sysagrega.model.imp.Mensagem;
import br.com.sysagrega.model.imp.MicroRegiao;
import br.com.sysagrega.model.imp.MotivoRetificacao;
import br.com.sysagrega.model.imp.Municipio;
import br.com.sysagrega.model.imp.Notificacao;
import br.com.sysagrega.model.imp.OrdemServico;
import br.com.sysagrega.model.imp.Parcela;
import br.com.sysagrega.model.imp.ParcelaProjeto;
import br.com.sysagrega.model.imp.Planejamentos;
import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.model.imp.Projeto;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.model.imp.Regiao;
import br.com.sysagrega.model.imp.Registro;
import br.com.sysagrega.model.imp.Retificacao;
import br.com.sysagrega.model.imp.Situacao;
import br.com.sysagrega.model.imp.Status;
import br.com.sysagrega.model.imp.TipoNotificacao;
import br.com.sysagrega.model.imp.TipoPrograma;
import br.com.sysagrega.model.imp.TipoRetificacao;
import br.com.sysagrega.model.imp.TrajetoRede;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.model.imp.VerificacaoProfissional;
import br.com.sysagrega.service.IAnexoService;
import br.com.sysagrega.service.ICaracteristicaProjetoService;
import br.com.sysagrega.service.IClienteService;
import br.com.sysagrega.service.IContratoService;
import br.com.sysagrega.service.ICoordenadaService;
import br.com.sysagrega.service.IEstadoService;
import br.com.sysagrega.service.IFaturamentoService;
import br.com.sysagrega.service.IMacroRegiaoService;
import br.com.sysagrega.service.IMensagemService;
import br.com.sysagrega.service.IMicroRegiaoService;
import br.com.sysagrega.service.IMunicipioService;
import br.com.sysagrega.service.IOrdemServicoService;
import br.com.sysagrega.service.IPlanejamentosService;
import br.com.sysagrega.service.IProfissionalService;
import br.com.sysagrega.service.IProjetoService;
import br.com.sysagrega.service.IRegiaoService;
import br.com.sysagrega.service.IRetificacaoService;
import br.com.sysagrega.service.ISituacaoService;
import br.com.sysagrega.service.IStatusService;
import br.com.sysagrega.service.ITipoProgramaService;
import br.com.sysagrega.service.ITrajetoRedeService;
import br.com.sysagrega.service.IVerificacaoProfissionalService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.service.imp.NotificacaoService;
import br.com.sysagrega.service.imp.RegistroService;
import br.com.sysagrega.service.imp.TipoNotificacaoService;
import br.com.sysagrega.service.imp.TipoRetificacaoService;
import br.com.sysagrega.service.imp.UsuarioService;
import br.com.sysagrega.util.Constante;
import br.com.sysagrega.util.DateUtil;
import br.com.sysagrega.util.EnviarEmail;
import br.com.sysagrega.util.RelatorioUtil;
import br.com.sysagrega.util.jsf.FacesUtil;
import br.com.sysagrega.util.jsf.PlanejamentoVO;

@Named
@ManagedBean
@ViewScoped
public class ProjetoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IClienteService clienteService;
	@Inject
	private IOrdemServicoService ordemServicoService;
	@Inject
	private IProjetoService projetoService;
	@Inject
	private ISituacaoService situacaoService;
	@Inject
	private IProfissionalService profissionalService;
	@Inject
	private IEstadoService estadoService;
	@Inject
	private IMacroRegiaoService macroRegiaoService;
	@Inject
	private IMicroRegiaoService microRegiaoService;
	@Inject
	private IMunicipioService municipioService;
	@Inject
	private IRegiaoService regiaoService;
	@Inject
	private ICaracteristicaProjetoService caracteristicasProjetoService;
	@Inject
	private IContratoService contratoService;
	@Inject
	private IPlanejamentosService planejamentosService;
	@Inject
	private ICoordenadaService coordenadaService;
	@Inject
	private ITrajetoRedeService trajetoRedeService;
	@Inject
	private IVerificacaoProfissionalService verificacaoProfissionalService;
	@Inject
	private IRetificacaoService retificacaoService;
	@Inject
	private IFaturamentoService faturamentoService;
	@Inject
	private IStatusService statusService;
	@Inject
	private ITipoProgramaService tipoProgramaService;
	@Inject
	private NotificacaoService notificacaoService;
	@Inject
	private RegistroService registroService;
	@Inject
	private TipoNotificacaoService tipoNotificacaoService;
	@Inject
	private TipoRetificacaoService tipoRetificacaoService;
	@Inject
	private UsuarioService usuarioService;
	@Inject
	private IAnexoService anexoService;
	@Inject
	private IMensagemService mensagemService;

	private final Long CONSTANTE_SEGUNDA_PARCELA = 50L;
	private final Long CONSTANTE_TERCEIRA_PARCELA = 20L;
	private final Long CONSTANTE_PRIMEIRA_PARCELA = 30L;

	@Produces
	@QualificadorOrdemServico
	private IProjeto projeto;
	private IProfissional profissional;
	private IOrdemServico ordemServico;
	private String cliente;
	private Double saldoContrato;
	private String formaDePagamentocontrato;
	private String labelOS;
	private List<Profissional> comboProfisssional;
	private List<String> comboTipoProjeto;
	private List<TipoNotificacao> comboTipoNotificacao;
	private List<TipoPrograma> comboTipoPrograma;
	private List<Planejamentos> comboPlanejamentos;
	private List<String> idsPlanejamentos;
	private List<Status> comboStatus;
	private List<Status> comboStatusProjeto;
	private List<Situacao> comboSituacoes;
	private List<Situacao> comboSituacoesFiltro;
	private List<Situacao> comboSituacoesConfirmarPagamento;
	private List<ETipoSubProjeto> comboSubTipoProjeto;
	private List<Estado> comboEstado;
	private List<Cidade> comboCidade;
	private List<Regiao> comboRegiao;
	private List<Contrato> comboContrato;
	private List<OrdemServico> comboOrdemServico;
	private List<OrdemServico> listOSNovoProjeto;
	private Long idContratoNovoProjeto;
	private List<MacroRegiao> comboMacroRegiao;
	private List<Municipio> comboMunicipio;
	private List<String> selectedMotivo;
	private List<MotivoRetificacao> listMotivo;
	private List<Projeto> listarProjetosByOS;
	private List<Projeto> comboProjetos;
	private List<Cliente> comboClientes;
	private List<Projeto> projetos;
	private List<TipoRetificacao> comboTipoRetificacao;
	private List<Situacao> comboStatusProjetoPesquisa;
	private List<MacroRegiao> comboMacroRegiaoPesuisar;
	private List<MicroRegiao> comboMicroRegiaoPesuisar;
	private Long filtroMacroRegiao;
	private Long filtroEstado;
	private Long filtroMunicipio;
	private String tituloDataTable;
	private Long idTipoRetificacao;
	private Long idStatusProjeto;
	private Boolean novo = false;
	private Boolean pesquisar = true;
	private Boolean visualizar = false;
	private Boolean editar = false;
	private Boolean disableSubProjeto = true;
	private Boolean disableCity = true;
	private Boolean disableMacroRegiao = true;
	private Boolean disableMunicipio = true;
	private Boolean ocultarFiltroProjeto = false;
	private Boolean cadastrarOS = false;
	private Boolean cadastrarProjeto = false;
	private Boolean abrirEditarOS = false;
	private Long idProfissionalGestor;
	private Long idPlanejamento;
	private Faturamento faturamento;
	private Long idProfissionalResposanvelResposta;
	private Boolean estudoESV = false;
	private Boolean viewProjeto;
	private Boolean visualizarProjeto = false;
	private Boolean visualizarOrdemServico = false;
	private Boolean exibirListPorOS;
	private Boolean exibirListPorProjeto;
	private Boolean exibirBtRetificacao = false;
	private Cliente filtroCliente;
	private Long filtroProjeto;
	private String filtroProjetoAgrega = null;
	private String filtroProjetoCliente = null;
	private String filtroNomeProjeto = null;
	private String filtroAnoProjeto = null;
	private String filtroSubProjeto = null;
	private Long PRIMEIRA_PARCELA_PROJETO = 30L;
	private Long numero;
	private String novaNrOS;
	private Integer qtdCadastradaProjeto = 0;
	private Integer qtdFaturado = 0;
	private Double valorFaturado = 0.0;
	private Integer qtdRestanteACadastrar;
	private String selectedPesquisarPor;
	private Long filtroStatus;
	private IVerificacaoProfissional verificacao;
	private IVerificacaoProfissional verificacaoSalvar;
	private Boolean disablePlanejamento;
	private Long idResponsavelFaturamento;
	private Long fusoInformado;
	private List<Coordenada> listCoordenada;
	private Coordenada coordenada;
	private IFaturamento fatSalvar;
	private List<TrajetoRede> listTrajetoRede;
	private List<Projeto> listarProjetosByFiltro;
	private List<Projeto> filterProjetos;
	private List<Projeto> projetosSelecionados;
	private List<OrdemServico> listarOssByFiltro;
	private List<OrdemServico> listaOS;
	private Boolean btAtualizarPlanejamento = false;
	private Boolean btConfirmarPlanejamento = false;
	private Boolean ocultaBt = true;
	private ICaracteristicaProjeto caracteristicasProjeto;
	private Long numeroParcela;
	private Double valorParcela;
	private Date dataCobranca;
	private Long notaFiscal;
	private String observacaoParcela;
	private Boolean quitacaoTotal;
	private List<IParcelaProjeto> listParcelaProjeto;
	private List<Faturamento> listarFaturamentosByProjeto;
	private IParcelaProjeto parcelaProjeto;
	private List<Retificacao> listRetificacao;
	private IRetificacao retificacao;
	private Boolean houveRetificacao;
	private Projeto filtro;
	private Contrato filtroContrato;
	private OrdemServico filtroNumeroOS;
	private Double valorTotalFaturar = 0.0;
	private Long filtroSituacao;
	private Long filtroSituacaoFaturar;
	private List<OrdemServico> ordemServicos;
	private List<OrdemServico> ordensServicosSelecionados;
	private List<String> listaDeIdOS;
	private List<String> listaDeIdContrato;
	private String primariaKmString;
	private String secundariaKmString;
	private String tensaoPrimariaKvString;
	private String conjugadaKmString;
	private String tensaoSecundariaKVString;
	private String distanciaSedeMunPavString;
	private String distanciaSedeMunNaoPavString;
	private String distanciaMunLocalProjPavString;
	private String distanciaMunLocalProjNaoPavString;
	private TreeNode rootRelatorio;
	private TreeNode rootMapas;
	private TreeNode rootUnidadeConservacao;
	private TreeNode rootIntervencao;
	private TreeNode[] selectedRelatorioNodes;
	private TreeNode[] selectedMapasNodes;
	private TreeNode[] selectedUnidadeConservacaoNodes;
	private TreeNode[] selectedIntervecaoNodes;
	private List<String> listCaracteristicas;
	private Long idProResponsavel;
	private Long idProMap;
	private Long idProCtgal;
	private Long idProfVerificacao;
	private Long idProfEPI;
	private Long idProMapConclusao;
	private Long idProCtgalConclusao;
	private Long idProfVerificacaoConclusao;
	private Long idProfEPIConclusao;
	private Date dataEntrega;
	private Date dataEntradaLocal;
	private Boolean opPlanejar = false;
	private Boolean opFaturar = false;
	private Long opEscolhida;
	private Long idStatusRegistro;
	private Date filtroDataInicial;
	private Date filtroDataFinal;
	private Double vlrProjeto;
	private Double VALOR_KM_PAVIMENTADA;
	private Double VALOR_KM_NAO_PAVIMENTDA;
	private Double VALOR_BASE_ESTUDO;
	private Double fator1;
	private Long idStatus;
	private Long idMapas;
	private Long idEPI;
	private Long idEvctga;
	private Long idVerificacao;
	private Date plInicio;
	private Date plFim;
	private Date plEntrega;
	private String btProjetos = "Cancelar";
	private IRegistro registoSalvar;
	private Notificacao notificacao;
	private List<Notificacao> listarNotificacao;
	private boolean gerouDae;
	private boolean gerouNotificacao;
	private boolean oculparCampos = false;
	private Coordenada coordenadaExcluir;
	private TrajetoRede trajetoExcluir;
	private TrajetoRede trajetoRede;
	private boolean exibitBotaoNovaOS;
	private boolean jaAlterado = true;
	private Long sitPlanejamento;
	private int currentTab = 0;
	private List<SituacaoPlanejamento> listSitPlanejamento;
	private List<Projeto> verProjetosByOS = null;
	private Long planejemanetoAtual;
	private String labelPesquisaData = "Data de entrada do Projeto ";
	private Boolean checkPlanejarFaturar = false;
	private Boolean planejar = false;
	private Boolean faturar;
	private List<Projeto> listProjetosPlanejar;
	private Boolean pagPlanejando = false;
	private List<String> nrProjetoAgrega;
	private List<Projeto> planejamentoSelecionados;
	private IPlanejamentos planejamentos;
	private Long planSeleect;
	private IPlanejamentos planSelecionado;
	private String tituloPlan;
	private Boolean validaBotaoPlan = true;
	private Boolean validaBotaoNovoPlan = true;
	private Boolean habilitaPlanejamento = false;
	private String vlrColuna = "1350px";

	@ManagedProperty(value = "#{menuBean.usuario}")
	private Usuario usuarioLogado;

	// Treee Relatório
	TreeNode epi, ev, ctgaSeia, ro, casaEmApp, tamLinhaDiferente, obraConstruida, vistoriaNaoREalizada, outros, diadSeia, 
	rca, seia, areaLago, faixaMarginal, topoMorro ;
	// Tree Mapas
	TreeNode localizacao, hidrografia, undDeConsevacao, vegetacao, leiMataAtlantica, areaEspecial, assentamento, terraIndigina, quilombola;
	// Tree Unidade
	TreeNode federal, icmbio, riuc, estadual, municipal, particular;

	Comparator<Projeto> comparadorProjeto = new Comparator<Projeto>() {
		public int compare(Projeto s1, Projeto s2) {
			return Integer.compare(Integer.parseInt(s1.getId().toString()), Integer.parseInt(s2.getId().toString()));
		}
	};
	Comparator<Planejamentos> comparadorPlanejamentos = new Comparator<Planejamentos>() {
		public int compare(Planejamentos s1, Planejamentos s2) {
			return Integer.compare(s1.getNrPlan(), s2.getNrPlan());
		}
	};

	@PostConstruct
	public void inicializar() {
		listaOS = ordemServicoService.getAll();
		setLabelOS("Valor Contrato");
		disablePlanejamento = true;
		quitacaoTotal = false;
		filtroProjetoAgrega = null;
		filtroSubProjeto = null;
		instanciarObjetos();
		carregaCombos();
		planejar = false;
		exibirListPorProjeto = false;
		selectedPesquisarPor = "PJ";
		checkPlanejarFaturar = true;
		exibirListPorOS = false;
		exibitBotaoNovaOS = false;
		filtro = new Projeto();
		filtro.setStatus(new Status());
		filtroCliente = new Cliente();
		filtroContrato = new Contrato();
		filtroNumeroOS = new OrdemServico();
		filtroNumeroOS.setContrato(new Contrato());
		rootRelatorio = createCheckboxRelatorio("Relatorio");
		rootMapas = createCheckboxRelatorio("Mapas");
		rootUnidadeConservacao = createCheckboxRelatorio("Unidade");
		rootIntervencao = createCheckboxRelatorio("Intervenção");
		fusoInformado = null;
		instaciarVerificacao();
		projetosSelecionados = new ArrayList<>();
		faturamento = new Faturamento();
		faturamento.setProjeto(new Projeto());
		faturamento.setResponsavel(new Profissional());
		listarFaturamentosByProjeto = new ArrayList<>();
		filtro = new Projeto();
		filtro.setStatus(new Status());
		registoSalvar = new Registro();
		notificacao = new Notificacao();

		if (projeto == null) {
			projeto = new Projeto();
		}
		if (projeto.getStatus() == null) {
			projeto.setStatus(new Status());
		}
		comboPlanejamentos = planejamentosService.getAll();
		comboPlanejamentos.sort(comparadorPlanejamentos);
		obterUsuarioLogado();
		comboTipoPrograma = tipoProgramaService.getAll();
		listSitPlanejamento = new ArrayList<>();
		carregarSituacaoPlanejamento();
		planSelecionado = new Planejamentos();
	}

	public void onTabChange(org.primefaces.event.TabChangeEvent event) {
	    String id = event.getTab().getId();
	    if (id.equals("tab0")) {
	        this.setCurrentTab(0);
	    } else if (id.equals("tab1")) {
	        this.setCurrentTab(1);
	    } else if (id.equals("tab2")) {
	        this.setCurrentTab(2);
	    } else if (id.equals("tab3")) {
	        this.setCurrentTab(3);
	    } else if (id.equals("tab4")) {
	        this.setCurrentTab(4);
	    } else if (id.equals("tab5")) {
	        this.setCurrentTab(5);
	    } else if (id.equals("tab6")) {
	        this.setCurrentTab(6);
	    } else if (id.equals("tab7")) {
	        this.setCurrentTab(7);
	    }
	}

	public void verificarModalidade(Long parametro){
		if(parametro == 1L){
			oculparCampos = false;
		}else if(parametro == 2L){
			oculparCampos = true;
		}else{
			FacesUtil.addErrorMessage("Por favor, selecione a modalidade de cálculo.");	
		}
	}

	private void carregarSituacaoPlanejamento() {
		for (SituacaoPlanejamento seg : SituacaoPlanejamento.values()) {
			listSitPlanejamento.add(seg);
		}
	}

	public void tualizarContratoByOS(Long idOS) {
		if (idOS != null) {
			ordemServico = ordemServicoService.getById(idOS);
		}
	}
	//TODO
	public void dispararEmail(Long tipo) {
		String assunto = "[AGREGA - Integra informa para tomada de ação]";
		String corpo = null;

		if (tipo.equals(1L)) {
			corpo = "Prezado usuário, \n\n Os dados de coordenadas foram registradas no projeto agrega de código "
					+ projeto.getSubTipoProjeto() + " " + projeto.getNumeroProjetoAgrega() + ".\n\n"
					+ " Atenciosamente,\n\n Diretoria da Agrega Consultores \n\n";
		/*
		 * Dados de Campo do Projeto.
		 */
		} else 	if (tipo.equals(2L)) {
			corpo = "Prezado usuário, \n\n O projeto agrega de código " + projeto.getSubTipoProjeto() + " "
					+ projeto.getNumeroProjetoAgrega() + ". Foram direcionados para você.\n\n"
					+ " Atenciosamente,\n\n Diretoria da Agrega Consultores \n\n";
		/*
		 * Caracteristicas do Projeto
		 */
		}else if (tipo.equals(3L)) {
			corpo = "Prezado usuário, \n\n O projeto agrega de código " + projeto.getSubTipoProjeto() + " "
					+ projeto.getNumeroProjetoAgrega() + ". As caractéristicas foram preenchidas e direcionados para você.\n\n"
					+ " Atenciosamente,\n\n Diretoria da Agrega Consultores \n\n";
		}
		String destinatario = "";
		String destinatariosInvalidos = "";
		boolean api = false, evctgal = false, mapa = false, verificacao = false;
		List<IProfissional> listaEnvolvidos = new ArrayList<>();
		if (projeto.getProfEpi() != null && projeto.getProfEpi().getEmail() != null) {
			if (EnviarEmail.validarEmail(projeto.getProfEpi().getEmail())) {
				destinatario = destinatario + projeto.getProfEpi().getEmail();
			} else {
				destinatariosInvalidos = destinatariosInvalidos + " EPI ";
			}
			api = true;
			listaEnvolvidos.add(projeto.getProfEpi());
		}
		if (projeto.getProfEvctgal() != null && projeto.getProfEvctgal().getEmail() != null) {
			if (EnviarEmail.validarEmail(projeto.getProfEvctgal().getEmail())) {
				if (api) {
					destinatario = destinatario + ", ";
				}
				destinatario = destinatario + projeto.getProfEvctgal().getEmail();
			} else {
				destinatariosInvalidos = destinatariosInvalidos + " EV/CTGAl ";
			}
			evctgal = true;
			listaEnvolvidos.add(projeto.getProfEvctgal());
		}
		if (projeto.getProfMapas() != null && projeto.getProfMapas().getEmail() != null) {
			if (EnviarEmail.validarEmail(projeto.getProfMapas().getEmail())) {
				if (api || evctgal) {
					destinatario = destinatario + ", ";
				}
				destinatario = destinatario + projeto.getProfMapas().getEmail();
			} else {
				destinatariosInvalidos = destinatariosInvalidos + " Mapas ";
			}
			mapa = true;
			listaEnvolvidos.add(projeto.getProfMapas());
		}
		if (projeto.getProfVerif() != null && projeto.getProfVerif().getEmail() != null) {
			if (EnviarEmail.validarEmail(projeto.getProfMapas().getEmail())) {
				if (api || evctgal || mapa) {
					destinatario = destinatario + ", ";
				}
				destinatario = destinatario + projeto.getProfVerif().getEmail();
			} else {
				destinatariosInvalidos = destinatariosInvalidos + " Verificação ";
			}
			verificacao = true;
			listaEnvolvidos.add(projeto.getProfVerif());
		}

		if (!destinatario.equals("")) {
			/*
			 * Salva a mensagem no banco de dados.
			 */
			Mensagem mensagem = null;
			for (IProfissional profissionalEnvolvido : listaEnvolvidos) {
				mensagem = null;
				if (mensagemService.obterByProjetoByProfissional(projeto.getId(), profissionalEnvolvido.getId(), 1L)
						.size() > 0) {
					mensagem = mensagemService
							.obterByProjetoByProfissional(projeto.getId(), profissionalEnvolvido.getId(), 1L).get(0);
				}

				if (mensagem == null) {
					mensagem = new Mensagem();
					mensagem.setDataInclusao(new Date());
					mensagem.setContador(1L);
				} else {
					mensagem.setContador(mensagem.getContador() + 1L);
					mensagem.setDataAlteracao(new Date());
				}
				mensagem.setProjeto(projetoService.getPorID(projeto.getId()));
				mensagem.setDescricao(corpo);
				mensagem.setLida(false);
				mensagem.setTipo(tipo); // 1- Coordenadas | 2 - Planejamento.
				mensagem.setTitulo(assunto);

				if (profissionalEnvolvido.getUsuarioAcesso() != null) {
					mensagem.setProfissional(
							profissionalService.obterProfissionalByLogin(profissionalEnvolvido.getUsuarioAcesso()));
					mensagemService.salvar(mensagem);
				}
			}
			if (destinatariosInvalidos != "") {
				EnviarEmail.informaEmailsInvalidos("E-mail de profissional:  " + destinatariosInvalidos + " invalido.");
			}
			EnviarEmail.enviarEmail(assunto, corpo, destinatario);
			/*
			 * FacesUtil.addInfoMessage(
			 * "E-mails enviados com sucesso para as partes interessadas.");
			 */
		} else {
			FacesUtil.addErrorMessage("Não foi possível enviar os e-mails para as partes interessadas.");
		}

	}

	public void obterUsuarioLogado() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context instanceof SecurityContext) {
			Authentication authentication = context.getAuthentication();
			if (authentication instanceof Authentication) {
				usuarioLogado = usuarioService.getUserPerfilByLogin(authentication.getName());
			}
		}
	}

	public void atualizarProjetos() {
		List<Projeto> listaProjeto = projetoService.getAll();
		for (Projeto p : listaProjeto) {
			if (p.getCaracteristicasProjeto() != null && p.getCaracteristicasProjeto().getId() != null) {
				CaracteristicaProjeto itemSalvar = caracteristicasProjetoService.getById(p.getCaracteristicasProjeto().getId());
				itemSalvar.setProjeto(p);
				caracteristicasProjetoService.salvar(itemSalvar);
			}
		}
		FacesUtil.addInfoMessage("Registros atualizados com sucesso.");
	}

	public void obterOSsByContrato(List<String> idSContratos) {
		if (idSContratos != null) {
			comboOrdemServico = new ArrayList<>();
			comboOrdemServico.addAll(obterListOS(idSContratos));
		}
	}
	
	public void obterOSNovoProjeto (Long idSContratos){
		listOSNovoProjeto = new ArrayList<>();
		if (idSContratos != null) {
			List<OrdemServico> listTodasOS = ordemServicoService.getAll();
			for (OrdemServico os : listTodasOS) {
				if (os.getContrato().getId().equals(idSContratos)) {
					listOSNovoProjeto.add(os);
				}
			}
		}
	}
	
	public List<OrdemServico> obterListOS(List<String> idSContratos){
		List<OrdemServico> listTodasOS = ordemServicoService.getAll();
		List<OrdemServico> list = new ArrayList<>();
		for (String item : idSContratos) {
			for (OrdemServico os : listTodasOS) {
				if (os.getContrato().getId().equals(Long.parseLong(item))) {
					list.add(os);
				}
			}
		}
		return list;
	}

	public String obterProfissionalByID(Long idProf) {
		if (idProf != null) {
			return profissionalService.getById(idProf).getNome();
		}
		return "-";
	}

	public String obterProfissionalByIDConcat(Long idProf) {
		if (idProf != null) {
			return concatNome2(profissionalService.getById(idProf).getNome());
		}
		return null;
	}

	public String concatNome(String param) {
		String nome = param;
		if (nome.length() > 20) {
			nome = nome.substring(0, 20) + "...";
		}
		return nome;
	}

	public String concatNome2(String param) {
		String nome = param;
		if (!nome.equals("--")) {
			nome = nome.substring(0, nome.indexOf(" "));
		}
		return nome;
	}

	public void cancelarPlanjamento() {
		projetosSelecionados = null;
		ocultarFiltroProjeto = true;
		filtro = new Projeto();
		filtro.setStatus(new Status());
		listarOssByFiltro = null;
		listarProjetosByFiltro = null;
		projetosSelecionados = null;
		FacesUtil.addErrorMessage("Todos os campos do planejamento foram limpos.");
	}
	
	public Double novoCalculoValorProjeto(){
		Double vlrBase = 3020.00;
		Double vlrProjeto = 0.0;
		if(projeto.getExtensaoTotalKm() != null && projeto.getExtensaoTotalKm() != null 
			&& projeto.getMunicipio() != null && projeto.getMunicipio().getRegiao() != null
			&& projeto.getMunicipio().getRegiao().getKm() != null){
			if(projeto.getExtensaoTotalKm()<1){
				vlrProjeto = 0.0;
			}else if(projeto.getExtensaoTotalKm()<5){
				vlrProjeto = ((projeto.getExtensaoTotalKm()-1)*0.1)*vlrBase;
			}else{
				vlrProjeto = (4.0*0.1*vlrBase)+(projeto.getExtensaoTotalKm()-5)*0.05*vlrBase;
			}
			vlrProjeto = (vlrBase + vlrProjeto) + projeto.getMunicipio().getRegiao().getKm()*2.30;
		}
		return vlrProjeto;
	}

	public Double calculaValorProjeto() {
		vlrProjeto = 0.0;
		// if(distanciaSedeMunPavString != null &&
		// !"".equals(distanciaSedeMunPavString)){
		if (distanciaSedeMunPavString != null && !distanciaSedeMunPavString.isEmpty()) {
			distanciaSedeMunPavString = distanciaSedeMunPavString.replace(",", ".");
			projeto.setDistanciaSedeMunPav(Double.parseDouble(distanciaSedeMunPavString));
		}
		if (distanciaSedeMunNaoPavString != null && !distanciaSedeMunNaoPavString.isEmpty()) {
			distanciaSedeMunNaoPavString = distanciaSedeMunNaoPavString.replace(",", ".");
			projeto.setDistanciaSedeMunNaoPav(Double.parseDouble(distanciaSedeMunNaoPavString));
		}

		if (distanciaMunLocalProjPavString != null && !distanciaMunLocalProjPavString.isEmpty()) {
			distanciaMunLocalProjPavString = distanciaMunLocalProjPavString.replace(",", ".");
			projeto.setDistanciaMunLocalProjPav(Double.parseDouble(distanciaMunLocalProjPavString));
		}
		if (distanciaMunLocalProjNaoPavString != null && !distanciaMunLocalProjNaoPavString.isEmpty()) {
			distanciaMunLocalProjNaoPavString = distanciaMunLocalProjNaoPavString.replace(",", ".");
			projeto.setDistanciaMunLocalProjNaoPav(Double.parseDouble(distanciaMunLocalProjNaoPavString));
		}

		if (this.projeto.getDistanciaSedeMunPav() != null && this.projeto.getDistanciaMunLocalProjPav() != null
				&& this.projeto.getDistanciaSedeMunNaoPav() != null
				&& this.projeto.getDistanciaMunLocalProjNaoPav() != null) {

			// Rever calculo
			if (projeto.getEp() != null && projeto.getEp()) {
				VALOR_BASE_ESTUDO = 2635.00;
			} else {
				VALOR_BASE_ESTUDO = 2617.50;
			}

			VALOR_KM_PAVIMENTADA = 1.40;
			VALOR_KM_NAO_PAVIMENTDA = 2.00;

			if (this.projeto.getTamanhoProjeto() < 1) {
				fator1 = 0.0;
			} else if (this.projeto.getTamanhoProjeto() > 1 && this.projeto.getTamanhoProjeto() <= 5) {
				fator1 = ((this.projeto.getTamanhoProjeto() - 1) * 0.1 * VALOR_BASE_ESTUDO);
			} else {
				fator1 = ((4.0 * 0.1 * VALOR_BASE_ESTUDO)
						+ (this.projeto.getTamanhoProjeto() - 5) * 0.05 * VALOR_BASE_ESTUDO);
			}
			vlrProjeto = 2 * (this.projeto.getDistanciaSedeMunPav() + this.projeto.getDistanciaMunLocalProjPav())
					* VALOR_KM_PAVIMENTADA
					+ 2 * (this.projeto.getDistanciaSedeMunNaoPav() + this.projeto.getDistanciaMunLocalProjNaoPav())
							* VALOR_KM_NAO_PAVIMENTDA
					+ VALOR_BASE_ESTUDO + fator1;
		}
		return vlrProjeto;
	}

	public void verificarGerouDae() {
		if (registoSalvar != null && registoSalvar.getGerouDAE() == null) {
			gerouDae = false;
		}
		if (registoSalvar != null && registoSalvar.getGerouDAE() != null && registoSalvar.getGerouDAE().equals(0L)) {
			gerouDae = true;
		} else if (registoSalvar != null && registoSalvar.getGerouDAE() != null
				&& registoSalvar.getGerouDAE().equals(1L)) {
			gerouDae = false;
		} else {
			gerouDae = false;
		}
		registoSalvar.setValorDAE(null);
		registoSalvar.setDataPagamento(null);
		registoSalvar.setDataEntrega(null);
	}

	public void verificarGerouDaeEdit() {
		if (registoSalvar != null && registoSalvar.getGerouDAE() == null) {
			gerouDae = false;
		}
		if (registoSalvar != null && registoSalvar.getGerouDAE() != null && registoSalvar.getGerouDAE().equals(0L)) {
			gerouDae = true;
		} else if (registoSalvar != null && registoSalvar.getGerouDAE() != null
				&& registoSalvar.getGerouDAE().equals(1L)) {
			gerouDae = false;
		} else {
			gerouDae = false;
		}
	}

	public void verificarPrazoPagamento() {
		String EMAIL_CORPO = "Corpo-Teste";
		String EMAIL_DESTINATARIO = "joao.brito@capgemini.com";
		EnviarEmail.enviarEmail("SGAC - Pagamento Pendente", EMAIL_CORPO, EMAIL_DESTINATARIO);
	}

	public void instaciarVerificacao() {
		verificacaoSalvar = new VerificacaoProfissional();
		verificacaoSalvar.setProfissionalEPI(new Profissional());
		verificacaoSalvar.setProfissionalEVCTGAL(new Profissional());
		verificacaoSalvar.setProfissionalMapas(new Profissional());
		verificacaoSalvar.setProfissionalVerificacao(new Profissional());
	}

	public List<Projeto> projetosByOS(OrdemServico ordemServico) {
		filtro = new Projeto();
		filtro.setOrdemServico(ordemServico);
		List<Projeto> verProjetos = new ArrayList<>();
		return verProjetos;
	}

	public void visualzarPlanejamento(OrdemServico ordemServico) {
		filtro = new Projeto();
		filtro.setStatus(new Status());
		filtro.setOrdemServico(ordemServico);
		List<Projeto> verProjetos = projetoService.getByFiltros(filtro);
		if (verProjetos == null) {
			verProjetos = new ArrayList<Projeto>();
		}
		verProjetosByOS = new ArrayList<>();
	}

	public void addProfissionalVazio() {
		if (idProfEPI != null) {
			this.projeto.setProfEpi(profissionalService.getById(idProfEPI));
		} else {
			projeto.setProfEpi(profissionalService.getById(50L)); // 50-
		}
		if (idProCtgal != null) {
			projeto.setProfEvctgal(profissionalService.getById(idProCtgal));
		} else {
			projeto.setProfEvctgal(profissionalService.getById(50L));
		}
		if (idProMap != null) {
			this.projeto.setProfMapas(profissionalService.getById(idProMap));
		} else {
			projeto.setProfMapas(profissionalService.getById(50L));
		}
		if (idProfVerificacao != null) {
			this.projeto.setProfVerif(profissionalService.getById(idProfVerificacao));
		} else {
			projeto.setProfVerif(profissionalService.getById(50L));
		}
	}

	// Verifica se foi alterado para criar revisão.
	public Boolean verificaItemAlterado() {
		Boolean itemAlterado = false;
		if (idMapas != this.projeto.getProfMapas().getId()) {
			idMapas = this.projeto.getProfMapas().getId();
			itemAlterado = true;
		} else if (idEPI != this.projeto.getProfEpi().getId()) {
			idEPI = this.projeto.getProfEpi().getId();
			itemAlterado = true;
		} else if (idEvctga != this.projeto.getProfEvctgal().getId()) {
			idEvctga = this.projeto.getProfEvctgal().getId();
			itemAlterado = true;
		} else if (idVerificacao != this.projeto.getProfVerif().getId()) {
			idVerificacao = this.projeto.getProfVerif().getId();
			itemAlterado = true;
		} else if (verificaSeFoiAleradaData()) {
			itemAlterado = true;
		}
		return itemAlterado;
	}

	// VErifica se a data do planejamento foi alterado
	public Boolean verificaSeFoiAleradaData() {
		String dtPlIniAtual = convertedata(plInicio);
		String dtPlIniNova = convertedata(this.projeto.getDtIniPlanj());
		String dtPlFimAtual = convertedata(plFim);
		String dtPlFimNova = convertedata(this.projeto.getDtFimPlanj());
		String dtPlEntregaAtual = convertedata(plEntrega);
		String dtPlEntregaNova = convertedata(this.projeto.getDtEntrega());

		if (!dtPlIniAtual.equalsIgnoreCase(dtPlIniNova)) {
			plInicio = this.projeto.getDtIniPlanj();
			return true;
		} else if (!dtPlFimAtual.equalsIgnoreCase(dtPlFimNova)) {
			plFim = this.projeto.getDtFimPlanj();
			return true;
		} else if (dtPlEntregaAtual != null && dtPlEntregaNova != null && !dtPlEntregaAtual.equalsIgnoreCase(dtPlEntregaNova)) {
			plEntrega = this.projeto.getDtEntrega();
			return true;
		} else {
			return false;
		}
	}

	// Converte a data Calendar em formato dd/MM/yyyy
	public String convertedata(Date vdata) {
		if(vdata != null){
			Date hoje = vdata;
			String formato = "dd/MM/yyyy";
			SimpleDateFormat formatter = new SimpleDateFormat(formato);
			return formatter.format(hoje);
		}
		return null;
	}

	// VErifica se o planejamento
	private Boolean verificaPlanDiferente() {
		if(planejemanetoAtual  != null 
				&& projeto.getPlanejamentos() !=null 
				&&  projeto.getPlanejamentos().getId() != null
				&& planejemanetoAtual.equals(this.projeto.getPlanejamentos().getId())) {
			return true;
		} else {
			return false;
		}
	}

	public void salvarPlanejamento() {
		addProfissionalVazio();
		if (dataEntrega != null) {
			this.projeto.setDtEntrega(dataEntrega);
		}
		// atualiza projeto
		if (verificaItemAlterado()) {
			if (jaAlterado) {
				if (verificaPlanDiferente()) {
					if (this.projeto.getPlanejamentos().getId() != 50L) {
						planejamentosService.criarRevisaoPlanejamento(usuarioLogado,
								this.projeto.getPlanejamentos().getId());
					}
				} else {
					if (this.projeto.getPlanejamentos() != null
						&& this.projeto.getPlanejamentos().getId() != null
						&& this.projeto.getPlanejamentos().getId() != 50L) {
						planejamentosService.criarRevisaoPlanejamento(usuarioLogado, planejemanetoAtual);
						planejamentosService.criarRevisaoPlanejamento(usuarioLogado,
						this.projeto.getPlanejamentos().getId());
					}
				}
				jaAlterado = false;
			}
		}
		if (idPlanejamento != null) {
			projeto.setPlanejamentos(planejamentosService.getById(idPlanejamento));
			projeto.setJaPlanejado(true);
			if (projeto.getStatus() == null
					|| (projeto.getStatus() != null && projeto.getStatus().getId().equals(10L))) {
				projeto.setStatus(statusService.getById(9L));
			}
		} else {
			projeto.setPlanejamentos(null);
			projeto.setJaPlanejado(false);
		}
		if (projeto.getStatus() == null || projeto.getStatus().getId() == null) {
			projeto.setStatus(statusService.getById(4L));
		}

		if (projeto.getStatus().getId().equals(4L) || projeto.getStatus().getId().equals(9L)) {
			projeto.setStatus(statusService.getById(9L));
		}
		
		//Valida a situação do planejamento.
		if(projeto.getPlanejamentos() != null){
			Boolean sitMapa = true;
			Boolean sitEpi = true;
			Boolean sitEvCtga = true;
			Boolean sitVerifica = true;
			
			if(projeto.getProfMapas()!= null && !projeto.getProfMapas().getId().equals(50L)){
				if(projeto.getCheckMapa()!= null && projeto.getCheckMapa()){
					sitMapa = true;
				}else{
					sitMapa = false;
				}
			}
			if(projeto.getProfEpi()!= null && !projeto.getProfEpi().getId().equals(50L)){
				if(projeto.getCheckEPI() != null && projeto.getCheckEPI()){
					sitEpi = true;
				}else{
					sitEpi = false;
				}
			}
			if(projeto.getProfEvctgal()!= null && !projeto.getProfEvctgal().getId().equals(50L)){
				if(projeto.getCheckEvCtga() != null && projeto.getCheckEvCtga()){
					sitEvCtga = true;
				}else{
					sitEvCtga = false;
				}
			}
			if(projeto.getProfVerif()!= null && !projeto.getProfVerif().getId().equals(50L)){
				if(projeto.getCheckVerificacap() != null && projeto.getCheckVerificacap()){
					sitVerifica = true;
				}else{
					sitVerifica = false;
				}
			}
			if(sitMapa && sitEpi && sitEvCtga && sitVerifica){
				projeto.setSituacaoPlanejamento(SituacaoPlanejamento.CONCLUIDO.getFlag());
			}else{
				projeto.setSituacaoPlanejamento(SituacaoPlanejamento.PENDENTE.getFlag());
			}
		}else{
			projeto.setSituacaoPlanejamento(SituacaoPlanejamento.SEM_PLANEJAMENTO.getFlag());
		}
		ordemServicoService.atualizar(projeto.getOrdemServico());
		projeto = projetoService.salvar(projeto);
		// conforme reuniao no dia 26/09/2017, Ana solicitou a implementacao.
		verificacao = verificacaoProfissionalService.getByProjeto(projeto.getId());
		if (verificacao == null) {
			verificacao = new VerificacaoProfissional();
		}
		verificacao.setProjeto(projeto);
		verificacao.setProfissionalEPI(projeto.getProfEpi());
		verificacao.setProfissionalEVCTGAL(projeto.getProfEvctgal());
		verificacao.setProfissionalMapas(projeto.getProfMapas());
		verificacao.setProfissionalVerificacao(projeto.getProfVerif());
		verificacao.setObservacaoPlanejamento(projeto.getObservacoesPagamento());
		verificacao.setDtFimPlanejamento(projeto.getDtFimPlanj());
		verificacao.setDtInicioPlanejamento(projeto.getDtIniPlanj());
		verificacao.setDataEntrega(projeto.getDtEntrega());
		verificacao = verificacaoProfissionalService.salvar(verificacao);
		verificacaoSalvar = verificacao;
		verificacaoSalvar.setDataEntrega(null);

		idProfEPI = (projeto.getProfEpi() != null ? projeto.getProfEpi().getId() : null);
		idProMap = (projeto.getProfMapas() != null ? projeto.getProfMapas().getId() : null);
		idProCtgal = (projeto.getProfEvctgal() != null ? projeto.getProfEvctgal().getId() : null);
		idProfVerificacao = (projeto.getProfVerif() != null ? projeto.getProfVerif().getId() : null);

		idProfEPIConclusao = (verificacaoSalvar.getProfissionalEPI() != null
				? verificacaoSalvar.getProfissionalEPI().getId() : null);
		idProMapConclusao = (verificacaoSalvar.getProfissionalMapas() != null
				? verificacaoSalvar.getProfissionalMapas().getId() : null);
		idProCtgalConclusao = (verificacaoSalvar.getProfissionalEVCTGAL() != null
				? verificacaoSalvar.getProfissionalEVCTGAL().getId() : null);
		idProfVerificacaoConclusao = (verificacaoSalvar.getProfissionalVerificacao() != null
				? verificacaoSalvar.getProfissionalVerificacao().getId() : null);
		comboPlanejamentos = planejamentosService.getAll();
		
		FacesUtil.menssagemSucesso("Planejamento atualizado com sucesso.");
	}

	public void salvarConclusao() {

		if (projeto == null) {
			FacesUtil.addErrorMessage("Selecione o projeto.");
			return;
		}
		verificacao = verificacaoProfissionalService.getByProjeto(projeto.getId());
		if (verificacao == null) {
			verificacao = new VerificacaoProfissional();
		}

		if (idProMapConclusao != null) {
			verificacao.setProfissionalMapas(profissionalService.getById(idProMapConclusao));
			;
		} else {
			verificacao.setProfissionalMapas(null);
		}
		if (idProfEPIConclusao != null) {
			verificacao.setProfissionalEPI(profissionalService.getById(idProfEPIConclusao));
		} else {
			verificacao.setProfissionalEPI(null);
		}
		if (idProCtgalConclusao != null) {
			verificacao.setProfissionalEVCTGAL(profissionalService.getById(idProCtgalConclusao));
		} else {
			verificacao.setProfissionalEVCTGAL(null);
		}
		if (idProfVerificacaoConclusao != null) {
			verificacao.setProfissionalVerificacao(profissionalService.getById(idProfVerificacaoConclusao));
		} else {
			verificacao.setProfissionalVerificacao(null);
		}
		verificacao.setProjeto(this.projeto);

		if (verificacaoSalvar.getDtInicioCampo() != null) {
			verificacao.setDtInicioCampo(verificacaoSalvar.getDtInicioCampo());
		}
		if (verificacaoSalvar.getDtFimCampo() != null) {
			verificacao.setDtFimCampo(verificacaoSalvar.getDtFimCampo());
		}
		if (verificacaoSalvar.getDtInicioPlanejamento() != null) {
			verificacao.setDtInicioPlanejamento(verificacaoSalvar.getDtInicioPlanejamento());
		}
		if (verificacaoSalvar.getDtFimPlanejamento() != null) {
			verificacao.setDtFimPlanejamento(verificacaoSalvar.getDtFimPlanejamento());
		}
		if (verificacaoSalvar.getDataEntrega() != null) {
			verificacao.setDataEntrega(verificacaoSalvar.getDataEntrega());
		}
		if (verificacaoSalvar.getObservacaoPlanejamento() != null) {
			verificacao.setObservacaoPlanejamento(verificacaoSalvar.getObservacaoPlanejamento());
		}
		verificacao = verificacaoProfissionalService.salvar(verificacao);
		this.projeto = projetoService.salvar(this.projeto);
		
		if (verificacaoSalvar.getDataEntrega() != null) {
			this.projeto.setStatus(statusService.getById(7L)); // Relatório
			this.projeto = projetoService.salvar(this.projeto);
			FacesUtil.addInfoMessage("Registro salvo com sucesso.");
		}else{
			FacesUtil.addErrorMessage("A Data de Entrega é obrigatório para conclusão do projeto. As informações serão salvas, porém o projeto não será concluído.");
		}
		dataEntradaLocal = this.projeto.getDataEntrada();
		
	}

	public TreeNode createCheckboxRelatorio(String tipo) {
		TreeNode root = new CheckboxTreeNode("Folder", null);
		if (tipo.equalsIgnoreCase("Relatorio")) {
			epi = new CheckboxTreeNode("EPI", root);
			ev = new CheckboxTreeNode("EV", root);
			ctgaSeia = new CheckboxTreeNode("EV", "CT", ev);
			ro = new CheckboxTreeNode("RO", root);
			casaEmApp = new CheckboxTreeNode("RO", "Casa em APP", ro);
			tamLinhaDiferente = new CheckboxTreeNode("RO", "Tamanho Linha Diferente", ro);
			obraConstruida = new CheckboxTreeNode("RO", "Obra construída", ro);
			vistoriaNaoREalizada = new CheckboxTreeNode("RO", "Vistoria não realizada", ro);
			outros = new CheckboxTreeNode("RO", "outros", ro);
			rca = new CheckboxTreeNode("RCA", root);
		//	icmbio = new CheckboxTreeNode("ICMBIO", root);
			riuc = new CheckboxTreeNode("RIUC", root);
		} else if (tipo.equalsIgnoreCase("Intervenção")) {
			diadSeia = new CheckboxTreeNode("DIAP(APP)", root);
			areaLago = new CheckboxTreeNode("DIAP(APP)", "Área em torno de lagos, nascente etc", diadSeia);
			faixaMarginal = new CheckboxTreeNode("DIAP(APP)","Faixa marginal cursos d'Água", diadSeia);
			topoMorro = new CheckboxTreeNode("DIAP(APP)","Topo Morro", diadSeia);
			seia = new CheckboxTreeNode("SEIA", root);
		} else if (tipo.equalsIgnoreCase("Mapas")) {
			localizacao = new CheckboxTreeNode("Localização", root);
			hidrografia = new CheckboxTreeNode("Hidrografia", root);
			undDeConsevacao = new CheckboxTreeNode("Unidade Conservação", root);
			vegetacao = new CheckboxTreeNode("Vegetação", root);
			leiMataAtlantica = new CheckboxTreeNode("Lei da Mata Atlântica", root);
			areaEspecial = new CheckboxTreeNode("Área Especial", root);
			assentamento = new CheckboxTreeNode("Área Especial", "Assentamento Agrário", areaEspecial);
			terraIndigina = new CheckboxTreeNode("Área Especial", "Terra Indígina", areaEspecial);
			quilombola = new CheckboxTreeNode("Área Especial", "Quilombola", areaEspecial);
		} else if (tipo.equalsIgnoreCase("Unidade")) {
			federal = new CheckboxTreeNode("Federal", root);
			estadual = new CheckboxTreeNode("Estadual", root);
			municipal = new CheckboxTreeNode("Municipal", root);
			particular = new CheckboxTreeNode("Particular (RPPN)", root);
		}
		return root;
	}

	public void carregaCombos() {
		// COMBO PROFISSIONAL
		comboProfisssional = profissionalService.getAllProfissionals();
		comboRegiao = regiaoService.getAllRegiao();
		comboEstado = estadoService.getAllEstados();
		comboContrato = contratoService.getAllContratos();
		comboClientes = clienteService.getAllClientes();
		comboStatusProjetoPesquisa = situacaoService.getAll();
		comboStatus = statusService.obterByTipo(ETipoStatus.STATUS_NOTIFICACAO.getFlag());
		comboStatusProjeto = statusService.obterByTipo(ETipoStatus.STATUS_PROJETO.getFlag());
		comboTipoRetificacao = tipoRetificacaoService.getAll();
		comboMacroRegiaoPesuisar = macroRegiaoService.getAllMacroRegiao();
		comboMicroRegiaoPesuisar = microRegiaoService.getAllMicroRegiao();
		comboTipoNotificacao = tipoNotificacaoService.getAll();

		// COMBO tipo de projeto.
		comboTipoProjeto = new ArrayList<>();
		for (ETipoProjeto item : ETipoProjeto.values()) {
			comboTipoProjeto.add(item.getDescricao());
		}
		listCaracteristicas = new ArrayList<>();
		for (ECaracteristicaRelatorio item : ECaracteristicaRelatorio.values()) {
			listCaracteristicas.add(item.getDescricao());
		}

		List<Situacao> comboSituacoesAUX = situacaoService.getAll();
		comboSituacoes = new ArrayList<>();
		comboSituacoesFiltro = new ArrayList<>();

		trajetoRede = new TrajetoRede();

		for (Situacao situacao : comboSituacoesAUX) {
			if (!situacao.getId().equals(ESituacao.FINALIZADO.getFlag())
					&& !situacao.getId().equals(ESituacao.PENDENTE.getFlag())) {
				comboSituacoesFiltro.add(situacao);
			}
		}
	}

	SimpleDateFormat formatAno = new SimpleDateFormat("yy");

	public void obterNumeracaoProjeto() {
		if (dataEntradaLocal == null) {
			FacesUtil.addErrorMessage("Por favor, Informe a data de entrada do projeto.");
			return;
		}
		projeto.setAnoReferencia(Long.parseLong(formatAno.format(dataEntradaLocal)));
		if (projeto.getSubTipoProjeto() == null) {
			FacesUtil.addErrorMessage("Por favor, selecione o SubTipo do Projeto.");
			return;
		}
		if (projeto.getAnoReferencia() == null) {
			FacesUtil.addErrorMessage("Por favor, selecione a Data de Entrada do Projeto.");
			return;
		}

		IProjeto projetoAUX = projetoService.obterNumeracaoMaiorByTipoByAno(projeto.getSubTipoProjeto(),
				projeto.getAnoReferencia());

		if (projetoAUX != null && projetoAUX.getId() != null) {
			Long novoNumeroProjetoAgrega = Long.parseLong(projetoService
					.obterNumeracaoMaiorByTipoByAno(projeto.getSubTipoProjeto(), projeto.getAnoReferencia())
					.getNumeroProjetoAgrega()) + 1L;

			if (novoNumeroProjetoAgrega < 10) {
				projeto.setNumeroProjetoAgrega("00" + novoNumeroProjetoAgrega);
			} else {
				if (novoNumeroProjetoAgrega < 100) {
					projeto.setNumeroProjetoAgrega("0" + novoNumeroProjetoAgrega);
				} else {
					projeto.setNumeroProjetoAgrega(String.valueOf(novoNumeroProjetoAgrega));
				}
			}

		} else {
			projeto.setNumeroProjetoAgrega("001");
		}
	}

	public void carregarSubTipoPorjetoPorTipoPorjeto() {
		comboSubTipoProjeto = new ArrayList<>();
		disableSubProjeto = true;
		if (this.projeto != null && !this.projeto.getTipoProjeto().equals("vazio")) {
			comboSubTipoProjeto = ETipoSubProjeto.obterPorTipoProjeto(projeto.getTipoProjeto());
			disableSubProjeto = false;
		} else {
			comboSubTipoProjeto = ETipoSubProjeto.obterPorTipoProjeto(null);
		}
	}

	public void carregarMacroRegiao() {
		if (projeto != null && projeto.getMunicipio() != null && projeto.getMunicipio().getId() != null) {
			projeto.setMunicipio(municipioService.getMunicipioById(projeto.getMunicipio().getId()));
		}
	}

	public void carregarCidadeByEstadoPesquisar() {
		comboMunicipio = new ArrayList<>();
		if (filtroEstado != null) {
			comboMunicipio = municipioService.getMunicipioByEstado(filtroEstado);
		}
	}

	public void carregarCidadeByEstado() {
		comboMunicipio = new ArrayList<>();
		disableMunicipio = true;
		if (this.projeto != null && this.projeto.getEstadoDadosTecnico() != null) {
			comboMunicipio = municipioService.getMunicipioByEstado(this.projeto.getEstadoDadosTecnico().getId());
			comboMacroRegiao = macroRegiaoService
					.getMacroRegiaoByEstadoId(this.projeto.getEstadoDadosTecnico().getId());
			disableMacroRegiao = false;
		}
	}

	public void obterMacroRegiaoByMunicipio(Long idMunicipio) {
		if (this.projeto != null && this.projeto.getEstadoDadosTecnico() != null) {
			comboMacroRegiao = macroRegiaoService
					.getMacroRegiaoByEstadoId(this.projeto.getEstadoDadosTecnico().getId());
			if (!comboMacroRegiao.isEmpty()) {
				disableMacroRegiao = false;
			}
		}
	}

	// Edita e confirma uma lista dinamicamente a lista de parcelas
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg;
		// obs. depois verificar regra do viewProjeto, pois foi copiado de
		// contrato
		if (viewProjeto) {
			msg = new FacesMessage("Parcela nº " + Integer.toString(((Parcela) event.getObject()).getNrParcela()),
					" somente vizualização");
		} else {
			msg = new FacesMessage("Parcela nº " + Integer.toString(((Parcela) event.getObject()).getNrParcela()),
					" Editada com sucesso");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// Cancela a edição de parcelas do contrato dinamicamente
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg;
		if (viewProjeto) {
			msg = new FacesMessage("Parcela nº " + Integer.toString(((Parcela) event.getObject()).getNrParcela()),
					" somente vizualização");
		} else {
			msg = new FacesMessage(
					"Edicação da parcela nº " + Integer.toString(((Parcela) event.getObject()).getNrParcela()),
					" Cancelada");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String retornarStringIds(List<String> lista) {
		String retorno = "";
		if (lista.size() == 1) {
			return retorno = lista.get(0);

		} else if (lista.size() > 1) {
			retorno = lista.get(0);
			for (String item : lista) {
				retorno = retorno + "," + item;
			}
			return retorno;
		}
		return null;

	}
	
	//Valida data Inicial e data Final.
	public void validarDataInicioFim(Date filtroDataInicial, Date filtroDataFinal ){
		Boolean validarData = false;
		if(filtroDataInicial!= null || filtroDataFinal!=null ){
			if(filtroDataInicial!= null && filtroDataFinal!= null){
				validarData =  DateUtil.validaDataInicialDataFinal(filtroDataInicial, filtroDataFinal);
			}else{
				if(filtroDataInicial==null){
					throw new NegocioException("Favor digite a data inicio de cadastro do projeto.");
				}else{
					throw new NegocioException("Favor digite a data fim de cadastro do projeto.");
				}
			}
			if(!validarData){
				throw new NegocioException("Data inicial deve ser menor que data Final.");
			}
		}
	}
	
	public void buscar() throws NegocioException{
		try{
			String idsContrato = null;
			String idsOS = null;
			filtro = new Projeto();
			exibirListPorOS = selectedPesquisarPor.equalsIgnoreCase("OS")? true:false;
			exibirListPorProjeto = selectedPesquisarPor.equalsIgnoreCase("OS")? false:true;
			validarDataInicioFim(filtroDataInicial, filtroDataFinal);
			filtro.setNumeroProjetoAgrega(filtroProjetoAgrega);
			filtro.setNumeroProjetoCliente(filtroProjetoCliente);
			filtro.setSubTipoProjeto(filtroSubProjeto);
			filtro.setNomeProjeto(filtroNomeProjeto);
			filtro.setAnoReferencia(filtroAnoProjeto!=null && !filtroAnoProjeto.isEmpty()? Long.parseLong(filtroAnoProjeto):null);
			filtro.setSituacaoPlanejamento(sitPlanejamento);
				
			if(listaDeIdContrato!= null && !listaDeIdContrato.isEmpty()){
				idsContrato = listaDeIdContrato.toString().replace("[", "").replace("]", "");
				if((listaDeIdOS != null && !listaDeIdOS.isEmpty())){
					idsOS = listaDeIdOS.toString().replace("[", "").replace("]", "");
				}
			}else if (selectedPesquisarPor.equalsIgnoreCase("OS")){
				throw new NegocioException("Selecione pelo menos um contrato para pesquisar as OS.");
			}
			obtenListProjetoOuOS(idsContrato, idsOS);
		} catch (Exception e) {
			FacesUtil.menssagemErro(e.getMessage());
		}
	}
	
	//Obtem a lista de projetos ou OS conforme filtro
	private void obtenListProjetoOuOS(String idsContrato, String idsOS) {
		Boolean resultado = true;
		if(selectedPesquisarPor.equalsIgnoreCase("OS")){
			listarOssByFiltro = ordemServicoService.obterIN(idsOS, idsContrato, filtroDataInicial, filtroDataFinal);
			if (listarOssByFiltro != null && !listarOssByFiltro.isEmpty()) {
				for (OrdemServico item : listarOssByFiltro) {
					item.setQtdProjetos(projetoService.totalProjetos(item.getId()));
				}
			}else{
				resultado = false;
			}
		}else{
			listarProjetosByFiltro = projetoService.obterProjetoIN(idsOS, idsContrato, filtroDataInicial, filtroDataFinal, filtro);
			resultado = (listarProjetosByFiltro == null || listarProjetosByFiltro.isEmpty())? false: true;
		}
		if(!resultado){
			throw new NegocioException("Não foi encontrado resultado para esses filtros");
		}
	}
	


	
	

	
	

	public List<Projeto> obterProjetoByIdContrato(List<String> listaDeIdContrato) {
		if (listaDeIdContrato != null && listaDeIdContrato.size() == 0) {
			return null;
		}
		String ids = listaDeIdContrato.toString().replace("[", "").replace("]", "");
		/*for (String item : listaDeIdContrato) {
			ids = ids + "," + item;
		}*/
		return projetoService.obterProjetoIN(null, ids, filtroDataInicial, filtroDataFinal, filtro);
	}

	public void verificarRefiticao(String item) {
		if (item.equals("true")) {
			exibirBtRetificacao = true;
		} else {
			exibirBtRetificacao = false;
		}

	}

	public void novaRetificacao() {
		this.retificacao = new Retificacao();
		idProfissionalResposanvelResposta = null;

		RequestContext.getCurrentInstance().execute("PF('dialogRetificacao').show();");

	}

	public void obterSituacoesFaturamento() {

		if (projetosSelecionados == null) {
			projetosSelecionados = new ArrayList<>();
		}
		valorTotalFaturar = 0.0;

		List<Situacao> comboSituacoesAUX = situacaoService.getAll();
		comboSituacoes = new ArrayList<>();

		for (Situacao situacao : comboSituacoesAUX) {

			if (situacao.getId().equals(ESituacao.CONFIRMAR_PRIMEIRO_PAGAMENTO.getFlag())
					|| situacao.getId().equals(ESituacao.CONFIRMAR_SEGUNDO_PAGAMENTO.getFlag())
					|| situacao.getId().equals(ESituacao.CONFIRMAR_TERCEIRO_PAGAMENTO.getFlag())

					|| situacao.getId().equals(ESituacao.QUITADO.getFlag())

					|| situacao.getId().equals(ESituacao.PRIMEIRO_FATURAMENTO_CONFIRMADO.getFlag())
					|| situacao.getId().equals(ESituacao.SEGUNDO_FATURAMENTO_CONFIRMADO.getFlag())
					|| situacao.getId().equals(ESituacao.TERCEIRO_FATURAMENTO_CONFIRMADO.getFlag())) {
				comboSituacoes.add(situacao);
			}

		}

		if (projetosSelecionados != null && projetosSelecionados.size() > 0) {
			Long porc = 0L;

			for (Projeto p : projetosSelecionados) {

				if (p.getSituacao().getId().equals(ESituacao.PENDENTE_PRIMEIRO_PAGAMENTO.getFlag())
						|| p.getSituacao().getId().equals(ESituacao.PRIMEIRO_FATURAMENTO_CONFIRMADO.getFlag())
						|| p.getSituacao().getId().equals(ESituacao.CONFIRMAR_PRIMEIRO_PAGAMENTO.getFlag())) {
					porc = CONSTANTE_PRIMEIRA_PARCELA;
				} else if (p.getSituacao().getId().equals(ESituacao.PENDENTE_SEGUNDO_PAGAMENTO.getFlag())
						|| p.getSituacao().getId().equals(ESituacao.SEGUNDO_FATURAMENTO_CONFIRMADO.getFlag())
						|| p.getSituacao().getId().equals(ESituacao.CONFIRMAR_SEGUNDO_PAGAMENTO.getFlag())) {
					porc = CONSTANTE_SEGUNDA_PARCELA;
				} else if (p.getSituacao().getId().equals(ESituacao.PENDENTE_TERCEIRO_PAGAMENTO.getFlag())
						|| p.getSituacao().getId().equals(ESituacao.TERCEIRO_FATURAMENTO_CONFIRMADO.getFlag())
						|| p.getSituacao().getId().equals(ESituacao.CONFIRMAR_TERCEIRO_PAGAMENTO.getFlag())
						|| p.getSituacao().getId().equals(ESituacao.FINALIZADO.getFlag())) {
					porc = CONSTANTE_TERCEIRA_PARCELA;
				}

				if (p.getValorProjeto() != null) {
					valorTotalFaturar = valorTotalFaturar + ((p.getValorProjeto() * porc) / 100);
				}
			}

			RequestContext.getCurrentInstance().execute("PF('dialogFaturarProjetos').show();");
		} else {
			FacesUtil.addErrorMessage("Por favor, selecione os projetos que deseja faturar.");
		}

	}

	public void faturar() {

		boolean erro = false;
		IFaturamento fatSalvar = null;

		if (faturamento.getDataNF() == null) {
			erro = true;
		}

		if (faturamento.getNumeroNF() == null) {
			erro = true;
		}

		if (idResponsavelFaturamento == null) {
			erro = true;
		}
		if (erro) {
			FacesUtil.addErrorMessage("Todos os campos são obrigatórios da fatura.");
			RequestContext.getCurrentInstance().execute("PF('dialogFaturarProjetos').show();");
			return;
		}
		Long parcela = null;

		for (Projeto proj : projetosSelecionados) {

			if (filtroSituacaoFaturar.equals(ESituacao.PENDENTE_PRIMEIRO_PAGAMENTO.getFlag())
					|| filtroSituacaoFaturar.equals(ESituacao.PRIMEIRO_FATURAMENTO_CONFIRMADO.getFlag())
					|| filtroSituacaoFaturar.equals(ESituacao.CONFIRMAR_PRIMEIRO_PAGAMENTO.getFlag())) {
				parcela = 1L;
			} else if (filtroSituacaoFaturar.equals(ESituacao.PENDENTE_SEGUNDO_PAGAMENTO.getFlag())
					|| filtroSituacaoFaturar.equals(ESituacao.SEGUNDO_FATURAMENTO_CONFIRMADO.getFlag())
					|| filtroSituacaoFaturar.equals(ESituacao.CONFIRMAR_SEGUNDO_PAGAMENTO.getFlag())) {
				parcela = 2L;
			} else if (filtroSituacaoFaturar.equals(ESituacao.PENDENTE_TERCEIRO_PAGAMENTO.getFlag())
					|| filtroSituacaoFaturar.equals(ESituacao.TERCEIRO_FATURAMENTO_CONFIRMADO.getFlag())
					|| filtroSituacaoFaturar.equals(ESituacao.CONFIRMAR_TERCEIRO_PAGAMENTO.getFlag())
					|| filtroSituacaoFaturar.equals(ESituacao.FINALIZADO.getFlag())) {
				parcela = 3L;
			}
			setProjeto(proj);
			if (filtroSituacaoFaturar.equals(ESituacao.QUITADO.getFlag())) {

				List<Faturamento> listFaturamentoAUX = faturamentoService.getByProjeto(proj.getId());

				if (listFaturamentoAUX.size() > 0) {

					for (Faturamento faturamentoAUX : listFaturamentoAUX) {
						// atualiza os faturamentos quando a situcacoa for
						// quitado.
						faturamentoAUX.setDataNF(faturamento.getDataNF());
						faturamentoAUX.setResponsavel(profissionalService.getById(idResponsavelFaturamento));
						faturamentoAUX.setNumeroNF(faturamento.getNumeroNF());
						faturamentoAUX.setSituacao(situacaoService.getById(ESituacao.QUITADO.getFlag()));

						if (parcela != null) {
							faturamentoAUX.setParcela(parcela);
							faturamentoAUX.setValorParcela(obterValorbyparcela(parcela));
						}

						faturamentoService.salvar(faturamentoAUX);
						// atualiza o projeto.
						proj.setSituacao(situacaoService.getById(ESituacao.QUITADO.getFlag()));
						projetoService.salvar(proj);

					}

				}

			} else {

				if (faturamentoService.getByProjetoByParcela(proj.getId(), parcela) != null) {
					fatSalvar = faturamentoService.getByProjetoByParcela(proj.getId(), parcela);
				}

				if (fatSalvar == null) {
					fatSalvar = new Faturamento();
				}
				fatSalvar.setDataNF(faturamento.getDataNF());
				fatSalvar.setResponsavel(profissionalService.getById(idResponsavelFaturamento));
				fatSalvar.setNumeroNF(faturamento.getNumeroNF());
				fatSalvar.setProjeto(proj);
				setProjeto(proj);
				fatSalvar.setContrato(proj.getContrato());
				if (parcela != null) {
					fatSalvar.setParcela(parcela);
					setProjeto(proj);
					fatSalvar.setValorParcela(obterValorbyparcela(parcela));
				}
				fatSalvar.setSituacao(new Situacao(filtroSituacaoFaturar));

				fatSalvar = faturamentoService.salvar(fatSalvar);

				proj.setSituacao(fatSalvar.getSituacao());
				projetoService.salvar(proj);
			}

			fatSalvar = new Faturamento();

		}
		filtroSituacaoFaturar = null;
		faturamento = new Faturamento();
		FacesUtil.addInfoMessage("Faturamento realizado com sucesso.");

	}

	public Double obterValorbyparcela(Long parcela) {
		Double valorParcela = 0.0;

		if (parcela == 1L) {
			parcela = CONSTANTE_PRIMEIRA_PARCELA;
		} else if (parcela == 2L) {
			parcela = CONSTANTE_SEGUNDA_PARCELA;
		} else if (parcela == 3L) {
			parcela = CONSTANTE_TERCEIRA_PARCELA;
		}

		if (projeto != null && projeto.getValorProjeto() != null) {
			valorParcela = (projeto.getValorProjeto()) * parcela / 100;
		}
		return valorParcela;
	}

	private Boolean camposDeNotificacaoObrigatorio = false;
	private Boolean camposDeRegistoObrigatorio = false;

	public void preNotificacao() {
		notificacao = new Notificacao();
		idProfissionalResposanvelResposta = null;
		camposDeNotificacaoObrigatorio = true;
		RequestContext.getCurrentInstance().execute("PF('dialogRegistroNotificacao').show();");

	}

	public void preExcluirOrdemServico(OrdemServico ordemOS) {
		Long idOs = 0L;
		Integer qtdProjeto = 0;
		if(ordemOS != null){
			idOs = ordemOS.getId();
			qtdProjeto = projetoService.obterProjetoPorOS(idOs);
		}
		if(qtdProjeto != 0){
			RequestContext.getCurrentInstance().execute("PF('dialogNaoExcluirOS').show();");
		}else{
			this.ordemServico = ordemOS;
			RequestContext.getCurrentInstance().execute("PF('dialogExcluirOS').show();");
		}
	}
	
	public void excluirOS() {
		try{
			if (ordemServico != null && ordemServico.getId() != null) {
				ordemServicoService.remover(ordemServico);
				buscar();
				FacesUtil.addInfoMessage("Ordem de Serviço Excluida com sucesso.");
			}
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro Ao Excluir O.S - Erro:" + e.getMessage());
		}
	}

	public void preExcluirPlanejamento() {
		if (projetosSelecionados != null && !projetosSelecionados.isEmpty()) {
			RequestContext.getCurrentInstance().execute("PF('dialogExcluirPlanejamento').show();");
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum projeto foi selecionado para excluir o planejamento.", ""));
		}
		
	}

	public void preExcluirProjeto(Projeto itemEx) {
		this.projeto = itemEx;
		RequestContext.getCurrentInstance().execute("PF('dialogExcluirProjeto').show();");
	}

	public void preExcluirCoordenada(Coordenada itemEx) {
		coordenadaExcluir = itemEx;
		RequestContext.getCurrentInstance().execute("PF('dialogExcluirCoordenada').show();");
	}

	public void preExcluirTrajetoRede(TrajetoRede itemEx) {
		trajetoExcluir = itemEx;
		RequestContext.getCurrentInstance().execute("PF('dialogExcluirTrajeto').show();");

	}

	public void excluirProjeto() {
		if (this.projeto.getId() != null) {
			projeto.setAtivo(false);
			projetoService.salvar(this.projeto);
			listarProjetosByOS = projetoService.getListaByOS(ordemServico.getId(), null, null);
			// verifica se Ordem de servico ou Contrato
			if (listaDeIdOS != null && listaDeIdOS.size() > 0) {
				String ids = listaDeIdOS.toString().replace("[", "").replace("]", "");
				listarProjetosByFiltro = projetoService.obterProjetoIN(ids, null, filtroDataInicial, filtroDataFinal, filtro);
			} else {
				listarProjetosByFiltro = obterProjetoByIdContrato(listaDeIdContrato);
			}

			FacesUtil.addInfoMessage("Projeto excluído com sucesso.");
			projeto = new Projeto();
		}
	}
	
	public void excluirCoordenada() {
		if (coordenadaExcluir != null && coordenadaExcluir.getId() != null) {
			coordenadaService.remover(coordenadaExcluir);
			if (projeto != null && projeto.getId() != null) {
				listCoordenada = coordenadaService.getByProjeto(projeto.getId());
				listCoordenada.sort(comparador);
			}
			verificarCoordenada();
			FacesUtil.addInfoMessage("Coordenada excluída com sucesso.");
		}
	}

	public void excluirTrajeto() {
		if (trajetoExcluir != null && trajetoExcluir.getId() != null) {
			trajetoRedeService.remover(trajetoExcluir);
			if (projeto != null && projeto.getId() != null) {
				listTrajetoRede = trajetoRedeService.getByProjeto(projeto.getId());
			}
			verificarTrajeto();
			FacesUtil.addInfoMessage("Trajeto excluído com sucesso.");
		}
	}

	public void excluirOrdemServico() {
		if (ordemServico != null && ordemServico.getId() != null) {

			listarProjetosByFiltro = projetoService.getListaByOS(ordemServico.getId(), null, null);

			if (listarProjetosByFiltro != null && listarProjetosByFiltro.size() > 0) {
				for (Projeto itemP : listarProjetosByFiltro) {
					itemP.setAtivo(false);
					projetoService.salvar(itemP);
				}
			}

			ordemServico.setAtivo(false);
			ordemServicoService.salvar(ordemServico);

			buscar();
			FacesUtil.addInfoMessage("Trajeto excluído com sucesso.");
		}
	}

	public void editarCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public void editarTrajeto(TrajetoRede trajetoRede) {
		this.trajetoRede = trajetoRede;
	}

	public void verificarCoordenada() {
		if (coordenada == null) {
			coordenada = new Coordenada();
		}

	}

	public void verificarTrajeto() {

		if (trajetoRede == null) {
			trajetoRede = new TrajetoRede();
		}
	}

	public void editarRetificacao(Retificacao retificacao) {
		this.retificacao = retificacao;

		if (retificacao.getResponsavelResposta() != null && retificacao.getResponsavelResposta().getId() != null) {
			idProfissionalResposanvelResposta = retificacao.getResponsavelResposta().getId();
		}
		if (retificacao.getTipoRetificacao() != null && retificacao.getTipoRetificacao().getId() != null) {
			idTipoRetificacao = retificacao.getTipoRetificacao().getId();
		}
		if (retificacao.getStatus() != null && retificacao.getStatus().getId() != null) {
			idStatus = retificacao.getStatus().getId();
		}

		camposDeNotificacaoObrigatorio = false;
		camposDeRegistoObrigatorio = false;
		RequestContext.getCurrentInstance().execute("PF('dialogRetificacao').show();");

	}

	public void removerRetificacao(Retificacao retificacao) {
		retificacaoService.remover(retificacao);
		listRetificacao = retificacaoService.getByProjeto(projeto.getId());
		FacesUtil.addInfoMessage("Registro excluído com sucesso!");
	}

	public void obterDadosProjetos(Projeto projeto) {

		rootRelatorio = createCheckboxRelatorio("Relatorio");
		rootMapas = createCheckboxRelatorio("Mapas");
		rootUnidadeConservacao = createCheckboxRelatorio("Unidade");
		rootIntervencao = createCheckboxRelatorio("Intervenção");
		//TODO teste
		//comboOrdemServico = ordemServicoService.getAll();
		btProjetos = "Voltar";
		disablePlanejamento = false;
		pesquisar = false;
		exibirListPorOS = false;
		abrirEditarOS = false;
		pagPlanejando = false;
		cadastrarProjeto = true;
		this.projeto = projeto;
		preencherListaAnexos();
		preencherListaAnexosPlan();
		listCoordenada = coordenadaService.getByProjeto(projeto.getId());
		if (listCoordenada != null && !listCoordenada.isEmpty()) {
			listCoordenada.sort(comparador);
		}

		listTrajetoRede = trajetoRedeService.getByProjeto(projeto.getId());

		ordemServico = projeto.getOrdemServico();
		caracteristicasProjeto = caracteristicasProjetoService.getByProjeto(projeto.getId());
		comboSubTipoProjeto = ETipoSubProjeto.obterPorTipoProjeto(projeto.getSubTipoProjeto());
		listRetificacao = retificacaoService.getByProjeto(projeto.getId());

		if (caracteristicasProjeto == null) {
			caracteristicasProjeto = new CaracteristicaProjeto();
		}

		if (listCoordenada == null) {
			listCoordenada = new ArrayList<>();
		}
		if (listTrajetoRede == null) {
			listTrajetoRede = new ArrayList<>();
		}
		coordenada = new Coordenada();
		trajetoRede = new TrajetoRede();

		verificacaoSalvar = verificacaoProfissionalService.getByProjeto(projeto.getId());
		if (verificacaoSalvar == null) {
			instaciarVerificacao();
		} else {
			idProfEPI = (projeto.getProfEpi() != null ? projeto.getProfEpi().getId() : null);
			idProMap = (projeto.getProfMapas() != null ? projeto.getProfMapas().getId() : null);
			idProCtgal = (projeto.getProfEvctgal() != null ? projeto.getProfEvctgal().getId() : null);
			idProfVerificacao = (projeto.getProfVerif() != null ? projeto.getProfVerif().getId() : null);

			idProfEPIConclusao = (verificacaoSalvar.getProfissionalEPI() != null
					? verificacaoSalvar.getProfissionalEPI().getId() : null);
			idProMapConclusao = (verificacaoSalvar.getProfissionalMapas() != null
					? verificacaoSalvar.getProfissionalMapas().getId() : null);
			idProCtgalConclusao = (verificacaoSalvar.getProfissionalEVCTGAL() != null
					? verificacaoSalvar.getProfissionalEVCTGAL().getId() : null);
			idProfVerificacaoConclusao = (verificacaoSalvar.getProfissionalVerificacao() != null
					? verificacaoSalvar.getProfissionalVerificacao().getId() : null);

		}
		verificarProfissionais(projeto);

		listarFaturamentosByProjeto = faturamentoService.getByProjeto(projeto.getId());
		if (listarFaturamentosByProjeto == null) {
			listarFaturamentosByProjeto = new ArrayList<>();
		}

		Registro filtroRegistro = new Registro();
		filtroRegistro.setProjeto(projeto);

		if (registroService.getByFiltros(filtroRegistro).size() > 0) {
			registoSalvar = registroService.getByFiltros(filtroRegistro).get(0);
		} else {
			registoSalvar = new Registro();
		}
		VerificaCaracteristicasMarcadasRelatorio();
		Notificacao filtro = new Notificacao();
		filtro.setProjeto(this.projeto);
		listarNotificacao = notificacaoService.getByFiltros(filtro);

		if (listarNotificacao == null) {
			listarNotificacao = new ArrayList<>();
		}

		verificarVariveis();
		comboMunicipio = municipioService.getAllMunicipio();
		verificarGerouDaeEdit();

		verificarCoordenada();
		verificarTrajeto();

		if (projeto.getStatus() == null) {
			projeto.setStatus(new Status());
		} else {
			idStatusProjeto = projeto.getStatus().getId();
		}
		if (projeto.getPlanejamentos() != null) {
			idPlanejamento = projeto.getPlanejamentos().getId();
		}
		fusoInformado = null;
		dataEntradaLocal = projeto.getDataEntrada();
	}

	public void editarProjeto(Projeto projeto) {
		currentTab = 0;
		visualizarProjeto = false;
		obterDadosProjetos(projeto);
		idMapas = projeto.getProfMapas().getId();
		idEPI = projeto.getProfEpi().getId();
		idEvctga = projeto.getProfEvctgal().getId();
		idVerificacao = projeto.getProfVerif().getId();
		plInicio = projeto.getDtIniPlanj();
		plFim = projeto.getDtFimPlanj();
		plEntrega = projeto.getDtEntrega();
		jaAlterado = true;
		planejemanetoAtual = idPlanejamento;
		idContratoNovoProjeto = projeto.getContrato().getId();
		obterOSNovoProjeto(idContratoNovoProjeto);
	}

	public void visualizarProjeto(Projeto projeto) {
		visualizarProjeto = true;
		obterDadosProjetos(projeto);
		idContratoNovoProjeto = projeto.getContrato().getId();
		obterOSNovoProjeto(idContratoNovoProjeto);
	}

	public void visualizarOrdemServico(OrdemServico ordemServico) {

		caracteristicasProjeto = new CaracteristicaProjeto();
		this.ordemServico = ordemServico;
		cadastrarProjeto = false;
		visualizarProjeto = false;
		abrirEditarOS = false;
		cadastrarOS = true;
		cadastrarOS = false;
		pesquisar = false;
		abrirEditarOS = true;
		visualizarOrdemServico = true;
		listarProjetosByOS = projetoService.getListaByOS(ordemServico.getId(), null, null);

	}

	public void editarOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
		cadastrarProjeto = false;
		visualizarProjeto = false;
		cadastrarOS = true;
		cadastrarOS = false;
		pesquisar = false;
		abrirEditarOS = true;
		visualizarOrdemServico = false;
		listarProjetosByOS = projetoService.getListaByOS(ordemServico.getId(), null, null);

		if (caracteristicasProjeto == null) {
			caracteristicasProjeto = new CaracteristicaProjeto();
		}
		coordenada = new Coordenada();

	}

	public void voltarConsultoria() {

		cadastrarProjeto = false;
		visualizarProjeto = false;
		cadastrarOS = false;
		cadastrarOS = false;
		pesquisar = false;
		abrirEditarOS = false;
		pagPlanejando = false;
		pesquisar = true;

	}

	public void voltarPlanejar() {

		cadastrarProjeto = false;
		visualizarProjeto = false;
		cadastrarOS = false;
		cadastrarOS = false;
		pesquisar = false;
		abrirEditarOS = false;
		pagPlanejando = false;
		pesquisar = true;
		filterProjetos = new ArrayList<>();
		listProjetosPlanejar = new ArrayList<>();
		projetosSelecionados = new ArrayList<>();
		listarProjetosByFiltro = new ArrayList<>();
	}

	public void verificarProfissionais(Projeto projeto) {

		if (verificacao == null) {
			verificacao = new VerificacaoProfissional();
		}

		if (verificacao.getProfissionalEVCTGAL() == null) {
			verificacao.setProfissionalEVCTGAL(new Profissional());
		}
		if (verificacao.getProfissionalVerificacao() == null) {
			verificacao.setProfissionalVerificacao(new Profissional());
		}
		if (verificacao.getProfissionalMapas() == null) {
			verificacao.setProfissionalMapas(new Profissional());
		}
		if (verificacao.getProfissionalEPI() == null) {
			verificacao.setProfissionalEPI(new Profissional());
		}

		if (projeto.getProfEpi() == null) {
			projeto.setProfEpi(new Profissional());
		}
		if (projeto.getProfEvctgal() == null) {
			projeto.setProfEvctgal(new Profissional());
		}
		if (projeto.getProfVerif() == null) {
			projeto.setProfVerif(new Profissional());
		}
		if (projeto.getProfMapas() == null) {
			projeto.setProfMapas(new Profissional());
		}
		idProMap = projeto.getProfMapas().getId() != null ? projeto.getProfMapas().getId() : null;
		idProfEPI = projeto.getProfEpi().getId() != null ? projeto.getProfEpi().getId() : null;
		idProCtgal = projeto.getProfEvctgal().getId() != null ? projeto.getProfEvctgal().getId() : null;
		idProfVerificacao = projeto.getProfVerif().getId() != null ? projeto.getProfVerif().getId() : null;

		if (verificacao.getProfissionalEVCTGAL() == null) {
			verificacao.setProfissionalEVCTGAL(new Profissional());
		}
		if (verificacao.getProfissionalVerificacao() == null) {
			verificacao.setProfissionalVerificacao(new Profissional());
		}

		if (verificacao.getProfissionalMapas() == null) {
			verificacao.setProfissionalMapas(new Profissional());
		}

		if (verificacao.getProfissionalEPI() == null) {
			verificacao.setProfissionalEPI(new Profissional());
		}

		if (projeto.getProfissionalGestor() != null) {
			idProfissionalGestor = projeto.getProfissionalGestor().getId();
		}

	}

	public void verificarSeOsProjeto(String tipo) {
		ordensServicosSelecionados = null;
		setOpEscolhida(3L);
		if (tipo != null && tipo.equalsIgnoreCase("OS")) {
			ocultarFiltroProjeto = false;

			List<Situacao> comboSituacoesAUX = situacaoService.getAll();
			comboSituacoes = new ArrayList<>();

			for (Situacao situacao : comboSituacoesAUX) {

				if (!situacao.getId().equals(ESituacao.PENDENTE.getFlag())) {
					comboSituacoes.add(situacao);
				}
			}

		} else {
			ocultarFiltroProjeto = true;
		}
		filtroProjetoAgrega = null;
		filtro = new Projeto();
		filtro.setStatus(new Status());
		listarOssByFiltro = null;
		listarProjetosByFiltro = null;
		projetosSelecionados = null;
	}

	public void verificarOperacao(Long op) {

		if (op.equals(1L)) {
			tituloDataTable = "Lista de Projetos a Planejar";
			opPlanejar = true;
			opFaturar = false;
			listarProjetosByFiltro = new ArrayList<>();
			projetosSelecionados = new ArrayList<>();
		} else if (op.equals(2L)) {
			opFaturar = true;
			opPlanejar = false;
			tituloDataTable = "Lista de Projetos a Faturar";
			listarProjetosByFiltro = new ArrayList<>();
			projetosSelecionados = new ArrayList<>();
			obterSituacoesFaturamento();
		} else if (op.equals(3L)) {
			opPlanejar = false;
			opFaturar = false;
			listarProjetosByFiltro = new ArrayList<>();
			projetosSelecionados = new ArrayList<>();
			tituloDataTable = "Resultados da pesquisa";
		}
	}

	public void adicionarOS() {
		cadastrarOS = true;
		pesquisar = false;
		cadastrarProjeto = false;
		abrirEditarOS = false;
		coordenada = new Coordenada();
		caracteristicasProjeto = new CaracteristicaProjeto();
		listarProjetosByFiltro = new ArrayList<>();
		projetosSelecionados = new ArrayList<>();
		listarOssByFiltro = new ArrayList<>();

	}

	public void verificarSeOSCOntrato() {
		if (this.ordemServico.getContrato().getId() != null) {
			IContrato contrato = new Contrato();
			contrato = contratoService.getContratoById(this.ordemServico.getContrato().getId());
			cliente = contrato.getProposta().getCliente().getNome();
			formaDePagamentocontrato = contrato.getFormaPagamento();

			if (formaDePagamentocontrato.equals("avista") || formaDePagamentocontrato.equals("parcelado")) {
				setLabelOS("Valor Contrato");
				saldoContrato = contrato.getVlrContrato();
			} else {
				setLabelOS("Saldo Contrato");
				saldoContrato = contrato.getSaldoContrato();
			}
		}
		ordemServico.setContrato(ordemServico.getContrato());
		ordemServico.setNumeroOS(gerarNumeroOS(ordemServico.getContrato().getId()));
	}

	public String gerarNumeroOS(Long idContrato) {
		OrdemServico ordemServicoTem = ordemServicoService.obterMaiorID(idContrato);
		Integer aux = Integer.valueOf(ordemServicoTem.getNumeroOS().substring(0, 2))+1;
		String aux2 = String.valueOf(aux);
		return 0+aux2+ordemServicoTem.getNumeroOS().substring(2);
	}

	public void cancelarNovaOS() {
		cadastrarOS = false;
		pesquisar = true;
		cadastrarProjeto = false;
		abrirEditarOS = false;
		ordemServico = new OrdemServico();
		ordemServico.setContrato(new Contrato());
		saldoContrato = null;
		formaDePagamentocontrato = null;
		cliente = null;
		btProjetos = "Cancelar";
		jaAlterado = true;
	}

	public void atualizarNovaOS() {
		if (this.ordemServico != null) {

			if (this.ordemServico.getNumeroOS() == null) {
				FacesUtil.addErrorMessage("Preencha o campo Nº da ordem de serviço.");
				return;
			}
			if (ordemServicoService.obterByNumeroOs(this.ordemServico.getNumeroOS(),
					this.ordemServico.getContrato().getId()) != null) {
				FacesUtil.addErrorMessage(
						"Para esse contrato, já existe uma Ordem de Serviço com essa numeração. Por favor, informe outra númeração.");
				return;
			}
			this.ordemServico.setUsuarioAtualizacao(usuarioLogado);
			this.ordemServico = ordemServicoService.salvar(ordemServico);
			FacesUtil.addInfoMessage("Ordem de Serviço atualizada com sucesso.");
		}
	}

	public void salvarNovaOS() {

		if (this.ordemServico != null) {

			if (ordemServicoService.obterByNumeroOs(this.ordemServico.getNumeroOS(),
					this.ordemServico.getContrato().getId()) != null) {
				FacesUtil.addErrorMessage(
						"Para esse contrato, já existe uma Ordem de Serviço com essa numeração. Por favor, informe outra númeração.");
				return;
			}
			ordemServico.setAtivo(true);
			ordemServico.setDataInclusao(new Date());
			ordemServico.setQtdCadastrada(qtdCadastradaProjeto);
			ordemServico.setQtdFaturada(qtdFaturado);
			ordemServico.setValorFaturado(valorFaturado);
			ordemServico.setUsuarioAtualizacao(usuarioLogado);
			//ordemServico.setContadorNrOS(numero + 1);
			ordemServico.setQtdProjetos(0);

			if (ordemServico.getContrato() != null && ordemServico.getContrato().getId() != null) {
				ordemServico.setCliente(
						contratoService.getContratoById(ordemServico.getContrato().getId()).getProposta().getCliente());
			}
			this.ordemServico = ordemServicoService.salvar(ordemServico);

			cadastrarOS = false;
			pesquisar = false;
			cadastrarProjeto = false;
			abrirEditarOS = true;
			atualizaQtdProjetoACadastrar();

			FacesUtil.addInfoMessage("Ordem de Serviço criada com sucesso.");
		}
	}

	public void atualizaQtdProjetoACadastrar() {
		qtdRestanteACadastrar = this.ordemServico.getQtdProjetos() - this.ordemServico.getQtdCadastrada();
	}

	public void voltarPesquisa() {
		instanciarObjetos();
		cadastrarOS = false;
		pesquisar = true;
		cadastrarProjeto = false;
		abrirEditarOS = false;
		ordemServico = new OrdemServico();
		ordemServico.setContrato(new Contrato());
		saldoContrato = null;
		formaDePagamentocontrato = null;
		cliente = null;
		idStatusProjeto = null;
	}

	public void instanciarObjetos() {
		this.ordemServico = new OrdemServico();
		this.ordemServico.setContrato(new Contrato());
		this.ordemServico.getContrato().setProposta(new Proposta());
		this.ordemServico.getContrato().getProposta().setCliente(new Cliente());
		/*
		 * this.profissionalEPI = new Profissional(); this.profissionalEVCTGAL =
		 * new Profissional(); this.profissionalMapas = new Profissional();
		 * this.profissionalVerificacao = new Profissional();
		 */
		this.projeto = new Projeto();
		this.projeto.setProfissionalGestor(new Profissional());
		this.projeto.setMunicipio(new Municipio());
		this.projeto.setEstadoDadosTecnico(new Estado());
		this.projeto = new Projeto();
		this.caracteristicasProjeto = new CaracteristicaProjeto();
		this.verificacao = new VerificacaoProfissional();
		this.retificacao = new Retificacao();
		this.projeto = new Projeto();
		this.projeto.setProfissionalGestor(new Profissional());
		this.projeto.setMunicipio(new Municipio());
		this.projeto.setEstadoDadosTecnico(new Estado());
		listCoordenada = new ArrayList<>();
		listTrajetoRede = new ArrayList<>();
		listParcelaProjeto = new ArrayList<>();
		listRetificacao = new ArrayList<>();

	}

	public void adicioarProjetoNaOS(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
		if (caracteristicasProjeto == null) {
			caracteristicasProjeto = new CaracteristicaProjeto();
		}
		coordenada = new Coordenada();
		adicionarProjeto();
	}

	public void adicionarProjeto() {
		idContratoNovoProjeto = null;
		ordemServico = null;
		cadastrarOS = false;
		pesquisar = false;
		cadastrarProjeto = true;
		abrirEditarOS = false;
		//TODO 
		//comboOrdemServico = ordemServicoService.getAll();
		if (caracteristicasProjeto == null) {
			caracteristicasProjeto = new CaracteristicaProjeto();
		}
		verificacao = new VerificacaoProfissional();
		this.verificacao.setProfissionalEPI(new Profissional());
		this.verificacao.setProfissionalEVCTGAL(new Profissional());
		this.verificacao.setProfissionalMapas(new Profissional());
		this.verificacao.setProfissionalVerificacao(new Profissional());
		projeto = new Projeto();
		Municipio muni = new Municipio();
		muni.setEstado(new Estado());
		muni.setRegiao(new Regiao());
		projeto.setMunicipio(muni);
		projeto.setEstadoDadosTecnico(new Estado());
		projeto.setStatus(new Status());
		projeto.setStatus(statusService.getById(4L));
		projeto.setTipoPrograma(new TipoPrograma()); // solicitacao Adelia
														// 20-07-2018 - as 12h00
		registoSalvar = new Registro();
		notificacao = new Notificacao();
		idProfissionalGestor = null;
		primariaKmString = null;
		primariaKmString = null;
		secundariaKmString = null;
		tensaoPrimariaKvString = null;
		conjugadaKmString = null;
		tensaoSecundariaKVString = null;
		distanciaSedeMunPavString = null;
		distanciaSedeMunNaoPavString = null;
		distanciaMunLocalProjPavString = null;
		distanciaMunLocalProjNaoPavString = null;
		idProMap = null;
		idProCtgal = null;
		idProfVerificacao = null;
		idProfEPI = null;
		dataEntrega = null;
		fusoInformado = null;

		instaciarVerificacao();
		verificacao = new VerificacaoProfissional();
		verificacao.setProfissionalEVCTGAL(new Profissional());
		verificacao.setProfissionalEPI(new Profissional());
		verificacao.setProfissionalEVCTGAL(new Profissional());
		verificacao.setProfissionalMapas(new Profissional());
		verificacao.setProfissionalVerificacao(new Profissional());
		registoSalvar = new Registro();

		comboMunicipio = municipioService.getAllMunicipio();
		listCoordenada = new ArrayList<>();
		listarNotificacao = new ArrayList<>();
		listTrajetoRede = new ArrayList<>();
		caracteristicasProjeto = new CaracteristicaProjeto();
		comboSubTipoProjeto = new ArrayList<>();
		listRetificacao = new ArrayList<>();
		listarFaturamentosByProjeto = new ArrayList<>();
		disablePlanejamento = true;
		coordenada = new Coordenada();
		trajetoRede = new TrajetoRede();

		selectedRelatorioNodes = null;
		selectedMapasNodes = null;
		selectedUnidadeConservacaoNodes = null;
		selectedIntervecaoNodes = null;
		listRetificacao = new ArrayList<>();

		idProMapConclusao = null;
		idProfEPIConclusao = null;
		idProCtgalConclusao = null;
		idProfVerificacaoConclusao = null;
		idPlanejamento = null;
		anexos = new ArrayList<>();
		rootRelatorio = createCheckboxRelatorio("Relatorio");
		rootMapas = createCheckboxRelatorio("Mapas");
		rootUnidadeConservacao = createCheckboxRelatorio("Unidade");
		rootIntervencao = createCheckboxRelatorio("Intervenção");
		createCheckboxRelatorio("Unidade");

		if (ordemServico == null) {
			ordemServico = new OrdemServico();
		}

		if (projeto.getPlanejamentos() == null) {
			projeto.setPlanejamentos(new Planejamentos());
		}
		dataEntradaLocal = null;
	}

	public void calcularExtensaoTotal() {
		Double total;
		Double areaHa;

		if (primariaKmString != null) {
			primariaKmString = primariaKmString.replace(",", ".");
			this.projeto.setPrimariaKm(Double.parseDouble(primariaKmString));
		}
		if (secundariaKmString != null) {
			secundariaKmString = secundariaKmString.replace(",", ".");
			this.projeto.setSecundariaKm(Double.parseDouble(secundariaKmString));
		}

		if (this.projeto.getPrimariaKm() != null && this.projeto.getSecundariaKm() != null) {
			total = this.projeto.getPrimariaKm() + this.projeto.getSecundariaKm();
			this.projeto.setExtensaoTotalKm(total);
			this.projeto.setTamanhoProjeto(total);

			areaHa = total * 1.5;
			this.projeto.setAreaHa(areaHa);

		}
	}

	public void verificarVariveis() {

		if (projeto.getDistanciaMunLocalProjNaoPav() != null) {
			distanciaMunLocalProjNaoPavString = String.valueOf(projeto.getDistanciaMunLocalProjNaoPav()).replace(".",
					",");
		}
		if (projeto.getDistanciaMunLocalProjPav() != null) {
			distanciaMunLocalProjPavString = String.valueOf(projeto.getDistanciaMunLocalProjPav()).replace(".", ",");
		}
		if (projeto.getDistanciaSedeMunNaoPav() != null) {
			distanciaSedeMunNaoPavString = String.valueOf(projeto.getDistanciaSedeMunNaoPav()).replace(".", ",");
		}
		if (projeto.getDistanciaSedeMunPav() != null) {
			distanciaSedeMunPavString = String.valueOf(projeto.getDistanciaSedeMunPav()).replace(".", ",");
		}

		if (projeto.getPrimariaKm() != null) {
			primariaKmString = String.valueOf(projeto.getPrimariaKm()).replace(".", ",");
		}

		if (projeto.getSecundariaKm() != null) {
			secundariaKmString = String.valueOf(projeto.getSecundariaKm()).replace(".", ",");
		}
		if (projeto.getConjugadaKm() != null) {
			conjugadaKmString = String.valueOf(projeto.getConjugadaKm()).replace(".", ",");
		}

		if (projeto.getTensaoPrimariaKv() != null) {
			tensaoPrimariaKvString = String.valueOf(projeto.getTensaoPrimariaKv()).replace(".", ",");
		}

		if (projeto.getTensaoSecundariaKV() != null) {
			tensaoSecundariaKVString = String.valueOf(projeto.getTensaoSecundariaKV()).replace(".", ",");
		}

		carregarMacroRegiao();

	}

	public void calcularTotalPostes() {
		Long total;

		if (this.projeto.getPostesPrimario() != null && this.projeto.getPostesSecundario() != null) {
			total = this.projeto.getPostesPrimario() + this.projeto.getPostesSecundario();
			this.projeto.setPostesTotal(total);
		}
	}

	public Double obterValorFaturadoByProjeto(Projeto projeto) {

		Double valor = 0.0;

		if (listarFaturamentosByProjeto == null) {
			listarFaturamentosByProjeto = new ArrayList<>();
		}

		if (projeto.getId() != null) {
			listarFaturamentosByProjeto = faturamentoService.getByProjeto(projeto.getId());

			if (listarFaturamentosByProjeto.size() > 0) {

				for (Faturamento item : listarFaturamentosByProjeto) {

					if (item.getSituacao().getId().equals(ESituacao.PRIMEIRO_FATURAMENTO_CONFIRMADO.getFlag())
							|| item.getSituacao().getId().equals(ESituacao.SEGUNDO_FATURAMENTO_CONFIRMADO.getFlag())
							|| item.getSituacao().getId().equals(ESituacao.TERCEIRO_FATURAMENTO_CONFIRMADO.getFlag())

							|| item.getSituacao().getId().equals(ESituacao.CONFIRMAR_PRIMEIRO_PAGAMENTO.getFlag())
							|| item.getSituacao().getId().equals(ESituacao.CONFIRMAR_SEGUNDO_PAGAMENTO.getFlag())
							|| item.getSituacao().getId().equals(ESituacao.CONFIRMAR_TERCEIRO_PAGAMENTO.getFlag())

							|| item.getSituacao().getId().equals(ESituacao.FINALIZADO.getFlag())
							|| item.getSituacao().getId().equals(ESituacao.CONFIRMAR_SEGUNDO_PAGAMENTO.getFlag())
							|| item.getSituacao().getId().equals(ESituacao.CONFIRMAR_TERCEIRO_PAGAMENTO.getFlag())) {

						valor = valor + item.getValorParcela();
					}
				}
			}
		}

		return valor;

	}

	public void salvarProjeto() {
		try {
			
			if (projeto.getModalidade() == null) {
				FacesUtil.addErrorMessage("Selecione a modalidade do projeto.");
				return;
			}
			
			if (projeto.getTipoProjeto() == null || "vazio".equals(projeto.getTipoProjeto())
					|| projeto.getSubTipoProjeto().isEmpty()) {
				FacesUtil.addErrorMessage("Selecione o tipo de projeto.");
				return;
			}
			
			if (projeto.getTipoPrograma().getId() == null) {
				FacesUtil.addErrorMessage("Informe o tipo de programa.");
				return;
			}

			if (projeto.getAnoReferencia() == null || projeto.getAnoReferencia().equals("")) {
				FacesUtil.addErrorMessage("Informe a data de entrada do projeto.");
				return;
			}

			if (projeto.getNomeProjeto() == null) {
				FacesUtil.addErrorMessage("Informe o nome do projeto.");
				return;
			}

			if (projeto.getNumeroProjetoCliente() == null) {
				FacesUtil.addErrorMessage("Informe o número projeto cliente.");
				return;
			}

			if (projeto.getNumeroProjetoAgrega() == null) {
				FacesUtil.addErrorMessage("Informe o número projeto Agrega.");
				return;
			}
			if (projeto.getNumeroProjetoCliente() == null) {
				FacesUtil.addErrorMessage("Informe o número projeto Cliente.");
				return;
			}

			if (dataEntradaLocal != null) {
				projeto.setDataEntrada(dataEntradaLocal);
			}

			if (ordemServico.getId() == null) {
				FacesUtil.addErrorMessage("Selecione a Ordem de Serviço.");
				return;
			} else {
				projeto.setOrdemServico(ordemServicoService.getById(ordemServico.getId()));
			}

			if (projeto.getPlanejamentos() != null && projeto.getPlanejamentos().getId() == null) {
				projeto.setPlanejamentos(null);
			}

			if (projeto.getSubTipoProjeto() == null || projeto.getSubTipoProjeto().isEmpty()) {
				FacesUtil.addErrorMessage("Selecione o Subtipo de projeto.");
				return;
			}

			if (projeto.getMunicipio() == null
					|| (projeto.getMunicipio() != null && projeto.getMunicipio().getId() == null)) {
				FacesUtil.addErrorMessage("Selecione o município do projeto.");
				return;
			}

			if (projeto.getEstadoDadosTecnico() != null && projeto.getEstadoDadosTecnico().getId() == null) {
				FacesUtil.addErrorMessage("Selecione o estado do projeto.");
				return;
			}

			if (idProfissionalGestor == null) {
				FacesUtil.addErrorMessage("Selecione o gestor do projeto.");
				return;
			}

			
			
			if (primariaKmString != null && !primariaKmString.isEmpty()) {
				primariaKmString = primariaKmString.replace(",", ".");
				projeto.setPrimariaKm(Double.parseDouble(primariaKmString));
			} else {
				FacesUtil.addErrorMessage("O campo primária (KM) é obrigatório.");
				return;
			}

			if (secundariaKmString != null && !secundariaKmString.isEmpty()) {
				secundariaKmString = secundariaKmString.replace(",", ".");
				projeto.setSecundariaKm(Double.parseDouble(secundariaKmString));
			} else {
				FacesUtil.addErrorMessage("O campo secundária (KM) é obrigatório.");
				return;
			}
			if (conjugadaKmString != null && !conjugadaKmString.isEmpty()) {
				conjugadaKmString = conjugadaKmString.replace(",", ".");
				projeto.setConjugadaKm(Double.parseDouble(conjugadaKmString));
			} else {
				FacesUtil.addErrorMessage("O campo conjulgada (KM) é obrigatório.");
				return;
			}

			if (tensaoPrimariaKvString != null && !tensaoPrimariaKvString.isEmpty()) {
				tensaoPrimariaKvString = tensaoPrimariaKvString.replace(",", ".");
				projeto.setTensaoPrimariaKv(Double.parseDouble(tensaoPrimariaKvString));
			} else {
				FacesUtil.addErrorMessage("O campo tensão primária é obrigatório.");
				return;
			}

			if (tensaoSecundariaKVString != null && !tensaoSecundariaKVString.isEmpty()) {
				tensaoSecundariaKVString = tensaoSecundariaKVString.replace(",", ".");
				projeto.setTensaoSecundariaKV(Double.parseDouble(tensaoSecundariaKVString));
			} else {
				FacesUtil.addErrorMessage("O campo tensão secudária é obrigatório.");
				return;
			}

		/*
		 * Caso a modalidade for 'Nova modalidade', nao sera obrigatorio os campos de distancias, pois o calculo nao e feito
		 * mais por ele.	
		 */
		if(projeto.getModalidade() != null && projeto.getModalidade()== 1L){	
			
			if (distanciaSedeMunNaoPavString != null && !distanciaSedeMunNaoPavString.isEmpty()) {
				distanciaSedeMunNaoPavString = distanciaSedeMunNaoPavString.replace(",", ".");
				projeto.setDistanciaSedeMunNaoPav(Double.parseDouble(distanciaSedeMunNaoPavString));
			} else {
				FacesUtil.addErrorMessage("O campo distância não pavimentada é obrigatório.");
				return;
			}

			if (distanciaMunLocalProjPavString != null && !distanciaMunLocalProjPavString.isEmpty()) {
				distanciaMunLocalProjPavString = distanciaMunLocalProjPavString.replace(",", ".");
				projeto.setDistanciaMunLocalProjPav(Double.parseDouble(distanciaMunLocalProjPavString));
			} else {
				FacesUtil.addErrorMessage("O campo distância pavimentada é obrigatório.");
				return;
			}

			if (distanciaMunLocalProjNaoPavString != null && !distanciaMunLocalProjNaoPavString.isEmpty()) {
				distanciaMunLocalProjNaoPavString = distanciaMunLocalProjNaoPavString.replace(",", ".");
				projeto.setDistanciaMunLocalProjNaoPav(Double.parseDouble(distanciaMunLocalProjNaoPavString));
			} else {
				FacesUtil.addErrorMessage("O campo  distância não pavimentada é obrigatório.");
				return;
			}
			if (distanciaSedeMunPavString != null && !distanciaSedeMunPavString.isEmpty()) {
				distanciaSedeMunPavString = distanciaSedeMunPavString.replace(",", ".");
				projeto.setDistanciaSedeMunPav(Double.parseDouble(distanciaMunLocalProjNaoPavString));
			} else {
				FacesUtil.addErrorMessage("O campo distância pavimentada é obrigatório.");
				return;
			}
		}
		
			if (this.projeto.getNomeProjeto() != null) {

				if (idProfissionalGestor != null) {
					projeto.setProfissionalGestor(profissionalService.getById(idProfissionalGestor));
				}

				if (this.ordemServico.getId() != null) {
					ordemServico = ordemServicoService.getById(this.ordemServico.getId());
					projeto.setOrdemServico(ordemServico);
				}

				if (this.ordemServico.getContrato().getId() != null) {
					projeto.setContrato(this.ordemServico.getContrato());
					projeto.setCliente(contratoService.getContratoById(ordemServico.getContrato().getId()).getProposta()
							.getCliente());
				}
				if (this.projeto.getCliente() == null) {
					projeto.setCliente(contratoService.getContratoById(ordemServico.getContrato().getId()).getProposta()
							.getCliente());
				}
				
				if(projeto.getModalidade() == 2L){
					projeto.setValorProjeto(novoCalculoValorProjeto());
				}else{
					projeto.setValorProjeto(calculaValorProjeto());
				}
				projeto.setSituacao(situacaoService.getById(ESituacao.PENDENTE_PRIMEIRO_PAGAMENTO.getFlag()));

				/*
				 * 4 - Iniciado 6 - Relatório em Elaboração 7 - Relatório
				 * concluído 9 - Planejado
				 */
				// Nao pode mais alterar o status se o mesmo estiver como
				// concluido ou em Relatório em Elaboração.

				if (projeto.getStatus() == null || projeto.getStatus().getId() == null) {
					projeto.setStatus(statusService.getById(4L));
				} else if (projeto.getStatus().getId().equals(4L)) {
					projeto.setStatus(statusService.getById(4L));
				}

				projeto.setUsuarioAtualizacao(usuarioLogado);

				if (projeto.getId() == null) {
					projeto.setDataInclusao(new Date());
				} else {
					projeto.setDataAtualizacao(new Date());
				}
				if (projeto.getId() == null) {
					projeto.setJaPlanejado(false);
				}

				if (projeto.getPossuiLPT() == null) {
					projeto.setPossuiLPT(false);
				}
				if (projeto.getEp() == null) {
					projeto.setEp(false);
				}
				if (projeto.getMeioFisico() == null) {
					projeto.setMeioFisico(false);
				}

				if (projeto.getMeioSocioEconomico() == null) {
					projeto.setMeioSocioEconomico(false);
				}

				if (projeto.getSupresaoVegetacao() == null) {
					projeto.setSupresaoVegetacao(false);
				}

				if (projeto.getUnidadaConservacao() == null) {
					projeto.setUnidadaConservacao(false);
				}

				if (projeto.getSitioArquilogico() == null) {
					projeto.setSitioArquilogico(false);
				}

				if (projeto.getVerificaNotificacao() == null) {
					projeto.setVerificaNotificacao(false);
				}
				// verifica os profisisonais, caso seja vazio, seta ID 50 -
				// Vazio(-)
				addProfissionalVazio();

				// verificar se o já existe um projeto com o mesmo numeroProjeto
				// ou numero porjeto Cliente.
				if (projeto.getId() == null) {

					if (projetoService.getByOSByNAgregaByNCliente(null, projeto.getNumeroProjetoAgrega(), null,
							projeto.getSubTipoProjeto(), projeto.getAnoReferencia()) != null) {
						FacesUtil.addErrorMessage("Já existe um projeto com esse Nº Agrega "
								+ projeto.getNumeroProjetoAgrega() + " do SubTipo " + projeto.getSubTipoProjeto()
								+ " na base de dados. Por favor, informe outro número.");
						return;
					}

					if (projetoService.getByOSByNAgregaByNCliente(null, null, projeto.getNumeroProjetoCliente(),
							projeto.getSubTipoProjeto(), projeto.getAnoReferencia()) != null) {
						FacesUtil.addErrorMessage(
								"Já existe um projeto com esse Nº Projeto Cliente " + projeto.getNumeroProjetoCliente()
										+ " na base de dados. Por favor, informe outro número.");
						return;
					}
				}

				if (projeto.getId() != null) {
					projeto = projetoService.atualizar(projeto);
				} else {
					projeto = projetoService.salvar(projeto);
				}
				// cria lista de parcelas pendentes do projeto
				salvarFaturamentos();

				listarFaturamentosByProjeto = faturamentoService.getByProjeto(this.projeto.getId());
				if (listarFaturamentosByProjeto == null) {
					listarFaturamentosByProjeto = new ArrayList<>();
				}
			}

			FacesUtil.addInfoMessage("Dados Cadastro salvo com sucesso.");
			disablePlanejamento = false;
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao Salvar novo Projeto - Erro:" + e.getMessage());
		}
	}
	
	
	

	DecimalFormat df = new DecimalFormat("###,##0.00");

	public String retornarString(Double valor) {
		if (valor != null) {
			// return String.valueOf(valor).replace(".", ",");
			return df.format(valor);
		}
		return "-";
	}

	public String retornarNomeProjeto(Projeto itemExibir) {
		String nomeProjetoCompleto = "";
		if (itemExibir != null) {
			nomeProjetoCompleto = itemExibir.getSubTipoProjeto() + itemExibir.getNumeroProjetoAgrega() + "."
					+ itemExibir.getAnoReferencia();
		}

		return nomeProjetoCompleto;

	}

	public void salvarFaturamentos() {
		Long parcela = 0L;

		for (int i = 0; i < 3; i++) {
			parcela++;

			IFaturamento fatSalvar = faturamentoService.getByProjetoByParcela(this.projeto.getId(), parcela);

			if (fatSalvar == null) {
				fatSalvar = new Faturamento();
			}

			if (fatSalvar.getId() != null) {

				// caso possua faturamentos, so altera se tiver pendentes de uma
				// das tres opcoes pendentes.
				if (fatSalvar.getSituacao().getId().equals(ESituacao.PENDENTE_PRIMEIRO_PAGAMENTO.getFlag())
						|| fatSalvar.getSituacao().getId().equals(ESituacao.PENDENTE_SEGUNDO_PAGAMENTO.getFlag())
						|| fatSalvar.getSituacao().getId().equals(ESituacao.PENDENTE_TERCEIRO_PAGAMENTO.getFlag())) {

					fatSalvar.setProjeto(this.projeto);
					fatSalvar.setContrato(projeto.getContrato());
					fatSalvar.setParcela(parcela);
					fatSalvar.setValorParcela(obterValorbyparcela(parcela));

					// caso nao possua faturamentos.
					if (parcela.equals(ESituacao.PENDENTE_PRIMEIRO_PAGAMENTO.getFlag())) {
						fatSalvar.setSituacao(situacaoService.getById(ESituacao.PENDENTE_PRIMEIRO_PAGAMENTO.getFlag()));
					} else if (parcela.equals(ESituacao.PENDENTE_SEGUNDO_PAGAMENTO.getFlag())) {
						fatSalvar.setSituacao(situacaoService.getById(ESituacao.PENDENTE_SEGUNDO_PAGAMENTO.getFlag()));
					} else if (parcela.equals(ESituacao.PENDENTE_TERCEIRO_PAGAMENTO.getFlag())) {
						fatSalvar.setSituacao(situacaoService.getById(ESituacao.PENDENTE_TERCEIRO_PAGAMENTO.getFlag()));
					}

					faturamentoService.salvar(fatSalvar);
					// this.projeto.setSituacao(fatSalvar.getSituacao());
					// this.projeto = projetoService.salvar(this.projeto);
				}
				// else.. se ja tiver recebido algum valor, nao podera receber
				// outros valores.

			} else {
				// caso seja o primeiro cadastro

				fatSalvar.setProjeto(this.projeto);
				fatSalvar.setContrato(projeto.getContrato());
				fatSalvar.setParcela(parcela);
				fatSalvar.setValorParcela(obterValorbyparcela(parcela));

				// caso nao possua faturamentos.
				if (parcela.equals(ESituacao.PENDENTE_PRIMEIRO_PAGAMENTO.getFlag())) {
					fatSalvar.setSituacao(situacaoService.getById(ESituacao.PENDENTE_PRIMEIRO_PAGAMENTO.getFlag()));
				} else if (parcela.equals(ESituacao.PENDENTE_SEGUNDO_PAGAMENTO.getFlag())) {
					fatSalvar.setSituacao(situacaoService.getById(ESituacao.PENDENTE_SEGUNDO_PAGAMENTO.getFlag()));
				} else if (parcela.equals(ESituacao.PENDENTE_TERCEIRO_PAGAMENTO.getFlag())) {
					fatSalvar.setSituacao(situacaoService.getById(ESituacao.PENDENTE_TERCEIRO_PAGAMENTO.getFlag()));
				}

				faturamentoService.salvar(fatSalvar);

			}
		}

	}

	public void salvarRegistro() {

		if (registoSalvar.getGerouDAE() == null) {
			FacesUtil.addErrorMessage("Preencha o campo: Gerou DAE?");
			return;
		}

		if (registoSalvar.getNumeroProcessoFormado() == null) {
			FacesUtil.addErrorMessage("Preencha o campo: Número Processo Formado");
			return;
		}

		if (registoSalvar.getDataFormacaoProcesso() == null) {
			FacesUtil.addErrorMessage("Preencha o campo: Data Processo Formado");
			return;
		}

		/*
		 * if(registoSalvar.getDataEntrega() == null){
		 * FacesUtil.addErrorMessage("Preencha o campo: Data de Entrega");
		 * return; }
		 */

		if (registoSalvar.getTipoRegistro() == null) {
			FacesUtil.addErrorMessage("Preencha o campo: Tipo de Registro");
			return;
		}

		if (gerouDae && registoSalvar.getValorDAE() == null && registoSalvar.getDataPagamento() == null) {
			FacesUtil.addErrorMessage("Preencha os campos de valor do DAE e Data de Pagamento!");
			return;
		}

		this.registoSalvar.setProjeto(this.projeto);
		registoSalvar = registroService.salvar(registoSalvar);

		Notificacao filtro = new Notificacao();
		filtro.setProjeto(this.projeto);
		listarNotificacao = notificacaoService.getByFiltros(filtro);
		FacesUtil.addInfoMessage("Registro salvo com sucesso.");
	}

	public void salvarNotificacao() {

		if (this.registoSalvar.getId() == null) {
			FacesUtil.addErrorMessage("Preencha todos os campos do registro.");
			return;
		}

		if (this.notificacao.getDataRecebimento() == null) {
			FacesUtil.addErrorMessage("A data de recebimento é obrigatório..");
			RequestContext.getCurrentInstance().execute("PF('dialogRegistroNotificacao').show();");
			return;
		}
		if (this.notificacao.getNumero() == null) {
			FacesUtil.addErrorMessage("O número é obrigatório..");
			RequestContext.getCurrentInstance().execute("PF('dialogRegistroNotificacao').show();");
			return;
		}

		if (this.notificacao.getMotivo() == null) {
			FacesUtil.addErrorMessage("O motivo é obrigatório..");
			RequestContext.getCurrentInstance().execute("PF('dialogRegistroNotificacao').show();");
			return;
		}

		notificacao.setRegistro(this.registoSalvar);
		notificacao.setProjeto(this.projeto);
		notificacaoService.salvar(this.notificacao);
		camposDeNotificacaoObrigatorio = false;
		camposDeRegistoObrigatorio = false;

		Notificacao filtroNot = new Notificacao();
		filtroNot.setProjeto(projeto);
		listarNotificacao = notificacaoService.getByFiltros(filtroNot);

		notificacao = new Notificacao();

	}

	public void excluirTrajeto(TrajetoRede item) {
		try {
			trajetoRedeService.remover(item);
			FacesUtil.addInfoMessage("Trajeto excluído com sucesso.");
			listTrajetoRede = trajetoRedeService.getByProjeto(projeto.getId());
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao excluir: " + e.getMessage());
		}
	}

	public void excluirCoordenada(Coordenada item) {
		try {
			coordenadaService.remover(item);
			FacesUtil.addInfoMessage("Coordenada excluída com sucesso.");
			listCoordenada = coordenadaService.getByProjeto(projeto.getId());
			listCoordenada.sort(comparador);
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao excluir: " + e.getMessage());
		}
	}

	public void excluirNotificacao(Notificacao item) {
		notificacaoService.remover(item);

		Notificacao filtro = new Notificacao();
		filtro.setProjeto(this.projeto);
		listarNotificacao = notificacaoService.getByFiltros(filtro);

		FacesUtil.addInfoMessage("Notificação excluída com sucesso.");
	}

	public void excluirRegistro() {

		Notificacao filtroAux = new Notificacao();
		filtroAux.setRegistro(this.registoSalvar);
		List<Notificacao> listarNotificacaoAUX = notificacaoService.getByFiltros(filtroAux);

		if (listarNotificacaoAUX == null) {
			listarNotificacaoAUX = new ArrayList<>();
		}

		if (listarNotificacaoAUX.size() != 0) {
			FacesUtil.addErrorMessage(
					"O registro não pode ser excluído. Existe(m) associação(ções) com outros notificação(ções).");
			return;
		} else {
			registroService.remover(registoSalvar);
			registoSalvar = new Registro();
			registoSalvar.setProjeto(projeto);

			Notificacao filtro = new Notificacao();
			filtro.setProjeto(this.projeto);
			filtro.setRegistro(registoSalvar);
			listarNotificacao = notificacaoService.getByFiltros(filtro);
			FacesUtil.addInfoMessage("Registro removido com sucesso.");
		}

	}

	public void editarNotificacao(Notificacao item) {
		this.notificacao = item;
	}

	public String retornarTes() {

		return "Teste";
	}

	public void salvarRetificacao() {

		retificacao.setProjeto(this.projeto.getId());

		if (idProfissionalResposanvelResposta != null) {
			retificacao.setResponsavelResposta(profissionalService.getById(idProfissionalResposanvelResposta));
		} else {
			FacesUtil.addErrorMessage("O campo responsável resposta é obrigatório.");
			RequestContext.getCurrentInstance().execute("PF('dialogRetificacao').show();");
		}

		if (idTipoRetificacao != null) {
			retificacao.setTipoRetificacao(tipoRetificacaoService.getById(idTipoRetificacao));
		} else {
			FacesUtil.addErrorMessage("O campo tipo de retificação é obrigatório.");
			RequestContext.getCurrentInstance().execute("PF('dialogRetificacao').show();");
			return;
		}

		if (idStatus != null) {
			retificacao.setStatus(statusService.getById(idStatus));
		} else {
			FacesUtil.addErrorMessage("O campo status é obrigatório.");
			RequestContext.getCurrentInstance().execute("PF('dialogRetificacao').show();");
			return;
		}

		retificacao = retificacaoService.salvar(retificacao);

		projeto.setVerificaNotificacao(true);
		projeto = projetoService.salvar(projeto);

		listRetificacao = retificacaoService.getByProjeto(this.projeto.getId());
		retificacao = new Retificacao();
		idProfissionalResposanvelResposta = null;
		camposDeRegistoObrigatorio = false;
		camposDeNotificacaoObrigatorio = false;
		RequestContext.getCurrentInstance().execute("PF('dialogRetificacao').hide();");

	}

	public void finalizarRetificacao(IRetificacao item) {

		item.setProjeto(this.projeto.getId());
		item.setStatus(statusService.getById(idStatus));
		if (idProfissionalResposanvelResposta != null) {
			retificacao.setResponsavelResposta(profissionalService.getById(idProfissionalResposanvelResposta));
		} else {
			FacesUtil.addErrorMessage("O campo Responsável Resposta é obrigatório.");
			RequestContext.getCurrentInstance().execute("PF('dialogRetificacao').show();");
		}
		item = retificacaoService.salvar(item);
		FacesUtil.addInfoMessage("Operação realizada com sucesso.");
		listRetificacao = retificacaoService.getByProjeto(this.projeto.getId());
		item = new Retificacao();
		RequestContext.getCurrentInstance().execute("PF('dialogRetificacao').hide();");

	}

	public void addCoordenada() {

		coordenada.setProjeto(this.projeto.getId());

		if ((coordenada.getPonto().isEmpty()) && coordenada.getCoordenadaX() == null
				&& coordenada.getCoordenadaX() == null) {
			FacesUtil.addErrorMessage("Os campos Ponto, Coordenda X e Y são obrigatórios.");
			return;
		} else if ((coordenada.getPonto().isEmpty()) && coordenada.getCoordenadaX() == null) {
			FacesUtil.addErrorMessage("Os campos Ponto, Coordenda X são obrigatórios.");
			return;
		} else if ((coordenada.getPonto().isEmpty()) && coordenada.getCoordenadaY() == null) {
			FacesUtil.addErrorMessage("Os campos Ponto, Coordenda Y são obrigatórios.");
			return;
		} else if (coordenada.getCoordenadaX() == null && coordenada.getCoordenadaY() == null) {
			FacesUtil.addErrorMessage("Os campos Coordenda X e Y são obrigatórios.");
			return;
		}

		if (coordenada.getPonto().isEmpty()) {
			FacesUtil.addErrorMessage("O campos Ponto é obrigatório.");
			return;
		}

		if (coordenada.getCoordenadaX() == null) {
			FacesUtil.addErrorMessage("O campo Coordenda X é obrigatório.");
			return;
		}

		if (coordenada.getCoordenadaY() == null) {
			FacesUtil.addErrorMessage("O campo Coordenda Y é obrigatório.");
			return;
		}

		if (coordenada.getDataCadastro() == null) {
			coordenada.setDataCadastro(new Date());
		}
		coordenada.setFuso(fusoInformado);
		coordenadaService.salvar(coordenada);
		coordenada = new Coordenada();
		coordenada.setFuso(fusoInformado);

		if (projeto != null && projeto.getId() != null) {
			listCoordenada = coordenadaService.getByProjeto(projeto.getId());
		}
		listCoordenada.sort(comparador);
		verificarCoordenada();
	}

	Comparator<Coordenada> comparador = new Comparator<Coordenada>() {
		public int compare(Coordenada s1, Coordenada s2) {
			return Integer.compare(Integer.parseInt(s1.getPonto().replaceAll("[^0-9]", "")),
					Integer.parseInt(s2.getPonto().replaceAll("[^0-9]", "")));
		}
	};

	Comparator<TrajetoRede> comparadorTrajeto = new Comparator<TrajetoRede>() {
		public int compare(TrajetoRede t1, TrajetoRede t2) {
			return Integer.compare(Integer.parseInt(t1.getPontoInicio().replaceAll("[^0-9]", "")),
					Integer.parseInt(t2.getPontoInicio().replaceAll("[^0-9]", "")));
		}
	};

	public void addTrajetoRede() {

		listTrajetoRede = trajetoRedeService.getByProjeto(projeto.getId());

		if (trajetoRede.getPontoInicio() == null
				|| (trajetoRede.getPontoInicio() != null && trajetoRede.getPontoInicio().isEmpty())) {
			FacesUtil.addErrorMessage("O campo inícial é obrigatório.");
			return;
		}

		if (trajetoRede.getPontoFim() == null
				|| (trajetoRede.getPontoFim() != null && trajetoRede.getPontoFim().isEmpty())) {
			FacesUtil.addErrorMessage("O campo final é obrigatório.");
			return;
		}
		trajetoRede.setProjeto(this.projeto.getId());

		if (trajetoRede.getDataCadastro() == null) {
			trajetoRede.setDataCadastro(new Date());
		}
		trajetoRedeService.salvar(trajetoRede);
		trajetoRede = new TrajetoRede();

		if (projeto != null && projeto.getId() != null) {
			listTrajetoRede = trajetoRedeService.getByProjeto(projeto.getId());
		}

		verificarTrajeto();
	}

	public void adicionarParcela() {
		parcelaProjeto = new ParcelaProjeto();
		parcelaProjeto.setNumeroParcela(numeroParcela);
		parcelaProjeto.setValorParcela(valorParcela);
		parcelaProjeto.setDataCobranca(dataCobranca);
		parcelaProjeto.setNotaFiscal(notaFiscal);
		parcelaProjeto.setProjeto(this.projeto);
		parcelaProjeto.setObservacao(observacaoParcela);

		listParcelaProjeto.add(parcelaProjeto);
		if (listParcelaProjeto.size() == 3) {
			quitacaoTotal = true;
		} else {
			quitacaoTotal = false;
		}
		numeroParcela = null;
		valorParcela = null;
		dataCobranca = null;
		notaFiscal = null;
		observacaoParcela = null;

	}

	public void preConfirmarPagamento(Faturamento faturamento) {
		this.fatSalvar = faturamento;
		comboSituacoes = situacaoService.getAll();
		comboSituacoesConfirmarPagamento = new ArrayList<>();
		for (Situacao item : comboSituacoes) {

			if (!item.getId().equals(ESituacao.PENDENTE_PRIMEIRO_PAGAMENTO.getFlag())
					&& !item.getId().equals(ESituacao.PENDENTE_SEGUNDO_PAGAMENTO.getFlag())
					&& !item.getId().equals(ESituacao.PENDENTE_TERCEIRO_PAGAMENTO.getFlag())) {

				if (faturamento.getParcela() == 1L) {
					if (ESituacao.PENDENTE_PRIMEIRO_PAGAMENTO.getFlag().equals(item.getId())
							|| ESituacao.PRIMEIRO_FATURAMENTO_CONFIRMADO.getFlag().equals(item.getId())
							|| ESituacao.CONFIRMAR_PRIMEIRO_PAGAMENTO.getFlag().equals(item.getId())) {
						comboSituacoesConfirmarPagamento.add(item);
					}

				} else if (faturamento.getParcela() == 2L) {
					if (ESituacao.PENDENTE_SEGUNDO_PAGAMENTO.getFlag().equals(item.getId())
							|| ESituacao.SEGUNDO_FATURAMENTO_CONFIRMADO.getFlag().equals(item.getId())
							|| ESituacao.CONFIRMAR_SEGUNDO_PAGAMENTO.getFlag().equals(item.getId())) {
						comboSituacoesConfirmarPagamento.add(item);
					}
				} else if (faturamento.getParcela() == 3L) {
					if (ESituacao.PENDENTE_TERCEIRO_PAGAMENTO.getFlag().equals(item.getId())
							|| ESituacao.TERCEIRO_FATURAMENTO_CONFIRMADO.getFlag().equals(item.getId())
							|| ESituacao.CONFIRMAR_TERCEIRO_PAGAMENTO.getFlag().equals(item.getId())) {
						comboSituacoesConfirmarPagamento.add(item);
					}
				}

			}
		}

		RequestContext.getCurrentInstance().execute("PF('dialogInformeNNfDataNF').show();");
	}

	public Double calcularParcela(Projeto itemProjeto) {

		if (itemProjeto != null && itemProjeto.getId() != null) {

			if (itemProjeto.getSituacao().getId().equals(ESituacao.CONFIRMAR_PRIMEIRO_PAGAMENTO.getFlag())
					|| itemProjeto.getSituacao().getId().equals(ESituacao.PENDENTE_PRIMEIRO_PAGAMENTO.getFlag())
					|| itemProjeto.getSituacao().getId().equals(ESituacao.PRIMEIRO_FATURAMENTO_CONFIRMADO.getFlag())) {
				return itemProjeto.getValorProjeto() / PRIMEIRA_PARCELA_PROJETO;
			}
			if (itemProjeto.getSituacao().getId().equals(ESituacao.CONFIRMAR_SEGUNDO_PAGAMENTO.getFlag())
					|| itemProjeto.getSituacao().getId().equals(ESituacao.PENDENTE_SEGUNDO_PAGAMENTO.getFlag())
					|| itemProjeto.getSituacao().getId().equals(ESituacao.SEGUNDO_FATURAMENTO_CONFIRMADO.getFlag())) {
				return itemProjeto.getValorProjeto() / CONSTANTE_SEGUNDA_PARCELA;
			}
			if (itemProjeto.getSituacao().getId().equals(ESituacao.CONFIRMAR_TERCEIRO_PAGAMENTO.getFlag())
					|| itemProjeto.getSituacao().getId().equals(ESituacao.PENDENTE_TERCEIRO_PAGAMENTO.getFlag())
					|| itemProjeto.getSituacao().getId().equals(ESituacao.TERCEIRO_FATURAMENTO_CONFIRMADO.getFlag())) {
				return itemProjeto.getValorProjeto() / CONSTANTE_TERCEIRA_PARCELA;
			}
			if (itemProjeto.getSituacao().getId().equals(ESituacao.QUITADO.getFlag())) {
				return itemProjeto.getValorProjeto();
			}
		}
		return 0.0;
	}

	public String formtoReal(Double valor) {
		DecimalFormat df = new DecimalFormat("0.##");
		String retorno = "";

		if (valor != null) {
			retorno = df.format(valor);
		}
		if (retorno.equals("0")) {
			retorno = "0,00";
		}
		return retorno;
	}

	public void confirmarPagamento() {

		if (faturamento.getDataNF() == null) {
			RequestContext.getCurrentInstance().execute("PF('dialogInformeNNfDataNF').show();");
			FacesUtil.addErrorMessage("O campo data de pagamento é obrigatório.");
			return;
		}
		if (faturamento.getNumeroNF() == null) {
			RequestContext.getCurrentInstance().execute("PF('dialogInformeNNfDataNF').show();");
			FacesUtil.addErrorMessage("O campo número nota fiscal é obrigatório.");
			return;
		}

		if (filtroSituacaoFaturar == null) {
			RequestContext.getCurrentInstance().execute("PF('dialogInformeNNfDataNF').show();");
			FacesUtil.addErrorMessage("O campo situação é obrigatório.");
			return;
		}

		if (filtroSituacaoFaturar.equals(ESituacao.PENDENTE_PRIMEIRO_PAGAMENTO.getFlag())
				|| filtroSituacaoFaturar.equals(ESituacao.PENDENTE_SEGUNDO_PAGAMENTO.getFlag())
				|| filtroSituacaoFaturar.equals(ESituacao.PENDENTE_TERCEIRO_PAGAMENTO.getFlag())) {
			RequestContext.getCurrentInstance().execute("PF('dialogInformeNNfDataNF').show();");
			FacesUtil.addErrorMessage("Escolha uma situção diferente de pendente de pagamento.");
		}
		IFaturamento fatSalvarAUX = null;
		if (fatSalvar.getParcela() == 2) {
			fatSalvarAUX = faturamentoService.getByProjetoByParcela(projeto.getId(), 1L);
			if (fatSalvarAUX.getNumeroNF() == null) {
				FacesUtil.addErrorMessage("Realize o faturamento da parcela anterior: Parcela de nº 1.");
				RequestContext.getCurrentInstance().execute("PF('dialogInformeNNfDataNF').show();");
				return;
			}
		} else if (fatSalvar.getParcela() == 3) {
			fatSalvarAUX = faturamentoService.getByProjetoByParcela(projeto.getId(), 2L);
			if (fatSalvarAUX.getNumeroNF() == null) {
				RequestContext.getCurrentInstance().execute("PF('dialogInformeNNfDataNF').show();");
				FacesUtil.addErrorMessage("Realize o faturamento da parcela anterior: Parcela de nº 2.");
				return;
			}

		}

		if (projeto != null && projeto.getId() != null) {
			fatSalvar = faturamentoService.getByProjetoByParcela(projeto.getId(), fatSalvar.getParcela());

			if (fatSalvar != null) {
				fatSalvar.setDataNF(faturamento.getDataNF());
				fatSalvar.setNumeroNF(faturamento.getNumeroNF());
				fatSalvar.setSituacao(situacaoService.getById(filtroSituacaoFaturar));
				fatSalvar.setContrato(projeto.getContrato());
				fatSalvar = faturamentoService.salvar(fatSalvar);

				// atualiza o saldo do contrato.
				if (projeto.getContrato() != null && projeto.getContrato().getSaldoContrato() != null
						&& fatSalvar.getValorParcela() != null) {
					projeto.getContrato()
							.setSaldoContrato(projeto.getContrato().getSaldoContrato() - fatSalvar.getValorParcela());
					contratoService.salvar(projeto.getContrato());
				}

				// atualiza projeto
				this.projeto.setSituacao(fatSalvar.getSituacao());
				// projeto.setStatus(statusService.getById(10L)); // faturado.
				projetoService.salvar(projeto);
				this.projeto = projetoService.salvar(this.projeto);

				FacesUtil.addInfoMessage("Faturamento salvo com sucesso.");
			} else {
				FacesUtil.addErrorMessage("Salve o projeto antes de executar o faturamento.");
			}
		}
		listarFaturamentosByProjeto = faturamentoService.getByProjeto(this.projeto.getId());
		faturamento = new Faturamento();
		faturamento.setResponsavel(new Profissional());
		faturamento.setProjeto(new Projeto());
		faturamento.setSituacao(new Situacao());
		fatSalvar = new Faturamento();
		fatSalvar.setResponsavel(new Profissional());
		fatSalvar.setSituacao(new Situacao());
		fatSalvar.setProjeto(new Projeto());
	}

	public void salvarDadosCampo() {
		// atualiza projeto

		/*
		 * 6 - Relatório em Elaboração 7 - Relatório concluído 9 - Planejado 4 -
		 * Iniciado 5 - Em campo
		 * 
		 */
		// Nao pode mais alterar o status se o mesmo estiver como concluido ou
		// em Relatório em Elaboração.
		if (projeto.getStatus().getId().equals(4L) || projeto.getStatus().getId().equals(5L)
				|| projeto.getStatus().getId().equals(9L) || projeto.getStatus().getId() == null) {

			if (projeto.getDataIncialCampo() == null && projeto.getDataFinalCampo() == null) {
				projeto.setStatus(statusService.getById(5L)); // Em campo
			} else if (projeto.getDataIncialCampo() != null && projeto.getDataFinalCampo() != null) {
				projeto.setStatus(statusService.getById(6L)); // Relatório em
			}
		}
		
		if (projeto.getDataIncialCampo() != null){
			verificacaoSalvar.setDtInicioCampo(projeto.getDataIncialCampo());
		} 
		if (projeto.getDataFinalCampo()!= null){
			verificacaoSalvar.setDtFimCampo(projeto.getDataFinalCampo());
		}

		this.projeto = projetoService.salvar(this.projeto);

		FacesUtil.addInfoMessage("Dados Campo salvo com sucesso.");

	}

	public void validarTelResponsavel() {
		this.projeto.setTelResponsavelEletrico(validaTelefone(this.projeto.getTelResponsavelEletrico()));
	}

	// Validação de Telefone Fixo ou Celular
	public String validaTelefone(String tel) {
		String valor = "";
		valor = tel;
		valor = valor.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");

		for (int i = 0; i < valor.length(); i++) {
			if (Character.isDigit(valor.charAt(i)) == false) {
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('dialoTelContato').show();");
				break;
			}
		}

		if (!valor.isEmpty()) {
			if (valor.length() < 10) {
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('dialoTelContato').show();");

			} else if (valor.length() > 11) {
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('dialoTelContato').show();");
			}
		}

		String texto = "";
		if (valor.toString() != null && !valor.toString().equals("")) {
			texto = valor.toString();
			if (texto.length() == 10) {
				texto = "(" + texto.substring(0, 2) + ") " + texto.substring(2, 6) + "-"
						+ texto.substring(6, texto.length());
			} else if (texto.length() == 11) {
				texto = "(" + texto.substring(0, 2) + ") " + texto.substring(2, 7) + "-"
						+ texto.substring(7, texto.length());
			}
		}
		return texto;
	}

	public void caracteristicasSelecionadasRelatorio() {
		listCaracteristicas = new ArrayList<>();

		if (selectedRelatorioNodes.length > 0) {
			for (ECaracteristicaRelatorio item : ECaracteristicaRelatorio.values()) {
				for (TreeNode selecionado : selectedRelatorioNodes) {
					if (item.getDescricao().equals(selecionado.getData().toString())) {
						if (item.getDescricao().equals(ECaracteristicaRelatorio.EPI.getDescricao())) {
							this.caracteristicasProjeto.setRelEpi(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaRelatorio.EV.getDescricao())) {
							this.caracteristicasProjeto.setRelEv(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaRelatorio.RO.getDescricao())) {
							this.caracteristicasProjeto.setRelRo(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaRelatorio.CASA_EM_APP.getDescricao())) {
							this.caracteristicasProjeto.setRelCasaEmAPP(true);
							break;
						} else if (item.getDescricao()
								.equals(ECaracteristicaRelatorio.TAM_LINHA_DIFERENTE.getDescricao())) {
							this.caracteristicasProjeto.setRelTamanhoLinhaDiferente(true);
							break;
						} else if (item.getDescricao()
								.equals(ECaracteristicaRelatorio.OBRA_CONSTRUIDA.getDescricao())) {
							this.caracteristicasProjeto.setRelObraConstruida(true);
							break;
						} else if (item.getDescricao()
								.equals(ECaracteristicaRelatorio.VISTORIA_NAO_REALIZADA.getDescricao())) {
							this.caracteristicasProjeto.setRelVistoriaNaoRealizada(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaRelatorio.OUTROS.getDescricao())) {
							this.caracteristicasProjeto.setRelOutros(true);
							break;
						/*} else if (item.getDescricao().equals(ECaracteristicaRelatorio.DIAP_SEIA.getDescricao())) {
							this.caracteristicasProjeto.setRelDiapSeia(true);
							break;*/
						} else if (item.getDescricao().equals(ECaracteristicaRelatorio.RCA.getDescricao())) {
							this.caracteristicasProjeto.setRca(true);
							break;
						/*} else if (item.getDescricao().equals(ECaracteristicaRelatorio.ICMBIO.getDescricao())) {
							this.caracteristicasProjeto.setUnidIcmbio(true);
							break;*/
						} else if (item.getDescricao().equals(ECaracteristicaRelatorio.RIUC.getDescricao())) {
							this.caracteristicasProjeto.setRiuc(true);
							break;
						}
					} else {
						if (item.getDescricao().equals(ECaracteristicaRelatorio.EPI.getDescricao())) {
							this.caracteristicasProjeto.setRelEpi(false);
						} else if (item.getDescricao().equals(ECaracteristicaRelatorio.EV.getDescricao())) {
							this.caracteristicasProjeto.setRelEv(false);
						} else if (item.getDescricao().equals(ECaracteristicaRelatorio.RO.getDescricao())) {
							this.caracteristicasProjeto.setRelRo(false);
						} else if (item.getDescricao().equals(ECaracteristicaRelatorio.CASA_EM_APP.getDescricao())) {
							this.caracteristicasProjeto.setRelCasaEmAPP(false);
						} else if (item.getDescricao()
								.equals(ECaracteristicaRelatorio.TAM_LINHA_DIFERENTE.getDescricao())) {
							this.caracteristicasProjeto.setRelTamanhoLinhaDiferente(false);
						} else if (item.getDescricao()
								.equals(ECaracteristicaRelatorio.OBRA_CONSTRUIDA.getDescricao())) {
							this.caracteristicasProjeto.setRelObraConstruida(false);
						} else if (item.getDescricao()
							.equals(ECaracteristicaRelatorio.VISTORIA_NAO_REALIZADA.getDescricao())) {
							this.caracteristicasProjeto.setRelVistoriaNaoRealizada(false);
						} else if (item.getDescricao().equals(ECaracteristicaRelatorio.OUTROS.getDescricao())) {
							this.caracteristicasProjeto.setRelOutros(false);
						/*} else if (item.getDescricao().equals(ECaracteristicaRelatorio.DIAP_SEIA.getDescricao())) {
							this.caracteristicasProjeto.setRelDiapSeia(false);*/
						} else if (item.getDescricao().equals(ECaracteristicaRelatorio.RCA.getDescricao())) {
							this.caracteristicasProjeto.setRca(false);
						/*} else if (item.getDescricao().equals(ECaracteristicaRelatorio.ICMBIO.getDescricao())) {
							this.caracteristicasProjeto.setUnidIcmbio(false);*/
						} else if (item.getDescricao().equals(ECaracteristicaRelatorio.RIUC.getDescricao())) {
							this.caracteristicasProjeto.setRiuc(false);
						}
					}
				}
			}
		} else {
			this.caracteristicasProjeto.setRelEpi(false);
			this.caracteristicasProjeto.setRelEv(false);
			this.caracteristicasProjeto.setRiuc(false);
			this.caracteristicasProjeto.setRelRo(false);
			this.caracteristicasProjeto.setRelCasaEmAPP(false);
			this.caracteristicasProjeto.setRelTamanhoLinhaDiferente(false);
			this.caracteristicasProjeto.setRelObraConstruida(false);
			this.caracteristicasProjeto.setRelVistoriaNaoRealizada(false);
			this.caracteristicasProjeto.setRelOutros(false);
			/*this.caracteristicasProjeto.setRelDiapSeia(false);*/
			this.caracteristicasProjeto.setRca(false);
			//this.caracteristicasProjeto.setUnidIcmbio(false);
			this.caracteristicasProjeto.setRiuc(false);
		}
	}
	
	public void caracteristicasSelecionadasIntervencao() {
		listCaracteristicas = new ArrayList<>();

		if (selectedIntervecaoNodes.length > 0) {
			for (ECaracteristicaIntervencao item : ECaracteristicaIntervencao.values()) {
				for (TreeNode selecionado : selectedIntervecaoNodes) {
					if (item.getDescricao().equals(selecionado.getData().toString())) {
						if (item.getDescricao().equals(ECaracteristicaIntervencao.DIAP_SEIA.getDescricao())) {
							this.caracteristicasProjeto.setRelDiapSeia(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaIntervencao.SEIA.getDescricao())) {
							this.caracteristicasProjeto.setSeia(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaIntervencao.AREA_TORNO_LAGO.getDescricao())) {
							this.caracteristicasProjeto.setAreaLago(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaIntervencao.FAIXA_MARGINAL.getDescricao())) {
							this.caracteristicasProjeto.setFaixaMarginal(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaIntervencao.TOPO_MORRO.getDescricao())) {
							this.caracteristicasProjeto.setTopoMorro(true);
							break;
						}
					} else {
						if (item.getDescricao().equals(ECaracteristicaIntervencao.DIAP_SEIA.getDescricao())) {
							this.caracteristicasProjeto.setRelDiapSeia(false);
						} else if (item.getDescricao().equals(ECaracteristicaIntervencao.SEIA.getDescricao())) {
							this.caracteristicasProjeto.setSeia(false);
						} else if (item.getDescricao().equals(ECaracteristicaIntervencao.AREA_TORNO_LAGO.getDescricao())) {
							this.caracteristicasProjeto.setAreaLago(false);
						} else if (item.getDescricao().equals(ECaracteristicaIntervencao.FAIXA_MARGINAL.getDescricao())) {
							this.caracteristicasProjeto.setFaixaMarginal(false);
						} else if (item.getDescricao().equals(ECaracteristicaIntervencao.TOPO_MORRO.getDescricao())) {
							this.caracteristicasProjeto.setTopoMorro(false);
						}
					}
				}
			}
		} else {
			this.caracteristicasProjeto.setRelDiapSeia(false);
			this.caracteristicasProjeto.setSeia(false);
			this.caracteristicasProjeto.setAreaLago(false);
			this.caracteristicasProjeto.setFaixaMarginal(false);
			this.caracteristicasProjeto.setTopoMorro(false);
		}
	}

	public void caracteristicasSelecionadasMapas() {
		listCaracteristicas = new ArrayList<>();

		if (selectedMapasNodes.length > 0) {
			for (ECaracteristicaMapas item : ECaracteristicaMapas.values()) {
				for (TreeNode selecionado : selectedMapasNodes) {
					if (item.getDescricao().equals(selecionado.getData().toString())) {
						if (item.getDescricao().equals(ECaracteristicaMapas.LOCALIZACAO.getDescricao())) {
							this.caracteristicasProjeto.setMapaLocalizacao(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaMapas.HIDROGRAFIA.getDescricao())) {
							this.caracteristicasProjeto.setMapaHidrografia(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaMapas.UND_CONSERVACAO.getDescricao())) {
							this.caracteristicasProjeto.setUnidConservacao(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaMapas.VEGETACAO.getDescricao())) {
							this.caracteristicasProjeto.setMapaVegetacao(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaMapas.LEI_MATA_ATLANTICA.getDescricao())) {
							this.caracteristicasProjeto.setMapaLeiMataAtlantica(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaMapas.AREA_ESPECIAL.getDescricao())) {
							this.caracteristicasProjeto.setMapaAreaEspecial(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaMapas.ASSENTAMENTO.getDescricao())) {
							this.caracteristicasProjeto.setMapaAssentamento(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaMapas.TERRA_INDIGNADA.getDescricao())) {
							this.caracteristicasProjeto.setMapaTerraIndigina(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaMapas.QUILOMBOLA.getDescricao())) {
							this.caracteristicasProjeto.setMapaQuilombola(true);
							break;
						}
					} else {
						if (item.getDescricao().equals(ECaracteristicaMapas.LOCALIZACAO.getDescricao())) {
							this.caracteristicasProjeto.setMapaLocalizacao(false);
						} else if (item.getDescricao().equals(ECaracteristicaMapas.HIDROGRAFIA.getDescricao())) {
							this.caracteristicasProjeto.setMapaHidrografia(false);
						} else if (item.getDescricao().equals(ECaracteristicaMapas.UND_CONSERVACAO.getDescricao())) {
							this.caracteristicasProjeto.setUnidConservacao(false);
						} else if (item.getDescricao().equals(ECaracteristicaMapas.VEGETACAO.getDescricao())) {
							this.caracteristicasProjeto.setMapaVegetacao(false);
						} else if (item.getDescricao().equals(ECaracteristicaMapas.LEI_MATA_ATLANTICA.getDescricao())) {
							this.caracteristicasProjeto.setMapaLeiMataAtlantica(false);
						} else if (item.getDescricao().equals(ECaracteristicaMapas.AREA_ESPECIAL.getDescricao())) {
							this.caracteristicasProjeto.setMapaAreaEspecial(false);
						} else if (item.getDescricao().equals(ECaracteristicaMapas.ASSENTAMENTO.getDescricao())) {
							this.caracteristicasProjeto.setMapaAssentamento(false);
						} else if (item.getDescricao().equals(ECaracteristicaMapas.TERRA_INDIGNADA.getDescricao())) {
							this.caracteristicasProjeto.setMapaTerraIndigina(false);
						} else if (item.getDescricao().equals(ECaracteristicaMapas.QUILOMBOLA.getDescricao())) {
							this.caracteristicasProjeto.setMapaQuilombola(false);
						}
					}
				}
			}
		} else {
			this.caracteristicasProjeto.setMapaLocalizacao(false);
			this.caracteristicasProjeto.setMapaHidrografia(false);
			this.caracteristicasProjeto.setUnidConservacao(false);
			this.caracteristicasProjeto.setMapaVegetacao(false);
			this.caracteristicasProjeto.setMapaLeiMataAtlantica(false);
			this.caracteristicasProjeto.setMapaAreaEspecial(false);
			this.caracteristicasProjeto.setMapaAssentamento(false);
			this.caracteristicasProjeto.setMapaTerraIndigina(false);
			this.caracteristicasProjeto.setMapaQuilombola(false);
		}
	}

	public void caracteristicasSelecionadasUnidadeConsevacao() {
		listCaracteristicas = new ArrayList<>();

		if (selectedUnidadeConservacaoNodes.length > 0) {
			for (ECaracteristicaUnidConservacao item : ECaracteristicaUnidConservacao.values()) {
				for (TreeNode selecionado : selectedUnidadeConservacaoNodes) {
					if (item.getDescricao().equals(selecionado.getData().toString())) {
						if (item.getDescricao().equals(ECaracteristicaUnidConservacao.FEDERAL.getDescricao())) {
							this.caracteristicasProjeto.setUndConservFederal(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaUnidConservacao.ESTADUAL.getDescricao())) {
							this.caracteristicasProjeto.setUndConservEstadual(true);
							break;
						} else if (item.getDescricao()
								.equals(ECaracteristicaUnidConservacao.MUNICIPAL.getDescricao())) {
							this.caracteristicasProjeto.setUndConservMunicipal(true);
							break;
						} else if (item.getDescricao().equals(ECaracteristicaUnidConservacao.PARTICULAR.getDescricao())) {
							this.caracteristicasProjeto.setParticular(true);
							break;
						}
					} else {
						if (item.getDescricao().equals(ECaracteristicaUnidConservacao.FEDERAL.getDescricao())) {
							this.caracteristicasProjeto.setUndConservFederal(false);
						} else if (item.getDescricao().equals(ECaracteristicaUnidConservacao.ESTADUAL.getDescricao())) {
							this.caracteristicasProjeto.setUndConservEstadual(false);
						} else if (item.getDescricao()
								.equals(ECaracteristicaUnidConservacao.MUNICIPAL.getDescricao())) {
							this.caracteristicasProjeto.setUndConservMunicipal(false);
						} else if (item.getDescricao().equals(ECaracteristicaUnidConservacao.PARTICULAR.getDescricao())) {
							this.caracteristicasProjeto.setParticular(false);	
						}
					}
				}
			}
		} else {
			this.caracteristicasProjeto.setUndConservFederal(false);
			this.caracteristicasProjeto.setUndConservEstadual(false);
			this.caracteristicasProjeto.setUndConservMunicipal(false);
			this.caracteristicasProjeto.setParticular(false);
		}
	}

	public void VerificaCaracteristicasMarcadasRelatorio() {
		CaracteristicaProjeto itensMarcados = new CaracteristicaProjeto();
		if (this.projeto.getId() != null) {
			itensMarcados = caracteristicasProjetoService.getByProjeto(this.projeto.getId());

			if (itensMarcados != null) {
				// Relatorio
				epi.setSelected(itensMarcados.getRelEpi());
				ev.setSelected(itensMarcados.getRelEv());
				ctgaSeia.setSelected(itensMarcados.getRelEv());
				if (itensMarcados.getRelRo() != null) {
					ro.setSelected(itensMarcados.getRelRo());
				}
				casaEmApp.setSelected(itensMarcados.getRelCasaEmAPP());
				tamLinhaDiferente.setSelected(itensMarcados.getRelTamanhoLinhaDiferente());
				obraConstruida.setSelected(itensMarcados.getRelObraConstruida());
				vistoriaNaoREalizada.setSelected(itensMarcados.getRelVistoriaNaoRealizada());
				outros.setSelected(itensMarcados.getRelOutros());
				diadSeia.setSelected(itensMarcados.getRelDiapSeia());
				rca.setSelected(itensMarcados.getRca());
				//icmbio.setSelected(itensMarcados.getUnidIcmbio());
				riuc.setSelected(itensMarcados.getRiuc());
				seia.setSelected(itensMarcados.getSeia());
				areaLago.setSelected(itensMarcados.getAreaLago());
				faixaMarginal.setSelected(itensMarcados.getFaixaMarginal());
				topoMorro.setSelected(itensMarcados.getTopoMorro());


				// Mapas
				localizacao.setSelected(itensMarcados.getMapaLocalizacao());
				hidrografia.setSelected(itensMarcados.getMapaHidrografia());
				undDeConsevacao.setSelected(itensMarcados.getUnidConservacao());
				vegetacao.setSelected(itensMarcados.getMapaVegetacao());
				leiMataAtlantica.setSelected(itensMarcados.getMapaLeiMataAtlantica());
				areaEspecial.setSelected(itensMarcados.getMapaAreaEspecial());
				assentamento.setSelected(itensMarcados.getMapaAssentamento());
				terraIndigina.setSelected(itensMarcados.getMapaTerraIndigina());
				quilombola.setSelected(itensMarcados.getMapaQuilombola());

				// unidade Consevação
				federal.setSelected(itensMarcados.getUndConservFederal());
				estadual.setSelected(itensMarcados.getUndConservEstadual());
				municipal.setSelected(itensMarcados.getUndConservMunicipal());
				particular.setSelected(itensMarcados.getParticular());

			}
		}
	}

	public void validarValorProjeto() {

	}

	public void salvarCaracteristicas() {
		caracteristicasProjeto.setProjeto(this.projeto);

		caracteristicasSelecionadasRelatorio();
		caracteristicasSelecionadasMapas();
		caracteristicasSelecionadasUnidadeConsevacao();
		caracteristicasSelecionadasIntervencao();

		this.caracteristicasProjeto = caracteristicasProjetoService.salvar(this.caracteristicasProjeto);
		this.projeto.setCaracteristicasProjeto(this.caracteristicasProjeto);
		this.projeto = projetoService.salvar(this.projeto);
		FacesUtil.addInfoMessage("Dados Campo salvo com sucesso.");
	}

	public void voltarProjeto(String tipo) {
		camposDeRegistoObrigatorio = false;
		camposDeNotificacaoObrigatorio = false;
		if (tipo.equalsIgnoreCase("retificacao")) {
			RequestContext.getCurrentInstance().execute("PF('dialogRetificacao').hide();");
		} else if (tipo.equalsIgnoreCase("seia")) {
			RequestContext.getCurrentInstance().execute("PF('dialogRegistroSeia').hide();");
		} else if (tipo.equalsIgnoreCase("diap")) {
			RequestContext.getCurrentInstance().execute("PF('dialogRegistroDiap').hide();");
		} else if (tipo.equalsIgnoreCase("notificacao")) {
			RequestContext.getCurrentInstance().execute("PF('dialogRegistroNotificacao').hide();");
		}

	}

	public void cancelar() {
		cadastrarProjeto = false;
		pesquisar = true;
		idStatusProjeto = null;
		visualizarProjeto = false;
		projeto = new Projeto();
		verificacao = new VerificacaoProfissional();

		listRetificacao = null;
		listCoordenada = null;
		listTrajetoRede = null;
		listarNotificacao = null;
		listarFaturamentosByProjeto = null;
		idProfEPIConclusao = null;
		idProMapConclusao = null;
		idProCtgalConclusao = null;
		idProfVerificacaoConclusao = null;
		idProfissionalGestor = null;
		primariaKmString = null;
		primariaKmString = null;
		secundariaKmString = null;
		tensaoPrimariaKvString = null;
		conjugadaKmString = null;
		tensaoSecundariaKVString = null;
		distanciaSedeMunPavString = null;
		distanciaSedeMunNaoPavString = null;
		distanciaMunLocalProjPavString = null;
		distanciaMunLocalProjNaoPavString = null;
		idProMap = null;
		idProCtgal = null;
		idProfVerificacao = null;
		idProfEPI = null;
		dataEntrega = null;
		fusoInformado = null;
		selectedRelatorioNodes = null;
		selectedMapasNodes = null;
		selectedUnidadeConservacaoNodes = null;
		selectedIntervecaoNodes = null;
		listRetificacao = new ArrayList<>();
		rootRelatorio = null;
		rootMapas = null;
		rootUnidadeConservacao = null;
		rootIntervencao = null;
		dataEntradaLocal = null;
		// atualiza a busca
		if(btProjetos.equalsIgnoreCase("Voltar")){
			buscar();
		}
		btProjetos = "Cancelar";
	}

	public IProjeto getProjeto() {
		return projeto;
	}

	public void setProjeto(IProjeto projeto) {
		this.projeto = projeto;
	}

	public IProfissional getProfissional() {
		return profissional;
	}

	public void setProfissional(IProfissional profissional) {
		this.profissional = profissional;
	}

	public List<Profissional> getComboProfisssional() {
		return comboProfisssional;
	}

	public void setComboProfisssional(List<Profissional> comboProfisssional) {
		this.comboProfisssional = comboProfisssional;
	}

	public List<String> getComboTipoProjeto() {
		return comboTipoProjeto;
	}

	public void setComboTipoProjeto(List<String> comboTipoProjeto) {
		this.comboTipoProjeto = comboTipoProjeto;
	}

	public List<ETipoSubProjeto> getComboSubTipoProjeto() {
		return comboSubTipoProjeto;
	}

	public void setComboSubTipoProjeto(List<ETipoSubProjeto> comboSubTipoProjeto) {
		this.comboSubTipoProjeto = comboSubTipoProjeto;
	}

	public Boolean getDisableSubProjeto() {
		return disableSubProjeto;
	}

	public void setDisableSubProjeto(Boolean disableSubProjeto) {
		this.disableSubProjeto = disableSubProjeto;
	}

	public List<Estado> getComboEstado() {
		return comboEstado;
	}

	public void setComboEstado(List<Estado> comboEstado) {
		this.comboEstado = comboEstado;
	}

	public List<Cidade> getComboCidade() {
		return comboCidade;
	}

	public void setComboCidade(List<Cidade> comboCidade) {
		this.comboCidade = comboCidade;
	}

	public List<Regiao> getComboRegiao() {
		return comboRegiao;
	}

	public void setComboRegiao(List<Regiao> comboRegiao) {
		this.comboRegiao = comboRegiao;
	}

	public Boolean getNovo() {
		return novo;
	}

	public void setNovo(Boolean novo) {
		this.novo = novo;
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

	public Cliente getFiltroCliente() {
		return filtroCliente;
	}

	public void setFiltroCliente(Cliente filtroCliente) {
		this.filtroCliente = filtroCliente;
	}

	public List<Projeto> getComboProjetos() {
		return comboProjetos;
	}

	public void setComboProjetos(List<Projeto> comboProjetos) {
		this.comboProjetos = comboProjetos;
	}

	public List<Cliente> getComboClientes() {
		return comboClientes;
	}

	public void setComboClientes(List<Cliente> comboClientes) {
		this.comboClientes = comboClientes;
	}

	public Integer getQtdCadastradaProjeto() {
		return qtdCadastradaProjeto;
	}

	public void setQtdCadastradaProjeto(Integer qtdCadastradaProjeto) {
		this.qtdCadastradaProjeto = qtdCadastradaProjeto;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public Boolean getEstudoESV() {
		return estudoESV;
	}

	public void setEstudoESV(Boolean estudoESV) {
		this.estudoESV = estudoESV;
	}

	public Boolean getDisableCity() {
		return disableCity;
	}

	public void setDisableCity(Boolean disableCity) {
		this.disableCity = disableCity;
	}

	public List<Contrato> getComboContrato() {
		return comboContrato;
	}

	public void setComboContrato(List<Contrato> comboContrato) {
		this.comboContrato = comboContrato;
	}

	public IOrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(IOrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public List<OrdemServico> getOrdemServicos() {
		return ordemServicos;
	}

	public void setOrdemServicos(List<OrdemServico> ordemServicos) {
		this.ordemServicos = ordemServicos;
	}

	public Boolean getCadastrarOS() {
		return cadastrarOS;
	}

	public void setCadastrarOS(Boolean cadastrarOS) {
		this.cadastrarOS = cadastrarOS;
	}

	public Boolean getCadastrarProjeto() {
		return cadastrarProjeto;
	}

	public void setCadastrarProjeto(Boolean cadastrarProjeto) {
		this.cadastrarProjeto = cadastrarProjeto;
	}

	public Boolean getAbrirEditarOS() {
		return abrirEditarOS;
	}

	public void setAbrirEditarOS(Boolean abrirEditarOS) {
		this.abrirEditarOS = abrirEditarOS;
	}

	public Integer getQtdRestanteACadastrar() {
		return qtdRestanteACadastrar;
	}

	public void setQtdRestanteACadastrar(Integer qtdRestanteACadastrar) {
		this.qtdRestanteACadastrar = qtdRestanteACadastrar;
	}

	public List<MacroRegiao> getComboMacroRegiao() {
		return comboMacroRegiao;
	}

	public void setComboMacroRegiao(List<MacroRegiao> comboMacroRegiao) {
		this.comboMacroRegiao = comboMacroRegiao;
	}

	public List<Municipio> getComboMunicipio() {
		return comboMunicipio;
	}

	public void setComboMunicipio(List<Municipio> comboMunicipio) {
		this.comboMunicipio = comboMunicipio;
	}

	public Boolean getDisableMacroRegiao() {
		return disableMacroRegiao;
	}

	public void setDisableMacroRegiao(Boolean disableMacroRegiao) {
		this.disableMacroRegiao = disableMacroRegiao;
	}

	public Boolean getDisableMunicipio() {
		return disableMunicipio;
	}

	public void setDisableMunicipio(Boolean disableMunicipio) {
		this.disableMunicipio = disableMunicipio;
	}

	public Boolean getViewProjeto() {
		return viewProjeto;
	}

	public void setViewProjeto(Boolean viewProjeto) {
		this.viewProjeto = viewProjeto;
	}

	public List<String> getSelectedMotivo() {
		return selectedMotivo;
	}

	public void setSelectedMotivo(List<String> selectedMotivo) {
		this.selectedMotivo = selectedMotivo;
	}

	public List<MotivoRetificacao> getListMotivo() {
		return listMotivo;
	}

	public void setListMotivo(List<MotivoRetificacao> listMotivo) {
		this.listMotivo = listMotivo;
	}

	public String getSelectedPesquisarPor() {
		return selectedPesquisarPor;
	}

	public void setSelectedPesquisarPor(String selectedPesquisarPor) {
		this.selectedPesquisarPor = selectedPesquisarPor;
	}

	public IEstadoService getEstadoService() {
		return estadoService;
	}

	public void setEstadoService(IEstadoService estadoService) {
		this.estadoService = estadoService;
	}

	public Boolean getExibirListPorOS() {
		return exibirListPorOS;
	}

	public void setExibirListPorOS(Boolean exibirListPorOS) {
		this.exibirListPorOS = exibirListPorOS;
	}

	public Boolean getExibirListPorProjeto() {
		return exibirListPorProjeto;
	}

	public void setExibirListPorProjeto(Boolean exibirListPorProjeto) {
		this.exibirListPorProjeto = exibirListPorProjeto;
	}

	public IProfissionalService getProfissionalService() {
		return profissionalService;
	}

	public void setProfissionalService(IProfissionalService profissionalService) {
		this.profissionalService = profissionalService;
	}

	public List<Coordenada> getListCoordenada() {
		return listCoordenada;
	}

	public void setListCoordenada(List<Coordenada> listCoordenada) {
		this.listCoordenada = listCoordenada;
	}

	public List<TrajetoRede> getListTrajetoRede() {
		return listTrajetoRede;
	}

	public void setListTrajetoRede(List<TrajetoRede> listTrajetoRede) {
		this.listTrajetoRede = listTrajetoRede;
	}

	public Boolean getDisablePlanejamento() {
		return disablePlanejamento;
	}

	public void setDisablePlanejamento(Boolean disablePlanejamento) {
		this.disablePlanejamento = disablePlanejamento;
	}

	public Long getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Long numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public Date getDataCobranca() {
		return dataCobranca;
	}

	public void setDataCobranca(Date dataCobranca) {
		this.dataCobranca = dataCobranca;
	}

	public Long getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(Long notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Boolean getQuitacaoTotal() {
		return quitacaoTotal;
	}

	public void setQuitacaoTotal(Boolean quitacaoTotal) {
		this.quitacaoTotal = quitacaoTotal;
	}

	public String getObservacaoParcela() {
		return observacaoParcela;
	}

	public void setObservacaoParcela(String observacaoParcela) {
		this.observacaoParcela = observacaoParcela;
	}

	public Double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(Double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public List<IParcelaProjeto> getListParcelaProjeto() {
		return listParcelaProjeto;
	}

	public void setListParcelaProjeto(List<IParcelaProjeto> listParcelaProjeto) {
		this.listParcelaProjeto = listParcelaProjeto;
	}

	public List<Retificacao> getListRetificacao() {
		return listRetificacao;
	}

	public void setListRetificacao(List<Retificacao> listRetificacao) {
		this.listRetificacao = listRetificacao;
	}

	public Boolean getHouveRetificacao() {
		return houveRetificacao;
	}

	public void setHouveRetificacao(Boolean houveRetificacao) {
		this.houveRetificacao = houveRetificacao;
	}

	public List<Projeto> getListarProjetosByFiltro() {
		return listarProjetosByFiltro;
	}

	public void setListarProjetosByFiltro(List<Projeto> listarProjetosByFiltro) {
		this.listarProjetosByFiltro = listarProjetosByFiltro;
	}

	public Projeto getFiltro() {
		return filtro;
	}

	public void setFiltro(Projeto filtro) {
		this.filtro = filtro;
	}

	public Long getFiltroProjeto() {
		return filtroProjeto;
	}

	public void setFiltroProjeto(Long filtroProjeto) {
		this.filtroProjeto = filtroProjeto;
	}

	public Contrato getFiltroContrato() {
		return filtroContrato;
	}

	public void setFiltroContrato(Contrato filtroContrato) {
		this.filtroContrato = filtroContrato;
	}

	public List<OrdemServico> getComboOrdemServico() {
		return comboOrdemServico;
	}

	public void setComboOrdemServico(List<OrdemServico> comboOrdemServico) {
		this.comboOrdemServico = comboOrdemServico;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getNovaNrOS() {
		return novaNrOS;
	}

	public void setNovaNrOS(String novaNrOS) {
		this.novaNrOS = novaNrOS;
	}

	public Double getValorFaturado() {
		return valorFaturado;
	}

	public void setValorFaturado(Double valorFaturado) {
		this.valorFaturado = valorFaturado;
	}

	public IVerificacaoProfissional getVerificacao() {
		return verificacao;
	}

	public void setVerificacao(IVerificacaoProfissional verificacao) {
		this.verificacao = verificacao;
	}

	public ICoordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public ICaracteristicaProjeto getCaracteristicasProjeto() {
		return caracteristicasProjeto;
	}

	public void setCaracteristicasProjeto(ICaracteristicaProjeto caracteristicasProjeto) {
		this.caracteristicasProjeto = caracteristicasProjeto;
	}

	public IParcelaProjeto getParcelaProjeto() {
		return parcelaProjeto;
	}

	public void setParcelaProjeto(IParcelaProjeto parcelaProjeto) {
		this.parcelaProjeto = parcelaProjeto;
	}

	public IRetificacao getRetificacao() {
		return retificacao;
	}

	public void setRetificacao(Retificacao retificacao) {
		this.retificacao = retificacao;
	}

	public IContratoService getContratoService() {
		return contratoService;
	}

	public void setContratoService(IContratoService contratoService) {
		this.contratoService = contratoService;
	}

	public List<Status> getComboStatus() {
		return comboStatus;
	}

	public void setComboStatus(List<Status> comboStatus) {
		this.comboStatus = comboStatus;
	}

	public Integer getQtdFaturado() {
		return qtdFaturado;
	}

	public void setQtdFaturado(Integer qtdFaturado) {
		this.qtdFaturado = qtdFaturado;
	}

	public Long getFiltroStatus() {
		return filtroStatus;
	}

	public void setFiltroStatus(Long filtroStatus) {
		this.filtroStatus = filtroStatus;
	}

	public Boolean getOcultarFiltroProjeto() {
		return ocultarFiltroProjeto;
	}

	public void setOcultarFiltroProjeto(Boolean ocultarFiltroProjeto) {
		this.ocultarFiltroProjeto = ocultarFiltroProjeto;
	}

	public List<OrdemServico> getListarOssByFiltro() {
		return listarOssByFiltro;
	}

	public void setListarOssByFiltro(List<OrdemServico> listarOssByFiltro) {
		this.listarOssByFiltro = listarOssByFiltro;
	}

	public Boolean getVisualizarProjeto() {
		return visualizarProjeto;
	}

	public void setVisualizarProjeto(Boolean visualizarProjeto) {
		this.visualizarProjeto = visualizarProjeto;
	}

	public List<Projeto> getListarProjetosByOS() {
		return listarProjetosByOS;
	}

	public void setListarProjetosByOS(List<Projeto> listarProjetosByOS) {
		this.listarProjetosByOS = listarProjetosByOS;
	}

	public Double getVlrProjeto() {
		return vlrProjeto;
	}

	public void setVlrProjeto(Double vlrProjeto) {
		this.vlrProjeto = vlrProjeto;
	}

	public Boolean getVisualizarOrdemServico() {
		return visualizarOrdemServico;
	}

	public void setVisualizarOrdemServico(Boolean visualizarOrdemServico) {
		this.visualizarOrdemServico = visualizarOrdemServico;
	}

	public void setRetificacao(IRetificacao retificacao) {
		this.retificacao = retificacao;
	}

	public Long getIdProfissionalGestor() {
		return idProfissionalGestor;
	}

	public void setIdProfissionalGestor(Long idProfissionalGestor) {
		this.idProfissionalGestor = idProfissionalGestor;
	}

	/**
	 * @return the idProfissionalResposanvelResposta
	 */
	public Long getIdProfissionalResposanvelResposta() {
		return idProfissionalResposanvelResposta;
	}

	/**
	 * @param idProfissionalResposanvelResposta
	 *            the idProfissionalResposanvelResposta to set
	 */
	public void setIdProfissionalResposanvelResposta(Long idProfissionalResposanvelResposta) {
		this.idProfissionalResposanvelResposta = idProfissionalResposanvelResposta;
	}

	/**
	 * @return the exibirBtRetificacao
	 */
	public Boolean getExibirBtRetificacao() {
		return exibirBtRetificacao;
	}

	/**
	 * @param exibirBtRetificacao
	 *            the exibirBtRetificacao to set
	 */
	public void setExibirBtRetificacao(Boolean exibirBtRetificacao) {
		this.exibirBtRetificacao = exibirBtRetificacao;
	}

	public TreeNode getRootRelatorio() {
		return rootRelatorio;
	}

	public void setRootRelatorio(TreeNode rootRelatorio) {
		this.rootRelatorio = rootRelatorio;
	}

	public TreeNode getRootMapas() {
		return rootMapas;
	}

	public void setRootMapas(TreeNode rootMapas) {
		this.rootMapas = rootMapas;
	}

	public TreeNode[] getSelectedRelatorioNodes() {
		return selectedRelatorioNodes;
	}

	public void setSelectedRelatorioNodes(TreeNode[] selectedRelatorioNodes) {
		this.selectedRelatorioNodes = selectedRelatorioNodes;
	}

	public TreeNode[] getSelectedMapasNodes() {
		return selectedMapasNodes;
	}

	public void setSelectedMapasNodes(TreeNode[] selectedMapasNodes) {
		this.selectedMapasNodes = selectedMapasNodes;
	}

	public TreeNode getRootUnidadeConservacao() {
		return rootUnidadeConservacao;
	}

	public void setRootUnidadeConservacao(TreeNode rootUnidadeConservacao) {
		this.rootUnidadeConservacao = rootUnidadeConservacao;
	}

	public TreeNode[] getSelectedUnidadeConservacaoNodes() {
		return selectedUnidadeConservacaoNodes;
	}

	public void setSelectedUnidadeConservacaoNodes(TreeNode[] selectedUnidadeConservacaoNodes) {
		this.selectedUnidadeConservacaoNodes = selectedUnidadeConservacaoNodes;
	}

	public List<TipoRetificacao> getComboTipoRetificacao() {
		return comboTipoRetificacao;
	}

	public void setComboTipoRetificacao(List<TipoRetificacao> comboTipoRetificacao) {
		this.comboTipoRetificacao = comboTipoRetificacao;
	}

	public List<Situacao> getComboStatusProjetoPesquisa() {
		return comboStatusProjetoPesquisa;
	}

	public void setComboStatusProjetoPesquisa(List<Situacao> comboStatusProjetoPesquisa) {
		this.comboStatusProjetoPesquisa = comboStatusProjetoPesquisa;
	}

	/**
	 * @return the projetosSelecionados
	 */
	public List<Projeto> getProjetosSelecionados() {
		return projetosSelecionados;
	}

	/**
	 * @param projetosSelecionados
	 *            the projetosSelecionados to set
	 */
	public void setProjetosSelecionados(List<Projeto> projetosSelecionados) {
		this.projetosSelecionados = projetosSelecionados;
	}

	/**
	 * @return the idProMap
	 */
	public Long getIdProMap() {
		return idProMap;
	}

	public List<Status> getComboStatusProjeto() {
		return comboStatusProjeto;
	}

	public void setComboStatusProjeto(List<Status> comboStatusProjeto) {
		this.comboStatusProjeto = comboStatusProjeto;
	}

	/**
	 * @param idProMap
	 *            the idProMap to set
	 */
	public void setIdProMap(Long idProMap) {
		this.idProMap = idProMap;
	}

	/**
	 * @return the idProCtgal
	 */
	public Long getIdProCtgal() {
		return idProCtgal;
	}

	/**
	 * @param idProCtgal
	 *            the idProCtgal to set
	 */
	public void setIdProCtgal(Long idProCtgal) {
		this.idProCtgal = idProCtgal;
	}

	/**
	 * @return the idProfVerificacao
	 */
	public Long getIdProfVerificacao() {
		return idProfVerificacao;
	}

	/**
	 * @param idProfVerificacao
	 *            the idProfVerificacao to set
	 */
	public void setIdProfVerificacao(Long idProfVerificacao) {
		this.idProfVerificacao = idProfVerificacao;
	}

	/**
	 * @return the idProfEPI
	 */
	public Long getIdProfEPI() {
		return idProfEPI;
	}

	/**
	 * @param idProfEPI
	 *            the idProfEPI to set
	 */
	public void setIdProfEPI(Long idProfEPI) {
		this.idProfEPI = idProfEPI;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Faturamento getFaturamento() {
		return faturamento;
	}

	public void setFaturamento(Faturamento faturamento) {
		this.faturamento = faturamento;
	}

	public Double getValorTotalFaturar() {
		return valorTotalFaturar;
	}

	public void setValorTotalFaturar(Double valorTotalFaturar) {
		this.valorTotalFaturar = valorTotalFaturar;
	}

	public IFaturamento getFatSalvar() {
		return fatSalvar;
	}

	public void setFatSalvar(IFaturamento fatSalvar) {
		this.fatSalvar = fatSalvar;
	}

	/**
	 * @return the opPlanejar
	 */
	public Boolean getOpPlanejar() {
		return opPlanejar;
	}

	/**
	 * @param opPlanejar
	 *            the opPlanejar to set
	 */
	public void setOpPlanejar(Boolean opPlanejar) {
		this.opPlanejar = opPlanejar;
	}

	public Long getOpEscolhida() {
		return opEscolhida;
	}

	public void setOpEscolhida(Long opEscolhida) {
		this.opEscolhida = opEscolhida;
	}

	public List<Faturamento> getListarFaturamentosByProjeto() {
		return listarFaturamentosByProjeto;
	}

	public void setListarFaturamentosByProjeto(List<Faturamento> listarFaturamentosByProjeto) {
		this.listarFaturamentosByProjeto = listarFaturamentosByProjeto;
	}

	public Double getSaldoContrato() {
		return saldoContrato;
	}

	public void setSaldoContrato(Double saldoContrato) {
		this.saldoContrato = saldoContrato;
	}

	public List<MacroRegiao> getComboMacroRegiaoPesuisar() {
		return comboMacroRegiaoPesuisar;
	}

	public void setComboMacroRegiaoPesuisar(List<MacroRegiao> comboMacroRegiaoPesuisar) {
		this.comboMacroRegiaoPesuisar = comboMacroRegiaoPesuisar;
	}

	public List<MicroRegiao> getComboMicroRegiaoPesuisar() {
		return comboMicroRegiaoPesuisar;
	}

	public void setComboMicroRegiaoPesuisar(List<MicroRegiao> comboMicroRegiaoPesuisar) {
		this.comboMicroRegiaoPesuisar = comboMicroRegiaoPesuisar;
	}

	public Long getFiltroMacroRegiao() {
		return filtroMacroRegiao;
	}

	public void setFiltroMacroRegiao(Long filtroMacroRegiao) {
		this.filtroMacroRegiao = filtroMacroRegiao;
	}

	public Long getFiltroEstado() {
		return filtroEstado;
	}

	public void setFiltroEstado(Long filtroEstado) {
		this.filtroEstado = filtroEstado;
	}

	public Long getFiltroMunicipio() {
		return filtroMunicipio;
	}

	public void setFiltroMunicipio(Long filtroMunicipio) {
		this.filtroMunicipio = filtroMunicipio;
	}

	public Boolean getOpFaturar() {
		return opFaturar;
	}

	public void setOpFaturar(Boolean opFaturar) {
		this.opFaturar = opFaturar;
	}

	public String getTituloDataTable() {
		return tituloDataTable;
	}

	public void setTituloDataTable(String tituloDataTable) {
		this.tituloDataTable = tituloDataTable;
	}

	public List<String> getListCaracteristicas() {
		return listCaracteristicas;
	}

	public void setListCaracteristicas(List<String> listCaracteristicas) {
		this.listCaracteristicas = listCaracteristicas;
	}

	public TreeNode getEpi() {
		return epi;
	}

	public void setEpi(TreeNode epi) {
		this.epi = epi;
	}

	public TreeNode getEv() {
		return ev;
	}

	public void setEv(TreeNode ev) {
		this.ev = ev;
	}

	public TreeNode getCtgaSeia() {
		return ctgaSeia;
	}

	public void setCtgaSeia(TreeNode ctgaSeia) {
		this.ctgaSeia = ctgaSeia;
	}

	public TreeNode getCasaEmApp() {
		return casaEmApp;
	}

	public void setCasaEmApp(TreeNode casaEmApp) {
		this.casaEmApp = casaEmApp;
	}

	public TreeNode getDiadSeia() {
		return diadSeia;
	}

	public void setDiadSeia(TreeNode diadSeia) {
		this.diadSeia = diadSeia;
	}

	public TreeNode getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(TreeNode localizacao) {
		this.localizacao = localizacao;
	}

	public TreeNode getHidrografia() {
		return hidrografia;
	}

	public void setHidrografia(TreeNode hidrografia) {
		this.hidrografia = hidrografia;
	}

	public TreeNode getLeiMataAtlantica() {
		return leiMataAtlantica;
	}

	public void setLeiMataAtlantica(TreeNode leiMataAtlantica) {
		this.leiMataAtlantica = leiMataAtlantica;
	}

	public TreeNode getAreaEspecial() {
		return areaEspecial;
	}

	public void setAreaEspecial(TreeNode areaEspecial) {
		this.areaEspecial = areaEspecial;
	}

	public TreeNode getAssentamento() {
		return assentamento;
	}

	public void setAssentamento(TreeNode assentamento) {
		this.assentamento = assentamento;
	}

	public TreeNode getFederal() {
		return federal;
	}

	public void setFederal(TreeNode federal) {
		this.federal = federal;
	}

	public TreeNode getIcmbio() {
		return icmbio;
	}

	public void setIcmbio(TreeNode icmbio) {
		this.icmbio = icmbio;
	}

	public TreeNode getEstadual() {
		return estadual;
	}

	public void setEstadual(TreeNode estadual) {
		this.estadual = estadual;
	}

	public TreeNode getMunicipal() {
		return municipal;
	}

	public void setMunicipal(TreeNode municipal) {
		this.municipal = municipal;
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

	public Long getFiltroSituacaoFaturar() {
		return filtroSituacaoFaturar;
	}

	public void setFiltroSituacaoFaturar(Long filtroSituacaoFaturar) {
		this.filtroSituacaoFaturar = filtroSituacaoFaturar;
	}

	public Long getIdResponsavelFaturamento() {
		return idResponsavelFaturamento;
	}

	public void setIdResponsavelFaturamento(Long idResponsavelFaturamento) {
		this.idResponsavelFaturamento = idResponsavelFaturamento;
	}

	public List<TipoNotificacao> getComboTipoNotificacao() {
		return comboTipoNotificacao;
	}

	public void setComboTipoNotificacao(List<TipoNotificacao> comboTipoNotificacao) {
		this.comboTipoNotificacao = comboTipoNotificacao;
	}

	public Notificacao getNotificacao() {
		return notificacao;
	}

	public void setNotificacao(Notificacao notificacao) {
		this.notificacao = notificacao;
	}

	public List<Notificacao> getListarNotificacao() {
		return listarNotificacao;
	}

	public void setListarNotificacao(List<Notificacao> listarNotificacao) {
		this.listarNotificacao = listarNotificacao;
	}

	public Long getIdTipoRetificacao() {
		return idTipoRetificacao;
	}

	public void setIdTipoRetificacao(Long idTipoRetificacao) {
		this.idTipoRetificacao = idTipoRetificacao;
	}

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public Long getIdStatusRegistro() {
		return idStatusRegistro;
	}

	public void setIdStatusRegistro(Long idStatusRegistro) {
		this.idStatusRegistro = idStatusRegistro;
	}

	public OrdemServico getFiltroNumeroOS() {
		return filtroNumeroOS;
	}

	public void setFiltroNumeroOS(OrdemServico filtroNumeroOS) {
		this.filtroNumeroOS = filtroNumeroOS;
	}

	public String getFormaDePagamentocontrato() {
		return formaDePagamentocontrato;
	}

	public void setFormaDePagamentocontrato(String formaDePagamentocontrato) {
		this.formaDePagamentocontrato = formaDePagamentocontrato;
	}

	public String getLabelOS() {
		return labelOS;
	}

	public void setLabelOS(String labelOS) {
		this.labelOS = labelOS;
	}

	public Boolean getCamposDeNotificacaoObrigatorio() {
		return camposDeNotificacaoObrigatorio;
	}

	public void setCamposDeNotificacaoObrigatorio(Boolean camposDeNotificacaoObrigatorio) {
		this.camposDeNotificacaoObrigatorio = camposDeNotificacaoObrigatorio;
	}

	public Boolean getCamposDeRegistoObrigatorio() {
		return camposDeRegistoObrigatorio;
	}

	public void setCamposDeRegistoObrigatorio(Boolean camposDeRegistoObrigatorio) {
		this.camposDeRegistoObrigatorio = camposDeRegistoObrigatorio;
	}

	public List<Situacao> getComboSituacoesConfirmarPagamento() {
		return comboSituacoesConfirmarPagamento;
	}

	public void setComboSituacoesConfirmarPagamento(List<Situacao> comboSituacoesConfirmarPagamento) {
		this.comboSituacoesConfirmarPagamento = comboSituacoesConfirmarPagamento;
	}

	public TreeNode getOutros() {
		return outros;
	}

	public void setOutros(TreeNode outros) {
		this.outros = outros;
	}

	public String getPrimariaKmString() {
		return primariaKmString;
	}

	public void setPrimariaKmString(String primariaKmString) {
		this.primariaKmString = primariaKmString;
	}

	public String getSecundariaKmString() {
		return secundariaKmString;
	}

	public void setSecundariaKmString(String secundariaKmString) {
		this.secundariaKmString = secundariaKmString;
	}

	public String getTensaoPrimariaKvString() {
		return tensaoPrimariaKvString;
	}

	public void setTensaoPrimariaKvString(String tensaoPrimariaKvString) {
		this.tensaoPrimariaKvString = tensaoPrimariaKvString;
	}

	public String getConjugadaKmString() {
		return conjugadaKmString;
	}

	public void setConjugadaKmString(String conjugadaKmString) {
		this.conjugadaKmString = conjugadaKmString;
	}

	public String getTensaoSecundariaKVString() {
		return tensaoSecundariaKVString;
	}

	public void setTensaoSecundariaKVString(String tensaoSecundariaKVString) {
		this.tensaoSecundariaKVString = tensaoSecundariaKVString;
	}

	public String getDistanciaSedeMunPavString() {
		return distanciaSedeMunPavString;
	}

	public void setDistanciaSedeMunPavString(String distanciaSedeMunPavString) {
		this.distanciaSedeMunPavString = distanciaSedeMunPavString;
	}

	public String getDistanciaSedeMunNaoPavString() {
		return distanciaSedeMunNaoPavString;
	}

	public void setDistanciaSedeMunNaoPavString(String distanciaSedeMunNaoPavString) {
		this.distanciaSedeMunNaoPavString = distanciaSedeMunNaoPavString;
	}

	public String getDistanciaMunLocalProjPavString() {
		return distanciaMunLocalProjPavString;
	}

	public void setDistanciaMunLocalProjPavString(String distanciaMunLocalProjPavString) {
		this.distanciaMunLocalProjPavString = distanciaMunLocalProjPavString;
	}

	public String getDistanciaMunLocalProjNaoPavString() {
		return distanciaMunLocalProjNaoPavString;
	}

	public void setDistanciaMunLocalProjNaoPavString(String distanciaMunLocalProjNaoPavString) {
		this.distanciaMunLocalProjNaoPavString = distanciaMunLocalProjNaoPavString;
	}

	public boolean getGerouDae() {
		return gerouDae;
	}

	public void setGerouDae(boolean gerouDae) {
		this.gerouDae = gerouDae;
	}

	public boolean isGerouNotificacao() {
		return gerouNotificacao;
	}

	public void setGerouNotificacao(boolean gerouNotificacao) {
		this.gerouNotificacao = gerouNotificacao;
	}

	public IRegistro getRegistoSalvar() {
		return registoSalvar;
	}

	public void setRegistoSalvar(IRegistro registoSalvar) {
		this.registoSalvar = registoSalvar;
	}

	public List<Situacao> getComboSituacoesFiltro() {
		return comboSituacoesFiltro;
	}

	public void setComboSituacoesFiltro(List<Situacao> comboSituacoesFiltro) {
		this.comboSituacoesFiltro = comboSituacoesFiltro;
	}

	public Coordenada getCoordenadaExcluir() {
		return coordenadaExcluir;
	}

	public void setCoordenadaExcluir(Coordenada coordenadaExcluir) {
		this.coordenadaExcluir = coordenadaExcluir;
	}

	public TrajetoRede getTrajetoExcluir() {
		return trajetoExcluir;
	}

	public void setTrajetoExcluir(TrajetoRede trajetoExcluir) {
		this.trajetoExcluir = trajetoExcluir;
	}

	public TrajetoRede getTrajetoRede() {
		return trajetoRede;
	}

	public void setTrajetoRede(TrajetoRede trajetoRede) {
		this.trajetoRede = trajetoRede;
	}

	public List<OrdemServico> getOrdensServicosSelecionados() {
		return ordensServicosSelecionados;
	}

	public void setOrdensServicosSelecionados(List<OrdemServico> ordensServicosSelecionados) {
		this.ordensServicosSelecionados = ordensServicosSelecionados;
	}

	public List<String> getListaDeIdOS() {
		return listaDeIdOS;
	}

	public void setListaDeIdOS(List<String> listaDeIdOS) {
		this.listaDeIdOS = listaDeIdOS;
	}

	public Long getIdProResponsavel() {
		return idProResponsavel;
	}

	public void setIdProResponsavel(Long idProResponsavel) {
		this.idProResponsavel = idProResponsavel;
	}

	public List<Projeto> getVerProjetosByOS() {
		return verProjetosByOS;
	}

	public void setVerProjetosByOS(List<Projeto> verProjetosByOS) {
		this.verProjetosByOS = verProjetosByOS;
	}

	public Long getIdStatusProjeto() {
		return idStatusProjeto;
	}

	public void setIdStatusProjeto(Long idStatusProjeto) {
		this.idStatusProjeto = idStatusProjeto;
	}

	public List<Projeto> getFilterProjetos() {
		return filterProjetos;
	}

	public void setFilterProjetos(List<Projeto> filterProjetos) {
		this.filterProjetos = filterProjetos;
	}

	public String getLabelPesquisaData() {
		return labelPesquisaData;
	}

	public void setLabelPesquisaData(String labelPesquisaData) {
		this.labelPesquisaData = labelPesquisaData;
	}

	public Boolean getCheckPlanejarFaturar() {
		return checkPlanejarFaturar;
	}

	public void setCheckPlanejarFaturar(Boolean checkPlanejarFaturar) {
		this.checkPlanejarFaturar = checkPlanejarFaturar;
	}

	public Boolean getPlanejar() {
		return planejar;
	}

	public void setPlanejar(Boolean planejar) {
		this.planejar = planejar;
	}

	public Boolean getFaturar() {
		return faturar;
	}

	public void setFaturar(Boolean faturar) {
		this.faturar = faturar;
	}

	
	
	public void habilitaPlanejamento(){
		if(planejar){
			habilitaPlanejamento = true;
			vlrColuna = "2050px";
		}else{
			habilitaPlanejamento = false;
			vlrColuna = "1350px";
		}
	}

	public void validaBotoesAtualizarAssociar(){
		this.validaBotaoPlan = true;
		if(this.listaDeIdContrato != null && !this.listaDeIdContrato.isEmpty()){
			if(planSelecionado!= null){
				this.validaBotaoPlan = false;
			}else{
				this.validaBotaoPlan = true;
			}
		}
	}
	
	public void validaBotoesNovoPlan(){
		this.validaBotaoNovoPlan = true;
		if(this.listaDeIdContrato != null && !this.listaDeIdContrato.isEmpty()){
			if(planSelecionado== null){
				this.validaBotaoNovoPlan = false;
			}else{
				this.validaBotaoNovoPlan = true;
			}
		}
	}
	
	public String marcaSituacaoPlanPendente(Boolean check, Long item){
		String color = "indianred";
		if(check != null && check){
			color = "white";
		}
		if(item!= null && item.equals(50L)){
			color = "white";
		}
		return color;
	}
	//Cria novo planejamento
	public void criarNovoPlanejamento(){
		if(this.tituloPlan != null && !this.tituloPlan.isEmpty()){
			this.planSelecionado = new Planejamentos();
			this.planSelecionado = planejamentosService.criarNovoPlanejamento(usuarioLogado);
			this.planSelecionado.setTitulo(this.tituloPlan);
			this.planSelecionado.setDtAlteracaoPlanej(new Date());
			this.planSelecionado.setDtCriacaoPlanej(new Date());
			this.planSelecionado = planejamentosService.salvar(this.planSelecionado);
			comboPlanejamentos = planejamentosService.getAll();
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("idplanList, idTituloPla");
			FacesUtil.menssagemSucesso("Planejamento "+this.planSelecionado.getNome()+ " criado  com sucesso!");
		}else{
			FacesUtil.menssagemErro("O Título do planejamento, não pode ser vazio. Por favor, digite o título!");
		}
	}
	
	//Salva título do planejamento
	public void salvarTituloPlanejamento(){
		if(tituloPlan!= null && !tituloPlan.isEmpty()){
			if(this.planSelecionado != null){
				this.planSelecionado.setTitulo(tituloPlan);
				this.planSelecionado = planejamentosService.salvar(this.planSelecionado);
				comboPlanejamentos = planejamentosService.getAll();
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("idplanList, idTituloPla");
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Título do planejamento "+ this.planSelecionado.getNome()+ " atualizado!"));
			}else{
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Nenhum planejamento foi selecionado"));
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Título do planejamento não pode ser vazio."));
		}
	}
	
	public void atualizaTituloPlaSelecionado(){
		if(this.planSeleect != null){
			planSelecionado = planejamentosService.getById(this.planSeleect);
		}else{
			planSelecionado = null;
		}
		if(this.planSelecionado!= null && !this.planSelecionado.getTitulo().isEmpty()){
			tituloPlan = this.planSelecionado.getTitulo();
		}else{
			tituloPlan =  null;
		}
		validaBotoesNovoPlan();
		validaBotoesAtualizarAssociar();
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("idplanList, idTituloPla, idBtAtuTituloPlan, idAssociarPlan, criaNovoPlan");
	}

	//Associa um planejamento a um ou vários projetos.
	public void associarPlanejamentoAosProjetos() {
		Boolean jaSendoUsado = false;
		List<IPlanejamentos> listPlan = new ArrayList<>();
		
		if (projetosSelecionados != null && !projetosSelecionados.isEmpty()) {
			jaSendoUsado = projetoService.verificaPlanejamentoExistente(planSelecionado);
			if (jaSendoUsado) {
				planejamentosService.criarRevisaoPlanejamento(usuarioLogado, planSelecionado.getId());
			}
			for (Projeto projeto : projetosSelecionados) {
				if (projeto.getPlanejamentos() != null
						&& (projeto.getPlanejamentos().getId() != planSelecionado.getId())) {
					if (listPlan != null && !listPlan.isEmpty()) {
						Boolean naoAchou = true;
						for (IPlanejamentos jaRevisado : listPlan) {
							if (jaRevisado.getId().equals(projeto.getPlanejamentos().getId())) {
								naoAchou = false;
								break;
							}
						}
						if (naoAchou) {
							planejamentosService.criarRevisaoPlanejamento(usuarioLogado,
									projeto.getPlanejamentos().getId());
							listPlan.add(projeto.getPlanejamentos());
						}
					} else {
						planejamentosService.criarRevisaoPlanejamento(usuarioLogado,
								projeto.getPlanejamentos().getId());
						listPlan.add(projeto.getPlanejamentos());
					}
				}
				projeto.setPlanejamentos(planSelecionado);
				projeto.setDataAtualizacao(new Date());
				projeto.setSituacaoPlanejamento(SituacaoPlanejamento.PENDENTE.getFlag());
				projetoService.atualizar(projeto);
			}
			comboPlanejamentos = planejamentosService.getAll();
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("idplanList");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Planejamento associado com sucesso!", ""));
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum projeto foi selecionado para associar.", ""));
		}
	}

	
	public void limparTelaPesquisa(){
		listaDeIdContrato = null;
		listaDeIdOS = null;
		filtroDataInicial = null;
		filtroDataFinal =null;
		filtroNomeProjeto = null;
		filtroProjetoCliente = null;
		filtroSubProjeto=null;
		filtroProjetoAgrega=null;
		filtroAnoProjeto=null;
		sitPlanejamento=null;
		planejar=false;
		planSeleect=null;
		tituloPlan=null;
		listarProjetosByFiltro=null;
		listarOssByFiltro=null;
	}
	
	
	public void addPlanejamento(Boolean tipo) {
		IPlanejamentos novoPlanejamento = null;
		String msgPlan = "Planejamento atualizado com sucesso!";
		List<IPlanejamentos> listPlanejamentos = new ArrayList<>();

		if (this.listProjetosPlanejar != null && this.listProjetosPlanejar.size() > 0) {
			for (Projeto projetoAtual : this.listProjetosPlanejar) {
				if (projetoAtual.getPlanejamentos() == null) {
					if (novoPlanejamento == null) {
						novoPlanejamento = planejamentosService.criarNovoPlanejamento(usuarioLogado);
						msgPlan = "Planejamento " + planejamentos + "criado com sucesso!";
					}
					projetoAtual.setPlanejamentos(novoPlanejamento);
					projetoAtual.setJaPlanejado(true);
					projetoAtual.setStatus(statusService.getById(9L));

				} else {
					if (listPlanejamentos != null && !listPlanejamentos.isEmpty()) {
						Boolean naoAchou = true;
						for (IPlanejamentos verificar : listPlanejamentos) {
							if (verificar.getId().equals(projetoAtual.getPlanejamentos().getId())) {
								naoAchou = false;
								break;
							}
						}
						if (naoAchou) {
							IPlanejamentos plan = planejamentosService.criarRevisaoPlanejamento(usuarioLogado,
									projetoAtual.getPlanejamentos().getId());
							listPlanejamentos.add(plan);
						}
					} else {
						IPlanejamentos plan = planejamentosService.criarRevisaoPlanejamento(usuarioLogado,
								projetoAtual.getPlanejamentos().getId());
						listPlanejamentos.add(plan);
					}
				}
				projetoAtual.setUsuarioAtualizacao(usuarioLogado);
				projetoService.atualizar(projetoAtual);
			}
		}
		// comboPlanejamentos = planejamentosService.getAll();
		buscarPlanejamentos();
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("tablePlanejamanto");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msgPlan, ""));
	}

	private Profissional retornaID(Projeto proj, String tipo) {
		Profissional prof = profissionalService.getById(50L);
		if (tipo.equalsIgnoreCase("epi") && proj.getProfEpi() != null && proj.getProfEpi().getId() != null) {
			return profissionalService.getById(proj.getProfEpi().getId());
		} else if (tipo.equalsIgnoreCase("evctga") && proj.getProfEvctgal() != null
				&& proj.getProfEvctgal().getId() != null) {
			return profissionalService.getById(proj.getProfEvctgal().getId());
		} else if (tipo.equalsIgnoreCase("mapas") && proj.getProfMapas() != null
				&& proj.getProfMapas().getId() != null) {
			return profissionalService.getById(proj.getProfMapas().getId());
		} else if (tipo.equalsIgnoreCase("verif") && proj.getProfVerif() != null
				&& proj.getProfVerif().getId() != null) {
			return profissionalService.getById(proj.getProfVerif().getId());
		}
		return prof;
	}

	public void verificarTipoDeLista() {
		limparTelaPesquisa();
		filtroDataInicial = null;
		filtroDataFinal = null;
		if (selectedPesquisarPor.equalsIgnoreCase("OS")) {
			labelPesquisaData = "Data de entrada OS";
			listarProjetosByFiltro = null;
			checkPlanejarFaturar = false;
			planejar = false;
			faturar = false;
			exibirListPorOS = false;
			exibitBotaoNovaOS = true;
			filtroDataInicial = null;
			filtroDataFinal = null;
		} else if (selectedPesquisarPor.equalsIgnoreCase("PJ")) {
			labelPesquisaData = "Data de entrada Projeto";
			listarOssByFiltro = null;
			checkPlanejarFaturar = true;
			exibirListPorOS = false;
			exibitBotaoNovaOS = false;
		}
	}

	public void planejarChek() {
		if (planejar) {
			ocultaBt = false;
			buscar();
		} else {
			ocultaBt = true;
			buscar();
		}
	}

	public void faturarChek() {
		if (faturar) {
			planejar = false;
		}
	}

	public List<Projeto> getListProjetosPlanejar() {
		return listProjetosPlanejar;
	}

	public void setListProjetosPlanejar(List<Projeto> listProjetosPlanejar) {
		this.listProjetosPlanejar = listProjetosPlanejar;
	}

	// TODO
	public void consultarPlanejamento() {
		setBtAtualizarPlanejamento(true);
		setBtConfirmarPlanejamento(false);
		prePlanejamento();
	}

	// TODO
	public void criarPlanejamento() {
		setBtAtualizarPlanejamento(false);
		setBtConfirmarPlanejamento(true);
		prePlanejamento();
	}
	
	public Date obterDataEntregaProjeto(Projeto projeto){
		IVerificacaoProfissional profVerificado = verificacaoProfissionalService.getByProjeto(projeto.getId());
		if(profVerificado != null && profVerificado.getDataEntrega()!= null){
			return profVerificado.getDataEntrega();
		}
		return null;
	}

	public void prePlanejamento() {

		if (projetosSelecionados == null) {
			projetosSelecionados = new ArrayList<>();
		}

		/*if (planejar && projetosSelecionados.size() == 0) {
			FacesUtil.addErrorMessage("Por favor, selecione os projetos que deseja planejar.");
			return;
		}*/

		pagPlanejando = true;
		pesquisar = false;
		filterProjetos = new ArrayList<>();
		listProjetosPlanejar = new ArrayList<>();
		if (projetosSelecionados != null && !projetosSelecionados.isEmpty()) {

			for (Projeto projeto : projetosSelecionados) {
				if (projeto.getProfEpi() == null) {
					projeto.setProfEpi(new Profissional());
				}
				if (projeto.getProfEvctgal() == null) {
					projeto.setProfEvctgal(new Profissional());
				}
				if (projeto.getProfMapas() == null) {
					projeto.setProfMapas(new Profissional());
				}
				if (projeto.getProfVerif() == null) {
					projeto.setProfVerif(new Profissional());
				}
				listProjetosPlanejar.add(projeto);
			}
		}
		planejamentoSelecionados = new ArrayList<>();
	}

	public Boolean getPagPlanejando() {
		return pagPlanejando;
	}

	public void setPagPlanejando(Boolean pagPlanejando) {
		this.pagPlanejando = pagPlanejando;
	}

	public List<String> getNrProjetoAgrega() {
		return nrProjetoAgrega;
	}

	public void setNrProjetoAgrega(List<String> nrProjetoAgrega) {
		this.nrProjetoAgrega = nrProjetoAgrega;
	}

	public TreeNode getTamLinhaDiferente() {
		return tamLinhaDiferente;
	}

	public void setTamLinhaDiferente(TreeNode tamLinhaDiferente) {
		this.tamLinhaDiferente = tamLinhaDiferente;
	}

	public TreeNode getTerraIndigina() {
		return terraIndigina;
	}

	public void setTerraIndigina(TreeNode terraIndigina) {
		this.terraIndigina = terraIndigina;
	}

	public TreeNode getQuilombola() {
		return quilombola;
	}

	public void setQuilombola(TreeNode quilombola) {
		this.quilombola = quilombola;
	}

	public List<Projeto> getPlanejamentoSelecionados() {
		return planejamentoSelecionados;
	}

	public void setPlanejamentoSelecionados(List<Projeto> planejamentoSelecionados) {
		this.planejamentoSelecionados = planejamentoSelecionados;
	}

	public IPlanejamentos getPlanejamentos() {
		return planejamentos;
	}

	public void setPlanejamentos(IPlanejamentos planejamentos) {
		this.planejamentos = planejamentos;
	}

	public void carregaDadosProjeto(Projeto projeto) {
		this.projeto = projeto;
		comboSubTipoProjeto = ETipoSubProjeto.obterPorTipoProjeto(projeto.getSubTipoProjeto());

		if (projeto != null) {
			listCoordenada = coordenadaService.getByProjeto(projeto.getId());
			listCoordenada.sort(comparador);
			listTrajetoRede = trajetoRedeService.getByProjeto(projeto.getId());
			exibirListPorOS = false;
			caracteristicasProjeto = caracteristicasProjetoService.getByProjeto(projeto.getId());

			if (caracteristicasProjeto == null) {
				caracteristicasProjeto = new CaracteristicaProjeto();
			}

			listRetificacao = retificacaoService.getByProjeto(projeto.getId());
			coordenada = new Coordenada();
			trajetoRede = new TrajetoRede();

			verificacao = verificacaoProfissionalService.getByProjeto(projeto.getId());
			if (verificacao == null) {
				instaciarVerificacao();
			}
			verificarProfissionais(projeto);
		}

		listarFaturamentosByProjeto = faturamentoService.getByProjeto(projeto.getId());
		if (listarFaturamentosByProjeto == null) {
			listarFaturamentosByProjeto = new ArrayList<>();
		}

		VerificaCaracteristicasMarcadasRelatorio();

		Registro filtroRegistro = new Registro();
		filtroRegistro.setProjeto(projeto);

		if (registroService.getByFiltros(filtroRegistro).size() > 0) {
			registoSalvar = registroService.getByFiltros(filtroRegistro).get(0);
		} else {
			registoSalvar = new Registro();
		}

		Notificacao filtro = new Notificacao();
		filtro.setProjeto(this.projeto);
		listarNotificacao = notificacaoService.getByFiltros(filtro);
		verificarVariveis();
		comboMunicipio = municipioService.getAllMunicipio();
		verificarGerouDaeEdit();
		if (projeto.getStatus() == null) {
			projeto.setStatus(new Status());
		} else {
			idStatusProjeto = projeto.getStatus().getId();
		}
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado",
					"Antigo: " + oldValue + ", Novo:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void buscarPlanejamentos() {

		listProjetosPlanejar = new ArrayList<>();
		filtro = new Projeto();
		if (idsPlanejamentos == null) {
			idsPlanejamentos = new ArrayList<>();
		}

		if (idsPlanejamentos.size() > 0) {
			String ids = idsPlanejamentos.get(0);
			for (String item : idsPlanejamentos) {
				ids = ids + "," + item;
			}
			listProjetosPlanejar = projetoService.obterProjetoSemPlanejamento(ids, filtroDataInicial, filtroDataFinal);

		} else {
			listProjetosPlanejar = projetoService.obterProjetoSemPlanejamento(null, filtroDataInicial, filtroDataFinal);
		}
	}
	
	public void ListaPlanejamentosExistentes() {
		listProjetosPlanejar = new ArrayList<>();
		filtro = new Projeto();
		String ids = null;
		
		if (idsPlanejamentos != null && !idsPlanejamentos.isEmpty()) {
			for (String item : idsPlanejamentos) {
				ids = ids + "," + item;
			}
		}
		listProjetosPlanejar = projetoService.obterProjetoSemPlanejamento(ids, filtroDataInicial, filtroDataFinal);
		listarProjetosByFiltro = new ArrayList<>();
		listarProjetosByFiltro.addAll(listProjetosPlanejar);
	}

	public List<Planejamentos> getComboPlanejamentos() {
		return comboPlanejamentos;
	}

	public void setComboPlanejamentos(List<Planejamentos> comboPlanejamentos) {
		this.comboPlanejamentos = comboPlanejamentos;
	}

	public List<String> getIdsPlanejamentos() {
		return idsPlanejamentos;
	}

	public void setIdsPlanejamentos(List<String> idsPlanejamentos) {
		this.idsPlanejamentos = idsPlanejamentos;
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

	public List<String> getListaDeIdContrato() {
		return listaDeIdContrato;
	}

	public void setListaDeIdContrato(List<String> listaDeIdContrato) {
		this.listaDeIdContrato = listaDeIdContrato;
	}

	public boolean isExibitBotaoNovaOS() {
		return exibitBotaoNovaOS;
	}

	public void setExibitBotaoNovaOS(boolean exibitBotaoNovaOS) {
		this.exibitBotaoNovaOS = exibitBotaoNovaOS;
	}

	public List<OrdemServico> getListaOS() {
		return listaOS;
	}

	public void setListaOS(List<OrdemServico> listaOS) {
		this.listaOS = listaOS;
	}

	public Boolean getBtAtualizarPlanejamento() {
		return btAtualizarPlanejamento;
	}

	public void setBtAtualizarPlanejamento(Boolean btAtualizarPlanejamento) {
		this.btAtualizarPlanejamento = btAtualizarPlanejamento;
	}

	public Boolean getBtConfirmarPlanejamento() {
		return btConfirmarPlanejamento;
	}

	public void setBtConfirmarPlanejamento(Boolean btConfirmarPlanejamento) {
		this.btConfirmarPlanejamento = btConfirmarPlanejamento;
	}

	public Boolean getOcultaBt() {
		return ocultaBt;
	}

	public void setOcultaBt(Boolean ocultaBt) {
		this.ocultaBt = ocultaBt;
	}

	// Proposta Financeira Ofical versão 1-0
	public void gerarPlanejamento() {
		List<PlanejamentoVO> listPlanejamento = new ArrayList<>();

		if (listProjetosPlanejar != null && !listProjetosPlanejar.isEmpty()) {

			for (Projeto projeto : listProjetosPlanejar) {
				PlanejamentoVO planejamentoVO = new PlanejamentoVO();
				planejamentoVO.setDataDoPlanejamento(projeto.getPlanejamentos().getDtAlteracaoPlanej() !=null? projeto.getPlanejamentos().getDtAlteracaoPlanej() :projeto.getPlanejamentos().getDtAlteracaoPlanej());
				planejamentoVO.setNomePlanejamento(projeto.getPlanejamentos().getNome());
				planejamentoVO.setTituloPlanejamento(projeto.getPlanejamentos().getTitulo());
				planejamentoVO.setNrOS(projeto.getOrdemServico().getNumeroOS());
				planejamentoVO.setNrEA(projeto.getSubTipoProjeto() + "-" + projeto.getNumeroProjetoAgrega() + "."
						+ projeto.getAnoReferencia());
				planejamentoVO.setNrProjeto(projeto.getNumeroProjetoCliente());
				planejamentoVO.setTamanhoProjeto(projeto.getTamanhoProjeto());
				planejamentoVO.setMunicipio(projeto.getMunicipio().getNome());
				planejamentoVO.setNomeProjeto(projeto.getNomeProjeto());
				planejamentoVO.setNrContrato(projeto.getContrato().getNrContrato());
				if (projeto.getProfEpi() != null) {
					if (projeto.getProfEpi().getNome().equalsIgnoreCase("--")) {
						planejamentoVO.setProfEpi(projeto.getProfEpi().getNome());
					} else {
						planejamentoVO.setProfEpi(projeto.getProfEpi().getNome().substring(0,
								projeto.getProfEpi().getNome().indexOf(" ")));
					}
				}
				if (projeto.getProfEvctgal() != null) {
					if (projeto.getProfEvctgal().getNome().equalsIgnoreCase("--")) {
						planejamentoVO.setProfEvCtga(projeto.getProfEvctgal().getNome());
					} else {
						planejamentoVO.setProfEvCtga(projeto.getProfEvctgal().getNome().substring(0,
								projeto.getProfEvctgal().getNome().indexOf(" ")));
					}
				}
				if (projeto.getProfMapas() != null) {
					if (projeto.getProfMapas().getNome().equalsIgnoreCase("--")) {
						planejamentoVO.setProfMapa(projeto.getProfMapas().getNome());
					} else {
						planejamentoVO.setProfMapa(projeto.getProfMapas().getNome().substring(0,
								projeto.getProfMapas().getNome().indexOf(" ")));
					}
				}
				if (projeto.getProfVerif() != null) {
					if (projeto.getProfVerif().getNome().equalsIgnoreCase("--")) {
						planejamentoVO.setProfVerificacao(projeto.getProfVerif().getNome());
					} else {
						planejamentoVO.setProfVerificacao(projeto.getProfVerif().getNome().substring(0,
								projeto.getProfVerif().getNome().indexOf(" ")));
					}
				}
				planejamentoVO.setDtPlanInicio(projeto.getDtIniPlanj());
				planejamentoVO.setDtPlanFim(projeto.getDtFimPlanj());
				planejamentoVO.setDtEntrega(obterDataEntregaProjeto(projeto));

				listPlanejamento.add(planejamentoVO);
			}
			String nomePlanejamento = "Planejamentos";

			RelatorioUtil.gerarPlanejamento("PlanejamentosRealizados", listPlanejamento, nomePlanejamento);
		} else {
			throw new NegocioException("Selecione um planejamento!");
		}
	}

	public void excluirPlanejamento() {
		String texto= "";
		Integer cont = 0;
		if(projetosSelecionados != null && !projetosSelecionados.isEmpty()){
			for (IProjeto projetoAtual : projetosSelecionados) {
				if(!projetoAtual.getSituacaoPlanejamento().equals(SituacaoPlanejamento.CONCLUIDO.getFlag())){
					projetoAtual.setPlanejamentos(null);
					projetoAtual.setSituacaoPlanejamento(SituacaoPlanejamento.SEM_PLANEJAMENTO.getFlag());
					projetoAtual.setStatus(statusService.getById(10L));
					projetoAtual.setDataAtualizacao(new Date());
					projetoAtual.setUsuarioAtualizacao(usuarioLogado);
					projetoService.salvar(projetoAtual);
				}else{
					cont = cont+1;
				}
			}
		}
		if(cont>0){
			texto = "Obs: "+ cont+" já estão concluídos e não podem ser removido seus planejamentos.";
		}
		projetosSelecionados = null;
		buscar();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Planejamento excluir com sucesso." , texto));
	}

	public String concatProjeto(String param) {
		String nome = param;
		if (nome.length() > 20) {
			nome = nome.substring(0, 15) + "...";
		}
		return nome;
	}

	public String concatMunicipio(String param) {
		String nome = param;
		if (nome.length() > 15) {
			nome = nome.substring(0, 15) + "...";
		}
		return nome;
	}

	public String getFiltroProjetoAgrega() {
		return filtroProjetoAgrega;
	}

	public void setFiltroProjetoAgrega(String filtroProjetoAgrega) {
		this.filtroProjetoAgrega = filtroProjetoAgrega;
	}

	public String getBtProjetos() {
		return btProjetos;
	}

	public void setBtProjetos(String btProjetos) {
		this.btProjetos = btProjetos;
	}

	public Long getIdPlanejamento() {
		return idPlanejamento;
	}

	public void setIdPlanejamento(Long idPlanejamento) {
		this.idPlanejamento = idPlanejamento;
	}

	public Date getDataEntradaLocal() {
		return dataEntradaLocal;
	}

	public void setDataEntradaLocal(Date dataEntradaLocal) {
		this.dataEntradaLocal = dataEntradaLocal;
	}

	public IVerificacaoProfissional getVerificacaoSalvar() {
		return verificacaoSalvar;
	}

	public void setVerificacaoSalvar(IVerificacaoProfissional verificacaoSalvar) {
		this.verificacaoSalvar = verificacaoSalvar;
	}

	public Long getIdProMapConclusao() {
		return idProMapConclusao;
	}

	public void setIdProMapConclusao(Long idProMapConclusao) {
		this.idProMapConclusao = idProMapConclusao;
	}

	public Long getIdProCtgalConclusao() {
		return idProCtgalConclusao;
	}

	public void setIdProCtgalConclusao(Long idProCtgalConclusao) {
		this.idProCtgalConclusao = idProCtgalConclusao;
	}

	public Long getIdProfVerificacaoConclusao() {
		return idProfVerificacaoConclusao;
	}

	public void setIdProfVerificacaoConclusao(Long idProfVerificacaoConclusao) {
		this.idProfVerificacaoConclusao = idProfVerificacaoConclusao;
	}

	public Long getIdProfEPIConclusao() {
		return idProfEPIConclusao;
	}

	public void setIdProfEPIConclusao(Long idProfEPIConclusao) {
		this.idProfEPIConclusao = idProfEPIConclusao;
	}

	public Long getFusoInformado() {
		return fusoInformado;
	}

	public void setFusoInformado(Long fusoInformado) {
		this.fusoInformado = fusoInformado;
	}

	public String getFiltroSubProjeto() {
		return filtroSubProjeto;
	}

	public void setFiltroSubProjeto(String filtroSubProjeto) {
		this.filtroSubProjeto = filtroSubProjeto;
	}

	public String getFiltroNomeProjeto() {
		return filtroNomeProjeto;
	}

	public void setFiltroNomeProjeto(String filtroNomeProjeto) {
		this.filtroNomeProjeto = filtroNomeProjeto;
	}

	public List<TipoPrograma> getComboTipoPrograma() {
		return comboTipoPrograma;
	}

	public void setComboTipoPrograma(List<TipoPrograma> comboTipoPrograma) {
		this.comboTipoPrograma = comboTipoPrograma;
		
	}
	
	
	/*
	 * INICIA OS METODOS DE ANEXO.NAO ESQUECER DE COLOCAR QUANDO FOR CADASTRAR
	 * UM NOVO, EDITAR E VISUALIZAR CRIAR NO C:arquivos
	 */
	private List<FileUploadEvent> listaArquivosAnexados;
	private List<Anexo> anexos;
	private List<Anexo> listAnexos;
	private FileUploadEvent fileUploadEvent;
	private Anexo excluirAnexo;
	private StreamedContent file;
	static String VAR_AMBIENTE_DADOS_SISTEMA = "DADOS_SISTEMA";
	
	private List<FileUploadEvent> listaArqAnexadosPlan;
	private List<Anexo> anexosPlan;
	private List<Anexo> listAnexosPlan;
	private FileUploadEvent fileUploadEventPlan;
	//private Anexo excluirAnexoPlan;
	private StreamedContent filePlan;
	
	

	public void excluirAnexo(Integer tipo) {
		if (excluirAnexo != null && excluirAnexo.getId() != null) {
			deletarArquivoDiretorio(tipo);
			if(tipo.equals(1)){
				preencherListaAnexos();
			}else{
				preencherListaAnexosPlan();
			}
		} else {
			listarArquivosAnexados(tipo);
		}
		FacesContext.getCurrentInstance().addMessage(":gerencia:pAnexo",menssagemSucessoAnexo());
	}

	private FacesMessage menssagemSucessoAnexo() {
		return new FacesMessage(FacesMessage.SEVERITY_INFO, "Arquivo anexo excluído com sucesso.! ", "");
	}

	private void listarArquivosAnexados(Integer tipo) {
		if(tipo.equals(1)){
			if (listaArquivosAnexados != null) {
				List<FileUploadEvent> aux = listaArquivosAnexados;
				listaArquivosAnexados = new ArrayList<>();
				anexos = new ArrayList<>();
				for (FileUploadEvent fileUploadEvent : aux) {
					if (!fileUploadEvent.getFile().getFileName().equals(excluirAnexo.getDescricao())) {
						listaArquivosAnexados.add(fileUploadEvent);
						Anexo itemTemp = new Anexo();
						itemTemp.setDescricao(fileUploadEvent.getFile().getFileName());
						itemTemp.setIdEntidade(projeto.getId());
						itemTemp.setTipo(ETipoEntidade.PROJETO.getFlag());
						anexos.add(itemTemp);
					}
				}
			}
		}else{
			if (listaArqAnexadosPlan != null) {
				List<FileUploadEvent> aux = listaArqAnexadosPlan;
				listaArqAnexadosPlan = new ArrayList<>();
				anexosPlan = new ArrayList<>();
				for (FileUploadEvent fileUploadEvent : aux) {
					if (!fileUploadEvent.getFile().getFileName().equals(excluirAnexo.getDescricao())) {
						listaArqAnexadosPlan.add(fileUploadEvent);
						Anexo itemTemp = new Anexo();
						itemTemp.setDescricao(fileUploadEvent.getFile().getFileName());
						itemTemp.setIdEntidade(projeto.getId());
						itemTemp.setTipo(ETipoEntidade.PLANEJAMENTO.getFlag());
						anexosPlan.add(itemTemp);
					}
				}
			}
		}
	}

	private void deletarArquivoDiretorio(Integer tipo) {
		anexoService.excluir(excluirAnexo);
		File deletarArquivoDiretorio = new File(new StringBuilder(obterCaminhoAnexo(tipo)).append(File.separator)
					.append(excluirAnexo.getDescricao()).toString());
		deletarArquivoDiretorio.delete();
	}

	public static String obterCaminhoAnexo(Integer tipo) {
		if(tipo.equals(1)){
			return "C:\\DADOS_SISTEMA\\AGREGA\\ANEXOS\\PROJETO\\";
		}else{
			return "C:\\DADOS_SISTEMA\\AGREGA\\ANEXOS\\PLANEJAMENTO\\";
		}
	}

	public List<Anexo> preencherListaAnexos() {
		listAnexos = new ArrayList<>();
		if (projeto != null && projeto.getId() != null) {
			return listAnexos = anexoService.getByIdByTipo(ETipoEntidade.PROJETO.getFlag(), projeto.getId(), null);
		}
		return listAnexos;
	}
	
	public List<Anexo> preencherListaAnexosPlan() {
		listAnexosPlan = new ArrayList<>();
		if (projeto != null && projeto.getId() != null) {
			return listAnexosPlan = anexoService.getByIdByTipo(ETipoEntidade.PLANEJAMENTO.getFlag(), projeto.getId(), null);
		}
		return listAnexosPlan;
	}

	public void visualizaAnexo(Projeto item) {
		if (item != null) {
			preencherListaAnexos();
			preencherListaAnexosPlan();
		}
	}

	public void arquivoUploadPlan(FileUploadEvent uploadedFile) {
		File file = null;
		try {
			String nameArquivo = uploadedFile.getFile().getFileName();
			String name = retornaNomeArquivo(nameArquivo, "_PLN_");
			verificarUpload(uploadedFile, file, nameArquivo, name, ETipoEntidade.PLANEJAMENTO.getFlag());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(":gerencia:pAnexo",messagemErroArquivo(e));
		}
	}
	
	public void arquivoUpload2(FileUploadEvent uploadedFile) {
		File file = null;
		try {
			String nameArquivo = uploadedFile.getFile().getFileName();
			String name = retornaNomeArquivo(nameArquivo, "_PRJ_");
			verificarUpload(uploadedFile, file, nameArquivo, name, ETipoEntidade.PROJETO.getFlag());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(":gerencia:pAnexo",messagemErroArquivo(e));
		}
	}

	private void verificarUpload(FileUploadEvent uploadedFile, File file, String nameArquivo, String name, Long EtipoEntidade) {
		if (!jaExiste(name, EtipoEntidade)) {
			Anexo anexo = listarAnexos(uploadedFile, name, file, EtipoEntidade);
			salvarListaAnexos(anexo.getDescricao(), EtipoEntidade);
		} else {
			RequestContext context = RequestContext.getCurrentInstance();
			if(EtipoEntidade.equals(ETipoEntidade.PROJETO.getFlag())){
				context.update("pAnexo");
			}else{
				context.update("pAnexoPlaneja");
			}
			FacesContext.getCurrentInstance().addMessage(":gerencia:pAnexo",menssagemNaoEnexaArquivo(nameArquivo));
		}
	}

	private FacesMessage messagemErroArquivo(Exception e) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao anexar! ", "Erro: " + e.getMessage());
	}

	private FacesMessage menssagemNaoEnexaArquivo(String nameArquivo) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não é possível anexar o arquivo! ",
				"O " + nameArquivo + " já existe");
	}

	private Anexo listarAnexos(FileUploadEvent uploadedFile, String name, File file, Long etipoEntidade) {
		file = new File(etipoEntidade==4L?Constante.PROJETO:Constante.PLANEJAMANTO, name);
		OutputStream out;
		try {
			out = new FileOutputStream(file);
			out.write(uploadedFile.getFile().getContents());
			out.close();
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}
		Anexo anexo = new Anexo();
		anexo.setDescricao(name);
		if(etipoEntidade.equals(ETipoEntidade.PLANEJAMENTO.getFlag())){
			listaArquivosAnexados = new ArrayList<>();
			listaArquivosAnexados.add(uploadedFile);
			this.listAnexos.add(anexo);
		}else{
			listaArqAnexadosPlan = new ArrayList<>();
			listaArqAnexadosPlan.add(uploadedFile);
			this.listAnexosPlan.add(anexo);
		}
		return anexo;
	}

	private Boolean jaExiste(String name, Long tipo) {
		return anexoService.verificaSeAnexoJaExite(tipo, projeto.getId(), name);
	}

	private String retornaNomeArquivo(String nameArquivo, String tipoSigla) {
		String name = nameArquivo.substring(0, nameArquivo.indexOf('.')).replace(" ", "_");
		String extensao = nameArquivo.substring(nameArquivo.indexOf('.'));
		String subtipo = projeto.getSubTipoProjeto();
		String numeroProjeto = projeto.getNumeroProjetoAgrega();
		Long anoReferencia = projeto.getAnoReferencia();
		if (anoReferencia != null) {
			name = name + tipoSigla + subtipo + numeroProjeto + anoReferencia + extensao;
		} else {
			name = name + tipoSigla + subtipo + numeroProjeto + extensao;
		}
		return name;
	}

	private void salvarListaAnexos(String nomeArquivo, Long eTipoEntidade) {
			Anexo anexo = new Anexo();
			anexo.setDescricao(nomeArquivo);
			anexo.setIdEntidade(projeto.getId());
			anexo.setTipo(eTipoEntidade);
			anexoService.salvar(anexo);
			RequestContext context = RequestContext.getCurrentInstance();
			if(eTipoEntidade.equals(ETipoEntidade.PROJETO.getFlag())){
				context.update("tabelaListaAnexoSelecionado");
				preencherListaAnexos();
			}else{
				context.update("tabelaListaAnexoSelecionadoPlan");
				preencherListaAnexosPlan();
			}
			FacesContext.getCurrentInstance().addMessage(":gerencia:pAnexo",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "O arquivos foi anexado com sucesso! ", ""));
	}
	
	public StreamedContent downloadAnexo(String arquivo, Integer tipo) {
		try {
			String caminho = obterCaminhoAnexo(tipo) + arquivo;
			FileInputStream stream = new FileInputStream(caminho);
			
			if(tipo.equals(1)){
				return file = new DefaultStreamedContent(stream, caminho, arquivo);
			}else{
				return	filePlan = new DefaultStreamedContent(stream, caminho, arquivo);
			}
		} catch (FileNotFoundException e) {
			FacesContext.getCurrentInstance().addMessage(":gerencia:pAnexo",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Anexo não encontrado. ", ""));
			return null;
		}
	}
	
	

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public FileUploadEvent getFileUploadEvent() {
		arquivoUpload2(fileUploadEvent);
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

	public List<Anexo> getListAnexos() {
		return listAnexos;
	}

	public void setListAnexos(List<Anexo> listAnexos) {
		this.listAnexos = listAnexos;
	}

	public String getFiltroProjetoCliente() {
		return filtroProjetoCliente;
	}

	public void setFiltroProjetoCliente(String filtroProjetoCliente) {
		this.filtroProjetoCliente = filtroProjetoCliente;
	}

	public String getFiltroAnoProjeto() {
		return filtroAnoProjeto;
	}

	public void setFiltroAnoProjeto(String filtroAnoProjeto) {
		this.filtroAnoProjeto = filtroAnoProjeto;
	}

	public Long getIdMapas() {
		return idMapas;
	}

	public void setIdMapas(Long idMapas) {
		this.idMapas = idMapas;
	}

	public Long getIdEPI() {
		return idEPI;
	}

	public void setIdEPI(Long idEPI) {
		this.idEPI = idEPI;
	}

	public Long getIdEvctga() {
		return idEvctga;
	}

	public void setIdEvctga(Long idEvctga) {
		this.idEvctga = idEvctga;
	}

	public Long getIdVerificacao() {
		return idVerificacao;
	}

	public void setIdVerificacao(Long idVerificacao) {
		this.idVerificacao = idVerificacao;
	}

	public Date getPlInicio() {
		return plInicio;
	}

	public void setPlInicio(Date plInicio) {
		this.plInicio = plInicio;
	}

	public Date getPlFim() {
		return plFim;
	}

	public void setPlFim(Date plFim) {
		this.plFim = plFim;
	}

	public Date getPlEntrega() {
		return plEntrega;
	}

	public void setPlEntrega(Date plEntrega) {
		this.plEntrega = plEntrega;
	}

	public boolean getJaAlterado() {
		return jaAlterado;
	}

	public void setJaAlterado(boolean jaAlterado) {
		this.jaAlterado = jaAlterado;
	}

	public Long getSitPlanejamento() {
		return sitPlanejamento;
	}

	public void setSitPlanejamento(Long sitPlanejamento) {
		this.sitPlanejamento = sitPlanejamento;
	}

	public List<SituacaoPlanejamento> getListSitPlanejamento() {
		return listSitPlanejamento;
	}

	public void setListSitPlanejamento(List<SituacaoPlanejamento> listSitPlanejamento) {
		this.listSitPlanejamento = listSitPlanejamento;
	}

	public Long getPlanSeleect() {
		return planSeleect;
	}

	public void setPlanSeleect(Long planSeleect) {
		this.planSeleect = planSeleect;
	}

	public IPlanejamentos getPlanSelecionado() {
		return planSelecionado;
	}

	public void setPlanSelecionado(IPlanejamentos planSelecionado) {
		this.planSelecionado = planSelecionado;
	}

	public String getTituloPlan() {
		return tituloPlan;
	}

	public void setTituloPlan(String tituloPlan) {
		this.tituloPlan = tituloPlan;
	}

	public Boolean getValidaBotaoPlan() {
		return validaBotaoPlan;
	}

	public void setValidaBotaoPlan(Boolean validaBotaoPlan) {
		this.validaBotaoPlan = validaBotaoPlan;
	}

	public Boolean getValidaBotaoNovoPlan() {
		return validaBotaoNovoPlan;
	}

	public void setValidaBotaoNovoPlan(Boolean validaBotaoNovoPlan) {
		this.validaBotaoNovoPlan = validaBotaoNovoPlan;
	}

	public Boolean getHabilitaPlanejamento() {
		return habilitaPlanejamento;
	}

	public void setHabilitaPlanejamento(Boolean habilitaPlanejamento) {
		this.habilitaPlanejamento = habilitaPlanejamento;
	}

	public String getVlrColuna() {
		return vlrColuna;
	}

	public void setVlrColuna(String vlrColuna) {
		this.vlrColuna = vlrColuna;
	}

	public boolean isOculparCampos() {
		return oculparCampos;
	}

	public void setOculparCampos(boolean oculparCampos) {
		this.oculparCampos = oculparCampos;
	}

	public TreeNode getSeia() {
		return seia;
	}

	public void setSeia(TreeNode Seia) {
		this.seia = Seia;
	}

	public TreeNode getAreaLago() {
		return areaLago;
	}

	public void setAreaLago(TreeNode areaLago) {
		this.areaLago = areaLago;
	}

	
	public TreeNode getTopoMorro() {
		return topoMorro;
	}

	public void setTopoMorro(TreeNode topoMorro) {
		this.topoMorro = topoMorro;
	}

	public TreeNode getParticular() {
		return particular;
	}

	public void setParticular(TreeNode particular) {
		this.particular = particular;
	}

	public TreeNode getRca() {
		return rca;
	}

	public void setRca(TreeNode rca) {
		this.rca = rca;
	}
	
	public int getCurrentTab() {
	    return currentTab;
	}

	public void setCurrentTab(int currentTab) {
	    this.currentTab = currentTab;
	}

	public List<FileUploadEvent> getListaArqAnexadosPlan() {
		return listaArqAnexadosPlan;
	}

	public void setListaArqAnexadosPlan(List<FileUploadEvent> listaArqAnexadosPlan) {
		this.listaArqAnexadosPlan = listaArqAnexadosPlan;
	}

	public List<Anexo> getAnexosPlan() {
		return anexosPlan;
	}

	public void setAnexosPlan(List<Anexo> anexosPlan) {
		this.anexosPlan = anexosPlan;
	}

	public List<Anexo> getListAnexosPlan() {
		return listAnexosPlan;
	}

	public void setListAnexosPlan(List<Anexo> listAnexosPlan) {
		this.listAnexosPlan = listAnexosPlan;
	}

	public FileUploadEvent getFileUploadEventPlan() {
		arquivoUploadPlan(fileUploadEventPlan);
		return fileUploadEventPlan;
	}

	public void setFileUploadEventPlan(FileUploadEvent fileUploadEventPlan) {
		this.fileUploadEventPlan = fileUploadEventPlan;
	}

	public StreamedContent getFilePlan() {
		return filePlan;
	}

	public void setFilePlan(StreamedContent filePlan) {
		this.filePlan = filePlan;
	}

	public TreeNode getRootIntervencao() {
		return rootIntervencao;
	}

	public void setRootIntervencao(TreeNode rootIntervencao) {
		this.rootIntervencao = rootIntervencao;
	}

	public TreeNode[] getSelectedIntervecaoNodes() {
		return selectedIntervecaoNodes;
	}

	public void setSelectedIntervecaoNodes(TreeNode[] selectedIntervecaoNodes) {
		this.selectedIntervecaoNodes = selectedIntervecaoNodes;
	}

	public List<OrdemServico> getListOSNovoProjeto() {
		return listOSNovoProjeto;
	}

	public void setListOSNovoProjeto(List<OrdemServico> listOSNovoProjeto) {
		this.listOSNovoProjeto = listOSNovoProjeto;
	}

	public Long getIdContratoNovoProjeto() {
		return idContratoNovoProjeto;
	}

	public void setIdContratoNovoProjeto(Long idContratoNovoProjeto) {
		this.idContratoNovoProjeto = idContratoNovoProjeto;
	}

	
}