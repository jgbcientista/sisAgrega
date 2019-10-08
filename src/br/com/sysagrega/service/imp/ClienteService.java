package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.repository.IClienteRepository;
import br.com.sysagrega.service.IClienteService;
import br.com.sysagrega.util.cdi.Transactional;

public class ClienteService implements IClienteService  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IClienteRepository clienteRepository;


	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IClienteService#salvar(br.com.sysagrega.model.ICliente)
	 */
	@Override
	@Transactional
	
	public ICliente salvar(ICliente cliente) {
		
		ICliente clienteCriado = new Cliente();
		
		try {
			
			// Validação para campos obrigatórios
			validaCamposObrigatorios(cliente);

			// Varifica se o profissional já está cadastrado no sistema
			ICliente clienteExistente = this.clienteRepository.getClienteByCnpj(cliente.getCnpjCPF(), true);

			if (clienteExistente != null) {
				throw new NegocioException("Cliente já cadastrado no sistema!");
			}

			clienteCriado = this.clienteRepository.saveOrMerge(cliente);
			
		} catch (Exception e) {
			
			throw new NegocioException("Erro de processamento com banco de dados (Cliente)!");
		}
		
		return clienteCriado;
	}

	/**
	 * Realiza a validação se os campos obrigatórios foram preenchidos Nome,
	 * Seguimento, TipoCliente, Situacao
	 * 
	 * @param cliente
	 * @return true (Se os campos obrigatórios foram preenchidos) ou false
	 * @author Elton
	 */
	private void validaCamposObrigatorios(ICliente cliente) {

		if (cliente == null) {

			throw new NegocioException("Favor informar um cliente!");

		} else if (cliente.getNome().isEmpty()      || 
					cliente.getNome() == null       || 
					cliente.getSeguimento() == null ||
					cliente.getTipoCliente() == null ) {

			throw new NegocioException("Os Campos obrigatórios não foram informados!");

		}
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IClienteService#getAllClientes()
	 */
	@Override
	public List<Cliente> getAllClientes() {

		return clienteRepository.getAllClientes();

	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IClienteService#atualizarProfissional(br.com.sysagrega.model.ICliente)
	 */
	@Override
	@Transactional
	public ICliente atualizarCliente(ICliente cliente) {
		
		ICliente clienteAtual = new Cliente();

		try {
			
			// Validação para campos obrigatórios
			validaCamposObrigatorios(cliente);

			clienteAtual = this.clienteRepository.saveOrMerge(cliente);
			
		} catch (Exception e) {
			
			throw new NegocioException("Erro de processamento com banco de dados!");
			
		}
		
		return clienteAtual;

	}

	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IClienteService#excluirProfissional(br.com.sysagrega.model.ICliente)
	 */
	@Override
	@Transactional
	public void excluirCliente(ICliente cliente) {

		if (cliente != null) {

			this.clienteRepository.remover(cliente);

		} else {

			throw new NegocioException("Favor selecionar um cliente!");

		}

	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IClienteService#getProfissionalByFilter(java.lang.String, br.com.sysagrega.model.Enums.TipoSituacaoCliente, br.com.sysagrega.model.Enums.TipoSeguimentoCliente, br.com.sysagrega.model.Enums.TipoCliente)
	 */
	@Override
	public List<Cliente> getClienteByAtivo() {
		return clienteRepository.getClienteByAtivo();
	}
	
	
	@Override
	public List<Cliente> getClienteByFilter(String nome, String seguimento,
			String tipoCliente) {
		
		return this.clienteRepository.getClienteByFilter(nome, seguimento, tipoCliente);
		
	}
	
	public List<Cliente> getClienteByFilterNome(String nome) {
		
		return this.clienteRepository.getClienteByFilterNome(nome);
		
	}
	
	@Override
	public Cliente getClienteByCnpj(String cnpjCPF, Boolean situacao){
		
		return (Cliente) this.clienteRepository.getClienteByCnpj(cnpjCPF, situacao);
	}
	
	
	

	@Override
	public Cliente getClienteById(Long id) {
		return (Cliente) this.clienteRepository.getClienteById(id);
	}

}
