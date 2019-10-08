package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IVerificacaoProfissional;
import br.com.sysagrega.model.imp.VerificacaoProfissional;
 
public interface IVerificacaoProfissionalService extends Serializable{

	void remover(IVerificacaoProfissional item);

	VerificacaoProfissional getById(Long id);

	List<VerificacaoProfissional> getAll();

	IVerificacaoProfissional salvar(IVerificacaoProfissional item);

	VerificacaoProfissional getByProjeto(Long idProjeto);

	List<VerificacaoProfissional> getByFiltros(VerificacaoProfissional filtro);


}