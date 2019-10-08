package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IAnexo;
import br.com.sysagrega.model.imp.Anexo;

public interface IAnexoService extends Serializable{

	IAnexo salvar(IAnexo item);

	void excluir(IAnexo item);

	IAnexo getById(Long id);

	List<Anexo> getAll();

	IAnexo atualizar(IAnexo contato);

	public List<Anexo> getByIdByTipo(Long codigo, Long tipo, String escricao);

	Boolean verificaSeAnexoJaExite(Long tipo, Long idEntidade, String descricao);
	
	
}