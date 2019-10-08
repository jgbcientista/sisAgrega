package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.IProposta;
import br.com.sysagrega.model.imp.Proposta;
import br.com.sysagrega.repository.imp.PropostaRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = IProposta.class)
public class PropostaConverter implements Converter<Object> {

	//@Inject
	private PropostaRepository propostaRepository;
	
	public PropostaConverter() {
		propostaRepository = CDIServiceLocator.getBean(PropostaRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		IProposta retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = propostaRepository.getPropostaById(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof IProposta) {
			return String.valueOf(((Proposta) value).getId());
		}
		
		return "";
	}

}
