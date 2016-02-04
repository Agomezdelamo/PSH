package com.otv.controller.springBeans;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.util.text.BasicTextEncryptor;
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
import com.otv.constants.enumerados.RolesEnum;
import com.otv.model.User;
import com.otv.preModel.command.LoginCommand;
import com.otv.services.interfaces.LoginService;
import com.otv.services.interfaces.RoleService;
import com.otv.services.interfaces.SessionManager;
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
	RoleService roleService;

	//inyección de dependencias por nombre de spring
	@Autowired
	UserService userService;
	
	//inyección de dependencias por nombre de spring
	@Autowired
	SessionManager sessionManager;
	
	//inyeccion del usuario que si se loga correctamente se mete en sesión.
	User userInSession;
	
	private StringBuilder page;
	
	private String rolUrl;
	
	private String redirectPage;
	
	private Boolean isLoginComplete = false;
	
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
				
				HttpSession session = request.getSession(true);
				sessionManager.addUserToSession(userInSession, session);
				String paginaHome = redirectToHome(userInSession.getId(), request);
				return new ModelAndView(paginaHome);
		}
		return new ModelAndView(resultado);
	}

	public String redirectToHome(int id, HttpServletRequest request) {

		/* pila  ultimo en el primer lugar.*/
		Stack<String> pila = new Stack<String>();
		page = new StringBuilder("redirect:/");
		rolUrl ="";
		
		//para usuario, me traigo al usuario de la base de datos para tenerlo en un command y redirijo a otra vista usuario
		if(roleService.isUser(id)) {
			System.out.println("soy usuario");
			
			rolUrl = RolesEnum.USUARIO.toString().toLowerCase();
		}
		//para admin, al usuario de la base de datos para tenerlo en un command y redirijo a otra vista admin
		if(roleService.isAdmin(id)) {
			System.out.println("soy admin");
			
			rolUrl = RolesEnum.ADMIN.toString().toLowerCase();
		}
		
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(request.getSession(false).getId());
		//Integer.toString(id) tambien valdria
		String idEncode = textEncryptor.encrypt(String.valueOf(id));
		
		page.append(Constantes.JSFPAGES_RAIZ).append("/").append(rolUrl).append("/").append("home.jsf").append("?").append(Constantes.FACES_REDIRECT).append("=true").append("&")
		.append(Constantes.PARAM_ID).append("=").append(idEncode);
		redirectPage = page.toString();
		pila.push(redirectPage);
		//TODO: crear un metodo para poner en sesion cualquier cosa en session manager
//		requestManager.ponerEnSesion(Constantes.PARAM_PILA, pila, request);
		
		/* booleano para que apartir de ahora todas las redirecciones las maneje el método Redirecciona(String page) */
		isLoginComplete = true;
		
		return redirectPage;
	}
	
	
	public LoginService getLoginService() {
		return loginService;
	}


	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}


}
