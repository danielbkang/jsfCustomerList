package com.jsonarjsf.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import java.io.Serializable;

import com.jsonarjsf.dao.LoginDAO;
import com.jsonarjsf.util.FaceMessageHelper;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1024801282834859663L;

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

	// validate login
	// This function returns dataDiscovery string if valid username and password has been inputted. Otherwise, it will return login string.
	public String validateUserPassword() {
		boolean valid = LoginDAO.validate(user, pwd);
		if (valid) {
			HttpSession session = UserPersistHelper.getSession();
			session.setAttribute("username", user);
			System.out.println("Login button clicked");
			return "dataDiscovery";
		} else {
			FaceMessageHelper.showFaceMessage("Incorrect Username and Password", "Please enter correct username and Password");
			return "login";
		}
	}

	// logout event, invalidate session
	public String logout() {
		HttpSession session = UserPersistHelper.getSession();
		user = "";
		pwd = "";
		System.out.println("Trying to logout.");
		session.invalidate();
		return "login";
	}
}
