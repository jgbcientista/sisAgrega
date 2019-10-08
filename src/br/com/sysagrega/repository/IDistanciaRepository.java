package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IDistancia;
import br.com.sysagrega.model.imp.Distancia;

public interface IDistanciaRepository extends Serializable{

	List<Distancia> getAll();

	Distancia getById(Long id);
	
	IDistancia salvar(IDistancia item);

	void remover(IDistancia item);

	List<Distancia> getByPlanilhao(Long id);
	
}