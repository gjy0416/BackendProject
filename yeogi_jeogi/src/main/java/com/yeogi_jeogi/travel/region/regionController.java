package com.yeogi_jeogi.travel.region;

import java.util.ArrayList;  

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yeogi_jeogi.travel.region.regionDAO;

import lombok.AllArgsConstructor;
 
@AllArgsConstructor
@Controller
public class regionController {
	private regionDAO rDao;
	
	@GetMapping("/travel/region/{cityNum}")
	public String regionPage(@PathVariable int cityNum, Model m) throws Exception {
		return regionPage(cityNum, 1, m);
	}
	@GetMapping("/travel/region/{cityNum}/{startNum}")
	public String regionPage(@PathVariable int cityNum, @PathVariable int startNum, Model m) throws Exception {
		int pageNum = 15;
		int allPageNum = rDao.getRegionPageNum(cityNum);
		ArrayList<region> list = rDao.getRegionTravelList(cityNum, startNum, pageNum);
		m.addAttribute("regionTravelList", list);
		m.addAttribute("allPageNum", allPageNum);
		m.addAttribute("pageNum", pageNum);
		m.addAttribute("pageColor", startNum);
		return "travel/region/region";
	}
}
