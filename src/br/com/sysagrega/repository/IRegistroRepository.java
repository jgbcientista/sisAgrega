package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IRegistro;
import br.com.sysagrega.model.imp.Registro;

public interface IRegistroRepository extends Serializable{

	List<Registro> getAll();

	Registro getById(Long id);
	
	void remover(IRegistro item);

	IRegistro salvar(IRegistro item);
	
	 
	
}