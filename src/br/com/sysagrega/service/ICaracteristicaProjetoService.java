package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ICaracteristicaProjeto;
import br.com.sysagrega.model.imp.CaracteristicaProjeto;

public interface ICaracteristicaProjetoService extends Serializable {

	
	List<CaracteristicaProjeto> getAllCaracteristicaProjeto();

	CaracteristicaProjeto getById(Long id);

	void remover(ICaracteristicaProjeto item);

	ICaracteristicaProjeto salvar(ICaracteristicaProjeto item);

	CaracteristicaProjeto getByProjeto(Long idProjeto);	

	

}