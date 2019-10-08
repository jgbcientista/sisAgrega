package br.com.sysagrega.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public interface ICltMensal extends Serializable{

	boolean isNovo();
	boolean isExistente();
	Long getCod();
	void setCod(Long cod);
	Date getDateCriacao();
	void setDateCriacao(Date dateCriacao);
	String getStatus();
	void setStatus(String status);
	BigDecimal getFgts();
	void setFgts(BigDecimal fgts);
	BigDecimal getRat();
	void setRat(BigDecimal rat);
	BigDecimal getEntidades();
	void setEntidades(BigDecimal entidades);
	BigDecimal getMultaFgts();
	void setMultaFgts(BigDecimal multaFgts);
	BigDecimal getInssPatronal();
	void setInssPatronal(BigDecimal inssPatronal);
	BigDecimal getAjudaVt();
	void setAjudaVt(BigDecimal ajudaVt);
	BigDecimal getAjudaVa();
	void setAjudaVa(BigDecimal ajudaVa);
	Boolean getFeriasMes();
	void setFeriasMes(Boolean feriasMes);
	Boolean getFerias13Mes();
	void setFerias13Mes(Boolean ferias13Mes);
	Boolean getSalario13Mes();
	void setSalario13Mes(Boolean salario13Mes);
	Boolean getAvisoPrevioMes();
	void setAvisoPrevioMes(Boolean avisoPrevioMes);
	boolean isAtivo();
	void setAtivo(boolean ativo);

	

}