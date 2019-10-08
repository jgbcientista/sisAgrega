package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IVerificacaoProfissional;
import br.com.sysagrega.model.imp.VerificacaoProfissional;
import br.com.sysagrega.repository.imp.VerificacaoProfissionalRepository;
import br.com.sysagrega.service.IVerificacaoProfissionalService;
import br.com.sysagrega.util.cdi.Transactional;

public class VerificacaoProfissionalService implements IVerificacaoProfissionalService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private VerificacaoProfissionalRepository verificacaoProfissionalRepository;

	@Override
	@Transactional
	public void remover(IVerificacaoProfissional item) {
		if (item != null) {
			// Removendo
			this.verificacaoProfissionalRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	public VerificacaoProfissional getById(Long id) {
		return this.verificacaoProfissionalRepository.getById(id);
	}
	
	@Override
	public VerificacaoProfissional getByProjeto(Long idProjeto){
		return this.verificacaoProfissionalRepository.getByProjeto(idProjeto);
	}

	@Override
	public List<VerificacaoProfissional> getAll() {
		return this.verificacaoProfissionalRepository.getAll();
	}
	@Override
	public List<VerificacaoProfissional>  getByFiltros(VerificacaoProfissional filtro){
		return this.verificacaoProfissionalRepository.getByFiltros(filtro);
	}
	
	
	@Override
	@Transactional
	public IVerificacaoProfissional salvar(IVerificacaoProfissional item) {
		try {
			 
			item = this.verificacaoProfissionalRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados: " +  e.getMessage());

		}
		return item; 
		
	}

	 
} 
