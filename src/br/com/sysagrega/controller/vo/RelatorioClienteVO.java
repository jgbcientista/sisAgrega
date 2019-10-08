package br.com.sysagrega.controller.vo;

import java.util.Date;
import java.util.List;

import br.com.sysagrega.model.imp.Proposta;

public class RelatorioClienteVO {
	
	private Long idCliente;
	private Date dataInicial;
	private Date dataFinal;
	private String tipoRelatorio;
	private List<Proposta> listProposta;
	private Long idServicoNegocio;
	private Long idArea;
	private Integer anoRelatorio;
	
	public RelatorioClienteVO(){
		
	}


	public Long getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}


	public Date getDataInicial() {
		return dataInicial;
	}


	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}


	public Date getDataFinal() {
		return dataFinal;
	}


	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}


	public List<Proposta> getListProposta() {
		return listProposta;
	}


	public void setListProposta(List<Proposta> listProposta) {
		this.listProposta = listProposta;
	}


	public String getTipoRelatorio() {
		return tipoRelatorio;
	}


	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}


	public Long getIdServicoNegocio() {
		return idServicoNegocio;
	}


	public void setIdServicoNegocio(Long idServicoNegocio) {
		this.idServicoNegocio = idServicoNegocio;
	}


	public Long getIdArea() {
		return idArea;
	}


	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}


	public Integer getAnoRelatorio() {
		return anoRelatorio;
	}


	public void setAnoRelatorio(Integer anoRelatorio) {
		this.anoRelatorio = anoRelatorio;
	}
	
	
}
