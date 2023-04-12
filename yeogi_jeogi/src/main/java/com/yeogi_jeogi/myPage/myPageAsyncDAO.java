package com.yeogi_jeogi.myPage;

public interface myPageAsyncDAO {
	public void deleteMyReservation(int rRsvNum) throws Exception;
	public void deleteMyComs(boolean IsBF, long cNum) throws Exception;
	public void deleteMyWrites(boolean IsBF, int bNum) throws Exception;
}
