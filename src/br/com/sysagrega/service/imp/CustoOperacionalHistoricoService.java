package br.com.sysagrega.service.imp;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.sysagrega.model.ICustoOperacionalHistorico;
import br.com.sysagrega.model.imp.CustoOperacionalHistorico;
import br.com.sysagrega.repository.ICustoOperacionalHistoricoRepository;
import br.com.sysagrega.service.ICustoOperacionalHistoricoService;
import br.com.sysagrega.util.cdi.Transactional;

public class CustoOperacionalHistoricoService implements ICustoOperacionalHistoricoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICustoOperacionalHistoricoRepository custoOperacionalHistoricoRepository;
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getAllBancos()
	 */
	@Override
	@Transactional
	public Set<CustoOperacionalHistorico> getAll() {
		return this.custoOperacionalHistoricoRepository.getAll();
		
	}
	
	public  List<CustoOperacionalHistorico> getByPropostaHistoricoId(Long idPropostaHistorico) {
		return this.custoOperacionalHistoricoRepository.getByPropostaHistoricoId(idPropostaHistorico);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getBancoById(java.lang.Long)
	 */
	@Override
	@Transactional
	public CustoOperacionalHistorico getById(Long id) {
		return this.custoOperacionalHistoricoRepository.getById(id);
		
	}

	@Override
	@Transactional
	public void salvar(ICustoOperacionalHistorico custo) {

		try {
			this.custoOperacionalHistoricoRepository.saveOrMerge(custo);

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}

	}
	@Override
	@Transactional
	public void excluir(ICustoOperacionalHistorico custo) {
		if (custo != null) {
			this.custoOperacionalHistoricoRepository.remover(custo);
		} else {
			throw new NegocioException("Favor selecionar um custo Operacioanl!");
		}

	}

}
