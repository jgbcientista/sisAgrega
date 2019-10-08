
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

import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.IRetificacao;
import br.com.sysagrega.model.IStatus;
import br.com.sysagrega.model.ITipoRetificacao;

@Entity
@Table(name = "TB_RETIFICACAO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_ret", sequenceName = "agrega.tb_seq_ret", allocationSize = 1)
public class Retificacao implements IRetificacao{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_ret")
	private Long id;
	
	private Long numero;
	
	@Temporal(TemporalType.DATE)
	private Date dataEnvio;
	
	private String descricao;
	
	@OneToOne(targetEntity = Profissional.class, cascade = CascadeType.REFRESH)
	private IProfissional responsavelResposta;
	
	@OneToOne(targetEntity = TipoRetificacao.class, cascade = CascadeType.REFRESH)
	private ITipoRetificacao tipoRetificacao;
	
	@OneToOne(targetEntity = Status.class, cascade = CascadeType.REFRESH)
	private IStatus status;
	
	private String resumoResposta;
	
	private String descricaoProblema;
	
	@Temporal(TemporalType.DATE)
	private Date dataResposta;
	
	private Long projeto;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getNumero() {
		return numero;
	}

	@Override
	public void setNumero(Long numero) {
		this.numero = numero;
	}

	@Override
	public ITipoRetificacao getTipoRetificacao() {
		return tipoRetificacao;
	}
	@Override
	public void setTipoRetificacao(ITipoRetificacao tipoRetificacao) {
		this.tipoRetificacao = tipoRetificacao;
	}

	@Override
	public Date getDataEnvio() {
		return dataEnvio;
	}

	@Override
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
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
	public IProfissional getResponsavelResposta() {
		return responsavelResposta;
	}

	@Override
	public void setResponsavelResposta(IProfissional responsavelResposta) {
		this.responsavelResposta = responsavelResposta;
	}

	@Override
	public String getResumoResposta() {
		return resumoResposta;
	}

	@Override
	public void setResumoResposta(String resumoResposta) {
		this.resumoResposta = resumoResposta;
	}

	@Override
	public Date getDataResposta() {
		return dataResposta;
	}

	@Override
	public void setDataResposta(Date dataResposta) {
		this.dataResposta = dataResposta;
	}

	@Override
	public Long getProjeto() {
		return projeto;
	}

	@Override
	public void setProjeto(Long projeto) {
		this.projeto = projeto;
	}
	@Override
	public IStatus getStatus() {
		return status;
	}
	@Override
	public void setStatus(IStatus status) {
		this.status = status;
	}
	@Override
	public String getDescricaoProblema() {
		return descricaoProblema;
	}
	@Override
	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}

 
	
	
	 
}
