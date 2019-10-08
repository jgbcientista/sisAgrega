package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.IPerfil;
import br.com.sysagrega.model.imp.Perfil;
import br.com.sysagrega.repository.IPerfilRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = IPerfil.class)
public class PerfilConverter implements Converter<Object> {

	//@Inject
	private IPerfilRepository perfilRepository;
	
	public PerfilConverter() {
		perfilRepository = CDIServiceLocator.getBean(IPerfilRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		IPerfil retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = perfilRepository.getById(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof IPerfil) {
			return String.valueOf(((Perfil) value).getId());
		}
		
		return "";
	}

}
