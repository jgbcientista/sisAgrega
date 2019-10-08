package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.IFornecedor;
import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.model.imp.Fornecedor;
import br.com.sysagrega.repository.IFornecedorRepository;
import br.com.sysagrega.service.IFornecedorService;
import br.com.sysagrega.util.cdi.Transactional;
import br.com.sysagrega.util.jsf.FacesUtil;

public class FornecedorService implements IFornecedorService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IFornecedorRepository fornecedorRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IFornecedorService#salvar(br.com.sysagrega
	 * .model.imp.Fornecedor)
	 */
	
	@Override
	@Transactional
	public IFornecedor salvar(IFornecedor fornecedor) {

		IFornecedor fornecedorCriado = new Fornecedor();
		
		try {
			// Varifica se o Fornecedor j� est� cadastrado no sistema
			IFornecedor fornecedorExistente = this.fornecedorRepository.getFornecedorByCnpj(fornecedor.getCnpjCPF());
			
			if (fornecedorExistente != null) {
				throw new NegocioException("Fornecedor j� cadastrado no sistema!");
			}
			fornecedorCriado = this.fornecedorRepository.saveOrMerge(fornecedor);
		
		} catch (Exception e) {
			
			throw new NegocioException("Erro de processamento com banco de dados(Fornecedor)!");
		}
		return fornecedorCriado;
	}
	
	/**
	 * Realiza a valida��o se os campos obrigat�rios foram preenchidos Nome,
	 * CPF, RG, Data Nascimento
	 * 
	 * @param Fornecedor
	 * @return true (Se os campos obrigatórios foram preenchidos) ou false
	 * @author Elton
	 */

	/**
	 * Retorna a lista de todos os Fornecedores do sistema para a tela de
	 * consulta
	 * 
	 * @return List<Fornecedor>
	 * @author Paulo
	 */
	@Override
	public List<Fornecedor> getAllFornecedor() {

		return fornecedorRepository.getAllFornecedor();

	}
	
	/**
	 * M�todo atualizar o objeto Fornecedor e retorna o mesmo atualizado
	 * 
	 * @param fornecedor
	 * @return fornecedor
	 * @author Paulo
	 */
	@Override
	@Transactional
	public IFornecedor atualizarFornecedor(IFornecedor fornecedor) {

		// Valida��o para campos obrigat�rios
		IFornecedor fornecedorAtual = this.fornecedorRepository.saveOrMerge(fornecedor);

		return fornecedorAtual;

	}
	
	/**
	 * M�todo exclui um objeto fornecedor
	 * 
	 * @param fornecedor
	 * @author Paulo
	 */
	@Override
	@Transactional
	public void excluirFornecedor(IFornecedor fornecedor) {

		if (fornecedor != null) {

			this.fornecedorRepository.remover(fornecedor);

		} else {

			FacesUtil.addErrorMessage("Favor selecionar um fornecedor!");

		}

	}
	
	/**
	 * M�todo realizar a busca de um objeto fornecedor, 
	 * conforme o filtro informado na tela de consulta.
	 * Se os filtros n�o forem informados o m�todo retorna todos os fornecedor
	 * 
	 * @param cnpj
	 * @param nomeFantasia
	 * @author Paulo
	 * 
	 */
	
	@Override
	public List<Fornecedor> getFornecedorByFilter(String nomeFantasia, String ramoAtividade, String principalAtividade) {
		
		return this.fornecedorRepository.getFornecedorByFilter(nomeFantasia, ramoAtividade, principalAtividade);
		
	}
	
	@Override
	public Fornecedor getFornecedorByCnpj(String cnpjCPF){
		
		return (Fornecedor) this.fornecedorRepository.getFornecedorByCnpj(cnpjCPF);
	}



	

}
