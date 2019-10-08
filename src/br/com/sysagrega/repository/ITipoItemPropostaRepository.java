package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ITipoItemProposta;
import br.com.sysagrega.model.imp.TipoItemProposta;

public interface ITipoItemPropostaRepository extends Serializable{

	List<TipoItemProposta> getAll();

	TipoItemProposta getById(Long id);
	
	TipoItemProposta salvar(TipoItemProposta tipo);

	void remover(ITipoItemProposta tipo);
	
	List<TipoItemProposta> getByPropostaId(Long idProposta, Long idTipoProposta);
	
}