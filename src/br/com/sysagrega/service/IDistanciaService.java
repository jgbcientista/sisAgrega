package br.com.sysagrega.service;

import java.io.Serializable;
 

import java.util.List;

import br.com.sysagrega.model.IDistancia;
import br.com.sysagrega.model.imp.Distancia;

public interface IDistanciaService extends Serializable{

	public void remover(IDistancia item);
	
	void salvar(IDistancia item);
	
	public IDistancia atualizar(IDistancia item);

	IDistancia getById(Long id);

	List<Distancia> getAll();
	
	List<Distancia> getByPlanilhao(Long id);

}