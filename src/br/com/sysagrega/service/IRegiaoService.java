package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Regiao;


public interface IRegiaoService extends Serializable{

	List<Regiao> getAllRegiao();

	

}