package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IMensagem;
import br.com.sysagrega.model.imp.Mensagem;
import br.com.sysagrega.repository.imp.MensagemRepository;
import br.com.sysagrega.service.IMensagemService;
import br.com.sysagrega.util.cdi.Transactional;

public class MensagemService implements IMensagemService {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MensagemRepository repository;

	@Override
	@Transactional
	public void remover(IMensagem item) {
		if (item != null) {
			// Removendo
			this.repository.remover(item);

		} else {
			throw new NegocioException("Favor selecionar uma mensagem!");

		}
	}

	@Override
	@Transactional
	public IMensagem atualizar(IMensagem item) {
		try {
			item = this.repository.salvar(item);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return item;
	}

	@Override
	public Mensagem getById(Long id) {
		return this.repository.getById(id);
	}

	@Override
	public List<Mensagem> getAll() {
		return this.repository.getAll();
	}

	@Override
	@Transactional
	public void salvar(IMensagem item) {
		try {
			 
			item = this.repository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
	}

	@Override
	public List<Mensagem> getByFilter(Mensagem filtro) {
		return this.repository.getByFilter(filtro);
	}
	
	@Override
	public List<Mensagem> obterByProjetoByProfissional(Long idProjeto, Long idProfissional, Long idTipo){
		return this.repository.obterByProjetoByProfissional(idProjeto, idProfissional, idTipo);
	}
	 
} 
