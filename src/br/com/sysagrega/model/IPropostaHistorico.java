package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

public interface IPropostaHistorico extends Serializable {

	/**
	 * @return the propostaId
	 */
	IProposta getPropostaId();

	void setPropostaId(IProposta propostaId);

	void setDataRevisao(Date dataRevisao);

	Date getDataRevisao();

	String getNumeroRevisao();

	void setNumeroRevisao(String numeroRevisao);

}