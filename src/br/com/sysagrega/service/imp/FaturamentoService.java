package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IFaturamento;
import br.com.sysagrega.model.imp.Faturamento;
import br.com.sysagrega.repository.imp.FaturamentoRepository;
import br.com.sysagrega.service.IFaturamentoService;
import br.com.sysagrega.util.cdi.Transactional;

public class FaturamentoService implements IFaturamentoService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FaturamentoRepository faturamentoRepository;

	@Override
	@Transactional
	public void remover(IFaturamento item) {
		if (item != null) {
			// Removendo
			this.faturamentoRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	public Faturamento getById(Long id) {
		return this.faturamentoRepository.getById(id);
	}
	
	@Override
	public  List<Faturamento> getByProjeto(Long idProjeto){
		return this.faturamentoRepository.getByProjeto(idProjeto);
	}
	
	@Override
	public  List<Faturamento> getByContrato(Long idContrato){
		return this.faturamentoRepository.getByContrato(idContrato);
	}
	
	public  Faturamento getByProjetoByParcela(Long idProjeto, Long parcela){
		return this.faturamentoRepository.getByProjetoByParcela(idProjeto, parcela);
	}

	@Override
	public List<Faturamento> getAll() {
		return this.faturamentoRepository.getAll();
	}
	
	@Override
	@Transactional
	public IFaturamento salvar(IFaturamento item) {
		try {
			 
			item = this.faturamentoRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados: " +  e.getMessage());

		}
		return item; 
		
	}

	@Override
	public List<Faturamento> getByFiltros(Faturamento filtro) {
		return this.faturamentoRepository.getByFiltros(filtro);
	}

	 
} 
