package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ITipoDespesa;
import br.com.sysagrega.model.imp.TipoDespesa;

public interface ITipoDespesaRepository extends Serializable{

	List<TipoDespesa> getAllTipoDespesas();

	TipoDespesa getTipoDespesaById(Long id);
	
	ITipoDespesa salvar(ITipoDespesa tipoDespesa);

	void remover(ITipoDespesa tipoDespesa);
	
}