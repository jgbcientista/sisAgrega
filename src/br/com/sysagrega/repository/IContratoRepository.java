package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.sysagrega.model.IContrato;
import br.com.sysagrega.model.imp.Contrato;

public interface IContratoRepository extends Serializable {

	Contrato getContratoById(Long id);

	IContrato getPropostaByNumero(String nrContrato);

	void remover(Contrato contrato);

	List<Contrato> getAllContratos();

	IContrato saveOrMerge(IContrato contrato);

	List<Contrato> getContratoByFilter(String nrContrato, String filtroCliente, String filtroStatus,
			Date filtroDataInicial, Date filtroDataFinal);

}