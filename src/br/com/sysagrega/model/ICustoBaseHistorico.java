package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Usuario;

public interface ICustoBaseHistorico extends Serializable{

	/**
	 * @return the descricao
	 */
	String getDescricao();

	/**
	 * @param descricao the descricao to set
	 */
	void setDescricao(String descricao);

	/**
	 * @return the quantidade
	 */
	Long getQuantidade();

	/**
	 * @param quantidade the quantidade to set
	 */
	void setQuantidade(Long quantidade);

	/**
	 * @return the valorUnitario
	 */
	Double getValorUnitario();

	/**
	 * @param valorUnitario the valorUnitario to set
	 */
	void setValorUnitario(Double valorUnitario);

	/**
	 * @return the valorTotal
	 */
	Double getValorTotal();

	/**
	 * @param valorTotal the valorTotal to set
	 */
	void setValorTotal(Double valorTotal);

	void setObservacoes(String observacoes);

	String getObservacoes();

	void setId(Long id);

	Long getId();

	void setPropostaHistorico(IPropostaHistorico propostaHistorico);

	IPropostaHistorico getPropostaHistorico();

	Date getDataAtualizacao();

	void setDataAtualizacao(Date dataAtualizacao);

	Usuario getUsuarioAtualizacao();

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);

	Date getDataInclusao();

	void setDataInclusao(Date dataInclusao);

	boolean isAtivo();

	void setAtivo(boolean ativo);

	String getTpUnidadeMedida();

	void setTpUnidadeMedida(String tpUnidadeMedida);

}