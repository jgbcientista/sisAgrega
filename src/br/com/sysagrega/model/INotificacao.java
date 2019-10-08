package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;


public interface INotificacao extends Serializable{

	Long getId();

	void setId(Long id);

	Long getNumero();

	void setNumero(Long numero);

	String getResponsavelResposta();

	void setResponsavelResposta(String responsavelResposta);

	Date getDataResposta();

	void setDataResposta(Date dataResposta);

	IProjeto getProjeto();

	void setProjeto(IProjeto projeto);

	Date getDataRecebimento();

	void setDataRecebimento(Date dataRecebimento);

	void setMotivo(String motivo);

	String getMotivo();

	IRegistro getRegistro();

	void setRegistro(IRegistro registro);


}