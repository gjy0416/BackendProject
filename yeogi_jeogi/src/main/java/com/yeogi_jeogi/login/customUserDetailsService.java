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
		String rawPwd = user.getPASSWORD();
		System.out.println("pwd : "+user.getPASSWORD()+"email : "+user.getEMAIL()+"img_add :"+user.getIMG_ADD());
		if (rawPwd != null && !rawPwd.isEmpty())
			persistance.setPASSWORD(encoder.encode(rawPwd));
		if (user.getEMAIL() != null && !user.getEMAIL().isEmpty())
			persistance.setEMAIL(user.getEMAIL());
		if (user.getIMG_ADD() != null && !user.getIMG_ADD().isEmpty())
			persistance.setIMG_ADD(user.getIMG_ADD());
		return user;
	}
}
