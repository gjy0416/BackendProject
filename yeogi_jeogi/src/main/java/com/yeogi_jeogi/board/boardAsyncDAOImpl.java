package com.yeogi_jeogi.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.yeogi_jeogi.jdbc.connClass;

@Repository
public class boardAsyncDAOImpl implements boardAsyncDAO {
	@Override
	public void addWrite(String boardType, board bd) throws Exception {
		String sql;
		if (boardType.equals("free"))
			sql = "INSERT INTO BOARD_FREE VALUES(SEQ_BOARD_FREE.NEXTVAL, (SELECT USER_NO FROM USERS WHERE ID = ?), ?, ?, ?, NULL, DEFAULT, DEFAULT, NULL)";
		else if (boardType.equals("travel"))
			sql = "INSERT INTO BOARD_TRAVEL VALUES(SEQ_BOARD_TRAVEL.NEXTVAL, (SELECT USER_NO FROM USERS WHERE ID = ?), ?, ?, NULL, DEFAULT, DEFAULT, NULL)";
		else if (boardType.equals("notice"))
			sql = "INSERT INTO BOARD_NOTICE VALUES(SEQ_BOARD_NOTICE.NEXTVAL, (SELECT MGR_NO FROM MANAGERS WHERE ID = ?), ?, ?, NULL, DEFAULT, DEFAULT, NULL)";
		else 
			sql = "INSERT INTO BOARD_EVENT VALUES(SEQ_BOARD_EVENT.NEXTVAL, (SELECT MGR_NO FROM MANAGERS WHERE ID = ?), ?, ?, ?, NULL, DEFAULT, DEFAULT, NULL)";
		
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try (conn; pstmt) {
			if (boardType.equals("notice") || boardType.equals("event")) {
				pstmt.setString(1, bd.getBMgrId());
			}
			else {
				pstmt.setString(1, bd.getBUserId());
			}
			pstmt.setString(2, bd.getBTitle());
			if (boardType.equals("free")) {
				pstmt.setString(3, bd.getBSort());
				pstmt.setString(4, bd.getBContent());
			}
			else if (boardType.equals("event")){
				pstmt.setString(3, bd.getBImgListAdd());
				pstmt.setString(4, bd.getBContent());
			}
			else {
				pstmt.setString(3, bd.getBContent());
			}
			pstmt.executeUpdate();
		}
		connClass.close(conn, pstmt, null);
	}
	@Override
	public void addComment(String boardType, boardComment bdc) throws Exception {
		String sql;
		if (boardType.equals("free"))
			sql = "INSERT INTO COMMENTS_BRD_FREE VALUES (SEQ_COMMENTS_BRD_FREE.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, NULL)";
		else if (boardType.equals("travel"))
			sql = "INSERT INTO COMMENTS_BRD_TRV VALUES (SEQ_COMMENTS_BRD_TRV.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, NULL)";
		else if (boardType.equals("notice"))
			sql = "INSERT INTO COMMENTS_BRD_NOTICE VALUES (SEQ_COMMENTS_BRD_NOTICE.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, NULL)";
		else
			sql = "INSERT INTO COMMENTS_BRD_EVENT VALUES (SEQ_COMMENTS_BRD_EVENT.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, NULL)";
		
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try (conn; pstmt) {
			pstmt.setInt(1, bdc.getCBNum());
			pstmt.setInt(2, bdc.getCUserNum());
			pstmt.setString(3, bdc.getCContent());
			pstmt.executeQuery();
		}
		pstmt.close();
		conn.close();
	}
	@Override
	public void insertRcm(String boardType, int bNum, int lUserNum) throws Exception {
		String sql;
		if (boardType.equals("free"))
			sql = "INSERT INTO RCM_BRD_FREE VALUES (SEQ_RCM_BRD_FREE.NEXTVAL, ?, ?)";
		else if (boardType.equals("travel"))
			sql = "INSERT INTO RCM_BRD_TRV VALUES (SEQ_RCM_BRD_TRV.NEXTVAL, ?, ?)";
		else if (boardType.equals("notice"))
			sql = "INSERT INTO RCM_BRD_NOTICE VALUES (SEQ_RCM_BRD_NOTICE.NEXTVAL, ?, ?)";
		else
			sql = "INSERT INTO RCM_BRD_EVENT VALUES (SEQ_RCM_BRD_EVENT.NEXTVAL, ?, ?)";
		
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bNum);
		pstmt.setInt(2, lUserNum);
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	@Override
	public boolean checkRcm(String boardType, int bNum, int lUserNum) throws Exception {
		String sql;
		if (boardType.equals("free"))
			sql = "SELECT COUNT(1) cnt FROM RCM_BRD_FREE WHERE BRD_NO = ? AND USER_NO = ?";
		else if (boardType.equals("travel"))
			sql = "SELECT COUNT(1) cnt FROM RCM_BRD_TRV WHERE BRD_NO = ? AND USER_NO = ?";
		else if (boardType.equals("notice"))
			sql = "SELECT COUNT(1) cnt FROM RCM_BRD_NOTICE WHERE BRD_NO = ? AND USER_NO = ?";
		else
			sql = "SELECT COUNT(1) cnt FROM RCM_BRD_EVENT WHERE BRD_NO = ? AND USER_NO = ?";

		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bNum);
		pstmt.setInt(2, lUserNum);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		if (rs.getInt("cnt") == 0) {
			return true;
		}
		return false;
	}
}
