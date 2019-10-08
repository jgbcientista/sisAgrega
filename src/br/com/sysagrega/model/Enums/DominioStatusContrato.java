package br.com.sysagrega.model.Enums;

public enum DominioStatusContrato {
	
	VIGENTE(1L, "Vigente"),
	DISTRATO(2L, "Distrato" ),
	CANCELADO(3L, "Cancelado" ),
	FINALIZADO(4L, "Finalizado" )
	;
	
	private Long flag;

	private String descricao;

	 
	private DominioStatusContrato(Long flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public Long getFlag() {
		return flag;
	}

	public String getDescricao() {

		return descricao;
	}

	 
	public static DominioStatusContrato obterPorFlag(Long flag) {

		DominioStatusContrato statusContrato = null;

		for (DominioStatusContrato tipo : values()) {

			if (tipo.getFlag().equals(flag)) {

				statusContrato = tipo;
				break;
			}
		}

		return statusContrato;
	}

}
