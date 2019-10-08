package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.repository.imp.EstadoRepository;
import br.com.sysagrega.service.IEstadoService;
import br.com.sysagrega.util.cdi.Transactional;

public class EstadoService implements IEstadoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EstadoRepository estadoRepository;
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IEstadoService#getAllEstados()
	 */
	@Override
	@Transactional
	public List<Estado> getAllEstados() {
		
		return this.estadoRepository.getAllEstados();
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IEstadoService#getEstadoById(java.lang.Long)
	 */
	@Override
	@Transactional
	public Estado getEstadoById(Long id) {
		
		return this.estadoRepository.getEstadoById(id);
		
	}

}
