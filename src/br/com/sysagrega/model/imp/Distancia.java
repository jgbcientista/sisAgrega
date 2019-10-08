package br.com.sysagrega.model.imp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.IDistancia;
import br.com.sysagrega.model.IProjeto;

@Entity
@Table(name  = "TB_DISTANCIA", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_dist", sequenceName = "agrega.tb_seq_dist", allocationSize = 1)
public class Distancia implements IDistancia {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_dist")
	private Long id;
	
	@Column(name = "DIS_DISTANCIA")
	private Double distancia;
	
	@Column(name = "DIS_PAVIMENTADA")
	private Boolean pavimentada;
	
	@OneToOne(targetEntity = Projeto.class, cascade = CascadeType.ALL)
	private IProjeto projeto;
	
	private boolean ativo;
	private String tpUnidadeMedida;
	
	
	@Override
	public Long getId() {
		return id;
	}
 
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean isAtivo() {
		return ativo;
	}
	@Override
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	@Override
	public Double getDistancia() {
		return distancia;
	}
	@Override
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Boolean getPavimentada() {
		return pavimentada;
	}
	@Override
	public void setPavimentada(Boolean pavimentada) {
		this.pavimentada = pavimentada;
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
	public String getTpUnidadeMedida() {
		return tpUnidadeMedida;
	}
	@Override
	public void setTpUnidadeMedida(String tpUnidadeMedida) {
		this.tpUnidadeMedida = tpUnidadeMedida;
	}

}
