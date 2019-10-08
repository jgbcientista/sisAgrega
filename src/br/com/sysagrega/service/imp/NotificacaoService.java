package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.INotificacao;
import br.com.sysagrega.model.imp.Notificacao;
import br.com.sysagrega.repository.imp.NotificacaoRepository;
import br.com.sysagrega.service.INotificacaoService;
import br.com.sysagrega.util.cdi.Transactional;

public class NotificacaoService implements INotificacaoService {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private NotificacaoRepository notificacaoRepository;

	@Override
	@Transactional
	public void remover(INotificacao item) {
		if (item != null) {
			// Removendo
			this.notificacaoRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public INotificacao atualizar(INotificacao item) {
		try {
			 
			item = this.notificacaoRepository.salvar(item);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return item;
	}

	@Override
	public Notificacao getById(Long id) {
		return this.notificacaoRepository.getById(id);
	}
	
	public List<Notificacao> getByFiltros(Notificacao filtro){
		return this.notificacaoRepository.getByFiltros(filtro);	
	}


	@Override
	@Transactional
	public INotificacao salvar(INotificacao item) {
		try {
			 
			item = this.notificacaoRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados: " + e.getMessage());

		}
		return item; 
		
	}

	@Override
	public List<Notificacao> getAll() {
		return this.notificacaoRepository.getAll();
	}

	 
} 
