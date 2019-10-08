package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;


public interface IRetificacao extends Serializable{

	Long getId();

	void setId(Long id);

	Long getNumero();

	void setNumero(Long numero);

	Date getDataEnvio();

	void setDataEnvio(Date dataEnvio);
	
	String getDescricao();

	void setDescricao(String descricao);

	String getResumoResposta();

	void setResumoResposta(String resumoResposta);
	
	String getDescricaoProblema();

	void setDescricaoProblema(String descricaoProblema);

	Date getDataResposta();

	void setDataResposta(Date dataResposta);

	Long getProjeto();

	void setProjeto(Long projeto);

	IStatus getStatus();

	void setStatus(IStatus status);

	IProfissional getResponsavelResposta();

	void setResponsavelResposta(IProfissional responsavelResposta);

	ITipoRetificacao getTipoRetificacao();

	void setTipoRetificacao(ITipoRetificacao tipoRetificacao);





}