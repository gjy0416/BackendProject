package com.yeogi_jeogi.member;

public interface membershipDAO {
	public void addmember(membership mem) throws Exception;
	public boolean checkUserIdDup(String userId) throws Exception;
}
