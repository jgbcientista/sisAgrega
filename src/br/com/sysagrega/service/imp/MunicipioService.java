package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.Municipio;
import br.com.sysagrega.repository.imp.MunicipioRepository;
import br.com.sysagrega.service.IMunicipioService;
import br.com.sysagrega.util.cdi.Transactional;

public class MunicipioService implements IMunicipioService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MunicipioRepository municipioRepository;
	
	
	@Override
	@Transactional
	public List<Municipio> getAllMunicipio() {
		
		return this.municipioRepository.getAllMunicipio();
		
	}
	

	@Override
	@Transactional
	public Municipio getMunicipioById(Long id) {
		
		return this.municipioRepository.getMunicipioById(id);
		
	}
	
	@Override
	public List<Municipio> getMunicipioByMicroRegiaoId(Long id) {
		
		return this.municipioRepository.getMunicipioByMicroRegiaoId(id);
		
	}
	
	@Override
	public List<Municipio> getMunicipioByMacroRegiaoId(Long id) {
		return this.municipioRepository.getMunicipioByMacroRegiaoId(id);
		
	}
	
	public List<Municipio> getMunicipioByEstado(Long id) {
		return this.municipioRepository.getMunicipioByEstado(id);
	}
	

}
