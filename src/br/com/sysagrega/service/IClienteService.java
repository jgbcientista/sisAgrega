package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.imp.Cliente;

public interface IClienteService extends Serializable{

	ICliente salvar(ICliente cliente);

	/**
	 * Retorna a lista de todos os clientes do sistema para a tela de
	 * consulta
	 * 
	 * @return List<Cliente>
	 * @author Elton
	 */
	List<Cliente> getAllClientes();

	/**
	 * M�todo atualizar o objeto cliente e retorna o mesmo atualizado
	 * 
	 * @param cliente
	 * @return cliente
	 * @author Elton
	 */
	ICliente atualizarCliente(ICliente cliente);

	/**
	 * M�todo exclui um objeto cliente
	 * 
	 * @param cliente
	 * @author Elton
	 */
	void excluirCliente(ICliente cliente);

	/**
	 * M�todo realizar a busca de um objeto cliente, 
	 * conforme o filtro informado na tela de consulta.
	 * Se os filtros n�o forem informados o m�todo retorna todos os clientes
	 * 

	 * @param seguimento
	 * @param tipoCliente
	 * @author Elton
	 * 
	 */
	List<Cliente> getClienteByFilter(String nome, String seguimento,String tipoCliente);
	
	List<Cliente> getClienteByFilterNome(String nome);

	List<Cliente> getClienteByAtivo();
	
	public Cliente getClienteById(Long id);

	Cliente getClienteByCnpj(String cnpjCPF, Boolean situacao);
	
}