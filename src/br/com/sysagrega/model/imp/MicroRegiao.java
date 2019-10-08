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

import br.com.sysagrega.model.IEstado;
import br.com.sysagrega.model.IMacroRegiao;
import br.com.sysagrega.model.IMicroRegiao;

@Entity
@Table(name  = "tb_micro_regiao", schema="agrega")
@SequenceGenerator(name = "agrega.tb_micro_regigao_seq", sequenceName = "agrega.tb_micro_regigao_seq", allocationSize = 1)
public class MicroRegiao implements IMicroRegiao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_micro_regigao_seq")
	private Long id;
	
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "nome_micro_regiao")
	private String nome;
	
	@ManyToOne(targetEntity = MacroRegiao.class, fetch = FetchType.LAZY)
	@JoinColumn(name="id_macro_regiao")
	private IMacroRegiao macroRegiao;
	
	@ManyToOne(targetEntity = Estado.class, fetch = FetchType.LAZY)
	@JoinColumn(name="id_uf")
	private IEstado estado;
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((macroRegiao == null) ? 0 : macroRegiao.hashCode());
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
		MicroRegiao other = (MicroRegiao) obj;
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
		if (macroRegiao == null) {
			if (other.macroRegiao != null)
				return false;
		} else if (!macroRegiao.equals(other.macroRegiao))
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
	public IMacroRegiao getMacroRegiao() {
		return macroRegiao;
	}

	@Override
	public void setMacroRegiao(IMacroRegiao macroRegiao) {
		this.macroRegiao = macroRegiao;
	}

	@Override
	public IEstado getEstado() {
		return estado;
	}

	@Override
	public void setEstado(IEstado estado) {
		this.estado = estado;
	}

	

	
}
