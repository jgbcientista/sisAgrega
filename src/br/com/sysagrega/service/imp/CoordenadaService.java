package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.Coordenada;
import br.com.sysagrega.repository.imp.CoordenadaRepository;
import br.com.sysagrega.service.ICoordenadaService;
import br.com.sysagrega.util.cdi.Transactional;

public class CoordenadaService implements ICoordenadaService {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CoordenadaRepository coordenadaRepository;

	
	@Override
	public List<Coordenada> getAll() {
		return this.coordenadaRepository.getAll();
	}
	
	@Override
	public Coordenada getById(Long id) {
		
		return this.coordenadaRepository.getById(id);
	}
	
	@Override
	public Coordenada obterMaiorOrdenacao(Long idProjeto) {
		return this.coordenadaRepository.obterMaiorOrdenacao(idProjeto);
	}
	
	@Override
	public List<Coordenada> getByProjeto(Long idProjeto){
		return this.coordenadaRepository.getByProjeto(idProjeto);
	}
	
	@Transactional
	public Coordenada salvar(Coordenada item) {
		try {
			 
			item = this.coordenadaRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados: " + e.getMessage());

		}
		return item; 
		
	}
	
	@Override
	@Transactional
	public void remover(Coordenada item) {
		if (item != null) {
			// Removendo
			this.coordenadaRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

} 
