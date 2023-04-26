package com.yeogi_jeogi.myPage;

import java.util.LinkedList;

import com.yeogi_jeogi.board.board;
import com.yeogi_jeogi.board.boardComment;
import com.yeogi_jeogi.login.CHANGEABLEUSERS;
import com.yeogi_jeogi.reservation.reservation;

public interface myPageDAO {	
	public myPage getMyInfo(int lUserNum) throws Exception;
	public LinkedList<myPageTravel> getMyInfoTravel(int lUserNum) throws Exception;
	public LinkedList<myPagePackage> getMyInfoPackage(int lUserNum) throws Exception;
	public LinkedList<myPageFestival> getMyInfoFestival(int lUserNum) throws Exception;
	public int getMaxPageNum(int lUserNum, boolean isBF) throws Exception;
	public LinkedList<board> getMyInfoWrites(int pageNum, int lUserNum, int startNum, boolean isBF) throws Exception;
	public LinkedList<boardComment> getMyAllComments(int lUserNum, int startNum, int pageNum)  throws Exception;
	public int getComsPageNum(int lUserNum) throws Exception;
	public LinkedList<reservation> getMyAllReservation(int rsvPageNum, int startNum, int lUserNum) throws Exception;
	public int getRsvPageNum(int lUserNum) throws Exception;
	public boolean checkMyWritesUserNum(boolean isBf, int bUserNum) throws Exception;
	public void updateChangeableInfo(CHANGEABLEUSERS user) throws Exception;
	public CHANGEABLEUSERS getMyChangeableInfo(String id) throws Exception;
}
