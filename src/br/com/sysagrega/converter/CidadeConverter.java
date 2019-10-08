package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.ICidade;
import br.com.sysagrega.model.imp.Cidade;
import br.com.sysagrega.repository.imp.CidadeRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = ICidade.class)
public class CidadeConverter implements Converter<Object> {

	//@Inject
	private CidadeRepository cidadeRepository;
	
	public CidadeConverter() {
		cidadeRepository = CDIServiceLocator.getBean(CidadeRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cidade retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = cidadeRepository.getCidadeById(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof ICidade) {
			return String.valueOf(((Cidade) value).getId());
		}
		
		return "";
	}

}
