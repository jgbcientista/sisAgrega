package br.com.sysagrega.controller.vo;

import java.util.Date;

public class CeletistaMensalistaVO {
	
	private Date dataInicio; 
	private Date dataFim;
	
	
	
	
	public CeletistaMensalistaVO(){
		
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	
}
