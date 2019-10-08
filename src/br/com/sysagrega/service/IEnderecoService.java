package br.com.sysagrega.service;

import java.io.Serializable;

import br.com.sysagrega.model.IEndereco;
import br.com.sysagrega.model.imp.Endereco;

public interface IEnderecoService extends Serializable{

	IEndereco salvar(IEndereco item);

	void excluir(IEndereco item);

	Endereco getById(Long id);



}