package br.com.sysagrega.model.Enums;

import java.util.ArrayList;
import java.util.List;

public enum ETipoSubProjeto {
	
	// NEGOCIO DE QUALIDADE
	DQ(1L, "DQ", "Negócio de Qualidade", "Diagnósticos da Qualidade"),
	AQ(2L, "AQ", "Negócio de Qualidade", "Auditória da Qualidade"),
	MP(3L, "MP", "Negócio de Qualidade", "Mapeamento dos Processos"),
	TID(4L,"TID","Negócio de Qualidade", "Técnica de Identificação de Defeitos"),
	PE(5L, "PE", "Negócio de Qualidade", " Planejamento Estratégico"),
	ISQ(6L,"ISQ","Negócio de Qualidade", "Implantação de Sistemas de Qualidade"),
	// MEIO AMBIENTE
	DA(8L,  "DA", "Meio Ambiente", "Diagnósticos Ambiental"),
	AA(9L,  "AA", "Meio Ambiente", "Auditória da Ambiental"),
	ISG(10L,"ISG","Meio Ambiente", "Implantação de SGA "),
	GR(11L, "GR", "Meio Ambiente", "Gestão de Resíduos"),
	EA(12L, "EA", "Meio Ambiente", "Estudo Ambiental"),
	IF(13L, "IF", "Meio Ambiente", "Inventário Florestal"),
	PAR(14L,"PAR","Meio Ambiente", "Plano de Afugentamento e Resgate"),
	PEA(15L,"PEA","Meio Ambiente", "Programas de Educação Ambiental"),
	FA(20L,"FA","Meio Ambiente", "Fiscalização Ambiental"),
	ARFS(21L,"ARFS","Meio Ambiente", "ARFS"),
	
	// SAUDE E SEGURANCA
	ISGS(16L,"ISGS","Saúde e Segurança", "Implantação de Sistema de Gestão de SSO"),
	DSS(17L, "DSS", "Saúde e Segurança", "Diagnósticos de SST"),
	AS(18L,  "AS",  "Saúde e Segurança", "Auditoria de SST"),
	VAERR(19L,"VAERR","Saúde e Segurança", "Verificação e Adequação/Enquadramento aos Requisitos Regulamentares");
	
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
