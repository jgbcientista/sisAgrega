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

import br.com.sysagrega.model.ICaracteristicaProjeto;
import br.com.sysagrega.model.IProjeto;

@Entity
@Table(name = "TB_CARACTERISTICAS_PROJETO", schema="agrega")
@SequenceGenerator(name = "agrega.tb_seq_cara", sequenceName = "agrega.tb_seq_cara", allocationSize = 1)
public class CaracteristicaProjeto implements ICaracteristicaProjeto {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator ="agrega.tb_seq_cara")
	private Long id;
	
	private Boolean RelEv;
	
	private Boolean RelEpi;
	
	private Boolean RelRo;
	
	private Boolean RelCasaEmAPP;
	
	private Boolean RelTamanhoLinhaDiferente;
	
	private Boolean RelObraConstruida;
	
	private Boolean RelVistoriaNaoRealizada;
	
	private Boolean RelOutros;
	
	private Boolean RelDiapSeia;
	
	private Boolean mapaLocalizacao;
	
	private Boolean mapaHidrografia;
	
	private Boolean mapaVegetacao;
	
	private Boolean mapaLeiMataAtlantica;
	
	private Boolean mapaAreaEspecial;
	
	private Boolean mapaAssentamento;
	
	private Boolean mapaTerraIndigina;
	
	private Boolean mapaQuilombola;
	
	private Boolean UnidIcmbio;
	
	private Boolean UnidConservacao;
	
	private Boolean undConservFederal;
	
	private Boolean undConservEstadual;
	
	private Boolean undConservMunicipal;
	
	private Boolean riuc;
	
	private Boolean seia;
	
	private Boolean areaLago;
	
	private Boolean faixaMarginal;
	
	private Boolean topoMorro;
	
	private Boolean particular;
	
	private Boolean rca;
	
	@OneToOne(targetEntity = Projeto.class, cascade = CascadeType.REFRESH)
	private IProjeto projeto;
	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public Boolean getMapaLocalizacao() {
		return mapaLocalizacao;
	}
	
	@Override
	public void setMapaLocalizacao(Boolean mapaLocalizacao) {
		this.mapaLocalizacao = mapaLocalizacao;
	}
	
	@Override
	public Boolean getMapaHidrografia() {
		return mapaHidrografia;
	}
	
	@Override
	public void setMapaHidrografia(Boolean mapaHidrografia) {
		this.mapaHidrografia = mapaHidrografia;
	}
	
	@Override
	public Boolean getMapaVegetacao() {
		return mapaVegetacao;
	}
	
	@Override
	public void setMapaVegetacao(Boolean mapaVegetacao) {
		this.mapaVegetacao = mapaVegetacao;
	}
	
	@Override
	public Boolean getMapaLeiMataAtlantica() {
		return mapaLeiMataAtlantica;
	}
	
	@Override
	public void setMapaLeiMataAtlantica(Boolean mapaLeiMataAtlantica) {
		this.mapaLeiMataAtlantica = mapaLeiMataAtlantica;
	}
	
	@Override
	public Boolean getUndConservFederal() {
		return undConservFederal;
	}
	
	@Override
	public void setUndConservFederal(Boolean undConservFederal) {
		this.undConservFederal = undConservFederal;
	}
	
	@Override
	public Boolean getUndConservEstadual() {
		return undConservEstadual;
	}
	
	@Override
	public void setUndConservEstadual(Boolean undConservEstadual) {
		this.undConservEstadual = undConservEstadual;
	}
	
	@Override
	public Boolean getUndConservMunicipal() {
		return undConservMunicipal;
	}
	
	@Override
	public void setUndConservMunicipal(Boolean undConservMunicipal) {
		this.undConservMunicipal = undConservMunicipal;
	}
	
	
	@Override
	public IProjeto getProjeto() {
		return projeto;
	}
	@Override
	public void setProjeto(IProjeto projeto) {
		this.projeto = projeto;
	}
	@Override
	public Boolean getRelEv() {
		return RelEv;
	}
	@Override
	public void setRelEv(Boolean relEv) {
		RelEv = relEv;
	}
	@Override
	public Boolean getRelEpi() {
		return RelEpi;
	}
	@Override
	public void setRelEpi(Boolean relEpi) {
		RelEpi = relEpi;
	}
	@Override
	public Boolean getRelRo() {
		return RelRo;
	}
	@Override
	public void setRelRo(Boolean relRo) {
		RelRo = relRo;
	}
	@Override
	public Boolean getRelCasaEmAPP() {
		return RelCasaEmAPP;
	}
	@Override
	public void setRelCasaEmAPP(Boolean relCasaEmAPP) {
		RelCasaEmAPP = relCasaEmAPP;
	}
	@Override
	public Boolean getRelTamanhoLinhaDiferente() {
		return RelTamanhoLinhaDiferente;
	}
	@Override
	public void setRelTamanhoLinhaDiferente(Boolean relTamanhoLinhaDiferente) {
		RelTamanhoLinhaDiferente = relTamanhoLinhaDiferente;
	}
	@Override
	public Boolean getRelObraConstruida() {
		return RelObraConstruida;
	}
	@Override
	public void setRelObraConstruida(Boolean relObraConstruida) {
		RelObraConstruida = relObraConstruida;
	}
	@Override
	public Boolean getRelVistoriaNaoRealizada() {
		return RelVistoriaNaoRealizada;
	}
	@Override
	public void setRelVistoriaNaoRealizada(Boolean relVistoriaNaoRealizada) {
		RelVistoriaNaoRealizada = relVistoriaNaoRealizada;
	}
	@Override
	public Boolean getRelOutros() {
		return RelOutros;
	}
	@Override
	public void setRelOutros(Boolean relOutros) {
		RelOutros = relOutros;
	}
	@Override
	public Boolean getRelDiapSeia() {
		return RelDiapSeia;
	}
	@Override
	public void setRelDiapSeia(Boolean relDiapSeia) {
		RelDiapSeia = relDiapSeia;
	}
	@Override
	public Boolean getMapaAreaEspecial() {
		return mapaAreaEspecial;
	}
	@Override
	public void setMapaAreaEspecial(Boolean mapaAreaEspecial) {
		this.mapaAreaEspecial = mapaAreaEspecial;
	}
	@Override
	public Boolean getMapaAssentamento() {
		return mapaAssentamento;
	}
	@Override
	public void setMapaAssentamento(Boolean mapaAssentamento) {
		this.mapaAssentamento = mapaAssentamento;
	}
	@Override
	public Boolean getMapaTerraIndigina() {
		return mapaTerraIndigina;
	}
	@Override
	public void setMapaTerraIndigina(Boolean mapaTerraIndigina) {
		this.mapaTerraIndigina = mapaTerraIndigina;
	}
	@Override
	public Boolean getMapaQuilombola() {
		return mapaQuilombola;
	}
	@Override
	public void setMapaQuilombola(Boolean mapaQuilombola) {
		this.mapaQuilombola = mapaQuilombola;
	}
	
	@Override
	public Boolean getUnidIcmbio() {
		return UnidIcmbio;
	}
	@Override
	public void setUnidIcmbio(Boolean unidIcmbio) {
		UnidIcmbio = unidIcmbio;
	}
	
	@Override
	public Boolean getUnidConservacao() {
		return UnidConservacao;
	}
	@Override
	public void setUnidConservacao(Boolean unidConservacao) {
		UnidConservacao = unidConservacao;
	}
	@Override
	public Boolean getRiuc() {
		return riuc;
	}
	@Override
	public void setRiuc(Boolean riuc) {
		this.riuc = riuc;
	}
	
	@Override
	public Boolean getAreaLago() {
		return areaLago;
	}
	@Override
	public void setAreaLago(Boolean areaLago) {
		this.areaLago = areaLago;
	}
	@Override
	public Boolean getFaixaMarginal() {
		return faixaMarginal;
	}
	@Override
	public void setFaixaMarginal(Boolean faixaMarginal) {
		this.faixaMarginal = faixaMarginal;
	}
	@Override
	public Boolean getTopoMorro() {
		return topoMorro;
	}
	@Override
	public void setTopoMorro(Boolean topoMorro) {
		this.topoMorro = topoMorro;
	}
	@Override
	public Boolean getParticular() {
		return particular;
	}
	@Override
	public void setParticular(Boolean particular) {
		this.particular = particular;
	}
	@Override
	public Boolean getRca() {
		return rca;
	}
	@Override
	public void setRca(Boolean rca) {
		this.rca = rca;
	}
	@Override
	public Boolean getSeia() {
		return seia;
	}
	@Override
	public void setSeia(Boolean seia) {
		this.seia = seia;
	}
	
	
	
	
}
