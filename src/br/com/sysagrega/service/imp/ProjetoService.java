package br.com.sysagrega.service.imp;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IPlanejamentos;
import br.com.sysagrega.model.IProjeto;
import br.com.sysagrega.model.imp.Projeto;
import br.com.sysagrega.repository.imp.ProjetoRepository;
import br.com.sysagrega.service.IProjetoService;
import br.com.sysagrega.util.cdi.Transactional;

public class ProjetoService implements IProjetoService {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProjetoRepository projetoRepository;

	@Override
	@Transactional
	public void remover(IProjeto item) {
		if (item != null) {
			// Removendo
			this.projetoRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	@Transactional
	public IProjeto atualizar(IProjeto tipoServico) {
		try {
			 
			tipoServico = this.projetoRepository.salvar(tipoServico);
			
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}
		return tipoServico;
	}

	@Override
	public Projeto getById(Long id) {
		return this.projetoRepository.getById(id);
	}
	
	@Override
	public IProjeto getByOrdemServico(Long idOrdemServico, Long idCliente, Long idContrato, Long idProfissional){
		return this.projetoRepository.getByOrdemServico(idOrdemServico, idCliente, idContrato, idProfissional);
	}
	@Override
	public IProjeto getByOSByNAgregaByNCliente(Long idOS, String nAgrega, String nCliente, String subTipoProjeto, Long anoReferencia){
		return this.projetoRepository.getByOSByNAgregaByNCliente(idOS, nAgrega, nCliente, subTipoProjeto, anoReferencia);
	}
	
	@Override
	public List<Projeto> getListaByOS(Long idOrdemServico, Long idCliente, Long idProjeto){
		return this.projetoRepository.getListaByOS(idOrdemServico, idCliente, idProjeto);
	}
	
	@Override
	public Integer totalProjetos(Long idOrdemServico){
		return this.projetoRepository.totalProjetos(idOrdemServico);
	}

	@Override
	public List<Projeto> getAll() {
		return this.projetoRepository.getAll();
	}
	
	public IProjeto obterMaiorID(){
		return this.projetoRepository.obterMaiorID();
	}
	public List<Projeto> getByFiltros(Projeto filtro){
		return this.projetoRepository.getByFiltros(filtro);	
	}
	@Override
	public Boolean verificaPlanejamentoExistente(IPlanejamentos planejamento){
		return this.projetoRepository.verificaPlanejamentoExistente(planejamento);
	}
	
	@Override
	public List<Projeto> getByProjetosSemPlanejamento(Projeto filtro){
		return projetoRepository.getByProjetosSemPlanejamento(filtro);
	}
	
	public Projeto getPorID(Long id){
		return projetoRepository.getPorID(id);
	}


	@Override
	@Transactional
	public IProjeto salvar(IProjeto item) {
		try {
			 
			item = this.projetoRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados: " + e.getMessage());

		}
		return item; 
	}

	@Override
	@Transactional
	public void salvarProjeto(IProjeto item) {
		try {
		this.projetoRepository.salvar(item);
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados - Projeto");
		}
	}

	@Override
	public List<Projeto> obterProjetoIN(String idOss, String idsContrato, Date dataInicial, Date dataFinal, Projeto filtro) {
		return this.projetoRepository.obterProjetoIN(idOss, idsContrato, dataInicial, dataFinal,filtro);
	}

	@Override
	public List<Projeto> obterProjetoSemPlanejamento(String idPlanejamentos, Date dataInicial, Date dataFinal) {
		return this.projetoRepository.obterProjetoSemPlanejamento(idPlanejamentos, dataInicial, dataFinal);
	}

	@Override
	public List<Projeto> obterProjetoByTipoByAnoReferencia(String subTipoProjeto, Long anoReferencia) {
		return this.projetoRepository.obterProjetoByTipoByAnoReferencia(subTipoProjeto, anoReferencia);
	}

	@Override
	public IProjeto obterNumeracaoMaiorByTipoByAno(String subTipoProjeto, Long anoReferencia) {
		return this.projetoRepository.obterNumeracaoMaiorByTipoByAno(subTipoProjeto, anoReferencia);
	}
	
	@Override
	public Integer obterProjetoPorOS(Long idOS) {
		return this.projetoRepository.obterProjetoPorOS(idOS);
	}
	
} 
