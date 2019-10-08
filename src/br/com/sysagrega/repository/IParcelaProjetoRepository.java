package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IParcelaProjeto;
import br.com.sysagrega.model.imp.ParcelaProjeto;


public interface IParcelaProjetoRepository extends Serializable{

	List<ParcelaProjeto> getAll();

	ParcelaProjeto getById(Long id);

	ParcelaProjeto getByProjeto(Long idProjeto);

	IParcelaProjeto salvar(IParcelaProjeto item);

	void remover(IParcelaProjeto item);



	
}