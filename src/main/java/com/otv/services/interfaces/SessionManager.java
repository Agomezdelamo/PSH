package com.otv.services.interfaces;

import javax.servlet.http.HttpSession;

import com.otv.model.User;

public interface SessionManager {


	public void addUserToSession(User user, HttpSession session);

	public void refreshUserInSession(User user, HttpSession session);

	public void removeUserFromSession(User user, HttpSession session);

}
