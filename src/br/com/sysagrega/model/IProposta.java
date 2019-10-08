package br.com.sysagrega.model;

import java.util.Date;
import java.util.List;

import br.com.sysagrega.model.imp.TipoItemProposta;

public interface IProposta {

	boolean isNovo();

	boolean isExistente();
	
	double getCalculoValorTotalCustosBdiComissao();

	double getCalculoValorTotalCustosSeguranca();

	double getCalculoValorTotalCustosOperacionais();

	double getCalculoValorTotalCustosDeslocamento();

	double getCalculoValorTotalCustosExecucao();

	void setDataAprovacao(Date dataAprovacao);

	Date getDataAprovacao();

	String getNumeroRevisao();

	String getRespProjeto();

	String getNomeProposta();

	String getDescricaoFinanceira();

	String getDescricaoTecnica();

	void setNumeroRevisao(String numRevisao);

	void setRespProjeto(String respProjeto);

	void setNomeProposta(String nomeProposta);

	void setDescricaoFinanceira(String descricaoFinanceira);

	void setDescricaoTecnica(String descricaoTecnica);

	List<TipoItemProposta> getListarTipoItemPropostaFinanceira();

	void setListarTipoItemPropostaFinanceira(List<TipoItemProposta> listarTipoItemPropostaFinanceira);

	List<TipoItemProposta> getListarTipoItemPropostaTecnica();

	void setListarTipoItemPropostaTecnica(List<TipoItemProposta> listarTipoItemPropostaTecnica);

	double getCalculoValorTotalCustosAdministrativos();

	List<TipoItemProposta> getListarTipoItemPropostaTecFinanceira();

	void setListarTipoItemPropostaTecFinanceira(List<TipoItemProposta> listarTipoItemPropostaTecFinanceira);

}