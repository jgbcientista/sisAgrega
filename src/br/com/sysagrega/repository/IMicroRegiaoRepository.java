package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.MicroRegiao;


public interface IMicroRegiaoRepository extends Serializable{

	List<MicroRegiao> getAllMicroRegiao();

	MicroRegiao getMicroRegiaoById(Long id);

	List<MicroRegiao> getMicroRegiaoByMacroRegiaoId(Long id);

	



	
}