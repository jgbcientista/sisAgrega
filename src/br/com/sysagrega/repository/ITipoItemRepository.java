package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ITipoItem;
import br.com.sysagrega.model.imp.TipoItem;

public interface ITipoItemRepository extends Serializable{

	List<TipoItem> getAll();

	TipoItem getById(Long id);
	
	ITipoItem salvar(ITipoItem tipo);

	void remover(ITipoItem tipo);
	
}