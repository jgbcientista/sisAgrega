package br.com.sysagrega.model.Enums;

public enum ETamanhoProjeto {
	
	KM(1L, "KM"),
	AREA_M2(2L, "Aréa M2");
	
	private Long flag;

	private String descricao;

	 
	private ETamanhoProjeto(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ETamanhoProjeto obterPorFlag(Long flag) {

		ETamanhoProjeto eTipoDespesa = null;

		for (ETamanhoProjeto tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoDespesa = tipo;
				break;
			}
		}

		return eTipoDespesa;
	}

}
