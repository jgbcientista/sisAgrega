package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.PlanilhaTecEquipamento;

public interface IPlanilhaTecEquipamentoRepository extends Serializable{

	List<PlanilhaTecEquipamento> getAll();

	PlanilhaTecEquipamento getById(Long id);

	PlanilhaTecEquipamento salvar(PlanilhaTecEquipamento item);

	List<PlanilhaTecEquipamento> getByPropostaId(Long idProposta);

	void remover(PlanilhaTecEquipamento item);

	

	

	
}