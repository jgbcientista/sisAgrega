package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.Cidade;
import br.com.sysagrega.repository.imp.CidadeRepository;
import br.com.sysagrega.service.ICidadeService;

public class CidadeService implements ICidadeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CidadeRepository cidadeRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.sysagrega.service.imp.ICidadeService#getAllCidades()
	 */
	@Override
	public List<Cidade> getAllCidades() {

		return this.cidadeRepository.getAllCidades();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.ICidadeService#getEstadoById(java.lang.Long)
	 */
	@Override
	public Cidade getCidadeById(Long id) {

		return this.cidadeRepository.getCidadeById(id);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.ICidadeService#getCidadesByEstado(java.lang.
	 * Long)
	 */
	@Override
	public List<Cidade> getCidadesByEstadoId(Long id) {

		return this.cidadeRepository.getCidadesByEstadoId(id);

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sysagrega.service.imp.ICidadeService#getCidadesByEstado(java.lang.
	 * Long)
	 */
	@Override
	public List<Cidade> getCidadesByEstado(String uf) {

		return this.cidadeRepository.getCidadesByEstado(uf);

	}

}
