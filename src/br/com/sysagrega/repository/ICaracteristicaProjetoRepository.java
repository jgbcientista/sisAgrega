package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ICaracteristicaProjeto;
import br.com.sysagrega.model.imp.CaracteristicaProjeto;

public interface ICaracteristicaProjetoRepository extends Serializable {

	void remover(ICaracteristicaProjeto item);

	List<CaracteristicaProjeto> getAllCaracteristicaProjeto();

	CaracteristicaProjeto getById(Long id);

	ICaracteristicaProjeto salvar(ICaracteristicaProjeto item);

	CaracteristicaProjeto getByProjeto(Long idProjeto);

	

}