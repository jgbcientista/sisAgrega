package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.MacroRegiao;
import br.com.sysagrega.repository.imp.MacroRegiaoRepository;
import br.com.sysagrega.service.IMacroRegiaoService;
import br.com.sysagrega.util.cdi.Transactional;

public class MacroRegiaoService implements IMacroRegiaoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MacroRegiaoRepository macroRegiaoRepository;
	
	
	@Override
	@Transactional
	public List<MacroRegiao> getAllMacroRegiao() {
		
		return this.macroRegiaoRepository.getAllMacroRegiao();
		
	}
	

	@Override
	@Transactional
	public MacroRegiao getMacroRegiaoById(Long id) {
		
		return this.macroRegiaoRepository.getMacroRegiaoById(id);
		
	}
	
	@Override
	public List<MacroRegiao> getMacroRegiaoByEstadoId(Long id) {
		
		return this.macroRegiaoRepository.getMacroRegiaoByEstadoId(id);
	}


	@Override
	public List<MacroRegiao> getMacroRegiaoByCidade(Long id) {
		return this.macroRegiaoRepository.getMacroRegiaoByCidade(id);
	}

}
