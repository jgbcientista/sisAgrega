package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import br.com.sysagrega.model.imp.Parcela;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.model.imp.Usuario;

public interface IContrato extends Serializable {

	boolean isNovo();

	boolean isExistente();

	Long getId();

	void setId(Long id);

	String getNrContrato();

	void setNrContrato(String nrContrato);

	Proposta getProposta();

	void setProposta(Proposta proposta);

	Date getDataAssinatura();

	void setDataAssinatura(Date dataAssinatura);

	Double getVlrContrato();

	void setVlrContrato(Double vlrContrato);

	Date getVigenciaDataInicial();

	void setVigenciaDataInicial(Date vigenciaDataInicial);

	Date getVigenciaDataFinal();

	void setVigenciaDataFinal(Date vigenciaDataFinal);

	String getFormaPagamento();

	void setFormaPagamento(String formaPagamento);

	Integer getQuantidadeParcela();

	void setQuantidadeParcela(Integer quantidadeParcela);

	Date getDataVencimento();

	void setDataVencimento(Date dataVencimento);

	Double getValorParcela();

	void setValorParcela(Double valorParcela);

	String getObservacoesParcelas();

	void setObservacoesParcelas(String observacoesParcelas);

	String getInformacoesClausulas();

	void setInformacoesClausulas(String informacoesClausulas);

	String getConsideracoesFinais();

	void setConsideracoesFinais(String consideracoesFinais);

	Date getDataAtualizacao();

	void setDataAtualizacao(Date dataAtualizacao);

	Usuario getUsuarioAtualizacao();

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);

	Date getDataInclusao();

	void setDataInclusao(Date dataInclusao);

	boolean isAtivo();

	void setAtivo(boolean ativo);

	Set<Parcela> getParcelas();

	void setParcelas(Set<Parcela> parcelas);

	String getStatusContrato();

	void setStatusContrato(String statusContrato);

	Double getSaldoContrato();

	void setSaldoContrato(Double saldoContrato);

	String getNomeCliente();

	void setNomeCliente(String nomeCliente);

	Date getDataDistrato();

	void setDataDistrato(Date dataDistrato);

	Date getDataCancelamento();

	void setDataCancelamento(Date dataCancelamento);

}