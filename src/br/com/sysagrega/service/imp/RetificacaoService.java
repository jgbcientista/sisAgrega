package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IRetificacao;
import br.com.sysagrega.model.imp.Retificacao;
import br.com.sysagrega.repository.imp.RetificacaoRepository;
import br.com.sysagrega.service.IRetificacaoService;
import br.com.sysagrega.util.cdi.Transactional;

public class RetificacaoService implements IRetificacaoService {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private RetificacaoRepository dao;

	
	@Override
	public List<Retificacao> getAll() {
		return this.dao.getAll();
	}
	
	@Override
	public Retificacao getById(Long id) {
		
		return this.dao.getById(id);
	}
	
	@Override
	public List<Retificacao> getByProjeto(Long idProjeto){
		return this.dao.getByProjeto(idProjeto);
	}
	
	@Override
	@Transactional
	public void remover(IRetificacao item){
		this.dao.remover(item);
	}
	
	@Override
	@Transactional
	public IRetificacao salvar(IRetificacao item) {
		try {
			 
			item = this.dao.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados: " +e.getMessage());

		}
		return item; 
		
	}
	 
} 
