package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.imp.Cliente;

public interface IClienteRepository extends Serializable{

	ICliente getClienteById(Long id);

	List<Cliente> getClienteByFilter(String nome, String seguimento,String tipoCliente);

	ICliente saveOrMerge(ICliente cliente);

	void remover(ICliente cliente);

	List<Cliente> getAllClientes();

	List<Cliente> getClienteByFilterNome(String nome);

	List<Cliente> getClienteByAtivo();

	ICliente getClienteByCnpj(String cnpjCPF, Boolean situacao);

}