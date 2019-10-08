package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Usuario;

public interface IPerfil extends Serializable{

	Long getId();

	void setId(Long id);
	
	String getNome();

	void setNome(String nome);

	void setDataInclusao(Date dataInclusao);

	Date getDataInclusao();

	boolean isNovo();
	boolean isExistente();

	String getSigla();

	void setSigla(String sigla);

	Date getDataAtualizacao();

	void setDataAtualizacao(Date dataAtualizacao);

	Usuario getUsuarioAtualizacao();

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);

	boolean isAtivo();

	void setAtivo(boolean ativo);
}