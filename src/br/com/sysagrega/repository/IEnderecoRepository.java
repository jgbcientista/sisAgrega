package br.com.sysagrega.repository;

import java.io.Serializable;

import br.com.sysagrega.model.IEndereco;
import br.com.sysagrega.model.imp.Endereco;

public interface IEnderecoRepository extends Serializable{

	IEndereco salvar(IEndereco item);

	void excluir(IEndereco item);

	Endereco getById(Long id);


}