package br.com.sysagrega.service.imp;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.sysagrega.model.ICustoSegurancaHistorico;
import br.com.sysagrega.model.imp.CustoSegurancaHistorico;
import br.com.sysagrega.repository.ICustoSegurancaHistoricoRepository;
import br.com.sysagrega.service.ICustoSegurancaHistoricoService;
import br.com.sysagrega.util.cdi.Transactional;

public class CustoSegurancaHistoricoService implements ICustoSegurancaHistoricoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICustoSegurancaHistoricoRepository iCustoSegurancaHistoricoRepository;
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getAllBancos()
	 */
	@Override
	@Transactional
	public Set<CustoSegurancaHistorico> getAll() {
		return this.iCustoSegurancaHistoricoRepository.getAll();
		
	}
	
	public  List<CustoSegurancaHistorico> getByPropostaHistoricoId(Long idPropostaHistorico) {
		return this.iCustoSegurancaHistoricoRepository.getByPropostaHistoricoId(idPropostaHistorico);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getBancoById(java.lang.Long)
	 */
	@Override
	@Transactional
	public CustoSegurancaHistorico getById(Long id) {
		return this.iCustoSegurancaHistoricoRepository.getById(id);
		
	}

	@Override
	@Transactional
	public void salvar(ICustoSegurancaHistorico custo) {

		try {
			this.iCustoSegurancaHistoricoRepository.saveOrMerge(custo);

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
	}
	
	@Override
	@Transactional
	public void excluir(ICustoSegurancaHistorico custo) {
		if (custo != null) {

			this.iCustoSegurancaHistoricoRepository.remover(custo);

		} else {

			throw new NegocioException("Favor selecionar um cliente!");

		}

	}

}
