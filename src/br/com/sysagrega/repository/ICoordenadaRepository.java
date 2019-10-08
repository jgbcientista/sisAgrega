package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ICoordenada;
import br.com.sysagrega.model.imp.Coordenada;

public interface ICoordenadaRepository extends Serializable{

	List<Coordenada> getAll();

	Coordenada getById(Long id);

	List<Coordenada> getByProjeto(Long idProjeto);

	ICoordenada salvar(Coordenada item);

	void remover(ICoordenada item);

	Coordenada obterMaiorOrdenacao(Long idProjeto);

	
}