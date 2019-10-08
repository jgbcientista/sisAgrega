package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.ITipoPrograma;
import br.com.sysagrega.model.imp.TipoPrograma;
import br.com.sysagrega.repository.imp.TipoProgramaRepository;
import br.com.sysagrega.service.ITipoProgramaService;
import br.com.sysagrega.util.cdi.Transactional;

public class TipoProgramaService implements ITipoProgramaService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoProgramaRepository repository;

	@Override
	@Transactional
	public void remover(ITipoPrograma item) {
		if (item != null) {
			this.repository.remover(item);
		} else {
			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	public TipoPrograma getById(Long id) {
		return this.repository.getById(id);
	}
	
	@Override
	public List<TipoPrograma> getAll() {
		return this.repository.getAll();
	}
	
	@Override
	@Transactional
	public ITipoPrograma salvar(ITipoPrograma item) {
		try {
			item = this.repository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados: " +  e.getMessage());

		}
		return item; 
	}
	 
 } 
