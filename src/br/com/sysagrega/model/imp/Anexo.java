package br.com.sysagrega.model.imp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.IAnexo;

@Entity
@Table(name = "TB_ANEXO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_ane", sequenceName = "agrega.tb_seq_ane", allocationSize = 1)
public class Anexo implements IAnexo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_ane")
	private Long id;
	
	@Column(name = "ANE_DESCRICAO")
	private String descricao;
	
	@Column(name = "ANE_TIPO")
	private Long tipo;
	
	@Column(name = "ANE_ID_ENTIDADE")
	private Long idEntidade;;


	@Override
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
	@Override
	public Long getTipo() {
		return tipo;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IBanco#getNome()
	 */
	@Override
	public String getDescricao() {
		return descricao;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IBanco#setNome(java.lang.String)
	 */
	@Override
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
 
	@Override
	public Long getIdEntidade() {
		return idEntidade;
	}
	@Override
	public void setIdEntidade(Long idEntidade) {
		this.idEntidade = idEntidade;
	}
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}

 


}
