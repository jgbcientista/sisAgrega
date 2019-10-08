package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.CustoAdministrativo;
import br.com.sysagrega.model.imp.CustoBdiComissao;
import br.com.sysagrega.model.imp.CustoDeslocamento;
import br.com.sysagrega.model.imp.CustoExecucao;
import br.com.sysagrega.model.imp.CustoOperacional;
import br.com.sysagrega.model.imp.CustoSeguranca;
import br.com.sysagrega.model.imp.Proposta;

public interface IPrecificacao extends Serializable{

	/**
	 * @return the id
	 */
	Long getId();

	/**
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * @return the custos
	 */
	List<CustoExecucao> getCustos();

	/**
	 * @param custos the custos to set
	 */
	void setCustos(List<CustoExecucao> custos);

	Proposta getProposta();

	void setProposta(Proposta proposta);

	void setValorTotalPrecificacao(double valorTotalPrecificacao);

	double getValorTotalPrecificacao();

	double getIR();

	double getCSLL();

	double getPIS();

	double getCOFINS();

	double getISS();

	double getImpostos();

	void setValorTotalImpostos(double valorTotalImpostos);

	double getValorTotalImpostos();

	void setDespesasBdiComissao(List<CustoBdiComissao> despesasBdiComissao);

	List<CustoBdiComissao> getDespesasBdiComissao();

	void setDespesasAdministrativas(List<CustoAdministrativo> despesasAdministrativas);

	List<CustoAdministrativo> getDespesasAdministrativas();

	void setDespesasSeguranca(List<CustoSeguranca> despesasSeguranca);

	List<CustoSeguranca> getDespesasSeguranca();

	void setDespesasOperacionais(List<CustoOperacional> despesasOperacionais);

	List<CustoOperacional> getDespesasOperacionais();

	void setDespesasDeslocamentos(List<CustoDeslocamento> despesasDeslocamentos);

	List<CustoDeslocamento> getDespesasDeslocamentos();

	double getValorTotalCustosExecucao();

	void setValorTotalCustosExecucao(double valorTotalCustosExecucao);

	double getCalculoValorTotalCustosExecucao();

	double getValorTotalCustosDesclocamento();

	void setValorTotalCustosDesclocamento(double valorTotalCustosDesclocamento);

	double getValorTotalCustosOperacionais();

	void setValorTotalCustosOperacionais(double valorTotalCustosOperacionais);

	double getValorTotalCustosAdministrativos();

	void setValorTotalCustosAdministrativos(double valorTotalCustosAdministrativos);

	void setValorTotalCustosSeguranca(double valorTotalCustosSeguranca);

	double getValorTotalCustosSeguranca();

	void setValorTotalCustosBdiComissoes(double valorTotalCustosBdiComissoes);

	double getValorTotalCustosBdiComissoes();

	double getCalculoValorTotalCustosDeslocamento();

	double getCalculoValorTotalCustosSeguranca();

	double getCalculoValorTotalCustosOperacionais();

	double getCalculoValorTotalCustosAdministraticos();

	double getValorTotalComBdiComissao();

	void setValorTotalComBdiComissao(double valorTotalComBdiComissao);

	double getValorTotalSemBdiComissao();

	void setValorTotalSemBdiComissao(double valorTotalSemBdiComissao);
	
	double getCalculoValorTotalCustosBdiComissao();


}