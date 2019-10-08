package br.com.sysagrega.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.sysagrega.model.Enums.ETipoProjeto;
import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.model.imp.Contrato;
import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.model.imp.Municipio;
import br.com.sysagrega.model.imp.OrdemServico;
import br.com.sysagrega.model.imp.Planejamento;
import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.model.imp.Projeto;
import br.com.sysagrega.model.imp.Status;
import br.com.sysagrega.model.imp.VerificacaoProfissional;
import br.com.sysagrega.service.ICaracteristicaProjetoService;
import br.com.sysagrega.service.IClienteService;
import br.com.sysagrega.service.IContratoService;
import br.com.sysagrega.service.ICoordenadaService;
import br.com.sysagrega.service.IEstadoService;
import br.com.sysagrega.service.IFaturamentoService;
import br.com.sysagrega.service.IMacroRegiaoService;
import br.com.sysagrega.service.IMunicipioService;
import br.com.sysagrega.service.IOrdemServicoService;
import br.com.sysagrega.service.IPlanejamentoService;
import br.com.sysagrega.service.IProfissionalService;
import br.com.sysagrega.service.IProjetoService;
import br.com.sysagrega.service.IRegiaoService;
import br.com.sysagrega.service.IRetificacaoService;
import br.com.sysagrega.service.ISituacaoService;
import br.com.sysagrega.service.IStatusService;
import br.com.sysagrega.service.ITrajetoRedeService;
import br.com.sysagrega.service.IVerificacaoProfissionalService;
import br.com.sysagrega.service.imp.NotificacaoService;
import br.com.sysagrega.service.imp.RegistroService;
import br.com.sysagrega.service.imp.TipoNotificacaoService;
import br.com.sysagrega.service.imp.TipoRetificacaoService;
import br.com.sysagrega.util.RelatorioUtil;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped

public class RelatorioProjetoBean implements Serializable {

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
	private IMunicipioService municipioService;

	@Inject
	private IRegiaoService regiaoService;

	@Inject
	private ICaracteristicaProjetoService caracteristicasProjetoService;

	@Inject
	private IContratoService contratoService;

	@Inject
	private IPlanejamentoService planejamentoService;

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
	private NotificacaoService notificacaoService;

	@Inject
	private RegistroService registroService;

	@Inject
	private TipoNotificacaoService tipoNotificacaoService;

	@Inject
	private TipoRetificacaoService tipoRetificacaoService;

	private List<Projeto> projetos;	
	private List<Planejamento> planejamentos;	
	private List<OrdemServico> ordensServicos;	
	private List<Contrato> contratos;
	private List<Planejamento> planejamentoByProfissional;
	private List<Cliente> comboClientes;
	private List<Status> comboStatus;
	private List<Estado> comboEstado;
	private List<Municipio> comboMunicipio;
	private List<String> comboTipoProjeto;
	private List<Profissional> comboProfissionais;
	
	private Long filtroContrato;
	private Long filtroOrdemServico;
	private Long filtroProjeto;
	private Long filtroCliente;
	private Long filtroStatus;
	private Date filtroDataInicial;
	private Date filtroDataFinal;
	private Long filtroEstado;
	private Long filtroMunicipio;
	private String filtroTipoProjeto;


	
	private Long filtroProfisionalEPI;
	private Long filtroProfissionalEVCTGAL;
	private Long filtroProfisionalMapas;
	private Long filtroProfissionalVerificacao;
	
	private String opcaoSelecionada = null;
	
	private boolean eprojeto;
	private boolean eos;
	

	@PostConstruct
	public void inicializar() {
		contratos = contratoService.getAllContratos();
		comboClientes = clienteService.getAllClientes();
		comboStatus = statusService.obterByTipo(1L);
		comboEstado = estadoService.getAllEstados();
		comboTipoProjeto = new ArrayList<>();
		comboProfissionais = profissionalService.getAllProfissionals();
		opcaoSelecionada = null;
		for (ETipoProjeto item : ETipoProjeto.values()) {
			comboTipoProjeto.add(item.getDescricao());
		}
		eos = true;
		eprojeto = false;
	}
	
	public void limpar(){
		projetos = null;
		filtroContrato = null;
		filtroOrdemServico= null;
		filtroProjeto= null;
		filtroCliente= null;
		filtroStatus= null;
		filtroDataInicial= null;
		filtroDataFinal= null;
		ordensServicos = null;
		projetos = null;
		filtroEstado = null;
		filtroMunicipio = null;
		filtroTipoProjeto = null;
		eprojeto = false;
		eos = true;
		filtroProfisionalMapas = null;
		filtroProfisionalEPI = null;
		filtroProfissionalEVCTGAL = null;
		filtroProfissionalVerificacao = null;
	}
	Planejamento filtroPlanejamento;
	
