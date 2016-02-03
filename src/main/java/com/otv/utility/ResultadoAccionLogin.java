package com.otv.utility;

import java.io.Serializable;

import com.otv.constants.enumerados.CodigoRespuestaEnum;

public class ResultadoAccionLogin implements Serializable {

	private static final long serialVersionUID = -1389274184545037710L;
	
	private CodigoRespuestaEnum codigoRespuesta;

	public CodigoRespuestaEnum getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(CodigoRespuestaEnum codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
}
