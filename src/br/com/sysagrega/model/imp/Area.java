package br.com.sysagrega.model.imp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.IArea;

@Entity
@Table(name  = "TB_AREA", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_are", sequenceName = "agrega.tb_seq_are", allocationSize = 1)
public class Area implements IArea {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_are")
	private Long id;
	
	@Column(name = "TIP_DESCRICAO")
	private String descricao;
	
	private boolean ativo;
	
	
	
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
	@Override
	public boolean isAtivo() {
		return ativo;
	}
	@Override
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	 
}
