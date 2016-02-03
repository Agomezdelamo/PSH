package com.otv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otv.dao.interfaces.UserDAO;
import com.otv.model.User;
import com.otv.services.interfaces.UserService;

/**
 * 
 * User Service
 * 
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */

//etiqueta de spring para que lo reconozca con su scan en spring-config y poder inyectarlo con @Autowired
@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	// UserDAO is injected...
	@Autowired
	UserDAO userDAO;
	
	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	@Override
	public void addUser(User user) {
		userDAO.addUser(user);
	}

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	@Override
	public void deleteUser(User user) {
		userDAO.deleteUser(user);
	}
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	@Transactional(readOnly = false)
	@Override
	public void saveOrUpdateUser(User user) {
		userDAO.saveOrUpdateUser(user);
	}
	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 */
	@Override
	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}

	/**
	 * Get User
	 * 
	 * @param  int User Dni
	 */
	@Override
	public User getUserByDni(String dni) {
		return userDAO.getUserByDni(dni);
	}

	/**
	 * Get user by name
	 * 
	 * @param name
	 * @return User
	 */
	public User getUserByUserName(String userName) {
		return userDAO.getUserByUserName(userName);
	};

	
	/**
	 * Get User List
	 * 
	 */
	@Override
	public List<User> getUsers() {	
		return userDAO.getUsers();
	}

	/**
	 * Get User DAO
	 * 
	 * @return UserDAO - User DAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * Set User DAO
	 * 
	 * @param UserDAO - User DAO
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
