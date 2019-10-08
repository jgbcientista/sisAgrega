package br.com.sysagrega.service;

import java.io.Serializable;
 

import java.util.List;

import br.com.sysagrega.model.ITipoServico;
import br.com.sysagrega.model.imp.TipoServico;

public interface ITipoServicoService extends Serializable{

	public void remover(ITipoServico tipoServico);
	
	void salvar(ITipoServico recurso);
	
	public ITipoServico getTipoServicoById(Long id);
	
	public List<TipoServico> getAllTipoServicos() ;
	
	public ITipoServico atualizar(ITipoServico tipoServico);

}