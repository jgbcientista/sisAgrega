package br.com.sysagrega.model.Enums;

public enum ETipoStatus {
	
	STATUS_PROJETO(1L, "Status Projeto"),
	STATUS_NOTIFICACAO(2L, "Status de Notificação" )
	;
	
	private Long flag;

	private String descricao;

	 
	private ETipoStatus(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ETipoStatus obterPorFlag(Long flag) {

		ETipoStatus eTipoProjeto = null;

		for (ETipoStatus tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoProjeto = tipo;
				break;
			}
		}

		return eTipoProjeto;
	}

}
