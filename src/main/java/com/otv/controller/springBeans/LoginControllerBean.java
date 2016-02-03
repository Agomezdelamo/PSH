package com.otv.controller.springBeans;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedProperty;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.otv.constants.Constantes;
import com.otv.constants.enumerados.CodigoRespuestaEnum;
import com.otv.model.User;
import com.otv.preModel.command.LoginCommand;
import com.otv.services.interfaces.LoginService;
import com.otv.services.interfaces.UserService;
import com.otv.utility.ResultadoAccionLogin;


@Controller
@RequestMapping("/login")
public class LoginControllerBean {
	
	LoginCommand loginCommand;
	
	//inyección de dependencias por nombre de spring
	@Autowired
	LoginService loginService;
	
	@Autowired
	RequestManager requestManager;
	
	//inyección de dependencias por nombre de spring
	@Autowired
	UserService userService;
	
	//inyeccion del usuario que si se loga correctamente se mete en sesión.
	User userInSession;
	
	/**
	 * Guarda la petición inicial (URL) antes de pasar por el interceptor de Spring Security
	 */
	private SavedRequest savedRequest;

	
	/**
	 * Método formBackingObject al que se llama antes de mostrar la vista. El método sólo se llama en las peticiones GET.
	 * 
	 * @param request - petición
	 * @param model - modelo
	 * @return devuelve el objeto con los datos del modelo para representar la vista.
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView formBackingObject(HttpServletRequest request, Model model) throws ServletException, IOException {
		//command para representar el modelo en la vista.
		loginCommand = new LoginCommand();
		
		//mapa que representa el modelo.
		Map<String, Object> modeloNuevo = new HashMap<String, Object>();

		// RECUPERA LA URL DE LA PETICIÓN ORIGINAL EN CASO DE PEDIR UNA PÁGINA PARA LA QUE SEA NECESARIO TENER SESION
		savedRequest = (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
		
		
		/**
		 * TODO:  Método ** ACTUALIZAR COMMAND DEL MODELO ** updateModelCommand que compruebe si ya hay un command con una clave string en el modelo,
		 * eso significaria que el login a fallado y ha redirigido a login, pasar por el método
		 * formBackingObject otra vez, compruebo que existe un command en el modelo,
		 * y entonces me va a tocar limpiarlo para que en la vista aparezcan los campos vacios.
		 */
		
		/*la primera carga retorna esto*/
		return new ModelAndView("loginVista", modeloNuevo);
	}
	
	
	/**
	 * Método onSubmit al que se llama al enviar el formulario. El método sólo se llama en las peticiones POST
	 * 
	 * @param request
	 * @param response
	 * @param command
	 * @param errors
	 * @param model
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * Cuando hay una petición POST de la página mapeada en la clase pasa por este método.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("command") LoginCommand command,
			Errors errors, Model model) throws ServletException, IOException {

		ResultadoAccionLogin resultCode = null;
		
		//obtengo del command de la vista el nombre de usuario, para comprobarlo en base de datos.
		//trim elimina los espacios y cosas que sobran.
		String username = command.getUsername().trim();
		String password = command.getPassword().trim();
		String resultado = "redirect:/login";
		
		resultCode = loginService.login(username, password);
		
		if (resultCode.getCodigoRespuesta().equals(CodigoRespuestaEnum.OK)) {
				userInSession = userService.getUserByUserName(username);
				requestManager.ponerEnSesion("user", userInSession, request);
				String paginaHome = requestManager.redirectToHome(userInSession.getId(), request);
				return new ModelAndView(paginaHome);
		}
		return new ModelAndView(resultado);
	}

	public LoginService getLoginService() {
		return loginService;
	}


	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}


	public RequestManager getRequestManager() {
		return requestManager;
	}


	public void setRequestManager(RequestManager requestManager) {
		this.requestManager = requestManager;
	}
}
