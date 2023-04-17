package com.yeogi_jeogi.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
	
	@GetMapping("/loginform")
	public String loginPage(@AuthenticationPrincipal loginUserData principal, HttpServletRequest request) {
		if (principal != null)
			return "login/allogin";
		String uri = request.getHeader("Referer");
		if (uri != null && !uri.contains("/login"))
			request.getSession().setAttribute("preUri", uri);
		return "login/login";
	}

}

