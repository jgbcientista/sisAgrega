package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IAnexo;
import br.com.sysagrega.model.imp.Anexo;
import br.com.sysagrega.repository.IAnexoRepository;
import br.com.sysagrega.service.IAnexoService;
import br.com.sysagrega.util.cdi.Transactional;

public class AnexoService implements IAnexoService  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IAnexoRepository repository;

	@Override
	@Transactional
	public IAnexo salvar(IAnexo item) {
		try {
			item = repository.saveOrMerge(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");
			
		}
		return item;
	}

	@Override
	public List<Anexo> getAll() {
		return repository.getAll();
	}

	@Override
	@Transactional
	public IAnexo atualizar(IAnexo item) {
		IAnexo itemAtual = new Anexo();
		try {
			itemAtual = this.repository.saveOrMerge(item);
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");
		}
		return itemAtual;

	}

	@Override
	@Transactional
	public void excluir(IAnexo item) {
		if (item != null) {
			this.repository.remover(item);
		} else {

			throw new NegocioException("Favor selecione o anexo!");
		}

	}

	@Override
	public IAnexo getById(Long id) {
		return this.repository.getById(id);
	}
	
	public List<Anexo> getByIdByTipo(Long codigo, Long tipo, String escricao) {
		return this.repository.getByIdByTipo(codigo, tipo, escricao);
	}
	
	@Override
	public Boolean verificaSeAnexoJaExite(Long tipo, Long idEntidade, String descricao) {
		return this.repository.verificaSeAnexoJaExite(tipo, idEntidade, descricao);
	}
	
}
