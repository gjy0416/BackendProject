package com.yeogi_jeogi.travel.season;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yeogi_jeogi.travel.season.seasonDAO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class seasonController {
	private seasonDAO sDao;
	
	@GetMapping("/travel/season/{seasonNum}")
	public String seasonPage(@PathVariable int seasonNum, Model m) throws Exception {
		return seasonPage(seasonNum, 1, m);
	}
	@GetMapping("/travel/season/{seasonNum}/{startNum}")
	public String seasonPage(@PathVariable int seasonNum, @PathVariable int startNum, Model m) throws Exception {
		int pageNum = 15;
		int allPageNum = sDao.getSeasonPageNum(seasonNum);
		ArrayList<season> list = sDao.getSeasonTravelList(seasonNum, startNum, pageNum);
		m.addAttribute("seasonTravelList", list);
		m.addAttribute("allPageNum", allPageNum);
		m.addAttribute("pageNum", pageNum);
		m.addAttribute("pageColor", startNum);
		return "travel/season/season";
	}
}
