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

import br.com.sysagrega.model.IPerfil;
import br.com.sysagrega.model.IUsuario;

@Entity
@Table(name = "TB_USUARIO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_usuario", sequenceName = "agrega.tb_seq_usuario", allocationSize = 1)
public class Usuario implements IUsuario {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.AUTO, generator ="agrega.tb_seq_usuario")
	private Long id;

	private String nome;
	
	private String setor;
	
	private String funcao;
	
	private String telefone;
	
	private String email;
	
	private String senha;
	
	private String login;
	
	private boolean ativo;
	
	@OneToOne(targetEntity = Perfil.class, cascade = CascadeType.REFRESH)
	private IPerfil perfil;
	
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	
	@Temporal(TemporalType.DATE)
	private Date dataAtualizacao;
	
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.REFRESH)
	private Usuario usuarioAtualizacao;
	
	public Usuario() {
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
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public void setLogin(String login) {
		this.login = login;
		
	}

	@Override
	public String getSenha() {
		return senha;
	}

	@Override
	public void setSenha(String senha) {
		this.senha = senha;
		
	}
	
	@Override
	public IPerfil getPerfil() {
		return perfil;
	}
	@Override
	public void setPerfil(IPerfil perfil) {
		this.perfil = perfil;
	}
	@Override
	public String getTelefone() {
		return telefone;
	}
	@Override
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

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
	public Date getDataInclusao() {
		return dataInclusao;
	}
	@Override
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	@Override
	public boolean getAtivo() {
		return ativo;
	}
	@Override
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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

 
	
}
