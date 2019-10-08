package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IMensagem;
import br.com.sysagrega.model.imp.Mensagem;

public interface IMensagemService extends Serializable{

	public void remover(IMensagem item);
	
	void salvar(IMensagem item);
	
	public Mensagem getById(Long id);
	
	public List<Mensagem> getAll() ;
	
	public IMensagem atualizar(IMensagem item);

	List<Mensagem> getByFilter(Mensagem filtro);
	
	public List<Mensagem> obterByProjetoByProfissional(Long idProjeto, Long idProfissional, Long idTipo);


}