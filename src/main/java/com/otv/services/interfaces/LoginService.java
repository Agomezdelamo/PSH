package com.otv.services.interfaces;

import com.otv.model.User;
import com.otv.utility.ResultadoAccionLogin;

public interface LoginService {
	
	public ResultadoAccionLogin login(String username, String password);
	
	public User findUserByLoginData(String username);
}
