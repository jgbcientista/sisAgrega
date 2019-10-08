package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Cidade;

public interface ICidadeRepository extends Serializable{

	List<Cidade> getAllCidades();

	Cidade getCidadeById(Long id);

	List<Cidade> getCidadesByEstadoId(Long id);

	List<Cidade> getCidadesByEstado(String uf);

}