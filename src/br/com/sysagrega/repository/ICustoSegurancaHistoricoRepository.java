package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoSegurancaHistorico;
import br.com.sysagrega.model.imp.CustoSegurancaHistorico;

public interface ICustoSegurancaHistoricoRepository extends Serializable{

	Set<CustoSegurancaHistorico> getAll();

	CustoSegurancaHistorico getById(Long id);
	
	ICustoSegurancaHistorico saveOrMerge(ICustoSegurancaHistorico custo);

	void remover(ICustoSegurancaHistorico custo);
	
	public  List<CustoSegurancaHistorico> getByPropostaHistoricoId(Long idPropostaHistorico);

}