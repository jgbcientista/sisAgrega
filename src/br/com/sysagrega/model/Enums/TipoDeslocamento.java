package br.com.sysagrega.model.Enums;

public enum TipoDeslocamento {
	
	SSA_CMA_SSA_VIAGENS("SSA x CMA x SSA(Viagens)-Km"),
	TAXI("Taxi"),
	ALUGUEL_CARRO("Aluguel de Carro"),
	VEICULO_MANUTENCAO_COMBUSTIVEL("Veículo, Manutenção e Combustível");
	
	private String descricao;

	private TipoDeslocamento(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
