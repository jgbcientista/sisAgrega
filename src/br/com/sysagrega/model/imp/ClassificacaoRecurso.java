package br.com.sysagrega.model.imp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.IClassificacaoRecurso;

@Entity
@Table(name = "TB_CLASSIFICACAO_RECURSO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_classificacao_recurso", sequenceName = "agrega.tb_seq_classificacao_recurso", allocationSize = 1)
public class ClassificacaoRecurso implements IClassificacaoRecurso {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_classificacao_recurso")
	private Long id;
	
	String descricao;
	

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
