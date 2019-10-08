package br.com.sysagrega.model.Enums;

public enum ETipoRetificacao {
	
	CD(1L, "CD"),
	CTGA(2L, "CTGA"),
	EPI(3L, "EPI"),
	EV(4L, "EV"),
	MAPA(5L, "MAPA"),
	DIAP(6L, "DIAP");
	
	private Long flag;

	private String descricao;

	 
	private ETipoRetificacao(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ETipoRetificacao obterPorFlag(Long flag) {

		ETipoRetificacao eTipoRetificacao = null;

		for (ETipoRetificacao tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoRetificacao = tipo;
				break;
			}
		}

		return eTipoRetificacao;
	}

}
