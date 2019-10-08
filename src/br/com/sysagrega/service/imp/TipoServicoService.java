package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.ITipoServico;
import br.com.sysagrega.model.imp.TipoServico;
import br.com.sysagrega.repository.imp.TipoServicoRepository;
import br.com.sysagrega.service.ITipoServicoService;
import br.com.sysagrega.util.cdi.Transactional;

public class TipoServicoService implements ITipoServicoService {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoServicoRepository tipoServicoRepository;

	@Override
	@Transactional
	public void remover(ITipoServico tipoServico) {
		if (tipoServico != null) {
			// Removendo
			this.tipoServicoRepository.remover(tipoServico);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public ITipoServico atualizar(ITipoServico tipoServico) {
		try {
			 
			tipoServico = this.tipoServicoRepository.salvar(tipoServico);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return tipoServico;
	}

	@Override
	public ITipoServico getTipoServicoById(Long id) {
		return this.tipoServicoRepository.getTipoServicoById(id);
	}

	@Override
	public List<TipoServico> getAllTipoServicos() {
		return this.tipoServicoRepository.getAllTipoServicos();
	}


	@Override
	@Transactional
	public void salvar(ITipoServico tipoServico) {
		try {
			 
			tipoServico = this.tipoServicoRepository.salvar(tipoServico);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		
	}

	 
} 
