package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.PlanilhaTecEquipamento;
import br.com.sysagrega.repository.imp.PlanilhaTecEquipamentoRepository;
import br.com.sysagrega.service.IPlanilhaTecEquipamentoService;
import br.com.sysagrega.util.cdi.Transactional;

public class PlanilhaTecEquipamentoService implements IPlanilhaTecEquipamentoService{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PlanilhaTecEquipamentoRepository planilhaTecEquipamentoRepository;

	@Override
	@Transactional
	public void remover(PlanilhaTecEquipamento item)  {
		if (item != null) {
			
			this.planilhaTecEquipamentoRepository.remover(item);

		} else {
			throw new NegocioException("Favor selecionar um item");
		}
		
	}

	@Override
	@Transactional
	public PlanilhaTecEquipamento atualizar(PlanilhaTecEquipamento item) {
		try {
			 
			item = this.planilhaTecEquipamentoRepository.salvar(item);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return item;
	}

	@Override
	public PlanilhaTecEquipamento getById(Long id){
		return this.planilhaTecEquipamentoRepository.getById(id);
	}
	
	@Override
	public  List<PlanilhaTecEquipamento> getByPropostaId(Long idProposta) {
		return this.planilhaTecEquipamentoRepository.getByPropostaId(idProposta);
	}

	
	@Override
	public List<PlanilhaTecEquipamento> getAll(){
		return this.planilhaTecEquipamentoRepository.getAll();
	}


	@Override
	@Transactional
	public PlanilhaTecEquipamento salvar(PlanilhaTecEquipamento item) {
		try {
			item = this.planilhaTecEquipamentoRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		
		return item;
	}

	 
} 
