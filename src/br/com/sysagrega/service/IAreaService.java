package br.com.sysagrega.service;

import java.io.Serializable;
 

import java.util.List;

import br.com.sysagrega.model.IArea;
import br.com.sysagrega.model.imp.Area;

public interface IAreaService extends Serializable{

	public void remover(IArea area);
	
	void salvar(IArea area);
	
	public IArea getAreaById(Long id);
	
	public List<Area> getAllAreas() ;
	
	public IArea atualizar(IArea area);

}