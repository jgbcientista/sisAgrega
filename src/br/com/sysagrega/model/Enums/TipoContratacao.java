package br.com.sysagrega.model.Enums;

public enum TipoContratacao {
	
	REGISTRADO_CLT("Registro CLT"),
	PJ("Pessoa Jurídica"),
	AUTONOMO("Autônomo"),
	ESTAGIARIO("Estágio"),
	OUTROS("Outros");
		
	private String descricao;

	private TipoContratacao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
