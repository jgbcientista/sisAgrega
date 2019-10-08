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

import br.com.sysagrega.model.IContato;

@Entity
@Table(name = "TB_CONTATO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_cont", sequenceName = "agrega.tb_seq_cont", allocationSize = 1)
public class Contato implements IContato {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_cont")
	private Long id;

	private String nomeContato;
	
	private Long idCliente;
	
	private Long idFornecedor;
	
	private String tel1Contato;
	
	private String tel2Contato;
	
	private String setorContato;
	
	private String emailContato;
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Temporal(TemporalType.DATE)
	private Date dataAtualizacao;
	
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.REFRESH)
	private Usuario usuarioAtualizacao;

	private boolean ativo;

	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String getNomeContato() {
		return nomeContato;
	}

	@Override
	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	@Override
	public String getTel1Contato() {
		return tel1Contato;
	}

	@Override
	public void setTel1Contato(String tel1Contato) {
		this.tel1Contato = tel1Contato;
	}

	@Override
	public String getTel2Contato() {
		return tel2Contato;
	}

	@Override
	public void setTel2Contato(String tel2Contato) {
		this.tel2Contato = tel2Contato;
	}

	@Override
	public String getSetorContato() {
		return setorContato;
	}

	@Override
	public void setSetorContato(String setorContato) {
		this.setorContato = setorContato;
	}

	@Override
	public String getEmailContato() {
		return emailContato;
	}

	@Override
	public void setEmailContato(String emailContato) {
		this.emailContato = emailContato;
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
	
	@Override
	public long getSerialversionuid() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Long getIdCliente() {
		return idCliente;
	}
	
	@Override
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	@Override
	public Long getIdFornecedor() {
		return idFornecedor;
	}
	
	@Override
	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	
	
	
	
}
