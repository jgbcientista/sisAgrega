package br.com.sysagrega.model.Enums;

public enum TipoCliente {
	
	PESSOA_FISICA("Pessoa F�sica"),
	PESSOA_JURIDICA("Pessoa Jur�dica");
	
	private String descricao;

	private TipoCliente(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
