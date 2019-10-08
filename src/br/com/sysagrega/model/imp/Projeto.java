
package br.com.sysagrega.model.imp;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sysagrega.model.ICaracteristicaProjeto;
import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.IContrato;
import br.com.sysagrega.model.IEstado;
import br.com.sysagrega.model.IMunicipio;
import br.com.sysagrega.model.IOrdemServico;
import br.com.sysagrega.model.IPlanejamentos;
import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.IProjeto;
@Entity
@Table(name = "TB_PROJETO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_proj", sequenceName = "agrega.tb_seq_proj", allocationSize = 1)

public class Projeto implements IProjeto{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_proj")
	private Long id;
	private Boolean verificaNotificacao;
	private String numeroProjetoAgrega;
	private String numeroProjetoCliente;
	private String nomeProjeto;
	private String obsEmpresaExecutora;
	private Long modalidade; // 1 - Modalidade Antiga | 2 - Modalidade Nova.
	@Transient
	private String filtroIDS;
	
	@OneToOne(targetEntity = Situacao.class, cascade = CascadeType.REFRESH)
	private Situacao situacao;
	
	@OneToOne(targetEntity = TipoPrograma.class, cascade = CascadeType.REFRESH)
	private TipoPrograma tipoPrograma;
	
	@OneToOne(targetEntity = Status.class, cascade = CascadeType.REFRESH)
	private Status status;
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private IProfissional profissionalGestor;
	
	@OneToOne(targetEntity = Contrato.class, cascade = CascadeType.REFRESH)
	private IContrato contrato;
	
	@OneToOne(targetEntity = Estado.class, cascade = CascadeType.REFRESH)
	private IEstado estadoDadosTecnico;

	@OneToOne(targetEntity = Municipio.class, cascade = CascadeType.REFRESH)
	private IMunicipio municipio;
	
	@OneToOne(targetEntity = CaracteristicaProjeto.class, cascade = CascadeType.REFRESH)
	private ICaracteristicaProjeto caracteristicasProjeto;
	
	@OneToOne(targetEntity = OrdemServico.class, cascade = CascadeType.REFRESH)
	private IOrdemServico ordemServico;
	
	private Long idOS;
	@Column(name = "ORDEMSERVICO_ID")
	public Long getIdOS() {
		return this.idOS;
	}

