
package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Usuario;

public interface IPropostaBase extends Serializable {

	/**
	 * @return the tipoProposta
	 */
	String getTipoProposta();

	/**
	 * @param tipoProposta the tipoProposta to set
	 */
	void setTipoProposta(String tipoProposta);

	/**
	 * @return the dataInclusao
	 */
	Date getDataInclusao();

	/**
	 * @param dataInclusao the dataInclusao to set
	 */
	void setDataInclusao(Date dataInclusao);

	/**
	 * @return the cliente
	 */
	ICliente getCliente();

	/**
	 * @param numeroProposta the numeroProposta to set
	 */
	void setNumeroProposta(String numeroProposta);

	String getNomeProjeto();

	void setNomeProjeto(String nomeProjeto);

	Long getId();

	void setId(Long id);

	double getValorTotalImpostos();

	void setValorTotalImpostos(double valorTotalImpostos);

	double getValorTotalPrecificacao();

	void setValorTotalPrecificacao(double valorTotalPrecificacao);

	double getValorTotalCustosExecucao();

	void setValorTotalCustosExecucao(double valorTotalCustosExecucao);

	double getValorTotalCustosDesclocamento();

	void setValorTotalCustosDesclocamento(double valorTotalCustosDesclocamento);

	double getValorTotalCustosOperacionais();

	double getImpostos();

	double getIr();

	double getCsll();

	double getPis();

	double getCofins();

	double getIss();

	void setValorTotalSemBdiComissao(Double valorTotalSemBdiComissao);

	Double getValorTotalSemBdiComissao();

	void setValorTotalComBdiComissao(double valorTotalComBdiComissao);

	double getValorTotalComBdiComissao();

	void setValorTotalCustosSeguranca(double valorTotalCustosSeguranca);

	double getValorTotalCustosSeguranca();

	void setValorTotalCustosBdiComissoes(double valorTotalCustosBdiComissoes);

	double getValorTotalCustosBdiComissoes();

	void setValorTotalCustosAdministrativos(double valorTotalCustosAdministrativos);

	double getValorTotalCustosAdministrativos();

	void setValorTotalCustosOperacionais(double valorTotalCustosOperacionais);

	void setValorTotalDaProposta(Double valorTotalDaProposta);

	Double getValorTotalDaProposta();

	void setCliente(ICliente cliente);

	Double getPercentualBDI();

	Double getPercentualComissao();

	/*Double getValorBDI();

	Double getValorComissao();

	Double getValorISS();

	Double getValorConfins();

	Double getValorPis();

	Double getValorCSLL();

	Double getValorIr();

	Double getValorImpostos();*/

	void setPercentualBDI(Double percentualBDI);

	void setPercentualComissao(Double percentualComissao);

	/*void setValorBDI(Double valorBDI);

	void setValorComissao(Double valorComissao);

	void setValorISS(Double valorISS);

	void setValorConfins(Double valorConfins);

	void setValorPis(Double valorPis);

	void setValorCSLL(Double valorCSLL);

	void setValorIr(Double valorIr);

	void setValorImpostos(Double valorImpostos);*/

	String getNumeroProposta();

	void setDadosDestinatarioEnvioTecnico(String dadosDestinatarioEnvioTecnico);

	String getDadosDestinatarioEnvioTecnico();

	void setDadosDestinatarioEnvioFinanceiro(
			String dadosDestinatarioEnvioFinanceiro);

	String getDptoEnvioFinanceiro();

	String getDptoEnvioTecnico();

	String getDestinatarioEnvioTecnico();

	String getDestinatarioEnvioFinanceiro();

	String getDadosDestinatarioEnvioFinanceiro();

	void setDptoEnvioFinanceiro(String dptoEnvioFinanceiro);

	void setDestinatarioEnvioFinanceiro(String destinatarioEnvioFinanceiro);

	void setDestinatarioEnvioTecnico(String destinatarioEnvioTecnico);

	void setDptoEnvioTecnico(String dptoEnvioTecnico);

	Date getDataAtualizacao();

	void setDataAtualizacao(Date dataAtualizacao);

