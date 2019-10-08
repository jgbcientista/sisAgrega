package br.com.sysagrega.model.Enums;

public enum EStatus {
	
	FASE_1(1L, "Fase 1"),
	FASE_2(2L, "Fase 2"),
	FASE_3(3L, "Fase 3"),
	CONCLUIDO(4L, "Concluído"),
	PROTOCOLADO(5L, "Protocolado"),
	FORMADO(6L, "Formado");
	
	
	private Long flag;

	private String descricao;

	 
	private EStatus(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static EStatus obterPorFlag(Long flag) {

		EStatus eTipoDespesa = null;

		for (EStatus tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoDespesa = tipo;
				break;
			}
		}

		return eTipoDespesa;
	}

}
