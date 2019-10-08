package br.com.sysagrega.model.imp;

import java.util.List;

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

import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.ITipoItemProposta;
import br.com.sysagrega.util.jsf.TipoItemValor;

@Entity
@Table(name = "TB_TIPO_ITEM_PROPOSTA", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_tipo_serv_propos", sequenceName = "agrega.tb_seq_tipo_serv_propos", allocationSize = 1)
public class TipoItemProposta implements ITipoItemProposta {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_tipo_serv_propos")
	private Long id;
	
	
	@Column(length=1000)
	String titulo;
	@Column(length=3500)
	String descricao;
	Long tipoProposta;
	private boolean ativo;
	
	@OneToOne(targetEntity = Proposta.class, cascade = CascadeType.REFRESH)
	private IProposta proposta;
	
	@Transient
	private List<TipoItemValor> listarTipoItemValor;
	
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String getTitulo() {
		return titulo;
	}

	@Override
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public void setProposta(IProposta proposta) {
		this.proposta = proposta;
	}

	@Override
	public IProposta getProposta() {
		return proposta;
	}
	@Override
	public Long getTipoProposta() {
		return tipoProposta;
	}
	@Override
	public void setTipoProposta(Long tipoProposta) {
		this.tipoProposta = tipoProposta;
	}
	
	@Override
	public boolean isAtivo() {
		return ativo;
	}
	@Override
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<TipoItemValor> getListarTipoItemValor() {
		return listarTipoItemValor;
	}

	public void setListarTipoItemValor(List<TipoItemValor> listarTipoItemValor) {
		this.listarTipoItemValor = listarTipoItemValor;
	}

}
