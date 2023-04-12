package com.yeogi_jeogi.reservation;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class reservationController {
	final reservationDAO dao;
	
	@Autowired
	public reservationController(reservationDAO dao) {
		this.dao = dao;
	}
	@GetMapping("/reservation")
	public String loginPage(HttpSession Session) {
		if (Session.getAttribute("userInfo") == null) {
			return "reservation/reservation";
		}
		return "reservation/reservation";
	}
}