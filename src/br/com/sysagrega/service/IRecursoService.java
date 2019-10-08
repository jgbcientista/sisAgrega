package br.com.sysagrega.service;

import java.io.Serializable;
 

import java.util.List;

import br.com.sysagrega.model.IRecurso;
import br.com.sysagrega.model.imp.Recurso;

public interface IRecursoService extends Serializable{

	public void remover(IRecurso recurso);
	
	void salvarRecurso(IRecurso recurso);
	
	public Recurso getRecursoById(Long id);
	
	public List<Recurso> getAllRecursos() ;
	
	public IRecurso atualizarRecurso(IRecurso recurso);

	List<Recurso> getRecursoByFilter(Recurso filtro);


	
}