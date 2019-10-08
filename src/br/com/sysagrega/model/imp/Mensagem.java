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

import br.com.sysagrega.model.IMensagem;

@Entity
@Table(name = "TB_MENSAGEM", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_mensagem", sequenceName = "agrega.tb_seq_mensagem", allocationSize = 1)
public class Mensagem implements IMensagem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_mensagem")
	private Long id;
	
	private String titulo;
	
	@Column(length=501)
	private String descricao;
	
	private Long tipo;
	
	private Long contador;
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private Profissional profissional;
	
	@OneToOne(targetEntity = Projeto.class, cascade = CascadeType.REFRESH)
	private Projeto projeto;
	
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	
	@Temporal(TemporalType.DATE)
	private Date dataAlteracao;
	
	private boolean lida;
	 
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String getTitulo() {
		return titulo;
	}
	@Override
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	public Long getTipo() {
		return tipo;
	}
	@Override
	public void setTipo(Long tipo) {
		this.tipo = tipo;
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
	public Date getDataInclusao() {
		return dataInclusao;
	}
	@Override
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	@Override
	public boolean isLida() {
		return lida;
	}
	@Override
	public void setLida(boolean lida) {
		this.lida = lida;
	}
	@Override
	public Projeto getProjeto() {
		return projeto;
	}
	@Override
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	@Override
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	@Override
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	@Override
	public Long getContador() {
		return contador;
	}
	@Override
	public void setContador(Long contador) {
		this.contador = contador;
	}

}
