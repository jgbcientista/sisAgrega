package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Usuario;

public interface IServico extends Serializable{

	/**
	 * @return the id
	 */
	Long getId();

	/**
	 * @param id the id to set
	 */
	void setId(Long id);
	
	ITipoServico getTipoServico();
	void setTipoServico(ITipoServico tipoServico);
	
	IArea getArea();
	void setArea(IArea area);
	
	String getDescricao();
	void setDescricao(String descricao);
	
	boolean isNovo();
	
	boolean isExistente();

	Date getDataAtualizacao();

	void setDataAtualizacao(Date dataAtualizacao);

	Usuario getUsuarioAtualizacao();

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);
	
	void setDataInclusao(Date dataInclusao);

	Date getDataInclusao();

	boolean isAtivo();

	void setAtivo(boolean ativo);

}