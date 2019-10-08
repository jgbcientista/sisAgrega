package br.com.sysagrega.model.Enums;

public enum ETipoRelatorioProposta {
	
	// TIPO DE RELATORIO
	EMITIDAS_POR_MES(1L, "Mensal" ),
	EMITIDAS_POR_CLIENTE(2L, "Cliente"),
	EMITIDAS_POR_AREA_NEGOCIO(3L, "Área de negócio"),
	EMITIDAS_POR_TIPO_NEGOCIO(4L, "Tipo de negócio");
	
	
	private Long flag;
	private String descricao;

	 
	private ETipoRelatorioProposta(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {
		return descricao;
	}

	 
	public static ETipoRelatorioProposta obterPorFlag(Long flag) {

		ETipoRelatorioProposta eTipoRelatorioProposta = null;

		for (ETipoRelatorioProposta tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoRelatorioProposta = tipo;
				break;
			}
		}

		return eTipoRelatorioProposta;
	}

}
