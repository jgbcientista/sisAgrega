package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IVerificacaoProfissional;
import br.com.sysagrega.model.imp.VerificacaoProfissional;


public interface IVerificacaoProfissionalRepository extends Serializable{

	List<VerificacaoProfissional> getAll();

	VerificacaoProfissional getById(Long id);

	IVerificacaoProfissional salvar(IVerificacaoProfissional item);

	void remover(IVerificacaoProfissional item);

	VerificacaoProfissional getByProjeto(Long idProjeto);

	List<VerificacaoProfissional> getByFiltros(VerificacaoProfissional filtro);

	
	
}