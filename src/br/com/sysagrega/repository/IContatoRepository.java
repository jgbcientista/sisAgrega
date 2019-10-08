package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IContato;
import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.model.imp.Contato;

public interface IContatoRepository extends Serializable{

	IContato getContatoById(Long id);

	IContato getContatoByCliente(Long idCliente);

	List<Contato> getContatoByAtivo();

	IContato saveOrMerge(IContato contato);

	void remover(IContato contato);

	List<Contato> getAllContato();

	List<Contato> getAllContatos();

	List<Contato> getContatoByFilter(Long idCliente);

	List<Contato> getByClienteId(Long idCliente);

	List<Contato> getByFornecedorId(Long idFornecedor);

	


	



}