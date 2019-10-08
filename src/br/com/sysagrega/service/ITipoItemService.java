package br.com.sysagrega.service;

import java.io.Serializable;
 

import java.util.List;

import br.com.sysagrega.model.ITipoItem;
import br.com.sysagrega.model.imp.TipoItem;


public interface ITipoItemService extends Serializable{

	public void remover(ITipoItem tipo);
	
	void salvar(ITipoItem tipo);
	
	public ITipoItem getById(Long id);
	
	public List<TipoItem> getAll() ;
	
	public ITipoItem atualizar(ITipoItem tipo);

}