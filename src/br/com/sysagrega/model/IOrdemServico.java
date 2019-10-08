package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Usuario;

public interface IOrdemServico extends Serializable{

	boolean isNovo();

	boolean isExistente();

	Long getId();

	void setId(Long id);

	Date getDataExclusao();

	void setDataExclusao(Date dataExclusao);

	Usuario getUsuarioAtualizacao();

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);

	Date getDataInclusao();

	void setDataInclusao(Date dataInclusao);

	boolean isAtivo();

	void setAtivo(boolean ativo);

	String getNumeroOS();

	void setNumeroOS(String numeroOS);

	IContrato getContrato();

	Integer getQtdProjetos();

	void setQtdProjetos(Integer qtdProjetos);

	Integer getQtdCadastrada();

	void setQtdCadastrada(Integer qtdCadastrada);

	Integer getQtdFaturada();

	void setQtdFaturada(Integer qtdFaturada);

	Double getValorFaturado();

	void setValorFaturado(Double valorFaturado);

	Date getDatafinalizacao();

	void setDatafinalizacao(Date datafinalizacao);

	Long getContadorNrOS();

	void setContadorNrOS(Long contadorNrOS);

	void setContrato(IContrato contrato);

	ICliente getCliente();

	void setCliente(ICliente cliente);
	
}