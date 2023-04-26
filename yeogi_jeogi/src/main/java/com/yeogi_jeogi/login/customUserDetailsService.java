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
	public void updateMyInfo(String id, String pwd) {
		USERS persistance = repository.findByID(id).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패");
		});
		if (pwd != null && !pwd.isEmpty()) {
			System.out.println("비밀번호 발견");
			persistance.setPASSWORD(encoder.encode(pwd));
		}
	}
}
