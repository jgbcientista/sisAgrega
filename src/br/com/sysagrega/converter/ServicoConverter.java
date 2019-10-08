package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.IServico;
import br.com.sysagrega.model.imp.Servico;
import br.com.sysagrega.repository.IServicoRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = IServico.class)
public class ServicoConverter implements Converter<Object> {

	//@Inject
	private IServicoRepository iServicoRepository;
	
	public ServicoConverter() {
		iServicoRepository = CDIServiceLocator.getBean(IServicoRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		IServico retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = iServicoRepository.getServicoById(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof IServico) {
			return String.valueOf(((Servico) value).getId());
		}
		
		return "";
	}

}
