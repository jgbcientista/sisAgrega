package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ITipoServico;
import br.com.sysagrega.model.imp.TipoServico;

public interface ITipoServicoRepository extends Serializable{

	List<TipoServico> getAllTipoServicos();

	TipoServico getTipoServicoById(Long id);
	
	ITipoServico salvar(ITipoServico tipoServico);

	void remover(ITipoServico tipoServico);
	
}