
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

import br.com.sysagrega.model.IPlanejamentos;

@Entity
@Table(name = "TB_PLANEJAMENTOS", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_planjs", sequenceName = "agrega.tb_seq_planjs", allocationSize = 1)
public class Planejamentos implements IPlanejamentos{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_planjs")
	private Long id;
	
	private String nome;
	
	private Integer nrPlan;
	
	private Integer nrRevisao;
	
	@Temporal(TemporalType.DATE)
	private Date dtCriacaoPlanej;
	
	@Temporal(TemporalType.DATE)
	private Date dtAlteracaoPlanej;
	
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.REFRESH)
	private Usuario usuarioAtualizacao;
	
	private String titulo;
		
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
	public Integer getNrRevisao() {
		return nrRevisao;
	}

	@Override
	public void setNrRevisao(Integer nrRevisao) {
		this.nrRevisao = nrRevisao;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public Date getDtAlteracaoPlanej() {
		return dtAlteracaoPlanej;
	}

	@Override
	public void setDtAlteracaoPlanej(Date dtAlteracaoPlanej) {
		this.dtAlteracaoPlanej = dtAlteracaoPlanej;
	}
	@Override
	public Date getDtCriacaoPlanej() {
		return dtCriacaoPlanej;
	}
	@Override
	public void setDtCriacaoPlanej(Date dtCriacaoPlanej) {
		this.dtCriacaoPlanej = dtCriacaoPlanej;
	}
	@Override
	public Integer getNrPlan() {
		return nrPlan;
	}
	@Override
	public void setNrPlan(Integer nrPlan) {
		this.nrPlan = nrPlan;
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
	public String getTitulo() {
		return titulo;
	}
	
	@Override
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
