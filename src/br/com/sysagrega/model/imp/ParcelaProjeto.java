package br.com.sysagrega.model.imp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.IParcelaProjeto;
import br.com.sysagrega.model.IProjeto;

@Entity
@Table(name = "TB_PARCELA_PROJETO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_parcela_proj_seq", sequenceName = "agrega.tb_parcela_proj_seq", allocationSize = 1)
public class ParcelaProjeto implements IParcelaProjeto{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_parcela_proj_seq")
	private Long id;

	 
	private Long numeroParcela;
	
	private Double valorParcela;
	
	private Date dataCobranca;
	
	/*@ManyToOne(targetEntity = NotaFiscal.class)
	@JoinColumn(name = "id_nota_fiscal")
	private NotaFiscal notaFiscal;*/
	
	private Long notaFiscal;
	
	private String observacao;
	
	@ManyToOne(targetEntity = Projeto.class)
	@JoinColumn(name = "ID_PROJETO")
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
	public Long getNumeroParcela() {
		return numeroParcela;
	}

	@Override
	public void setNumeroParcela(Long numeroParcela) {
		this.numeroParcela = numeroParcela;
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
	public Date getDataCobranca() {
		return dataCobranca;
	}

	@Override
	public void setDataCobranca(Date dataCobranca) {
		this.dataCobranca = dataCobranca;
	}

	/*@Override
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	@Override
	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}*/
	
	

	@Override
	public String getObservacao() {
		return observacao;
	}

	@Override
	public Long getNotaFiscal() {
		return notaFiscal;
	}

	@Override
	public void setNotaFiscal(Long notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	@Override
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public IProjeto getProjeto() {
		return projeto;
	}

	@Override
	public void setProjeto(IProjeto projeto) {
		this.projeto = projeto;
	}
	
	

	
}
