package br.com.sysagrega.service;

import java.io.Serializable;
 

import java.util.List;

import br.com.sysagrega.model.IProjetoOrdemServico;
import br.com.sysagrega.model.imp.ProjetoOrdemServico;

public interface IProjetoOrdemServicoService extends Serializable{

	public void remover(IProjetoOrdemServico item);
	
	void salvar(IProjetoOrdemServico item);
	
	public IProjetoOrdemServico atualizar(IProjetoOrdemServico item);

	IProjetoOrdemServico getById(Long id);

	List<ProjetoOrdemServico> getAll();
	
	List<ProjetoOrdemServico> getByProjetoByOS(Long idProjeto, Long idOrdemServico);

}