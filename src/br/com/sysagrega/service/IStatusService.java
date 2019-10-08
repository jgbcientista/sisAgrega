package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IStatus;
import br.com.sysagrega.model.imp.Status;

public interface IStatusService extends Serializable{

	List<Status> getAll();

	Status getById(Long id);

	IStatus salvar(IStatus item);

	public void remover(IStatus item);

	List<Status> obterByTipo(Long tipo);
	

}