	public void setIdOs(Long idOS) {
		this.idOS = idOS;
	}
	
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.REFRESH)
	private Usuario usuarioAtualizacao;

	@OneToOne(targetEntity = Cliente.class, cascade = CascadeType.REFRESH)
	private ICliente cliente;
	private Date dataEntrada;
	private String tipoProjeto;
	private String subTipoProjeto;
	private Long anoReferencia;
	private String numeroArt;
	private Boolean possuiLPT;
	private String empresaProjetoEletrico;
	private String telResponsavelEletrico;
	private Boolean meioFisico;
	private Boolean meioSocioEconomico;
	private Boolean ep;
	private Double primariaKm;
	private Double secundariaKm;
	private Double conjugadaKm;
	private Double extensaoTotalKm; //primario + secundario
	private Double areaHa;
	private Long postesPrimario;
	private Long postesSecundario;
	private Long postesTotal; //poste primario + secundario
	private Long consumidores;
	private Double tensaoPrimariaKv;
	private Double tensaoSecundariaKV;
	private String cabos;
	private String faixaServidaoM;
	private Double tamanhoProjeto;
	private Double distanciaSedeMunPav;
	private Double distanciaSedeMunNaoPav;
	private Double distanciaMunLocalProjPav;
	private Double distanciaMunLocalProjNaoPav;
	@Column(length=1001)
	private String obsDadosTecnico;
	@Column(length=100)
	private String obsDadosFronteira;
	private Boolean supresaoVegetacao;
	private Boolean unidadaConservacao;
	private Boolean sitioArquilogico;
	@Column(length=1001)
	private String obsDadosCampo;
	private String obsCaracteristicas;
	private Double valorProjeto;
	private Double valorJaCobrado;
	private Double saldoRestante;
	@Column(length=1001)
	private String observacoesPagamento;
	private String obsVerificacao;
	private Boolean ativo = true;
	private Boolean jaPlanejado = false;
	
	@OneToOne(targetEntity = Planejamentos.class, cascade = CascadeType.REFRESH)
	private IPlanejamentos planejamentos;
	private Long situacaoPlanejamento;
	private Boolean checkMapa;
	private Boolean checkEPI;
	private Boolean checkEvCtga;
	private Boolean checkVerificacap;

	public Double getObterValorByParcela(Long parcela){
		Double valorParcela = 0.0;
		if(getValorProjeto() != null){
			valorParcela = (getValorProjeto())*parcela/100;
		}
		return valorParcela;
	}
	
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	
	@Temporal(TemporalType.DATE)
	private Date dataAtualizacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataIncialCampo;
	
	@Temporal(TemporalType.DATE)
	private Date dataFinalCampo;
	
	@Transient
	Date filtroDataInicial;
	
	@Transient
	Date filtroDataFinal;
	
	@Transient
	Date FiltroDataEntradaoInicial;
	
	@Transient
	Date FiltroDataEntradaoFinal;
	
	@Transient
	private Long todos;
	
	@Transient
	private VerificacaoProfissional verificacaoProfissional;
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private IProfissional profEpi;
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private IProfissional profEvctgal;
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private IProfissional profMapas;
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private IProfissional profVerif;
	
	@Temporal(TemporalType.DATE)
	private Date dtIniPlanj;
	
	@Temporal(TemporalType.DATE)
	private Date dtFimPlanj;
	
	@Temporal(TemporalType.DATE)
	private Date dtEntrega;
	
	@Column(length=1001)
	private String obsPlanejamento;
	
	@Transient
	@Override
	public boolean isNovo() {
		return getId() == null;
	}
	
	@Transient
	@Override
	public boolean isExistente() {
		return !isNovo();
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;		
	}
	
	@Override
	public Usuario getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}
	
	@Override
	public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}
	
	@Override
	public Date getDataInclusao() {
		return dataInclusao;
	}
	
	@Override
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	@Override
	public IOrdemServico getOrdemServico() {
		return ordemServico;
	}
	
	@Override
	public void setOrdemServico(IOrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	
	@Override
	public String getTipoProjeto() {
		return tipoProjeto;
	}
	
	@Override
	public void setTipoProjeto(String tipoProjeto) {
		this.tipoProjeto = tipoProjeto;
	}
	
	@Override
	public Boolean getAtivo() {
		return ativo;
	}
	
	@Override
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String getNumeroArt() {
		return numeroArt;
	}
	
	@Override
	public void setNumeroArt(String numeroArt) {
		this.numeroArt = numeroArt;
	}
	
	
	@Override
	public String getNumeroProjetoAgrega() {
		return numeroProjetoAgrega;
	}
	
	@Override
	public void setNumeroProjetoAgrega(String numeroProjetoAgrega) {
		this.numeroProjetoAgrega = numeroProjetoAgrega;
	}
	
	@Override
	public Date getDataEntrada() {
		return dataEntrada;
	}
	
	@Override
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	@Override
	public String getNumeroProjetoCliente() {
		return numeroProjetoCliente;
	}
	
	@Override
	public void setNumeroProjetoCliente(String numeroProjetoCliente) {
		this.numeroProjetoCliente = numeroProjetoCliente;
	}
	
	@Override
	public String getNomeProjeto() {
		return nomeProjeto;
	}
	
	@Override
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}
	
	@Override
	public IProfissional getProfissionalGestor() {
		return profissionalGestor;
	}
	
	@Override
	public void setProfissionalGestor(IProfissional profissionalGestor) {
		this.profissionalGestor = profissionalGestor;
	}
	
	@Override
	public String getSubTipoProjeto() {
		return subTipoProjeto;
	}
	
	@Override
	public void setSubTipoProjeto(String subTipoProjeto) {
		this.subTipoProjeto = subTipoProjeto;
	}
	
	@Override
	public IMunicipio getMunicipio() {
		return municipio;
	}
	
	@Override
	public void setMunicipio(IMunicipio municipio) {
		this.municipio = municipio;
	}
	
	@Override
	public Double getTamanhoProjeto() {
		return tamanhoProjeto;
	}
	
	@Override
	public void setTamanhoProjeto(Double tamanhoProjeto) {
		this.tamanhoProjeto = tamanhoProjeto;
	}
	
	@Override
	public String getObsDadosTecnico() {
		return obsDadosTecnico;
	}
	
	@Override
	public void setObsDadosTecnico(String obsDadosTecnico) {
		this.obsDadosTecnico = obsDadosTecnico;
	}
		
	@Override
	public ICaracteristicaProjeto getCaracteristicasProjeto() {
		return caracteristicasProjeto;
	}
	@Override
	public void setCaracteristicasProjeto(ICaracteristicaProjeto caracteristicasProjeto) {
		this.caracteristicasProjeto = caracteristicasProjeto;
	}
