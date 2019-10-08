package br.com.sysagrega.model.Enums;

public enum EQuantidadeItens {
	
	ITEM5(5L,"5"),
	ITEM6(6L,"6"),
	ITEM7(7L,"7"),
	ITEM8(8L,"8"),
	ITEM9(9L,"9"),
	ITEM10(10L,"10"),
	ITEM11(11L,"11"),
	ITEM12(12L,"12");

	
	private String descricao;
	private Long flag;

	 
		private EQuantidadeItens(Long flag, String descricao) {
			this.flag = flag;
			this.descricao = descricao;
		}
		
		private EQuantidadeItens(String descricao) {
			this.descricao = descricao;
		}


		public Long getFlag() {
			return flag;
		}

		public String getDescricao() {

			return descricao;
		}

		 
		public static EQuantidadeItens obterPorFlag(Long flag) {

			EQuantidadeItens eTipoDespesa = null;

			for (EQuantidadeItens tipo : values()) {

				if (tipo.getFlag().equals(flag)) {

					eTipoDespesa = tipo;
					break;
				}
			}

			return eTipoDespesa;
		}




}
