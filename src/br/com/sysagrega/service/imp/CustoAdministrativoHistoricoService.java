package br.com.sysagrega.service.imp;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.sysagrega.model.ICustoAdministrativoHistorico;
import br.com.sysagrega.model.imp.CustoAdministrativoHistorico;
import br.com.sysagrega.repository.ICustoAdministrativoHistoricoRepository;
import br.com.sysagrega.service.ICustoAdministrativoHistoricoService;
import br.com.sysagrega.util.cdi.Transactional;

public class CustoAdministrativoHistoricoService implements ICustoAdministrativoHistoricoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICustoAdministrativoHistoricoRepository iCustoAdministrativoHistoricoRepository;
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getAllBancos()
	 */
	@Override
	@Transactional
	public Set<CustoAdministrativoHistorico> getAll() {
		return this.iCustoAdministrativoHistoricoRepository.getAll();
		
	}
	
	public  List<CustoAdministrativoHistorico> getByPropostaHistoricoId(Long idPropostaHistorico) {
		return this.iCustoAdministrativoHistoricoRepository.getByPropostaId(idPropostaHistorico);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getBancoById(java.lang.Long)
	 */
	@Override
	@Transactional
	public CustoAdministrativoHistorico getById(Long id) {
		return this.iCustoAdministrativoHistoricoRepository.getById(id);
		
	}

	@Override
	@Transactional
	public void salvar(ICustoAdministrativoHistorico custoHistorico) {

		try {
			this.iCustoAdministrativoHistoricoRepository.saveOrMerge(custoHistorico);

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}

	}
	@Override
	@Transactional
	public void excluir(ICustoAdministrativoHistorico custoHistorico) {
		if (custoHistorico != null) {

			this.iCustoAdministrativoHistoricoRepository.remover(custoHistorico);

		} else {

			throw new NegocioException("Favor selecionar um Custo Administrativo!");

		}

	}

}
