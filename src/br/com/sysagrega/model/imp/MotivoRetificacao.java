
package br.com.sysagrega.model.imp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.IMotivoRetificacao;

@Entity
@Table(name = "TB_MOTIVO_RETIFICACAO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_micro_mot_seq", sequenceName = "agrega.tb_micro_mot_seq", allocationSize = 1)
public class MotivoRetificacao implements IMotivoRetificacao{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_micro_mot_seq")
	private Long id;
	
	private String motivoRetificacao;

	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getMotivoRetificacao() {
		return motivoRetificacao;
	}

	@Override
	public void setMotivoRetificacao(String motivoRetificacao) {
		this.motivoRetificacao = motivoRetificacao;
	}
	
	 
}
