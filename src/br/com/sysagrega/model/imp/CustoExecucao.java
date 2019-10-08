package br.com.sysagrega.model.imp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.ICustoExecucao;

@Entity
@Table(name = "TB_CUSTO_EXECUCAO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_cust_exe", sequenceName = "agrega.tb_seq_cust_exe", allocationSize = 1)
public class CustoExecucao extends CustoBase implements ICustoExecucao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_cust_exe")
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
