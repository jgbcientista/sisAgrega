package br.com.sysagrega.model.imp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.ISituacao;

@Entity
@Table(name = "TB_SITUACAO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_situacao", sequenceName = "agrega.tb_seq_situacao", allocationSize = 1)
public class Situacao implements ISituacao {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_situacao")
	private Long id;
	private String nome; 
	
	public Situacao() {
	}
	
	public Situacao(Long id) {
		this.id = id;
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
	
}
