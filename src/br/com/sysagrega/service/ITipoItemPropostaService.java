package br.com.sysagrega.service;

import java.io.Serializable;
 

import java.util.List;

import br.com.sysagrega.model.ITipoItemProposta;
import br.com.sysagrega.model.imp.TipoItemProposta;


public interface ITipoItemPropostaService extends Serializable{

	public void remover(ITipoItemProposta tipo);
	
	TipoItemProposta salvar(TipoItemProposta tipo);
	
	public ITipoItemProposta getById(Long id);
	
	public List<TipoItemProposta> getAll() ;
	
	public TipoItemProposta atualizar(TipoItemProposta tipo);
	
	public  List<TipoItemProposta> getByPropostaId(Long idProposta, Long idTipoProposta) ;

}