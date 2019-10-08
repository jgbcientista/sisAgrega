package br.com.sysagrega.service;

import java.io.Serializable;
 

import java.util.List;

import br.com.sysagrega.model.ICustoPlanilhaFinanceira;
import br.com.sysagrega.model.imp.CustoPlanilhaFinanceira;


public interface ICustoPlanilhaFinanceiraService extends Serializable{

	public void remover(CustoPlanilhaFinanceira item);
	
	public CustoPlanilhaFinanceira salvar(CustoPlanilhaFinanceira item);
	
	public  CustoPlanilhaFinanceira getById(Long id);
	
	public List<CustoPlanilhaFinanceira> getAll() ;
	
	public CustoPlanilhaFinanceira atualizar(CustoPlanilhaFinanceira item);
	
	public  List<CustoPlanilhaFinanceira> getByPropostaId(Long idProposta);

}