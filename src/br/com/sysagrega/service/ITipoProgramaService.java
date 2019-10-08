package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ITipoPrograma;
import br.com.sysagrega.model.imp.TipoPrograma;

public interface ITipoProgramaService extends Serializable{

	List<TipoPrograma> getAll();

	TipoPrograma getById(Long id);

	ITipoPrograma salvar(ITipoPrograma item);

	public void remover(ITipoPrograma item);


}