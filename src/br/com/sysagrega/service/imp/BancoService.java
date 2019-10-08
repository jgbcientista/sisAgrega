package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.Banco;
import br.com.sysagrega.repository.imp.BancoRepository;
import br.com.sysagrega.service.IBancoService;
import br.com.sysagrega.util.cdi.Transactional;

public class BancoService implements IBancoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private BancoRepository bancoRepository;
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getAllBancos()
	 */
	@Override
	@Transactional
	public List<Banco> getAllBancos() {
		
		return this.bancoRepository.getAllBancos();
		
	}
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getBancoById(java.lang.Long)
	 */
	@Override
	@Transactional
	public Banco getBancoById(Long id) {
		
		return this.bancoRepository.getBancoById(id);
		
	}

}
