package com.yeogi_jeogi.travel.mbti;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class mbtiController {
	private mbtiDAO mDao;
	
	@GetMapping("/travel/mbti/{mbtiNum}")
	public String mbtiPage(@PathVariable int mbtiNum, Model m) throws Exception {
		return mbtiPage(mbtiNum, 1, m);
	}
	@GetMapping("/travel/mbti/{mbtiNum}/{startNum}")
	public String mbtiPage(@PathVariable int mbtiNum, @PathVariable int startNum, Model m) throws Exception {
		int pageNum = 15;
		int allPageNum = mDao.getmbtiPageNum(mbtiNum);
		ArrayList<mbti> list = mDao.getmbtilist(mbtiNum, startNum, pageNum);
		m.addAttribute("regionTravelList", list);
		m.addAttribute("allPageNum", allPageNum);
		m.addAttribute("pageNum", pageNum);
		m.addAttribute("pageColor", startNum);
		return "travel/mbti/mbti";
	}
}
