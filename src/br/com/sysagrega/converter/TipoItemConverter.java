package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.ITipoItem;
import br.com.sysagrega.model.imp.TipoItem;
import br.com.sysagrega.repository.ITipoItemRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = ITipoItem.class)
public class TipoItemConverter implements Converter<Object> {

	//@Inject
	private ITipoItemRepository iRepository;
	
	public TipoItemConverter() {
		iRepository = CDIServiceLocator.getBean(ITipoItemRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ITipoItem retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = iRepository.getById(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof ITipoItem) {
			return String.valueOf(((TipoItem) value).getId());
		}
		
		return "";
	}

}
