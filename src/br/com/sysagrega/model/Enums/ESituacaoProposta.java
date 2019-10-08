package br.com.sysagrega.model.Enums;

public enum ESituacaoProposta {
	
	ACEITE(1L,"Aceite"),
	REVISADA(2L,"Revisada"),
	NAO_ACEITE(3L,"Não aceite");
	

	private Long flag;

	private String descricao;

	 
	private ESituacaoProposta(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ESituacaoProposta obterPorFlag(Long flag) {

		ESituacaoProposta eTipo = null;

		for (ESituacaoProposta tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipo = tipo;
				break;
			}
		}

		return eTipo;
	}




}
