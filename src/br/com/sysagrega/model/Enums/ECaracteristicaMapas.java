package br.com.sysagrega.model.Enums;

public enum ECaracteristicaMapas {
	
	LOCALIZACAO(1L,"Localização"),
	HIDROGRAFIA(2L,"Hidrografia"),
	UND_CONSERVACAO(3L,"Unidade Conservação"),
	VEGETACAO(4L,"Vegetação"),
	LEI_MATA_ATLANTICA(5L,"Lei da Mata Atlântica"),
	AREA_ESPECIAL(6L,"Área Especial"),
	ASSENTAMENTO(7L,"Assentamento Agrário"),
	TERRA_INDIGNADA(8L,"Terra Indígina"),
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
