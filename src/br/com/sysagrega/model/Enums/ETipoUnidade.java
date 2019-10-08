package br.com.sysagrega.model.Enums;

public enum ETipoUnidade {
	
	UNIDADE(1L, "Und"),
	KILOMETRO(2L, "Km"),
	DIARIA(3L, "Diária"),
	HOMEM_HORA(4L, "h/h");
	
	
	private Long flag;

	private String descricao;

	 
	private ETipoUnidade(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ETipoUnidade obterPorFlag(Long flag) {

		ETipoUnidade eTipoDespesa = null;

		for (ETipoUnidade tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoDespesa = tipo;
				break;
			}
		}

		return eTipoDespesa;
	}

}
