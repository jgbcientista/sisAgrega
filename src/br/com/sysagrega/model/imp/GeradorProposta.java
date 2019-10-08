package br.com.sysagrega.model.imp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.IGeradorProposta;

@Entity
@Table(name = "TB_GERADOR_PROPOSTA", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_gera", sequenceName = "agrega.tb_seq_gera", allocationSize = 1)
public class GeradorProposta implements IGeradorProposta {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_gera")
	private Long id;

	private Integer numero;
	
	private Integer ano;
	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Integer getNumero() {
		return numero;
	}
	@Override
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	@Override
	public Integer getAno() {
		return ano;
	}
	@Override
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
}
