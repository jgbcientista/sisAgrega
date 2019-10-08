package br.com.sysagrega.model.imp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.ITipoNotificacao;

@Entity
@Table(name = "TB_TIPO_NOTIFICACAO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_tip_not", sequenceName = "agrega.tb_seq_tip_not", allocationSize = 1)
public class TipoNotificacao implements ITipoNotificacao {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_tip_not")
	private Long id;

	private String descricao;
	
	public TipoNotificacao() {
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
	public String getDescricao() {
		return descricao;
	}

	@Override
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	} 
	
}
