package br.com.sysagrega.model.Enums;

public enum TipoTamanhoCliente {
	
	PEQUENO("Pequeno"),
	MEDIO("M�dio"),
	GRANDE("Grande");
	
	private String descricao;

	private TipoTamanhoCliente(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
