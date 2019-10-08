package br.com.sysagrega.model.Enums;

public enum EAnoPesquisa {
	
	// TIPO DE RELATORIO
	ANO_2017(1L, "2017" ),
	ANO_2018(2L, "2018"),
	ANO_2019(3L, "2019");
	
	
	private Long flag;
	private String descricao;

	 
	private EAnoPesquisa(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {
		return descricao;
	}

	 
	public static EAnoPesquisa obterPorFlag(Long flag) {

		EAnoPesquisa eAnoPesquisa = null;

		for (EAnoPesquisa tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eAnoPesquisa = tipo;
				break;
			}
		}

		return eAnoPesquisa;
	}

}
