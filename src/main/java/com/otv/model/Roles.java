package com.otv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
@Table(name="roles")
public class Roles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -956375316873920543L;
	
	private int rolesId;
	private String rolDesc;

	
	/**
	 * Get User Id
	 * 
	 * @return int - User Id
	 */
	@Id
	@GenericGenerator(name="kaugen" , strategy="increment")
	@GeneratedValue(generator="kaugen")
	@Column(name="ROLES_ID", unique = true, nullable = false)
	public int getRolesId() {
		return rolesId;
	}
	
	/**
	 * Set User Id
	 * 
	 * @param int - User Id
	 */
	public void setRolesId(int rolesId) {
		this.rolesId = rolesId;
	}
	
	@Column(name="ROLDESC", unique = true, nullable = false)
	public String getRolDesc() {
		return rolDesc;
	}

	public void setRolDesc(String rolDesc) {
		this.rolDesc = rolDesc;
	}

	@Override
	public String toString() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("ROLES_ID : ").append(getRolesId());
		strBuff.append("rolDesc : ").append(getRolDesc());
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
        if (rolDesc != null)
			return (this.getClass().hashCode() + rolDesc.hashCode());
		else
			return super.hashCode();
    }

}
