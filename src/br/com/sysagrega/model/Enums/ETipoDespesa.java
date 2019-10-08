package br.com.sysagrega.model.Enums;

public enum ETipoDespesa {
	
	CUSTO_EXECUCAO(1L, "CUSTO_EXECUCAO"),
	CUSTO_DESLOCAMENTO(2L, "CUSTO_DESLOCAMENTO"),
	CUSTO_DESPESA_OPERACIONAL(3L, "CUSTO_DESPESA_OPERACIONAL"),
	CUSTO_DESPESA_SEGURANCA(4L, "CUSTO_DESPESA_SEGURANCAL"),
	CUSTO_BDI_COMISSAO(5L, "CUSTO_BDI_COMISSAO"),
	CUSTO_DESPESA_ADMINISTRATIVA(6L, "CUSTO_DESPESA_ADMINISTRATIVAL");
	
	private Long flag;

	private String descricao;

	 
	private ETipoDespesa(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ETipoDespesa obterPorFlag(Long flag) {

		ETipoDespesa eTipoDespesa = null;

		for (ETipoDespesa tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoDespesa = tipo;
				break;
			}
		}

		return eTipoDespesa;
	}

}
