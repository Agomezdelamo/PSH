package com.otv.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.otv.dao.interfaces.LoginDAO;
import com.otv.model.User;

@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public User findUserByLoginData(String username) {
		List listaReturn = getSessionFactory().getCurrentSession().createQuery("from User where username=?").setParameter(0, username).list();
		return (User)listaReturn.get(0);
	};

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
