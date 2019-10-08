package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.CustoPlanilhaTecnica;
import br.com.sysagrega.repository.imp.CustoPlanilhaTecnicaRepository;
import br.com.sysagrega.service.ICustoPlanilhaTecnicaService;
import br.com.sysagrega.util.cdi.Transactional;

public class CustoPlanilhaTecnicaService implements ICustoPlanilhaTecnicaService{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CustoPlanilhaTecnicaRepository custoPlanilhaTecnicaRepository;

	@Override
	@Transactional
	public void remover(CustoPlanilhaTecnica item)  {
		if (item != null) {
			
			this.custoPlanilhaTecnicaRepository.remover(item);

		} else {
			throw new NegocioException("Favor selecionar um item");
		}
		
	}

	@Override
	@Transactional
	public CustoPlanilhaTecnica atualizar(CustoPlanilhaTecnica item) {
		try {
			 
			item = this.custoPlanilhaTecnicaRepository.salvar(item);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return item;
	}

	@Override
	public CustoPlanilhaTecnica getById(Long id){
		return this.custoPlanilhaTecnicaRepository.getById(id);
	}
	
	@Override
	public  List<CustoPlanilhaTecnica> getByPropostaId(Long idProposta) {
		return this.custoPlanilhaTecnicaRepository.getByPropostaId(idProposta);
	}

	
	@Override
	public List<CustoPlanilhaTecnica> getAll(){
		return this.custoPlanilhaTecnicaRepository.getAll();
	}


	@Override
	@Transactional
	public CustoPlanilhaTecnica salvar(CustoPlanilhaTecnica item) {
		try {
			item = this.custoPlanilhaTecnicaRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		
		return item;
	}

	 
} 
