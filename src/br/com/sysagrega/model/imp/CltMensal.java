package br.com.sysagrega.model.imp;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sysagrega.model.ICltMensal;

@Entity
@Table(name  = "TB_CLT_MENSAL", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_cltMsl", sequenceName = "agrega.tb_seq_cltMsl", allocationSize = 1)
public class CltMensal implements ICltMensal {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COD", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_cltMsl")
	private Long cod;
	private Date dateCriacao;
	private String status;
	private BigDecimal fgts;
	private BigDecimal rat;
	private BigDecimal entidades;
	private BigDecimal multaFgts;
	private BigDecimal inssPatronal;
	private BigDecimal ajudaVt;
	private BigDecimal ajudaVa;
	private Boolean feriasMes;
	private Boolean ferias13Mes;
	private Boolean salario13Mes;
	private Boolean avisoPrevioMes;
	private boolean ativo;
	
	@Transient
	@Override
	public boolean isNovo() {
		return getCod() == null;
	}

	@Transient
	@Override
	public boolean isExistente() {
		return !isNovo();
	}
	
	@Override
	public Long getCod() {
		return cod;
	}
	@Override
	public void setCod(Long cod) {
		this.cod = cod;
	}
	@Override
	public Date getDateCriacao() {
		return dateCriacao;
	}
	@Override
	public void setDateCriacao(Date dateCriacao) {
		this.dateCriacao = dateCriacao;
	}
	@Override
	public String getStatus() {
		return status;
	}
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public BigDecimal getFgts() {
		return fgts;
	}
	@Override
	public void setFgts(BigDecimal fgts) {
		this.fgts = fgts;
	}
	@Override
	public BigDecimal getRat() {
		return rat;
	}
	@Override
	public void setRat(BigDecimal rat) {
		this.rat = rat;
	}
	@Override
	public BigDecimal getEntidades() {
		return entidades;
	}
	@Override
	public void setEntidades(BigDecimal entidades) {
		this.entidades = entidades;
	}
	@Override
	public BigDecimal getMultaFgts() {
		return multaFgts;
	}
	@Override
	public void setMultaFgts(BigDecimal multaFgts) {
		this.multaFgts = multaFgts;
	}
	@Override
	public BigDecimal getInssPatronal() {
		return inssPatronal;
	}
	@Override
	public void setInssPatronal(BigDecimal inssPatronal) {
		this.inssPatronal = inssPatronal;
	}
	@Override
	public BigDecimal getAjudaVt() {
		return ajudaVt;
	}
	@Override
	public void setAjudaVt(BigDecimal ajudaVt) {
		this.ajudaVt = ajudaVt;
	}
	@Override
	public BigDecimal getAjudaVa() {
		return ajudaVa;
	}
	@Override
	public void setAjudaVa(BigDecimal ajudaVa) {
		this.ajudaVa = ajudaVa;
	}
	@Override
	public Boolean getFeriasMes() {
		return feriasMes;
	}
	@Override
	public void setFeriasMes(Boolean feriasMes) {
		this.feriasMes = feriasMes;
	}
	@Override
	public Boolean getFerias13Mes() {
		return ferias13Mes;
	}
	@Override
	public void setFerias13Mes(Boolean ferias13Mes) {
		this.ferias13Mes = ferias13Mes;
	}
	@Override
	public Boolean getSalario13Mes() {
		return salario13Mes;
	}
	@Override
	public void setSalario13Mes(Boolean salario13Mes) {
		this.salario13Mes = salario13Mes;
	}
	@Override
	public Boolean getAvisoPrevioMes() {
		return avisoPrevioMes;
	}
	@Override
	public void setAvisoPrevioMes(Boolean avisoPrevioMes) {
		this.avisoPrevioMes = avisoPrevioMes;
	}
	@Override
	public boolean isAtivo() {
		return ativo;
	}
	@Override
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
		
}
