package com.otv.controller.backingBeans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Controller;

import com.otv.controller.backingBeans.abstractBeans.UserAbstractBean;
import com.otv.model.User;

//Inyeccion de dependencias de jsf
//para usarla #{userListBean.propiedad} en la vista, o 
//para auto-inyectarlo en otro bean con esta etiqueta @ManagedProperty("#{userListBean}").
@ManagedBean

//Asociacion a la vista jsf
//ViewScoped -- Indica que el ciclo de vida del bean esta unido al de la vista,
//cuando cambias de vista muere el bean.
@ViewScoped
public class UserListBean extends UserAbstractBean implements Serializable {
	
	private User userForSelectOneMenu;

	
	private static final long serialVersionUID = 1L;

	@Override
	@PostConstruct
	public void init() {
		super.init();
	}
	
	/**
	 * m�todo que redirecciona a la vista de a�adir un nuevo usuario
	 */
	public void addUser() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("userNew.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * m�todo que redirecciona a la vista de editar usuario
	 * @param user recibe como par�metro un usuario que es 
	 * el que se pasa a la sesi�n para que en el init lo pase
	 * al command y asi poder pintar los datos.
	 */
	
	public void editUser(User user) {
		putUserInSession(user);

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("userEdit.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void initRemoveUser(User user) {
		/**
		 * meto el usuario en sesi�n para
		 * probar a usar la sesi�n.
		 * 	
		 */
		putUserInSession(user);
	}
	
	public void remove() {
		/**
		 * obtengo el usuario del campo del abstract que a su vez lo obtiene de la sesi�n,
		 * es dar un rodeo innecesario, pero estamos probando sesi�n.
		 */
		removeUser(getCurrentUser());
	}

	/**
	 * @return the userForSelectOneMenu
	 */
	public User getUserForSelectOneMenu() {
		return userForSelectOneMenu;
	}

	/**
	 * @param userForSelectOneMenu the userForSelectOneMenu to set
	 */
	public void setUserForSelectOneMenu(User userForSelectOneMenu) {
		this.userForSelectOneMenu = userForSelectOneMenu;
	}
	
	
}
