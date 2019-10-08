package br.com.sysagrega.model.imp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.ITipoServico;

@Entity
@Table(name  = "TB_TIPO_SERVICO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_tipo_serv", sequenceName = "agrega.tb_seq_tipo_serv", allocationSize = 1)
public class TipoServico implements ITipoServico {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_tipo_serv")
	private Long id;
	
	@Column(name = "TIP_DESCRICAO")
	private String descricao;
	
	
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

 
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	 
}
