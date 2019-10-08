package br.com.sysagrega.model.Enums;

public enum ETipoNotificacao {
	
	SEA(1L, "Sea"),
	DIAP(2L, "Diap");
	
	;
	
	private Long flag;

	private String descricao;

	 
	private ETipoNotificacao(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ETipoNotificacao obterPorFlag(Long flag) {

		ETipoNotificacao eTipoDespesa = null;

		for (ETipoNotificacao tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoDespesa = tipo;
				break;
			}
		}

		return eTipoDespesa;
	}

}
