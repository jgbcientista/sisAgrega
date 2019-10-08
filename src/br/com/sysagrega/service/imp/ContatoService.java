package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IContato;
import br.com.sysagrega.model.imp.Contato;
import br.com.sysagrega.repository.IContatoRepository;
import br.com.sysagrega.service.IContatoService;
import br.com.sysagrega.util.cdi.Transactional;

public class ContatoService implements IContatoService  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IContatoRepository contatoRepository;

	@Override
	@Transactional
	public void salvar(IContato contato) {

		try {
			
			this.contatoRepository.saveOrMerge(contato);
			
		} catch (Exception e) {
			
			throw new NegocioException("Erro de processamento com banco de dados!");
			
		}
	}

	@Override
	public List<Contato> getAllContatos() {

		return contatoRepository.getAllContatos();

	}

	@Override
	@Transactional
	public IContato atualizarContato(IContato contato) {
		
		IContato contatoAtual = new Contato();

		try {
			
			contatoAtual = this.contatoRepository.saveOrMerge(contato);
			
		} catch (Exception e) {
			
			throw new NegocioException("Erro de processamento com banco de dados!");
			
		}
		
		return contatoAtual;

	}

	@Override
	@Transactional
	public void excluirContato(IContato contato) {

		if (contato != null) {

			this.contatoRepository.remover(contato);

		} else {

			throw new NegocioException("Favor selecionar um contato!");

		}

	}
		
	@Override
	public List<Contato> getContatoByAtivo() {
		return contatoRepository.getContatoByAtivo();
	}
	
	@Override
	public Contato getContatoById(Long id) {
		return (Contato) this.contatoRepository.getContatoById(id);
	}

	
	@Override
	public List<Contato> getClienteByFilter(Long idCliente) {
		
		return this.contatoRepository.getContatoByFilter(idCliente);
		
	}
	
	@Override
	public List<Contato> getByClienteId(Long idCliente) {
		
		return this.contatoRepository.getByClienteId(idCliente);
		
	}
	
	@Override
	public List<Contato> getByFornecedorId(Long idFornecedor) {
		
		return this.contatoRepository.getByFornecedorId(idFornecedor);
		
	}
	
	
	
}
