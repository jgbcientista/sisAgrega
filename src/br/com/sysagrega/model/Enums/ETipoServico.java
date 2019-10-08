package br.com.sysagrega.model.Enums;

public enum ETipoServico {
	
	ENGENHARIA(1L, "Engenharua"),
	SERVICOS_GERAL(2L, "Serviços em Geral");
	
	private String descricao;

	private ETipoServico(Long id, String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
