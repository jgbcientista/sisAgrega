package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.AplicacaoRecurso;
import br.com.sysagrega.model.imp.ClassificacaoRecurso;
import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.model.imp.TipoRecurso;
import br.com.sysagrega.model.imp.Usuario;

public interface IRecurso extends Serializable{

	/**
	 * @return the id
	 */
	Long getId();

	/**
	 * @param id the id to set
	 */
	void setId(Long id);
	
	String getNome();
	void setNome(String nome);
	
	String getFabricante();
	void setFabricante(String fabricante);
	
	Date getValidade();
	void setValidade(Date valiade);
	
	Date getGarantia();
	void setGarantia(Date garantia);
	
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
	
	void setAtivo(boolean ativo);

	boolean isAtivo();

	void setClassificacaoRecurso(ClassificacaoRecurso classificacaoRecurso);

	ClassificacaoRecurso getClassificacaoRecurso();

	TipoRecurso getTipoRecurso();

	void setAplicacao(AplicacaoRecurso aplicacao);

	AplicacaoRecurso getAplicacao();

	void setTipoRecurso(TipoRecurso tipoRecurso);

	void setFornecedor(String fornecedor);

	String getFornecedor();

	void setMarca(String marca);

	String getMarca();

	void setNumeroSerie(Long numeroSerie);

	Long getNumeroSerie();

	void setDataAquisicao(Date dataAquisicao);

	void setCusto(String custo);

	Date getDataAquisicao();

	void setArmazenamento(String armazenamento);

	String getArmazenamento();

	void setDepreciacao(String depreciacao);

	String getDepreciacao();

	Profissional getProfissional();

	void setProfissional(Profissional profissional);

	void setQuantidade(Long quantidade);

	Long getQuantidade();

	String getCusto();

	void setStatus(String status);

	String getStatus();

}