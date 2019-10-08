package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IUsuario;
import br.com.sysagrega.model.imp.Usuario;

public interface IUsuarioService extends Serializable{

	IUsuario salvar(IUsuario usuario);

	List<Usuario> getAll();

	IUsuario atualizar(IUsuario usuario);

	void excluir(IUsuario usuario);

	public List<Usuario> getByFilter(String login, String nome, String perfil);
	
	public Usuario getById(Long id);
	
	public Usuario getUserPerfilByLogin(String login);
}