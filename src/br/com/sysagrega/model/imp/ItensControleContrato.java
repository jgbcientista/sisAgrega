package br.com.sysagrega.model.imp;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.IItensControleContrato;

@Entity
@Table(name  = "TB_ITENS_CONTRATO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_contra", sequenceName = "agrega.tb_seq_contra", allocationSize = 1)
public class ItensControleContrato implements IItensControleContrato {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_contra")
	private Long id;
	
	private String nrEstudo;
	
	private String nomeProjeto;
	
	private String projeto;
	
	private String municipio;
	
	private String epi;
	
	private String os;
	
	@OneToMany(targetEntity = Parcela.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "itenId")
	private Set<Parcela> parcelas;
	
	private String observacoes;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	 
	@Override
	public String getNrEstudo() {
		return nrEstudo;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#setNrEstudo(java.lang.String)
	 */
	@Override
	public void setNrEstudo(String nrEstudo) {
		this.nrEstudo = nrEstudo;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#getNomeProjeto()
	 */
	@Override
	public String getNomeProjeto() {
		return nomeProjeto;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#setNomeProjeto(java.lang.String)
	 */
	@Override
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#getMunicipio()
	 */
	@Override
	public String getMunicipio() {
		return municipio;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#setMunicipio(java.lang.String)
	 */
	@Override
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#getEpi()
	 */
	@Override
	public String getEpi() {
		return epi;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#setEpi(java.lang.String)
	 */
	@Override
	public void setEpi(String epi) {
		this.epi = epi;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#getOs()
	 */
	@Override
	public String getOs() {
		return os;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#setOs(java.lang.String)
	 */
	@Override
	public void setOs(String os) {
		this.os = os;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#getParcelas()
	 */
	@Override
	public Set<Parcela> getParcelas() {
		return parcelas;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#setParcelas(java.util.Set)
	 */
	@Override
	public void setParcelas(Set<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#setParcelas(java.util.Set)
	 */
	@Override
	public String getObservacoes() {
		return observacoes;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#setParcelas(java.util.Set)
	 */
	@Override
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#setParcelas(java.util.Set)
	 */
	@Override
	public String getProjeto() {
		return projeto;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IItensControleContrato#setParcelas(java.util.Set)
	 */
	@Override
	public void setProjeto(String projeto) {
		this.projeto = projeto;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((epi == null) ? 0 : epi.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((municipio == null) ? 0 : municipio.hashCode());
		result = prime * result + ((nomeProjeto == null) ? 0 : nomeProjeto.hashCode());
		result = prime * result + ((nrEstudo == null) ? 0 : nrEstudo.hashCode());
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		result = prime * result + ((os == null) ? 0 : os.hashCode());
		result = prime * result + ((parcelas == null) ? 0 : parcelas.hashCode());
		result = prime * result + ((projeto == null) ? 0 : projeto.hashCode());
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
		if (!(obj instanceof ItensControleContrato))
			return false;
		ItensControleContrato other = (ItensControleContrato) obj;
		if (epi == null) {
			if (other.epi != null)
				return false;
		} else if (!epi.equals(other.epi))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (municipio == null) {
			if (other.municipio != null)
				return false;
		} else if (!municipio.equals(other.municipio))
			return false;
		if (nomeProjeto == null) {
			if (other.nomeProjeto != null)
				return false;
		} else if (!nomeProjeto.equals(other.nomeProjeto))
			return false;
		if (nrEstudo == null) {
			if (other.nrEstudo != null)
				return false;
		} else if (!nrEstudo.equals(other.nrEstudo))
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (os == null) {
			if (other.os != null)
				return false;
		} else if (!os.equals(other.os))
			return false;
		if (parcelas == null) {
			if (other.parcelas != null)
				return false;
		} else if (!parcelas.equals(other.parcelas))
			return false;
		if (projeto == null) {
			if (other.projeto != null)
				return false;
		} else if (!projeto.equals(other.projeto))
			return false;
		return true;
	}
	
}
