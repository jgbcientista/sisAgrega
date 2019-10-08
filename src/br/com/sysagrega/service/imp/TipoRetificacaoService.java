package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.TipoRetificacao;
import br.com.sysagrega.repository.imp.TipoRetificacaoRepository;
import br.com.sysagrega.service.ITipoRetificacaoService;

public class TipoRetificacaoService implements ITipoRetificacaoService{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoRetificacaoRepository tipoRetificacaoRepository;
  

	@Override
	public TipoRetificacao getById(Long id) {
		return this.tipoRetificacaoRepository.getById(id);
	}

	@Override
	public List<TipoRetificacao> getAll() {
		return this.tipoRetificacaoRepository.getAll();
	}

	 
} 
