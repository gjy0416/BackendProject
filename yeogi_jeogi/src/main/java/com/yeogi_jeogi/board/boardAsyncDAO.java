package com.yeogi_jeogi.board;

public interface boardAsyncDAO {
	public void addWrite(String boardType, board bd) throws Exception;
	public void addComment(String boardType, boardComment bdc) throws Exception;
	public void insertRcm(String boardType, int bNum, int lUserNum) throws Exception;
	public boolean checkRcm(String boardType, int bNum, int lUserNum) throws Exception;
}
