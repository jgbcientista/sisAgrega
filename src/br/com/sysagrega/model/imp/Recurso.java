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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sysagrega.model.IRecurso;

@Entity
@Table(name = "TB_RECURSO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_recur", sequenceName = "agrega.tb_seq_recur", allocationSize = 1)
public class Recurso implements IRecurso {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_recur")
	private Long id;
	
	String nome;
	
	String fabricante;
	
	String status;
	
	@Temporal(TemporalType.DATE)
	private Date validade;
	
	@Temporal(TemporalType.DATE)
	private Date  garantia;
	
	@Column(length=501)
	String descricao;
	
	@Temporal(TemporalType.DATE)
	private Date dataAtualizacao;
	
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.REFRESH)
	private Usuario usuarioAtualizacao;
	
	@OneToOne(targetEntity = AplicacaoRecurso.class, cascade = CascadeType.REFRESH)
	private AplicacaoRecurso aplicacao;
	
	@OneToOne(targetEntity = TipoRecurso.class, cascade = CascadeType.REFRESH)
	private TipoRecurso tipoRecurso;
	
	@OneToOne(targetEntity =ClassificacaoRecurso.class, cascade = CascadeType.REFRESH)
	private ClassificacaoRecurso classificacaoRecurso;
	
	@OneToOne(targetEntity =Profissional.class, cascade = CascadeType.REFRESH)
	private Profissional profissional;

	private String depreciacao;
	private String armazenamento;
	private String custo;
	@Temporal(TemporalType.DATE)
	private Date dataAquisicao;
	private Long numeroSerie;
	
	private String marca;
	private String fornecedor;
	private Long quantidade;
	
	
	
	
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	
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

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome =nome;
		
	}

	@Override
	public String getFabricante() {
		return fabricante;
	}

	@Override
	public void setFabricante(String fabricante) {
		this.fabricante =fabricante;
		
	}

	@Override
	public Date getValidade() {
		return validade;
	}

	@Override
	public void setValidade(Date validade) {
		this.validade = validade;
		
	}

	@Override
	public Date getGarantia() {
		return garantia;
	}

	@Override
	public void setGarantia(Date garantia) {
		this.garantia = garantia;
		
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public void setDescricao(String descricao) {
		this.descricao = descricao;
		
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	@Override
	public AplicacaoRecurso getAplicacao() {
		return aplicacao;
	}
	@Override
	public void setAplicacao(AplicacaoRecurso aplicacao) {
		this.aplicacao = aplicacao;
	}
	@Override
	public TipoRecurso getTipoRecurso() {
		return tipoRecurso;
	}
	@Override
	public void setTipoRecurso(TipoRecurso tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}
	@Override
	public ClassificacaoRecurso getClassificacaoRecurso() {
		return classificacaoRecurso;
	}
	@Override
	public void setClassificacaoRecurso(ClassificacaoRecurso classificacaoRecurso) {
		this.classificacaoRecurso = classificacaoRecurso;
	}
	@Override
	public String getDepreciacao() {
		return depreciacao;
	}
	@Override
	public void setDepreciacao(String depreciacao) {
		this.depreciacao = depreciacao;
	}
	@Override
	public String getArmazenamento() {
		return armazenamento;
	}
	@Override
	public void setArmazenamento(String armazenamento) {
		this.armazenamento = armazenamento;
	}
	@Override
	public String getCusto() {
		return custo;
	}
	@Override
	public void setCusto(String custo) {
		this.custo = custo;
	}
	@Override
	public Date getDataAquisicao() {
		return dataAquisicao;
	}
	@Override
	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}
	@Override
	public Long getNumeroSerie() {
		return numeroSerie;
	}
	@Override
	public void setNumeroSerie(Long numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	@Override
	public String getMarca() {
		return marca;
	}
	@Override
	public void setMarca(String marca) {
		this.marca = marca;
	}
	@Override
	public String getFornecedor() {
		return fornecedor;
	}
	@Override
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	 
	@Override
	public Long getQuantidade() {
		return quantidade;
	}
	@Override
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	@Override
	public Profissional getProfissional() {
		return profissional;
	}
	@Override
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	@Override
	public String getStatus() {
		return status;
	}
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	

}
