package br.com.sysagrega.model.Enums;

public enum ETipoProjeto {
	
	// NEGOCIO DE QUALIDADE
	NEGOCIO_DE_QUALIDADE(1L, "Qualidade" ),
	MEIO_AMBIENTE(2L, "Meio Ambiente"),
	SAUDE_E_SEGURANCA(3L, "Saúde e Segurança");
	
	private Long flag;

	private String descricao;

	 
	private ETipoProjeto(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ETipoProjeto obterPorFlag(Long flag) {

		ETipoProjeto eTipoProjeto = null;

		for (ETipoProjeto tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoProjeto = tipo;
				break;
			}
		}

		return eTipoProjeto;
	}

}
