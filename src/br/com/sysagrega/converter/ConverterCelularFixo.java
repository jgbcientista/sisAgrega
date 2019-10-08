package br.com.sysagrega.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="convertCelularFixo")  
public class ConverterCelularFixo implements Converter<Object> {
  
    @Override  
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
        if(valor != null || valor != "") {
            valor = valor.toString().replaceAll("[- /.]", "");
            valor = valor.toString().replaceAll("[-()]", "");
        }
        return valor;  
    }
  
    @Override  
    public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
    	String texto = "";
    	if (valor.toString() != null && !valor.toString().equals("")) {
    		texto = valor.toString();
    		if (texto.length() == 8) {
    			// adicionar traço após 4 dígitos
    			texto = texto.substring(0, 4) + "-" + texto.substring(4, texto.length());
    		} else if (texto.length() == 9) {
    			// adicionar traço após 5 dígitos
    			texto = texto.substring(0, 5) + "-" + texto.substring(5, texto.length());
    		}
    	}
        return texto;
    }
    
}