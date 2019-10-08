package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IFaturamento;
import br.com.sysagrega.model.imp.Faturamento;


public interface IFaturamentoRepository extends Serializable{

	List<Faturamento> getAll();

	Faturamento getById(Long id);

	IFaturamento salvar(IFaturamento item);

	void remover(IFaturamento item);

	List<Faturamento> getByProjeto(Long idProjeto);

	Faturamento getByProjetoByParcela(Long idProjeto, Long parcela);

	List<Faturamento> getByContrato(Long idContrato);

	List<Faturamento> getByFiltros(Faturamento filtro);

	
	
}