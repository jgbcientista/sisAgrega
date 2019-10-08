package br.com.sysagrega.model.imp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sysagrega.model.IParcela;

@Entity
@Table(name  = "TB_PARCELA", schema="agrega")
@SequenceGenerator(name = "agrega.tb_parcela_seq", sequenceName = "agrega.tb_parcela_seq", allocationSize = 1)
public class Parcela implements IParcela {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_parcela_seq")
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	private Double valorParcela;
	
	private Integer nrParcela;
	
	private String observacoesParcela;
	
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Date getDataVencimento() {
		return dataVencimento;
	}

	@Override
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Override
	public Double getValorParcela() {
		return valorParcela;
	}

	@Override
	public void setValorParcela(Double valorParcela) {
		this.valorParcela = valorParcela;
	}

	@Override
	public Integer getNrParcela() {
		return nrParcela;
	}

	@Override
	public void setNrParcela(Integer nrParcela) {
		this.nrParcela = nrParcela;
	}

	@Override
	public String getObservacoesParcela() {
		return observacoesParcela;
	}

	@Override
	public void setObservacoesParcela(String observacoesParcela) {
		this.observacoesParcela = observacoesParcela;
	}
	
}
