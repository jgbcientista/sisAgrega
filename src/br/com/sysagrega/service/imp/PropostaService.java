package br.com.sysagrega.service.imp;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;

import br.com.sysagrega.controller.vo.RelatorioClienteVO;
import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.imp.CustoAdministrativo;
import br.com.sysagrega.model.imp.CustoAdministrativoHistorico;
import br.com.sysagrega.model.imp.CustoDeslocamento;
import br.com.sysagrega.model.imp.CustoDeslocamentoHistorico;
import br.com.sysagrega.model.imp.CustoExecucao;
import br.com.sysagrega.model.imp.CustoExecucaoHistorico;
import br.com.sysagrega.model.imp.CustoOperacional;
import br.com.sysagrega.model.imp.CustoOperacionalHistorico;
import br.com.sysagrega.model.imp.CustoSeguranca;
import br.com.sysagrega.model.imp.CustoSegurancaHistorico;
import br.com.sysagrega.model.imp.GeradorProposta;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.model.imp.PropostaHistorico;
import br.com.sysagrega.repository.IPropostaHistoricoRepository;
import br.com.sysagrega.repository.IPropostaRepository;
import br.com.sysagrega.service.ICustoAdministrativoHistoricoService;
import br.com.sysagrega.service.ICustoAdministrativoService;
import br.com.sysagrega.service.ICustoDeslocamentoHistoricoService;
import br.com.sysagrega.service.ICustoDeslocamentoService;
import br.com.sysagrega.service.ICustoExecucaoHistoricoService;
import br.com.sysagrega.service.ICustoExecucaoService;
import br.com.sysagrega.service.ICustoOperacionalHistoricoService;
import br.com.sysagrega.service.ICustoOperacionalService;
import br.com.sysagrega.service.ICustoSegurancaHistoricoService;
import br.com.sysagrega.service.ICustoSegurancaService;
import br.com.sysagrega.service.IGeradorPropostaService;
import br.com.sysagrega.service.IPropostaService;
import br.com.sysagrega.util.DateUtil;
import br.com.sysagrega.util.cdi.Transactional;

