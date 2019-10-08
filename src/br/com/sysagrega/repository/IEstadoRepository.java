package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Estado;

public interface IEstadoRepository extends Serializable{

	List<Estado> getAllEstados();

	Estado getEstadoById(Long id);

}