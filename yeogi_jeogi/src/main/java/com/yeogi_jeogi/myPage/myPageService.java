package com.yeogi_jeogi.myPage;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.Future;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yeogi_jeogi.login.CHANGEABLEUSERS;
import com.yeogi_jeogi.login.USERS;
import com.yeogi_jeogi.login.UserChangeableRepository;
import com.yeogi_jeogi.login.customUserDetailsService;
import com.yeogi_jeogi.login.loginUserData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class myPageService {
	
	private final myPageDAOImpl dao;
	private final myPageAsyncDAOImpl asyncDao;
	private final customUserDetailsService cdService;
	private final UserChangeableRepository cRepository;
	
	@Value("${yeogi_jeogi.imgs}")
	String fdir;
	
	public myPageDAO myPageNService() {
		return dao;
	}
	@Async
	public Future<myPageAsyncDAO> myPageAsyncService() {
		return new AsyncResult<myPageAsyncDAO>(asyncDao);
	}
	public CHANGEABLEUSERS getChangeableInfo(String id) throws Exception {
		return dao.getMyChangeableInfo(id);
	}
	public void myPageUpdateInfoService(loginUserData principal, CHANGEABLEUSERS cUser, String pwd, String email, String phone, String mbti, MultipartFile profileImg, HttpSession session) throws Exception {
		if (!email.equals("") || !phone.equals("") || !mbti.equals("") || profileImg != null) {
			CHANGEABLEUSERS ccUser = cRepository.findByID(principal.getUsername()).orElseThrow(() -> {
				return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다! :"+principal.getUsername());
			});
			String fileAdd = "";
			if (profileImg != null && !profileImg.isEmpty()) {
				if (!cUser.getIMG_ADD().equals("/images/myPage/none_user_img.png")) {
					File deleteFile = new File(fdir + cUser.getIMG_ADD().substring(cUser.getIMG_ADD().indexOf("/", 1)));
					if (deleteFile.delete())
						System.out.println("기존 이미지가 삭제되었습니다!");
					else
						System.out.println("기존 이미지를 찾을 수 없습니다!");
				}
				MultipartFile mFile = profileImg;
				String rdFileName = UUID.randomUUID().toString();
				String fileName = rdFileName+"."+mFile.getOriginalFilename().substring(mFile.getOriginalFilename().lastIndexOf(".")+1);
				File file = new File(fdir+"/"+fileName);
				mFile.transferTo(file);
				fileAdd = "/uploadImages/"+fileName;
				ccUser.setIMG_ADD(fileAdd);
			}
			else {
				System.out.println("이미지가 없습니다!");
			}
			if (!email.equals(""))
				ccUser.setEMAIL(email);
			if (!phone.equals(""))
				ccUser.setPHONE(phone);
			if (!mbti.equals(""))
				ccUser.setMBTI(mbti);
			cRepository.save(ccUser);
//			dao.updateChangeableInfo(ccUser);
			session.setAttribute("changeableInfo", ccUser);
		}
		if (!pwd.equals("")) {
			pwd = pwd.replace(",", "");
			cdService.updateMyInfo(principal.getUsername(), pwd);
			Authentication authentication = new UsernamePasswordAuthenticationToken(principal.getUsername(), pwd);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

}
