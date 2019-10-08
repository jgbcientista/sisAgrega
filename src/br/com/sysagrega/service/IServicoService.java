package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IServico;
import br.com.sysagrega.model.imp.Servico;

public interface IServicoService extends Serializable{

	void salvar(IServico servico);

	/**
	 * Retorna a lista de todos os Servicos do sistema para a tela de
	 * consulta
	 * 
	 * @return List<servico>
	 */
	List<Servico> getAllServicos();

	/**
	 * M�todo atualizar o objeto cliente e retorna o mesmo atualizado
	 * 
	 */
	IServico atualizarServico(IServico servico);

	/**
	 * M�todo exclui um objeto cliente
	 * 
	 * @param cliente
	 */
	void excluirServico(IServico servico);

	/**
	 * conforme o filtro informado na tela de consulta.
	 * Se os filtros n�o forem informados o m�todo retorna todos os clientes
	 * 
	 * @param cnpj
	 * @param situacao
	 * @param seguimento
	 * 
	 */
	List<Servico> getServicoByFilter(Long tipo, Long area, String descricao);

	Servico getServicoById(Long id);

}