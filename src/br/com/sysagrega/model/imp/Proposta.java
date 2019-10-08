package br.com.sysagrega.model.imp;


import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sysagrega.model.IProposta;

@Entity
@Table(name = "TB_PROPOSTA", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_propos", sequenceName = "agrega.tb_seq_propos", allocationSize = 1)
public class Proposta extends PropostaBase implements IProposta {

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_propos")
	private Long id;
	
	private static final long serialVersionUID = 1L;

	@Transient
	private Set<CustoBdiComissao> despesasBdiComissao;
	
	@Transient
	private Set<CustoAdministrativo> despesasAdministrativas;
	
	@Transient
	private Set<CustoSeguranca> despesasSeguranca;
	
	@Transient
	private Set<CustoOperacional> despesasOperacionais;
	
	@Transient
	private Set<CustoDeslocamento> despesasDeslocamentos;
	
	@Transient
	private List<TipoItemProposta> listarTipoItemPropostaFinanceira;
	
	@Transient
	private List<TipoItemProposta> listarTipoItemPropostaTecnica;
	
	@Transient
	private List<TipoItemProposta> listarTipoItemPropostaTecFinanceira;
	
	@Transient
	private List<CustoPlanilhaFinanceira> listarCustoPlanilhaFinanceira;
	
	@Transient
	private List<CustoPlanilhaTecnica> listarCustoEquipeTecnica;
	
	@Transient
	private List<PlanilhaTecEquipamento> listarPlanilhaEquipamento;
	
	@Transient
	private Set<CustoExecucao> custos;
	
	public double getCalculoValorTotalCustoPlanilhaFinanceira() {

		double totalDosCustos = 0;
		// Realiza a soma de todos os custos de execução
		if (!getListarCustoPlanilhaFinanceira().isEmpty()) {

			for (CustoPlanilhaFinanceira list : getListarCustoPlanilhaFinanceira()) {

				totalDosCustos += list.getValorTotal();
			}
		}

		return totalDosCustos;

	}
	
	@Transient
	@Override
	public boolean isNovo() {
		return getId() == null;
	}

	@Transient
	@Override
	public boolean isExistente() {
		return !isNovo();
	}

	// Inicio dos metodo de calculos
	@Transient
	@Override
	public double getCalculoValorTotalCustosExecucao() {

		double totalDosCustos = 0;
		// Realiza a soma de todos os custos de execução
		if (getCustos() != null && !getCustos().isEmpty()) {

			for (CustoExecucao list : getCustos()) {
				totalDosCustos += list.getValorTotal();
			}
		}

		return totalDosCustos;

	}

	@Transient
	@Override
	public double getCalculoValorTotalCustosDeslocamento() {

		double totalDosCustos = 0;
		// Realiza a soma de todos os custos de execução
		if (getDespesasDeslocamentos() != null && !getDespesasDeslocamentos().isEmpty()) {

			for (CustoDeslocamento list : getDespesasDeslocamentos()) {

				totalDosCustos += list.getValorTotal();

			}
		}

		return totalDosCustos;

	}

	@Transient
	@Override
	public double getCalculoValorTotalCustosOperacionais() {

		double totalDosCustos = 0;
		// Realiza a soma de todos os custos de execução
		if (getDespesasOperacionais() != null && !getDespesasOperacionais().isEmpty()) {

			for (CustoOperacional list : getDespesasOperacionais()) {

				totalDosCustos += list.getValorTotal();

			}
		}

		return totalDosCustos;

	}

	@Transient
	@Override
	public double getCalculoValorTotalCustosSeguranca() {

		double totalDosCustos = 0;
		// Realiza a soma de todos os custos de execução
		if (getDespesasSeguranca() != null && !getDespesasSeguranca().isEmpty()) {

			for (CustoSeguranca list : getDespesasSeguranca()) {

				totalDosCustos += list.getValorTotal();

			}
		}

		return totalDosCustos;

	}

	@Transient
	@Override
	public double getCalculoValorTotalCustosAdministrativos() {

		double totalDosCustos = 0;
		// Realiza a soma de todos os custos de execução
		if (getDespesasAdministrativas() != null && !getDespesasAdministrativas().isEmpty()) {

			for (CustoAdministrativo list : getDespesasAdministrativas()) {

				totalDosCustos += list.getValorTotal();

			}
		}

		return totalDosCustos;

	}

	@Transient
	@Override
	public double getCalculoValorTotalCustosBdiComissao() {

		double totalDosCustos = 0;
		// Realiza a soma de todos os custos de execução
		if (getDespesasBdiComissao() != null && !getDespesasBdiComissao().isEmpty()) {

			for (CustoBdiComissao list : getDespesasBdiComissao()) {

				totalDosCustos += list.getValorTotal();

			}
		}

		return totalDosCustos;

	}
	
	@Override
	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao= dataAprovacao;
		
	}

	@Override
	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	@Override
	public List<TipoItemProposta> getListarTipoItemPropostaFinanceira() {
		return listarTipoItemPropostaFinanceira;
	}

	@Override
	public void setListarTipoItemPropostaFinanceira(
			List<TipoItemProposta> listarTipoItemPropostaFinanceira) {
		this.listarTipoItemPropostaFinanceira = listarTipoItemPropostaFinanceira;
	}

	@Override
	public List<TipoItemProposta> getListarTipoItemPropostaTecnica() {
		return listarTipoItemPropostaTecnica;
	}

	@Override
	public void setListarTipoItemPropostaTecnica(
			List<TipoItemProposta> listarTipoItemPropostaTecnica) {
		this.listarTipoItemPropostaTecnica = listarTipoItemPropostaTecnica;
	}
	
	@Override
	public List<TipoItemProposta> getListarTipoItemPropostaTecFinanceira() {
		return listarTipoItemPropostaTecFinanceira;
	}

	@Override
	public void setListarTipoItemPropostaTecFinanceira(
			List<TipoItemProposta> listarTipoItemPropostaTecFinanceira) {
		this.listarTipoItemPropostaTecFinanceira = listarTipoItemPropostaTecFinanceira;
	}

	@Override
	public String getRespProjeto() {
		return respProjeto;
	}

	@Override
	public String getDescricaoFinanceira() {
		return descricaoFinanceira;
	}

	@Override
	public String getDescricaoTecnica() {
		return descricaoTecnica;
	}

	@Override
	public void setRespProjeto(String respProjeto) {
		this.respProjeto = respProjeto;
	}

	@Override
	public void setDescricaoFinanceira(String descricaoFinanceira) {
		this.descricaoFinanceira = descricaoFinanceira;
	}

	@Override
	public void setDescricaoTecnica(String descricaoTecnica) {
		this.descricaoTecnica = descricaoTecnica;
	}

	@Override
	public String getNomeProposta() {
		return this.nomeProposta;
	}

	@Override
	public void setNomeProposta(String nomeProposta) {
		this.nomeProposta = nomeProposta;
		
	}

	public List<CustoPlanilhaFinanceira> getListarCustoPlanilhaFinanceira() {
		return listarCustoPlanilhaFinanceira;
	}

	public void setListarCustoPlanilhaFinanceira(
			List<CustoPlanilhaFinanceira> listarCustoPlanilhaFinanceira) {
		this.listarCustoPlanilhaFinanceira = listarCustoPlanilhaFinanceira;
	}
	
	public List<CustoPlanilhaTecnica> getListarCustoEquipeTecnica() {
		return listarCustoEquipeTecnica;
	}

	public void setListarCustoEquipeTecnica(
			List<CustoPlanilhaTecnica> listarCustoEquipeTecnica) {
		this.listarCustoEquipeTecnica = listarCustoEquipeTecnica;
	}
	
	public List<PlanilhaTecEquipamento> getListarPlanilhaEquipamento() {
		return listarPlanilhaEquipamento;
	}

	public void setListarPlanilhaEquipamento(
			List<PlanilhaTecEquipamento> listarPlanilhaEquipamento) {
		this.listarPlanilhaEquipamento = listarPlanilhaEquipamento;
	}
	
	@Override
	public void setNumeroRevisao(String numeroRevisao) {
		this.numeroRevisao = numeroRevisao;
		
	}


	public Set<CustoExecucao> getCustos() {
		return custos;
	}


	public void setCustos(Set<CustoExecucao> custos) {
		this.custos = custos;
	}


	public Set<CustoBdiComissao> getDespesasBdiComissao() {
		return despesasBdiComissao;
	}


	public void setDespesasBdiComissao(Set<CustoBdiComissao> despesasBdiComissao) {
		this.despesasBdiComissao = despesasBdiComissao;
	}


	public Set<CustoAdministrativo> getDespesasAdministrativas() {
		return despesasAdministrativas;
	}


	public void setDespesasAdministrativas(
			Set<CustoAdministrativo> despesasAdministrativas) {
		this.despesasAdministrativas = despesasAdministrativas;
	}


	public Set<CustoSeguranca> getDespesasSeguranca() {
		return despesasSeguranca;
	}


	public void setDespesasSeguranca(Set<CustoSeguranca> despesasSeguranca) {
		this.despesasSeguranca = despesasSeguranca;
	}


	public Set<CustoOperacional> getDespesasOperacionais() {
		return despesasOperacionais;
	}


	public void setDespesasOperacionais(Set<CustoOperacional> despesasOperacionais) {
		this.despesasOperacionais = despesasOperacionais;
	}


	public Set<CustoDeslocamento> getDespesasDeslocamentos() {
		return despesasDeslocamentos;
	}


	public void setDespesasDeslocamentos(
			Set<CustoDeslocamento> despesasDeslocamentos) {
		this.despesasDeslocamentos = despesasDeslocamentos;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}


}
