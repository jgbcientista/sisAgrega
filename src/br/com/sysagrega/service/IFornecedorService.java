package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IFornecedor;
import br.com.sysagrega.model.imp.Fornecedor;


public interface IFornecedorService extends Serializable {

	List<Fornecedor> getAllFornecedor();

	IFornecedor atualizarFornecedor(IFornecedor fornecedor);

	void excluirFornecedor(IFornecedor fornecedor);

	List<Fornecedor> getFornecedorByFilter(String nomeFantasia, String ramoAtividade, String principalAtividade);

	IFornecedor salvar(IFornecedor fornecedor);

	Fornecedor getFornecedorByCnpj(String cnpjCPF);

}
