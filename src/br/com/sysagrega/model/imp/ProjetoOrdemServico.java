
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

import br.com.sysagrega.model.IOrdemServico;
import br.com.sysagrega.model.IProjeto;
import br.com.sysagrega.model.IProjetoOrdemServico;

@Entity
@Table(name = "TB_PROJETO_ORDEM_SERVICO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_proj_ordem", sequenceName = "agrega.tb_seq_proj_ordem", allocationSize = 1)
public class ProjetoOrdemServico implements IProjetoOrdemServico{
	
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_proj_ordem")
	private Long id;
	
	@OneToOne(targetEntity = Projeto.class, cascade = CascadeType.REFRESH)
	private IProjeto projeto;
	
	@OneToOne(targetEntity = OrdemServico.class, cascade = CascadeType.REFRESH)
	private IOrdemServico ordemServico;
	 
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.REFRESH)
	private Usuario usuarioAtualizacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	
	private Boolean ativo = false;
	
	
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
	public IOrdemServico getOrdemServico() {
		return ordemServico;
	}
	@Override
	public void setOrdemServico(IOrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	} 
	@Override
	public Boolean getAtivo() {
		return ativo;
	}
	@Override
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	@Override
	public IProjeto getProjeto() {
		return projeto;
	}
	@Override
	public void setProjeto(IProjeto projeto) {
		this.projeto = projeto;
	}

}
