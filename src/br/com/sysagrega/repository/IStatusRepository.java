package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IStatus;
import br.com.sysagrega.model.imp.Status;


public interface IStatusRepository extends Serializable{

	List<Status> getAll();

	Status getById(Long id);

	void remover(IStatus item);

	IStatus salvar(IStatus item);

	List<Status> obterByTipo(Long tipo);

	
	
}