/*
	@Override
	public IPlanejamento getPlanejamento() {
		return planejamento;
	}

	@Override
	public void setPlanejamento(IPlanejamento planejamento) {
		this.planejamento = planejamento;
	}*/

	@Override
	public Boolean getPossuiLPT() {
		return possuiLPT;
	}
	
	@Override
	public void setPossuiLPT(Boolean possuiLPT) {
		this.possuiLPT = possuiLPT;
	}
	

	@Override
	public IEstado getEstadoDadosTecnico() {
		return estadoDadosTecnico;
	}

	@Override
	public void setEstadoDadosTecnico(IEstado estadoDadosTecnico) {
		this.estadoDadosTecnico = estadoDadosTecnico;
	}
	 
	
	@Override
	public Boolean getSupresaoVegetacao() {
		return supresaoVegetacao;
	}

	@Override
	public void setSupresaoVegetacao(Boolean supresaoVegetacao) {
		this.supresaoVegetacao = supresaoVegetacao;
	}

	@Override
	public Boolean getUnidadaConservacao() {
		return unidadaConservacao;
	}
	
	@Override
	public void setUnidadaConservacao(Boolean unidadaConservacao) {
		this.unidadaConservacao = unidadaConservacao;
	}
	
	@Override
	public String getObsDadosCampo() {
		return obsDadosCampo;
	}
	
	@Override
	public void setObsDadosCampo(String obsDadosCampo) {
		this.obsDadosCampo = obsDadosCampo;
	}

	@Override
	public String getEmpresaProjetoEletrico() {
		return empresaProjetoEletrico;
	}

	@Override
	public void setEmpresaProjetoEletrico(String empresaProjetoEletrico) {
		this.empresaProjetoEletrico = empresaProjetoEletrico;
	}

	@Override
	public String getTelResponsavelEletrico() {
		return telResponsavelEletrico;
	}

	@Override
	public void setTelResponsavelEletrico(String telResponsavelEletrico) {
		this.telResponsavelEletrico = telResponsavelEletrico;
	}

	@Override
	public Boolean getMeioFisico() {
		return meioFisico;
	}

	@Override
	public void setMeioFisico(Boolean meioFisico) {
		this.meioFisico = meioFisico;
	}

	@Override
	public Boolean getMeioSocioEconomico() {
		return meioSocioEconomico;
	}

	@Override
	public void setMeioSocioEconomico(Boolean meioSocioEconomico) {
		this.meioSocioEconomico = meioSocioEconomico;
	}

	@Override
	public Double getPrimariaKm() {
		return primariaKm;
	}

	@Override
	public void setPrimariaKm(Double primariaKm) {
		this.primariaKm = primariaKm;
	}

	@Override
	public Double getSecundariaKm() {
		return secundariaKm;
	}

	@Override
	public void setSecundariaKm(Double secundariaKm) {
		this.secundariaKm = secundariaKm;
	}

	@Override
	public Double getConjugadaKm() {
		return conjugadaKm;
	}

	@Override
	public void setConjugadaKm(Double conjugadaKm) {
		this.conjugadaKm = conjugadaKm;
	}

	@Override
	public Double getExtensaoTotalKm() {
		return extensaoTotalKm;
	}

	@Override
	public void setExtensaoTotalKm(Double extensaoTotalKm) {
		this.extensaoTotalKm = extensaoTotalKm;
	}

	@Override
	public Double getAreaHa() {
		return areaHa;
	}

	@Override
	public void setAreaHa(Double areaHa) {
		this.areaHa = areaHa;
	}

	@Override
	public Long getPostesPrimario() {
		return postesPrimario;
	}

	@Override
	public void setPostesPrimario(Long postesPrimario) {
		this.postesPrimario = postesPrimario;
	}

	@Override
	public Long getPostesSecundario() {
		return postesSecundario;
	}

	@Override
	public void setPostesSecundario(Long postesSecundario) {
		this.postesSecundario = postesSecundario;
	}

	@Override
	public Long getPostesTotal() {
		return postesTotal;
	}

	@Override
	public void setPostesTotal(Long postesTotal) {
		this.postesTotal = postesTotal;
	}

	@Override
	public Long getConsumidores() {
		return consumidores;
	}

	@Override
	public void setConsumidores(Long consumidores) {
		this.consumidores = consumidores;
	}

	@Override
	public Double getTensaoPrimariaKv() {
		return tensaoPrimariaKv;
	}

	@Override
	public void setTensaoPrimariaKv(Double tensaoPrimariaKv) {
		this.tensaoPrimariaKv = tensaoPrimariaKv;
	}

	@Override
	public Double getTensaoSecundariaKV() {
		return tensaoSecundariaKV;
	}
	
	@Override
	public void setTensaoSecundariaKV(Double tensaoSecundariaKV) {
		this.tensaoSecundariaKV = tensaoSecundariaKV;
	}

	@Override
	public String getCabos() {
		return cabos;
	}

	@Override
	public void setCabos(String cabos) {
		this.cabos = cabos;
	}

	@Override
	public String getFaixaServidaoM() {
		return faixaServidaoM;
	}

	@Override
	public void setFaixaServidaoM(String faixaServidaoM) {
		this.faixaServidaoM = faixaServidaoM;
	}

	@Override
	public Double getDistanciaSedeMunPav() {
		return distanciaSedeMunPav;
	}

	@Override
	public void setDistanciaSedeMunPav(Double distanciaSedeMunPav) {
		this.distanciaSedeMunPav = distanciaSedeMunPav;
	}

	@Override
	public Double getDistanciaSedeMunNaoPav() {
		return distanciaSedeMunNaoPav;
	}

	@Override
	public void setDistanciaSedeMunNaoPav(Double distanciaSedeMunNaoPav) {
		this.distanciaSedeMunNaoPav = distanciaSedeMunNaoPav;
	}

	@Override
	public Double getDistanciaMunLocalProjPav() {
		return distanciaMunLocalProjPav;
	}

	@Override
	public void setDistanciaMunLocalProjPav(Double distanciaMunLocalProjPav) {
		this.distanciaMunLocalProjPav = distanciaMunLocalProjPav;
	}

	@Override
	public Double getDistanciaMunLocalProjNaoPav() {
		return distanciaMunLocalProjNaoPav;
	}

	@Override
	public void setDistanciaMunLocalProjNaoPav(Double distanciaMunLocalProjNaoPav) {
		this.distanciaMunLocalProjNaoPav = distanciaMunLocalProjNaoPav;
	}

	@Override
	public Boolean getSitioArquilogico() {
		return sitioArquilogico;
	}

	@Override
	public void setSitioArquilogico(Boolean sitioArquilogico) {
		this.sitioArquilogico = sitioArquilogico;
	}

	@Override
	public String getObsCaracteristicas() {
		return obsCaracteristicas;
	}

	@Override
	public void setObsCaracteristicas(String obsCaracteristicas) {
		this.obsCaracteristicas = obsCaracteristicas;
	}

	@Override
	public Double getValorProjeto() {
		return valorProjeto;
	}

	@Override
	public void setValorProjeto(Double valorProjeto) {
		this.valorProjeto = valorProjeto;
	}

	@Override
	public Double getValorJaCobrado() {
		return valorJaCobrado;
	}

	@Override
	public void setValorJaCobrado(Double valorJaCobrado) {
		this.valorJaCobrado = valorJaCobrado;
	}

	@Override
	public Double getSaldoRestante() {
		return saldoRestante;
	}

	@Override
	public void setSaldoRestante(Double saldoRestante) {
		this.saldoRestante = saldoRestante;
	}

	@Override
	public String getObsVerificacao() {
		return obsVerificacao;
	}

	@Override
	public void setObsVerificacao(String obsVerificacao) {
		this.obsVerificacao = obsVerificacao;
	}

	@Override
	public String getObservacoesPagamento() {
		return observacoesPagamento;
	}

	@Override
	public void setObservacoesPagamento(String observacoesPagamento) {
		this.observacoesPagamento = observacoesPagamento;
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
	@Override
	public IContrato getContrato() {
		return contrato;
	}
	@Override
	public void setContrato(IContrato contrato) {
		this.contrato = contrato;
	}
	/*@Override
	public Boolean getPrimeiraParcela() {
		return primeiraParcela;
	}
	@Override
	public void setPrimeiraParcela(Boolean primeiraParcela) {
		this.primeiraParcela = primeiraParcela;
	}
	@Override
	public Boolean getSegundaParcela() {
		return segundaParcela;
	}
	@Override
	public void setSegundaParcela(Boolean segundaParcela) {
		this.segundaParcela = segundaParcela;
	}
	@Override
	public Boolean getTerceiraParcela() {
		return terceiraParcela;
	}
	@Override
	public void setTerceiraParcela(Boolean terceiraParcela) {
		this.terceiraParcela = terceiraParcela;
	}*/
	@Override
	public Situacao getSituacao() {
		return situacao;
	}
	@Override
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Long getTodos() {
		return todos;
	}

	public void setTodos(Long todos) {
		this.todos = todos;
	}
	@Override
	public String getObsDadosFronteira() {
		return obsDadosFronteira;
	}
	@Override
	public void setObsDadosFronteira(String obsDadosFronteira) {
		this.obsDadosFronteira = obsDadosFronteira;
	}
	@Override
	public Boolean getEp() {
		return ep;
	}
	@Override
	public void setEp(Boolean ep) {
		this.ep = ep;
	}
	@Override
	public Status getStatus() {
		return status;
	}
	@Override
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	@Override
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	@Override
	public ICliente getCliente() {
		return cliente;
	}
	@Override
	public void setCliente(ICliente cliente) {
		this.cliente = cliente;
	}

	public VerificacaoProfissional getVerificacaoProfissional() {
		return verificacaoProfissional;
	}

	public void setVerificacaoProfissional(VerificacaoProfissional verificacaoProfissional) {
		this.verificacaoProfissional = verificacaoProfissional;
	}


	@Override
	public IPlanejamentos getPlanejamentos() {
		return planejamentos;
	}

	@Override
	public void setPlanejamentos(IPlanejamentos planejamentos) {
		this.planejamentos = planejamentos;
	}
	@Override
	public IProfissional getProfEpi() {
		return profEpi;
	}
	@Override
	public void setProfEpi(IProfissional profEpi) {
		this.profEpi = profEpi;
	}
	@Override
	public IProfissional getProfEvctgal() {
		return profEvctgal;
	}
	@Override
	public void setProfEvctgal(IProfissional profEvctgal) {
		this.profEvctgal = profEvctgal;
	}
	@Override
	public IProfissional getProfMapas() {
		return profMapas;
	}
	@Override
	public void setProfMapas(IProfissional profMapas) {
		this.profMapas = profMapas;
	}
	@Override
	public IProfissional getProfVerif() {
		return profVerif;
	}
	@Override
	public void setProfVerif(IProfissional profVerif) {
		this.profVerif = profVerif;
	}
	@Override
	public Date getDtIniPlanj() {
		return dtIniPlanj;
	}
	@Override
	public void setDtIniPlanj(Date dtIniPlanj) {
		this.dtIniPlanj = dtIniPlanj;
	}
	@Override
	public Date getDtFimPlanj() {
		return dtFimPlanj;
	}
	@Override
	public void setDtFimPlanj(Date dtFimPlanj) {
		this.dtFimPlanj = dtFimPlanj;
	}
	@Override
	public Date getDtEntrega() {
		return dtEntrega;
	}
	@Override
	public void setDtEntrega(Date dtEntrega) {
		this.dtEntrega = dtEntrega;
	}
	@Override
	public String getObsPlanejamento() {
		return obsPlanejamento;
	}
	@Override
	public void setObsPlanejamento(String obsPlanejamento) {
		this.obsPlanejamento = obsPlanejamento;
	}
	@Override
	public Boolean getJaPlanejado() {
		return jaPlanejado;
	}
	@Override
	public void setJaPlanejado(Boolean jaPlanejado) {
		this.jaPlanejado = jaPlanejado;
	}

	public Date getFiltroDataEntradaoInicial() {
		return FiltroDataEntradaoInicial;
	}

	public void setFiltroDataEntradaoInicial(Date filtroDataEntradaoInicial) {
		FiltroDataEntradaoInicial = filtroDataEntradaoInicial;
	}

	public Date getFiltroDataEntradaoFinal() {
		return FiltroDataEntradaoFinal;
	}

	public void setFiltroDataEntradaoFinal(Date filtroDataEntradaoFinal) {
		FiltroDataEntradaoFinal = filtroDataEntradaoFinal;
	}
	@Override
	public String getObsEmpresaExecutora() {
		return obsEmpresaExecutora;
	}
	
	@Override
	public void setObsEmpresaExecutora(String obsEmpresaExecutora) {
		this.obsEmpresaExecutora = obsEmpresaExecutora;
	}
	@Override
	public Boolean getVerificaNotificacao() {
		return verificaNotificacao;
	}

	@Override
	public void setVerificaNotificacao(Boolean verificaNotificacao) {
		this.verificaNotificacao = verificaNotificacao;
	}

	public String getFiltroIDS() {
		return filtroIDS;
	}

	public void setFiltroIDS(String filtroIDS) {
		this.filtroIDS = filtroIDS;
	}
	@Override
	public Date getDataIncialCampo() {
		return dataIncialCampo;
	}
	@Override
	public void setDataIncialCampo(Date dataIncialCampo) {
		this.dataIncialCampo = dataIncialCampo;
	}
	@Override
	public Date getDataFinalCampo() {
		return dataFinalCampo;
	}
	@Override
	public void setDataFinalCampo(Date dataFinalCampo) {
		this.dataFinalCampo = dataFinalCampo;
	}
	@Override
	public TipoPrograma getTipoPrograma() {
		return tipoPrograma;
	}
	@Override
	public void setTipoPrograma(TipoPrograma tipoPrograma) {
		this.tipoPrograma = tipoPrograma;
	}
	@Override
	public Long getAnoReferencia() {
		return anoReferencia;
	}
	@Override
	public void setAnoReferencia(Long anoReferencia) {
		this.anoReferencia = anoReferencia;
	}
	@Override
	public Long getSituacaoPlanejamento() {
		return situacaoPlanejamento;
	}
	@Override
	public void setSituacaoPlanejamento(Long situacaoPlanejamento) {
		this.situacaoPlanejamento = situacaoPlanejamento;
	}
	@Override
	public Boolean getCheckMapa() {
		return checkMapa;
	}
	@Override
	public void setCheckMapa(Boolean checkMapa) {
		this.checkMapa = checkMapa;
	}
	@Override
	public Boolean getCheckEPI() {
		return checkEPI;
	}
	@Override
	public void setCheckEPI(Boolean checkEPI) {
		this.checkEPI = checkEPI;
	}
	@Override
	public Boolean getCheckEvCtga() {
		return checkEvCtga;
	}
	@Override
	public void setCheckEvCtga(Boolean checkEvCtga) {
		this.checkEvCtga = checkEvCtga;
	}
	@Override
	public Boolean getCheckVerificacap() {
		return checkVerificacap;
	}
	@Override
	public void setCheckVerificacap(Boolean checkVerificacap) {
		this.checkVerificacap = checkVerificacap;
	}
	@Override
	public Long getModalidade() {
		return modalidade;
	}
	@Override
	public void setModalidade(Long modalidade) {
		this.modalidade = modalidade;
	}
	
	
	
}
