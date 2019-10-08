package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.TipoNotificacao;
import br.com.sysagrega.repository.imp.TipoNotificacaoRepository;
import br.com.sysagrega.service.ITipoNotificacaoService;

public class TipoNotificacaoService implements ITipoNotificacaoService{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoNotificacaoRepository tipoNotificacaoRepository;
  

	@Override
	public TipoNotificacao getById(Long id) {
		return this.tipoNotificacaoRepository.getById(id);
	}

	@Override
	public List<TipoNotificacao> getAll() {
		return this.tipoNotificacaoRepository.getAll();
	}

	 
} 
