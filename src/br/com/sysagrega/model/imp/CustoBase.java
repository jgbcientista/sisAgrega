package br.com.sysagrega.model.imp;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sysagrega.model.ICustoBase;
import br.com.sysagrega.model.IProposta;

@MappedSuperclass
public abstract class CustoBase implements ICustoBase {

	private static final long serialVersionUID = 1L;

	private String descricao;

	private Long quantidade;

	private Double valorUnitario;

	private Double valorTotal;
	
	private String observacoes;
	
	//para unidade medida
	private String tpUnidadeMedida;
	
	@OneToOne(targetEntity = Proposta.class, cascade = CascadeType.REFRESH)
	private IProposta proposta;
	
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	
	@Temporal(TemporalType.DATE)
	private Date dataAtualizacao;
	
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.REFRESH)
	private Usuario usuarioAtualizacao;
	
	private boolean ativo;

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

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.ICustoBase#getPrecificacao()
	 */
//	@Override
//	public IPrecificacao getPrecificacao() {
//		return precificacao;
//	}
//
//	/* (non-Javadoc)
//	 * @see br.com.sysagrega.model.imp.ICustoBase#setPrecificacao(br.com.sysagrega.model.IPrecificacao)
//	 */
//	@Override
//	public void setPrecificacao(IPrecificacao precificacao) {
//		this.precificacao = precificacao;
//	}

	/**
	 * @return the observacoes
	 */
	@Override
	public String getObservacoes() {
		return observacoes;
	}

	/**
	 * @param observacoes the observacoes to set
	 */
	@Override
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CustoBase))
			return false;
		CustoBase other = (CustoBase) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (proposta == null) {
			if (other.proposta != null)
				return false;
		} else if (!proposta.equals(other.proposta))
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (Double.doubleToLongBits(valorTotal) != Double.doubleToLongBits(other.valorTotal))
			return false;
		if (Double.doubleToLongBits(valorUnitario) != Double.doubleToLongBits(other.valorUnitario))
			return false;
		return true;
	}
	
	@Override
	public void setProposta(IProposta proposta) {
		this.proposta = proposta;
	}

	@Override
	public IProposta getProposta() {
		return proposta;
	}

	@Override
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	@Override
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	@Override
	public Usuario getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}
	@Override
	public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}
	@Override
	public Date getDataInclusao() {
		return dataInclusao;
	}
	@Override
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	@Override
	public boolean isAtivo() {
		return ativo;
	}
	@Override
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	@Override
	public String getTpUnidadeMedida() {
		return tpUnidadeMedida;
	}
	@Override
	public void setTpUnidadeMedida(String tpUnidadeMedida) {
		this.tpUnidadeMedida = tpUnidadeMedida;
	}
	
	
	
}
