package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.ITipoItemProposta;
import br.com.sysagrega.model.imp.TipoItemProposta;
import br.com.sysagrega.repository.ITipoItemPropostaRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = ITipoItemProposta.class)
public class TipoItemPropostaConverter implements Converter<Object> {

	//@Inject
	private ITipoItemPropostaRepository iRepository;
	
	public TipoItemPropostaConverter() {
		iRepository = CDIServiceLocator.getBean(ITipoItemPropostaRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ITipoItemProposta retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = iRepository.getById(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof ITipoItemProposta) {
			return String.valueOf(((TipoItemProposta) value).getId());
		}
		
		return "";
	}

}
