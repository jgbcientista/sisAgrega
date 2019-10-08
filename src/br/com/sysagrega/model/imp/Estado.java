package br.com.sysagrega.model.imp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.IEstado;

@Entity
@Table(name  = "TB_UF", schema="agrega")
@SequenceGenerator(name = "agrega.tb_estado_seq", sequenceName = "agrega.tb_estado_seq", allocationSize = 1)
public class Estado implements IEstado {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_estado_seq")
	private Long id;
	
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "CODIGO_IBGE")
	private String codigoIbge;
	
	@Column(name = "SIGLA_UF")
	private String uf;
	
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "NOME_UF")
	private String descricaoUf;
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IEstado#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IEstado#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IEstado#getUf()
	 */
	@Override
	public String getUf() {
		return uf;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IEstado#setUf(java.lang.String)
	 */
	@Override
	public void setUf(String uf) {
		this.uf = uf;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IEstado#getDescricaoUf()
	 */
	@Override
	public String getDescricaoUf() {
		return descricaoUf;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IEstado#setDescricaoUf(java.lang.String)
	 */
	@Override
	public void setDescricaoUf(String descricaoUf) {
		this.descricaoUf = descricaoUf;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IEstado#setCidades(java.util.List)
	 */
	@Override
	public String getCodigoIbge() {
		return codigoIbge;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IEstado#setCidades(java.util.List)
	 */
	@Override
	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoIbge == null) ? 0 : codigoIbge.hashCode());
		result = prime * result + ((descricaoUf == null) ? 0 : descricaoUf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Estado))
			return false;
		Estado other = (Estado) obj;
		if (codigoIbge == null) {
			if (other.codigoIbge != null)
				return false;
		} else if (!codigoIbge.equals(other.codigoIbge))
			return false;
		if (descricaoUf == null) {
			if (other.descricaoUf != null)
				return false;
		} else if (!descricaoUf.equals(other.descricaoUf))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}
	
	
}
