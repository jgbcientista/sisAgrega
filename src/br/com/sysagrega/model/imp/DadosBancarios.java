package br.com.sysagrega.model.imp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.IBanco;
import br.com.sysagrega.model.IDadosBancarios;

@Entity
@Table(name = "TB_DADOS_BANCARIOS", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_dad_banc", sequenceName = "agrega.tb_seq_dad_banc", allocationSize = 1)
public class DadosBancarios implements IDadosBancarios {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_dad_banc")
	private Long id;
	
	@ManyToOne(targetEntity = Banco.class)
	@JoinColumn(name = "ID_BANCO")
	private IBanco idBanco;
	
	@Column(name = "NR_AGENGIA")
	private String nrAgencia;
	
	@Column(name = "NR_CONTA")
	private String nrConta;
	
	@Column(name = "TP_CONTA")
	private String tipoConta;
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IDadosBancarios#getNrAgencia()
	 */
	@Override
	public String getNrAgencia() {
		return nrAgencia;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IDadosBancarios#setNrAgencia(java.lang.Long)
	 */
	@Override
	public void setNrAgencia(String nrAgencia) {
		this.nrAgencia = nrAgencia;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IDadosBancarios#getNrConta()
	 */
	@Override
	public String getNrConta() {
		return nrConta;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IDadosBancarios#setNrConta(java.lang.Long)
	 */
	@Override
	public void setNrConta(String nrConta) {
		this.nrConta = nrConta;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IDadosBancarios#getTipoConta()
	 */
	@Override
	public String getTipoConta() {
		return tipoConta;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IDadosBancarios#setTipoConta(java.lang.String)
	 */
	@Override
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
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
	public IBanco getIdBanco() {
		return idBanco;
	}
	@Override
	public void setIdBanco(IBanco idBanco) {
		this.idBanco = idBanco;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idBanco == null) ? 0 : idBanco.hashCode());
		result = prime * result + ((nrAgencia == null) ? 0 : nrAgencia.hashCode());
		result = prime * result + ((nrConta == null) ? 0 : nrConta.hashCode());
		result = prime * result + ((tipoConta == null) ? 0 : tipoConta.hashCode());
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
		if (!(obj instanceof DadosBancarios))
			return false;
		DadosBancarios other = (DadosBancarios) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idBanco == null) {
			if (other.idBanco != null)
				return false;
		} else if (!idBanco.equals(other.idBanco))
			return false;
		if (nrAgencia == null) {
			if (other.nrAgencia != null)
				return false;
		} else if (!nrAgencia.equals(other.nrAgencia))
			return false;
		if (nrConta == null) {
			if (other.nrConta != null)
				return false;
		} else if (!nrConta.equals(other.nrConta))
			return false;
		if (tipoConta == null) {
			if (other.tipoConta != null)
				return false;
		} else if (!tipoConta.equals(other.tipoConta))
			return false;
		return true;
	}
}
