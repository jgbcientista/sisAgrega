package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.Aditivo;
import br.com.sysagrega.repository.IAditivo;
import br.com.sysagrega.repository.imp.AditivoRepository;
import br.com.sysagrega.service.IAditivoService;
import br.com.sysagrega.util.cdi.Transactional;

public class AditivoService implements IAditivoService {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AditivoRepository itemRepository;

	@Override
	@Transactional
	public void remover(IAditivo item) {
		if (item != null) {
			// Removendo
			this.itemRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public IAditivo atualizar(IAditivo tipoServico) {
		try {
			 
			tipoServico = this.itemRepository.salvar(tipoServico);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return tipoServico;
	}

	@Override
	public Aditivo getById(Long id) {
		return this.itemRepository.getById(id);
	}


	@Override
	@Transactional
	public IAditivo salvar(IAditivo item) {
		try {
			 
			item = this.itemRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados: " + e.getMessage());

		}
		return item; 
		
	}

	@Override
	public List<Aditivo> getByContrato(Aditivo filtro) {
		return 	this.itemRepository.getByContrato(filtro);
	}

	 
} 
