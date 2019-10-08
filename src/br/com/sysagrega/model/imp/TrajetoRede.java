
package br.com.sysagrega.model.imp;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sysagrega.model.ITrajetoRede;

@Entity
@Table(name = "TB_TRAJETO_REDE", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_tra_rede", sequenceName = "agrega.tb_seq_tra_rede", allocationSize = 1)
public class TrajetoRede implements ITrajetoRede{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_tra_rede")
	private Long id;
	 
	private String pontoInicio;
	
	private String pontoFim;
	
	/*private String descricao;*/
	
	private Long projeto;
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
/*	
	private Long ordem;*/
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getPontoInicio() {
		return pontoInicio;
	}

	@Override
	public void setPontoInicio(String pontoInicio) {
		this.pontoInicio = pontoInicio;
	}

	@Override
	public String getPontoFim() {
		return pontoFim;
	}

	@Override
	public void setPontoFim(String pontoFim) {
		this.pontoFim = pontoFim;
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
	public Date getDataCadastro() {
		return dataCadastro;
	}
	@Override
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	 
	 
}
