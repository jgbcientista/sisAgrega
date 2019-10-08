package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ITrajetoRede;
import br.com.sysagrega.model.imp.TrajetoRede;

public interface ITrajetoRedeRepository extends Serializable{

	List<TrajetoRede> getAll();

	TrajetoRede getById(Long id);

	List<TrajetoRede> getByProjeto(Long idProjeto);

	void remover(ITrajetoRede item);

	TrajetoRede salvar(TrajetoRede item);

	TrajetoRede obterMaiorTrajeto(Long projeto);


	
}