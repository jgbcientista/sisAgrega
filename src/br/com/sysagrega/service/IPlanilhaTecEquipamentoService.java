package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.PlanilhaTecEquipamento;


public interface IPlanilhaTecEquipamentoService extends Serializable{

	void remover(PlanilhaTecEquipamento item);

	PlanilhaTecEquipamento atualizar(PlanilhaTecEquipamento item);

	PlanilhaTecEquipamento getById(Long id);

	List<PlanilhaTecEquipamento> getByPropostaId(Long idProposta);

	List<PlanilhaTecEquipamento> getAll();

	PlanilhaTecEquipamento salvar(PlanilhaTecEquipamento item);

	

	


}