	public void buscar() throws ParseException{
	
	if(opcaoSelecionada == null || opcaoSelecionada==""){
		FacesUtil.addErrorMessage("Escolha a operação que deseja realizar: Pesquisa por Projeto ou Ordem de Serviço.");
		return;
	}
		
	if(eprojeto){
		if(filtroProfisionalEPI != null
				|| filtroProfisionalMapas != null
				|| filtroProfissionalVerificacao != null
				|| filtroProfissionalEVCTGAL != null
				|| filtroDataInicial !=null
				|| filtroDataFinal != null){
		
		if(filtroDataInicial != null && filtroDataFinal == null || filtroDataFinal != null && filtroDataInicial == null){
			FacesUtil.addErrorMessage("Não é possível realizar a consulta com apenas uma data. Por favor, informe as duas datas.");
			return;
		}
		buscarPlanejamentos();
			
		}else{ 
			buscarProjetos();
		}
	}else{
		buscarOrdensServico();
	}
	
	if(projetos == null){
		projetos = new ArrayList<>();
	}
	if(ordensServicos == null){
		ordensServicos = new ArrayList<>();
	}
	
	if(eprojeto && projetos.size()==0){
		FacesUtil.addErrorMessage("Nenhum registro encontrato.");
	} else if(eos && ordensServicos.size()==0){
		FacesUtil.addErrorMessage("Nenhum registro encontrato.");
	}
}
	
	public void gerarRelatorioOS(){
		String nomeRelatorio = "RelatorioOrdensServico";
		buscarOrdensServico();; 
		OrdemServico filtro = new OrdemServico();
		
		for (OrdemServico item : ordensServicos) {
			item.setQtdProjetos(projetoService.getListaByOS(item.getId(), null, null) ==null ? 0 : projetoService.getListaByOS(item.getId(), null, null).size());
		}
		
		if(filtroOrdemServico != null){
			filtro.setId(filtroOrdemServico);
			filtro = ordemServicoService.getPorID(filtroOrdemServico);
		}
		if(filtroContrato != null){
			filtro.setContrato(contratoService.getContratoById(filtroContrato));
		}
		
		
		RelatorioUtil.gerarRelatorioOS(nomeRelatorio,ordensServicos,filtro);
		FacesUtil.addInfoMessage("Relatório gerado com sucesso.");
	}
	
	
	public void gerarRelatorio() throws ParseException{
		String nomeRelatorio = "RelatorioProjetosFiltros";
		
			buscarProjetos();
			Projeto filtro = new Projeto();
			
			if(filtroContrato != null){
				filtro.setContrato(contratoService.getContratoById(filtroContrato));
			}
			if(filtroOrdemServico != null){
				filtro.setOrdemServico(ordemServicoService.getById(filtroOrdemServico));
			}
			if(filtroProjeto != null){
				filtro.setId(filtroProjeto);
			}
			if(filtroCliente != null){
				filtro.setCliente(clienteService.getClienteById(filtroCliente));
			}
			if(filtroMunicipio != null){
				filtro.setMunicipio(municipioService.getMunicipioById(filtroMunicipio));
			}
			if(filtroTipoProjeto != null){
				filtro.setTipoProjeto(filtroTipoProjeto);
			}
			if(filtroDataInicial != null){
				filtro.setFiltroDataInicial(filtroDataInicial);
			}
			if(filtroDataFinal != null){
				filtro.setFiltroDataFinal(filtroDataFinal);	
			}
			
			RelatorioUtil.gerarRelatorioProjeto(nomeRelatorio,projetos, filtro);
			FacesUtil.addInfoMessage("Relatório gerado com sucesso.");
	}
	

	public void buscarOrdensServico(){
		
		OrdemServico filtro = new OrdemServico();
		
		if(filtroContrato != null){
			filtro.setContrato(contratoService.getContratoById(filtroContrato));
		}else{
			filtro.setContrato(null);
		}
		if(filtroOrdemServico != null){
			filtro.setId(filtroOrdemServico);
		} else{
			filtro.setId(null);
		}
		if(filtroCliente != null ){
			filtro.setCliente(clienteService.getClienteById(filtroCliente));
		}
		
		ordensServicos = ordemServicoService.getByFiltros(filtro);
		
		if(ordensServicos !=null && ordensServicos.size()>0){
			for (OrdemServico item : ordensServicos) {
				item.setQtdProjetos(projetoService.getListaByOS(item.getId(), null, null) ==null ? 0 : projetoService.getListaByOS(item.getId(), null, null).size());
			}
		}
	}
	
