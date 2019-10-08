package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.model.imp.Usuario;

public interface IProfissionalService extends Serializable {

	void salvar(IProfissional profissional);

	List<Profissional> getAllProfissionals();

	IProfissional atualizarProfissional(IProfissional profissional);

	void excluirProfissional(IProfissional profissional);

	List<Profissional> getProfissionalByFilter(String nome, String cpf, String tipoColaborador);
	
	public Profissional getById(Long id);

	public Profissional obterProfissionalByLogin(Usuario usuario);

}