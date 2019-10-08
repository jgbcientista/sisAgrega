package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IAnexo;
import br.com.sysagrega.model.imp.Anexo;

public interface IAnexoRepository extends Serializable{

	Anexo getById(Long id);

	IAnexo saveOrMerge(IAnexo item);

	List<Anexo> getAll();

	void remover(IAnexo item);

	List<Anexo> getByIdByTipo(Long tipo, Long idEntidade, String descricao);

	Boolean verificaSeAnexoJaExite(Long tipo, Long idEntidade, String descricao);




}