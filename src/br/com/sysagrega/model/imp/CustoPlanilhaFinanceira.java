package br.com.sysagrega.model.imp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sysagrega.model.ICustoPlanilhaFinanceira;
import br.com.sysagrega.model.IProposta;

@Entity
@Table(name = "TB_CUSTO_PLANINHA_FINANCEIRA", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_plan", sequenceName = "agrega.tb_seq_plan", allocationSize = 1)
public class CustoPlanilhaFinanceira implements ICustoPlanilhaFinanceira {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_plan")
	private Long id;
	
	private String descricao;
	private Long quantidade;
	private Double valorUnitario;
	private Double valorTotal;
	private Double desconto;
	private Double valorNegociado;
	
	@OneToOne(targetEntity = Proposta.class, cascade = CascadeType.REFRESH)
	IProposta proposta;
	
	@Transient
	private Double valorTotalByProposta = 0.0;
	
	@Override
	public String getDescricao() {
		return descricao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#setDescricao(java.lang.String)
	 */
	@Override
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#getQuantidade()
	 */
	@Override
	public Long getQuantidade() {
	
		return quantidade;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#setQuantidade(long)
	 */
	@Override
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#getValorUnitario()
	 */
	@Override
	public Double getValorUnitario() {
	
		return valorUnitario;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#setValorUnitario(double)
	 */
	@Override
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#getValorTotal()
	 */
	@Override
	public Double getValorTotal() {
		return valorTotal;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#setValorTotal(double)
	 */
	@Override
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the proposta
	 */
	@Override
	public IProposta getProposta() {
		return proposta;
	}

	/**
	 * @param proposta the proposta to set
	 */
	@Override
	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	@Override
	public Double gevalorTotal() {
		return valorTotal;
	}

	
	public Double getValorTotalByProposta() {
		return valorTotalByProposta;
	}

	public void setValorTotalByProposta(Double valorTotalByProposta) {
		this.valorTotalByProposta = valorTotalByProposta;
	}

	public void setProposta(IProposta proposta) {
		this.proposta = proposta;
	}

	@Override
	public Double getDesconto() {
		return desconto;
	}

	@Override
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	@Override
	public Double getValorNegociado() {
		return valorNegociado;
	}

	@Override
	public void setValorNegociado(Double valorNegociado) {
		this.valorNegociado = valorNegociado;
	}
	

}
