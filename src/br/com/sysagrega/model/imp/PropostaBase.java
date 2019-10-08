package br.com.sysagrega.model.imp;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.IPropostaBase;

@MappedSuperclass
public abstract class PropostaBase implements IPropostaBase {
	
 
	private static final long serialVersionUID = 1L;
	
	private String tipoProposta;
	
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	
	private String nomeProjeto;
	
	@OneToOne(targetEntity = Cliente.class, cascade = CascadeType.REFRESH)
	private ICliente cliente;
	
	protected Double valorTotalDaProposta;
	
	protected String numeroProposta;
	
	protected Date dataAprovacao;
	
	protected String numeroRevisao;
	
	protected String respProjeto;
	
	@Column(length=1000)
	protected String dptoEnvioFinanceiro;
	
	@Column(length=1000)
	protected String dptoEnvioTecnico;
	
	protected String destinatarioEnvioTecnico;
	
	protected String destinatarioEnvioFinanceiro;
	
	protected String dadosDestinatarioEnvioTecnico;
	
	protected String dadosDestinatarioEnvioFinanceiro;
	
	@Column(length=3500)
	protected String descricaoFinanceira;
	
	@Column(length=3500)
	protected String descricaoTecnica;
	
	protected String nomeProposta;
	
	private double valorTotalImpostos;

	private double valorTotalPrecificacao;
	
	private double valorTotalCustoPlanilhaFinanceira;

	private double valorTotalCustosExecucao;

	private double valorTotalCustosDesclocamento;

	private double valorTotalCustosOperacionais;

	private double valorTotalCustosAdministrativos;

	private double valorTotalCustosBdiComissoes;

	private double valorTotalCustosSeguranca;

	private double valorTotalComBdiComissao;

	private Double valorTotalSemBdiComissao;
	
	private Double percentualBDI;
	
	private Double percentualComissao;
	
	private Double desconto;
	
	private Long revisaoPrecificacao;
	
	private String statusProposta;
	
	private String textPropostaGenerica;
	
	private String consideracoesFinais;
	
