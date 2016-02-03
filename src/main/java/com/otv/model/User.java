package com.otv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * User Entity
 * 
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
@Entity
@Table(name="usuario")
public class User implements Serializable {

	private static final long serialVersionUID = -2146509047914491778L;
	
	private int id;
	private String name;
	private String surname;
	private String dni;
	private String username;
	private String password;
	@Autowired
	private Roles rol;

	
	/**
	 * Get User Id
	 * 
	 * @return int - User Id
	 */
	@Id
	@GenericGenerator(name="kaugen" , strategy="increment")
	@GeneratedValue(generator="kaugen")
	@Column(name="ID", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	/**
	 * Set User Id
	 * 
	 * @param int - User Id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Get User Name
	 * 
	 * @return String - User Name
	 */
	@Column(name="NAME", unique = true, nullable = false)
	public String getName() {
		return name;
	}
	
	/**
	 * Set User Name
	 * 
	 * @param String - User Name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get User Surname
	 * 
	 * @return String - User Surname
	 */
	@Column(name="SURNAME", unique = true, nullable = false)
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Set User Surname
	 * 
	 * @param String - User Surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}	
	
	/**
	 * @return the dni
	 */
	@Column(name="DNI", unique = true, nullable = false)
	public String getDni() {
		return dni;
	}
	
	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	/**
	 * Get User Name
	 * 
	 * @return String - User Name
	 */
	@Column(name="USERNAME", unique = true, nullable = false)
	public String getUsername() {
		return username;
	}
	
	/**
	 * Set User Surname
	 * 
	 * @param String - UserSurname
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Get User Name
	 * 
	 * @return String - User Name
	 */
	@Column(name="PASSWORD", unique = true, nullable = false)
	public String getPassword() {
		return password;
	}
	
	/**
	 * Set Rol
	 * 
	 * @param int - rol
	 * 
	 * ojo usuario password y rol creo que estan como que pueen ser null
	 * y como que son unicos y creo que eso no es asi, cuidao.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * 0-1 muchos roles o ninguno pertenecen a un usuario
	 * 
	 * es cero a uno porque no esta mapeado en rol, normalmente se mapean las 
	 * relaciones en dos direcciones.
	 * 
	 * joincolumn indica que la columna es foreign key.
	 * 
	 * 
	 * cascade tipe all borra todo lo relacionado con esta propiedad, aqui no es conveniente,
	 * si borro un usuario quiero borrar un rol.
	 * 
	 * @ManyToOne(cascade = CascadeType.ALL)
	 * 
	 * investigar la asociación vaga(lazy)
	 * 
	 * @return
	 */
	@ManyToOne
    @JoinColumn(name = "ROL")
	public Roles getRol() {
		return rol;
	}
	
	public void setRol(Roles rol) {
		this.rol = rol;
	}
	
	@Override
	public String toString() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("id : ").append(getId());
		strBuff.append("dni : ").append(getDni());
		strBuff.append(", name : ").append(getName());
		strBuff.append(", surname : ").append(getSurname());
		return strBuff.toString();
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }


    @Override
    public int hashCode() {
        if (name != null)
			return (this.getClass().hashCode() + name.hashCode());
		else
			return super.hashCode();
    }

}
