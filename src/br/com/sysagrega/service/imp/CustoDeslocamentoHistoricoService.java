package br.com.sysagrega.service.imp;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.sysagrega.model.ICustoDeslocamentoHistorico;
import br.com.sysagrega.model.imp.CustoDeslocamentoHistorico;
import br.com.sysagrega.repository.ICustoDeslocamentoHistoricoRepository;
import br.com.sysagrega.service.ICustoDeslocamentoHistoricoService;
import br.com.sysagrega.util.cdi.Transactional;

public class CustoDeslocamentoHistoricoService implements ICustoDeslocamentoHistoricoService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICustoDeslocamentoHistoricoRepository iCustoDeslocamentoHistoricoRepository;
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getAllBancos()
	 */
	@Override
	@Transactional
	public Set<CustoDeslocamentoHistorico> getAll() {
		return this.iCustoDeslocamentoHistoricoRepository.getAll();
		
	}
	
	public  List<CustoDeslocamentoHistorico> getByPropostaHistoricoId(Long idPropostaHistorico) {
		return this.iCustoDeslocamentoHistoricoRepository.getByPropostaHistoricoId(idPropostaHistorico);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getBancoById(java.lang.Long)
	 */
	@Override
	@Transactional
	public CustoDeslocamentoHistorico getById(Long id) {
		
		return this.iCustoDeslocamentoHistoricoRepository.getById(id);
		
	}

	@Override
	@Transactional
	public void salvar(ICustoDeslocamentoHistorico custo) {

		try {
			this.iCustoDeslocamentoHistoricoRepository.saveOrMerge(custo);

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}

	}
	@Override
	@Transactional
	public void excluir(ICustoDeslocamentoHistorico custo) {
		if (custo != null) {

			this.iCustoDeslocamentoHistoricoRepository.remover(custo);

		} else {

			throw new NegocioException("Favor selecionar um cliente!");

		}

	}

}
