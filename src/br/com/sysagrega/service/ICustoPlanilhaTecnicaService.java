package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.CustoPlanilhaTecnica;


public interface ICustoPlanilhaTecnicaService extends Serializable{

	void remover(CustoPlanilhaTecnica item);

	CustoPlanilhaTecnica getById(Long id);

	CustoPlanilhaTecnica atualizar(CustoPlanilhaTecnica item);

	List<CustoPlanilhaTecnica> getByPropostaId(Long idProposta);

	List<CustoPlanilhaTecnica> getAll();

	CustoPlanilhaTecnica salvar(CustoPlanilhaTecnica item);


	


}