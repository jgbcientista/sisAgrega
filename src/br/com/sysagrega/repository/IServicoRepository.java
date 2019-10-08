package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IServico;
import br.com.sysagrega.model.imp.Servico;

public interface IServicoRepository extends Serializable{

	List<Servico> getAllServicos();

	Servico getServicoById(Long id);
	
	IServico saveOrMerge(IServico servico);

	void remover(IServico servico);
	
	List<Servico> getServicoByFilter(Long tipo, Long area, String descricao);

}