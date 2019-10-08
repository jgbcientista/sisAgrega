package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IRecurso;
import br.com.sysagrega.model.imp.Recurso;

public interface IRecursoRepository extends Serializable{

	List<Recurso> getAllRecursos();

	Recurso getRecursoById(Long id);
	
	IRecurso salvarRecurso(IRecurso recurso);

	void remover(IRecurso recurso);

	List<Recurso> getRecursoByFilter(Recurso filtro);

}