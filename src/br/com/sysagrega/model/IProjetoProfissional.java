package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Usuario;

public interface IProjetoProfissional extends Serializable{
	
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

	Boolean getChefe();

	void setChefe(Boolean chefe);

	IProjeto getProjeto();

	void setProjeto(IProjeto projeto);

	void setProfissional(IProfissional profissional);

	IProfissional getProfissional();
	

}