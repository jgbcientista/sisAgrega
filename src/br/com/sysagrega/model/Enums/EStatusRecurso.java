package br.com.sysagrega.model.Enums;

public enum EStatusRecurso {
	
	EM_USO(1L, "Em Uso"),
	EM_MANUTENCAO(2L, "Em Manutenção"),
	SEM_APLICACAO(3L, "Sem Aplicação"),
	DESCONTINUADO(4L, "Descontinuado")
	;
	
	
	private Long flag;

	private String descricao;

	 
	private EStatusRecurso(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static EStatusRecurso obterPorFlag(Long flag) {

		EStatusRecurso eTipoDespesa = null;

		for (EStatusRecurso tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoDespesa = tipo;
				break;
			}
		}

		return eTipoDespesa;
	}

}
