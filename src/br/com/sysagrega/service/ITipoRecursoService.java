package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ITipoRecurso;
import br.com.sysagrega.model.imp.TipoRecurso;

public interface ITipoRecursoService extends Serializable{

	public void remover(ITipoRecurso recurso);
	
	void salvar(ITipoRecurso recurso);
	
	public TipoRecurso getById(Long id);
	
	public List<TipoRecurso> getAll() ;
	
	public ITipoRecurso atualizar(ITipoRecurso recurso);

	
}