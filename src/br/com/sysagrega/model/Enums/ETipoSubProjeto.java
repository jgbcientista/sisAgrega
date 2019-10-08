package br.com.sysagrega.model.Enums;

import java.util.ArrayList;
import java.util.List;

public enum ETipoSubProjeto {
	
	// NEGOCIO DE QUALIDADE
	DQ(1L, "DQ", "Neg�cio de Qualidade", "Diagn�sticos da Qualidade"),
	AQ(2L, "AQ", "Neg�cio de Qualidade", "Audit�ria da Qualidade"),
	MP(3L, "MP", "Neg�cio de Qualidade", "Mapeamento dos Processos"),
	TID(4L,"TID","Neg�cio de Qualidade", "T�cnica de Identifica��o de Defeitos"),
	PE(5L, "PE", "Neg�cio de Qualidade", " Planejamento Estrat�gico"),
	ISQ(6L,"ISQ","Neg�cio de Qualidade", "Implanta��o de Sistemas de Qualidade"),
	// MEIO AMBIENTE
	DA(8L,  "DA", "Meio Ambiente", "Diagn�sticos Ambiental"),
	AA(9L,  "AA", "Meio Ambiente", "Audit�ria da Ambiental"),
	ISG(10L,"ISG","Meio Ambiente", "Implanta��o de SGA "),
	GR(11L, "GR", "Meio Ambiente", "Gest�o de Res�duos"),
	EA(12L, "EA", "Meio Ambiente", "Estudo Ambiental"),
	IF(13L, "IF", "Meio Ambiente", "Invent�rio Florestal"),
	PAR(14L,"PAR","Meio Ambiente", "Plano de Afugentamento e Resgate"),
	PEA(15L,"PEA","Meio Ambiente", "Programas de Educa��o Ambiental"),
	FA(20L,"FA","Meio Ambiente", "Fiscaliza��o Ambiental"),
	ARFS(21L,"ARFS","Meio Ambiente", "ARFS"),
	
	// SAUDE E SEGURANCA
	ISGS(16L,"ISGS","Sa�de e Seguran�a", "Implanta��o de Sistema de Gest�o de SSO"),
	DSS(17L, "DSS", "Sa�de e Seguran�a", "Diagn�sticos de SST"),
	AS(18L,  "AS",  "Sa�de e Seguran�a", "Auditoria de SST"),
	VAERR(19L,"VAERR","Sa�de e Seguran�a", "Verifica��o e Adequa��o/Enquadramento aos Requisitos Regulamentares");
	
	private Long flag;
	
	private String sigla;
	
	private String tipoProjeto;

	private String descricao;

	 
	private ETipoSubProjeto(Long flag, String sigla, String tipoProjeto, String descricao) {
		this.flag = flag;
		this.sigla = sigla;
		this.tipoProjeto = tipoProjeto;
		this.descricao = descricao;
	}
	
	public Long getFlag(){
		return flag;
	}

	public String getSigla() {
		return sigla;
	}
	
	public String getTipoProjeto() {

		return tipoProjeto;
	}

	public String getDescricao() {

		return descricao;
	}

	public static List<ETipoSubProjeto> obterPorTipoProjeto (String tipoProjeto){
		List<ETipoSubProjeto> listSubProjeto = new ArrayList<ETipoSubProjeto>();
		
		for (ETipoSubProjeto tipos : values()) {
			if(tipoProjeto.equalsIgnoreCase(tipos.getTipoProjeto())){
				listSubProjeto.add(tipos);
			}else{
				listSubProjeto.add(tipos);	
			}
		}
		
		return listSubProjeto;
		
	}
	
	
	public static ETipoSubProjeto obterPorTipo(Long tipoProjeto) {

		ETipoSubProjeto eTipoSubProjeto = null;

		for (ETipoSubProjeto tipo : values()) {

			if (tipo.getTipoProjeto().equals(tipoProjeto)) {

				eTipoSubProjeto = tipo;
				break;
			}
		}

		return eTipoSubProjeto;
	}

}
