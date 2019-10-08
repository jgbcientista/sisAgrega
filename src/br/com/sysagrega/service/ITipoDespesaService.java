package br.com.sysagrega.service;

import java.io.Serializable;
 

import java.util.List;

import br.com.sysagrega.model.ITipoDespesa;
import br.com.sysagrega.model.imp.TipoDespesa;


public interface ITipoDespesaService extends Serializable{

	public void remover(ITipoDespesa tipo);
	
	void salvar(ITipoDespesa tipo);
	
	public ITipoDespesa getTipoDespesaById(Long id);
	
	public List<TipoDespesa> getAllTipoDespesas() ;
	
	public ITipoDespesa atualizar(ITipoDespesa tipo);

}