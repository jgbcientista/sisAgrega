package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sysagrega.model.IFornecedor;
import br.com.sysagrega.model.imp.Fornecedor;
import br.com.sysagrega.repository.imp.FornecedorRepository;
import br.com.sysagrega.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = IFornecedor.class)
public class FornecedorConverter implements Converter<Object> {

	//@Inject
	private FornecedorRepository fornecedorRepository;
	
	public FornecedorConverter() {
		fornecedorRepository = CDIServiceLocator.getBean(FornecedorRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		IFornecedor retorno = null;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			retorno = fornecedorRepository.getFornecedorById(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof IFornecedor) {
			return String.valueOf(((Fornecedor) value).getId());
		}
		
		return "";
	}

}