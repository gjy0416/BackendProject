package com.yeogi_jeogi.login;

public class loginUserChangeableData  {
	private CHANGEABLEUSERS user;
	
	public loginUserChangeableData(CHANGEABLEUSERS user) {
		this.user = user;
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

}
