package com.otv.dao.interfaces;

import java.util.List;

import com.otv.model.User;

/**
 * 
 * User DAO Interface
 * 
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
public interface UserDAO {

	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	public void addUser(User user);
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	public void updateUser(User user);
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	public void saveOrUpdateUser(User user);
	
	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	public void deleteUser(User user);
	
	/**
	 * Get User by id
	 * 
	 * @param  id
	 * @return User
	 */
	public User getUserById(int id);
	
	
	/**
	 * Get User by dni
	 * 
	 * @param  dni
	 * @return User
	 */
	public User getUserByDni(String dni);
	
	/**
	 * Get user by userName
	 * 
	 * @param userName
	 * @return User
	 */
	public User getUserByUserName(String userName);
	
	/**
	 * Get User List
	 * 
	 */
	public List<User> getUsers();
}
