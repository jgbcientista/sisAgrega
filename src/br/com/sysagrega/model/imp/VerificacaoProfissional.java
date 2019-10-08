
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

import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.IProjeto;
import br.com.sysagrega.model.IVerificacaoProfissional;

@Entity
@Table(name = "TB_VERIFICACAO_PROFISSIONAL", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_veri_prof", sequenceName = "agrega.tb_seq_veri_prof", allocationSize = 1)
public class VerificacaoProfissional implements IVerificacaoProfissional{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_veri_prof")
	private Long id;
	
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private IProfissional profissionalVerificacao;
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private IProfissional profissionalMapas;
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private IProfissional profissionalEPI;
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private IProfissional profissionalEVCTGAL;
	
	@Temporal(TemporalType.DATE)
	private Date dtInicioPlanejamento;
	
	@Temporal(TemporalType.DATE)
	private Date dtFimPlanejamento;
	
	@Temporal(TemporalType.DATE)
	private Date dtInicioCampo;
	
	@Temporal(TemporalType.DATE)
	private Date dtFimCampo;
	
	
	@Temporal(TemporalType.DATE)
	private Date dataEntrega;
	
	@Column(length=1000)
	private String observacaoPlanejamento;
	
	@OneToOne(targetEntity = Projeto.class, cascade = CascadeType.REMOVE)
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
	public String getObservacaoPlanejamento() {
		return observacaoPlanejamento;
	}

	@Override
	public void setObservacaoPlanejamento(String observacaoPlanejamento) {
		this.observacaoPlanejamento = observacaoPlanejamento;
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
	public Date getDataEntrega() {
		return dataEntrega;
	}

	@Override
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
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
	public Date getDtFimPlanejamento() {
		return dtFimPlanejamento;
	}
	@Override
	public void setDtFimPlanejamento(Date dtFimPlanejamento) {
		this.dtFimPlanejamento = dtFimPlanejamento;
	}
	@Override
	public Date getDtInicioCampo() {
		return dtInicioCampo;
	}
	@Override
	public void setDtInicioCampo(Date dtInicioCampo) {
		this.dtInicioCampo = dtInicioCampo;
	}
	@Override
	public Date getDtFimCampo() {
		return dtFimCampo;
	}
	@Override
	public void setDtFimCampo(Date dtFimCampo) {
		this.dtFimCampo = dtFimCampo;
	}
	 

 
	
	
}
