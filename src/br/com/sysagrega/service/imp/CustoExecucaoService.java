package br.com.sysagrega.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.CustoExecucao;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.repository.ICustoExecucaoRepository;
import br.com.sysagrega.service.ICustoExecucaoService;
import br.com.sysagrega.util.cdi.Transactional;

public class CustoExecucaoService implements ICustoExecucaoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICustoExecucaoRepository iCustoExecucaoRepository;
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getAllBancos()
	 */
	@Override
	@Transactional
	public Set<CustoExecucao> getAllCustos() {
		return this.iCustoExecucaoRepository.getAllCustos();
		
	}
	
	public  List<CustoExecucao> getByPropostaId(Long idProposta) {
		return this.iCustoExecucaoRepository.getByPropostaId(idProposta);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getBancoById(java.lang.Long)
	 */
	@Override
	@Transactional
	public CustoExecucao getCustoExecucaoById(Long id) {
		
		return this.iCustoExecucaoRepository.getCustoExecucaoById(id);
		
	}

	@Override
	@Transactional
	public CustoExecucao salvar(CustoExecucao custoExecucao) {

		try {
			custoExecucao = this.iCustoExecucaoRepository.saveOrMerge(custoExecucao);

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return custoExecucao;

	}
	@Override
	@Transactional
	public CustoExecucao excluir(CustoExecucao custoExecucao) {
		if (custoExecucao != null) {

			custoExecucao = this.iCustoExecucaoRepository.remover(custoExecucao);

		} else {
			throw new NegocioException("Favor selecionar um custoExecucao!");

		}
		return custoExecucao;

	}
	
	@Override
	@Transactional
	public List<CustoExecucao> salvarCopiaExecucao(List<CustoExecucao> lisCustoExecucao, Proposta proposta) {
		List<CustoExecucao> list = new ArrayList<>();
		try {
			CustoExecucao itemCusto = new CustoExecucao();
			for (CustoExecucao custo : lisCustoExecucao) {
				itemCusto = new CustoExecucao();
				itemCusto.setDescricao(custo.getDescricao());
				itemCusto.setValorUnitario(custo.getValorUnitario());
				itemCusto.setQuantidade(custo.getQuantidade());
				itemCusto.setObservacoes(custo.getObservacoes());
				itemCusto.setValorTotal(custo.getValorTotal());
				itemCusto.setProposta(proposta);
				itemCusto.setTpUnidadeMedida(custo.getTpUnidadeMedida());
				itemCusto.setAtivo(true);
				itemCusto = this.iCustoExecucaoRepository.saveOrMerge(itemCusto);
				list.add(itemCusto);
			}
			
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar Custo de Execução. Erro :"+e);
		}
		return list;
	}

}
