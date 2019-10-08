package br.com.sysagrega.controller.vo;

import java.util.ArrayList;
import java.util.List;

import br.com.sysagrega.model.imp.Proposta;

public class PropostaMesVO {
	
	private String nomeMes;
	private List<Proposta> listPropostas;
	private double totalVlrProposta;
	private Integer totalQdtProposta;
	
	
	public PropostaMesVO() {
		listPropostas = new ArrayList<Proposta>();
    }
     
    public PropostaMesVO(String nomeMes) {
        this.nomeMes = nomeMes;
        listPropostas = new ArrayList<Proposta>();
    }


	public String getNomeMes() {
		return nomeMes;
	}


	public void setNomeMes(String nomeMes) {
		this.nomeMes = nomeMes;
	}


	public List<Proposta> getListPropostas() {
		return listPropostas;
	}


	public void setListPropostas(List<Proposta> listPropostas) {
		this.listPropostas = listPropostas;
	}

	public double getTotalVlrProposta() {
	   return totalVlrProposta;
	}

	public void setTotalVlrProposta(double totalVlrProposta) {
		this.totalVlrProposta = totalVlrProposta;
	}

	public Integer getTotalQdtProposta() {
		return totalQdtProposta;
	}

	public void setTotalQdtProposta(Integer totalQdtProposta) {
		this.totalQdtProposta = totalQdtProposta;
	}
}
