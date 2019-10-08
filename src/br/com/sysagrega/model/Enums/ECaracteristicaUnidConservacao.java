package br.com.sysagrega.model.Enums;

public enum ECaracteristicaUnidConservacao {
	
	FEDERAL(1L,"Federal"),
	ESTADUAL(2L,"Estadual"),
	MUNICIPAL(3L,"Municipal"),
	PARTICULAR(4L,"Particular (RPPN)");
	
	private Long flag;

	private String descricao;

	 
	private ECaracteristicaUnidConservacao(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ECaracteristicaUnidConservacao obterPorFlag(Long flag) {

		ECaracteristicaUnidConservacao eCaracteristicaUnidConservacao = null;

		for (ECaracteristicaUnidConservacao tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eCaracteristicaUnidConservacao = tipo;
				break;
			}
		}

		return eCaracteristicaUnidConservacao;
	}

}
