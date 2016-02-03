package com.otv.constants.enumerados;

public enum CodigoRespuestaEnum {
	
	OK(1,"Logado correctamente."),
	ERROR(0,"Error de logado.");
	
	/**
	 * ResponseCode Identifier
	 */
	private int id;

	/**
	 * Response Description
	 */
	private String result;
	
	CodigoRespuestaEnum(int id, String result) {
		this.id = id;
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
