package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ICltMensal;
import br.com.sysagrega.model.imp.CltMensal;

public interface ICltMensalService extends Serializable{

	ICltMensal salvar(ICltMensal cltMensal);
	List<CltMensal> getAllCltMensal();
	ICltMensal atualizarCliente(ICltMensal cltMensal);
	void excluirCltMensal(CltMensal cltMensal);
	CltMensal getCltMensalById(Long cod);

}