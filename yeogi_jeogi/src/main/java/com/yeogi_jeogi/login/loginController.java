package com.yeogi_jeogi.login;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
	
	@GetMapping("/loginform")
	public String loginPage(@AuthenticationPrincipal loginUserData principal) {
		if (principal != null)
			return "login/allogin";
		return "login/login";
	}

}

