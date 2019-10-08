package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.CustoPlanilhaTecnica;

public interface ICustoPlanilhaTecnicaRepository extends Serializable{

	List<CustoPlanilhaTecnica> getAll();

	CustoPlanilhaTecnica getById(Long id);

	CustoPlanilhaTecnica salvar(CustoPlanilhaTecnica item);

	List<CustoPlanilhaTecnica> getByPropostaId(Long idProposta);

	void remover(CustoPlanilhaTecnica item);

	

	
}