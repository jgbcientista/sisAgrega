package br.com.sysagrega.model.Enums;

public enum ETipoProposta {
	
	FINANCEIRA(1L,"Financeira"),
	TECNICA(2L,"T�cnica"),
	TECN_FINANCE(3L,"T�cnica/Financeira");
	
	
	private String descricao;
	private Long flag;

	 
		private ETipoProposta(Long flag, String descricao) {
			this.flag = flag;
			this.descricao = descricao;
		}
		
		private ETipoProposta(String descricao) {
			this.descricao = descricao;
		}


		public Long getFlag() {
			return flag;
		}

		public String getDescricao() {

			return descricao;
		}

		 
		public static ETipoProposta obterPorFlag(Long flag) {

			ETipoProposta eTipoDespesa = null;

			for (ETipoProposta tipo : values()) {

				if (tipo.getFlag().equals(flag)) {

					eTipoDespesa = tipo;
					break;
				}
			}

			return eTipoDespesa;
		}




}
