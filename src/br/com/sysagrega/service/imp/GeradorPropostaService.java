package br.com.sysagrega.service.imp;

import javax.inject.Inject;

import br.com.sysagrega.model.IGeradorProposta;
import br.com.sysagrega.model.imp.GeradorProposta;
import br.com.sysagrega.repository.IGeradorPropostaRepository;
import br.com.sysagrega.service.IGeradorPropostaService;
import br.com.sysagrega.util.cdi.Transactional;

public class GeradorPropostaService implements IGeradorPropostaService  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IGeradorPropostaRepository geradorPropostaRepository;

	@Override
	@Transactional
	public void salvar(IGeradorProposta geradorProposta) {
		try {
			this.geradorPropostaRepository.saveOrMerge(geradorProposta);
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");
		}
	}
	
	@Override
	public synchronized GeradorProposta obterMaiorID() {
		return this.geradorPropostaRepository.obterMaiorID();
	}
	
	@Override
	public void remover(IGeradorProposta geradorProposta) {
		this.geradorPropostaRepository.remover(geradorProposta);
	}
		
	
	
	
	
}
