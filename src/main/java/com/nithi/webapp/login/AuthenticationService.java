package com.nithi.webapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	
	public boolean authenticate(String name,String password) {
		boolean isValidName = name.equalsIgnoreCase("nithish");
		boolean isValidPassword = password.equalsIgnoreCase("1234");
		 return isValidName && isValidPassword;
	}

}
