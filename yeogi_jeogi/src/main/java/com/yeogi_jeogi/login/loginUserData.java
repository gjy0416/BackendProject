package com.yeogi_jeogi.login;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class loginUserData implements UserDetails {
	private USERS user;
	
	public loginUserData(USERS user) {
		this.user = user;
	}
	public int getlUserNum() {
		return user.getUSER_NO();
	}
	public int getlGradeNum() {
		return user.getGRADE_NO();
	}
	public String getlUserId() {
		return user.getID();
	}
	public String getlName() {
		return getUsername();
	}
	public String getlEmail() {
		return user.getEMAIL();
	}
	public String getlPhone() {
		return user.getPHONE();
	}
	public String getlMbti() {
		return user.getMBTI();
	}
	public String getlImgAdd() {
		return user.getIMG_ADD();
	}
	public String getlRole() {
		return user.getROLE();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority("ROLE_"+user.getROLE()));
		return auth;
	}
	@Override
	public String getPassword() {
		return user.getPASSWORD();
	}
	@Override
	public String getUsername() {
		return user.getID();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
   public boolean isAccountNonLocked() {
        return true;
   }
   @Override
   public boolean isCredentialsNonExpired() {
        return true;
   }
   @Override
   public boolean isEnabled() {
   	return true;
   }
}
