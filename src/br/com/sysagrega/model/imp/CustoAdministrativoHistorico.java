package br.com.sysagrega.model.imp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.ICustoAdministrativoHistorico;

@Entity
@Table(name = "TB_CUSTO_ADMINISTRATIVO_HISTORICO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_cah", sequenceName = "agrega.tb_seq_cah", allocationSize = 1)
public class CustoAdministrativoHistorico extends CustoBaseHistorico implements ICustoAdministrativoHistorico{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_cah")
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
