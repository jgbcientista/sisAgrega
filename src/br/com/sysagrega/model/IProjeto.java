package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Situacao;
import br.com.sysagrega.model.imp.Status;
import br.com.sysagrega.model.imp.TipoPrograma;
import br.com.sysagrega.model.imp.Usuario;

public interface IProjeto extends Serializable{

	boolean isNovo();

	boolean isExistente();

	Long getId();

	void setId(Long id);

	Usuario getUsuarioAtualizacao();

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);

	Date getDataInclusao();

	void setDataInclusao(Date dataInclusao);

	IOrdemServico getOrdemServico();

	void setOrdemServico(IOrdemServico ordemServico);

	String getTipoProjeto();

	void setTipoProjeto(String tipoProjeto);

	Boolean getAtivo();

	void setAtivo(Boolean ativo);

	String getNumeroArt();

	void setNumeroArt(String numeroArt);

	String getNumeroProjetoAgrega();

	void setNumeroProjetoAgrega(String numeroProjetoAgrega);

	Date getDataEntrada();

	String getNumeroProjetoCliente();

	void setNumeroProjetoCliente(String numeroProjetoCliente);

	String getNomeProjeto();

	void setNomeProjeto(String nomeProjeto);

	IProfissional getProfissionalGestor();

	void setProfissionalGestor(IProfissional profissionalGestor);

	String getSubTipoProjeto();

	void setSubTipoProjeto(String subTipoProjeto);

	IMunicipio getMunicipio();

	void setMunicipio(IMunicipio municipio);

	Double getTamanhoProjeto();

	void setTamanhoProjeto(Double tamanhoProjeto);

	String getObsDadosTecnico();

	void setObsDadosTecnico(String obsDadosTecnico);

	Boolean getPossuiLPT();

	void setPossuiLPT(Boolean possuiLPT);

	IEstado getEstadoDadosTecnico();

	void setEstadoDadosTecnico(IEstado estadoDadosTecnico);

	Boolean getSupresaoVegetacao();

	void setSupresaoVegetacao(Boolean supresaoVegetacao);

	Boolean getUnidadaConservacao();

	void setUnidadaConservacao(Boolean unidadaConservacao);

	String getObsDadosCampo();

	void setObsDadosCampo(String obsDadosCampo);

	String getEmpresaProjetoEletrico();

	void setEmpresaProjetoEletrico(String empresaProjetoEletrico);

	String getTelResponsavelEletrico();

	void setTelResponsavelEletrico(String telResponsavelEletrico);

	Boolean getMeioFisico();

	void setMeioFisico(Boolean meioFisico);

	Boolean getMeioSocioEconomico();

	void setMeioSocioEconomico(Boolean meioSocioEconomico);

	Double getPrimariaKm();

	void setPrimariaKm(Double primariaKm);

	Double getSecundariaKm();

	void setSecundariaKm(Double secundariaKm);

	Double getConjugadaKm();

	void setConjugadaKm(Double conjugadaKm);

	Double getExtensaoTotalKm();

	void setExtensaoTotalKm(Double extensaoTotalKm);

	Double getAreaHa();

	void setAreaHa(Double areaHa);

	Long getPostesPrimario();

	void setPostesPrimario(Long postesPrimario);

	Long getPostesSecundario();

	void setPostesSecundario(Long postesSecundario);

	Long getPostesTotal();

	void setPostesTotal(Long postesTotal);

	Long getConsumidores();

	void setConsumidores(Long consumidores);

	Double getTensaoPrimariaKv();

	void setTensaoPrimariaKv(Double tensaoPrimariaKv);

	Double getTensaoSecundariaKV();

	String getCabos();

	void setCabos(String cabos);

	String getFaixaServidaoM();

	void setFaixaServidaoM(String faixaServidaoM);

	Double getDistanciaSedeMunPav();

	void setDistanciaSedeMunPav(Double distanciaSedeMunPav);

	Double getDistanciaSedeMunNaoPav();

	void setDistanciaSedeMunNaoPav(Double distanciaSedeMunNaoPav);

	Double getDistanciaMunLocalProjPav();

	void setDistanciaMunLocalProjPav(Double distanciaMunLocalProjPav);

	Double getDistanciaMunLocalProjNaoPav();

	void setDistanciaMunLocalProjNaoPav(Double distanciaMunLocalProjNaoPav);

	Boolean getSitioArquilogico();

	void setSitioArquilogico(Boolean sitioArquilogico);

	String getObsCaracteristicas();

	void setObsCaracteristicas(String obsCaracteristicas);

	Double getValorProjeto();

	void setValorProjeto(Double valorProjeto);

	Double getValorJaCobrado();

	void setValorJaCobrado(Double valorJaCobrado);

	Double getSaldoRestante();

	void setSaldoRestante(Double saldoRestante);

	String getObsVerificacao();

	void setObsVerificacao(String obsVerificacao);

	ICaracteristicaProjeto getCaracteristicasProjeto();

	void setCaracteristicasProjeto(ICaracteristicaProjeto caracteristicasProjeto);

	String getObservacoesPagamento();

	void setObservacoesPagamento(String observacoesPagamento);

	IContrato getContrato();

	void setContrato(IContrato contrato);

	Situacao getSituacao();

	void setSituacao(Situacao situacao);

	void setTensaoSecundariaKV(Double tensaoSecundariaKV);

	String getObsDadosFronteira();

	void setObsDadosFronteira(String obsDadosFronteira);

	Boolean getEp();

	void setEp(Boolean ep);

	void setDataEntrada(Date dataEntrada);

	Status getStatus();

	void setStatus(Status status);

	void setDataAtualizacao(Date dataAtualizacao);

	Date getDataAtualizacao();

	void setCliente(ICliente cliente);

	ICliente getCliente();

	IPlanejamentos getPlanejamentos();

	void setPlanejamentos(IPlanejamentos planejamentos);

	IProfissional getProfEpi();

	void setProfEpi(IProfissional profEpi);

	IProfissional getProfEvctgal();

	void setProfEvctgal(IProfissional profEvctgal);

	IProfissional getProfMapas();

	void setProfMapas(IProfissional profMapas);

	IProfissional getProfVerif();

	void setProfVerif(IProfissional profVerif);

	Date getDtIniPlanj();

	void setDtIniPlanj(Date dtIniPlanj);

	Date getDtFimPlanj();

	void setDtFimPlanj(Date dtFimPlanj);

	Date getDtEntrega();

	void setDtEntrega(Date dtEntrega);

	String getObsPlanejamento();

	void setObsPlanejamento(String obsPlanejamento);

	Boolean getJaPlanejado();

	void setJaPlanejado(Boolean jaPlanejado);

	String getObsEmpresaExecutora();

	void setObsEmpresaExecutora(String obsEmpresaExecutora);

	Boolean getVerificaNotificacao();

	void setVerificaNotificacao(Boolean verificaNotificacao);

	Date getDataIncialCampo();

	void setDataIncialCampo(Date dataIncialCampo);

	Date getDataFinalCampo();

	void setDataFinalCampo(Date dataFinalCampo);

	TipoPrograma getTipoPrograma();

	void setTipoPrograma(TipoPrograma tipoPrograma);

	Long getAnoReferencia();

	void setAnoReferencia(Long anoReferencia);

	Long getSituacaoPlanejamento();

	void setSituacaoPlanejamento(Long situacaoPlanejamento);

	void setCheckVerificacap(Boolean checkVerificacap);

	Boolean getCheckVerificacap();

	void setCheckEvCtga(Boolean checkEvCtga);

	Boolean getCheckEvCtga();

	void setCheckEPI(Boolean checkEPI);

	Boolean getCheckEPI();

	void setCheckMapa(Boolean checkMapa);

	Boolean getCheckMapa();

	Long getModalidade();

	void setModalidade(Long modalidade);;


	
	
}