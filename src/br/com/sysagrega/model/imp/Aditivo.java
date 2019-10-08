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
import javax.persistence.Transient;

import br.com.sysagrega.repository.IAditivo;

@Entity
@Table(name  = "TB_ADITIVO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_aditivo", sequenceName = "agrega.tb_seq_aditivo", allocationSize = 1)
public class Aditivo implements IAditivo {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_aditivo")
	private Long id;
	
	@OneToOne(targetEntity = Contrato.class, cascade = CascadeType.REFRESH)
	private Contrato contrato;
	
	private Date dataAditivo; 
	
	private Double valor;
	
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

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IContrato#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see br.com.sysagrega.model.imp.IContrato#setId(java.lang.Long)
	 */
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
	public boolean isAtivo() {
		return ativo;
	}

	@Override
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	@Override
	public Contrato getContrato() {
		return contrato;
	}
	@Override
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	@Override
	public Date getDataAditivo() {
		return dataAditivo;
	}
	@Override
	public void setDataAditivo(Date dataAditivo) {
		this.dataAditivo = dataAditivo;
	}
	@Override
	public Double getValor() {
		return valor;
	}
	@Override
	public void setValor(Double valor) {
		this.valor = valor;
	}
 
 
	

		
}
