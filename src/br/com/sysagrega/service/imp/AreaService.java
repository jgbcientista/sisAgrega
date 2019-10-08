package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IArea;
import br.com.sysagrega.model.imp.Area;
import br.com.sysagrega.repository.imp.AreaRepository;
import br.com.sysagrega.service.IAreaService;
import br.com.sysagrega.util.cdi.Transactional;

public class AreaService implements IAreaService {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AreaRepository areaRepository;

	@Override
	@Transactional
	public void remover(IArea area) {
		if (area != null) {
			// Removendo
			this.areaRepository.remover(area);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public IArea atualizar(IArea tipoServico) {
		try {
			 
			tipoServico = this.areaRepository.salvar(tipoServico);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return tipoServico;
	}

	@Override
	public IArea getAreaById(Long id) {
		return this.areaRepository.getAreaById(id);
	}

	@Override
	public List<Area> getAllAreas() {
		return this.areaRepository.getAllAreas();
	}


	@Override
	@Transactional
	public void salvar(IArea tipoServico) {
		try {
			 
			tipoServico = this.areaRepository.salvar(tipoServico);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		
	}

	 
} 
