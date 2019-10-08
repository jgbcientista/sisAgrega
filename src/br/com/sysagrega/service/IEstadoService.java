package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Estado;

public interface IEstadoService extends Serializable{

	List<Estado> getAllEstados();

	Estado getEstadoById(Long id);

}