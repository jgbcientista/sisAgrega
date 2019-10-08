package br.com.sysagrega.model;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Usuario;

public interface IUsuario extends Serializable{

	Long getId();

	void setId(Long id);
	
	String getLogin();

	void setLogin(String login);
	
	String getSenha();

	void setSenha(String senha);
	
	String getNome();

	void setNome(String nome);
	
	String getSetor();

	void setSetor(String setor);

	String getFuncao();

	void setFuncao(String funcao);

	String getEmail();

	void setEmail(String email);
	
	boolean isNovo();
	boolean isExistente();

	void setPerfil(IPerfil perfil);

	IPerfil getPerfil();

	String getTelefone();

	void setTelefone(String telefone);

	void setDataInclusao(Date dataInclusao);

	Date getDataInclusao();

	boolean getAtivo();

	void setAtivo(boolean ativo);

	Usuario getUsuarioAtualizacao();

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);

	Date getDataAtualizacao();

	void setDataAtualizacao(Date dataAtualizacao);

}