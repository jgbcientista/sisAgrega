package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.ITipoDespesa;
import br.com.sysagrega.model.imp.TipoDespesa;
import br.com.sysagrega.repository.imp.TipoDespesaRepository;
import br.com.sysagrega.service.ITipoDespesaService;
import br.com.sysagrega.util.cdi.Transactional;

public class TipoDespesaService implements ITipoDespesaService {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoDespesaRepository tipoDespesaRepository;

	@Override
	@Transactional
	public void remover(ITipoDespesa tipo)  {
		if (tipo != null) {
			
			this.tipoDespesaRepository.remover(tipo);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public ITipoDespesa atualizar(ITipoDespesa tipoServico) {
		try {
			 
			tipoServico = this.tipoDespesaRepository.salvar(tipoServico);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return tipoServico;
	}

	@Override
	public ITipoDespesa getTipoDespesaById(Long id){
		return this.tipoDespesaRepository.getTipoDespesaById(id);
	}

	@Override
	public List<TipoDespesa> getAllTipoDespesas() {
		return this.tipoDespesaRepository.getAllTipoDespesas();
	}


	@Override
	@Transactional
	public void salvar(ITipoDespesa tipoServico) {
		try {
			 
			tipoServico = this.tipoDespesaRepository.salvar(tipoServico);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		
	}
	 
} 
