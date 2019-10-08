package br.com.sysagrega.model.Enums;

public enum TipoFornecedor {
	
	PESSOA_FISICA("Pessoa F�sica"),
	PESSOA_JURIDICA("Pessoa Jur�dica"),
	ORGAOS_PUBLICOS("Outras partes interessadas");
	
	private String descricao;

	private TipoFornecedor(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
