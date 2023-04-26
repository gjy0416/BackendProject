package com.yeogi_jeogi.login;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class customAuthenticationToken extends AbstractAuthenticationToken {
	
	private static final long serialVersionUID = -6231225291766414883L;
	private Object principal;
	private String credential;
   private String imgAdd;
   
	public customAuthenticationToken(Object user, String pwd, String imgAdd) {
		super(null);
		this.principal = user;
		this.credential = pwd;
		this.imgAdd = imgAdd;
	}
	@Override
	public String getCredentials() {
		return credential;
	}
	@Override
	public Object getPrincipal() {
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
