package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IServico;
import br.com.sysagrega.model.imp.Servico;
import br.com.sysagrega.repository.IServicoRepository;
import br.com.sysagrega.service.IServicoService;
import br.com.sysagrega.util.cdi.Transactional;

public class ServicoService implements IServicoService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private IServicoRepository iServicoRepository;
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getAllBancos()
	 */
	@Override
	@Transactional
	public List<Servico> getAllServicos() {
		
		return this.iServicoRepository.getAllServicos();
		
	}
	

	/* (non-Javadoc)
	 * @see br.com.sysagrega.service.imp.IBancoService#getBancoById(java.lang.Long)
	 */
	@Override
	@Transactional
	public Servico getServicoById(Long id) {
		return this.iServicoRepository.getServicoById(id);
		
	}


	@Override
	@Transactional
	public void salvar(IServico servico) {
		try {
		 
			servico = this.iServicoRepository.saveOrMerge(servico);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados: " + e.getMessage());

		}
		
	}


	@Override
	@Transactional
	public IServico atualizarServico(IServico servico) {
		try {
			this.iServicoRepository.saveOrMerge(servico);

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados: " + e.getMessage());

		}
		return servico;
	}


	@Override
	@Transactional
	public void excluirServico(IServico servico) {
		if (servico != null) {
			// Removendo
			this.iServicoRepository.remover(servico);

		} else {

			throw new NegocioException("Favor selecionar uma servico!");

		}
		
	}

	@Override
	public List<Servico> getServicoByFilter(Long tipo, Long area, String descricao) {
		return this.iServicoRepository.getServicoByFilter(tipo, area, descricao);
		
	}

}
