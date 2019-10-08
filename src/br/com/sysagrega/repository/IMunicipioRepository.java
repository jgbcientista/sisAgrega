package br.com.sysagrega.repository;

import java.io.Serializable;
import java.util.List;

import br.com.sysagrega.model.imp.MicroRegiao;
import br.com.sysagrega.model.imp.Municipio;


public interface IMunicipioRepository extends Serializable{

	List<Municipio> getAllMunicipio();

	Municipio getMunicipioById(Long id);

	List<Municipio> getMunicipioByMicroRegiaoId(Long id);

	List<Municipio> getMunicipioByMacroRegiaoId(Long id);

	List<Municipio> getMunicipioByEstado(Long id);





	
}