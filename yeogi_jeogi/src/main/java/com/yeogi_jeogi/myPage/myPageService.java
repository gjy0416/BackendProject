package com.yeogi_jeogi.myPage;

import java.io.File;
import java.util.UUID;
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
		System.out.println("service들왔을때 user 비밀번호"+user.getPASSWORD());
		String fileAdd = "";
		if (profileImg != null && !profileImg.isEmpty()) {
			if (!principal.getlImgAdd().equals("/images/myPage/none_user_img.png")) {
				File deleteFile = new File(fdir + principal.getlImgAdd().substring(principal.getlImgAdd().indexOf("/", 1)));
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
			user.setIMG_ADD(fileAdd);
		}
		else {
			System.out.println("이미지가 없습니다!");
		}
		user.setID(principal.getlUserId());
		user.setPASSWORD(user.getPASSWORD().replace(",", ""));
		System.out.println("들가기전 셋id하고 user비밀번호"+user.getID()+user.getPASSWORD());
		USERS user2 = cdService.updateMyInfo(user);
		System.out.println("들간 후셋id하고 user비밀번호"+user.getID()+user.getPASSWORD());
	}

}