	public void buscarPlanejamentos(){
		projetos = new ArrayList<>();
		filtroPlanejamento = new Planejamento();
		
		if(filtroProfisionalEPI!= null){
			filtroPlanejamento.setProfissionalEPI(profissionalService.getById(filtroProfisionalEPI));
		}else{
			filtroPlanejamento.setProfissionalEPI(null);
		}
		
		if(filtroProfisionalMapas!= null){
			filtroPlanejamento.setProfissionalMapas(profissionalService.getById(filtroProfisionalMapas));
		}else{
			filtroPlanejamento.setProfissionalMapas(null);
		}
		if(filtroProfissionalEVCTGAL!= null){
			filtroPlanejamento.setProfissionalEVCTGAL(profissionalService.getById(filtroProfissionalEVCTGAL));
		}else{
			filtroPlanejamento.setProfissionalEVCTGAL(null);
		}
		
		if(filtroProfissionalVerificacao!= null){
			filtroPlanejamento.setProfissionalVerificacao(profissionalService.getById(filtroProfissionalVerificacao));
		}else{
			filtroPlanejamento.setProfissionalVerificacao(null);
		}
		if(filtroDataInicial != null){
			filtroPlanejamento.setDtInicioPlanejamento(filtroDataInicial);
		}else{
			filtroPlanejamento.setDtInicioPlanejamento(null);
		}
		
		if(filtroDataFinal!= null){
			filtroPlanejamento.setDtfimPlanejamento(filtroDataFinal);
		}else{
			filtroPlanejamento.setDtfimPlanejamento(null);
		}
		planejamentos = planejamentoService.getByFiltros(filtroPlanejamento);
		
		if(planejamentos == null){
			planejamentos = new ArrayList<>();
		}
		if(planejamentos.size()>0){
			
			for (Planejamento item : planejamentos) {
				 projetos.add(projetoService.getPorID(item.getProjeto().getId()));
			}
		}
		
	}
	
	
	public void buscarProjetos() throws ParseException{
 
			Projeto filtro = new Projeto();
			if(filtroProjeto != null){
				filtro.setId(filtroProjeto);
			}else{
				filtro.setId(null);
			}
			
			if(filtroOrdemServico != null){
				filtro.setOrdemServico(ordemServicoService.getById(filtroOrdemServico));
			}else{
				filtro.setOrdemServico(null);
			}
			if(filtroStatus != null){
				filtro.setStatus(statusService.getById(filtroStatus));
			}else{
				filtro.setStatus(null);
			}
			if(filtroDataInicial != null){
				filtro.setFiltroDataInicial(filtroDataInicial);
			}else{
				filtro.setFiltroDataInicial(null);
			}
			if(filtroDataFinal != null){
				filtro.setFiltroDataFinal(filtroDataFinal);
			}else{
				filtro.setFiltroDataFinal(null);
			}
			if(filtroTipoProjeto != null){
				filtro.setTipoProjeto(filtroTipoProjeto);
			}else{
				filtro.setTipoProjeto(null);
			}
			
			if(filtroCliente != null ){
				filtro.setCliente(clienteService.getClienteById(filtroCliente));
			}
			
			if(filtroEstado != null){
				if(filtroMunicipio != null){
					filtro.setMunicipio(municipioService.getMunicipioById(filtroMunicipio));
				}else{
					FacesUtil.addErrorMessage("Por favor, selecione o município.");
					return;
				}
				
			}else{
				filtro.setMunicipio(null);
			}
			
			planejamentos = new ArrayList<>();
			projetos = projetoService.getByFiltros(filtro);
			
	}
	
	public void verificarSeOsProjeto(String tipoSelecionado) {
		projetos = null;
		ordensServicos = null;
		limpar();
		if (tipoSelecionado != null && tipoSelecionado.equalsIgnoreCase("OS")) {
			eprojeto = false;
			eos = true;
			
		} else {
			eprojeto = true;
			eos = false;
		}
	}
	
public void verProjetosByOS(Long idOS){
	projetos = projetoService.getListaByOS(idOS, null, null);
	RequestContext.getCurrentInstance().execute("PF('dialogVerProjetos').show();");
}
	
	public void obterMunicipios() {
		comboMunicipio = new ArrayList<>();
 
		if (filtroEstado != null) {
			comboMunicipio = municipioService.getMunicipioByEstado(filtroEstado);
		}
		 
	}
	
	public void obterByOrdemServico(){
		if(filtroContrato != null){
			OrdemServico filtro = new OrdemServico();
			filtro.setContrato(contratoService.getContratoById(filtroContrato));
			ordensServicos = ordemServicoService.getByFiltros(filtro);
		}else{
			ordensServicos = null;
		}
	}
	
	public List<OrdemServico> getOrdensServicos() {
		return ordensServicos;
	}


	public void setOrdensServicos(List<OrdemServico> ordensServicos) {
		this.ordensServicos = ordensServicos;
	}


