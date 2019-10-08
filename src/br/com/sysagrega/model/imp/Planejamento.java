
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

import br.com.sysagrega.model.IPlanejamento;
import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.IProjeto;

@Entity
@Table(name = "TB_PLANEJAMENTO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_plane", sequenceName = "agrega.tb_seq_plane", allocationSize = 1)
public class Planejamento implements IPlanejamento{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_plane")
	private Long id;
	
	
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private IProfissional profissionalEPI;
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private IProfissional profissionalEVCTGAL;
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private IProfissional profissionalMapas;
	
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private IProfissional profissionalVerificacao;
	
	
/*	@Temporal(TemporalType.DATE)*/
	private Date dtInicioPlanejamento;
	
	/*@Temporal(TemporalType.DATE)*/
	private Date dtfimPlanejamento;
	
/*	@Temporal(TemporalType.DATE)*/
	private Date dtEntrega;
	
	@Column(length=1001)
	private String obsPlanejamento;
	
	@Temporal(TemporalType.DATE)
	private Date dataInsercao;
	
	@OneToOne(targetEntity = Projeto.class, cascade = CascadeType.REFRESH)
	private IProjeto projeto;
	
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
	public IProfissional getProfissionalVerificacao() {
		return profissionalVerificacao;
	}

	@Override
	public void setProfissionalVerificacao(IProfissional profissionalVerificacao) {
		this.profissionalVerificacao = profissionalVerificacao;
	}

	@Override
	public IProfissional getProfissionalMapas() {
		return profissionalMapas;
	}
	
	@Override
	public void setProfissionalMapas(IProfissional profissionalMapas) {
		this.profissionalMapas = profissionalMapas;
	}

	@Override
	public IProfissional getProfissionalEPI() {
		return profissionalEPI;
	}

	@Override
	public void setProfissionalEPI(IProfissional profissionalEPI) {
		this.profissionalEPI = profissionalEPI;
	}

	@Override
	public IProfissional getProfissionalEVCTGAL() {
		return profissionalEVCTGAL;
	}

	@Override
	public void setProfissionalEVCTGAL(IProfissional profissionalEVCTGAL) {
		this.profissionalEVCTGAL = profissionalEVCTGAL;
	}


	@Override
	public Date getDtEntrega() {
		return dtEntrega;
	}

	@Override
	public void setDtEntrega(Date dtEntrega) {
		this.dtEntrega = dtEntrega;
	}

	@Override
	public String getObsPlanejamento() {
		return obsPlanejamento;
	}

	@Override
	public void setObsPlanejamento(String obsPlanejamento) {
		this.obsPlanejamento = obsPlanejamento;
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
	public Date getDtInicioPlanejamento() {
		return dtInicioPlanejamento;
	}
	@Override
	public void setDtInicioPlanejamento(Date dtInicioPlanejamento) {
		this.dtInicioPlanejamento = dtInicioPlanejamento;
	}
	@Override
	public Date getDtfimPlanejamento() {
		return dtfimPlanejamento;
	}
	@Override
	public void setDtfimPlanejamento(Date dtfimPlanejamento) {
		this.dtfimPlanejamento = dtfimPlanejamento;
	}
	@Override
	public Date getDataInsercao() {
		return dataInsercao;
	}
	@Override
	public void setDataInsercao(Date dataInsercao) {
		this.dataInsercao = dataInsercao;
	}
	
	
}
