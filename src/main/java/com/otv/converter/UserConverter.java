package com.otv.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.otv.model.User;
import com.otv.services.UserServiceImpl;
import com.otv.services.interfaces.UserService;

@FacesConverter(value="userConverter")
public class UserConverter implements Converter {
	
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if(value==null||value.equals("")){
            return null;
        }
		Integer userId = Integer.parseInt(value);
		
		int id = userId;
		
		FacesContext context = FacesContext.getCurrentInstance();
		/**
		 * Ojo, recojo el interface del servicio de usuario, porque la clase que implementa
		 * es transaccional, quiere decir que monta un proxy intermedio en forma de objeto,
		 * y no deja castear de ese objeto proxy a la clase implementada del servicio.
		 */
		UserService userService = context.getApplication().evaluateExpressionGet(context, "#{userService}", UserService.class);
        User usuario = (User)userService.getUserById(userId);
        
		return usuario;
	}

	/**
	 * Método getAsString de CountryConverter
	 */
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		
		//compruebo que el value es una instancia de User, que es un objeto que no esta vacio y que su toString no esta vacio.
		if(value instanceof User && value != null && !value.toString().isEmpty()) {
	//		Lo paso a la clase envoltoria para que me permita hacer el toString();
			Integer id = ((User) value).getId(); 
			return id.toString();
		}
		
		else {
			return "";
		}
	}
}