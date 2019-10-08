package br.com.sysagrega.service.imp;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IOrdemServico;
import br.com.sysagrega.model.imp.OrdemServico;
import br.com.sysagrega.repository.imp.OrdemServicoRepository;
import br.com.sysagrega.service.IOrdemServicoService;
import br.com.sysagrega.util.cdi.Transactional;

public class OrdemServicoService implements IOrdemServicoService {
  
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrdemServicoRepository ordemServicoRepository;

	@Override
	@Transactional
	public void remover(IOrdemServico item) {
		if (item != null) {
			// Removendo
			this.ordemServicoRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");
		}
		
	}

	@Override
	@Transactional
	public IOrdemServico atualizar(IOrdemServico tipoServico) {
		try {
			 
			tipoServico = this.ordemServicoRepository.salvar(tipoServico);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return tipoServico;
	}

	@Override
	public OrdemServico getById(Long id) {
		return this.ordemServicoRepository.getById(id);
	}
	
	@Override
	public OrdemServico getPorID(Long id) {
		return this.ordemServicoRepository.getPorID(id);
	}
	
	
	@Override
	public OrdemServico obterMaiorID(Long idContrato) {
		return this.ordemServicoRepository.obterMaiorID(idContrato);
	}
	
	@Override
	public Integer obterUltimaNrOS(Long idContrato) {
		return this.ordemServicoRepository.obterUltimaNrOS(idContrato);
	}

	
	@Override
	public List<OrdemServico> getAll() {
		return this.ordemServicoRepository.getAll();
	}
	public List<OrdemServico> getOrdemServicoByFilter(Long cliente, Long numeroOS){
		return this.ordemServicoRepository.getOrdemServicoByFilter(cliente, numeroOS);
	}
	
	public List<OrdemServico> getByFiltros(OrdemServico filtro){
		return this.ordemServicoRepository.getByFiltros(filtro);
	}
	
	public OrdemServico obterByNumeroOs(String numeroOs, Long idContrato){
		return this.ordemServicoRepository.obterByNumeroOs(numeroOs, idContrato);
	}

	@Override
	@Transactional
	public IOrdemServico salvar(IOrdemServico item) {
		try {
			item = this.ordemServicoRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return item;
	}

	@Override
	public List<OrdemServico> obterIN(String idsOs, String idsContrato, Date dataInicial, Date dataFinal) {
		return this.ordemServicoRepository.obterIN(idsOs, idsContrato, dataInicial, dataFinal);
	}

	 
} 
