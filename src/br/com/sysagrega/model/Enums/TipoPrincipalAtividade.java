package br.com.sysagrega.model.Enums;

public enum TipoPrincipalAtividade {
	
	VENDA("Venda"),
	SERVICOS("Serviços"),
	VENDA_SERVICOS("Vendas e Serviços"),
	DELIBERAR("Deliberar"),
	FISCALIZAR("Fiscalizar"),
	REGULAR("Regular"),
	ORIENTAR("Orientar"),
	PROTEGER("Proteger"),
	POLICIAR("Policiar"),
	LICENCIAR("Licenciar"),
	AUTORIZAR("Autorizar"),
	REGISTRAR("Registrar");
	
	private String descricao;

	private TipoPrincipalAtividade(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
