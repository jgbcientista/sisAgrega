package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Usuario;

public interface IFornecedor extends Serializable{
	
	void setId(Long id);
	
	Long getId();

	void setNomeFantasia(String nomeFantasia);
	
	String getNomeFantasia();
	
	void setRazaoSocial(String razaoSocial);
	
	String getRazaoSocial();
	
	void setRamoAtividade(String ramoAtividade);
	
	String getRamoAtividade();
	
	void setPessoaContato(String pessoaContato);
	
	String getPessoaContato();
	
	void setEndereco(IEndereco endereco);

	IEndereco getEndereco();
	
	void setEmail(String email);

	String getEmail();

	void setCelular(String celular);

	String getCelular();

	void setTelefone(String telefone);

	String getTelefone();

	void setDataCadastro(Date dataCadastro);

	Date getDataCadastro();

	void setIscricaoEstadual(String iscricaoEstadual);

	String getIscricaoEstadual();
			
	void setIscricaoMunicipal(String iscricaoMunicipal);

	String getIscricaoMunicipal();

	void setCnpjCPF(String cnpjCPF);

	String getCnpjCPF();
	
	void setDadosBancarios(IDadosBancarios dadosBancarios);

	IDadosBancarios getDadosBancarios();
	
	boolean isExistente();

	boolean isNovo();

	String getPrincipalAtividade();

	void setPrincipalAtividade(String principalAtividade);

	String getTipoFornecedor();

	void setTipoFornecedor(String tipoFornecedor);

	String getObservacao();

	void setObservacao(String observacao);

	Date getDataAtualizacao();

	void setDataAtualizacao(Date dataAtualizacao);

	Usuario getUsuarioAtualizacao();

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);

	boolean isAtivo();

	void setAtivo(boolean ativo);

}