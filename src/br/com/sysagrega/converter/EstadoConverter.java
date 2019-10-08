package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.IEstado;
import br.com.sysagrega.model.imp.Estado;
import br.com.sysagrega.repository.imp.EstadoRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = IEstado.class)
public class EstadoConverter implements Converter<Object> {

	//@Inject
	private EstadoRepository estadoRepository;
	
	public EstadoConverter() {
		estadoRepository = CDIServiceLocator.getBean(EstadoRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Estado retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = estadoRepository.getEstadoById(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof IEstado) {
			return String.valueOf(((IEstado) value).getId());
		}
		
		return "";
	}

}
