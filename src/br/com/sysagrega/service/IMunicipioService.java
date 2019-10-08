package br.com.sysagrega.service;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.Municipio;

public interface IMunicipioService extends Serializable{

	List<Municipio> getMunicipioByMacroRegiaoId(Long id);

	List<Municipio> getMunicipioByMicroRegiaoId(Long id);

	Municipio getMunicipioById(Long id);

	List<Municipio> getAllMunicipio();
	
	public List<Municipio> getMunicipioByEstado(Long id);



	
}