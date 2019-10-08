package br.com.sysagrega.model.Enums;

public enum TipoColaborador {
	
	SOCIO("Sócio"),
	EMPREGADO("Empregado"),
	PARCEIRO("Parceiro"),
	ESTAGIO("Estagiário");
	
	private String descricao;

	private TipoColaborador(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
