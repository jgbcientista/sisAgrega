package br.com.sysagrega.model.Enums;

public enum TipoSeguimentoCliente {
	
	AGRICULTURA("Agricultura"),
	ALIMENTOS("Alimentos"),
	ARMAZEM("Armazém"),
	CALCADOS("Calçados"),
	CERAMICA("Ceramica"),
	COMERCIO("Comércio"),
	CONST_CIVIL("Construção Civil"),
	CONSULTORIA("Consultoria"),
	COURO("Couro"),
	ENERGIA("Energia"),
	FARMACOS("Fármacos"),
	IND_MINERIOS("Ind. Minerios"),
	INFORMATICA("Informática"),
	MOVEIS("Móveis"),
	PAPEL("Papel"),
	PERFUMARIA("Perfumaria"),
	PLASTICO("Plástico"),
	QUIM_PETROQ("Química/Petroquímica"),
	TINTAS("Tintas"),
	TRANSPORTE("Transporte"),
	VESTIARIO("Vestiário");
	
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
