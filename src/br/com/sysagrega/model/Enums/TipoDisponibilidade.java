package br.com.sysagrega.model.Enums;

public enum TipoDisponibilidade {
	
	VIAGENS("Viagens"),
	MEIO_TURNO("Meio Turno"),
	LOCAL("Local"),
	TODAS("TODAS");
	
		
	private String descricao;

	private TipoDisponibilidade(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
