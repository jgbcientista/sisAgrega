package br.com.sysagrega.model.Enums;

public enum ESituacaoRetificacao {
	
	ABERTO(1L, "Aberto"),
	EM_ANDAMENTO(2L, "Andamento"),
	FECHADO(3L, "Fechado");
	
	;
	
	
	private Long flag;

	private String descricao;

	 
	private ESituacaoRetificacao(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ESituacaoRetificacao obterPorFlag(Long flag) {

		ESituacaoRetificacao eTipoDespesa = null;

		for (ESituacaoRetificacao tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoDespesa = tipo;
				break;
			}
		}

		return eTipoDespesa;
	}

}
