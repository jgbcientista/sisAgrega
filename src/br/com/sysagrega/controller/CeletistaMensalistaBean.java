package br.com.sysagrega.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sysagrega.model.imp.CltMensal;
import br.com.sysagrega.service.ICltMensalService;
import br.com.sysagrega.service.imp.NegocioException;
import br.com.sysagrega.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CeletistaMensalistaBean implements Serializable {
	/**
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICltMensalService cltMensalService;

	private CltMensal cltMensal;
	private CltMensal filtro;
	private List<CltMensal> listCltMensal;
	private Boolean pesquisar;
	private Boolean novo;
	
	

	@PostConstruct
	public void inicializar() {
		setFiltro(new CltMensal());
		listCltMensal = cltMensalService.getAllCltMensal();
		pesquisar = true;
		novo = false;
	}


	public void pesquisar(){
		pesquisar = false;
		novo = true;
	}
	
	public void novo(){
		pesquisar = false;
		cltMensal = new CltMensal();
		cltMensal.setDateCriacao(new Date());
		cltMensal.setStatus("Vigente");
		cltMensal.setAtivo(true);
	}
	
	
	public void cancelar(){
		cltMensal = new CltMensal();
		pesquisar = true;
	}
	
	public void salvar(){
		try{
			cltMensalService.salvar(cltMensal);
			cltMensal = new CltMensal();
			pesquisar = true;
			listCltMensal = cltMensalService.getAllCltMensal();
			FacesUtil.addInfoMessage("Registro salvo com sucesso.");
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar "+e);
		}
	}


	public Boolean getPesquisar() {
		return pesquisar;
	}


	public void setPesquisar(Boolean pesquisar) {
		this.pesquisar = pesquisar;
	}


	public Boolean getNovo() {
		return novo;
	}


	public void setNovo(Boolean novo) {
		this.novo = novo;
	}

	public CltMensal getCltMensal() {
		return cltMensal;
	}


	public void setCltMensal(CltMensal cltMensal) {
		this.cltMensal = cltMensal;
	}


	public CltMensal getFiltro() {
		return filtro;
	}


	public void setFiltro(CltMensal filtro) {
		this.filtro = filtro;
	}


	public List<CltMensal> getListCltMensal() {
		return listCltMensal;
	}


	public void setListCltMensal(List<CltMensal> listCltMensal) {
		this.listCltMensal = listCltMensal;
	}


		
		
}
