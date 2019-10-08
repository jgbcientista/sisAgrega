package br.com.sysagrega.model.Enums;

public enum ECaracteristicaMapas {
	
	LOCALIZACAO(1L,"Localiza��o"),
	HIDROGRAFIA(2L,"Hidrografia"),
	UND_CONSERVACAO(3L,"Unidade Conserva��o"),
	VEGETACAO(4L,"Vegeta��o"),
	LEI_MATA_ATLANTICA(5L,"Lei da Mata Atl�ntica"),
	AREA_ESPECIAL(6L,"�rea Especial"),
	ASSENTAMENTO(7L,"Assentamento Agr�rio"),
	TERRA_INDIGNADA(8L,"Terra Ind�gina"),
	QUILOMBOLA(9L,"Quilombola");
	
	
	private Long flag;

	private String descricao;

	 
	private ECaracteristicaMapas(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ECaracteristicaMapas obterPorFlag(Long flag) {

		ECaracteristicaMapas ECaracteristicaMapas = null;

		for (ECaracteristicaMapas tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				ECaracteristicaMapas = tipo;
				break;
			}
		}

		return ECaracteristicaMapas;
	}

}
