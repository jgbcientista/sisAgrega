package br.com.sysagrega.model.Enums;

public enum TipoCustoAdm {
	
	ADMINISTRACAO("Administra��o"),
	EQUIPE_COMUNICACAO("Equipe e comunica��o"),
	IMPRESSAO("Impress�o"),
	MAT_ESCRITORIO("Mat. Escrit�rio");
	
	private String descricao;

	private TipoCustoAdm(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
