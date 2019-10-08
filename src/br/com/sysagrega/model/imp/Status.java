package br.com.sysagrega.model.imp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.IStatus;

@Entity
@Table(name = "TB_STATUS", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_status", sequenceName = "agrega.tb_seq_status", allocationSize = 1)
public class Status implements IStatus {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_status")
	private Long id;
	private String nome; 
	private Integer tipo; 
	
	public Status() {
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
		this.nome = nome;
	}
	@Override
	public Integer getTipo() {
		return tipo;
	}
	@Override
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
}
