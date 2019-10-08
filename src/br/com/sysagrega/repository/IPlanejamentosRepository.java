package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IPlanejamentos;
import br.com.sysagrega.model.imp.Planejamentos;


public interface IPlanejamentosRepository extends Serializable{

	List<Planejamentos> getAll();

	Planejamentos getById(Long id);

	IPlanejamentos salvar(IPlanejamentos item);

	void remover(IPlanejamentos item);

	Integer obterMaiorID();
	
	
}