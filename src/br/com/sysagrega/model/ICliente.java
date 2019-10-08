package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Usuario;

public interface ICliente extends Serializable{

	Long getId();

	void setId(Long id);
	
	String getTipoCliente();

	void setTipoCliente(String tipoCliente);

	String getTipoIndicacao();

	void setTipoIndicacao(String tipoIndicacao);

	String getPorteEmpresa();

	void setPorteEmpresa(String porteEmpresa);

	String getSeguimento();

	void setSeguimento(String seguimento);

	String getNome();

	void setNome(String nome);

	String getSigla();

	void setSigla(String sigla);

	String getRg();

	void setRg(String rg);

	void setCnpjCPF(String cnpjCPF);

	String getCnpjCPF();

	Long getInscricaoEstadual();

	void setInscricaoEstadual(Long inscricaoEstadual);

	Long getInscricaoMunicipal();

	void setInscricaoMunicipal(Long inscricaoMunicipal);

	String getCnae();

	void setCnae(String cnae);

	String getStatus();

	void setStatus(String status);

	IEndereco getEndereco();

	void setEndereco(IEndereco endereco);

	String getContato();

	void setContato(String contato);

	String getSetor();

	void setSetor(String setor);

	String getFuncao();

	void setFuncao(String funcao);

	String getTelefone();

	void setTelefone(String telefone);

	String getCelular();

	void setCelular(String celular);

	String getEmail();

	void setEmail(String email);

	Date getDataCadastro();

	void setDataCadastro(Date dataCadastro);

	String getObservacoes();

	void setObservacoes(String observacoes);

	boolean isNovo();

	boolean isExistente();

	String getPendencia();

	void setPendencia(String pendencia);

	Usuario getUsuarioAtualizacao();

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);

	Date getDataAtualizacao();

	void setDataAtualizacao(Date dataAtualizacao);

	boolean isAtivo();

	void setAtivo(boolean ativo);

}