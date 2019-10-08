package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.IUsuario;
import br.com.sysagrega.model.imp.Usuario;
import br.com.sysagrega.repository.IUsuarioRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = IUsuario.class)
public class UsuarioConverter implements Converter<Object> {

	//@Inject
	private IUsuarioRepository usuarioRepository;
	
	public UsuarioConverter() {
		usuarioRepository = CDIServiceLocator.getBean(IUsuarioRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		IUsuario retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = usuarioRepository.getById(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof IUsuario) {
			return String.valueOf(((Usuario) value).getId());
		}
		
		return "";
	}

}
