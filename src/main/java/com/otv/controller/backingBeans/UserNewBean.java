package com.otv.controller.backingBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.otv.controller.backingBeans.abstractBeans.UserAbstractBean;

//Inyeccion de dependencias de jsf
//para usarla #{userNewBean.propiedad} en la vista, o 
//para auto-inyectarlo en otro bean con esta etiqueta @ManagedProperty("#{userNewBean}").
@ManagedBean

// Asociacion a la vista jsf
// ViewScoped -- Indica que el ciclo de vida del bean esta unido al de la vista,
// cuando cambias de vista muere el bean.
@ViewScoped
public class UserNewBean extends UserAbstractBean implements Serializable {

	
	/**
	 * serial �nico para la persistencia.
	 */
	private static final long serialVersionUID = -3191208221080110275L;

	
	/**
	 * genero campo logger y le digo que registre mi clase.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserNewBean.class);

	
	/**
	 * PostConstruct
	 * se construye una vez estan instanciados todos los campos
	 */
	@Override
	@PostConstruct
	public void init() {
		LOGGER.trace("INICIO: init() del bean, child");

		super.init();
		initializedData();
		LOGGER.trace("FIN: init() del bean, child");
	}
	
	public void initializedData() {
		getUsuarioCommand().setName("");
		getUsuarioCommand().setSurname("");
		getUsuarioCommand().setDni("0");
	}


}