package com.yeogi_jeogi.board;

import java.sql.SQLException;
import java.util.LinkedList;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

public interface boardDAO {
	public int getMaxPageNum(String boardType, String sort) throws Exception;
	public int getMaxPageNum(String boardType) throws Exception;
	public LinkedList<board> getAllList(String boardType, int startNum, int pageNum, String sort) throws Exception;
	public LinkedList<board> getAllList(String boardType, int startNum, int pageNum) throws Exception;
	public board getBoard(String boardType, int bNum) throws SQLException;
	public LinkedList<boardComment> getAllCommentList(String boardType, int cBNum) throws Exception;
	public LinkedList<board> searchListAll(String boardType, String sSort, String sWord) throws Exception;
	public void updateCount(String boardType, int bNum) throws Exception;
}
