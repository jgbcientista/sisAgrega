package br.com.sysagrega.model.imp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.ICustoExecucaoHistorico;

@Entity
@Table(name = "TB_CUSTO_EXECUCAO_HISTORICO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_ceh", sequenceName = "agrega.tb_seq_ceh", allocationSize = 1)
public class CustoExecucaoHistorico extends CustoBaseHistorico implements ICustoExecucaoHistorico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_ceh")
	private Long id;

	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
		
	}
}
