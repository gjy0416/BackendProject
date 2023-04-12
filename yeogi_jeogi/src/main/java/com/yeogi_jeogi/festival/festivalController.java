package com.yeogi_jeogi.festival;

import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class festivalController {

	public festivalDAO dao = new festivalDAO();
	
    @GetMapping("/festival")
    public String festivalPage(Model m, @RequestParam(value="page", defaultValue="1") int page, 
    		@RequestParam(value="lcName", defaultValue="") String lcName) throws Exception { //, 
    	LinkedList<festival> fval =  dao.getSearchFestival(lcName);
    	m.addAttribute("festival", fval);
    	m.addAttribute("totalCnt", fval.size());
    	m.addAttribute("page", page);
    	m.addAttribute("lastPage", (int) Math.ceil((double) fval.size() / 5));

		return "festival/festival";
    }

	/*
	 * @GetMapping("/festival/list") public String board(Model model) {
	 * model.addAttribute("viewAll", boardService.viewAll()); return
	 * "festival/boardList"; }
	 */

	 @GetMapping("/festival/list") 
	 public String boardList(HttpSession Session) {
		 return "festival/boardList"; 
	 }

}