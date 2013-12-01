package com.cheetah.core.shared.rpc;

import net.customware.gwt.dispatch.shared.Action;

import com.cheetah.core.shared.models.Person;

/**
 * LoginUser which will be served as action in RPC communication, corresponding
 * to result {@link LoginResult}
 * 
 * @author Kobe Sun
 * 
 */
public class LoginAction implements Action<LoginResult> {

	private static final long serialVersionUID = -7541443368424711160L;

	private Person user;

	@SuppressWarnings("unused")
	private LoginAction() {
	}

	public LoginAction(String userName, String password) {
		this.user = new Person(userName, password);
	}

	public String getUserName() {
		return user.getUsername();
	}

	public String getPassword() {
		return user.getPassword();
	}

	public String toString() {
		return user.getUsername() + ":" + user.getPassword();
	}

	public Person getUser() {
		return user;
	}

}
