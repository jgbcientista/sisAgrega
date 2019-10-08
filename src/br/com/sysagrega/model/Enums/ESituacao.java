package br.com.sysagrega.model.Enums;

public enum ESituacao {
	
	PENDENTE_PRIMEIRO_PAGAMENTO(1L,"1ª Parcela - Pendente"),
	PENDENTE_SEGUNDO_PAGAMENTO(2L, "2ª Parcela - Pendente"),
	PENDENTE_TERCEIRO_PAGAMENTO(3L,"3ª Parcela - Pendente"),
	
	PRIMEIRO_FATURAMENTO_CONFIRMADO(4L,"1ª Parcela - Faturado"),
	SEGUNDO_FATURAMENTO_CONFIRMADO(5L, "2ª Parcela - Faturado"),
	TERCEIRO_FATURAMENTO_CONFIRMADO(6L,"3ª Parcela - Faturado"),
	
	CONFIRMAR_PRIMEIRO_PAGAMENTO(7L,"Confirmar 1ª Pagamento"),
	CONFIRMAR_SEGUNDO_PAGAMENTO(8L, "Confirmar 2ª Pagamento"),
	CONFIRMAR_TERCEIRO_PAGAMENTO(9L,"Confirmar 3ª Pagamento"),
	
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
