package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.MicroRegiao;
import br.com.sysagrega.repository.imp.MicroRegiaoRepository;
import br.com.sysagrega.service.IMicroRegiaoService;
import br.com.sysagrega.util.cdi.Transactional;

public class MicroRegiaoService implements IMicroRegiaoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MicroRegiaoRepository microRegiaoRepository;
	
	
	@Override
	@Transactional
	public List<MicroRegiao> getAllMicroRegiao() {
		
		return this.microRegiaoRepository.getAllMicroRegiao();
		
	}
	

	@Override
	@Transactional
	public MicroRegiao getMicroRegiaoById(Long id) {
		
		return this.microRegiaoRepository.getMicroRegiaoById(id);
		
	}
	
	@Override
	public List<MicroRegiao> getMicroRegiaoByMacroRegiaoId(Long id) {
		
		return this.microRegiaoRepository.getMicroRegiaoByMacroRegiaoId(id);
		
	}

}
