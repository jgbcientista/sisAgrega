package br.com.sysagrega.model.Enums;

public enum TipoArquivoRelatorio {
	
	PDF(".pdf"),
	JASPER(".jasper");
	
	private String descricao;

	private TipoArquivoRelatorio(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
