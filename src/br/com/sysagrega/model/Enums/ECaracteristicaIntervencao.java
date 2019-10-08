package br.com.sysagrega.model.Enums;


public enum ECaracteristicaIntervencao {
	
	DIAP_SEIA(1L,"DIAP/SEIA"),
	AREA_TORNO_LAGO(2L,"Área em torno de lagos, nascente etc"),
	FAIXA_MARGINAL(3L,"Faixa marginal cursos d'Água"),
	TOPO_MORRO(4L,"Topo Morro"),
	SEIA(5L,"SEIA");
	
	
	private Long flag;

	private String descricao;

	 
	private ECaracteristicaIntervencao(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ECaracteristicaIntervencao obterPorFlag(Long flag) {

		ECaracteristicaIntervencao eCaracteristica = null;

		for (ECaracteristicaIntervencao tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eCaracteristica = tipo;
				break;
			}
		}

		return eCaracteristica;
	}

}
