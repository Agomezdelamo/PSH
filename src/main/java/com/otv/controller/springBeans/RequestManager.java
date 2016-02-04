package com.otv.controller.springBeans;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.otv.constants.Constantes;
import com.otv.constants.enumerados.RolesEnum;
import com.otv.services.interfaces.RoleService;
import com.otv.utility.DecoderBean;

@ManagedBean
@SessionScoped
public class RequestManager {
	
	@ManagedProperty("#{decoderBean}")
	DecoderBean decoderBean;
	
	/**
	 * metodo REST que entra en juego cuando aparecen estos parametros.
	 * 
	 * @value login
	 * @param id
	 * 
	 * tipo de request de ejemplo que haría entrar en este método.
	 * NOMBREDEAPP/login?id=2
	 */

	@RequestMapping(value="login", params="id") 
	@ResponseBody
	public void manageIdParam(@RequestParam("id") int id) { 
		System.out.println("este es mi id" + id + "\r\n");
	}
	
	/**
	 * Se pone en sesión un objeto como atributo.
	 * 
	 * @param claveAtributoSesion
	 * @param valor
	 */
	public void ponerEnSesion(String claveAtributoSesion, Object valor, HttpServletRequest request) {
		/**
		 * tener en sesión toda la navegacion en una pila?
		 */
		request.getSession(false).setAttribute(claveAtributoSesion, valor);
	}
	
	/**
	 * Se obtiene de la sesión un atributo como objeto.
	 * 
	 * @param claveAtributoSesion
	 * @param valor
	 */
	public Object cojerDeSesion(String claveAtributoSesion) {
		/**
		 * obtengo la request-peticion en el contexto de jsf
		 */
		HttpServletRequest peticion = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		/**
		 * obtengo el atributo de la sesión.
		 */
		Object objectSession = peticion.getSession(false).getAttribute(claveAtributoSesion);
		
		return objectSession;
	}
	
	/**
	 * Devuelve una cadena con los parámetros de tipo request a pasar
	 * 
	 * @param nextPage
	 *            Página a la que se navega
	 * @param parameters
	 *            Mapa de parámetros a pasar
	 * @param menuIdToSelect
	 *            id del menu a seleccionar
	 * @return cadena URL
	 */
	public static String getFacesRedirectWithParametersMap(String nextPage, Map<String, String> parameters, String menuIdToSelect) {

		// Creamos la cadena con la página siguiente + faces-redirect=true
		StringBuilder page = new StringBuilder(nextPage);
		page.append("?").append(Constantes.FACES_REDIRECT).append("=true");

		// añadimos los parámetros codificados a la URL a pasar
		Iterator<Entry<String, String>> it = parameters.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			page.append("&").append(entry.getKey()).append("=").append(entry.getValue());
		}
		// añadimos el id de la página en la que nos encontramos
		if (menuIdToSelect != null) {
//			page.append("&").append(WSConstants.MENU_ID_TO_SELECT).append("=").append(menuIdToSelect);
		}
		return page.toString();
	}

	
	
	/**
	 * Persiste un atributo en la request
	 * 
	 * @param atributeName
	 *            nombre del atributo
	 * @param attribute
	 *            atributo
	 */
	public static void setRequestAttribute(String atributeName, Object attribute) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		request.setAttribute(atributeName, attribute);
	}

	/**
	 * Devuelve un atributo de la request
	 * 
	 * @param atributeName
	 *            nombre del atributo
	 * @return atributo requerido
	 */
	public static Object getRequestAttribute(String atributeName) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return request.getAttribute(atributeName);
	}

}