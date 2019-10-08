package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.ICliente;
import br.com.sysagrega.model.imp.Cliente;
import br.com.sysagrega.repository.imp.ClienteRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = ICliente.class)
public class ClienteConverter implements Converter<Object> {

	//@Inject
	private ClienteRepository clienteRepository;
	
	public ClienteConverter() {
		clienteRepository = CDIServiceLocator.getBean(ClienteRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ICliente retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = clienteRepository.getClienteById(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof ICliente) {
			return String.valueOf(((Cliente) value).getId());
		}
		
		return "";
	}

}
