package br.com.sysagrega.model.imp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sysagrega.model.IPlanilhaTecEquipamento;
import br.com.sysagrega.model.IProposta;

@Entity
@Table(name = "TB_PLAN_TEC_EQUIPAMENTO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_plan_eqpm", sequenceName = "agrega.tb_seq_plan_eqpm", allocationSize = 1)
public class PlanilhaTecEquipamento implements IPlanilhaTecEquipamento {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_plan_eqpm")
	private Long id;
	
	private String descricao;
	private Integer equipeAdm;
	private Integer equipeCampo;
	private Boolean item;
	
	@OneToOne(targetEntity = Proposta.class, cascade = CascadeType.REFRESH)
	private IProposta proposta;
		
	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public IProposta getProposta() {
		return proposta;
	}

	@Override
	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	@Override
	public Integer getEquipeAdm() {
		return equipeAdm;
	}

	@Override
	public void setEquipeAdm(Integer equipeAdm) {
		this.equipeAdm = equipeAdm;
	}

	@Override
	public Integer getEquipeCampo() {
		return equipeCampo;
	}

	@Override
	public void setEquipeCampo(Integer equipeCampo) {
		this.equipeCampo = equipeCampo;
	}

	@Override
	public Boolean getItem() {
		return item;
	}

	@Override
	public void setItem(Boolean item) {
		this.item = item;
	}

		

}
