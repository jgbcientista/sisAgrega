package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.repository.IProfissionalRepository;
import br.com.sysagrega.service.IProfissionalService;
import br.com.sysagrega.util.cdi.Transactional;

public class ProfissionalService implements IProfissionalService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IProfissionalRepository profissionalRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IProfissionalService#salvar(br.com.sysagrega
	 * .model.imp.Profissional)
	 */
	@Override
	@Transactional
	public void salvar(IProfissional profissional) {

		try {
			
			// Validação para campos obrigatórios
			validaCamposObrigatorios(profissional);

			// Varifica se o profissional já está cadastrado no sistema
			IProfissional profissionalExistente = this.profissionalRepository.getProfissionalByCpf(profissional.getCpf());

			if (profissionalExistente != null) {
				throw new NegocioException("Profissional já cadastrado no sistema!");
			}

			this.profissionalRepository.saveOrMerge(profissional);
			
		} catch (Exception e) {
			
			throw new NegocioException("Erro de processamento com banco de dados!");
			
		}
	}

	/**
	 * Realiza a validação se os campos obrigatórios foram preenchidos Nome,
	 * CPF, RG, Data Nascimento
	 * 
	 * @param profissional
	 * @return true (Se os campos obrigatÃ³rios foram preenchidos) ou false
	 * @author Elton
	 */
	private void validaCamposObrigatorios(IProfissional profissional) {

		if (profissional == null) {

			throw new NegocioException("Favor informar um profissional!");

		} else if (profissional.getNome().isEmpty() || profissional.getNome() == null || profissional.getCpf().isEmpty()
				|| profissional.getCpf() == null || profissional.getRg().isEmpty() || profissional.getRg() == null
				|| profissional.getDataNascimento() == null) {

			throw new NegocioException("Os Campos obrigatórios não foram informados!");

		}
	}

	/**
	 * Retorna a lista de todos os profissionais do sistema para a tela de
	 * consulta
	 * 
	 * @return List<Profissional>
	 * @author Elton
	 */
	@Override
	public List<Profissional> getAllProfissionals() {

		return profissionalRepository.getAllProfissionals();

	}

	/**
	 * Método atualizar o objeto profissional e retorna o mesmo atualizado
	 * 
	 * @param profissional
	 * @return profissional
	 * @author Elton
	 */
	@Override
	@Transactional
	public IProfissional atualizarProfissional(IProfissional profissional) {
		IProfissional profissionalNew = new Profissional();
		try {
			profissionalNew = this.profissionalRepository.saveOrMerge(profissional);
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");
		}
		return profissionalNew;
	}

	
	/**
	 * Método exclui um objeto profissional
	 * 
	 * @param profissional
	 * @author Elton
	 */
	@Override
	@Transactional
	public void excluirProfissional(IProfissional profissional) {

		if (profissional != null) {

			this.profissionalRepository.remover(profissional);

		} else {

			throw new NegocioException("Favor selecionar um profissional!");

		}

	}
	
	@Override
	public List<Profissional> getProfissionalByFilter(String nome, String cpf, String tipoColaborador) {
		
		return this.profissionalRepository.getProfissionalByFilter(nome, cpf, tipoColaborador);
	}
	
	public Profissional getById(Long id){
		return this.profissionalRepository.getById(id);
	}
	@Override
	public Profissional obterProfissionalByLogin(Usuario usuario) {
		return this.profissionalRepository.obterProfissionalByLogin(usuario);
	}

}
