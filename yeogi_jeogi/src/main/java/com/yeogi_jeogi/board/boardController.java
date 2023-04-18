package com.yeogi_jeogi.board;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.task.TaskRejectedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeogi_jeogi.login.loginUserData;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class boardController {
	final boardService service;
	
	@GetMapping("/board/{boardType}")
	public String boardFreeList(@PathVariable String boardType, Model m) {
		return boardList(boardType, 1, m);
	}
	@GetMapping("/board/{boardType}/{startNum}")
	public String boardList(@PathVariable String boardType, @PathVariable int startNum, Model m) {
		LinkedList<board> bd;
		int pageNum = 15;
		try {
			int allPageNum = 0;
			if (boardType.equals("free") || boardType.equals("travel") || boardType.equals("notice")) {
				bd = service.boardNService().getAllList(boardType ,startNum, pageNum);
				allPageNum = service.boardNService().getMaxPageNum(boardType);
			}
			else if (boardType.equals("event")){
				return eventList(boardType, startNum, m);
			}
			else {
				return "board/boardFail";
			}
			m.addAttribute("allPageNum", allPageNum);
			m.addAttribute("boardList", bd);
			m.addAttribute("pageNum", pageNum);
			m.addAttribute("pageColor", startNum);
			m.addAttribute("boardType", boardType);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "board/boardList";
	}
	@PostMapping("/board/freesort")
	@ResponseBody
	public boolean boardSortList(Model m, @RequestBody String sort) {
		System.out.println("컨트롤러 들어옴 sort = "+sort);
		
		LinkedList<board> bd;
		int pageNum = 15;
		try {
			int allPageNum = 0;
			bd = service.boardNService().getAllList("free" ,1, pageNum, sort);
			allPageNum = service.boardNService().getMaxPageNum("free", sort);
			m.addAttribute("allPageNum", allPageNum);
			m.addAttribute("boardList", bd);
			m.addAttribute("pageNum", pageNum);
			m.addAttribute("pageColor", 1);
			m.addAttribute("boardType", "free");
			System.out.println("컨트롤러 끝");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	@GetMapping("/board/event/view/{bNum}")
	public String eventList(String boardType, @PathVariable int startNum, Model m) {
		int allPageNum = 0;
		int pageNum = 9;
		LinkedList<board> bd = null;
		try {
			bd = service.boardNService().getAllList(boardType ,startNum, pageNum);
			allPageNum = service.boardNService().getMaxPageNum(boardType);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("allPageNum", allPageNum);
		m.addAttribute("boardList", bd);
		m.addAttribute("pageNum", pageNum);
		m.addAttribute("pageColor", startNum);
		m.addAttribute("boardType", boardType);
		return "board/eventList";
	}
	@GetMapping("/board/{boardType}/view/{bNum}")
	public String getBoard(HttpSession session, @AuthenticationPrincipal loginUserData principal, @PathVariable String boardType, @PathVariable int bNum, Model m) {
		LinkedList<boardComment> bdc;
		board bd;
		try {
			if (boardType.equals("free") || boardType.equals("travel") || boardType.equals("notice") || boardType.equals("event")) {
				service.boardNService().updateCount(boardType, bNum);
				bd = service.boardNService().getBoard(boardType, bNum);
				if (bd == null)
					return "board/boardFail";
				bdc = service.boardNService().getAllCommentList(boardType, bNum);
			}
			else {
				return "board/boardFail";
			}
			if (principal != null) {
				session.setAttribute("boardUserNum", principal.getlUserNum());
			}
			m.addAttribute("boardType", boardType);
			m.addAttribute("board", bd);
			m.addAttribute("comList", bdc);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "board/boardView";
	}
	@GetMapping("/board/{boardType}/write")
	public String WritePage(@AuthenticationPrincipal loginUserData principal, @PathVariable String boardType, HttpSession session, Model m) {
		if (boardType.equals("free") || boardType.equals("travel") || boardType.equals("event") || boardType.equals("notice") ) {
			m.addAttribute("boardType", boardType);
			return "board/boardWrite";
		}
		return "board/boardFail";
	}
	@PostMapping("/board/{boardType}/write/add")
	public String addNewWrite(@AuthenticationPrincipal loginUserData principal, @PathVariable String boardType, HttpSession session, @ModelAttribute board bd) {
		try {
			if (principal == null)
				return "myPage/myPageFail";
			if (boardType.equals("free") || boardType.equals("travel") || boardType.equals("event") || boardType.equals("notice")) {
				String content = bd.getBContent().replace("\uFEFF", "");
				bd.setBContent(content);
				service.boardAsyncService().get().addWrite(boardType, bd);
			}
			else {
				return "board/boardFail";
			}
		}
		catch (TaskRejectedException ee) {
			return "rejectView";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/{boardType}";
	}
	@PostMapping("/board/{boardType}/addcom")
	public String addNewBfComment(@AuthenticationPrincipal loginUserData principal, @PathVariable String boardType, HttpSession session, @ModelAttribute boardComment bdc) {
		try {
			bdc.setCUserNum(principal.getlUserNum());
			if (boardType.equals("free") || boardType.equals("travel") || boardType.equals("event") || boardType.equals("notice")) {
				service.boardAsyncService().get().addComment(boardType, bdc);
			}
			else {
				return "board/boardFail";
			}
		}
		catch (TaskRejectedException ee) {
			return "rejectView";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/{boardType}/view/"+bdc.getCBNum();
	}
	@PostMapping("/board/{boardType}/rcm")
	@ResponseBody
	public boolean rcmUpdate(@AuthenticationPrincipal loginUserData principal, @PathVariable String boardType, @RequestBody boardRcm rcm, HttpSession session) {
		try {
			if (principal != null && principal.getlUserNum() == rcm.getrUserNum()) {
				if (service.boardAsyncService().get().checkRcm(boardType, rcm.getrBNum(), rcm.getrUserNum())) {
					service.boardAsyncService().get().insertRcm(boardType, rcm.getrBNum(), rcm.getrUserNum());
					return false;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	@GetMapping("/eventlist")
	public String eventListPage() {
		return "board/eventList";
	}
	@PostMapping("/seimgupload")
	public String sImgUpload(HttpServletRequest request) throws Exception {
		service.imageUpload(request);
		return "";
	}
}
