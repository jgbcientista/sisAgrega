package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IStatus;
import br.com.sysagrega.model.imp.Status;


public interface SituacaoRepository extends Serializable{

	List<Status> getAll();

	Status getById(Long id);

	void remover(IStatus item);

	IStatus salvar(IStatus item);

	
	
}