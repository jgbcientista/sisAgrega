package br.com.sysagrega.model;

import java.io.Serializable;

public interface ICaracteristicaProjeto extends Serializable{

	void setUnidIcmbio(Boolean unidIcmbio);

	Boolean getUnidIcmbio();

	void setMapaQuilombola(Boolean mapaQuilombola);

	Boolean getMapaQuilombola();

	void setMapaTerraIndigina(Boolean mapaTerraIndigina);

	Boolean getMapaTerraIndigina();

	void setMapaAssentamento(Boolean mapaAssentamento);

	Boolean getMapaAssentamento();

	void setMapaAreaEspecial(Boolean mapaAreaEspecial);

	Boolean getMapaAreaEspecial();

	void setRelDiapSeia(Boolean relDiapSeia);

	Boolean getRelDiapSeia();

	void setRelObraConstruida(Boolean relObraConstruida);

	void setRelOutros(Boolean relOutros);

	Boolean getRelOutros();

	void setRelVistoriaNaoRealizada(Boolean relVistoriaNaoRealizada);

	Boolean getRelVistoriaNaoRealizada();

	Boolean getRelObraConstruida();

	void setRelTamanhoLinhaDiferente(Boolean relTamanhoLinhaDiferente);

	Boolean getRelTamanhoLinhaDiferente();

	void setRelCasaEmAPP(Boolean relCasaEmAPP);

	Boolean getRelCasaEmAPP();

	void setRelRo(Boolean relRo);

	Boolean getRelRo();

	void setRelEpi(Boolean relEpi);

	Boolean getRelEpi();

	void setRelEv(Boolean relEv);

	Boolean getRelEv();

	void setProjeto(IProjeto projeto);

	IProjeto getProjeto();

	void setUndConservMunicipal(Boolean undConservMunicipal);

	Boolean getUndConservMunicipal();

	void setUndConservEstadual(Boolean undConservEstadual);

	Boolean getUndConservEstadual();

	void setUndConservFederal(Boolean undConservFederal);

	Boolean getUndConservFederal();

	void setMapaLeiMataAtlantica(Boolean mapaLeiMataAtlantica);

	Boolean getMapaLeiMataAtlantica();

	void setMapaVegetacao(Boolean mapaVegetacao);

	Boolean getMapaVegetacao();

	void setMapaHidrografia(Boolean mapaHidrografia);

	Boolean getMapaHidrografia();

	void setMapaLocalizacao(Boolean mapaLocalizacao);

	Boolean getMapaLocalizacao();

	void setId(Long id);

	Long getId();

	Boolean getUnidConservacao();

	void setUnidConservacao(Boolean unidConservacao);

	void setRiuc(Boolean riuc);

	Boolean getRiuc();

	Boolean getAreaLago();

	void setAreaLago(Boolean areaLago);

	Boolean getFaixaMarginal();

	void setFaixaMarginal(Boolean faixaMarginal);

	Boolean getTopoMorro();

	void setTopoMorro(Boolean topoMorro);

	Boolean getParticular();

	void setParticular(Boolean particular);

	Boolean getRca();

	void setRca(Boolean rca);

	Boolean getSeia();

	void setSeia(Boolean seia);

	
		
}