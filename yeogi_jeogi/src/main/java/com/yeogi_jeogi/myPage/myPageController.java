package com.yeogi_jeogi.myPage;

import java.security.Principal;
import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.core.task.TaskRejectedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yeogi_jeogi.board.board;
import com.yeogi_jeogi.board.boardComment;
import com.yeogi_jeogi.login.USERS;
import com.yeogi_jeogi.login.loginUserData;
import com.yeogi_jeogi.reservation.reservation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class myPageController {
	final myPageService service;
	
	@GetMapping("/mypage")
	public String myPagePage(@AuthenticationPrincipal loginUserData principal, HttpSession Session, Model m) {
		return myPageWrites_bf(principal, 1, Session, m);
	}

	@GetMapping("/mypage/wants")
	public String myPageWants(@AuthenticationPrincipal loginUserData principal, HttpSession session, Model m) {
		if (principal != null) {
			try {
				myPage mp = service.myPageNService().getMyInfo(principal.getlUserNum());
				LinkedList<myPageTravel> mpt = service.myPageNService().getMyInfoTravel(principal.getlUserNum());
				LinkedList<myPagePackage> mpp = service.myPageNService().getMyInfoPackage(principal.getlUserNum());
				LinkedList<myPageFestival> mpf = service.myPageNService().getMyInfoFestival(principal.getlUserNum());

				session.setAttribute("userMyPageInfo", mp);
				session.setAttribute("userMyPageTravelInfo", mpt);
				session.setAttribute("userMyPagePackageInfo", mpp);
				session.setAttribute("userMyPageFestivalInfo", mpf);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "myPage/myPageWants";
		}
		return "myPage/myPageFail";
	}

	@GetMapping("/mypage/bfwrites")
	public String myPageWrites_bf(@AuthenticationPrincipal loginUserData principal, HttpSession session, Model m, Principal pc) {
		return myPageWrites_bf(principal, 1, session, m);
	}

	@GetMapping("/mypage/bfwrites/{startNum}")
	public String myPageWrites_bf(@AuthenticationPrincipal loginUserData principal, @PathVariable int startNum, HttpSession session, Model m) {
		if (principal != null) {
			try {
				myPage mp = service.myPageNService().getMyInfo(principal.getlUserNum());
				int pageNum = 8;
				LinkedList<board> mpwBF = service.myPageNService().getMyInfoWrites(pageNum, principal.getlUserNum(), startNum, true);
				session.setAttribute("userMyPageInfo", mp);
				session.setAttribute("userMyPageBfWritesList", mpwBF);
				int maxPageNum_bf = service.myPageNService().getMaxPageNum(principal.getlUserNum(), true);
				session.setAttribute("MaxPage_bf", maxPageNum_bf);
				session.setAttribute("myWritePageNum", pageNum);
				session.setAttribute("myPageWriteNumColor", startNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "myPage/myPageWrites_bf";
		}
		return "myPage/myPageFail";
	}

	@GetMapping("/mypage/btwrites")
	public String myPageWrites_bt(@AuthenticationPrincipal loginUserData principal, HttpSession session, Model m) {
		return myPageWrites_bt(principal, 1, session, m);
	}

	@GetMapping("/mypage/btwrites/{startNum}")
	public String myPageWrites_bt(@AuthenticationPrincipal loginUserData principal, @PathVariable int startNum, HttpSession session, Model m) {
		if (principal != null) {
			try {
				myPage mp = service.myPageNService().getMyInfo(principal.getlUserNum());
				int pageNum = 8;
				LinkedList<board> mpwBT = service.myPageNService().getMyInfoWrites(pageNum, principal.getlUserNum(), startNum, false);
				session.setAttribute("userMyPageInfo", mp);
				session.setAttribute("userMyPageBtWritesList", mpwBT);
				int maxPageNum_bt = service.myPageNService().getMaxPageNum(principal.getlUserNum(), false);
				session.setAttribute("MaxPage_bt", maxPageNum_bt);
				session.setAttribute("myWritePageNum", pageNum);
				session.setAttribute("myPageWriteNumColor", startNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "myPage/myPageWrites_bt";
		}
		return "myPage/myPageFail";
	}

	
	@GetMapping("/mypage/bfwrites/delete/{bNum}")
	public String deleteMyBF(@AuthenticationPrincipal loginUserData principal, @PathVariable int bNum, HttpSession session) {
		if (principal != null) {
			try {
				if (service.myPageNService().checkMyWritesUserNum(true, bNum)) {
					try {
						service.myPageAsyncService().get().deleteMyWrites(true, bNum);
					}
					catch (TaskRejectedException ee) {
						return "rejectView";
					} 
					catch (Exception e2) {
						e2.printStackTrace();
					} 
					return "redirect:/mypage/bfwrites";
				} 
			}
			catch (Exception e) {
				return "myPage/myPageWrites_bf";
			}
		}
		return "myPage/myPageFail";
	}
	@GetMapping("/mypage/btwrites/delete/{bNum}")
	public String deleteMyBT(@AuthenticationPrincipal loginUserData principal, @PathVariable int bNum, HttpSession session) {
		if (principal != null) {
			try {
				if (service.myPageNService().checkMyWritesUserNum(false, bNum)) {
					try {
						service.myPageAsyncService().get().deleteMyWrites(false, bNum);
					}
					catch (TaskRejectedException ee) {
						return "rejectView";
					} 
					catch (Exception e2) {
						e2.printStackTrace();
					} 
					return "redirect:/mypage/btwrites";
				} 
			}
			catch (Exception e) {
				return "myPage/myPageWrites_bt";
			}
		}
		return "myPage/myPageFail";
	}
	@GetMapping("mypage/coms")
	public String myCommentsList(@AuthenticationPrincipal loginUserData principal, HttpSession session, Model m) {
		return myCommentsList(principal, 1, session, m);
	}

	@GetMapping("mypage/coms/{startNum}")
	public String myCommentsList(@AuthenticationPrincipal loginUserData principal, @PathVariable int startNum, HttpSession session, Model m) {
		int pageNum = 8;
		if (principal != null) {
			try {
				LinkedList<boardComment> bdc = service.myPageNService().getMyAllComments(principal.getlUserNum(), startNum, pageNum);
				session.setAttribute("myComList", bdc);
				int maxComPageNum = service.myPageNService().getComsPageNum(principal.getlUserNum());
				myPage mp = service.myPageNService().getMyInfo(principal.getlUserNum());
				session.setAttribute("userMyPageInfo", mp);
				session.setAttribute("myComPageNum", maxComPageNum);
				session.setAttribute("myComMaxPageNum", pageNum);
				session.setAttribute("myPageComsPageColor", startNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "myPage/myPageComs";
		}
		return "myPage/myPageFail";
	}

	@GetMapping("/mypage/coms/delete/{cNum}")
	public String deleteMyCom(@AuthenticationPrincipal loginUserData principal, @PathVariable long cNum, @RequestParam(value = "num") int cUserNum, HttpSession session) {
		if (principal != null) {
			if (principal.getlUserNum() == cUserNum) {
				try {
					if (String.valueOf(cNum).substring(0, 3).equals("402")) {
						service.myPageAsyncService().get().deleteMyComs(true, cNum);
					} else {
						service.myPageAsyncService().get().deleteMyComs(false, cNum);
					}
				} catch (TaskRejectedException ee) {
					return "rejectView";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "redirect:/mypage/coms";
			}
			return "/myPage/myPageNoAuth";
		}
		return "myPage/myPageFail";
	}

	@GetMapping("/mypage/reservations/{startNum}")
	public String myPageReservations(@AuthenticationPrincipal loginUserData principal, @PathVariable int startNum, HttpSession session, Model m) {
		if (principal != null) {
			try {
				int pageNum = 8;
				myPage mp = service.myPageNService().getMyInfo(principal.getlUserNum());
				session.setAttribute("userMyPageInfo", mp);
				LinkedList<reservation> rsvList = service.myPageNService().getMyAllReservation(pageNum, startNum, principal.getlUserNum());
				int rsvPageNum = service.myPageNService().getRsvPageNum(principal.getlUserNum());
				session.setAttribute("myReservations", rsvList);
				session.setAttribute("myRsvPageNum", rsvPageNum);
				session.setAttribute("myRsvMaxPageNum", pageNum);
				session.setAttribute("myPageRsvPageColor", startNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "myPage/myPageReservations";
		}
		return "myPage/myPageFail";
	}

	@GetMapping("/mypage/reservations")
	public String myPageReservations(@AuthenticationPrincipal loginUserData principal, HttpSession session, Model m) {
		return myPageReservations(principal, 1, session, m);
	}

	@GetMapping("/mypage/reservations/delete/{rRsvNum}")
	public String deleteMyReservation(@AuthenticationPrincipal loginUserData principal, @PathVariable int rRsvNum, @RequestParam(value = "rUserNum") int rUserNum, HttpSession session) {
		if (principal != null) {
			if (principal.getlUserNum() == rUserNum) {
				try {
					service.myPageAsyncService().get().deleteMyReservation(rRsvNum);
				} catch (TaskRejectedException ee) {
					return "rejectView";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "redirect:/mypage/reservations";
			}
			return "myPage/myPageNoAuth";
		}
		return "myPage/myPageFail";
	}
	@GetMapping("/mypage/infochange")
	public String infoChangePage() {
		return "myPage/myPageInfoChange";
	}
	@PostMapping("/mypage/infochange/change")
	public String infoChangeSend(@AuthenticationPrincipal loginUserData principal, @ModelAttribute USERS user, @RequestParam MultipartFile profileImg) throws Exception {
		service.myPageUpdateInfoService(principal, user, profileImg);
		return "myPage/myPageWrites_bf";
	}
}
