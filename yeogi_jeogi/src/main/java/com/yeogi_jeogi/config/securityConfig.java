package com.yeogi_jeogi.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class securityConfig {
	
	@Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		/* @formatter:off */
		http
			.csrf().disable()
			.cors().disable()
			.headers().frameOptions().sameOrigin().and()
//			.authenticationManager(new AuthenticationManager() {
//				@Override
//				public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//					String rawPwd = (String) authentication.getCredentials();
//					String encodePwd = passwordEncoder().encode(rawPwd);
//					System.out.println("비교들어감");
//					if (!passwordEncoder().matches(rawPwd, encodePwd)) {
//						throw new BadCredentialsException("비밀번호 틀림!");
//					}					
//					return new customAuthenticationToken((String) authentication.getName(), (String) authentication.getCredentials(), "");
//				}
//			})
			.authorizeRequests()
				.antMatchers("/mypage","/mypage/**", "/board/free/write", "/board/travel/write", "/mypage/coms/delete/**").hasAnyAuthority("ROLE_USER", "ROLE_MANAGER") //유저, 관리자 로그인 시 사용가능 페이지
				.antMatchers("/board/notice/write", "/board/event/write").hasRole("MANAGER") //관리자 로그인 시 사용가능 페이지
				.antMatchers("/", "/main", "/loginform", "/board/**","/membership/add", "/products", "/membership/**", "/festival",
									"/festival/list", "/travel/**").permitAll() // 설정한 리소스의 접근을 인증절차 없이 허용
				.antMatchers("/css/**", "/images/**", "/js/**", "/smarteditor/**", "/uploadImages/**").permitAll() //static 요소들
				.anyRequest().authenticated() // 그 외 모든 리소스를 의미하며 인증 필요
				.and()
			.formLogin()
				.loginPage("/loginform") // 기본 로그인 페이지
				.loginProcessingUrl("/login/send")
				.usernameParameter("lUserId")
				.passwordParameter("lPassword")
				.successHandler( // 로그인 성공 핸들러
					new AuthenticationSuccessHandler() { // 익명 객체 사용
						@Override
						public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
							String uri = (String) request.getSession().getAttribute("preUri");
							if (uri != null && !uri.contains("mypage"))
								response.sendRedirect(uri);
							else
								response.sendRedirect("/main");
						}
					})
				.failureHandler( // 로그인 실패 후 핸들러
					new AuthenticationFailureHandler() { // 익명 객체 사용
						@Override
						public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
							response.sendRedirect("/loginform");
						}
					})
				.permitAll()
				.and()
				.logout()
					.permitAll()
					.logoutUrl("/logout") // 로그아웃 URL (기본 값 : /logout)
					.logoutSuccessHandler(new LogoutSuccessHandler() {
						@Override
						public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
								throws IOException, ServletException {
							response.sendRedirect(request.getHeader("Referer"));
						}
					})
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 주소창에 요청해도 포스트로 인식하여 로그아웃
					.deleteCookies("JSESSIONID") // 로그아웃 시 JSESSIONID 제거
					.invalidateHttpSession(true) // 로그아웃 시 세션 종료
					.clearAuthentication(true); // 로그아웃 시 권한 제거
		
		return http.build();
		/* @formatter:on */
	}

}