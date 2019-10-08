package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IDistancia;
import br.com.sysagrega.model.imp.Distancia;
import br.com.sysagrega.repository.imp.DistanciaRepository;
import br.com.sysagrega.service.IDistanciaService;
import br.com.sysagrega.util.cdi.Transactional;

public class DistanciaService implements IDistanciaService {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DistanciaRepository distanciaRepository;

	@Override
	@Transactional
	public void remover(IDistancia item) {
		if (item != null) {
			// Removendo
			this.distanciaRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public IDistancia atualizar(IDistancia item) {
		try {
			item = this.distanciaRepository.salvar(item);
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");
		}
		return item;
	}

	@Override
	public Distancia getById(Long id) {
		return this.distanciaRepository.getById(id);
	}

	@Override
	public List<Distancia> getAll() {
		return this.distanciaRepository.getAll();
	}
	
	@Override
	public List<Distancia> getByPlanilhao(Long id) {
		return this.distanciaRepository.getByPlanilhao(id);
	}


	@Override
	@Transactional
	public void salvar(IDistancia item) {
		try {
			 
			item = this.distanciaRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados: " +e.getMessage());

		}
		
	}

	 
} 
