package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IProjetoOrdemServico;
import br.com.sysagrega.model.imp.ProjetoOrdemServico;

public interface IProjetoOrdemServicoRepository extends Serializable{

	List<ProjetoOrdemServico> getAll();

	ProjetoOrdemServico getById(Long id);
	
	IProjetoOrdemServico salvar(IProjetoOrdemServico item);

	void remover(IProjetoOrdemServico item);

	List<ProjetoOrdemServico> getByProjetoByOS(Long idProjeto, Long idOrdemServico);
}