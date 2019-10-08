package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Usuario;

public interface IContato extends Serializable{

	Long getId();

	void setId(Long id);

	String getNomeContato();

	void setNomeContato(String nomeContato);

	String getTel2Contato();

	void setTel2Contato(String tel2Contato);

	String getSetorContato();

	void setSetorContato(String setorContato);

	String getEmailContato();

	void setEmailContato(String emailContato);

	Date getDataCadastro();

	void setDataCadastro(Date dataCadastro);

	Date getDataAtualizacao();

	void setDataAtualizacao(Date dataAtualizacao);

	Usuario getUsuarioAtualizacao();

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);

	boolean isAtivo();

	void setAtivo(boolean ativo);

	long getSerialversionuid();

	void setTel1Contato(String tel1Contato);

	String getTel1Contato();

	Long getIdCliente();

	void setIdCliente(Long idCliente);

	Long getIdFornecedor();

	void setIdFornecedor(Long idFornecedor);



	

}