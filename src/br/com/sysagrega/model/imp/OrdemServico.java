package br.com.sysagrega.model.imp;

import java.text.SimpleDateFormat;
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
import br.com.sysagrega.model.IContrato;
import br.com.sysagrega.model.IOrdemServico;

@Entity
@Table(name = "TB_ORDEM_SERVICO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_ordem_serv_seq", sequenceName = "agrega.tb_ordem_serv_seq", allocationSize = 1)
public class OrdemServico implements IOrdemServico{
	
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_ordem_serv_seq")
	private Long id;
	
	private String numeroOS;
	
	private Long contadorNrOS;
	
	@OneToOne(targetEntity = Contrato.class, cascade = CascadeType.REFRESH)
	private IContrato contrato;
	
	private Integer qtdProjetos;
	
	private Integer qtdCadastrada;
	
	private Integer qtdFaturada;
	
	private Double valorFaturado;
	
	private Date datafinalizacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataExclusao;
	
	@OneToOne(targetEntity = Usuario.class, cascade = CascadeType.REFRESH)
	private Usuario usuarioAtualizacao;
	
	@OneToOne(targetEntity = Cliente.class, cascade = CascadeType.REFRESH)
	private ICliente cliente;
	
	@Temporal(TemporalType.DATE)
	private Date dataInclusao;
	
	@Transient
	private Date dataFiltroInicial;
	
	@Transient
	private Date dataFiltroFinal;
	
	private boolean ativo;
	
	public String getRetornoAno(Date data){
		SimpleDateFormat formatoAno = new SimpleDateFormat("yyyy");
		
		if(data == null){
			return "-";
		}
		
		return formatoAno.format(data);
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
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;		
	}
	@Override
	public Date getDataExclusao() {
		return dataExclusao;
	}
	@Override
	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
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

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	@Override
	public Long getContadorNrOS() {
		return contadorNrOS;
	}

	@Override
	public void setContadorNrOS(Long contadorNrOS) {
		this.contadorNrOS = contadorNrOS;
	}

	@Override
	public IContrato getContrato() {
		return contrato;
	}

	@Override
	public void setContrato(IContrato contrato) {
		this.contrato = contrato;
	}

	@Override
	public Integer getQtdProjetos() {
		return qtdProjetos;
	}

	@Override
	public void setQtdProjetos(Integer qtdProjetos) {
		this.qtdProjetos = qtdProjetos;
	}

	@Override
	public Integer getQtdCadastrada() {
		return qtdCadastrada;
	}

	@Override
	public void setQtdCadastrada(Integer qtdCadastrada) {
		this.qtdCadastrada = qtdCadastrada;
	}

	@Override
	public Integer getQtdFaturada() {
		return qtdFaturada;
	}

	@Override
	public void setQtdFaturada(Integer qtdFaturada) {
		this.qtdFaturada = qtdFaturada;
	}

	@Override
	public Double getValorFaturado() {
		return valorFaturado;
	}

	@Override
	public void setValorFaturado(Double valorFaturado) {
		this.valorFaturado = valorFaturado;
	}
	@Override
	public Date getDatafinalizacao() {
		return datafinalizacao;
	}
	@Override
	public void setDatafinalizacao(Date datafinalizacao) {
		this.datafinalizacao = datafinalizacao;
	}
	@Override
	public ICliente getCliente() {
		return cliente;
	}
	@Override
	public void setCliente(ICliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataFiltroInicial() {
		return dataFiltroInicial;
	}

	public void setDataFiltroInicial(Date dataFiltroInicial) {
		this.dataFiltroInicial = dataFiltroInicial;
	}

	public Date getDataFiltroFinal() {
		return dataFiltroFinal;
	}

	public void setDataFiltroFinal(Date dataFiltroFinal) {
		this.dataFiltroFinal = dataFiltroFinal;
	}

	 
	
}
