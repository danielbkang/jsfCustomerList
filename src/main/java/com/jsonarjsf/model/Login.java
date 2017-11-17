package com.jsonarjsf.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

import com.jsonarjsf.dao.LoginDAO;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

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
			return "dataDiscovery";
		} else {
			return "login";
		}
	}

	// logout event, invalidate session
	public String logout() {
		return "login";
	}
}
