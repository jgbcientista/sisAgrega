package br.com.sysagrega.model.imp;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.IPropostaHistorico;


@Entity
@Table(name  = "TB_PROPOSTA_HISTORICO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_propost_hist", sequenceName = "agrega.tb_seq_propost_hist", allocationSize = 1)
public class PropostaHistorico extends PropostaBase implements IPropostaHistorico{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_propost_hist")
	private Long id;

	@ManyToOne(targetEntity = Proposta.class)
	@JoinColumn(name = "propostaId")
	private IProposta propostaId;
	
	@Temporal(TemporalType.DATE)
	private Date dataRevisao;
	
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaHistorico#getPropostaId()
	 */
	@Override
	public IProposta getPropostaId() {
		return propostaId;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IPropostaHistorico#setPropostaId(br.com.sysagrega.model.imp.Proposta)
	 */
	@Override
	public void setPropostaId(IProposta propostaId) {
		this.propostaId = propostaId;
	}

	/**
	 * @return the dataRevisao
	 */
	@Override
	public Date getDataRevisao() {
		return dataRevisao;
	}

	/**
	 * @param dataRevisao the dataRevisao to set
	 */
	@Override
	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataRevisao == null) ? 0 : dataRevisao.hashCode());
		result = prime * result + ((propostaId == null) ? 0 : propostaId.hashCode());
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
		if (!(obj instanceof PropostaHistorico))
			return false;
		PropostaHistorico other = (PropostaHistorico) obj;
		if (dataRevisao == null) {
			if (other.dataRevisao != null)
				return false;
		} else if (!dataRevisao.equals(other.dataRevisao))
			return false;
		if (propostaId == null) {
			if (other.propostaId != null)
				return false;
		} else if (!propostaId.equals(other.propostaId))
			return false;
		return true;
	}

	@Override
	public String getNumeroRevisao() {
		return numeroRevisao;
	}

	@Override
	public void setNumeroRevisao(String numeroRevisao) {
		this.numeroRevisao = numeroRevisao;
		
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
