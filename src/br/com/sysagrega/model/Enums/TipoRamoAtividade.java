package br.com.sysagrega.model.Enums;

public enum TipoRamoAtividade {
	
	ADVOCACIA("Advocacia"),
	ALIMENTACAO("Alimenta��o"),
	ALUGUEL_DE_BENS_MOVEIS("Aluguel de bens m�veis"),
	ASSOCIACAO("Associa��o"),
	CONTABILIDADE("Contabilidade"),
	CONCELHO_CLASSE_PROFISSIONAL("Conselho de Classe Profissional"),
	DEFESA_CIVIL("Defesa Civil"),
	CORPO_BOMBEIRO("Corpo de Bombeiro"),
	FUNDACAO("Funda��o"),
	IMOBILIARIO("Imobili�rio"),
	INFORMATICA("Inform�tica"),
	LAZER("Lazer"),
	LICENCAS_AUTORIZACOES("Licen�as/Autoriza��es"),
	MARCAS_E_PATENTES("Marcas e Patentes"),
	ORGAO_AMBIENTAL("�rg�o Ambiental"),
	ONG("ONG"),
	SEGUROS("Seguros"),
	SERVICOS_BANCARIOS("Servi�os Banc�rios"),
	SOCIEDADE("Sociedade"),
	TELEFONIA("Telefonia"),
	VESTUARIO("Vestu�rio"),
	OUTROS("Outros");
	
	
	
	private String descricao;

	private TipoRamoAtividade(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
