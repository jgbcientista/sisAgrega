package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IContato;
import br.com.sysagrega.model.imp.Contato;

public interface IContatoService extends Serializable{

	void salvar(IContato contato);

	List<Contato> getAllContatos();

	IContato atualizarContato(IContato contato);

	void excluirContato(IContato contato);

	List<Contato> getContatoByAtivo();

	Contato getContatoById(Long id);

	List<Contato> getClienteByFilter(Long idCliente);

	List<Contato> getByClienteId(Long idCliente);

	List<Contato> getByFornecedorId(Long idFornecedor);




}