package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.Date;

import br.com.sysagrega.model.imp.Contrato;
import br.com.sysagrega.model.imp.Usuario;

public interface IAditivo extends Serializable {

	boolean isNovo();

	boolean isExistente();

	Long getId();

	void setId(Long id);

	void setDataAditivo(Date dataAditivo);

	Date getDataAditivo();

	void setContrato(Contrato contrato);

	Contrato getContrato();

	void setAtivo(boolean ativo);

	boolean isAtivo();

	void setUsuarioAtualizacao(Usuario usuarioAtualizacao);

	Usuario getUsuarioAtualizacao();

	Double getValor();

	void setValor(Double valor);


}