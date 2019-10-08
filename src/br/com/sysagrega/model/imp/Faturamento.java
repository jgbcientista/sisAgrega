
package br.com.sysagrega.model.imp;


import java.util.Date;

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

import br.com.sysagrega.model.IContrato;
import br.com.sysagrega.model.IFaturamento;
import br.com.sysagrega.model.IProjeto;

@Entity
@Table(name = "TB_FATURAMENTO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_nota_fat_seq", sequenceName = "agrega.tb_nota_fat_seq", allocationSize = 1)
public class Faturamento implements IFaturamento{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_nota_fat_seq")
	private Long id;
	 
	private Long numeroNF;
	
	private Date dataNF;
	
	private Long parcela;
	
	private Double valorParcela;
	
	@OneToOne(targetEntity = Situacao.class, cascade = CascadeType.REFRESH)
	private Situacao situacao;
	
	@OneToOne(targetEntity = Projeto.class, cascade = CascadeType.REFRESH)
	private IProjeto projeto;
	
	@OneToOne(targetEntity = Contrato.class, cascade = CascadeType.REFRESH)
	private IContrato contrato;
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private Profissional responsavel;
	
	@Transient
	Long CONSTANTE_PRIMEIRA_PARCELA = 30L;
	@Transient
	Long CONSTANTE_SEGUNDA_PARCELA = 50L;
	@Transient
	Long CONSTANTE_TERCEIRA_PARCELA = 20L;
	
	public Double getObterValorByParcela(Long parcela){
		Double valorParcela = 0.0;
		
		if(parcela == 1L){
			parcela = CONSTANTE_PRIMEIRA_PARCELA;	
		}else if(parcela == 2L){
			parcela = CONSTANTE_SEGUNDA_PARCELA;
		} else if(parcela == 3L){
			parcela = CONSTANTE_TERCEIRA_PARCELA;
		}
		
		if(projeto != null && projeto.getValorProjeto() != null){
			valorParcela = (projeto.getValorProjeto())*parcela/100;
		}
		return valorParcela;
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
	public Long getNumeroNF() {
		return numeroNF;
	}

	@Override
	public void setNumeroNF(Long numeroNF) {
		this.numeroNF = numeroNF;
	}

	@Override
	public Date getDataNF() {
		return dataNF;
	}

	@Override
	public void setDataNF(Date dataNF) {
		this.dataNF = dataNF;
	}
	 
	@Override
	public Long getParcela() {
		return parcela;
	}
	@Override
	public void setParcela(Long parcela) {
		this.parcela = parcela;
	}

	@Override
	public IProjeto getProjeto() {
		return projeto;
	}

	@Override
	public void setProjeto(IProjeto projeto) {
		this.projeto = projeto;
	}

	@Override
	public Profissional getResponsavel() {
		return responsavel;
	}

	@Override
	public void setResponsavel(Profissional responsavel) {
		this.responsavel = responsavel;
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
	public Situacao getSituacao() {
		return situacao;
	}
	@Override
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	@Override
	public IContrato getContrato() {
		return contrato;
	}
	@Override
	public void setContrato(IContrato contrato) {
		this.contrato = contrato;
	}

	
}
