package com.jsonarjsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import java.io.Serializable;

import com.jsonarjsf.dao.LoginDAO;
import com.jsonarjsf.util.FaceMessageHelper;
import com.jsonarjsf.util.UserPersistHelper;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1234501282834859663L;
//	private boolean isAuthenticated;
	private String user;
	private String pwd;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

//	public boolean isAuthenticated() {
//		return isAuthenticated;
//	}

//	public void setAuthenticated(Boolean isAuthenticated) {
//		HttpSession session = UserPersistHelper.getSession();
//		session.setAttribute("login", isAuthenticated);
////		this.isAuthenticated = isAuthenticated;
//	}
	
	// validate login
	// This function returns dataDiscovery string if valid username and password has been inputed. Otherwise, it will return login string.
	public String validateUserPassword() {
		boolean valid = LoginDAO.validate(user, pwd);
//		setAuthenticated(new Boolean(valid));
		if (valid) {
			HttpSession session = UserPersistHelper.getSession();
			session.setAttribute("username", user);
			System.out.println("Login button clicked, and database validation matched.");
			return "dataDiscovery?faces-redirect=true";
		} else {
			FaceMessageHelper.showFaceMessage("Incorrect Username and Password", "Please enter correct username and Password");
			return "login?faces-redirect=true";
		}
	}
	
	// logout event, invalidate session
	public String logout() {
		HttpSession session = UserPersistHelper.getSession();
		session.setAttribute("username", null);
//		setAuthenticated(new Boolean(false));
		System.out.println("session Attribute username set to null.");
		user = null;
		pwd = null;
		System.out.println("Trying to logout.");
		session.invalidate();
		return "login";
	}
}
