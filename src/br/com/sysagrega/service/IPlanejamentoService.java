package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IPlanejamento;
import br.com.sysagrega.model.imp.Planejamento;
 
public interface IPlanejamentoService extends Serializable{

	void remover(IPlanejamento item);

	Planejamento getById(Long id);

	List<Planejamento> getAll();

	IPlanejamento salvar(IPlanejamento item);
	
	Planejamento getByProjeto(Long idProjeto);

	List<Planejamento> getByFiltros(Planejamento filtro);
	
}