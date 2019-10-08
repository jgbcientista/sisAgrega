package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IPerfil;
import br.com.sysagrega.model.imp.Perfil;
import br.com.sysagrega.repository.IPerfilRepository;
import br.com.sysagrega.service.IPerfilService;
import br.com.sysagrega.util.cdi.Transactional;

public class PerfilService implements IPerfilService  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IPerfilRepository perfilRepository;


	@Override
	@Transactional
	public void salvar(IPerfil perfil) {
		try {
			// Varifica se o item já está cadastrado no sistema
			IPerfil usuarioExistente = null;
			if(perfil != null && perfil.getId() != null){
				usuarioExistente = this.perfilRepository.getById(perfil.getId());
			}
			if (usuarioExistente != null) {
				throw new NegocioException("item já cadastrado no sistema!");
			}

			this.perfilRepository.saveOrMerge(perfil);
			
		} catch (Exception e) {
			
			throw new NegocioException("Erro de processamento com banco de dados!");
			
		}
	}

	 
	private void validaCamposObrigatorios(IPerfil perfil) {
		if (perfil == null) {

			throw new NegocioException("Favor informar os dados corretamente!");

		} else if (perfil.getNome().isEmpty()      || perfil.getNome() == null  ) {

			throw new NegocioException("Os Campos obrigatórios não foram informados!");

		}
	}

	@Override
	public List<Perfil> getAll() {
		return perfilRepository.getAll();

	}
 
	@Override
	@Transactional
	public IPerfil atualizar(IPerfil perfil) {
		
		IPerfil atual = new Perfil();
		try {
			// Validação para campos obrigatórios
			validaCamposObrigatorios(perfil);

			atual = this.perfilRepository.saveOrMerge(perfil);
			
		} catch (Exception e) {
			
			throw new NegocioException("Erro de processamento com banco de dados!");
			
		}
		
		return atual;

	}

	@Override
	@Transactional
	public void excluir(IPerfil perfil) {

		if (perfil != null) {

			this.perfilRepository.remover(perfil);

		} else {
			throw new NegocioException("Favor selecionar um usuario!");

		}
	}
	
	@Override
	public List<Perfil> getByFilter(String nome) {
		return this.perfilRepository.getByFilter(nome);
		
	}
	

	@Override
	public Perfil getById(Long id) {
		return (Perfil) this.perfilRepository.getById(id);
	}

}
