package com.otv.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.otv.controller.backingBeans.UserNewBean;
import com.otv.model.User;
import com.otv.services.interfaces.UserService;


@FacesValidator("userNewValidator")
public class UserNewValidator implements Validator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserNewValidator.class);

	
	@Override
	public void validate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		LOGGER.trace("INICIO: bean de validación de userNew");
			
			int maxCharacter = 8;
			String realCharacter = (String) object;
			int numCharacter = realCharacter.length();
			
//			1. VALIDACION maximo caracteres
			if(numCharacter > maxCharacter) {
				FacesContext.getCurrentInstance().addMessage("formAdd:dni", new FacesMessage(FacesMessage.SEVERITY_ERROR, "demasiados caracteres", "No puede pasar de 8 carácteres. Error en fase 3"));
//				lanzo una excepción, como no la capturo hace de flag para cascar la validación
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "demasiaooo", "demasiaao pal body"));
			}
			
//			2. VALIDACION dni repetido
			UserService userService = context.getApplication().evaluateExpressionGet(context, "#{UserServiceImpl}", UserService.class);
				User userDni = (User)userService.getUserByDni((String)object);
				if(userDni != null) {
					String dni = userDni.getDni();
				
					if(dni.equals((String)object) ) {
						FacesContext.getCurrentInstance().addMessage("formAdd:dni", new FacesMessage(FacesMessage.SEVERITY_ERROR, "dni repetido", "El dni esta repetido. Error en fase 3."));
						throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "demasiaooo", "demasiaao pal body"));
					}
				}	 
				
//			3. VALIDACION ULTIMO CARACTER UNA LETRA
				String dniLast = (String)object.toString();
				int n = dniLast.length();
				char l = dniLast.charAt(n-1);
				
				//valueOf es un convertidor del tipo parseInt, acepta casi cualquier cosita primitiva y la pasa a String.
				String a = String.valueOf(l);
				if(!a.matches("[a-zA-Z]")) {
					FacesContext.getCurrentInstance().addMessage("formAdd:dni", new FacesMessage(FacesMessage.SEVERITY_ERROR, "caracter", "el último cáracter debe de ser una letra. Error en fase 3."));
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "demasiaooo", "demasiaao pal body"));
				};

		LOGGER.trace("FIN: bean de validación de userNew");
	}
}
