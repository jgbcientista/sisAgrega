package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

public interface IParcela extends Serializable {

	
	Long getId();
	
	void setId(Long id);

	Date getDataVencimento();

	void setDataVencimento(Date dataVencimento);

	Double getValorParcela();

	void setValorParcela(Double valorParcela);

	Integer getNrParcela();

	void setNrParcela(Integer nrParcela);

	String getObservacoesParcela();

	void setObservacoesParcela(String observacoesParcela);

	
}