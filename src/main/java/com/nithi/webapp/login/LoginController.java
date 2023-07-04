package com.nithi.webapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller // web request
@SessionAttributes("name")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	AuthenticationService authenticationService;

	public LoginController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String gotoLogin() {
		
		logger.debug("login page");

		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
		if (authenticationService.authenticate(name, password)) {
			model.put("name", name);

			return "welcome";
		}

		model.put("errorMessage", "Invalid Credentials...Please try again");

		return "login";
	}

}

// http://localhost:8080/login?name=Nithish
// http://localhost:8080/login
//@RequestMapping("login") // handle "say-hello" particular request
//public String gotoLogin(@RequestParam String name, ModelMap model) {
//	
//	logger.debug("Request name is {}", name);
//	
//	model.put("name", name);
//	return "login";
//}
