package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ICltMensal;
import br.com.sysagrega.model.imp.CltMensal;

public interface ICltMensalRepository extends Serializable {

	CltMensal getCltMensalById(Long cod);
	ICltMensal saveOrMerge(ICltMensal cltMensal);
	void remover(CltMensal cltMensal);
	List<CltMensal> getAllCltMensal();

}