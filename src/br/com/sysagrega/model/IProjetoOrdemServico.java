package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Usuario;

public interface IProjetoOrdemServico extends Serializable{
	
	void setId(Long id);
	
	Long getId();

	boolean isExistente();

	boolean isNovo();

	Usuario getUsuarioAtualizacao();

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);

	Date getDataInclusao();

	void setDataInclusao(Date dataInclusao);

	Boolean getAtivo();

	void setAtivo(Boolean ativo);

	void setOrdemServico(IOrdemServico ordemServico);

	IOrdemServico getOrdemServico();

	IProjeto getProjeto();

	void setProjeto(IProjeto projeto);

}