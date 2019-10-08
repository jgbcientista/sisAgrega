package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;


public interface ITrajetoRede extends Serializable{

	Long getId();

	void setId(Long id);

	String getPontoInicio();

	void setPontoInicio(String pontoInicio);

	String getPontoFim();

	void setPontoFim(String pontoFim);

/*	String getDescricao();

	void setDescricao(String descricao);
*/
	void setProjeto(Long projeto);

	Long getProjeto();

	Date getDataCadastro();

	void setDataCadastro(Date dataCadastro);

/*	Long getOrdem();

	void setOrdem(Long ordem);
	*/
	
}