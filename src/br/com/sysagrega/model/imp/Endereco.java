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

import br.com.sysagrega.model.ICidade;
import br.com.sysagrega.model.IEndereco;
import br.com.sysagrega.model.IEstado;

@Entity
@Table(name = "TB_ENDERECO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_endereco_id_seq", sequenceName = "agrega.tb_endereco_id_seq", allocationSize = 1)
public class Endereco implements IEndereco {
	
	private static final long serialVersionUID = 1L;
 
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_endereco_id_seq")
	private Long id;
	
	@Column(name = "NR_CEP")
	private String cep;
	
	@Column(name = "DS_BAIRRO")
	private String bairro;
	
	@Column(name = "DS_RUA")
	private String rua;
	
	@Column(name = "NR_RUA")
	private Long numeroRua;
	
	@ManyToOne(targetEntity = Cidade.class)
	@JoinColumn(name = "ID_CIDADE")
	private ICidade cidade;
	
	@ManyToOne(targetEntity = Estado.class)
	@JoinColumn(name = "ID_UF")
	private IEstado estado;
	
	@Column(name = "DS_COMPLEMENTO")
	private String infoComplementar;
	
	@Override
	public Long getId() {
		return id;
	}
	
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String getCep() {
		return cep;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IEndereco#setCep(java.lang.String)
	 */
	@Override
	public void setCep(String cep) {
		this.cep = cep;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IEndereco#getBairro()
	 */
	@Override
	public String getBairro() {
		return bairro;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IEndereco#setBairro(java.lang.String)
	 */
	@Override
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IEndereco#getRua()
	 */
	@Override
	public String getRua() {
		return rua;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IEndereco#setRua(java.lang.String)
	 */
	@Override
	public void setRua(String rua) {
		this.rua = rua;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IEndereco#getEstado()
	 */
	@Override
	public String getInfoComplementar() {
		return infoComplementar;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IEndereco#setInfoComplementar(java.lang.String)
	 */
	@Override
	public void setInfoComplementar(String infoComplementar) {
		this.infoComplementar = infoComplementar;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IEndereco#setBairro(java.lang.String)
	 */
	@Override
	public ICidade getCidade() {
		return cidade;
	}
	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.embedded.imp.IEndereco#setBairro(java.lang.String)
	 */
	@Override
	public void setCidade(ICidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public Long getNumeroRua() {
		return numeroRua;
	}
	@Override
	public void setNumeroRua(Long numeroRua) {
		this.numeroRua = numeroRua;
	}

	@Override
	public IEstado getEstado() {
		return estado;
	}
	@Override
	public void setEstado(IEstado estado) {
		this.estado = estado;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((infoComplementar == null) ? 0 : infoComplementar.hashCode());
		result = prime * result + ((numeroRua == null) ? 0 : numeroRua.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
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
		if (!(obj instanceof Endereco))
			return false;
		Endereco other = (Endereco) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (infoComplementar == null) {
			if (other.infoComplementar != null)
				return false;
		} else if (!infoComplementar.equals(other.infoComplementar))
			return false;
		if (numeroRua == null) {
			if (other.numeroRua != null)
				return false;
		} else if (!numeroRua.equals(other.numeroRua))
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		return true;
	}
}

