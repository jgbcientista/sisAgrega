package br.com.sysagrega.model.Enums;

public enum TipoIndicacao {
	
	INDICACAO("Indica��o "),
	JORNAL("Jornal"),
	REVISTA("Revista"),
	SITE_EMPRESA("Site da Empresa"),
	FUNCIONARIO("Funcion�rio"),
	INTERNET("Internet"),
	OUTROS("Outros");
	
	private String descricao;

	private TipoIndicacao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
