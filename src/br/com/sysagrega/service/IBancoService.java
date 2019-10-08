package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Banco;

public interface IBancoService extends Serializable{

	List<Banco> getAllBancos();

	Banco getBancoById(Long id);

}