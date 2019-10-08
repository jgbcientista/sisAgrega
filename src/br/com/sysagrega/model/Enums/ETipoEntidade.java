package br.com.sysagrega.model.Enums;

public enum ETipoEntidade {
	
	CLIENTE(1L, "Cliente"),
	FORNECEDOR(2L, "Fornecedor"),
	CONTRATO(3L, "Contrato"),
	PROJETO (4L, "Projeto"),
	PROPOSTA (5L, "Proposta"),
	PLANEJAMENTO (6L, "Planejamento"),
	PROFISSIONAL(7l, "Profissional");
	
	private Long flag;

	private String descricao;

	 
	private ETipoEntidade(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ETipoEntidade obterPorFlag(Long flag) {

		ETipoEntidade eTipoDespesa = null;

		for (ETipoEntidade tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoDespesa = tipo;
				break;
			}
		}

		return eTipoDespesa;
	}

}
