package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IClassificacaoRecurso;
import br.com.sysagrega.model.imp.ClassificacaoRecurso;

public interface IClassificacaoRecursoRepository extends Serializable{

	List<ClassificacaoRecurso> getAll();

	ClassificacaoRecurso getById(Long id);
	
	IClassificacaoRecurso salvar(IClassificacaoRecurso recurso);

	void remover(IClassificacaoRecurso recurso);
	
}