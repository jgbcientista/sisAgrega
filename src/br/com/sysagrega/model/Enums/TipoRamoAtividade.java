package br.com.sysagrega.model.Enums;

public enum TipoRamoAtividade {
	
	ADVOCACIA("Advocacia"),
	ALIMENTACAO("Alimentação"),
	ALUGUEL_DE_BENS_MOVEIS("Aluguel de bens móveis"),
	ASSOCIACAO("Associação"),
	CONTABILIDADE("Contabilidade"),
	CONCELHO_CLASSE_PROFISSIONAL("Conselho de Classe Profissional"),
	DEFESA_CIVIL("Defesa Civil"),
	CORPO_BOMBEIRO("Corpo de Bombeiro"),
	FUNDACAO("Fundação"),
	IMOBILIARIO("Imobiliário"),
	INFORMATICA("Informática"),
	LAZER("Lazer"),
	LICENCAS_AUTORIZACOES("Licenças/Autorizações"),
	MARCAS_E_PATENTES("Marcas e Patentes"),
	ORGAO_AMBIENTAL("Órgão Ambiental"),
	ONG("ONG"),
	SEGUROS("Seguros"),
	SERVICOS_BANCARIOS("Serviços Bancários"),
	SOCIEDADE("Sociedade"),
	TELEFONIA("Telefonia"),
	VESTUARIO("Vestuário"),
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
