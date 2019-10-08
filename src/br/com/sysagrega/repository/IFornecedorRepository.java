package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IFornecedor;
import br.com.sysagrega.model.imp.Fornecedor;

public interface IFornecedorRepository extends Serializable{
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IFornecedorRepository#getFornecedorById(java.lang.Long)
	 */
	IFornecedor getFornecedorById(Long id);

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IFornecedorRepository#atualizar(br.com.sysagrega.model.imp.Fornecedor)
	 */
	IFornecedor saveOrMerge(IFornecedor fornecedor);

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IFornecedorRepository#remover(br.com.sysagrega.model.imp.Fornecedor)
	 */
	void remover(IFornecedor fornecedor);

	/* (non-Javadoc)
	 * @see br.com.sysagrega.repository.imp.IFornecedorRepository#getAllFornecedores()
	 */
	List<Fornecedor> getAllFornecedor();

	IFornecedor getFornecedorByCnpj(String cnpj);

	List<Fornecedor> getFornecedorByFilter(String nomeFantasia, String ramoAtividade, String principalAtividade);

}