	@Temporal(TemporalType.DATE)
	private Date dataAtualizacao;
	
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.REFRESH)
	private Usuario usuarioAtualizacao;
	
	private boolean ativo;
	
	private String unidade;
	
	private static final double ISS = 5;
	private static final double COFINS = 3;
	private static final double PIS = 0.65;
	private static final double CSLL = 2.88;
	private static final double IR = 4.8;
	private static final double IMPOSTOS = 0.1633;
	
	private String objPrincTec;
	
	private String validadeTec;
	
	private String objPrincFinc;
	
	private String financeiroCusto;
	
	private String formaPagtoFinanc;
	
	private String validadeFinc;
	
	private String assinaturaTec;
	
	private String funcaoCargoTec;
	
	private String assinaturaFinanc;
	
	private String funcaoCargoFinanc;
	
	private String assinaturaTecFinan;
	
	private String funcaoCargoTecFinan;
	
	private Long idServicoNegocio;
	
	private Long idArea;
	
	private Long idNegocio;
	
	private String coletaPreco;
	
	private String dtLimiteApreset;
	
	private String objDadosEmpresa;
	
	private String destEnvioTecFinan;
	
	private String dadosDestEnvioTecFinan;
	
	private String dptoEnvioTecFinan;
	
	private String descricaoTecFinan;
	
	private String objPrincTecFinac;
	
	private String validadeTecFinac;
	
	
	private String titiloPlanilhaFinc;
	
	private String descricaoPlanilhaFinac;
	
	private String tituloPlanTecnica;
	
	private String descricaPlanTecnica;
	
	private String tituloPlanEquipamento;
	
	private String descricaPlanEquipamento;
	
	private String tituloPlanComer;
	
	private String descricaPlanComer;
	
	private Boolean inserirPlanFinan;
	
	private Boolean inserirPlanTec;
	
	private Boolean inserirPlanTecEquipamento;
	
	private Boolean inserirPlanCom;
	
	private Double vlrNegTotalPlanFinanceira;
	
	private Double descPlanFin;
	
	private Integer ordProposta;
	
	@Temporal(TemporalType.DATE)
	private Date dtEmissPropTec;
	
	@Temporal(TemporalType.DATE)
	private Date dtEmissPropFinc;
	
	@Temporal(TemporalType.DATE)
	private Date dtEmissPropTecFinac;
	
	@Temporal(TemporalType.DATE)
	private Date dtEmissaoGeral;
	
	
	private String motivoExclusao;

	
	
	


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((nomeProjeto == null) ? 0 : nomeProjeto.hashCode());
		result = prime * result + ((numeroProposta == null) ? 0 : numeroProposta.hashCode());
		result = prime * result + ((tipoProposta == null) ? 0 : tipoProposta.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorTotalComBdiComissao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalCustosAdministrativos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalCustosBdiComissoes);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalCustosDesclocamento);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalCustosExecucao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalCustosOperacionais);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalCustosSeguranca);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((valorTotalDaProposta == null) ? 0 : valorTotalDaProposta.hashCode());
		temp = Double.doubleToLongBits(valorTotalImpostos);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorTotalPrecificacao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((valorTotalSemBdiComissao == null) ? 0 : valorTotalSemBdiComissao.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PropostaBase))
			return false;
		PropostaBase other = (PropostaBase) obj;
		 if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (nomeProjeto == null) {
			if (other.nomeProjeto != null)
				return false;
		} else if (!nomeProjeto.equals(other.nomeProjeto))
			return false;
		if (numeroProposta == null) {
			if (other.numeroProposta != null)
				return false;
		 } 
		if (Double.doubleToLongBits(valorTotalComBdiComissao) != Double
				.doubleToLongBits(other.valorTotalComBdiComissao))
			return false;
		if (Double.doubleToLongBits(valorTotalCustosAdministrativos) != Double
				.doubleToLongBits(other.valorTotalCustosAdministrativos))
			return false;
		if (Double.doubleToLongBits(valorTotalCustosBdiComissoes) != Double
				.doubleToLongBits(other.valorTotalCustosBdiComissoes))
			return false;
		if (Double.doubleToLongBits(valorTotalCustosDesclocamento) != Double
				.doubleToLongBits(other.valorTotalCustosDesclocamento))
			return false;
		if (Double.doubleToLongBits(valorTotalCustosExecucao) != Double
				.doubleToLongBits(other.valorTotalCustosExecucao))
			return false;
		if (Double.doubleToLongBits(valorTotalCustosOperacionais) != Double
				.doubleToLongBits(other.valorTotalCustosOperacionais))
			return false;
		if (Double.doubleToLongBits(valorTotalCustosSeguranca) != Double
				.doubleToLongBits(other.valorTotalCustosSeguranca))
			return false;
		if (valorTotalDaProposta == null) {
			if (other.valorTotalDaProposta != null)
				return false;
		} else if (!valorTotalDaProposta.equals(other.valorTotalDaProposta))
			return false;
		if (valorTotalSemBdiComissao == null) {
			if (other.valorTotalSemBdiComissao != null)
				return false;
		} else if (!valorTotalSemBdiComissao.equals(other.valorTotalSemBdiComissao))
			return false;
		if (Double.doubleToLongBits(valorTotalImpostos) != Double.doubleToLongBits(other.valorTotalImpostos))
			return false;
		if (Double.doubleToLongBits(valorTotalPrecificacao) != Double.doubleToLongBits(other.valorTotalPrecificacao))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#getTipoProposta()
	 */
	@Override
	public String getTipoProposta() {
		return tipoProposta;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#setTipoProposta(java.lang.String)
	 */
	@Override
	public void setTipoProposta(String tipoProposta) {
		this.tipoProposta = tipoProposta;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#getDataInclusao()
	 */
	@Override
	public Date getDataInclusao() {
		return dataInclusao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#setDataInclusao(java.util.Date)
	 */
	@Override
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#getCliente()
	 */
	@Override
	public ICliente getCliente() {
		return cliente;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#setCliente(java.lang.String)
	 */
	@Override
	public void setCliente(ICliente cliente) {
		this.cliente = cliente;
	}
 
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#getNumeroProposta()
	 */
	@Override
	public String getNumeroProposta() {
		return numeroProposta;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaBase#setNumeroProposta(java.lang.String)
	 */
	@Override
	public void setNumeroProposta(String numeroProposta) {
		this.numeroProposta = numeroProposta;
	}
	/**
	 * @return the nomeProjeto
	 */
	@Override
	public String getNomeProjeto() {
		return nomeProjeto;
	}

	/**
	 * @param nomeProjeto the nomeProjeto to set
	 */
	@Override
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}


	/**
	 * @return the valorTotalImpostos
	 */
	@Override
	public double getValorTotalImpostos() {
		return valorTotalImpostos;
	}

	/**
	 * @param valorTotalImpostos the valorTotalImpostos to set
	 */
	@Override
	public void setValorTotalImpostos(double valorTotalImpostos) {
		this.valorTotalImpostos = valorTotalImpostos;
	}

	/**
	 * @return the valorTotalPrecificacao
	 */
	@Override
	public double getValorTotalPrecificacao() {
		return valorTotalPrecificacao;
	}

	/**
	 * @param valorTotalPrecificacao the valorTotalPrecificacao to set
	 */
	@Override
	public void setValorTotalPrecificacao(double valorTotalPrecificacao) {
		this.valorTotalPrecificacao = valorTotalPrecificacao;
	}

	/**
	 * @return the valorTotalCustosExecucao
	 */
	@Override
	public double getValorTotalCustosExecucao() {
		return valorTotalCustosExecucao;
	}

	/**
	 * @param valorTotalCustosExecucao the valorTotalCustosExecucao to set
	 */
	@Override
	public void setValorTotalCustosExecucao(double valorTotalCustosExecucao) {
		this.valorTotalCustosExecucao = valorTotalCustosExecucao;
	}

	/**
	 * @return the valorTotalCustosDesclocamento
	 */
	@Override
	public double getValorTotalCustosDesclocamento() {
		return valorTotalCustosDesclocamento;
	}

	/**
	 * @param valorTotalCustosDesclocamento the valorTotalCustosDesclocamento to set
	 */
	@Override
	public void setValorTotalCustosDesclocamento(double valorTotalCustosDesclocamento) {
		this.valorTotalCustosDesclocamento = valorTotalCustosDesclocamento;
	}

	/**
	 * @return the valorTotalCustosOperacionais
	 */
	@Override
	public double getValorTotalCustosOperacionais() {
		return valorTotalCustosOperacionais;
	}

	/**
	 * @param valorTotalCustosOperacionais the valorTotalCustosOperacionais to set
	 */
	@Override
	public void setValorTotalCustosOperacionais(double valorTotalCustosOperacionais) {
		this.valorTotalCustosOperacionais = valorTotalCustosOperacionais;
	}

	/**
	 * @return the valorTotalCustosAdministrativos
	 */
	@Override
	public double getValorTotalCustosAdministrativos() {
		return valorTotalCustosAdministrativos;
	}

	/**
	 * @param valorTotalCustosAdministrativos the valorTotalCustosAdministrativos to set
	 */
	@Override
	public void setValorTotalCustosAdministrativos(double valorTotalCustosAdministrativos) {
		this.valorTotalCustosAdministrativos = valorTotalCustosAdministrativos;
	}

	/**
	 * @return the valorTotalCustosBdiComissoes
	 */
	@Override
	public double getValorTotalCustosBdiComissoes() {
		return valorTotalCustosBdiComissoes;
	}

	/**
	 * @param valorTotalCustosBdiComissoes the valorTotalCustosBdiComissoes to set
	 */
	@Override
	public void setValorTotalCustosBdiComissoes(double valorTotalCustosBdiComissoes) {
		this.valorTotalCustosBdiComissoes = valorTotalCustosBdiComissoes;
	}

	/**
	 * @return the valorTotalCustosSeguranca
	 */
	@Override
	public double getValorTotalCustosSeguranca() {
		return valorTotalCustosSeguranca;
	}

	/**
	 * @param valorTotalCustosSeguranca the valorTotalCustosSeguranca to set
	 */
	@Override
	public void setValorTotalCustosSeguranca(double valorTotalCustosSeguranca) {
		this.valorTotalCustosSeguranca = valorTotalCustosSeguranca;
	}

	/**
	 * @return the valorTotalComBdiComissao
	 */
	@Override
	public double getValorTotalComBdiComissao() {
		return valorTotalComBdiComissao;
	}

	/**
	 * @param valorTotalComBdiComissao the valorTotalComBdiComissao to set
	 */
	@Override
	public void setValorTotalComBdiComissao(double valorTotalComBdiComissao) {
		this.valorTotalComBdiComissao = valorTotalComBdiComissao;
	}

	/**
	 * @return the valorTotalSemBdiComissao
	 */
	@Override
	public Double getValorTotalSemBdiComissao() {
		return valorTotalSemBdiComissao;
	}

	/**
	 * @param valorTotalSemBdiComissao the valorTotalSemBdiComissao to set
	 */
	@Override
	public void setValorTotalSemBdiComissao(Double valorTotalSemBdiComissao) {
		this.valorTotalSemBdiComissao = valorTotalSemBdiComissao;
	}

	/**
	 * @return the iss
	 */
	@Override
	public double getIss() {
		return ISS;
	}

	/**
	 * @return the cofins
	 */
	@Override
	public double getCofins() {
		return COFINS;
	}

	/**
	 * @return the pis
	 */
	@Override
	public double getPis() {
		return PIS;
	}

	/**
	 * @return the csll
	 */
	@Override
	public double getCsll() {
		return CSLL;
	}

	/**
	 * @return the ir
	 */
	@Override
	public double getIr() {
		return IR;
	}

	/**
	 * @return the impostos
	 */
	@Override
	public double getImpostos() {
		return IMPOSTOS;
	}

	/**
	 * @return the valorTotalDaProposta
	 */
	@Override
	public Double getValorTotalDaProposta() {
		return valorTotalDaProposta;
	}

	/**
	 * @param valorTotalDaProposta the valorTotalDaProposta to set
	 */
	@Override
	public void setValorTotalDaProposta(Double valorTotalDaProposta) {
		this.valorTotalDaProposta = valorTotalDaProposta;
	}

	public double getValorTotalCustoPlanilhaFinanceira() {
		return valorTotalCustoPlanilhaFinanceira;
	}

	public void setValorTotalCustoPlanilhaFinanceira(
			double valorTotalCustoPlanilhaFinanceira) {
		this.valorTotalCustoPlanilhaFinanceira = valorTotalCustoPlanilhaFinanceira;
	}

	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	public String getRespProjeto() {
		return respProjeto;
	}

	public String getDescricaoFinanceira() {
		return descricaoFinanceira;
	}

	public String getDescricaoTecnica() {
		return descricaoTecnica;
	}

	public String getNomeProposta() {
		return nomeProposta;
	}

	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	public void setRespProjeto(String respProjeto) {
		this.respProjeto = respProjeto;
	}


	public void setDescricaoFinanceira(String descricaoFinanceira) {
		this.descricaoFinanceira = descricaoFinanceira;
	}

	public void setDescricaoTecnica(String descricaoTecnica) {
		this.descricaoTecnica = descricaoTecnica;
	}

	public void setNomeProposta(String nomeProposta) {
		this.nomeProposta = nomeProposta;
	}

	public String getNumeroRevisao() {
		return numeroRevisao;
	}

	public void setNumeroRevisao(String numeroRevisao) {
		this.numeroRevisao = numeroRevisao;
	}
	@Override
	public Double getPercentualBDI() {
		return percentualBDI;
	}
	@Override
	public void setPercentualBDI(Double percentualBDI) {
		this.percentualBDI = percentualBDI;
	}
	@Override
	public Double getPercentualComissao() {
		return percentualComissao;
	}
	@Override
	public void setPercentualComissao(Double percentualComissao) {
		this.percentualComissao = percentualComissao;
	}
	@Override
	public String getDptoEnvioFinanceiro() {
		return dptoEnvioFinanceiro;
	}
	@Override
	public String getDptoEnvioTecnico() {
		return dptoEnvioTecnico;
	}
	@Override
	public String getDestinatarioEnvioTecnico() {
		return destinatarioEnvioTecnico;
	}
	@Override
	public String getDestinatarioEnvioFinanceiro() {
		return destinatarioEnvioFinanceiro;
	}
	@Override
	public String getDadosDestinatarioEnvioFinanceiro() {
		return dadosDestinatarioEnvioFinanceiro;
	}
	@Override
	public void setDptoEnvioFinanceiro(String dptoEnvioFinanceiro) {
		this.dptoEnvioFinanceiro = dptoEnvioFinanceiro;
	}
	@Override
	public void setDptoEnvioTecnico(String dptoEnvioTecnico) {
		this.dptoEnvioTecnico = dptoEnvioTecnico;
	}
	@Override
	public void setDestinatarioEnvioTecnico(String destinatarioEnvioTecnico) {
		this.destinatarioEnvioTecnico = destinatarioEnvioTecnico;
	}
	@Override
	public void setDestinatarioEnvioFinanceiro(String destinatarioEnvioFinanceiro) {
		this.destinatarioEnvioFinanceiro = destinatarioEnvioFinanceiro;
	}


	@Override
	public void setDadosDestinatarioEnvioFinanceiro(
			String dadosDestinatarioEnvioFinanceiro) {
		this.dadosDestinatarioEnvioFinanceiro = dadosDestinatarioEnvioFinanceiro;
	}

	@Override
	public String getDadosDestinatarioEnvioTecnico() {
		return dadosDestinatarioEnvioTecnico;
	}

	@Override
	public void setDadosDestinatarioEnvioTecnico(
			String dadosDestinatarioEnvioTecnico) {
		this.dadosDestinatarioEnvioTecnico = dadosDestinatarioEnvioTecnico;
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
	public Usuario getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}
	@Override
	public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}
	@Override
	public boolean isAtivo() {
		return ativo;
	}
	@Override
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	@Override
	public String getUnidade() {
		return unidade;
	}
	@Override
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	@Override
	public Double getDesconto() {
		return desconto;
	}

	@Override
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	@Override
	public Long getRevisaoPrecificacao() {
		return revisaoPrecificacao;
	}

	@Override
	public void setRevisaoPrecificacao(Long revisaoPrecificacao) {
		this.revisaoPrecificacao = revisaoPrecificacao;
	}

	@Override
	public String getStatusProposta() {
		return statusProposta;
	}

	@Override
	public void setStatusProposta(String statusProposta) {
		this.statusProposta = statusProposta;
	}
	
	@Override
	public String getConsideracoesFinais() {
		return consideracoesFinais;
	}

	@Override
	public void setConsideracoesFinais(String consideracoesFinais) {
		this.consideracoesFinais = consideracoesFinais;
	}

	@Override
	public String getTextPropostaGenerica() {
		return textPropostaGenerica;
	}

	@Override
	public void setTextPropostaGenerica(String textPropostaGenerica) {
		this.textPropostaGenerica = textPropostaGenerica;
	}

	@Override
	public String getObjPrincTec() {
		return objPrincTec;
	}
	@Override
	public void setObjPrincTec(String objPrincTec) {
		this.objPrincTec = objPrincTec;
	}
	
	@Override
	public String getValidadeTec() {
		return validadeTec;
	}

	@Override
	public void setValidadeTec(String validadeTec) {
		this.validadeTec = validadeTec;
	}

	@Override
	public String getObjPrincFinc() {
		return objPrincFinc;
	}

	@Override
	public void setObjPrincFinc(String objPrincFinc) {
		this.objPrincFinc = objPrincFinc;
	}

	@Override
	public String getFinanceiroCusto() {
		return financeiroCusto;
	}

	@Override
	public void setFinanceiroCusto(String financeiroCusto) {
		this.financeiroCusto = financeiroCusto;
	}

	@Override
	public String getFormaPagtoFinanc() {
		return formaPagtoFinanc;
	}

	@Override
	public void setFormaPagtoFinanc(String formaPagtoFinanc) {
		this.formaPagtoFinanc = formaPagtoFinanc;
	}

	@Override
	public String getValidadeFinc() {
		return validadeFinc;
	}

	@Override
	public void setValidadeFinc(String validadeFinc) {
		this.validadeFinc = validadeFinc;
	}

	@Override
	public String getAssinaturaTec() {
		return assinaturaTec;
	}

	@Override
	public void setAssinaturaTec(String assinaturaTec) {
		this.assinaturaTec = assinaturaTec;
	}

	@Override
	public String getFuncaoCargoTec() {
		return funcaoCargoTec;
	}

	@Override
	public void setFuncaoCargoTec(String funcaoCargoTec) {
		this.funcaoCargoTec = funcaoCargoTec;
	}

	@Override
	public String getAssinaturaFinanc() {
		return assinaturaFinanc;
	}

	@Override
	public void setAssinaturaFinanc(String assinaturaFinanc) {
		this.assinaturaFinanc = assinaturaFinanc;
	}

	@Override
	public String getFuncaoCargoFinanc() {
		return funcaoCargoFinanc;
	}

	@Override
	public void setFuncaoCargoFinanc(String funcaoCargoFinanc) {
		this.funcaoCargoFinanc = funcaoCargoFinanc;
	}

	@Override
	public Long getIdServicoNegocio() {
		return idServicoNegocio;
	}

	@Override
	public void setIdServicoNegocio(Long idServicoNegocio) {
		this.idServicoNegocio = idServicoNegocio;
	}

	@Override
	public Long getIdArea() {
		return idArea;
	}

	@Override
	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	@Override
	public Long getIdNegocio() {
		return idNegocio;
	}

	@Override
	public void setIdNegocio(Long idNegocio) {
		this.idNegocio = idNegocio;
	}

	@Override
	public String getColetaPreco() {
		return coletaPreco;
	}

	@Override
	public void setColetaPreco(String coletaPreco) {
		this.coletaPreco = coletaPreco;
	}

	@Override
	public String getDtLimiteApreset() {
		return dtLimiteApreset;
	}

	@Override
	public void setDtLimiteApreset(String dtLimiteApreset) {
		this.dtLimiteApreset = dtLimiteApreset;
	}

	@Override
	public String getObjDadosEmpresa() {
		return objDadosEmpresa;
	}

	@Override
	public void setObjDadosEmpresa(String objDadosEmpresa) {
		this.objDadosEmpresa = objDadosEmpresa;
	}

	@Override
	public String getDestEnvioTecFinan() {
		return destEnvioTecFinan;
	}

	@Override
	public void setDestEnvioTecFinan(String destEnvioTecFinan) {
		this.destEnvioTecFinan = destEnvioTecFinan;
	}

	@Override
	public String getDadosDestEnvioTecFinan() {
		return dadosDestEnvioTecFinan;
	}

	@Override
	public void setDadosDestEnvioTecFinan(String dadosDestEnvioTecFinan) {
		this.dadosDestEnvioTecFinan = dadosDestEnvioTecFinan;
	}

	@Override
	public String getDptoEnvioTecFinan() {
		return dptoEnvioTecFinan;
	}

	@Override
	public void setDptoEnvioTecFinan(String dptoEnvioTecFinan) {
		this.dptoEnvioTecFinan = dptoEnvioTecFinan;
	}

	@Override
	public String getDescricaoTecFinan() {
		return descricaoTecFinan;
	}

	@Override
	public void setDescricaoTecFinan(String descricaoTecFinan) {
		this.descricaoTecFinan = descricaoTecFinan;
	}

	@Override
	public String getObjPrincTecFinac() {
		return objPrincTecFinac;
	}

	@Override
	public void setObjPrincTecFinac(String objPrincTecFinac) {
		this.objPrincTecFinac = objPrincTecFinac;
	}

	@Override
	public String getAssinaturaTecFinan() {
		return assinaturaTecFinan;
	}

	@Override
	public void setAssinaturaTecFinan(String assinaturaTecFinan) {
		this.assinaturaTecFinan = assinaturaTecFinan;
	}

	@Override
	public String getFuncaoCargoTecFinan() {
		return funcaoCargoTecFinan;
	}

	@Override
	public void setFuncaoCargoTecFinan(String funcaoCargoTecFinan) {
		this.funcaoCargoTecFinan = funcaoCargoTecFinan;
	}

	@Override
	public String getValidadeTecFinac() {
		return validadeTecFinac;
	}

	@Override
	public void setValidadeTecFinac(String validadeTecFinac) {
		this.validadeTecFinac = validadeTecFinac;
	}

	@Override
	public String getTitiloPlanilhaFinc() {
		return titiloPlanilhaFinc;
	}

	@Override
	public void setTitiloPlanilhaFinc(String titiloPlanilhaFinc) {
		this.titiloPlanilhaFinc = titiloPlanilhaFinc;
	}

	@Override
	public String getDescricaoPlanilhaFinac() {
		return descricaoPlanilhaFinac;
	}

	@Override
	public void setDescricaoPlanilhaFinac(String descricaoPlanilhaFinac) {
		this.descricaoPlanilhaFinac = descricaoPlanilhaFinac;
	}

	@Override
	public String getTituloPlanTecnica() {
		return tituloPlanTecnica;
	}

	@Override
	public void setTituloPlanTecnica(String tituloPlanTecnica) {
		this.tituloPlanTecnica = tituloPlanTecnica;
	}

	@Override
	public String getDescricaPlanTecnica() {
		return descricaPlanTecnica;
	}

	@Override
	public void setDescricaPlanTecnica(String descricaPlanTecnica) {
		this.descricaPlanTecnica = descricaPlanTecnica;
	}

	@Override
	public String getTituloPlanComer() {
		return tituloPlanComer;
	}

	@Override
	public void setTituloPlanComer(String tituloPlanComer) {
		this.tituloPlanComer = tituloPlanComer;
	}

	@Override
	public String getDescricaPlanComer() {
		return descricaPlanComer;
	}

	@Override
	public void setDescricaPlanComer(String descricaPlanComer) {
		this.descricaPlanComer = descricaPlanComer;
	}

	@Override
	public Boolean getInserirPlanFinan() {
		return inserirPlanFinan;
	}

	@Override
	public void setInserirPlanFinan(Boolean inserirPlanFinan) {
		this.inserirPlanFinan = inserirPlanFinan;
	}

	@Override
	public Boolean getInserirPlanTec() {
		return inserirPlanTec;
	}

	@Override
	public void setInserirPlanTec(Boolean inserirPlanTec) {
		this.inserirPlanTec = inserirPlanTec;
	}

	@Override
	public Boolean getInserirPlanCom() {
		return inserirPlanCom;
	}

	@Override
	public void setInserirPlanCom(Boolean inserirPlanCom) {
		this.inserirPlanCom = inserirPlanCom;
	}

	@Override
	public Double getVlrNegTotalPlanFinanceira() {
		return vlrNegTotalPlanFinanceira;
	}

	@Override
	public void setVlrNegTotalPlanFinanceira(Double vlrNegTotalPlanFinanceira) {
		this.vlrNegTotalPlanFinanceira = vlrNegTotalPlanFinanceira;
	}

	@Override
	public Double getDescPlanFin() {
		return descPlanFin;
	}

	@Override
	public void setDescPlanFin(Double descPlanFin) {
		this.descPlanFin = descPlanFin;
	}

	@Override
	public Boolean getInserirPlanTecEquipamento() {
		return inserirPlanTecEquipamento;
	}

	@Override
	public void setInserirPlanTecEquipamento(Boolean inserirPlanTecEquipamento) {
		this.inserirPlanTecEquipamento = inserirPlanTecEquipamento;
	}

	public String getTituloPlanEquipamento() {
		return tituloPlanEquipamento;
	}

	public void setTituloPlanEquipamento(String tituloPlanEquipamento) {
		this.tituloPlanEquipamento = tituloPlanEquipamento;
	}

	
	public String getDescricaPlanEquipamento() {
		return descricaPlanEquipamento;
	}

	public void setDescricaPlanEquipamento(String descricaPlanEquipamento) {
		this.descricaPlanEquipamento = descricaPlanEquipamento;
	}
	@Override
	public Integer getOrdProposta() {
		return ordProposta;
	}
	@Override
	public void setOrdProposta(Integer ordProposta) {
		this.ordProposta = ordProposta;
	}
	

	@Override
	public Date getDtEmissPropTec() {
		return dtEmissPropTec;
	}

	@Override
	public void setDtEmissPropTec(Date dtEmissPropTec) {
		this.dtEmissPropTec = dtEmissPropTec;
	}

	@Override
	public Date getDtEmissPropFinc() {
		return dtEmissPropFinc;
	}

	@Override
	public void setDtEmissPropFinc(Date dtEmissPropFinc) {
		this.dtEmissPropFinc = dtEmissPropFinc;
	}

	@Override
	public Date getDtEmissPropTecFinac() {
		return dtEmissPropTecFinac;
	}

	@Override
	public void setDtEmissPropTecFinac(Date dtEmissPropTecFinac) {
		this.dtEmissPropTecFinac = dtEmissPropTecFinac;
	}
	@Override
	public Date getDtEmissaoGeral() {
		return dtEmissaoGeral;
	}
	@Override
	public void setDtEmissaoGeral(Date dtEmissaoGeral) {
		this.dtEmissaoGeral = dtEmissaoGeral;
	}

	@Override
	@Column(length=305)
	public String getMotivoExclusao() {
		return motivoExclusao;
	}

	@Override
	public void setMotivoExclusao(String motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}

	
}
