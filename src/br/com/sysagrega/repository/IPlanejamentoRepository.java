package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IPlanejamento;
import br.com.sysagrega.model.imp.Planejamento;


public interface IPlanejamentoRepository extends Serializable{

	List<Planejamento> getAll();

	Planejamento getById(Long id);

	IPlanejamento salvar(IPlanejamento item);

	void remover(IPlanejamento item);
	
	public Planejamento getByProjeto(Long idProjeto);

	List<Planejamento> getByFiltros(Planejamento filtro);

	
	
}