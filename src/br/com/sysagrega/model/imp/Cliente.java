package br.com.sysagrega.model.imp;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.IEndereco;

@Entity
@Table(name = "TB_CLIENTE", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_cli", sequenceName = "agrega.tb_seq_cli", allocationSize = 1)
public class Cliente implements ICliente {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.AUTO, generator ="agrega.tb_seq_cli")
	private Long id;

	private String tipoCliente;
	
	private String tipoIndicacao;
	
	private String porteEmpresa;
	
	private String seguimento;
	
	private String nome;
	
	private String sigla;
	
	private String rg;
	
	private String cnpjCPF;
	
	private Long inscricaoEstadual;
	
	private Long inscricaoMunicipal;
	
	private String cnae;
	
	private String status;
	
	@OneToOne(targetEntity = Endereco.class, cascade = CascadeType.MERGE)
	private IEndereco endereco;
	
	private String contato;
	
	private String setor;
	
	private String funcao;
	
	private String telefone;

	private String celular;
	
	private String email;
	
	private String pendencia;
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column(length=1001)
	private String observacoes;
	
	@Temporal(TemporalType.DATE)
	private Date dataAtualizacao;
	
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.REFRESH)
	private Usuario usuarioAtualizacao;

	private boolean ativo;
	
	
	@Transient
	@Override
	public boolean isNovo() {
		return getId() == null;
	}
	
	@Transient
	@Override
	public boolean isExistente() {
		return !isNovo();
	}

	/*@see java.lang.Object#hashCode()*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tipoCliente == null) ? 0 : tipoCliente.hashCode());
		result = prime * result + ((tipoIndicacao == null) ? 0 : tipoIndicacao.hashCode());
		result = prime * result + ((porteEmpresa == null) ? 0 : porteEmpresa.hashCode());
		result = prime * result + ((seguimento == null) ? 0 : seguimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((cnpjCPF == null) ? 0 : cnpjCPF.hashCode());
		result = prime * result + ((inscricaoEstadual == null) ? 0 : inscricaoEstadual.hashCode());
		result = prime * result + ((inscricaoMunicipal == null) ? 0 : inscricaoMunicipal.hashCode());
		result = prime * result + ((cnae == null) ? 0 : cnae.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((contato == null) ? 0 : contato.hashCode());
		result = prime * result + ((setor == null) ? 0 : setor.hashCode());
		result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		
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
		if (!(obj instanceof Cliente))
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		if (tipoCliente == null) {
			if (other.tipoCliente != null)
				return false;
		} else if (!tipoCliente.equals(other.tipoCliente))
			return false;
		
		if (tipoIndicacao == null) {
			if (other.tipoIndicacao != null)
				return false;
		} else if (!tipoIndicacao.equals(other.tipoIndicacao))
			return false;
		
		if (porteEmpresa == null) {
			if (other.porteEmpresa != null)
				return false;
		} else if (!porteEmpresa.equals(other.porteEmpresa))
			return false;
		
		if (seguimento == null) {
			if (other.seguimento != null)
				return false;
		} else if (!seguimento.equals(other.seguimento))
			return false;
		
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		
		if (cnpjCPF == null) {
			if (other.cnpjCPF != null)
				return false;
		} else if (!cnpjCPF.equals(other.cnpjCPF))
			return false;
		
		if (inscricaoEstadual == null) {
			if (other.inscricaoEstadual != null)
				return false;
		} else if (!inscricaoEstadual.equals(other.inscricaoEstadual))
			return false;
		
		if (inscricaoMunicipal == null) {
			if (other.inscricaoMunicipal != null)
				return false;
		} else if (!inscricaoMunicipal.equals(other.inscricaoMunicipal))
			return false;
		
		if (cnae == null) {
			if (other.cnae != null)
				return false;
		} else if (!cnae.equals(other.cnae))
			return false;
		
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		
		if (contato == null) {
			if (other.contato != null)
				return false;
		} else if (!contato.equals(other.contato))
			return false;
		
		if (setor == null) {
			if (other.setor != null)
				return false;
		} else if (!setor.equals(other.setor))
			return false;
		
		if (funcao == null) {
			if (other.funcao != null)
				return false;
		} else if (!funcao.equals(other.funcao))
			return false;
		
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
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
	public String getTipoCliente() {
		return tipoCliente;
	}

	@Override
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	@Override
	public String getTipoIndicacao() {
		return tipoIndicacao;
	}

	@Override
	public void setTipoIndicacao(String tipoIndicacao) {
		this.tipoIndicacao = tipoIndicacao;
	}
	
	@Override
	public String getPorteEmpresa() {
		return porteEmpresa;
	}

	@Override
	public void setPorteEmpresa(String porteEmpresa) {
		this.porteEmpresa = porteEmpresa;
	}
	
	@Override
	public String getSeguimento() {
		return seguimento;
	}

	@Override
	public void setSeguimento(String seguimento) {
		this.seguimento = seguimento;
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
	public String getSigla() {
		return sigla;
	}

	@Override
	public void setSigla(String sigla) {
		this.sigla = sigla;
		
	}
	
	@Override
	public String getRg() {
		return rg;
	}

	@Override
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	@Override
	public void setCnpjCPF(String cnpjCPF) {
		this.cnpjCPF = cnpjCPF;
		
	}

	@Override
	public String getCnpjCPF() {
		return cnpjCPF;
	}
	
	@Override
	public Long getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	@Override
	public void setInscricaoEstadual(Long inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
		
	}
	
	@Override
	public Long getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	@Override
	public void setInscricaoMunicipal(Long inscricaoMunicipal) {
		this.inscricaoMunicipal =inscricaoMunicipal;
		
	}

	@Override
	public String getCnae() {
		return cnae;
	}

	@Override
	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;		
	}

	@Override
	public IEndereco getEndereco() {
		return endereco;
	}

	@Override
	public void setEndereco(IEndereco endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String getContato() {
		return contato;
	}

	@Override
	public void setContato(String contato) {
		this.contato = contato;
	}

	@Override
	public String getSetor() {
		return setor;
	}
	
	@Override
	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	@Override
	public String getFuncao() {
		return funcao;
	}
	
	@Override
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	@Override
	public String getTelefone() {
		return telefone;
	}

	@Override
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String getCelular() {
		return celular;
	}

	@Override
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Date getDataCadastro() {
		return dataCadastro;
	}

	@Override
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String getObservacoes() {
		return observacoes;
	}

	@Override
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	@Override
	public String getPendencia() {
		return pendencia;
	}
	@Override
	public void setPendencia(String pendencia) {
		this.pendencia = pendencia;
	}
	@Override
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	@Override
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@Override
	public Usuario getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}
	@Override
	public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}
	
	@Override
	public boolean isAtivo() {
		return ativo;
	}
	@Override
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
