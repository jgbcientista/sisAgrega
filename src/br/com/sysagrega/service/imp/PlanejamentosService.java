package br.com.sysagrega.service.imp;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.IPlanejamentos;
import br.com.sysagrega.model.imp.Planejamentos;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.repository.imp.PlanejamentosRepository;
import br.com.sysagrega.service.IPlanejamentosService;
import br.com.sysagrega.util.DateUtil;
import br.com.sysagrega.util.cdi.Transactional;

public class PlanejamentosService implements IPlanejamentosService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PlanejamentosRepository planejamentoRepository;

	@Override
	@Transactional
	public void remover(IPlanejamentos item) {
		if (item != null) {
			// Removendo
			this.planejamentoRepository.remover(item);

		} else {

			throw new NegocioException("Favor selecionar uma recurso!");

		}
		
	}

	@Override
	public Planejamentos getById(Long id) {
		return this.planejamentoRepository.getById(id);
	}
	public List<Planejamentos> getByFiltros(Planejamentos filtro){
		return this.planejamentoRepository.getByFiltros(filtro);
	}
	
	
	@Override
	public List<Planejamentos> getAll() {
		return this.planejamentoRepository.getAll();
	}
	
	@Override
	@Transactional
	public IPlanejamentos salvar(IPlanejamentos item) {
		try {
			item = this.planejamentoRepository.salvar(item);
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados!");
		}
		return item; 
	}
	
	@Override
	@Transactional
	public IPlanejamentos criarNovoPlanejamento(Usuario usuarioAtualizacao) {
		IPlanejamentos item = new Planejamentos();
		Integer plan = null;
		try {
			String data =  DateUtil.getCurrentDayMonthAndYear();
			data = data.replace(".", "");
			item.setNrRevisao(0);
			plan = this.planejamentoRepository.obterMaiorID();
			Integer numero = 0;
			if(plan == null){
				numero = 1;
			}else{
				numero = plan + 1;
			}
			item.setNrPlan(numero);
			item.setNome("PLJ"+numero+"_D"+data+"RV0");
			item.setDtCriacaoPlanej(new Date());
			item.setUsuarioAtualizacao(usuarioAtualizacao);
			item = this.planejamentoRepository.salvar(item);
			
		} catch (Exception e) {
			throw new NegocioException("Erro ao Criar Novo Planejamento!");

		}
		return item; 
	}
	
	@Override
	@Transactional
	public IPlanejamentos criarRevisaoPlanejamento(Usuario usuarioAtualizacao, Long id) {
		IPlanejamentos planejamento = this.planejamentoRepository.getById(id);
		try{
			String data =  DateUtil.getCurrentDayMonthAndYear();
			data = data.replace(".", "");
			String nomePlan = planejamento.getNome();
			nomePlan = nomePlan.substring(0, nomePlan.indexOf("_"));
			
			planejamento.setNrRevisao(planejamento.getNrRevisao()+1);
			planejamento.setNome(nomePlan+"_D"+data+"RV"+planejamento.getNrRevisao());
			planejamento.setDtAlteracaoPlanej(new Date());
			planejamento.setUsuarioAtualizacao(usuarioAtualizacao);
			planejamento = this.planejamentoRepository.salvar(planejamento);
			
		} catch (Exception e) {
			throw new NegocioException("Erro ao Criar Novo Planejamento!");
		}
		return planejamento;
	}

	 
} 
