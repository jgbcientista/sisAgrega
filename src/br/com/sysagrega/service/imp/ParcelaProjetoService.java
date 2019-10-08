package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IParcelaProjeto;
import br.com.sysagrega.model.imp.ParcelaProjeto;
import br.com.sysagrega.repository.imp.ParcelaProjetoRepository;
import br.com.sysagrega.service.IParcelaProjetoService;
import br.com.sysagrega.util.cdi.Transactional;

public class ParcelaProjetoService implements IParcelaProjetoService {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ParcelaProjetoRepository parcelaProjetoRepository;

	
	@Override
	public List<ParcelaProjeto> getAll() {
		return this.parcelaProjetoRepository.getAll();
	}
	
	@Override
	public ParcelaProjeto getById(Long id) {
		
		return this.parcelaProjetoRepository.getById(id);
	}
	
	@Override
	public IParcelaProjeto getByProjeto(Long idProjeto){
		return this.parcelaProjetoRepository.getByProjeto(idProjeto);
	}
	
	@Override
	@Transactional
	public IParcelaProjeto salvar(IParcelaProjeto item) {
		try {
			 
			item = this.parcelaProjetoRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return item; 
		
	}
	 
} 
