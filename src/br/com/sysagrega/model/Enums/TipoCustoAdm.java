package br.com.sysagrega.model.Enums;

public enum TipoCustoAdm {
	
	ADMINISTRACAO("Administração"),
	EQUIPE_COMUNICACAO("Equipe e comunicação"),
	IMPRESSAO("Impressão"),
	MAT_ESCRITORIO("Mat. Escritório");
	
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
