package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.IContrato;
import br.com.sysagrega.repository.imp.ContratoRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = IContrato.class)
public class ContratoConverter implements Converter<Object> {

	//@Inject
	private ContratoRepository contratoRepository;
	
	public ContratoConverter() {
		contratoRepository = CDIServiceLocator.getBean(ContratoRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		IContrato retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = contratoRepository.getContratoById(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof IContrato) {
			return String.valueOf(((IContrato) value).getId());
		}
		
		return "";
	}

}
