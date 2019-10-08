package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ITipoPrograma;
import br.com.sysagrega.model.imp.TipoPrograma;


public interface ITipoProgramaRepository extends Serializable{

	List<TipoPrograma> getAll();

	TipoPrograma getById(Long id);

	void remover(ITipoPrograma item);

	ITipoPrograma salvar(ITipoPrograma item);

}