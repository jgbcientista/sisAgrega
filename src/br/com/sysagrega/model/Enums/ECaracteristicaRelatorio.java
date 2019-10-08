package br.com.sysagrega.model.Enums;


public enum ECaracteristicaRelatorio {
	
	EPI(1L,"EPI"),
	EV(2L,"EV"),
	RO(3L,"EPI"),
	CASA_EM_APP(4L,"Casa em APP"),
	TAM_LINHA_DIFERENTE(5L,"Tamanho Linha Diferente"),
	OBRA_CONSTRUIDA(6L,"Obra construída"),
	VISTORIA_NAO_REALIZADA(7L,"Vistoria não realizada"),
	OUTROS(8L,"outros"),
	/*DIAP_SEIA(9L,"DIAP/SEIA"),*/
	RCA(10L,"RCA"),
	ICMBIO(11L,"ICMBIO"),
	RIUC(12L,"RIUC");
	
	
	
	private Long flag;

	private String descricao;

	 
	private ECaracteristicaRelatorio(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ECaracteristicaRelatorio obterPorFlag(Long flag) {

		ECaracteristicaRelatorio eCaracteristica = null;

		for (ECaracteristicaRelatorio tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eCaracteristica = tipo;
				break;
			}
		}

		return eCaracteristica;
	}

}
