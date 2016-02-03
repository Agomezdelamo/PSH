package com.otv.preModel.command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.dao.DataAccessException;

import com.otv.model.User;
import com.otv.services.interfaces.UserService;

public class UserCommand implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constantes
	 */
	private static final String SUCCESS = "success";
	private static final String ERROR   = "error";
	
	//Inyecci�n del servicio para usuarios
	@ManagedProperty(value="#{UserServiceImpl}")
	private transient UserService userService;

	List<User> userList;

	private String name;
	private String surname;
	private String dni;
	
	/**
	 * constructor por defecto del command
	 * 
	 */
	public UserCommand() {
	
	}
	
	/**
	 * Get User List
	 * 
	 * @return List - User List
	 */
	public List<User> getUserList() {
		userList = new ArrayList<User>();
		userList.addAll(getUserService().getUsers());
		return userList;
	}
	
	/**
	 * Get User Service
	 * 
	 * @return IUserService - User Service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Set User Service
	 * 
	 * @param UserServiceImpl - User Service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Set User List
	 * 
	 * @param List - User List
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * Get User Name
	 * 
	 * @return String - User Name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set User Name
	 * 
	 * @param String - User Name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get User Surname
	 * 
	 * @return String - User Surname
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Set User Surname
	 * 
	 * @param String - User Surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
}