	Usuario getUsuarioAtualizacao();

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);

	void setAtivo(boolean ativo);

	boolean isAtivo();

	String getUnidade();

	void setUnidade(String unidade);

	Double getDesconto();

	void setDesconto(Double desconto);

	Long getRevisaoPrecificacao();

	void setRevisaoPrecificacao(Long revisaoPrecificacao);

	String getStatusProposta();

	void setStatusProposta(String statusProposta);

	String getConsideracoesFinais();

	void setConsideracoesFinais(String consideracoesFinais);

	String getTextPropostaGenerica();

	void setTextPropostaGenerica(String textPropostaGenerica);

	
	String getObjPrincTec();

	void setObjPrincTec(String objPrincTec);

	String getValidadeTec();

	void setValidadeTec(String validadeTec);

	String getObjPrincFinc();

	void setObjPrincFinc(String objPrincFinc);

	String getFinanceiroCusto();

	void setFinanceiroCusto(String financeiroCusto);

	String getFormaPagtoFinanc();

	void setFormaPagtoFinanc(String formaPagtoFinanc);

	String getValidadeFinc();

	void setValidadeFinc(String validadeFinc);

	String getAssinaturaTec();

	void setAssinaturaTec(String assinaturaTec);

	String getFuncaoCargoTec();

	void setFuncaoCargoTec(String funcaoCargoTec);

	String getAssinaturaFinanc();

	void setAssinaturaFinanc(String assinaturaFinanc);

	String getFuncaoCargoFinanc();

	void setFuncaoCargoFinanc(String funcaoCargoFinanc);

	Long getIdServicoNegocio();

	void setIdServicoNegocio(Long idServicoNegocio);

	Long getIdArea();

	void setIdArea(Long idArea);

	Long getIdNegocio();

	void setIdNegocio(Long idNegocio);

	String getColetaPreco();

	void setColetaPreco(String coletaPreco);

	String getDtLimiteApreset();

	void setDtLimiteApreset(String dtLimiteApreset);

	String getObjDadosEmpresa();

	void setObjDadosEmpresa(String objDadosEmpresa);

	String getDestEnvioTecFinan();

	void setDestEnvioTecFinan(String destEnvioTecFinan);

	String getDadosDestEnvioTecFinan();

	void setDadosDestEnvioTecFinan(String dadosDestEnvioTecFinan);

	String getDptoEnvioTecFinan();

	void setObjPrincTecFinac(String objPrincTecFinac);

	String getObjPrincTecFinac();

	void setDescricaoTecFinan(String descricaoTecFinan);

	String getDescricaoTecFinan();

	void setDptoEnvioTecFinan(String dptoEnvioTecFinan);

	void setAssinaturaTecFinan(String assinaturaTecFinan);

	String getFuncaoCargoTecFinan();

	void setFuncaoCargoTecFinan(String funcaoCargoTecFinan);

	String getAssinaturaTecFinan();

	String getValidadeTecFinac();

	void setValidadeTecFinac(String validadeTecFinac);

	void setDescricaPlanComer(String descricaPlanComer);

	String getDescricaPlanComer();

	void setTituloPlanComer(String tituloPlanComer);

	String getTituloPlanComer();

	void setDescricaPlanTecnica(String descricaPlanTecnica);

	String getDescricaPlanTecnica();

	void setTituloPlanTecnica(String tituloPlanTecnica);

	String getTituloPlanTecnica();

	void setDescricaoPlanilhaFinac(String descricaoPlanilhaFinac);

	String getDescricaoPlanilhaFinac();

	void setTitiloPlanilhaFinc(String titiloPlanilhaFinc);

	String getTitiloPlanilhaFinc();

	void setInserirPlanCom(Boolean inserirPlanCom);

	Boolean getInserirPlanCom();

	void setInserirPlanTec(Boolean inserirPlanTec);

	Boolean getInserirPlanTec();

	void setInserirPlanFinan(Boolean inserirPlanFinan);

	Boolean getInserirPlanFinan();

	Double getVlrNegTotalPlanFinanceira();

	void setVlrNegTotalPlanFinanceira(Double vlrNegTotalPlanFinanceira);

	Double getDescPlanFin();

	void setDescPlanFin(Double descPlanFin);

	Boolean getInserirPlanTecEquipamento();

	void setInserirPlanTecEquipamento(Boolean inserirPlanTecEquipamento);

	Integer getOrdProposta();

	void setOrdProposta(Integer ordProposta);

	Date getDtEmissPropTec();

	void setDtEmissPropTec(Date dtEmissPropTec);

	Date getDtEmissPropFinc();

	void setDtEmissPropFinc(Date dtEmissPropFinc);

	Date getDtEmissPropTecFinac();

	void setDtEmissPropTecFinac(Date dtEmissPropTecFinac);

	Date getDtEmissaoGeral();

	void setDtEmissaoGeral(Date dtEmissaoGeral);

	String getMotivoExclusao();

	void setMotivoExclusao(String motivoExclusao);

	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}