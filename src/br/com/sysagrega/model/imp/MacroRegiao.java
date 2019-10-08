package br.com.sysagrega.model.imp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.ICidade;
import br.com.sysagrega.model.IEstado;
import br.com.sysagrega.model.IMacroRegiao;

@Entity
@Table(name  = "tb_macro_regiao", schema="agrega")
@SequenceGenerator(name = "agrega.tb_macro_regi_seq", sequenceName = "agrega.tb_macro_regi_seq", allocationSize = 1)
public class MacroRegiao implements IMacroRegiao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_macro_regi_seq")
	private Long id;
	
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "nome_macro_regiao")
	private String nome;
	
	@ManyToOne(targetEntity = Estado.class, fetch = FetchType.LAZY)
	@JoinColumn(name="id_uf")
	private IEstado estado;
	
	
	@ManyToOne(targetEntity = Cidade.class, fetch = FetchType.LAZY)
	@JoinColumn(name="id_cidade")
	private ICidade cidade;
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MacroRegiao other = (MacroRegiao) obj;
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
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
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
	public ICidade getCidade() {
		return cidade;
	}
	@Override
	public void setCidade(ICidade cidade) {
		this.cidade = cidade;
	}


	
}
