package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IAplicacaoRecurso;
import br.com.sysagrega.model.imp.AplicacaoRecurso;

public interface IAplicacaoRecursoRepository extends Serializable{

	List<AplicacaoRecurso> getAll();

	AplicacaoRecurso getById(Long id);
	
	IAplicacaoRecurso salvar(IAplicacaoRecurso recurso);

	void remover(IAplicacaoRecurso recurso);
	
}