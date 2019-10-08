package br.com.sysagrega.model.Enums;

public enum SituacaoPlanejamento {
	
	SEM_PLANEJAMENTO(1L, "Sem Planejamento"),
	PENDENTE(2L, "Pendente"),
	CONCLUIDO(3L, "Concluído");
	
	
	private Long flag;
	private String descricao;

	 
	private SituacaoPlanejamento(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static SituacaoPlanejamento obterPorFlag(Long flag) {
		SituacaoPlanejamento situacaoPlanejamento = null;
		for (SituacaoPlanejamento tipo : values()) {
			if (tipo.getFlag().equals(flag)) {
				situacaoPlanejamento = tipo;
				break;
			}
		}
		return situacaoPlanejamento;
	}

}
