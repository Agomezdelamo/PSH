package com.otv.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.otv.dao.interfaces.UserDAO;
import com.otv.model.User;

/**
 * 
 * User DAO
 * 
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */

//etiqueta de spring para que lo reconozca con su scan en spring-config y poder inyectarlo con @Autowired
@Repository("userDAO")
public class UserDAOImpl implements UserDAO, Serializable {
	
	private static final long serialVersionUID = 5833658085829025884L;
	
	@Autowired
	SessionFactory sessionFactory;

	/**
	 * Get Hibernate Session Factory
	 * 
	 * @return SessionFactory - Hibernate Session Factory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Set Hibernate Session Factory
	 * 
	 * @param SessionFactory - Hibernate Session Factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	@Override
	public void addUser(User user) {
		getSessionFactory().getCurrentSession().save(user);
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	@Override
	public void deleteUser(User user) {
		getSessionFactory().getCurrentSession().delete(user);
	}

	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	@Override
	public void updateUser(User user) {
		getSessionFactory().getCurrentSession().update(user);
	}
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	@Override
	public void saveOrUpdateUser(User user) {
		getSessionFactory().getCurrentSession().saveOrUpdate(user);
	}

	/**
	 * Get User
	 * 
	 * @param  int User Id
	 * @return User 
	 */
	@Override
	public User getUserById(int id) {
		List list = getSessionFactory().getCurrentSession()
											.createQuery("from User where id=?")
									        .setParameter(0, id).list();
		return (User)list.get(0);
	}
	
	/**
	 * Get User by dni
	 * 
	 * @param  int User Dni
	 */
	public User getUserByDni(String dni) {
		User usuarioQuery;

		List list = getSessionFactory().getCurrentSession().createQuery("from User where dni=?").setParameter(0, dni).list();

		if(list.isEmpty()) { usuarioQuery = null; }
		else { usuarioQuery = (User)list.get(0); }
	
		return usuarioQuery;
	};
	
	/**
	 * Get user by userName
	 * @param userName
	 * @return User
	 */
	public User getUserByUserName(String userName) {
		User usuarioQuery;
		
		List list = getSessionFactory().getCurrentSession().createQuery("from User where username=?").setParameter(0,userName).list();
		if(list.isEmpty()) { usuarioQuery = null; }
		else { usuarioQuery = (User)list.get(0); }
	
		return usuarioQuery;
	}
	 
	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	@Override
	public List<User> getUsers() {
		List list = getSessionFactory().getCurrentSession().createQuery("from User").list();
		return list;
	}


}
