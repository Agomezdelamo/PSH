package com.otv.constants.enumerados;

public enum RolesEnum {
	
	ADMIN(1),
	USUARIO(2),
	INVITADO(3);
	
	private int id;
	
	/*el constructor de un enum es privado*/
	RolesEnum(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.name().toString();
	}
}
