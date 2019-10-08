package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ITipoRecurso;
import br.com.sysagrega.model.imp.TipoRecurso;

public interface ITipoRecursoRepository extends Serializable{

	List<TipoRecurso> getAll();

	TipoRecurso getById(Long id);
	
	ITipoRecurso salvar(ITipoRecurso recurso);

	void remover(ITipoRecurso recurso);
	
}