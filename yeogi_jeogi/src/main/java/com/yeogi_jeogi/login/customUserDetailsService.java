package com.yeogi_jeogi.login;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class customUserDetailsService implements UserDetailsService {
	private final UserRepository repository;
	private final PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		USERS principal = repository.findByID(username)
				.orElseThrow(() -> {
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다! :"+username);
				});
		return new loginUserData(principal);
	}
	@Transactional
	public USERS updateMyInfo(USERS user) {
		USERS persistance = repository.findByID(user.getID()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패");
		});
		System.out.println("user 비밀번호"+user.getPASSWORD()+"persistance 비밀번호"+persistance.getPASSWORD());
		if (user.getPASSWORD() != null && !user.getPASSWORD().isEmpty()) {
			System.out.println("비밀번호 발견");
			persistance.setPASSWORD(encoder.encode(user.getPASSWORD()));
		}
		if (user.getEMAIL() != null && !user.getEMAIL().isEmpty()) {
			System.out.println("이메일 발견");
			persistance.setEMAIL(user.getEMAIL());
		}
		if (user.getIMG_ADD() != null && !user.getIMG_ADD().isEmpty()) {
			System.out.println("이미지 발견");
			persistance.setIMG_ADD(user.getIMG_ADD());
		}
		user.setPASSWORD(persistance.getPASSWORD());
		user.setEMAIL(persistance.getEMAIL());
		user.setIMG_ADD(persistance.getIMG_ADD());
		return persistance;
	}
}
