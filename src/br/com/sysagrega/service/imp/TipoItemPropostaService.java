package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.ITipoItemProposta;
import br.com.sysagrega.model.imp.TipoItemProposta;
import br.com.sysagrega.repository.imp.TipoItemPropostaRepository;
import br.com.sysagrega.service.ITipoItemPropostaService;
import br.com.sysagrega.util.cdi.Transactional;

public class TipoItemPropostaService implements ITipoItemPropostaService{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoItemPropostaRepository  tipoItemPropostaRepository;

	@Override
	@Transactional
	public void remover(ITipoItemProposta tipo)  {
		if (tipo != null) {
			
			this.tipoItemPropostaRepository.remover(tipo);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public TipoItemProposta atualizar(TipoItemProposta tipo) {
		try {
			 
			tipo = this.tipoItemPropostaRepository.salvar(tipo);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return tipo;
	}

	@Override
	public ITipoItemProposta getById(Long id){
		return this.tipoItemPropostaRepository.getById(id);
	}
	
	@Override
	public  List<TipoItemProposta> getByPropostaId(Long idProposta, Long idTipoProposta) {
		return this.tipoItemPropostaRepository.getByPropostaId(idProposta,idTipoProposta);
	}

	@Override
	public List<TipoItemProposta> getAll(){
		return this.tipoItemPropostaRepository.getAll();
	}


	@Override
	@Transactional
	public TipoItemProposta salvar(TipoItemProposta tipo) {
		try {
			tipo = this.tipoItemPropostaRepository.salvar(tipo);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		
		return tipo;
	}

	 
} 
