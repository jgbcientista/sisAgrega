package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IPlanejamento;
import br.com.sysagrega.model.imp.Planejamento;
import br.com.sysagrega.repository.imp.PlanejamentoRepository;
import br.com.sysagrega.service.IPlanejamentoService;
import br.com.sysagrega.util.cdi.Transactional;

public class PlanejamentoService implements IPlanejamentoService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PlanejamentoRepository planejamentoRepository;

	@Override
	@Transactional
	public void remover(IPlanejamento item) {
		if (item != null) {
			// Removendo
			this.planejamentoRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	public Planejamento getById(Long id) {
		return this.planejamentoRepository.getById(id);
	}
	@Override
	public Planejamento getByProjeto(Long idProjeto){
		return this.planejamentoRepository.getByProjeto(idProjeto);
	}
	@Override
	public List<Planejamento> getByFiltros(Planejamento filtro){
		return this.planejamentoRepository.getByFiltros(filtro);
	}
	
	@Override
	public List<Planejamento> getAll() {
		return this.planejamentoRepository.getAll();
	}
	
	@Override
	@Transactional
	public IPlanejamento salvar(IPlanejamento item) {
		try {
			 
			item = this.planejamentoRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return item; 
		
	}

	 
} 
