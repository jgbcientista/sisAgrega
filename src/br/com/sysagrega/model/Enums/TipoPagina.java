package br.com.sysagrega.model.Enums;

public enum TipoPagina {
	
	//A��es telas
	NOVO("Novo"),
	CONSULTA("Consultar"),
	VISUALIZAR("Visualizar"),
	EDITAR("Editar"),
	
	
	//A��es telas de profissional
	NOVO_PROF("Novo"),
	CONSULTA_PROF("Consultar"),
	VISUALIZAR_PROF("Visualizar"),
	EDIT_PROFI("Editar"),
	
	//A��es telas de clientes
	NOVO_CLIENTE("Novo Cliente"),
	CONSULTA_CLIENTE("Consultar Cliente"),
	VISUALIZAR_CLIENTE("Visualizar Cliente"),
	EDIT_CLIENTE("Editar Cliente"),
	
	//A��es telas de fornecedor
	NOVO_FORNEC("Novo Fornecedor"),
	CONSULTA_FORNEC("Consultar Fornecedor"),
	VISUALIZAR_FORNEC("Visualizar fornecedor"),
	EDIT_FORNEC("Editar Fornecedor"),
	
	//A��es telas de proposta
	NOVA_PROPOSTA("Nova Proposta"),
	NOVA_PRECIFICACAO ("Nova Precificacao"),
	CONSULTA_PROPOSTA("Consultar Proposta"),
	VISUALIZAR_PROPOSTA("Visualizar Proposta"),
	EDIT_PROPOSTA("Editar Proposta"),
	HISTORICO_PROPOSTA("Hitorico Proposta"),
	
	//A��es telas de contrato
	NOVO_CONTRATO("Novo Contrato"),
	CONSULTA_CONTRATO("Consultar Contrato"),
	VISUALIZAR_CONTRATO("Visualizar Contrato"),
	EDIT_CONTRATO("Editar Contrato"),
	
	
	//A��es telas de Servico
	NOVO_SERVICO("Novo Servico"),
	CONSULTA_SERVICO("Consultar Servico"),
	VISUALIZAR_SERVICO("Visualizar Servico"),
	EDIT_SERVICO("Editar Servico"),
	
	//A��es telas de recurso
	NOVO_RECURSO("Novo Recurso"),
	CONSULTA_RECURSO("Consultar Recurso"),
	VISUALIZAR_RECURSO("Visualizar Recurso"),
	EDIT_RECURSO("Editar Recurso"),
	
	//A��es telas de Usuario
	NOVO_USUARIO("Novo Usu�rio"),
	CONSULTA_USUARIO("Consultar Usu�rio"),
	VISUALIZAR_USUARIO("Visualizar Usu�rio"),
	EDIT_USUARIO("Editar Usu�rio"),
	
	//A��es telas de perfil
	NOVO_PERFIL("Novo Perfil"),
	CONSULTA_PERFIL("Consultar Perfil"),
	VISUALIZAR_PERFIL("Visualizar Perfil"),
	EDIT_PERFIL("Editar Perfil"),
	
	//A��es telas de Ordem de Servico
	NOVO_ORDEM("Novo"),
	CONSULTA_ORDEM("Consultar"),
	VISUALIZAR_ORDEM("Visualizar"),
	EDIT_ORDEM("Editar"),
	
	
	//A��es telas de Projeto
	NOVO_PROJETO("Novo"),
	CONSULTA_PROJETO("Consultar"),
	VISUALIZAR_PROJETO("Visualizar"),
	EDIT_PROJETO("Editar");
	
	
	private String descricao;

	private TipoPagina(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	




}
