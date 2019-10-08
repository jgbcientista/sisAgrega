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

import br.com.sysagrega.model.ICustoPlanilhaTecnica;
import br.com.sysagrega.model.IProposta;

@Entity
@Table(name = "TB_CUSTO_PLANINHA_TECNICA", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_plantec", sequenceName = "agrega.tb_seq_plantec", allocationSize = 1)
public class CustoPlanilhaTecnica implements ICustoPlanilhaTecnica {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_plantec")
	private Long id;
	
	private String descricao;
	private Long quantitativo;
	private Integer unidade;
	
	
	@OneToOne(targetEntity = Proposta.class, cascade = CascadeType.REFRESH)
	IProposta proposta;
		
	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public IProposta getProposta() {
		return proposta;
	}

	@Override
	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	@Override
	public Long getQuantitativo() {
		return quantitativo;
	}

	@Override
	public void setQuantitativo(Long quantitativo) {
		this.quantitativo = quantitativo;
	}

	@Override
	public Integer getUnidade() {
		return unidade;
	}

	@Override
	public void setUnidade(Integer unidade) {
		this.unidade = unidade;
	}

	

}
