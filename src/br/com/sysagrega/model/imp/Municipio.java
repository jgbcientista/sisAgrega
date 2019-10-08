package br.com.sysagrega.model.imp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.IEstado;
import br.com.sysagrega.model.IMunicipio;
import br.com.sysagrega.model.IRegiao;

@Entity
@Table(name  = "tb_municipio", schema="agrega")
@SequenceGenerator(name = "agrega.tb_muni_seq", sequenceName = "agrega.tb_muni_seq", allocationSize = 1)
public class Municipio implements IMunicipio {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_muni_seq")
	private Long id;
	
	private String nome;
	
	@ManyToOne(targetEntity = Estado.class, fetch = FetchType.LAZY)
	@JoinColumn(name="id_uf", nullable = false, unique = false)
	private IEstado estado;
	
/*	@ManyToOne(targetEntity = Regiao.class, fetch = FetchType.LAZY)
	@JoinColumn(name="id_regiao")*/
	@OneToOne(targetEntity = Regiao.class, cascade = CascadeType.REFRESH)
	private IRegiao regiao;
	
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
	public IEstado getEstado() {
		return estado;
	}

	@Override
	public void setEstado(IEstado estado) {
		this.estado = estado;
	}
	@Override
	public IRegiao getRegiao() {
		return regiao;
	}
	@Override
	public void setRegiao(IRegiao regiao) {
		this.regiao = regiao;
	}
	 
	 
	
	
	
}
