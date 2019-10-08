package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IPerfil;
import br.com.sysagrega.model.imp.Perfil;

public interface IPerfilRepository extends Serializable{

	IPerfil getById(Long id);

	public List<Perfil> getByFilter(String nome);

	IPerfil saveOrMerge(IPerfil usuario);

	void remover(IPerfil usuario);

	List<Perfil> getAll();


}