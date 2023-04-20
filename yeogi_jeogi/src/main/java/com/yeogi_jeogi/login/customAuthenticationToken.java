package com.yeogi_jeogi.login;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class customAuthenticationToken extends AbstractAuthenticationToken {
	
	private static final long serialVersionUID = -6231225291766414883L;
	private USERS principal;
	private String credential;
   private String imgAdd;
   
	public customAuthenticationToken(USERS user, String pwd, String imgAdd, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = user;
		this.credential = pwd;
		this.imgAdd = imgAdd;
	}
	@Override
	public String getCredentials() {
		return credential;
	}
	@Override
	public USERS getPrincipal() {
		return principal;
	}
	public String getImgAdd() {
		return imgAdd;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	public void setPrincipal(USERS principal) {
		this.principal = principal;
	}
	public void setImgAdd(String imgAdd) {
		this.imgAdd = imgAdd;
	}
}
