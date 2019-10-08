package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.ITrajetoRede;
import br.com.sysagrega.model.imp.TrajetoRede;
import br.com.sysagrega.repository.imp.TrajetoRedeRepository;
import br.com.sysagrega.service.ITrajetoRedeService;
import br.com.sysagrega.util.cdi.Transactional;

public class TrajetoRedeService implements ITrajetoRedeService {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TrajetoRedeRepository trajetoRedeRepository;

	
	@Override
	public List<TrajetoRede> getAll() {
		return this.trajetoRedeRepository.getAll();
	}
	
	@Override
	public TrajetoRede getById(Long id) {
		
		return this.trajetoRedeRepository.getById(id);
	}
	
	@Override
	public List<TrajetoRede> getByProjeto(Long idProjeto){
		return this.trajetoRedeRepository.getByProjeto(idProjeto);
	}
	
	
	@Transactional
	public TrajetoRede salvar(TrajetoRede item) {
		try {
			 
			item = this.trajetoRedeRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return item; 
	}
	@Override
	@Transactional
	public void remover(ITrajetoRede item) {
		if (item != null) {
			// Removendo
			this.trajetoRedeRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}
	
	@Override
	public TrajetoRede obterMaiorTrajeto(Long idProjeto) {
		return this.trajetoRedeRepository.obterMaiorTrajeto(idProjeto);
	}
	
	 
} 
