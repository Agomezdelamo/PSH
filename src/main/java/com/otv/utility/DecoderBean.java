package com.otv.utility;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.jasypt.util.text.BasicTextEncryptor;

import com.otv.controller.springBeans.RequestManager;


public class DecoderBean implements Serializable {

	private static final long serialVersionUID = 1521164342925718849L;


	/**
	 * Atributo para encriptar los parámetros
	 */
	private transient BasicTextEncryptor textEncryptor;


	/**
	 * Constructor por defecto
	 */
	public DecoderBean() {
		textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getId());
	}

	/**
	 * Codifica el valor recibido por parámetros
	 * 
	 * @param value
	 *            valor a codificar
	 * @return cadena de texto codificada
	 */
	public String encode(String value) {
		String encodedString = textEncryptor.encrypt(value);
		try {
			encodedString = URLEncoder.encode(encodedString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERROR: Se ha producido un error en la codificacion... "+ e);
		}

		return encodedString;
	}


	/**
	 * Decodifica el valor recibido por parámetros
	 * 
	 * @param value valor a decodificar
	 * 
	 * @return cadena de texto decodificada
	 */
	public String decode(String value) {
		String decodedString = value;

		decodedString = textEncryptor.decrypt(decodedString);
		return decodedString;

	}

}
