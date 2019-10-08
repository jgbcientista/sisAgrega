
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

import br.com.sysagrega.model.INotificacao;
import br.com.sysagrega.model.IProjeto;
import br.com.sysagrega.model.IRegistro;

@Entity
@Table(name = "TB_NOTIFICACAO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_notifica_seq", sequenceName = "agrega.tb_notifica_seq", allocationSize = 1)
public class Notificacao implements INotificacao{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_notifica_seq")
	private Long id;
	 
	private Long numero;
	
	private Date dataRecebimento;
	
	private String responsavelResposta;
	
	private String motivo;
	
	private Date dataResposta;
	
	@OneToOne(targetEntity = Projeto.class, cascade = CascadeType.REFRESH)
	private IProjeto projeto;
	
	@OneToOne(targetEntity = Registro.class, cascade = CascadeType.REFRESH)
	private IRegistro registro;

	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getNumero() {
		return numero;
	}

	@Override
	public void setNumero(Long numero) {
		this.numero = numero;
	}

	 

	@Override
	public Date getDataRecebimento() {
		return dataRecebimento;
	}
	@Override
	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	@Override
	public String getResponsavelResposta() {
		return responsavelResposta;
	}

	@Override
	public void setResponsavelResposta(String responsavelResposta) {
		this.responsavelResposta = responsavelResposta;
	}

	@Override
	public String getMotivo() {
		return motivo;
	}

	@Override
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Override
	public Date getDataResposta() {
		return dataResposta;
	}

	@Override
	public void setDataResposta(Date dataResposta) {
		this.dataResposta = dataResposta;
	}

	@Override
	public IProjeto getProjeto() {
		return projeto;
	}

	@Override
	public void setProjeto(IProjeto projeto) {
		this.projeto = projeto;
	}
	 
	@Override
	public IRegistro getRegistro() {
		return registro;
	}
	@Override
	public void setRegistro(IRegistro registro) {
		this.registro = registro;
	}
	
	
	 
}
