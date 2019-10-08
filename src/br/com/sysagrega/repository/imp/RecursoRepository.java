package br.com.sysagrega.repository.imp;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.sysagrega.model.IRecurso;
import br.com.sysagrega.model.imp.Recurso;
import br.com.sysagrega.repository.IRecursoRepository;
import br.com.sysagrega.service.imp.NegocioException;

public class RecursoRepository implements IRecursoRepository {
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	@Inject
	public RecursoRepository(EntityManager manager) {
		
		this.manager = manager;
		
	}

	@Override
	public List<Recurso> getAllRecursos() {
		UaiCriteria<Recurso> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Recurso.class);
		easyCriteria.andEquals("ativo", true);
		
		return easyCriteria.getResultList();
	}

	@Override
	public Recurso getRecursoById(Long id) {
		return this.manager.find(Recurso.class, id);
	}

	@Override
	public IRecurso salvarRecurso(IRecurso recurso) {
		return this.manager.merge(recurso);
		 
	}

	@Override
	public void remover(IRecurso recurso) {
	
	try {
			
		IRecurso rec = getRecursoById(recurso.getId());
			this.manager.remove(rec);
			this.manager.flush();
			
		} catch (PersistenceException e) {
			
			throw new NegocioException("Servico não pode ser excluído.");
			
		}
		
	}

	@Override
	public List<Recurso> getRecursoByFilter(Recurso filtro) {

		UaiCriteria<Recurso> easyCriteria = UaiCriteriaFactory.createQueryCriteria(manager, Recurso.class);
		
		
		if(filtro != null && filtro.getFabricante()   != null ) {
			easyCriteria.andStringLike("fabricante", "%" +  filtro.getFabricante() + "%");
		}
		
		if(filtro != null && filtro.getNome()   != null && filtro.getNome() != "") {
			easyCriteria.andStringLike("nome", "%" +  filtro.getNome() + "%");
		}
		
		if(filtro != null && filtro.getValidade()   != null ) {
			easyCriteria.andEquals("validade", filtro.getValidade());
		}
		
		if(filtro != null && filtro.getTipoRecurso()   != null  && filtro.getTipoRecurso().getId()   != null ) {
			easyCriteria.andEquals("tipoRecurso", filtro.getTipoRecurso().getId());
		}
		
		if(filtro != null && filtro.getClassificacaoRecurso()   != null  && filtro.getClassificacaoRecurso().getId()   != null ) {
			easyCriteria.andEquals("classificacaoRecurso", filtro.getClassificacaoRecurso().getId());
		}
		
		easyCriteria.andEquals("ativo", true);
		
		try {
			
			return easyCriteria.getResultList();
			
		} catch (NoResultException e) {
			
			return null;
			
		}
	}


}
