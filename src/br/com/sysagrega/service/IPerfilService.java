package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IPerfil;
import br.com.sysagrega.model.imp.Perfil;

public interface IPerfilService extends Serializable{

	void salvar(IPerfil usuario);

	List<Perfil> getAll();

	IPerfil atualizar(IPerfil usuario);

	void excluir(IPerfil usuario);

	public List<Perfil> getByFilter(String nome);
	
	public Perfil getById(Long id);
}