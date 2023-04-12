package com.yeogi_jeogi.member;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class membershipService {
	final membershipDAOImpl dao;
	final private PasswordEncoder pwEncoder;
	
	@Async
	public void addMemberService(membership mem) throws Exception {
		mem.setmPassword(pwEncoder.encode(mem.getmPassword()));
		dao.addmember(mem);
	}
	public boolean checkIdDup(String userId) throws Exception {
		return dao.checkUserIdDup(userId); 
	}
}
