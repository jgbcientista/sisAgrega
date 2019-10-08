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

import br.com.sysagrega.model.IDadosBancarios;
import br.com.sysagrega.model.IEndereco;
import br.com.sysagrega.model.IProfissional;

@Entity
@Table(name = "TB_PROFISSIONAL", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_prof", sequenceName = "agrega.tb_seq_prof", allocationSize = 1)
public class Profissional implements IProfissional{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.AUTO, generator ="agrega.tb_seq_prof")
	private Long id;
	
	private String tipoColaborador;
	
	private String tipoContratacao;
	
	private String nome;
	
	private String rg;
	
	private String cpf;
	
	private String cnh;
	
	private String tipoSanguineo;
	
	private String funcaoCargo;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	private Date asoValidade;
	
	private String escolaridade;
	
	private String graduacao;
	
	private String posgraducao;
	
	private String profissao;
	
	private String especializacao;
	
	private String numeroCTPS;
	
	private String numeroConselho;
	
	private String classeCTPS;
	
	private String serieCTPS;
	
	private String pisPasep;
	
	private String tamanhoCamisa;
	
	private String tamanhoCalca;
	
	private String numeroSapato;
	
	private String disponibilidade;
	
	private String telefone;
	
	private String celular;
	
	private String email;
	
	@Column(length=501)
	private String descricaoDiversa;
	
	@Temporal(TemporalType.DATE)
	private Date inicioAtividade;
	
	@Temporal(TemporalType.DATE)
	private Date fimAtividade;
	
	@OneToOne(targetEntity = Endereco.class, cascade = CascadeType.MERGE)
	private IEndereco endereco;
	
	@OneToOne(targetEntity = DadosBancarios.class, cascade = CascadeType.MERGE)
	private IDadosBancarios dadosBancarios;
	
	@Temporal(TemporalType.DATE)
	private Date dataAtualizacao;
	
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.REFRESH)
	private Usuario usuarioAtualizacao;
	
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.REFRESH)
	private Usuario usuarioAcesso;
	
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	
	private boolean ativo;
	
	private String razaoSocial;
	
	private String telefonePrincipal;
	
	private String cnpj;
	
	private Long inscricaoEstadual;
	
	private Long inscricaoMunicipal;
	
	private String cnae;
	
	private String sigla;
	
	@OneToOne(targetEntity = Endereco.class, cascade = CascadeType.MERGE,optional=true)
	private IEndereco enderecoPj;
	
	private Boolean acessarIntegra;
	
	private String emailPj;
	
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
	public String getProfissao() {
		return profissao;
	}
	@Override
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	
	@Override
	public String getCpf() {
		return cpf;
	}
	@Override
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	public String getPisPasep() {
		return pisPasep;
	}
	@Override
	public void setPisPasep(String pisPasep) {
		this.pisPasep = pisPasep;
	}
	
	@Override
	public String getTamanhoCamisa() {
		return tamanhoCamisa;
	}
	@Override
	public void setTamanhoCamisa(String tamanhoCamisa) {
		this.tamanhoCamisa = tamanhoCamisa;
	}
	@Override
	public String getTamanhoCalca() {
		return tamanhoCalca;
	}
	@Override
	public void setTamanhoCalca(String tamanhoCalca) {
		this.tamanhoCalca = tamanhoCalca;
	}
	@Override
	public String getNumeroSapato() {
		return numeroSapato;
	}
	@Override
	public void setNumeroSapato(String numeroSapato) {
		this.numeroSapato = numeroSapato;
	}
	@Override
	public Date getDataNascimento() {
		return dataNascimento;
	}
	@Override
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	public IEndereco getEndereco() {
		return endereco;
	}
	@Override
	public void setEndereco(IEndereco endereco) {
		this.endereco = endereco;
	}
	@Override
	public IDadosBancarios getDadosBancarios() {
		return dadosBancarios;
	}
	@Override
	public void setDadosBancarios(IDadosBancarios dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}

	@Override
	public Date getAsoValidade() {
		return asoValidade;
	}

	@Override
	public void setAsoValidade(Date asoValidade) {
		this.asoValidade = asoValidade;
		
	}

	@Override
	public String getDisponibilidade() {
		return disponibilidade;
	}

	@Override
	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
		
	}

	@Override
	public String getDescricaoDiversa() {
		return descricaoDiversa;
	}

	@Override
	public void setDescricaoDiversa(String descricaoDiversa) {
		this.descricaoDiversa = descricaoDiversa;
		
	}

	@Override
	public String getCnh() {
		return cnh;
	}

	@Override
	public void setCnh(String cnh) {
		this.cnh = cnh;
		
	}

	@Override
	public String getTipoColaborador() {
		return tipoColaborador;
	}

	@Override
	public String getEspecializacao() {
		return especializacao;
	}

	@Override
	public String getTipoContratacao() {
		return tipoContratacao;
	}

	@Override
	public String getNumeroCTPS() {
		return numeroCTPS;
	}

	@Override
	public String getClasseCTPS() {
		return classeCTPS;
	}
	
	@Override
	public String getSerieCTPS() {
		return serieCTPS;
	}

	@Override
	public String getEscolaridade() {
		return escolaridade;
	}

	@Override
	public String getGraduacao() {
		return graduacao;
	}

	@Override
	public String getPosgraducao() {
		return posgraducao;
	}

	@Override
	public Date getInicioAtividade() {
		return inicioAtividade;
	}

	@Override
	public Date getFimAtividade() {
		return fimAtividade;
	}

	@Override
	public void setTipoColaborador(String tipoColaborador) {
		this.tipoColaborador = tipoColaborador;
	}

	@Override
	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}

	@Override
	public void setTipoContratacao(String tipoContratacao) {
		this.tipoContratacao = tipoContratacao;
	}

	@Override
	public void setNumeroCTPS(String numeroCTPS) {
		this.numeroCTPS = numeroCTPS;
	}

	@Override
	public void setClasseCTPS(String classeCTPS) {
		this.classeCTPS = classeCTPS;
	}

	@Override
	public void setSerieCTPS(String serieCTPS) {
		this.serieCTPS = serieCTPS;
	}

	@Override
	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	@Override
	public void setGraduacao(String graduacao) {
		this.graduacao = graduacao;
	}

	@Override
	public void setPosgraducao(String posgraducao) {
		this.posgraducao = posgraducao;
	}

	@Override
	public void setInicioAtividade(Date inicioAtividade) {
		this.inicioAtividade = inicioAtividade;
	}
	
	@Override
	public void setFimAtividade(Date fimAtividade) {
		this.fimAtividade = fimAtividade;
	}

	@Override
	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	@Override
	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}
	@Override
	public String getFuncaoCargo() {
		return funcaoCargo;
	}
	@Override
	public void setFuncaoCargo(String funcaoCargo) {
		this.funcaoCargo = funcaoCargo;
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
	public Date getDataInclusao() {
		return dataInclusao;
	}
	@Override
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	@Override
	public boolean isAtivo() {
		return ativo;
	}
	@Override
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asoValidade == null) ? 0 : asoValidade.hashCode());
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((classeCTPS == null) ? 0 : classeCTPS.hashCode());
		result = prime * result + ((cnh == null) ? 0 : cnh.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dadosBancarios == null) ? 0 : dadosBancarios.hashCode());
		result = prime * result + ((dataAtualizacao == null) ? 0 : dataAtualizacao.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((descricaoDiversa == null) ? 0 : descricaoDiversa.hashCode());
		result = prime * result + ((disponibilidade == null) ? 0 : disponibilidade.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((escolaridade == null) ? 0 : escolaridade.hashCode());
		result = prime * result + ((especializacao == null) ? 0 : especializacao.hashCode());
		result = prime * result + ((fimAtividade == null) ? 0 : fimAtividade.hashCode());
		result = prime * result + ((funcaoCargo == null) ? 0 : funcaoCargo.hashCode());
		result = prime * result + ((graduacao == null) ? 0 : graduacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inicioAtividade == null) ? 0 : inicioAtividade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numeroCTPS == null) ? 0 : numeroCTPS.hashCode());
		result = prime * result + ((numeroSapato == null) ? 0 : numeroSapato.hashCode());
		result = prime * result + ((pisPasep == null) ? 0 : pisPasep.hashCode());
		result = prime * result + ((posgraducao == null) ? 0 : posgraducao.hashCode());
		result = prime * result + ((profissao == null) ? 0 : profissao.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((serieCTPS == null) ? 0 : serieCTPS.hashCode());
		result = prime * result + ((tamanhoCalca == null) ? 0 : tamanhoCalca.hashCode());
		result = prime * result + ((tamanhoCamisa == null) ? 0 : tamanhoCamisa.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((tipoColaborador == null) ? 0 : tipoColaborador.hashCode());
		result = prime * result + ((tipoContratacao == null) ? 0 : tipoContratacao.hashCode());
		result = prime * result + ((tipoSanguineo == null) ? 0 : tipoSanguineo.hashCode());
		result = prime * result + ((usuarioAtualizacao == null) ? 0 : usuarioAtualizacao.hashCode());
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
		if (getClass() != obj.getClass())
			return false;
		Profissional other = (Profissional) obj;
		if (asoValidade == null) {
			if (other.asoValidade != null)
				return false;
		} else if (!asoValidade.equals(other.asoValidade))
			return false;
		if (ativo != other.ativo)
			return false;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (classeCTPS == null) {
			if (other.classeCTPS != null)
				return false;
		} else if (!classeCTPS.equals(other.classeCTPS))
			return false;
		if (cnh == null) {
			if (other.cnh != null)
				return false;
		} else if (!cnh.equals(other.cnh))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dadosBancarios == null) {
			if (other.dadosBancarios != null)
				return false;
		} else if (!dadosBancarios.equals(other.dadosBancarios))
			return false;
		if (dataAtualizacao == null) {
			if (other.dataAtualizacao != null)
				return false;
		} else if (!dataAtualizacao.equals(other.dataAtualizacao))
			return false;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (descricaoDiversa == null) {
			if (other.descricaoDiversa != null)
				return false;
		} else if (!descricaoDiversa.equals(other.descricaoDiversa))
			return false;
		if (disponibilidade == null) {
			if (other.disponibilidade != null)
				return false;
		} else if (!disponibilidade.equals(other.disponibilidade))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (escolaridade == null) {
			if (other.escolaridade != null)
				return false;
		} else if (!escolaridade.equals(other.escolaridade))
			return false;
		if (especializacao == null) {
			if (other.especializacao != null)
				return false;
		} else if (!especializacao.equals(other.especializacao))
			return false;
		if (fimAtividade == null) {
			if (other.fimAtividade != null)
				return false;
		} else if (!fimAtividade.equals(other.fimAtividade))
			return false;
		if (funcaoCargo == null) {
			if (other.funcaoCargo != null)
				return false;
		} else if (!funcaoCargo.equals(other.funcaoCargo))
			return false;
		if (graduacao == null) {
			if (other.graduacao != null)
				return false;
		} else if (!graduacao.equals(other.graduacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inicioAtividade == null) {
			if (other.inicioAtividade != null)
				return false;
		} else if (!inicioAtividade.equals(other.inicioAtividade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeroCTPS == null) {
			if (other.numeroCTPS != null)
				return false;
		} else if (!numeroCTPS.equals(other.numeroCTPS))
			return false;
		if (numeroSapato == null) {
			if (other.numeroSapato != null)
				return false;
		} else if (!numeroSapato.equals(other.numeroSapato))
			return false;
		if (pisPasep == null) {
			if (other.pisPasep != null)
				return false;
		} else if (!pisPasep.equals(other.pisPasep))
			return false;
		if (posgraducao == null) {
			if (other.posgraducao != null)
				return false;
		} else if (!posgraducao.equals(other.posgraducao))
			return false;
		if (profissao == null) {
			if (other.profissao != null)
				return false;
		} else if (!profissao.equals(other.profissao))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (serieCTPS == null) {
			if (other.serieCTPS != null)
				return false;
		} else if (!serieCTPS.equals(other.serieCTPS))
			return false;
		if (tamanhoCalca == null) {
			if (other.tamanhoCalca != null)
				return false;
		} else if (!tamanhoCalca.equals(other.tamanhoCalca))
			return false;
		if (tamanhoCamisa == null) {
			if (other.tamanhoCamisa != null)
				return false;
		} else if (!tamanhoCamisa.equals(other.tamanhoCamisa))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (tipoColaborador == null) {
			if (other.tipoColaborador != null)
				return false;
		} else if (!tipoColaborador.equals(other.tipoColaborador))
			return false;
		if (tipoContratacao == null) {
			if (other.tipoContratacao != null)
				return false;
		} else if (!tipoContratacao.equals(other.tipoContratacao))
			return false;
		if (tipoSanguineo == null) {
			if (other.tipoSanguineo != null)
				return false;
		} else if (!tipoSanguineo.equals(other.tipoSanguineo))
			return false;
		if (usuarioAtualizacao == null) {
			if (other.usuarioAtualizacao != null)
				return false;
		} else if (!usuarioAtualizacao.equals(other.usuarioAtualizacao))
			return false;
		return true;
	}
	@Override
	public String getCnpj() {
		return cnpj;
	}
	@Override
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
		this.inscricaoMunicipal = inscricaoMunicipal;
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
	public String getSigla() {
		return sigla;
	}
	@Override
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	@Override
	public String getRazaoSocial() {
		return razaoSocial;
	}
	@Override
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	@Override
	public String getTelefonePrincipal() {
		return telefonePrincipal;
	}
	@Override
	public void setTelefonePrincipal(String telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}

	@Override
	public IEndereco getEnderecoPj() {
		return enderecoPj;
	}

	@Override
	public void setEnderecoPj(IEndereco enderecoPj) {
		this.enderecoPj = enderecoPj;
	}

	@Override
	public String getEmailPj() {
		return emailPj;
	}

	@Override
	public void setEmailPj(String emailPj) {
		this.emailPj = emailPj;
	}
	@Override
	public Usuario getUsuarioAcesso() {
		return usuarioAcesso;
	}
	@Override
	public void setUsuarioAcesso(Usuario usuarioAcesso) {
		this.usuarioAcesso = usuarioAcesso;
	}
	@Override
	public String getNumeroConselho() {
		return numeroConselho;
	}
	@Override
	public void setNumeroConselho(String numeroConselho) {
		this.numeroConselho = numeroConselho;
	}
	
	@Override
	public Boolean getAcessarIntegra() {
		return acessarIntegra;
	}

	@Override
	public void setAcessarIntegra(Boolean acessarIntegra) {
		this.acessarIntegra = acessarIntegra;
	}
	
}


