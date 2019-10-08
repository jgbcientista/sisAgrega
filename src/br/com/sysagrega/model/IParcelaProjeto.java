package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Faturamento;


public interface IParcelaProjeto extends Serializable{

	Long getId();

	void setId(Long id);

	Long getNumeroParcela();

	void setNumeroParcela(Long numeroParcela);

	Double getValorParcela();

	void setValorParcela(Double valorParcela);

	Date getDataCobranca();

	void setDataCobranca(Date dataCobranca);

/*	NotaFiscal getNotaFiscal();

	void setNotaFiscal(NotaFiscal notaFiscal);*/

	String getObservacao();

	void setObservacao(String observacao);

	IProjeto getProjeto();

	void setProjeto(IProjeto projeto);

	Long getNotaFiscal();

	void setNotaFiscal(Long notaFiscal);


	
	
}