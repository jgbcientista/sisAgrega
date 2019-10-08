package br.com.sysagrega.model.Enums;

public enum ESituacao {
	
	PENDENTE_PRIMEIRO_PAGAMENTO(1L,"1� Parcela - Pendente"),
	PENDENTE_SEGUNDO_PAGAMENTO(2L, "2� Parcela - Pendente"),
	PENDENTE_TERCEIRO_PAGAMENTO(3L,"3� Parcela - Pendente"),
	
	PRIMEIRO_FATURAMENTO_CONFIRMADO(4L,"1� Parcela - Faturado"),
	SEGUNDO_FATURAMENTO_CONFIRMADO(5L, "2� Parcela - Faturado"),
	TERCEIRO_FATURAMENTO_CONFIRMADO(6L,"3� Parcela - Faturado"),
	
	CONFIRMAR_PRIMEIRO_PAGAMENTO(7L,"Confirmar 1� Pagamento"),
	CONFIRMAR_SEGUNDO_PAGAMENTO(8L, "Confirmar 2� Pagamento"),
	CONFIRMAR_TERCEIRO_PAGAMENTO(9L,"Confirmar 3� Pagamento"),
	
	FINALIZADO(10L, "Encerrado"),
	PENDENTE(11L,"Pendente"),
	QUITADO(12L,"Quitado");
	
	private Long flag;

	private String descricao;

	 
	private ESituacao(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static ESituacao obterPorFlag(Long flag) {

		ESituacao eTipoDespesa = null;

		for (ESituacao tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				eTipoDespesa = tipo;
				break;
			}
		}

		return eTipoDespesa;
	}

}
