package br.com.sysagrega.util.jsf;

import java.util.Date;

import br.com.sysagrega.model.imp.Proposta;

public class TipoItemValor {
	
	private Long tipoDespesa;
	private String descricao;
	private Long quantidade;
	private Double totalDespesa;
	private Double valorUnitario;
	private String observacao;
	private Proposta proposta;
	private String tpUnidadeMedida;
	private String consideracoesFinais;
	
	// faturamento
	private String numProjeto;
	private Date dataFaturamento;
	private Double valorParcelaUM;
	private Double valorParcelaDOIS;
	private Double valorParcelaTRES;
	private String situacao;
	private Long codigoSituacao;
	private Double valorTotal;
	
	
	public Long getTipoDespesa() {
		return tipoDespesa;
	}
	public void setTipoDespesa(Long tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Double getTotalDespesa() {
		return totalDespesa;
	}
	public void setTotalDespesa(Double totalDespesa) {
		this.totalDespesa = totalDespesa;
	}
	public Proposta getProposta() {
		return proposta;
	}
	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}
	
	public String getTpUnidadeMedida() {
		return tpUnidadeMedida;
	}
	public void setTpUnidadeMedida(String tpUnidadeMedida) {
		this.tpUnidadeMedida = tpUnidadeMedida;
	}
	public String getConsideracoesFinais() {
		return consideracoesFinais;
	}
	public void setConsideracoesFinais(String consideracoesFinais) {
		this.consideracoesFinais = consideracoesFinais;
	}
	public String getNumProjeto() {
		return numProjeto;
	}
	public void setNumProjeto(String numProjeto) {
		this.numProjeto = numProjeto;
	}
	public Date getDataFaturamento() {
		return dataFaturamento;
	}
	public void setDataFaturamento(Date dataFaturamento) {
		this.dataFaturamento = dataFaturamento;
	}
	public Double getValorParcelaUM() {
		return valorParcelaUM;
	}
	public void setValorParcelaUM(Double valorParcelaUM) {
		this.valorParcelaUM = valorParcelaUM;
	}
	public Double getValorParcelaDOIS() {
		return valorParcelaDOIS;
	}
	public void setValorParcelaDOIS(Double valorParcelaDOIS) {
		this.valorParcelaDOIS = valorParcelaDOIS;
	}
	public Double getValorParcelaTRES() {
		return valorParcelaTRES;
	}
	public void setValorParcelaTRES(Double valorParcelaTRES) {
		this.valorParcelaTRES = valorParcelaTRES;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Long getCodigoSituacao() {
		return codigoSituacao;
	}
	public void setCodigoSituacao(Long codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}
	 
	 
	
}
