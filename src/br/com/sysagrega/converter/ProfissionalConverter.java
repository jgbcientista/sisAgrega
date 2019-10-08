package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.IProfissional;
import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.repository.imp.ProfissionalRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = IProfissional.class)
public class ProfissionalConverter implements Converter<Object> {

	//@Inject
	private ProfissionalRepository profissionalRepository;
	
	public ProfissionalConverter() {
		profissionalRepository = CDIServiceLocator.getBean(ProfissionalRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		IProfissional retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = profissionalRepository.getById(id);
		}
		
		return retorno;
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof IProfissional) {
			return String.valueOf(((Profissional) value).getId());
		}
		
		return "";
	}

}
