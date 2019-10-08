package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.CustoPlanilhaFinanceira;

public interface ICustoPlanilhaFinanceiraRepository extends Serializable{

	List<CustoPlanilhaFinanceira> getAll();

	CustoPlanilhaFinanceira getById(Long id);
	
	CustoPlanilhaFinanceira salvar(CustoPlanilhaFinanceira item);

	void remover(CustoPlanilhaFinanceira item);
	
	public  List<CustoPlanilhaFinanceira> getByPropostaId(Long idProposta);
	
}