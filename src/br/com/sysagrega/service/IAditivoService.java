package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Aditivo;
import br.com.sysagrega.repository.IAditivo;

public interface IAditivoService extends Serializable{

	public void remover(IAditivo item);
	
	IAditivo salvar(IAditivo item);
	
	public IAditivo atualizar(IAditivo item);

	IAditivo getById(Long id);
	
	public List<Aditivo> getByContrato(Aditivo filtro);


}