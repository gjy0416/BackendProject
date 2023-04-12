package com.yeogi_jeogi.myPage;

import java.io.File;
import java.util.Random;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yeogi_jeogi.login.USERS;
import com.yeogi_jeogi.login.customUserDetailsService;
import com.yeogi_jeogi.login.loginUserData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class myPageService {
	
	private final myPageDAOImpl dao;
	private final myPageAsyncDAOImpl asyncDao;
	private final customUserDetailsService cdService;
	private final AuthenticationManager authenticationManager;
	
	@Value("${yeogi_jeogi.imgs}")
	String fdir;
	
	public myPageDAO myPageNService() {
		return dao;
	}
	@Async
	public Future<myPageAsyncDAO> myPageAsyncService() {
		return new AsyncResult<myPageAsyncDAO>(asyncDao);
	}
	public void myPageUpdateInfoService(loginUserData principal, USERS user, MultipartFile profileImg) throws Exception {
		String fileAdd = "";
		if (profileImg != null) {
			MultipartFile mFile = profileImg;
			String rdFileName = randomFileName();
			String fileName = rdFileName+"."+mFile.getOriginalFilename().substring(mFile.getOriginalFilename().lastIndexOf(".")+1);
//			fileNameList().add(fileName);
			File file = new File(fdir+"/"+fileName);
			mFile.transferTo(file);
			fileAdd = "/uploadImages/"+fileName;
			user.setIMG_ADD(fileAdd);
		}
		else {
			System.out.println("이미지가 없습니다!");
		}
		user.setID(principal.getlUserId());
		user.setUSER_NO(principal.getlUserNum());
		cdService.updateMyInfo(user);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getID(), principal.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	private String randomFileName() {
		Random rd = new Random();
		String [] stringList = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a", "b"};
		String fileName = "";
		int fileNameLength = 10;
		for (int i = 0; i < fileNameLength; i++) {
			int randomNum = rd.nextInt(stringList.length);
			fileName += stringList[randomNum];
		}
		return fileName;
	}
//	@Bean
//	public ArrayList<String> fileNameList() {
//		return new ArrayList<String>();
//	}

}
