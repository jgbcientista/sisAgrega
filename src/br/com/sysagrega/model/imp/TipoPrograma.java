package br.com.sysagrega.model.imp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.ITipoPrograma;

@Entity
@Table(name = "TB_TIPO_PROGRAMA", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_programa", sequenceName = "agrega.tb_seq_programa", allocationSize = 1)
public class TipoPrograma implements ITipoPrograma {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_programa")
	private Long id;
	private String descricao; 
	
	public TipoPrograma() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	 
	
}
