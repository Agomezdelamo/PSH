package com.otv.services.interfaces;

public interface RoleService {

	/**
	 * Método para comprobar si un usuario es rol Usuario por su id.
	 * 
	 * @param id
	 * @return Boolean
	 */
	Boolean isUser(int id);
	
	/**
	 * Método para comprobar si un usuario es rol Administrador por su id.
	 * 
	 * @param id
	 * @return Boolean
	 */
	public Boolean isAdmin(int id);


}
