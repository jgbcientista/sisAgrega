package br.com.sysagrega.controller.vo;

import java.util.List;

import br.com.sysagrega.model.IProfissional;

public class EmailVO {
	
	private static final long serialVersionUID = 1L;
	
	private String assunto;
	private String corpo;
	private String destinatario;
	private String destinatariosInvalidos;
	private List<IProfissional> listaEnvolvidos;
	private Boolean api;
	private Boolean evctgal;
	private Boolean mapa;
	private Boolean verificacao;
	
	
	
	
	
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getDestinatariosInvalidos() {
		return destinatariosInvalidos;
	}
	public void setDestinatariosInvalidos(String destinatariosInvalidos) {
		this.destinatariosInvalidos = destinatariosInvalidos;
	}
	public List<IProfissional> getListaEnvolvidos() {
		return listaEnvolvidos;
	}
	public void setListaEnvolvidos(List<IProfissional> listaEnvolvidos) {
		this.listaEnvolvidos = listaEnvolvidos;
	}
	public Boolean getApi() {
		return api;
	}
	public void setApi(Boolean api) {
		this.api = api;
	}
	public Boolean getEvctgal() {
		return evctgal;
	}
	public void setEvctgal(Boolean evctgal) {
		this.evctgal = evctgal;
	}
	public Boolean getMapa() {
		return mapa;
	}
	public void setMapa(Boolean mapa) {
		this.mapa = mapa;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Boolean getVerificacao() {
		return verificacao;
	}
	public void setVerificacao(Boolean verificacao) {
		this.verificacao = verificacao;
	}
	
	
	
}
