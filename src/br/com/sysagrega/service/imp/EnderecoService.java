package br.com.sysagrega.service.imp;

import javax.inject.Inject;

import br.com.sysagrega.model.IEndereco;
import br.com.sysagrega.model.imp.Endereco;
import br.com.sysagrega.repository.IEnderecoRepository;
import br.com.sysagrega.service.IEnderecoService;
import br.com.sysagrega.util.cdi.Transactional;

public class EnderecoService implements IEnderecoService  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IEnderecoRepository enderecoRepository;


	
	@Override
	@Transactional
	public IEndereco salvar(IEndereco item) {
		try {
			 
			item = this.enderecoRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados: " +  e.getMessage());

		}
		return item; 
		
	}
 
	
	@Override
	@Transactional
	public void excluir(IEndereco item) {

		if (item != null) {

			this.enderecoRepository.excluir(item);

		} else {

			throw new NegocioException("Favor selecionar um cliente!");

		}

	}
	

	@Override
	public Endereco getById(Long id) {
		return (Endereco) this.enderecoRepository.getById(id);
	}

}
