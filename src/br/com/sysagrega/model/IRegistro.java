package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;


public interface IRegistro extends Serializable{

	Long getId();

	void setId(Long id);

	String getNumeroRequerimento();

	void setNumeroRequerimento(String numeroRequerimento);

	String getNumeroProcessoFormado();

	void setNumeroProcessoFormado(String numeroProcessoFormado);

	Date getDataFormacaoProcesso();

	void setDataFormacaoProcesso(Date dataFormacaoProcesso);

	Long getGerouDAE();

	void setGerouDAE(Long gerouDAE);

	Double getValorDAE();

	void setValorDAE(Double valorDAE);

	Date getDataPagamento();

	void setDataPagamento(Date dataPagamento);

	IProjeto getProjeto();

	void setProjeto(IProjeto projeto);

	Date getDataEntrega();

	void setDataEntrega(Date dataEntrega);

	void setTipoRegistro(String tipoRegistro);

	String getTipoRegistro();

	String getMotivoNaoGerouDae();

	void setMotivoNaoGerouDae(String motivoNaoGerouDae);

}