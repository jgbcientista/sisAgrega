package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IClassificacaoRecurso;
import br.com.sysagrega.model.imp.ClassificacaoRecurso;

public interface IClassificacaoRecursoService extends Serializable{

	public void remover(IClassificacaoRecurso recurso);
	
	void salvar(IClassificacaoRecurso recurso);
	
	public ClassificacaoRecurso getById(Long id);
	
	public List<ClassificacaoRecurso> getAll() ;
	
	public IClassificacaoRecurso atualizar(IClassificacaoRecurso recurso);

}