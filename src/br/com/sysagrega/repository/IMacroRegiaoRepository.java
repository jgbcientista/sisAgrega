package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.MacroRegiao;

public interface IMacroRegiaoRepository extends Serializable{

	List<MacroRegiao> getAllMacroRegiao();

	MacroRegiao getMacroRegiaoById(Long id);

	List<MacroRegiao> getMacroRegiaoByEstadoId(Long id);

	List<MacroRegiao> getMacroRegiaoByCidade(Long id);



	
}