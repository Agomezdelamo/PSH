package com.otv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otv.constants.enumerados.RolesEnum;
import com.otv.model.User;
import com.otv.services.interfaces.RoleService;
import com.otv.services.interfaces.UserService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	UserService userService;
	
	@Override
	/**
	 * Método para comprobar si un usuario tiene rol admin
	 */
	public Boolean isUser(int id) {
		User usuarioConsulta;
		Boolean result = false;
		
		usuarioConsulta = userService.getUserById(id);
		
		if(usuarioConsulta.getRol().getRolesId() == RolesEnum.USUARIO.getId()) {
			result = true;
		}
		
		return result;
	}
	
	@Override
	/**
	 * Método para comprobar si un usuario tiene rol admin
	 */
	public Boolean isAdmin(int id) {
		User usuarioConsulta;
		Boolean result = false;
		
		usuarioConsulta = userService.getUserById(id);
		
		if(usuarioConsulta.getRol().getRolesId() == RolesEnum.ADMIN.getId()) {
			result = true;
		}
		
		return result;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
