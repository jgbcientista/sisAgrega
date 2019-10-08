package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import br.com.sysagrega.model.ICustoOperacionalHistorico;
import br.com.sysagrega.model.imp.CustoOperacionalHistorico;

public interface ICustoOperacionalHistoricoRepository extends Serializable{

	Set<CustoOperacionalHistorico> getAll();

	CustoOperacionalHistorico getById(Long id);
	
	 ICustoOperacionalHistorico saveOrMerge(ICustoOperacionalHistorico custo);

	void remover(ICustoOperacionalHistorico custoHistorico);

	List<CustoOperacionalHistorico> getByPropostaHistoricoId(Long idPropostaHistorico);

}