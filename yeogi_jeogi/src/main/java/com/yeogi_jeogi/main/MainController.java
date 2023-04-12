package com.yeogi_jeogi.main;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@Autowired
	public listDAO dao = new listDAO();
	
	@GetMapping("/main")
	public String mainPage(Model m) throws Exception {
		LinkedList<listProvince> lp =  dao.getProvince();
		LinkedList<listCity> lc =  dao.getCity();
		m.addAttribute("province", lp);
		m.addAttribute("city", lc);
		return "main/main";
	}
	
	@GetMapping("/test")
	public String test() throws Exception {
		return "NewFile2";
	}
	
}
