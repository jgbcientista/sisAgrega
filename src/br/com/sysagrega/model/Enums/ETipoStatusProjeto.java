package br.com.sysagrega.model.Enums;

public enum ETipoStatusProjeto {
	
	A_FATURA(1L, "Pendente de Faturamento"),
	FATURADO(2L, "Faturado"),
	A_CONFORMAR_FATURAMENTO(3L, "A Confirmar Faturamento"),
	PLANEJADO(4L, "Planejado"),
	MAPA(5L, "Pendente de Planejamento"),
	RETIFICADO_DIAP(6L, "Retificado - DIAP"),
	RETIFICADO_SEIA(7L, "Retificado - SEIA"),
	RETIFICACAO_GERAL(7L, "Retificado geral"),
	RETIFICADO_TODOS(8L, "Retificado - todos");
	
	
	private Long flag;

	private String descricao;

	 
	private ETipoStatusProjeto(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ETipoStatusProjeto obterPorFlag(Long flag) {

		ETipoStatusProjeto eTipoStatusProjeto = null;

		for (ETipoStatusProjeto tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoStatusProjeto = tipo;
				break;
			}
		}

		return eTipoStatusProjeto;
	}

}
