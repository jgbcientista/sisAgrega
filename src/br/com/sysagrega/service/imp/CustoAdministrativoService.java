package br.com.sysagrega.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.sysagrega.model.ICustoAdministrativo;
import br.com.sysagrega.model.imp.CustoAdministrativo;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.repository.ICustoAdministrativoRepository;
import br.com.sysagrega.service.ICustoAdministrativoService;
import br.com.sysagrega.util.cdi.Transactional;

public class CustoAdministrativoService implements ICustoAdministrativoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICustoAdministrativoRepository iCustoAdministrativoRepository;
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getAllBancos()
	 */
	@Override
	@Transactional
	public Set<CustoAdministrativo> getAll() {
		return this.iCustoAdministrativoRepository.getAll();
		
	}
	
	public  List<CustoAdministrativo> getByPropostaId(Long idProposta) {
		return this.iCustoAdministrativoRepository.getByPropostaId(idProposta);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getBancoById(java.lang.Long)
	 */
	@Override
	@Transactional
	public CustoAdministrativo getById(Long id) {
		return this.iCustoAdministrativoRepository.getById(id);
		
	}

	@Override
	@Transactional
	public void salvar(ICustoAdministrativo custo) {

		try {
			this.iCustoAdministrativoRepository.saveOrMerge(custo);

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}

	}
	@Override
	@Transactional
	public void excluir(ICustoAdministrativo custo) {
		if (custo != null) {

			this.iCustoAdministrativoRepository.remover(custo);

		} else {

			throw new NegocioException("Favor selecionar um cliente!");

		}

	}
	
	@Override
	@Transactional
	public List<CustoAdministrativo> salvarCopiaCustoAdm(List<CustoAdministrativo> lisCustoAdministrativo, Proposta proposta) {
		List<CustoAdministrativo> list = new ArrayList<>();
		try {
			CustoAdministrativo itemCusto = new CustoAdministrativo();
			for (CustoAdministrativo custo : lisCustoAdministrativo) {
				itemCusto = new CustoAdministrativo();
				itemCusto.setDescricao(custo.getDescricao());
				itemCusto.setValorUnitario(custo.getValorUnitario());
				itemCusto.setQuantidade(custo.getQuantidade());
				itemCusto.setObservacoes(custo.getObservacoes());
				itemCusto.setValorTotal(custo.getValorTotal());
				itemCusto.setProposta(proposta);
				itemCusto.setTpUnidadeMedida(custo.getTpUnidadeMedida());
				itemCusto.setAtivo(true);
				itemCusto = (CustoAdministrativo) this.iCustoAdministrativoRepository.saveOrMerge(itemCusto);
				list.add(itemCusto);
			}
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar Custo Administrativo. Erro :"+e);
		}
		
		return list;
	}

}
