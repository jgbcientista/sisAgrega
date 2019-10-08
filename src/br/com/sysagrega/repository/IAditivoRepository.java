package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Aditivo;

public interface IAditivoRepository extends Serializable{

	List<Aditivo> getAll();

	Aditivo getById(Long id);
	
	IAditivo salvar(IAditivo item);

	void remover(IAditivo item);
	
	public List<Aditivo> getByContrato(Aditivo filtro);
	
	
	
}