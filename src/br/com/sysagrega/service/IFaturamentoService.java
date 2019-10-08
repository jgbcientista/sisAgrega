package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IFaturamento;
import br.com.sysagrega.model.imp.Faturamento;

public interface IFaturamentoService extends Serializable{

	List<Faturamento> getAll();

	Faturamento getById(Long id);

	List<Faturamento> getByProjeto(Long idProjeto);

	IFaturamento salvar(IFaturamento item);

	public void remover(IFaturamento item);
	
	public Faturamento getByProjetoByParcela(Long idProjeto, Long parcela);

	List<Faturamento> getByContrato(Long idContrato);
	
	List<Faturamento> getByFiltros(Faturamento filtro);
	

}