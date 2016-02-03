package com.otv.controller.backingBeans.abstractBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.otv.model.User;
import com.otv.preModel.command.UserCommand;
import com.otv.services.interfaces.UserService;

public abstract class UserAbstractBean {

	private UserCommand usuarioCommand;

	// Inyectamos en jsf porque estos beans estan en entorno jsf
	@ManagedProperty("#{userService}")
	private transient UserService userService;

	private List<User> userList;

	private User currentUser;

	private String emptyUser;
	
	private static final String USERSESSION = "idUserSession";
	/**
	 * genero campo logger y le digo que registre mi clase.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserAbstractBean.class);

	public void init() {
		LOGGER.trace("INICIO: init() del abstract, super");
		User usuario = new User();
		emptyUser = "Seleccionar uno";
			
		currentUser = getUserSesion();
		if (currentUser == null) {
			currentUser = usuario;
		}

		/**
		 * paso al command o un usuario en blanco inicializado en el orm del
		 * modelo o el usuario actual.
		 */
		
		modelToCommand(currentUser);
		updateUserList();

		LOGGER.trace("FIN: init() del abstract, super");
	}

	/**
	 * Pasa del command al modelo para m�s adelante persistir
	 * 
	 * Retorna un @return User que almacena todos los datos existentes en el
	 * command.
	 */
	private User commandToModel() {
		User usuario = new User();

		usuario.setName(usuarioCommand.getName());
		usuario.setSurname(usuarioCommand.getSurname());
		usuario.setDni(usuarioCommand.getDni());

		return usuario;
	}

	/**
	 * Rellena el command con los datos que tiene el modelo.
	 * 
	 * Pasa del modelo al command para la vista
	 * 
	 * @param usuario
	 * @return void
	 */
	private void modelToCommand(User user) {
		usuarioCommand = new UserCommand();

		usuarioCommand.setName(user.getName());
		usuarioCommand.setSurname(user.getSurname());
		usuarioCommand.setDni(user.getDni());
	}

	public String saveUser() {
		LOGGER.trace("INICIO: de salvar usuario ()");
		String valueReturn = "";
		
		
		/**
		 * VALIDACIÓN EN FASE-5 (SERVIDOR)
		 * */
		if(usuarioCommand.getSurname() == null || usuarioCommand.getSurname().isEmpty()) {
			//TODO 
			
			//FacesMessage.SEVERITY_ERROR constante de jsf
			/*FacesMessage es un enumerado de jsf. En su constructor sobrecargado recibe 3 parametros,
			un objeto de clase Severity, y dos Strings que hacen de resumen y contenido*/
			FacesContext.getCurrentInstance().addMessage("formAdd:surname", new FacesMessage(FacesMessage.SEVERITY_ERROR, "error2", "Apellido necesario. Error en fase 5 -VALIDACIÓN EN EL BEAN (SERVIDOR)."));
				/** el método addMessage recibe como primer párametro el id,
				 *  por lo que veo no funciona si no 
				 *  indicas el idForm:idComponente
				 */
			
			// recupero el contexto de jsf y le digo que ha fallado con este flag, ojo es sólo para la fase 5.
			FacesContext.getCurrentInstance().validationFailed();
		}
		
		else {
			// obtenemos usuario que hemos guardado en el command to model y que
			// hemos obtenido de la vista
			// puesto que el usuarioCommand se carga en la construccion del
			// usuarioBean.
	
			// el usuario almacenado en el command se le pasa al servicio en el formato
			// correcto que es el que se da en el commandToModel
			getUserService().addUser(commandToModel());
	
			LOGGER.trace("FIN: de salvar usuario ()");
			/*se redirige la pantalla destino para que el action redirija solito,
				siempre procurar devolver un string en el return.*/
			valueReturn = "userList?faces-redirect=true";
		}		
		return valueReturn;
	}
	
	public String updateUser() {
		LOGGER.trace("INICIO: de salvar usuario ()");
		
		// obtenemos usuario que hemos guardado en el command to model y que
		// hemos obtenido de la vista
		// puesto que el usuarioCommand se carga en la construcci�n del
		// usuarioBean.
		
		// ese usario se le pasa al servicio
		getUserService().updateUser(commandToModel());
		
		LOGGER.trace("FIN: de salvar usuario ()");
		
		return "userList?faces-redirect=true";
		
	}

	/**
	 * metodo comun para borrar un usuario en este es donde realmente se borra.
	 * devuelve un @return string que redirige a la p�gina que corresponda.
	 */

	public String removeUser(User user) {
		try {
			getUserService().deleteUser(user);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "userList?faces-redirect=true";
	}

	/**
	 * Esto no seria mejor hacerlo en el command, puesto que vamos a la Db a
	 * traernos una lista de usuarios?
	 * compruebo que el objeto devuelto no es nulo, y que no esta vacia la lista

	 * @return
	 */
	public List<User> updateUserList() {
		
		userList = userService.getUsers();
		
		if (userList != null && !userList.isEmpty()) { return userList; } 
		else { return userList = new ArrayList<User>(); }
	}

	public void putUserInSession(User user) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		session.setAttribute(USERSESSION, user);
	}

	public User getUserSesion() {
		/**
		 * inicializar siempre a null en los m�todos
		 */
		User retrieveUser = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		
		/**
		 * siempre comprobar la sesi�n porque se puede
		 * perder.
		 */
		if (null != session) {
			retrieveUser = (User) session.getAttribute(USERSESSION);
		}

		return retrieveUser;
	}

	/**
	 * @return the usuarioCommand
	 */
	public UserCommand getUsuarioCommand() {
		return usuarioCommand;
	}

	/**
	 * @param usuarioCommand
	 *            the usuarioCommand to set
	 */
	public void setUsuarioCommand(UserCommand usuarioCommand) {
		this.usuarioCommand = usuarioCommand;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * @param userList
	 *            the userList to set
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser
	 *            the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * @return the emptyUser
	 */
	public String getEmptyUser() {
		return emptyUser;
	}

	/**
	 * @param emptyUser the emptyUser to set
	 */
	public void setEmptyUser(String emptyUser) {
		this.emptyUser = emptyUser;
	}

}
