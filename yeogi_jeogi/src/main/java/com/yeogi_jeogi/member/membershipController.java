package com.yeogi_jeogi.member;

import org.springframework.core.task.TaskRejectedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeogi_jeogi.login.loginUserData;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class membershipController {
	final membershipService service;
	
	@PostMapping("/membership/add")
	public String addmembership(@AuthenticationPrincipal loginUserData principal, @ModelAttribute membership mem, Model m) {
		if (principal == null) {
			try {
				service.addMemberService(mem);
			}
			catch (TaskRejectedException ee) {
				return "rejectView";
			}
			catch (Exception e) {
				e.printStackTrace();
				return "login/membershipFail";
			}
			return "member/membershipSuccess";
		}
		return "rejectView";
	}
	@PostMapping("/membership/check")
	@ResponseBody
	public boolean checkIdDup(@RequestBody String mUserId) throws Exception {
		if (service.checkIdDup(mUserId)) {
			return true;
		}
		return false;
	}
}