public class PropostaService implements IPropostaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IPropostaRepository propostaRepository;
	@Inject
	private IPropostaHistoricoRepository propostaRepositoryHistorico;
	@Inject
	private IGeradorPropostaService geradorPropostaService;
	@Inject
	private ICustoAdministrativoService custoAdministrativoService;
	@Inject
	private ICustoAdministrativoHistoricoService custoAdministrativoHistoricoService;
	@Inject
	private ICustoExecucaoService custoExecucaoService;
	@Inject
	private ICustoExecucaoHistoricoService custoExecucaoHistoricoService;
	@Inject
	private ICustoDeslocamentoService custoDeslocamentoService;
	@Inject
	private ICustoDeslocamentoHistoricoService custoDeslocamentoHistoricoService;
	@Inject
	private ICustoSegurancaService custoSegurancaService;
	@Inject
	private ICustoSegurancaHistoricoService custoSegurancaHistoricoService;
	@Inject
	private ICustoOperacionalService custoOperacionalService;
	@Inject
	private ICustoOperacionalHistoricoService custoOperacionalHistoricoService;

	
	@Override
	@Transactional
	public Proposta preSalvar(Proposta proposta) {

		try {
			proposta.setDataInclusao(new Date());
			proposta.setDataAtualizacao(new Date());
			proposta = this.propostaRepository.saveOrMerge(proposta);
			
			GeradorProposta numeroProjeto = this.geradorPropostaService.obterMaiorID();
			Integer ano = new DateTime().getYear();
			Integer novoNumero=1;
			GeradorProposta noNumeroGeradorProjeto = new GeradorProposta();
			
			if(numeroProjeto != null && numeroProjeto.getAno().equals(ano)){
				novoNumero = numeroProjeto.getNumero() + 1;
			}
			
			String numeroProposta;
			if(novoNumero < 10){
				numeroProposta = "00" + novoNumero;
				/*numeroProposta = "00" + proposta.getId();*/
			}else if (novoNumero >= 10 && novoNumero < 100){
				numeroProposta = "0" + novoNumero;
				/*numeroProposta = "0" + proposta.getId();*/
			}else{
				/*numeroProposta =""+ proposta.getId();*/
				numeroProposta =""+ novoNumero;
			}
			proposta.setOrdProposta(Integer.valueOf(numeroProjeto.getId().toString()));
			proposta.setNumeroProposta("AC-" + numeroProposta + "." + DateUtil.getCurrentMonthAndYear()+ "." 
			+ proposta.getCliente().getSigla() + "/Rev." + proposta.getRevisaoPrecificacao());
			proposta = this.propostaRepository.saveOrMerge(proposta);
			
			noNumeroGeradorProjeto.setAno(ano);
			noNumeroGeradorProjeto.setNumero(novoNumero);
			this.geradorPropostaService.salvar(noNumeroGeradorProjeto);
			
		} catch (Exception e) {
			throw new NegocioException("Erro de processamento com banco de dados: " +e.getMessage());

		}
			return proposta;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IPropostaService#salvar(br.com.sysagrega.
	 * model.imp.Proposta)
	 */
	@Override
	@Transactional
	public Proposta salvar(Proposta proposta) {

		try {

			//Set data de inclusão no sistema
			proposta.setDataInclusao(new Date());
			proposta = this.propostaRepository.saveOrMerge(proposta);
			proposta.setNumeroProposta("AC." + proposta.getId() + "." + DateUtil.getCurrentMonthAndYear());

			// Salvando histórico
			salvarHistorico(configuraObjetoHistorico(proposta), proposta.getNumeroProposta());

		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados: " +e.getMessage());

		}
			return proposta;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.sysagrega.service.imp.IPropostaService#getAllPropostas()
	 */
	@Override
	public List<Proposta> getAllPropostas() {

		return propostaRepository.getAllPropostas();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IPropostaService#atualizarProposta(br.com.
	 * sysagrega.model.imp.Proposta)
	 */
	@Override
	@Transactional
	public IProposta atualizarProposta(Proposta proposta, String status) {
		Proposta propostaNew = new Proposta();
		try {
			if(status !=null && status.equals("NovaRevisao")){
				String numeroProposta;
				String numeroRevAtual = proposta.getNumeroProposta();
				if(proposta.getId() < 10){
					numeroProposta = "00" + proposta.getId();
				}else if (proposta.getId() >= 10 && proposta.getId() < 100){
					numeroProposta = "0" + proposta.getId();
				}else{
					numeroProposta =""+ proposta.getId();
				}
				
				Long numRevisao = proposta.getRevisaoPrecificacao();
				numRevisao ++;
				proposta.setRevisaoPrecificacao(numRevisao);
				proposta.setStatusProposta("Revisão");
				
				if(proposta.getNumeroProposta() != null){
					proposta.setNumeroProposta(proposta.getNumeroProposta().substring(0,14)+ proposta.getCliente().getSigla() + "/Rev." + numRevisao);
				}else{
					proposta.setNumeroProposta("AC-" + numeroProposta + "." + DateUtil.getCurrentMonthAndYear()+ "." 
					+ proposta.getCliente().getSigla() + "/Rev." + numRevisao);
				}
			 propostaNew = this.propostaRepository.saveOrMerge(proposta);
			 PropostaHistorico propHistorico = salvarHistorico(configuraObjetoHistorico(propostaNew), numeroRevAtual);
			
			 historicoCustoAdm(propostaNew, propHistorico);
			 historicoCustoExecucao(propostaNew, propHistorico);
			 historicoCustoSeguranca(propostaNew, propHistorico);
			 historicoCustoDeslocamento(propostaNew, propHistorico);
			 historicoCustoOperacional(propostaNew, propHistorico);
			 
			 proposta.setNumeroRevisao("Rev" + proposta.getRevisaoPrecificacao());
			 
			}else if(status != null && status.equals("Finalizada")){
				proposta.setStatusProposta(status);
				proposta.setDataAtualizacao(new Date());
				propostaNew = this.propostaRepository.saveOrMerge(proposta); 
			//	salvarHistorico(configuraObjetoHistorico(propostaNew), proposta.getNumeroProposta());
			//	proposta.setNumeroRevisao("Rev" + proposta.getRevisaoPrecificacao());
			}else{
				propostaNew = this.propostaRepository.saveOrMerge(proposta);
			}
			 
		} catch (Exception e) {

			throw new NegocioException("Erro de processamento com banco de dados!");

		}

		return propostaNew;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IPropostaService#excluirProposta(br.com.
	 * sysagrega.model.IProposta)
	 */
	@Override
	@Transactional
	public void excluirProposta(Proposta proposta) {

		if (proposta != null) {

			// Removendo todo histórico da proposta
			this.propostaRepositoryHistorico.removerHistorico(proposta);
			// Removendo a proposta
			this.propostaRepository.remover(proposta);

		} else {

			throw new NegocioException("Favor selecionar uma proposta!");

		}

	}
	
	@Override
	public Proposta getPropostaById(Long id) {
		return this.propostaRepository.getPropostaById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IPropostaService#getPropostaByFilter(java.
	 * lang.Long, java.lang.Long)
	 */
	@Override
	public List<Proposta> getPropostaByFilter(String filtroNumeroProposta, Long filtroCliente, Character filtroStatus,
			Date filtroDataInicial, Date filtroDataFinal) {

		// Validação do periodo
		if (filtroDataInicial != null && filtroDataFinal != null) {

			if (DateUtil.compareDates(filtroDataInicial, filtroDataFinal) > 0) {

				throw new NegocioException("A data final deve ser igual ou superior a data inicial.");

			}
		}

		return this.propostaRepository.getPropostaByFilter(filtroNumeroProposta, filtroCliente, filtroStatus,
				filtroDataInicial, filtroDataFinal);

	}
	
	@Override
	public synchronized  List<Proposta> getObterListPropostaRelatorioByFilter(RelatorioClienteVO filtro) {
		
		
		return this.propostaRepository.getObterListPropostaRelatorioByFilter(filtro);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.IPropostaService#salvarHistorico(br.com.
	 * sysagrega.model.IPropostaHistorico)
	 */
	@Override
	public PropostaHistorico salvarHistorico(PropostaHistorico propostaHistorico, String numeroProposta) {
		propostaHistorico = this.propostaRepositoryHistorico.saveHistorico(propostaHistorico);
		//TODO
		propostaHistorico.setNumeroRevisao(numeroProposta);
	//	propostaHistorico.setNumeroRevisao("REV-" + propostaHistorico.getId() + "." + DateUtil.getCurrentMonthAndYear());
		return propostaHistorico;
	}

	@Override
	public List<PropostaHistorico> getPropostaHistoricoByFilter(IProposta propostaId) {

		return this.propostaRepositoryHistorico.getPropostaHistoricoByFilter(propostaId);

	}

	private PropostaHistorico configuraObjetoHistorico(Proposta proposta) {

		PropostaHistorico historico = new PropostaHistorico();

		// Ordem
		historico.setPropostaId(proposta);
		historico.setNumeroProposta(proposta.getNumeroProposta());
		historico.setNomeProjeto(proposta.getNomeProjeto());
		historico.setDataInclusao(proposta.getDataInclusao());
		historico.setCliente(proposta.getCliente());
		historico.setTipoProposta(proposta.getTipoProposta());
		if(proposta.getValorTotalComBdiComissao() != Double.valueOf(0)){
			historico.setValorTotalDaProposta(proposta.getValorTotalComBdiComissao()+((proposta.getValorTotalComBdiComissao()) / (1 - proposta.getImpostos())  - (proposta.getValorTotalComBdiComissao()))-(proposta.getDesconto()));
		}
		historico.setValorTotalCustosExecucao(proposta.getValorTotalCustosExecucao());
		historico.setValorTotalCustosDesclocamento(proposta.getValorTotalCustosDesclocamento());
		historico.setValorTotalCustosOperacionais(proposta.getValorTotalCustosOperacionais());
		historico.setValorTotalCustosAdministrativos(proposta.getValorTotalCustosAdministrativos());
		historico.setValorTotalCustosSeguranca(proposta.getValorTotalCustosSeguranca());
		if(proposta.getValorTotalSemBdiComissao() != Double.valueOf(0)){
			historico.setValorTotalCustosBdiComissoes(((proposta.getValorTotalSemBdiComissao() * proposta.getPercentualBDI())/100));
		}
		historico.setDesconto(proposta.getDesconto());
		historico.setPercentualBDI(proposta.getPercentualBDI());
		historico.setPercentualComissao(proposta.getPercentualComissao());
		
		// Total custos com e sem bdi
		historico.setValorTotalComBdiComissao(proposta.getValorTotalComBdiComissao());
		historico.setValorTotalSemBdiComissao(proposta.getValorTotalSemBdiComissao());
		if(proposta.getImpostos() != Double.valueOf(0)){
			historico.setValorTotalImpostos((proposta.getValorTotalComBdiComissao() / (1 - proposta.getImpostos()) - (proposta.getValorTotalComBdiComissao())));
		}
		historico.setDataRevisao(new Date());
		return historico;
	}
	
	private void historicoCustoAdm(Proposta proposta, PropostaHistorico historico){
		
		List<CustoAdministrativo> listCustoAdm = custoAdministrativoService.getByPropostaId(proposta.getId());
		if(listCustoAdm != null && !listCustoAdm.isEmpty()){
			CustoAdministrativoHistorico custoAdmHistorico;
			for (CustoAdministrativo custAdm : listCustoAdm) {
				custoAdmHistorico =  new CustoAdministrativoHistorico();
				custoAdmHistorico.setDescricao(custAdm.getDescricao());
				custoAdmHistorico.setValorUnitario(custAdm.getValorUnitario());
				custoAdmHistorico.setQuantidade(custAdm.getQuantidade());
				custoAdmHistorico.setObservacoes(custAdm.getObservacoes());
				custoAdmHistorico.setPropostaHistorico(historico);
				custoAdmHistorico.setTpUnidadeMedida(custAdm.getTpUnidadeMedida());
				custoAdmHistorico.setValorTotal(custAdm.getValorTotal());
				custoAdmHistorico.setAtivo(true);
				custoAdmHistorico.setTpUnidadeMedida(custAdm.getTpUnidadeMedida());
				
				custoAdministrativoHistoricoService.salvar(custoAdmHistorico);
			}
		}
	}
	
	private void historicoCustoExecucao(Proposta proposta, PropostaHistorico historico){
		List<CustoExecucao> listCusto = custoExecucaoService.getByPropostaId(proposta.getId());
		if(listCusto != null && !listCusto.isEmpty()){
			CustoExecucaoHistorico custoHistorico;
			for (CustoExecucao custo : listCusto) {
				custoHistorico =  new CustoExecucaoHistorico();
				custoHistorico.setDescricao(custo.getDescricao());
				custoHistorico.setValorUnitario(custo.getValorUnitario());
				custoHistorico.setQuantidade(custo.getQuantidade());
				custoHistorico.setObservacoes(custo.getObservacoes());
				custoHistorico.setPropostaHistorico(historico);
				custoHistorico.setTpUnidadeMedida(custo.getTpUnidadeMedida());
				custoHistorico.setValorTotal(custo.getValorTotal());
				custoHistorico.setAtivo(true);
				custoHistorico.setTpUnidadeMedida(custo.getTpUnidadeMedida());
				
				custoExecucaoHistoricoService.salvar(custoHistorico);
			}
		}
	}
	
	private void historicoCustoDeslocamento(Proposta proposta, PropostaHistorico historico){
		List<CustoDeslocamento> listCusto = custoDeslocamentoService.getByPropostaId(proposta.getId());
		if(listCusto != null && !listCusto.isEmpty()){
			CustoDeslocamentoHistorico custoHistorico;
			for (CustoDeslocamento custo : listCusto) {
				custoHistorico =  new CustoDeslocamentoHistorico();
				custoHistorico.setDescricao(custo.getDescricao());
				custoHistorico.setValorUnitario(custo.getValorUnitario());
				custoHistorico.setQuantidade(custo.getQuantidade());
				custoHistorico.setObservacoes(custo.getObservacoes());
				custoHistorico.setPropostaHistorico(historico);
				custoHistorico.setTpUnidadeMedida(custo.getTpUnidadeMedida());
				custoHistorico.setValorTotal(custo.getValorTotal());
				custoHistorico.setAtivo(true);
				custoHistorico.setTpUnidadeMedida(custo.getTpUnidadeMedida());
				
				custoDeslocamentoHistoricoService.salvar(custoHistorico);
			}
		}
	}
	
	private void historicoCustoSeguranca(Proposta proposta, PropostaHistorico historico){
		List<CustoSeguranca> listCusto = custoSegurancaService.getByPropostaId(proposta.getId());
		if(listCusto != null && !listCusto.isEmpty()){
			CustoSegurancaHistorico custoHistorico;
			for (CustoSeguranca custo : listCusto) {
				custoHistorico =  new CustoSegurancaHistorico();
				custoHistorico.setDescricao(custo.getDescricao());
				custoHistorico.setValorUnitario(custo.getValorUnitario());
				custoHistorico.setQuantidade(custo.getQuantidade());
				custoHistorico.setObservacoes(custo.getObservacoes());
				custoHistorico.setPropostaHistorico(historico);
				custoHistorico.setTpUnidadeMedida(custo.getTpUnidadeMedida());
				custoHistorico.setValorTotal(custo.getValorTotal());
				custoHistorico.setAtivo(true);
				custoHistorico.setTpUnidadeMedida(custo.getTpUnidadeMedida());
				
				custoSegurancaHistoricoService.salvar(custoHistorico);
			}
		}
	}
	
	private void historicoCustoOperacional (Proposta proposta, PropostaHistorico historico){
		List<CustoOperacional> listCusto = custoOperacionalService.getByPropostaId(proposta.getId());
		if(listCusto != null && !listCusto.isEmpty()){
			CustoOperacionalHistorico custoHistorico;
			for (CustoOperacional custo : listCusto) {
				custoHistorico =  new CustoOperacionalHistorico();
				custoHistorico.setDescricao(custo.getDescricao());
				custoHistorico.setValorUnitario(custo.getValorUnitario());
				custoHistorico.setQuantidade(custo.getQuantidade());
				custoHistorico.setObservacoes(custo.getObservacoes());
				custoHistorico.setPropostaHistorico(historico);
				custoHistorico.setTpUnidadeMedida(custo.getTpUnidadeMedida());
				custoHistorico.setValorTotal(custo.getValorTotal());
				custoHistorico.setAtivo(true);
				custoHistorico.setTpUnidadeMedida(custo.getTpUnidadeMedida());
				
				custoOperacionalHistoricoService.salvar(custoHistorico);
			}
		}
	}
}
