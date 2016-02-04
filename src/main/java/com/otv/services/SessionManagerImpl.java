package com.otv.services;

import javax.faces.context.FacesContext;
import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.otv.constants.Constantes;
import com.otv.model.User;
import com.otv.services.interfaces.SessionManager;

@Service("sessionManager")
public class SessionManagerImpl implements SessionManager {



	@Override
	public void addUserToSession(User user, HttpSession session) {
		refreshUserInSession(user, session);
	}

	@Override
	public void refreshUserInSession(User user, HttpSession session) {
		// Se guarda en el contexto de JSF
		session.setAttribute(Constantes.PARAM_USER, user);

//		// Se guardan en sesión los permisos de visibilidad del usuario
//		session.setAttribute(Constantes.PARAM_VISIBILITY_PERMISSIONS, visibilityPermissionsService.getMyPermissions(user));
//
//		// Se guardan en sesión los permisos de visibilidad del ususario en sesión sobre el usuario seleccionado, en este caso soy yo mismo
//		session.setAttribute(Constantes.PARAM_VISIBILITY_PERMISSIONS_OVER_USER, visibilityPermissionsService.getUserPermissions(user, user));
//
//		// Se guarda en la sesion la cuenta del usuario (ACCOUNT_ADMIN)
//		if ((user.getRole() != null) && (user.getRole().getId() == Role.ACCOUNT_ADMIN_ID)) {
//			session.setAttribute(Constantes.PARAM_CURRENT_ACCOUNT_ID, user.getManagedAccount().getId());
//		}
//
//		// Se guarda en la sesion la unidad organizativa del usuario (ORGANIZATIONAL_UNIT_ADMIN)
//		if ((user.getRol() != null) && (user.getRole().getId() == Role.ORGANIZATIONAL_UNIT_ADMIN_ID || SecurityChecker.isSpecialist())) {
//			session.setAttribute(Constantes.PARAM_CURRENT_ORGANIZATIONALUNIT_ID, user.getOrganizationalUnit().getId());
//		}

	}

	@Override
	public void removeUserFromSession(User user, HttpSession session) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.removeAttribute("loginBean");

		request.removeAttribute(Constantes.PARAM_USER);

		session.invalidate();
	}

}
