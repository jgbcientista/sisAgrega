package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IRegistro;
import br.com.sysagrega.model.imp.Registro;
import br.com.sysagrega.repository.imp.RegistroRepository;
import br.com.sysagrega.service.IRegistroService;
import br.com.sysagrega.util.cdi.Transactional;

public class RegistroService implements IRegistroService {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private RegistroRepository registroRepository;

	@Override
	@Transactional
	public void remover(IRegistro item) {
		if (item != null) {
			// Removendo
			this.registroRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public IRegistro atualizar(IRegistro item) {
		try {
			 
			item = this.registroRepository.salvar(item);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return item;
	}

	@Override
	public Registro getById(Long id) {
		return this.registroRepository.getById(id);
	}
	
	public List<Registro> getByFiltros(Registro filtro){
		return this.registroRepository.getByFiltros(filtro);	
	}


	@Override
	@Transactional
	public IRegistro salvar(IRegistro item) {
		try {
			 
			item = this.registroRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados: " + e.getMessage());

		}
		return item; 
		
	}

	@Override
	public List<Registro> getAll() {
		return this.registroRepository.getAll();
	}

	 
} 
