package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IMensagem;
import br.com.sysagrega.model.imp.Mensagem;


public interface IMensagemRepository extends Serializable{

	List<Mensagem> getAll();

	Mensagem getById(Long id);
	
	void remover(IMensagem item);
	
	List<Mensagem> getByFilter(Mensagem filtro);

	IMensagem salvar(IMensagem item);

	List<Mensagem> obterByProjetoByProfissional(Long idProjeto, Long idProfissional, Long idTipo);

}