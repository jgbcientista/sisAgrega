package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IArea;
import br.com.sysagrega.model.imp.Area;

public interface IAreaRepository extends Serializable{

	List<Area> getAllAreas();

	Area getAreaById(Long id);
	
	IArea salvar(IArea area);

	void remover(IArea area);
	
}