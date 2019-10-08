package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.IRecurso;
import br.com.sysagrega.model.imp.Recurso;
import br.com.sysagrega.repository.imp.RecursoRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = IRecurso.class)
public class RecursoConverter implements Converter<Object> {

	//@Inject
	private RecursoRepository recursoRepository;
	
	public RecursoConverter() {
		recursoRepository = CDIServiceLocator.getBean(RecursoRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		IRecurso retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = recursoRepository.getRecursoById(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof IRecurso) {
			return String.valueOf(((Recurso) value).getId());
		}
		
		return "";
	}

}
