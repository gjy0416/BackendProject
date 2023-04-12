package com.yeogi_jeogi.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.yeogi_jeogi.jdbc.connClass;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public class boardDAOImpl implements boardDAO {
	@Override
	public int getMaxPageNum(String boardType) throws Exception {
		return getMaxPageNum(boardType, "");
	}
	@Override
	public int getMaxPageNum(String boardType, String sort) throws Exception {
		int result;
		Connection conn = connClass.open();
		String innerSql = "";
		String sql;
		if (boardType.equals("free")) {
			if (sort.equals("자유"))
				innerSql = "WHERE SORT = '자유' ";
			else if (sort.equals("후기"))
				innerSql = "WHERE SORT = '후기' ";
			else if (sort.equals("여행 추천"))
				innerSql = "WHERE SORT = '여행 친구 찾기' ";
			sql = "SELECT COUNT(1) cnt FROM BOARD_FREE "+innerSql;
		}
		else if (boardType.equals("travel"))
			sql = "SELECT COUNT(1) cnt FROM BOARD_TRAVEL";
		else if (boardType.equals("notice"))
			sql = "SELECT COUNT(1) cnt FROM BOARD_NOTICE";
		else 
			sql = "SELECT COUNT(1) cnt FROM BOARD_EVENT";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		result = rs.getInt("cnt");
		connClass.close(conn, pstmt, rs);
		
		return result;
	}
	@Override
	public LinkedList<board> getAllList(String boardType, int startNum, int pageNum) throws Exception {
		return getAllList(boardType, startNum,pageNum, "");
	}
	@Override
	public LinkedList<board> getAllList(String boardType, int startNum, int pageNum, String sort) throws Exception {
		LinkedList<board> list = new LinkedList<board>();
		System.out.println("보드 타입"+boardType);
		System.out.println("분류"+sort);
		String innerSql = "";		
		String sql;
		
		if (boardType.equals("free")) {
			if (sort.equals("자유"))
				innerSql = "WHERE SORT = '자유' ";
			else if (sort.equals("후기"))
				innerSql = "WHERE SORT = '후기' ";
			else if (sort.equals("여행 추천"))
				innerSql = "WHERE SORT = '여행 친구 찾기' ";
			sql = "SELECT A.BRD_NO bNum, (SELECT ID FROM USERS WHERE USER_NO = A.USER_NO) bUserId, A.SORT bSort, A.TITLE bTitle, A.COUNT bCount, TO_CHAR(A.CREATE_DATE,'YY/MM/DD hh:mi') bCDate, bRcm, bUserGradeImgAdd "
					+ "								FROM (SELECT ROWNUM RN, C.*, (SELECT IMG_ADD FROM LIST_GRADE WHERE F = GRADE_NO) bUserGradeImgAdd "
					+ "									FROM (SELECT B.*,"
					+ "					                         (SELECT COUNT(1) FROM RCM_BRD_FREE A WHERE A.BRD_NO = B.BRD_NO) bRcm,"
					+ "                                                (SELECT GRADE_NO FROM USERS WHERE USER_NO = B.USER_NO) F "
					+ "					                         FROM BOARD_FREE B "
																			 +innerSql
					+ "					                         ORDER BY BRD_NO DESC) C) A "
					+ "								WHERE RN BETWEEN ? AND ?";
		}
		else if (boardType.equals("travel"))
			sql = "SELECT A.BRD_NO bNum, (SELECT ID FROM USERS WHERE USER_NO = A.USER_NO) bUserId, A.TITLE bTitle, A.COUNT bCount, TO_CHAR(A.CREATE_DATE,'YY/MM/DD hh:mi') bCDate, bRcm, bUserGradeImgAdd "
					+ "								FROM (SELECT ROWNUM RN, C.*, (SELECT IMG_ADD FROM LIST_GRADE WHERE F = GRADE_NO) bUserGradeImgAdd "
					+ "									FROM (SELECT B.*,"
					+ "					                         (SELECT COUNT(1) FROM RCM_BRD_TRV A WHERE A.BRD_NO = B.BRD_NO) bRcm,"
					+ "                                                (SELECT GRADE_NO FROM USERS WHERE USER_NO = B.USER_NO) F "
					+ "					                         FROM BOARD_TRAVEL B "
					+ "					                         ORDER BY BRD_NO DESC) C) A "
					+ "								WHERE RN BETWEEN ? AND ?";
		else if (boardType.equals("notice"))
			sql = "SELECT A.BRD_NO bNum, (SELECT ID FROM USERS WHERE USER_NO = A.USER_NO) bUserId, A.TITLE bTitle, A.COUNT bCount, TO_CHAR(A.CREATE_DATE,'YY/MM/DD hh:mi') bCDate, bRcm, bUserGradeImgAdd "
					+ "								FROM (SELECT ROWNUM RN, C.*, (SELECT IMG_ADD FROM LIST_GRADE WHERE F = GRADE_NO) bUserGradeImgAdd "
					+ "									FROM (SELECT B.*,"
					+ "					                         (SELECT COUNT(1) FROM RCM_BRD_NOTICE A WHERE A.BRD_NO = B.BRD_NO) bRcm,"
					+ "                                                (SELECT GRADE_NO FROM USERS WHERE USER_NO = B.USER_NO) F "
					+ "					                         FROM BOARD_NOTICE B "
					+ "					                         ORDER BY BRD_NO DESC) C) A "
					+ "								WHERE RN BETWEEN ? AND ?";
		else 
			sql = "SELECT A.BRD_NO bNum, (SELECT ID FROM USERS WHERE USER_NO = A.USER_NO) bUserId, A.TITLE bTitle, A.COUNT bCount, TO_CHAR(A.CREATE_DATE,'YY/MM/DD hh:mi') bCDate, A.IMG_LIST_ADD bImgListAdd, bRcm, bUserGradeImgAdd "
					+ "								FROM (SELECT ROWNUM RN, C.*, (SELECT IMG_ADD FROM LIST_GRADE WHERE F = GRADE_NO) bUserGradeImgAdd "
					+ "									FROM (SELECT B.*,"
					+ "					                         (SELECT COUNT(1) FROM RCM_BRD_EVENT A WHERE A.BRD_NO = B.BRD_NO) bRcm,"
					+ "                                                (SELECT GRADE_NO FROM USERS WHERE USER_NO = B.USER_NO) F "
					+ "					                         FROM BOARD_EVENT B "
					+ "					                         ORDER BY BRD_NO DESC) C) A "
					+ "								WHERE RN BETWEEN ? AND ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = connClass.open();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, (startNum -1) * pageNum +1);
		pstmt.setInt(2, startNum * pageNum);
		rs = pstmt.executeQuery();

		try {
			while (rs.next()) {
				board bd = new board();
				String subNum = (String.valueOf(rs.getInt("bNum"))).substring(3);
				bd.setBNum(rs.getInt("bNum"));
				bd.setBSubNum(Integer.parseInt(subNum));
				bd.setBUserId(rs.getString("bUserId"));
				if (boardType.equals("event"))
					bd.setBImgListAdd(rs.getString("bImgListAdd"));
				if (boardType.equals("free"))
					bd.setBSort(rs.getString("bSort"));
				bd.setBTitle(rs.getString("bTitle"));
				bd.setBCount(rs.getInt("bCount"));
				bd.setBCDate(rs.getString("bCDate"));
				bd.setBRcm(rs.getInt("bRcm"));
				bd.setBUserGradeImgAdd(rs.getString("bUserGradeImgAdd"));
				list.add(bd);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		connClass.close(conn, pstmt, rs);
		return list; 
	}
	@Override
	public board getBoard(String boardType, int bNum) throws SQLException {
		Connection conn = connClass.open();
		board bd = new board();
		String sql;
		if (boardType.equals("free"))
			sql = "SELECT BRD_NO bNum, (SELECT ID FROM USERS WHERE B.USER_NO = USER_NO) bUserId, SORT bSort, TITLE bTitle, CONTENT bContent, IMG_ADD bImgAdd,"
											+ "COUNT bCount, TO_CHAR(CREATE_DATE,'YY/MM/DD hh:mi') bCDate, TO_CHAR(MODIFY_DATE,'YY/MM/DD hh:mi') bMDate, bRcm, (SELECT IMG_ADD FROM LIST_GRADE WHERE F = GRADE_NO) bUserGradeImgAdd "
										+ "FROM (SELECT BRD_NO, USER_NO, SORT, TITLE, CONTENT, IMG_ADD, COUNT, CREATE_DATE, MODIFY_DATE,"
											+ "(SELECT COUNT(1) FROM RCM_BRD_FREE WHERE BRD_NO = A.BRD_NO) bRcm, (SELECT GRADE_NO FROM USERS WHERE USER_NO = A.USER_NO) F "
											+ "FROM BOARD_FREE A "
											+ "WHERE BRD_NO = ?) B";
		else if (boardType.equals("travel"))
			sql = "SELECT BRD_NO bNum, (SELECT ID FROM USERS WHERE B.USER_NO = USER_NO) bUserId, TITLE bTitle, CONTENT bContent, IMG_ADD bImgAdd,"
											+ "COUNT bCount, TO_CHAR(CREATE_DATE,'YY/MM/DD hh:mi') bCDate, TO_CHAR(MODIFY_DATE,'YY/MM/DD hh:mi') bMDate, bRcm, (SELECT IMG_ADD FROM LIST_GRADE WHERE F = GRADE_NO) bUserGradeImgAdd "
										+ "FROM (SELECT BRD_NO, USER_NO, TITLE, CONTENT, IMG_ADD, COUNT, CREATE_DATE, MODIFY_DATE,"
											+ "(SELECT COUNT(1) FROM RCM_BRD_TRV WHERE BRD_NO = A.BRD_NO) bRcm, (SELECT GRADE_NO FROM USERS WHERE USER_NO = A.USER_NO) F "
											+ "FROM BOARD_TRAVEL A "
											+ "WHERE BRD_NO = ?) B";
		else if (boardType.equals("notice"))
			sql = "SELECT BRD_NO bNum, (SELECT ID FROM MANAGERS WHERE B.MGR_NO = MGR_NO) bMgrId, TITLE bTitle, CONTENT bContent, IMG_ADD bImgAdd,"
											+ "COUNT bCount, TO_CHAR(CREATE_DATE,'YY/MM/DD hh:mi') bCDate, TO_CHAR(MODIFY_DATE,'YY/MM/DD hh:mi') bMDate, bRcm, (SELECT IMG_ADD FROM LIST_GRADE WHERE F = GRADE_NO) bUserGradeImgAdd "
										+ "FROM (SELECT BRD_NO, MGR_NO, TITLE, CONTENT, IMG_ADD, COUNT, CREATE_DATE, MODIFY_DATE,"
											+ "(SELECT COUNT(1) FROM RCM_BRD_NOTICE WHERE BRD_NO = A.BRD_NO) bRcm, (SELECT GRADE_NO FROM USERS WHERE USER_NO = A.USER_NO) F "
											+ "FROM BOARD_NOTICE A "
											+ "WHERE BRD_NO = ?) B";
		else
			sql = "SELECT BRD_NO bNum, (SELECT ID FROM MANAGERS WHERE B.MGR_NO = MGR_NO) bMgrId, IMG_LIST_ADD bImgListAdd, TITLE bTitle, CONTENT bContent, IMG_ADD bImgAdd,"
											+ "COUNT bCount, TO_CHAR(CREATE_DATE,'YY/MM/DD hh:mi') bCDate, TO_CHAR(MODIFY_DATE,'YY/MM/DD hh:mi') bMDate, bRcm, (SELECT IMG_ADD FROM LIST_GRADE WHERE F = GRADE_NO) bUserGradeImgAdd "
										+ "FROM (SELECT BRD_NO, MGR_NO, IMG_LIST_ADD, TITLE, CONTENT, IMG_ADD, COUNT, CREATE_DATE, MODIFY_DATE,"
											+ "(SELECT COUNT(1) FROM RCM_BRD_EVENT WHERE A.BRD_NO = BRD_NO) bRcm, (SELECT GRADE_NO FROM USERS WHERE USER_NO = A.USER_NO) F "
											+ "FROM BOARD_EVENT A "
											+ "WHERE BRD_NO = ?) B";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bNum);
		ResultSet rs = pstmt.executeQuery();
		
		if (!rs.next()) {
			return null;
		}
		try (conn; pstmt; rs;) {
			bd.setBNum(rs.getInt("bNum"));
			if (boardType.equals("free") || boardType.equals("travel"))
				bd.setBUserId(rs.getString("bUserId"));
			else
				bd.setBMgrId(rs.getString("bMgrId"));
			if (boardType.equals("event"))
				bd.setBImgListAdd(rs.getString("bImgListAdd"));
			if (boardType.equals("free"))
				bd.setBSort(rs.getString("bSort"));
			bd.setBTitle(rs.getString("bTitle"));
			bd.setBContent(rs.getString("bContent"));
			if (rs.getString("bImgAdd") != null) {
				bd.setBImgAdd(rs.getString("bImgAdd"));
			}
			else {
				bd.setBImgAdd("");
			}
			bd.setBCount(rs.getInt("bCount"));
			bd.setBCDate(rs.getString("bCDate"));
			if (rs.getString("bMDate") != null) {
				bd.setBMDate(rs.getString("bMDate"));
			}
			else {
				bd.setBMDate("");
			}
			bd.setBRcm(rs.getInt("bRcm"));
			bd.setBUserGradeImgAdd(rs.getString("bUserGradeImgAdd"));
		}	
		connClass.close(conn, pstmt, rs);
		return bd;
	}
	@Override
	public LinkedList<boardComment> getAllCommentList(String boardType, int cBNum) throws Exception {
		LinkedList<boardComment> list = new LinkedList<boardComment>();
		String sql;
		if (boardType.equals("free"))
			sql = "SELECT COM_NO cNum, BRD_NO cBNum, USER_NO cUserNum, (SELECT ID FROM USERS WHERE USER_NO = A.USER_NO) cUserId, CONTENT cContent, G_COUNT cGCount, TO_CHAR(CREATE_DATE, 'YY/MM/DD hh:mi') cCDate,"
							+ "(SELECT C.IMG_ADD FROM USERS B, LIST_GRADE C WHERE B.USER_NO = A.USER_NO AND B.USER_NO = C.GRADE_NO) cUserGradeImgAdd "
						+ "FROM COMMENTS_BRD_FREE A "
						+ "WHERE BRD_NO = ? "
						+ "ORDER BY cGCount";
		else if (boardType.equals("travel"))
			sql = "SELECT COM_NO cNum, BRD_NO cBNum, USER_NO cUserNum, (SELECT ID FROM USERS WHERE USER_NO = A.USER_NO) cUserId, CONTENT cContent, G_COUNT cGCount, TO_CHAR(CREATE_DATE, 'YY/MM/DD hh:mi') cCDate,"
							+ "(SELECT C.IMG_ADD FROM USERS B, LIST_GRADE C WHERE B.USER_NO = A.USER_NO AND B.USER_NO = C.GRADE_NO) cUserGradeImgAdd "
						+ "FROM COMMENTS_BRD_TRV A "
						+ "WHERE BRD_NO = ? "
						+ "ORDER BY cGCount";
		else if (boardType.equals("notice"))
			sql = "SELECT COM_NO cNum, BRD_NO cBNum, USER_NO cUserNum, (SELECT ID FROM USERS WHERE USER_NO = A.USER_NO) cUserId, CONTENT cContent, G_COUNT cGCount, TO_CHAR(CREATE_DATE, 'YY/MM/DD hh:mi') cCDate,"
							+ "(SELECT C.IMG_ADD FROM USERS B, LIST_GRADE C WHERE B.USER_NO = A.USER_NO AND B.USER_NO = C.GRADE_NO) cUserGradeImgAdd "
						+ "FROM COMMENTS_BRD_NOTICE A "
						+ "WHERE BRD_NO = ? "
						+ "ORDER BY cGCount";
		else
			sql = "SELECT COM_NO cNum, BRD_NO cBNum, USER_NO cUserNum, (SELECT ID FROM USERS WHERE USER_NO = A.USER_NO) cUserId, CONTENT cContent, G_COUNT cGCount, TO_CHAR(CREATE_DATE, 'YY/MM/DD hh:mi') cCDate,"
							+ "(SELECT C.IMG_ADD FROM USERS B, LIST_GRADE C WHERE B.USER_NO = A.USER_NO AND B.USER_NO = C.GRADE_NO) cUserGradeImgAdd "
						+ "FROM COMMENTS_BRD_EVENT A "
						+ "WHERE BRD_NO = ? "
						+ "ORDER BY cGCount";

		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, cBNum);
		ResultSet rs = pstmt.executeQuery();

		try(conn; pstmt; rs) {
			while (rs.next()) {
				boardComment bdc = new boardComment();
				bdc.setCNum(rs.getLong("cNum"));
				bdc.setCBNum(rs.getInt("cBNum"));
				bdc.setCUserNum(rs.getInt("cUserNum"));
				bdc.setCUserId(rs.getString("cUserId"));
				bdc.setCContent(rs.getString("cContent"));
				bdc.setCGCount(rs.getInt("cGCount"));
				bdc.setCCDate(rs.getString("cCDate"));
				bdc.setCUserGradeImgAdd(rs.getString("cUserGradeImgAdd"));
				list.add(bdc);
			}
		}
		connClass.close(conn, pstmt, rs);
		return list; 
	}
	@Override
	public LinkedList<board> searchListAll(String boardType, String sSort, String sWord) throws Exception {
		final int searchPageNum = 5;
		LinkedList<board> list = new LinkedList<board>();
		String chooseSQL;
		
		if (sSort.equals("title")) {
			chooseSQL = "WHERE TITLE LIKE ? ORDER BY bNum DESC";
		}
		else if (sSort.equals("content")) {
			chooseSQL = "WHERE CONTENT LIKE ? ORDER BY bNum DESC";
		}
		else if (sSort.equals("id")) {
			chooseSQL = "WHERE (SELECT ID FROM USERS WHERE USER_NO = A.USER_NO) LIKE ? ORDER BY bNum DESC";
		}
		else {
			chooseSQL = "WHERE TITLE LIKE ? "
						+ "OR CONTENT LIKE ? "
						+ "OR (SELECT ID FROM USERS WHERE USER_NO = A.USER_NO) LIKE ? "
						+ "ORDER BY bNum DESC";
		}
		String sql;
		if (boardType.equals("free"))
			sql = "SELECT bNum, bTitle, bSort, bContent, bImgAdd, bCount, bCDate, bMDate, bUserId, bRcm "
							+ "FROM (SELECT ROWNUM RN, bNum, bTitle, bSort, bContent, bImgAdd, bCount, bCDate, bMDate, bUserId, bRcm "
								+ "FROM (SELECT BRD_NO bNum, TITLE bTitle, SORT bSort, CONTENT bContent, IMG_ADD bImgAdd, COUNT bCount,"
								+ "CREATE_DATE bCDate, MODIFY_DATE bMDate, (SELECT NAME FROM USERS WHERE USER_NO = A.USER_NO) bUserId, (SELECT COUNT(1) FROM RCM_BRD_FREE WHERE BRD_NO = A.BRD_NO) bRcm "
								+ "FROM BOARD_FREE A "+chooseSQL+")) "
							+ "WHERE RN BETWEEN 1 AND ?";
		else if (boardType.equals("travel"))
			sql = "SELECT bNum, bTitle, bContent, bImgAdd, bCount, bCDate, bMDate, bUserId, bRcm "
							+ "FROM (SELECT ROWNUM RN, bNum, bTitle, bContent, bImgAdd, bCount, bCDate, bMDate, bUserId, bRcm "
								+ "FROM (SELECT BRD_NO bNum, TITLE bTitle, CONTENT bContent, IMG_ADD bImgAdd, COUNT bCount,"
								+ "CREATE_DATE bCDate, MODIFY_DATE bMDate, (SELECT NAME FROM USERS WHERE USER_NO = A.USER_NO) bUserId, (SELECT COUNT(1) FROM RCM_BRD_TRV WHERE BRD_NO = A.BRD_NO) bRcm "
								+ "FROM BOARD_TRAVEL A "+chooseSQL+")) "
							+ "WHERE RN BETWEEN 1 AND ?";
		else if (boardType.equals("notice"))
			sql = "SELECT bNum, bTitle, bContent, bImgAdd, bCount, bCDate, bMDate, bMgrId, bRcm "
							+ "FROM (SELECT ROWNUM RN, bNum, bTitle, bContent, bImgAdd, bCount, bCDate, bMDate, bMgrId, bRcm "
								+ "FROM (SELECT BRD_NO bNum, TITLE bTitle, CONTENT bContent, IMG_ADD bImgAdd, COUNT bCount,"
								+ "CREATE_DATE bCDate, MODIFY_DATE bMDate, (SELECT NAME FROM MANAGERS WHERE MGR_NO = A.MGR_NO) bMgrId, (SELECT COUNT(1) FROM RCM_BRD_NOTICE WHERE BRD_NO = A.BRD_NO) bRcm "
								+ "FROM BOARD_NOTICE A "+chooseSQL+")) "
							+ "WHERE RN BETWEEN 1 AND ?";
		else
			sql = "SELECT bNum, bImgListAdd, bTitle, bContent, bImgAdd, bCount, bCDate, bMDate, bMgrId, bRcm "
							+ "FROM (SELECT ROWNUM RN, bNum, bImgListAdd, bTitle, bContent, bImgAdd, bCount, bCDate, bMDate, bMgrId, bRcm "
								+ "FROM (SELECT BRD_NO bNum, IMG_LIST_ADD bImgListAdd, TITLE bTitle, CONTENT bContent, IMG_ADD bImgAdd, COUNT bCount,"
								+ "CREATE_DATE bCDate, MODIFY_DATE bMDate, (SELECT NAME FROM MANAGERS WHERE MGR_NO = A.MGR_NO) bMgrId, (SELECT COUNT(1) FROM RCM_BRD_EVENT WHERE BRD_NO = A.BRD_NO) bRcm "
								+ "FROM BOARD_EVENT A "+chooseSQL+")) "
						+ "WHERE RN BETWEEN 1 AND ?";

		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (sSort.equals("all")){
			pstmt.setString(1, sWord);
			pstmt.setString(2, sWord);
			pstmt.setString(3, sWord);
			pstmt.setInt(4, searchPageNum);
		}
		else {
			pstmt.setString(1, sWord);
			pstmt.setInt(2, searchPageNum);
		}
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			board bd = new board();
			bd.setBNum(rs.getInt("bNum"));
			String subNum = (String.valueOf(rs.getInt("bNum"))).substring(3);
			bd.setBSubNum(Integer.parseInt(subNum));
			bd.setBSort(rs.getString("bSort"));
			bd.setBUserId(rs.getString("bUserId"));
			bd.setBTitle(rs.getString("bTitle"));
			bd.setBContent(rs.getString("bContent"));
			bd.setBImgAdd(null);
			bd.setBCount(rs.getInt("bCount"));
			bd.setBCDate(rs.getString("bCDate"));
			bd.setBMDate(rs.getString("bMDate"));
			bd.setBRcm(rs.getInt("bRcm"));
			
			list.add(bd);
		}
		connClass.close(conn, pstmt, rs);
		return list;
	}
	@Override
	public void updateCount(String boardType, int bNum) throws Exception {
		String sql;
		if (boardType.equals("free"))
			sql = "UPDATE BOARD_FREE SET COUNT = COUNT +1 WHERE BRD_NO = ?";
		else if (boardType.equals("travel"))
			sql = "UPDATE BOARD_TRAVEL SET COUNT = COUNT +1 WHERE BRD_NO = ?";
		else if (boardType.equals("notice"))
			sql = "UPDATE BOARD_NOTICE SET COUNT = COUNT +1 WHERE BRD_NO = ?";
		else
			sql = "UPDATE BOARD_EVENT SET COUNT = COUNT +1 WHERE BRD_NO = ?";
		
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bNum);
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
}
