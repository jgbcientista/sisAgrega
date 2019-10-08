package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Banco;

public interface IBancoRepository extends Serializable{

	List<Banco> getAllBancos();

	Banco getBancoById(Long id);

}