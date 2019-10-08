package br.com.sysagrega.service.imp;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.CustoExecucaoHistorico;
import br.com.sysagrega.repository.ICustoExecucaoHistoricoRepository;
import br.com.sysagrega.service.ICustoExecucaoHistoricoService;
import br.com.sysagrega.util.cdi.Transactional;

public class CustoExecucaoHistoricoService implements ICustoExecucaoHistoricoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICustoExecucaoHistoricoRepository iCustoExecucaoHistoricoRepository;
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getAllBancos()
	 */
	@Override
	@Transactional
	public Set<CustoExecucaoHistorico> getAllCustos() {
		return this.iCustoExecucaoHistoricoRepository.getAllCustos();
		
	}
	
	public  List<CustoExecucaoHistorico> getByPropostaHistoricoId(Long idPropostaHistorico) {
		return this.iCustoExecucaoHistoricoRepository.getByPropostaHistoricoId(idPropostaHistorico);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getBancoById(java.lang.Long)
	 */
	@Override
	@Transactional
	public CustoExecucaoHistorico getCustoExecucaoHistoricoById(Long id) {
		
		return this.iCustoExecucaoHistoricoRepository.getCustoExecucaoHistoricoById(id);
		
	}

	@Override
	@Transactional
	public CustoExecucaoHistorico salvar(CustoExecucaoHistorico custoExecucaoHistorico) {

		try {
			custoExecucaoHistorico = this.iCustoExecucaoHistoricoRepository.saveOrMerge(custoExecucaoHistorico);

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return custoExecucaoHistorico;

	}
	@Override
	@Transactional
	public CustoExecucaoHistorico excluir(CustoExecucaoHistorico custoExecucaoHistorico) {
		if (custoExecucaoHistorico != null) {

			custoExecucaoHistorico = this.iCustoExecucaoHistoricoRepository.remover(custoExecucaoHistorico);

		} else {
			throw new NegocioException("Favor selecionar um custoExecucao!");

		}
		return custoExecucaoHistorico;

	}

}
