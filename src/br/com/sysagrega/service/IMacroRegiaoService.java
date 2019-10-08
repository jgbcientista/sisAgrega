package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.MacroRegiao;

public interface IMacroRegiaoService extends Serializable{

	List<MacroRegiao> getAllMacroRegiao();

	MacroRegiao getMacroRegiaoById(Long id);

	List<MacroRegiao> getMacroRegiaoByEstadoId(Long id);
	
	public List<MacroRegiao> getMacroRegiaoByCidade(Long id);

	
}