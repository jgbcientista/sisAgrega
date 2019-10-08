package br.com.sysagrega.controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.sysagrega.model.imp.Profissional;
import br.com.sysagrega.service.IProfissionalService;

@FacesConverter(forClass=Profissional.class, value="converterProfissional")
public class ConverterProfissional implements Converter {  
   
	@Inject
	private IProfissionalService profissionalService;
	
	 @Override
	    public Object getAsObject(FacesContext context, UIComponent component,String value) {                

	        if(value == null || value.isEmpty()){
	            return null;
	        }else{
	            Long id = Long.parseLong(value);
	            Profissional unidade = profissionalService.getById(id);
	            return unidade;
	        }

	    }

	    @Override
	    public String getAsString(FacesContext context, UIComponent component,Object value) {
	       
	    	if(value != null &&!value.toString().equals("")){
	    		Profissional itemConvert = (Profissional)value;
	    		
	            return String.valueOf(itemConvert.getId());
	        }else{
	            return null;
	        }

	    }
}  