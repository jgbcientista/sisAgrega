package br.com.sysagrega.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.sysagrega.model.ICustoOperacional;
import br.com.sysagrega.model.imp.CustoOperacional;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.repository.ICustoOperacionalRepository;
import br.com.sysagrega.service.ICustoOperacionalService;
import br.com.sysagrega.util.cdi.Transactional;

public class CustoOperacionalService implements ICustoOperacionalService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICustoOperacionalRepository custoOperacionalRepository;
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getAllBancos()
	 */
	@Override
	@Transactional
	public Set<CustoOperacional> getAll() {
		return this.custoOperacionalRepository.getAll();
		
	}
	
	public  List<CustoOperacional> getByPropostaId(Long idProposta) {
		return this.custoOperacionalRepository.getByPropostaId(idProposta);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getBancoById(java.lang.Long)
	 */
	@Override
	@Transactional
	public CustoOperacional getById(Long id) {
		return this.custoOperacionalRepository.getById(id);
		
	}

	@Override
	@Transactional
	public void salvar(ICustoOperacional custo) {

		try {
			this.custoOperacionalRepository.saveOrMerge(custo);

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}

	}
	@Override
	@Transactional
	public void excluir(ICustoOperacional custo) {
		if (custo != null) {

			this.custoOperacionalRepository.remover(custo);

		} else {

			throw new NegocioException("Favor selecionar um cliente!");

		}

	}
	
	@Override
	@Transactional
	public List<CustoOperacional> salvarCopiaCustoOperacional(List<CustoOperacional> lisCustoOperacioanl, Proposta proposta) {
		List<CustoOperacional> list = new ArrayList<>();
		try {
			CustoOperacional itemCusto = new CustoOperacional();
			for (CustoOperacional custo : lisCustoOperacioanl) {
				itemCusto = new CustoOperacional();
				itemCusto.setDescricao(custo.getDescricao());
				itemCusto.setValorUnitario(custo.getValorUnitario());
				itemCusto.setQuantidade(custo.getQuantidade());
				itemCusto.setObservacoes(custo.getObservacoes());
				itemCusto.setValorTotal(custo.getValorTotal());
				itemCusto.setProposta(proposta);
				itemCusto.setTpUnidadeMedida(custo.getTpUnidadeMedida());
				itemCusto.setAtivo(true);
				itemCusto = (CustoOperacional) this.custoOperacionalRepository.saveOrMerge(itemCusto);
				list.add(itemCusto);
			}
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar Custo de Operacional. Erro :"+e);
		}
		
		return list;
	}

}
