package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoDeslocamentoHistorico;
import br.com.sysagrega.model.imp.CustoDeslocamentoHistorico;

public interface ICustoDeslocamentoHistoricoRepository extends Serializable{

	Set<CustoDeslocamentoHistorico> getAll();

	CustoDeslocamentoHistorico getById(Long id);
	
	ICustoDeslocamentoHistorico saveOrMerge(ICustoDeslocamentoHistorico custo);

	void remover(ICustoDeslocamentoHistorico custo);

	List<CustoDeslocamentoHistorico> getByPropostaHistoricoId(Long idPropostaHistorico);

}