package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IUsuario;
import br.com.sysagrega.model.imp.Usuario;

public interface IUsuarioRepository extends Serializable{

	IUsuario getById(Long id);

	public List<Usuario> getByFilter(String login, String nome, String perfil);

	IUsuario saveOrMerge(IUsuario usuario);

	void remover(IUsuario usuario);

	List<Usuario> getAll();
	
	public Usuario getUserPerfilByLogin(String login);


}