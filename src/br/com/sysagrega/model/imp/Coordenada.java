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

import br.com.sysagrega.model.ICoordenada;

@Entity
@Table(name = "TB_COORDENADA", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_coor", sequenceName = "agrega.tb_seq_coor", allocationSize = 1)
public class Coordenada implements ICoordenada{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_coor")
	private Long id;
	 
	private String ponto;
	
	private Long coordenadaX;
	
	private Long coordenadaY;
	
	private Long fuso;
	
	private Long projeto;
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
		
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getCoordenadaX() {
		return coordenadaX;
	}

	@Override
	public void setCoordenadaX(Long coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	@Override
	public Long getCoordenadaY() {
		return coordenadaY;
	}

	@Override
	public void setCoordenadaY(Long coordenadaY) {
		this.coordenadaY = coordenadaY;
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

	@Override
	public String getPonto() {
		return ponto;
	}

	@Override
	public void setPonto(String ponto) {
		this.ponto = ponto;
	}
	@Override
	public Long getFuso() {
		return fuso;
	}
	@Override
	public void setFuso(Long fuso) {
		this.fuso = fuso;
	}
	
	 
}
