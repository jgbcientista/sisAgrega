package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Regiao;

public interface IRegiaoRepository extends Serializable{

	List<Regiao> getAllRegiao();

	Regiao getRegiaoById(Long id);

	

}