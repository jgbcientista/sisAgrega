package br.com.sysagrega.model.Enums;

public enum TipoFornecedor {
	
	PESSOA_FISICA("Pessoa Física"),
	PESSOA_JURIDICA("Pessoa Jurídica"),
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
