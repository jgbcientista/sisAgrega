package br.com.sysagrega.service.imp;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.com.sysagrega.model.ICustoBdiComissao;
import br.com.sysagrega.model.imp.CustoBdiComissao;
import br.com.sysagrega.repository.ICustoBDIComissaoRepository;
import br.com.sysagrega.service.ICustoBDIComissaoService;
import br.com.sysagrega.util.cdi.Transactional;

public class CustoBDIComissaoService implements ICustoBDIComissaoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICustoBDIComissaoRepository iCustoBDIComissaoRepository;
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getAllBancos()
	 */
	@Override
	@Transactional
	public Set<CustoBdiComissao> getAll() {
		return this.iCustoBDIComissaoRepository.getAll();
		
	}
	public  List<CustoBdiComissao> getByPropostaId(Long idProposta) {
		return this.iCustoBDIComissaoRepository.getByPropostaId(idProposta);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getBancoById(java.lang.Long)
	 */
	@Override
	@Transactional
	public CustoBdiComissao getById(Long id) {
		return this.iCustoBDIComissaoRepository.getById(id);
		
	}

	@Override
	@Transactional
	public void salvar(ICustoBdiComissao custo) {

		try {
			this.iCustoBDIComissaoRepository.saveOrMerge(custo);

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}

	}
	@Override
	@Transactional
	public void excluir(ICustoBdiComissao custo) {
		if (custo != null) {

			this.iCustoBDIComissaoRepository.remover(custo);

		} else {

			throw new NegocioException("Favor selecionar um cliente!");

		}

	}

}
