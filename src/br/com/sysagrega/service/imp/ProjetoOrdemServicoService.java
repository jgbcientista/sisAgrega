package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IProjetoOrdemServico;
import br.com.sysagrega.model.imp.ProjetoOrdemServico;
import br.com.sysagrega.repository.imp.ProjetoOrdemServicoRepository;
import br.com.sysagrega.service.IProjetoOrdemServicoService;
import br.com.sysagrega.util.cdi.Transactional;

public class ProjetoOrdemServicoService implements IProjetoOrdemServicoService {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProjetoOrdemServicoRepository projetoOrdemServicoRepository;

	@Override
	@Transactional
	public void remover(IProjetoOrdemServico item) {
		if (item != null) {
			// Removendo
			this.projetoOrdemServicoRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public IProjetoOrdemServico atualizar(IProjetoOrdemServico item) {
		try {
			item = this.projetoOrdemServicoRepository.salvar(item);
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");
		}
		return item;
	}

	@Override
	public ProjetoOrdemServico getById(Long id) {
		return this.projetoOrdemServicoRepository.getById(id);
	}

	@Override
	public List<ProjetoOrdemServico> getAll() {
		return this.projetoOrdemServicoRepository.getAll();
	}
	
	@Override
	public List<ProjetoOrdemServico> getByProjetoByOS(Long idProjeto, Long idOrdemServico) {
		return this.projetoOrdemServicoRepository.getByProjetoByOS(idProjeto, idOrdemServico);
	}

	@Override
	@Transactional
	public void salvar(IProjetoOrdemServico item) {
		try {
			item = this.projetoOrdemServicoRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados: " + e.getMessage());

		}
		
	}

	 
} 
