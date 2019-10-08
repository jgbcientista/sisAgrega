package br.com.sysagrega.service.imp;

import java.util.List;

import javax.inject.Inject;

import br.com.sysagrega.model.imp.Cidade;
import br.com.sysagrega.model.imp.Regiao;
import br.com.sysagrega.repository.imp.CidadeRepository;
import br.com.sysagrega.repository.imp.RegiaoRepository;
import br.com.sysagrega.service.ICidadeService;
import br.com.sysagrega.service.IRegiaoService;

public class RegiaoService implements IRegiaoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private RegiaoRepository regiaoRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.sysagrega.service.imp.ICidadeService#getAllCidades()
	 */
	@Override
	public List<Regiao> getAllRegiao() {

		return this.regiaoRepository.getAllRegiao();

	}

	

}
