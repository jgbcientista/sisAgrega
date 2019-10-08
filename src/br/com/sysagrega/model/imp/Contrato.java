package br.com.sysagrega.model.imp;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sysagrega.model.IContrato;

@Entity
@Table(name  = "TB_CONTRATO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_contr", sequenceName = "agrega.tb_seq_contr", allocationSize = 1)
public class Contrato implements IContrato {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_contr")
	private Long id;
	
	private String nrContrato;
	
	private String nomeCliente;
	
	@OneToOne(targetEntity = Proposta.class, cascade = CascadeType.REFRESH)
	private Proposta proposta;
	
	private Date dataAssinatura; 
	
	private Double vlrContrato;
	
	private Date vigenciaDataInicial;
	
	private Date vigenciaDataFinal;
	
	private String formaPagamento;
	
	private Integer quantidadeParcela;
	
	private Date dataVencimento;
	
	private Double valorParcela;
	
	private String observacoesParcelas;
	
	@Column(length=1010)
	private String informacoesClausulas;
	
	@Column(length=1010)
	private String consideracoesFinais;
	
	private String statusContrato;
	
	private Double saldoContrato;
	
	@OneToMany(targetEntity = Parcela.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "contratoId")
	private Set<Parcela> parcelas;
	
	@Temporal(TemporalType.DATE)
	private Date dataAtualizacao;
	
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.REFRESH)
	private Usuario usuarioAtualizacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	
	@Temporal(TemporalType.DATE)
	private Date dataDistrato;
	
	@Temporal(TemporalType.DATE)
	private Date dataCancelamento;
	
	private boolean ativo;
	
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

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IContrato#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IContrato#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getNrContrato() {
		return nrContrato;
	}

	@Override
	public void setNrContrato(String nrContrato) {
		this.nrContrato = nrContrato;
	}

	@Override
	public Proposta getProposta() {
		return proposta;
	}

	@Override
	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	@Override
	public Date getDataAssinatura() {
		return dataAssinatura;
	}

	@Override
	public void setDataAssinatura(Date dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}

	@Override
	public Double getVlrContrato() {
		return vlrContrato;
	}

	@Override
	public void setVlrContrato(Double vlrContrato) {
		this.vlrContrato = vlrContrato;
	}

	@Override
	public Date getVigenciaDataInicial() {
		return vigenciaDataInicial;
	}

	@Override
	public void setVigenciaDataInicial(Date vigenciaDataInicial) {
		this.vigenciaDataInicial = vigenciaDataInicial;
	}

	@Override
	public Date getVigenciaDataFinal() {
		return vigenciaDataFinal;
	}

	@Override
	public void setVigenciaDataFinal(Date vigenciaDataFinal) {
		this.vigenciaDataFinal = vigenciaDataFinal;
	}

	@Override
	public String getFormaPagamento() {
		return formaPagamento;
	}

	@Override
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@Override
	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}

	@Override
	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
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
	public String getObservacoesParcelas() {
		return observacoesParcelas;
	}

	@Override
	public void setObservacoesParcelas(String observacoesParcelas) {
		this.observacoesParcelas = observacoesParcelas;
	}

	@Override
	public String getInformacoesClausulas() {
		return informacoesClausulas;
	}

	@Override
	public void setInformacoesClausulas(String informacoesClausulas) {
		this.informacoesClausulas = informacoesClausulas;
	}

	@Override
	public String getConsideracoesFinais() {
		return consideracoesFinais;
	}

	@Override
	public void setConsideracoesFinais(String consideracoesFinais) {
		this.consideracoesFinais = consideracoesFinais;
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
	public Set<Parcela> getParcelas() {
		return parcelas;
	}

	@Override
	public void setParcelas(Set<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	@Override
	public String getStatusContrato() {
		return statusContrato;
	}

	@Override
	public void setStatusContrato(String statusContrato) {
		this.statusContrato = statusContrato;
	}

	@Override
	public Double getSaldoContrato() {
		return saldoContrato;
	}

	@Override
	public void setSaldoContrato(Double saldoContrato) {
		this.saldoContrato = saldoContrato;
	}
	@Override
	public String getNomeCliente() {
		return nomeCliente;
	}
	@Override
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	@Override
	public Date getDataDistrato() {
		return dataDistrato;
	}
	@Override
	public void setDataDistrato(Date dataDistrato) {
		this.dataDistrato = dataDistrato;
	}
	@Override
	public Date getDataCancelamento() {
		return dataCancelamento;
	}
	@Override
	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}
	
	
	
	
	

		
}
