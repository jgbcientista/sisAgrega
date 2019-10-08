package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IAplicacaoRecurso;
import br.com.sysagrega.model.imp.AplicacaoRecurso;

public interface IAplicacaoRecursoService extends Serializable{

	public void remover(IAplicacaoRecurso recurso);
	
	void salvar(IAplicacaoRecurso recurso);
	
	public AplicacaoRecurso getById(Long id);
	
	public List<AplicacaoRecurso> getAll() ;
	
	public IAplicacaoRecurso atualizar(IAplicacaoRecurso recurso);

	
}