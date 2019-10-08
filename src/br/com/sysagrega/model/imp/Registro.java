
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

import br.com.sysagrega.model.IProjeto;
import br.com.sysagrega.model.IRegistro;

@Entity
@Table(name = "TB_REGISTRO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_registro", sequenceName = "agrega.tb_seq_registro", allocationSize = 1)
public class Registro implements IRegistro{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_registro")
	private Long id;
	
	private String tipoRegistro;
	 
	private String numeroRequerimento;
	
	private String numeroProcessoFormado;
	
	private Date dataFormacaoProcesso;
	
	private Long gerouDAE;
	
	private String motivoNaoGerouDae;
	
	private Double valorDAE;
	
	private Date dataPagamento;
	
	private Date dataEntrega;
	
	@OneToOne(targetEntity = Projeto.class, cascade = CascadeType.REFRESH)
	private IProjeto projeto;
	

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getNumeroRequerimento() {
		return numeroRequerimento;
	}

	@Override
	public void setNumeroRequerimento(String numeroRequerimento) {
		this.numeroRequerimento = numeroRequerimento;
	}

	@Override
	public String getNumeroProcessoFormado() {
		return numeroProcessoFormado;
	}

	@Override
	public void setNumeroProcessoFormado(String numeroProcessoFormado) {
		this.numeroProcessoFormado = numeroProcessoFormado;
	}

	@Override
	public Date getDataFormacaoProcesso() {
		return dataFormacaoProcesso;
	}

	@Override
	public void setDataFormacaoProcesso(Date dataFormacaoProcesso) {
		this.dataFormacaoProcesso = dataFormacaoProcesso;
	}

	@Override
	public Long getGerouDAE() {
		return gerouDAE;
	}

	@Override
	public void setGerouDAE(Long gerouDAE) {
		this.gerouDAE = gerouDAE;
	}
	
	
	@Override
	public String getMotivoNaoGerouDae() {
		return motivoNaoGerouDae;
	}
	@Override
	public void setMotivoNaoGerouDae(String motivoNaoGerouDae) {
		this.motivoNaoGerouDae = motivoNaoGerouDae;
	}

	@Override
	public Double getValorDAE() {
		return valorDAE;
	}

	@Override
	public void setValorDAE(Double valorDAE) {
		this.valorDAE = valorDAE;
	}

	@Override
	public Date getDataPagamento() {
		return dataPagamento;
	}

	@Override
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Override
	public Date getDataEntrega() {
		return dataEntrega;
	}

	@Override
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
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
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	@Override
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	
}
