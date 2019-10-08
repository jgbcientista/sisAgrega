package br.com.sysagrega.model;

import java.io.Serializable;

import br.com.sysagrega.model.imp.Proposta;

public interface ICustoPlanilhaFinanceira extends Serializable{

	Long getId();

	void setId(Long id);
	
	 String getDescricao();

	 Long getQuantidade();

	 Double getValorUnitario();

	 Double gevalorTotal();
	void setProposta(Proposta proposta);

	IProposta getProposta();

	void setDescricao(String descricao);

	void setQuantidade(Long quantidade);

	void setValorUnitario(Double valorUnitario);

	Double getValorTotal();

	void setValorTotal(Double valorTotal);

	Double getDesconto();

	void setDesconto(Double desconto);

	Double getValorNegociado();

	void setValorNegociado(Double valorNegociado);

	 

}