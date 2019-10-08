package br.com.sysagrega.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.sysagrega.model.ICustoSeguranca;
import br.com.sysagrega.model.imp.CustoSeguranca;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.repository.ICustoSegurancaRepository;
import br.com.sysagrega.service.ICustoSegurancaService;
import br.com.sysagrega.util.cdi.Transactional;

public class CustoSegurancaService implements ICustoSegurancaService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICustoSegurancaRepository iCustoSegurancaRepository;
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getAllBancos()
	 */
	@Override
	@Transactional
	public Set<CustoSeguranca> getAll() {
		return this.iCustoSegurancaRepository.getAll();
		
	}
	
	public  List<CustoSeguranca> getByPropostaId(Long idProposta) {
		return this.iCustoSegurancaRepository.getByPropostaId(idProposta);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getBancoById(java.lang.Long)
	 */
	@Override
	@Transactional
	public CustoSeguranca getById(Long id) {
		return this.iCustoSegurancaRepository.getById(id);
		
	}

	@Override
	@Transactional
	public void salvar(ICustoSeguranca custo) {

		try {
			this.iCustoSegurancaRepository.saveOrMerge(custo);

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}

	}
	@Override
	@Transactional
	public void excluir(ICustoSeguranca custo) {
		if (custo != null) {

			this.iCustoSegurancaRepository.remover(custo);

		} else {

			throw new NegocioException("Favor selecionar um cliente!");

		}
	}
	
	@Override
	@Transactional
	public List<CustoSeguranca> salvarCopiaCustoSeguranca(List<CustoSeguranca> lisCustoSeguranca, Proposta proposta) {
		List<CustoSeguranca> list = new ArrayList<>();
		try {
			CustoSeguranca itemCusto = new CustoSeguranca();
			for (CustoSeguranca custo : lisCustoSeguranca) {
				itemCusto = new CustoSeguranca();
				itemCusto.setDescricao(custo.getDescricao());
				itemCusto.setValorUnitario(custo.getValorUnitario());
				itemCusto.setQuantidade(custo.getQuantidade());
				itemCusto.setObservacoes(custo.getObservacoes());
				itemCusto.setValorTotal(custo.getValorTotal());
				itemCusto.setProposta(proposta);
				itemCusto.setTpUnidadeMedida(custo.getTpUnidadeMedida());
				itemCusto.setAtivo(true);
				itemCusto = (CustoSeguranca) this.iCustoSegurancaRepository.saveOrMerge(itemCusto);
				list.add(itemCusto);
			}
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar Custo de Segurança. Erro :"+e);
		}
		
		return list;
	}

}
