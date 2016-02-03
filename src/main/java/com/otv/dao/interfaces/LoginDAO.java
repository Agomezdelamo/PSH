package com.otv.dao.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.otv.model.User;
import com.otv.utility.ResultadoAccionLogin;

public interface LoginDAO {
	
	public User findUserByLoginData(String username);
}
