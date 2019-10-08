package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.IPlanejamentos;
import br.com.sysagrega.model.imp.Planejamentos;
import br.com.sysagrega.model.imp.Usuario;
 
public interface IPlanejamentosService extends Serializable{

	void remover(IPlanejamentos item);

	Planejamentos getById(Long id);

	List<Planejamentos> getAll();

	IPlanejamentos salvar(IPlanejamentos item);
	
	List<Planejamentos> getByFiltros(Planejamentos filtro);

	IPlanejamentos criarNovoPlanejamento(Usuario usuarioAtualizacao);

	IPlanejamentos criarRevisaoPlanejamento(Usuario usuarioAtualizacao, Long id);


	
	
}