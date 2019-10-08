package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.IBanco;
import br.com.sysagrega.model.imp.Banco;
import br.com.sysagrega.repository.imp.BancoRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = IBanco.class)
public class BancoConverter implements Converter<Object> {

	//@Inject
	private BancoRepository bancoRepository;
	
	public BancoConverter() {
		bancoRepository = CDIServiceLocator.getBean(BancoRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Banco retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = bancoRepository.getBancoById(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof IBanco) {
			return String.valueOf(((Banco) value).getId());
		}
		
		return "";
	}

}
