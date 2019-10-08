package br.com.sysagrega.model.Enums;

public enum TipoStatus {
	
	CONTRATO_ATIVO("Contrato Ativo"),
	CONTRATO_INATIVO("Sem Contrato"),
	INADIMPLENTE("inadimplente"),
	PROPOSTA_ANALISE("Proposta em An�lise");
	
	private String descricao;

	private TipoStatus(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
