package com.otv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otv.constants.enumerados.CodigoRespuestaEnum;
import com.otv.dao.interfaces.LoginDAO;
import com.otv.model.User;
import com.otv.services.interfaces.LoginService;
import com.otv.utility.ResultadoAccionLogin;

//etiqueta de spring para que lo reconozca con su scan en spring-config y poder inyectarlo con @Autowired
@Service("loginService")
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginDAO loginDAO;

	@Transactional(readOnly = false)
	@Override
	public ResultadoAccionLogin login(String username, String password) {
		ResultadoAccionLogin codigoResultado = new ResultadoAccionLogin();
		//es error por defecto
		codigoResultado.setCodigoRespuesta(CodigoRespuestaEnum.ERROR);
		User user = null;
		
		try {
			// Obtenemos el usuario que se va a intentar logar
			user = findUserByLoginData(username);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		if(user != null) {
			if(user.getPassword().equals(password)) {
				codigoResultado.setCodigoRespuesta(CodigoRespuestaEnum.OK);
			}
		}
		return codigoResultado;
	};
	
	@Transactional(readOnly = false)
	@Override
	public User findUserByLoginData(String username) {
		
		User resultado = null;
		
		//compruebo que  el usuario no es null.
		if(username !=null ) {
			//hago la consulta a base de datos.
			resultado = loginDAO.findUserByLoginData(username);
		}
		
		return resultado;
	}


	public LoginDAO getLoginDAO() {
		return loginDAO;
	}


	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	};

	
}
