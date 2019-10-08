package br.com.sysagrega.model.Enums;

public enum EFaseExecucao {
	
	FASE_1(1L, "Fase 1"),
	FASE_2(2L, "Fase 2"),
	FASE_3(3L, "Fase 3");
	
	
	private Long flag;

	private String descricao;

	 
	private EFaseExecucao(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static EFaseExecucao obterPorFlag(Long flag) {

		EFaseExecucao eTipoDespesa = null;

		for (EFaseExecucao tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoDespesa = tipo;
				break;
			}
		}

		return eTipoDespesa;
	}

}
