package br.com.sysagrega.model.Enums;

public enum TipoSeguimentoCliente {
	
	AGRICULTURA("Agricultura"),
	ALIMENTOS("Alimentos"),
	ARMAZEM("Armaz�m"),
	CALCADOS("Cal�ados"),
	CERAMICA("Ceramica"),
	COMERCIO("Com�rcio"),
	CONST_CIVIL("Constru��o Civil"),
	CONSULTORIA("Consultoria"),
	COURO("Couro"),
	ENERGIA("Energia"),
	FARMACOS("F�rmacos"),
	IND_MINERIOS("Ind. Minerios"),
	INFORMATICA("Inform�tica"),
	MOVEIS("M�veis"),
	PAPEL("Papel"),
	PERFUMARIA("Perfumaria"),
	PLASTICO("Pl�stico"),
	QUIM_PETROQ("Qu�mica/Petroqu�mica"),
	TINTAS("Tintas"),
	TRANSPORTE("Transporte"),
	VESTIARIO("Vesti�rio");
	
	private String descricao;

	private TipoSeguimentoCliente(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
