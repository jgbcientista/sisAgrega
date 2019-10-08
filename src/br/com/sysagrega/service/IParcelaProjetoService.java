package br.com.sysagrega.service;

import java.io.Serializable;
 

import java.util.List;

import br.com.sysagrega.model.IParcelaProjeto;
import br.com.sysagrega.model.imp.ParcelaProjeto;

public interface IParcelaProjetoService extends Serializable{

	List<ParcelaProjeto> getAll();

	ParcelaProjeto getById(Long id);

	IParcelaProjeto getByProjeto(Long idProjeto);

	IParcelaProjeto salvar(IParcelaProjeto item);

		
	

}