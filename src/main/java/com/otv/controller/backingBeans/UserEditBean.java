package com.otv.controller.backingBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Controller;

import ch.qos.logback.classic.Logger;

import com.otv.controller.backingBeans.abstractBeans.UserAbstractBean;

//Inyeccion de dependencias de jsf
//para usarla #{userEditBean.propiedad} en la vista, o 
//para auto-inyectarlo en otro bean con esta etiqueta @ManagedProperty("#{userEditBean}").
@ManagedBean

//Asociacion a la vista jsf
//ViewScoped -- Indica que el ciclo de vida del bean esta unido al de la vista,
//cuando cambias de vista muere el bean.
@ViewScoped
public class UserEditBean extends UserAbstractBean implements Serializable {


	private static final long serialVersionUID = 454299856363344509L;
	

	@Override
	@PostConstruct
	public void init() {
		super.init();
	}

}