	public List<Contrato> getContratos() {
		return contratos;
	}


	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}


	public List<Planejamento> getPlanejamentoByProfissional() {
		return planejamentoByProfissional;
	}


	public void setPlanejamentoByProfissional(List<Planejamento> planejamentoByProfissional) {
		this.planejamentoByProfissional = planejamentoByProfissional;
	}


	public List<Cliente> getComboClientes() {
		return comboClientes;
	}

	public void setComboClientes(List<Cliente> comboClientes) {
		this.comboClientes = comboClientes;
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

	public Long getFiltroContrato() {
		return filtroContrato;
	}


	public void setFiltroContrato(Long filtroContrato) {
		this.filtroContrato = filtroContrato;
	}


	public Long getFiltroOrdemServico() {
		return filtroOrdemServico;
	}


	public void setFiltroOrdemServico(Long filtroOrdemServico) {
		this.filtroOrdemServico = filtroOrdemServico;
	}


	public Long getFiltroProjeto() {
		return filtroProjeto;
	}


	public void setFiltroProjeto(Long filtroProjeto) {
		this.filtroProjeto = filtroProjeto;
	}


	public Long getFiltroCliente() {
		return filtroCliente;
	}


	public void setFiltroCliente(Long filtroCliente) {
		this.filtroCliente = filtroCliente;
	}


	public List<Projeto> getProjetos() {
		return projetos;
	}


	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public boolean isEprojeto() {
		return eprojeto;
	}

	public void setEprojeto(boolean eprojeto) {
		this.eprojeto = eprojeto;
	}

	public boolean isEos() {
		return eos;
	}

	public void setEos(boolean eos) {
		this.eos = eos;
	}

	public List<Status> getComboStatus() {
		return comboStatus;
	}

	public void setComboStatus(List<Status> comboStatus) {
		this.comboStatus = comboStatus;
	}

	public Long getFiltroStatus() {
		return filtroStatus;
	}

	public void setFiltroStatus(Long filtroStatus) {
		this.filtroStatus = filtroStatus;
	}
	public List<Estado> getComboEstado() {
		return comboEstado;
	}
	public void setComboEstado(List<Estado> comboEstado) {
		this.comboEstado = comboEstado;
	}
	public List<Municipio> getComboMunicipio() {
		return comboMunicipio;
	}
	public void setComboMunicipio(List<Municipio> comboMunicipio) {
		this.comboMunicipio = comboMunicipio;
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
	public List<String> getComboTipoProjeto() {
		return comboTipoProjeto;
	}
	public void setComboTipoProjeto(List<String> comboTipoProjeto) {
		this.comboTipoProjeto = comboTipoProjeto;
	}
	public String getFiltroTipoProjeto() {
		return filtroTipoProjeto;
	}
	public void setFiltroTipoProjeto(String filtroTipoProjeto) {
		this.filtroTipoProjeto = filtroTipoProjeto;
	}

	public String getOpcaoSelecionada() {
		return opcaoSelecionada;
	}

	public void setOpcaoSelecionada(String opcaoSelecionada) {
		this.opcaoSelecionada = opcaoSelecionada;
	}

	public List<Profissional> getComboProfissionais() {
		return comboProfissionais;
	}

	public void setComboProfissionais(List<Profissional> comboProfissionais) {
		this.comboProfissionais = comboProfissionais;
	}

	public Long getFiltroProfisionalMapas() {
		return filtroProfisionalMapas;
	}

	public void setFiltroProfisionalMapas(Long filtroProfisionalMapas) {
		this.filtroProfisionalMapas = filtroProfisionalMapas;
	}

	public Long getFiltroProfisionalEPI() {
		return filtroProfisionalEPI;
	}

	public void setFiltroProfisionalEPI(Long filtroProfisionalEPI) {
		this.filtroProfisionalEPI = filtroProfisionalEPI;
	}

	public Long getFiltroProfissionalEVCTGAL() {
		return filtroProfissionalEVCTGAL;
	}

	public void setFiltroProfissionalEVCTGAL(Long filtroProfissionalEVCTGAL) {
		this.filtroProfissionalEVCTGAL = filtroProfissionalEVCTGAL;
	}

	public Long getFiltroProfissionalVerificacao() {
		return filtroProfissionalVerificacao;
	}

	public void setFiltroProfissionalVerificacao(Long filtroProfissionalVerificacao) {
		this.filtroProfissionalVerificacao = filtroProfissionalVerificacao;
	}

	public List<Planejamento> getPlanejamentos() {
		return planejamentos;
	}

	public void setPlanejamentos(List<Planejamento> planejamentos) {
		this.planejamentos = planejamentos;
	}
	
	
	
	
}
 