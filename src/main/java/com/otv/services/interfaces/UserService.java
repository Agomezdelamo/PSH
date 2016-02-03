package com.otv.services.interfaces;

import java.util.List;

import com.otv.model.User;

/**
 * 
 * User Service Interface
 * 
 * @author onlinetechvision.com
 * @since 25 Mar 2012
 * @version 1.0.0
 *
 */
public interface UserService {
	
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
	 * Get User
	 * 
	 * @param  int User Id
	 */
	public User getUserById(int id);

	/**
	 * Get User
	 * 
	 * @param  int User Id
	 */
	public User getUserByDni(String id);
	
	/**
	 * Get user by name
	 * 
	 * @param name
	 * @return User
	 */
	public User getUserByUserName(String userName);	
	
	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	public List<User> getUsers();
}
