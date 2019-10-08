package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.ISituacao;
import br.com.sysagrega.model.imp.Situacao;
import br.com.sysagrega.repository.imp.SituacaoRepository;
import br.com.sysagrega.service.ISituacaoService;

public class SituacaoService implements ISituacaoService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SituacaoRepository situacaoRepository;

	@Override
	public Situacao getById(Long id) {
		return this.situacaoRepository.getById(id);
	}
	
	@Override
	public List<Situacao> getAll() {
		return this.situacaoRepository.getAll();
	}
	
} 
