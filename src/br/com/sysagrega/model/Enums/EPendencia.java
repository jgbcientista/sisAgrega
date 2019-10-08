package br.com.sysagrega.model.Enums;

public enum EPendencia {
	
	DOCUMENTO(1L, "Documento"),
	LEGISLAÇÃO(2L, "Legislação"),
	FINANCEIRA(3L, "Financeira"),
	DOCUMENTO_EMPRESA(4L, "Documento da Empresa"),
	TODOS(5L, "Todos"),
	OUTRO(6L, "Nenhum");
	
	private Long flag;

	private String descricao;

	 
	private EPendencia(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static EPendencia obterPorFlag(Long flag) {

		EPendencia eTipoDespesa = null;

		for (EPendencia tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoDespesa = tipo;
				break;
			}
		}

		return eTipoDespesa;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
