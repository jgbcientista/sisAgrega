package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.ICaracteristicaProjeto;
import br.com.sysagrega.model.IPlanejamento;
import br.com.sysagrega.model.imp.CaracteristicaProjeto;
import br.com.sysagrega.model.imp.TrajetoRede;
import br.com.sysagrega.repository.ICaracteristicaProjetoRepository;
import br.com.sysagrega.service.ICaracteristicaProjetoService;
import br.com.sysagrega.util.cdi.Transactional;

public class CaracteristicaProjetoService implements ICaracteristicaProjetoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ICaracteristicaProjetoRepository caracteristicaProjetoRepository;

	
	@Override
	@Transactional
	public ICaracteristicaProjeto salvar(ICaracteristicaProjeto item) {
		try {
			 
			item = this.caracteristicaProjetoRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return item; 
		
	}


	@Override
	public void remover (ICaracteristicaProjeto item) {
		
		try {

			this.caracteristicaProjetoRepository.remover(item);
			
		} catch (Exception e) {
			
			throw new NegocioException("Erro de processamento com banco de dados!");
			
		}
	}
	
	
	@Override
	public List<CaracteristicaProjeto> getAllCaracteristicaProjeto(){

		return caracteristicaProjetoRepository.getAllCaracteristicaProjeto();

	}
	
	@Override
	public CaracteristicaProjeto getById(Long id) {
		
		return caracteristicaProjetoRepository.getById(id);
	}
	@Override
	public CaracteristicaProjeto getByProjeto(Long idProjeto){
		return this.caracteristicaProjetoRepository.getByProjeto(idProjeto);
	}
	

}
