package br.com.sysagrega.model.Enums;

public enum EPerfil {
	
	DIRETOR(1L, "DIRETOR"),
	ADM(2L, "ADMINISTRATIVO"),
	RECEPCAO(3L, "RECEPCAO"),
	SECRETARIA(4L, "SECRETARIA"),
	PUBLICO(5L, "PUBLICO"),
	OPERACIONAL(6L, "OPERACIONAL");
	
	private Long flag;
	private String descricao;

	 
	private EPerfil(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static EPerfil obterPorFlag(Long flag) {

		EPerfil etipo = null;

		for (EPerfil tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				etipo = tipo;
				break;
			}
		}

		return etipo;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
