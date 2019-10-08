package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Coordenada;

public interface ICoordenadaService extends Serializable{

	List<Coordenada> getAll();

	Coordenada getById(Long id);

	List<Coordenada> getByProjeto(Long idProjeto);

	Coordenada salvar(Coordenada item);

	void remover(Coordenada item);

	Coordenada obterMaiorOrdenacao(Long idProjeto);

	
	

}