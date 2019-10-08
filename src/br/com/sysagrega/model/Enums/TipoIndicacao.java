package br.com.sysagrega.model.Enums;

public enum TipoIndicacao {
	
	INDICACAO("Indicação "),
	JORNAL("Jornal"),
	REVISTA("Revista"),
	SITE_EMPRESA("Site da Empresa"),
	FUNCIONARIO("Funcionário"),
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
