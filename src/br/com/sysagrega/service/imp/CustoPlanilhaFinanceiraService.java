package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.CustoPlanilhaFinanceira;
import br.com.sysagrega.repository.imp.CustoPlanilhaFinanceiraRepository;
import br.com.sysagrega.service.ICustoPlanilhaFinanceiraService;
import br.com.sysagrega.util.cdi.Transactional;

public class CustoPlanilhaFinanceiraService implements ICustoPlanilhaFinanceiraService{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CustoPlanilhaFinanceiraRepository  custoPlanilhaFinanceiraRepository;

	@Override
	@Transactional
	public void remover(CustoPlanilhaFinanceira item)  {
		if (item != null) {
			
			this.custoPlanilhaFinanceiraRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public CustoPlanilhaFinanceira atualizar(CustoPlanilhaFinanceira item) {
		try {
			 
			item = this.custoPlanilhaFinanceiraRepository.salvar(item);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return item;
	}

	@Override
	public CustoPlanilhaFinanceira getById(Long id){
		return this.custoPlanilhaFinanceiraRepository.getById(id);
	}
	
	@Override
	public  List<CustoPlanilhaFinanceira> getByPropostaId(Long idProposta) {
		return this.custoPlanilhaFinanceiraRepository.getByPropostaId(idProposta);
	}

	@Override
	public List<CustoPlanilhaFinanceira> getAll(){
		return this.custoPlanilhaFinanceiraRepository.getAll();
	}


	@Override
	@Transactional
	public CustoPlanilhaFinanceira salvar(CustoPlanilhaFinanceira item) {
		try {
			item = this.custoPlanilhaFinanceiraRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		
		return item;
	}

	 
} 
