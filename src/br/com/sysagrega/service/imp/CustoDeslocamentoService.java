package br.com.sysagrega.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.sysagrega.model.ICustoDeslocamento;
import br.com.sysagrega.model.imp.CustoDeslocamento;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.repository.ICustoDeslocamentoRepository;
import br.com.sysagrega.service.ICustoDeslocamentoService;
import br.com.sysagrega.util.cdi.Transactional;

public class CustoDeslocamentoService implements ICustoDeslocamentoService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICustoDeslocamentoRepository iCustoDeslocamentoRepository;
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getAllBancos()
	 */
	@Override
	@Transactional
	public Set<CustoDeslocamento> getAll() {
		return this.iCustoDeslocamentoRepository.getAll();
		
	}
	
	public  List<CustoDeslocamento> getByPropostaId(Long idProposta) {
		return this.iCustoDeslocamentoRepository.getByPropostaId(idProposta);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getBancoById(java.lang.Long)
	 */
	@Override
	@Transactional
	public CustoDeslocamento getById(Long id) {
		
		return this.iCustoDeslocamentoRepository.getById(id);
		
	}

	@Override
	@Transactional
	public void salvar(ICustoDeslocamento custo) {

		try {
			this.iCustoDeslocamentoRepository.saveOrMerge(custo);

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}

	}
	@Override
	@Transactional
	public void excluir(ICustoDeslocamento custo) {
		if (custo != null) {

			this.iCustoDeslocamentoRepository.remover(custo);

		} else {

			throw new NegocioException("Favor selecionar um cliente!");

		}
	}
	
	@Override
	@Transactional
	public List<CustoDeslocamento> salvarCopiaCustoDeslocamento(List<CustoDeslocamento> lisCustoDeslocamento, Proposta proposta) {
		List<CustoDeslocamento> list = new ArrayList<>();
		try {
			CustoDeslocamento itemCusto = new CustoDeslocamento();
			for (CustoDeslocamento custo : lisCustoDeslocamento) {
				itemCusto = new CustoDeslocamento();
				itemCusto.setDescricao(custo.getDescricao());
				itemCusto.setValorUnitario(custo.getValorUnitario());
				itemCusto.setQuantidade(custo.getQuantidade());
				itemCusto.setObservacoes(custo.getObservacoes());
				itemCusto.setValorTotal(custo.getValorTotal());
				itemCusto.setProposta(proposta);
				itemCusto.setTpUnidadeMedida(custo.getTpUnidadeMedida());
				itemCusto.setAtivo(true);
				itemCusto = (CustoDeslocamento) this.iCustoDeslocamentoRepository.saveOrMerge(itemCusto);
				list.add(itemCusto);
			}
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar Custo de Deslocamento. Erro :"+e);
		}
		
		return list;
	}

}
