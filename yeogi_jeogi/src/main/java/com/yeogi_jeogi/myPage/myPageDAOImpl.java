package com.yeogi_jeogi.myPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;

import org.springframework.stereotype.Repository;

import com.yeogi_jeogi.board.board;
import com.yeogi_jeogi.board.boardComment;
import com.yeogi_jeogi.jdbc.connClass;
import com.yeogi_jeogi.login.CHANGEABLEUSERS;
import com.yeogi_jeogi.reservation.reservation;

@Repository
public class myPageDAOImpl implements myPageDAO {
	@Override
	public myPage getMyInfo(int lUserNum) throws Exception{
		myPage mp = new myPage();
		String sql = "SELECT (SELECT COUNT(1) FROM WANTS_TRAVEL WHERE USER_NO = ?) mpTravels,"
				+ "        (SELECT COUNT(1) FROM WANTS_PACKAGE WHERE USER_NO = ?) mpPackages,"
				+ "        (SELECT COUNT(1) FROM WANTS_FESTIVAL WHERE USER_NO = ?) mpFestivals,"
				+ "        ((SELECT COUNT(1) FROM BOARD_FREE WHERE USER_NO = ?) + (SELECT COUNT(1) FROM BOARD_TRAVEL WHERE USER_NO = ?)) mpWrites,"
				+ "        ((SELECT COUNT(1) FROM COMMENTS_BRD_FREE WHERE USER_NO = ?) + (SELECT COUNT(1) FROM COMMENTS_BRD_TRV WHERE USER_NO = ?)) mpComments,"
				+ "        (SELECT COUNT(1) FROM RESERVATIONS WHERE USER_NO = ?) mpReservations "
				+ "    FROM DUAL";
		
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (int i = 1; i <= 8; i++) {
			pstmt.setInt(i,lUserNum);
		}
		
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		mp.setMpTravels(rs.getInt("mpTravels"));
		mp.setMpPackages(rs.getInt("mpPackages"));
		mp.setMpFestivals(rs.getInt("mpFestivals"));
		mp.setMpWrites(rs.getInt("mpWrites"));
		mp.setMpComments(rs.getInt("mpComments"));
		mp.setMpReservations(rs.getInt("mpReservations"));
		
		connClass.close(conn, pstmt, rs);
		return mp;
	}
	@Override
	public LinkedList<myPageTravel> getMyInfoTravel(int lUserNum) throws Exception {
		LinkedList<myPageTravel> list = new LinkedList<myPageTravel>();
		String sql = "SELECT B.WTRV_NO mptWNum, A.TRAVEL_NO mptTNum, (SELECT D.NAME FROM LIST_REGION C, LIST_CITY D WHERE C.REGION_ID = A.REGION_ID AND C.CITY_ID = D.CITY_ID) mptRName, A.NAME mptName, A.IMG_ADD mptImgAdd,"
							+ "(SELECT COUNT FROM LIST_TRAVEL WHERE A.TRAVEL_NO = TRAVEL_NO) mptCount, (SELECT COUNT(1) FROM RCM_TRAVEL WHERE TRAVEL_NO = A.TRAVEL_NO) mptRcm "
				+ "FROM LIST_TRAVEL A, WANTS_TRAVEL B "
				+ "WHERE B.USER_NO = ? "
				+ "AND B.TRAVEL_NO = A.TRAVEL_NO "
				+ "ORDER BY mptWNum ASC";
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, lUserNum);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			myPageTravel mpt = new myPageTravel();
			mpt.setMptWNum(rs.getLong("mptWNum"));
			mpt.setMptTNum(rs.getInt("mptTNum"));
			mpt.setMptRName(rs.getString("mptRName"));
			mpt.setMptName(rs.getString("mptName"));
			mpt.setMptImgAdd(rs.getString("mptImgAdd"));
			mpt.setMptCount(rs.getInt("mptCount"));
			mpt.setMptRcm(rs.getInt("mptRcm"));
			list.add(mpt);
		}
		connClass.close(conn, pstmt, rs);
		
		return list;
	}
	@Override
	public LinkedList<myPagePackage> getMyInfoPackage(int lUserNum) throws Exception {
		LinkedList<myPagePackage> list = new LinkedList<myPagePackage>();
		
		String sql = "SELECT B.WPKG_NO mppWNum, A.PKG_NO mppPNum, (SELECT D.NAME FROM LIST_REGION C, LIST_CITY D WHERE C.REGION_ID = A.REGION_ID AND D.CITY_ID = C.CITY_ID) mppRName,"
							+ "A.TITLE mppTitle, A.SORT mppSort, A.IMG_ADD mppImgAdd,"
							+ "A.COUNT mppCount, A.PRICE mppPrice, (SELECT COUNT(1) FROM RCM_PACKAGES WHERE A.PKG_NO = PKG_NO) mppRcm "
							+ "FROM PACKAGES A, WANTS_PACKAGE B "
							+ "WHERE B.USER_NO = ? "
							+ "AND B.PKG_NO = A.PKG_NO "
							+ "ORDER BY mppWNum ASC";
		
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, lUserNum);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			myPagePackage mpp = new myPagePackage();
			mpp.setMppWNum(rs.getLong("mppWNum"));
			mpp.setMppPNum(rs.getInt("mppPNum"));
			mpp.setMppRName(rs.getString("mppRName"));
			mpp.setMppTitle(rs.getString("mppTitle"));
			mpp.setMppSort(rs.getString("mppSort"));
			mpp.setMppImgAdd(rs.getString("mppImgAdd"));
			mpp.setMppCount(rs.getInt("mppCount"));
			mpp.setMppPrice(rs.getInt("mppPrice"));
			mpp.setMppRcm(rs.getInt("mppRcm"));
			list.add(mpp);
		}
		connClass.close(conn, pstmt, rs);
		
		return list;
	}
	@Override
	public LinkedList<myPageFestival> getMyInfoFestival(int lUserNum) throws Exception {
		LinkedList<myPageFestival> list = new LinkedList<myPageFestival>();
		
		String sql = "SELECT B.WFEST_NO mpfWNum, A.FEST_NO mpfFNum, (SELECT C.NAME FROM LIST_CITY C, LIST_REGION D WHERE C.CITY_ID = D.CITY_ID AND D.REGION_ID = A.REGION_ID) mpfRName,"
							+ "A.TITLE mpfTitle, A.IMG_ADD mpfImgAdd,"
							+ "A.START_DATE mpfStartDate, A.END_DATE mpfEndDate,"
							+ "(SELECT COUNT(1) FROM RCM_FESTIVALS WHERE FEST_NO = A.FEST_NO) mpfRcm "
							+ "FROM FESTIVALS A, WANTS_FESTIVAL B "
							+ "WHERE B.USER_NO = ? "
							+ "AND B.FEST_NO = A.FEST_NO "
							+ "ORDER BY mpfWNum ASC";
		
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, lUserNum);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			myPageFestival mpf = new myPageFestival();
			mpf.setMpfWNum(rs.getLong("mpfWNum"));
			mpf.setMpfFNum(rs.getInt("mpfFNum"));
			mpf.setMpfRName(rs.getString("mpfRName"));
			mpf.setMpfTitle(rs.getString("mpfTitle"));
			mpf.setMpfImgAdd(rs.getString("mpfImgAdd"));
			mpf.setMpfStartDate(rs.getString("mpfStartDate"));
			mpf.setMpfEndDate(rs.getString("mpfEndDate"));
			mpf.setMpfRcm(rs.getInt("mpfRcm"));
			list.add(mpf);
		}
		connClass.close(conn, pstmt, rs);
		
		return list;
	}
	@Override
	public int getMaxPageNum(int lUserNum, boolean isBF) throws Exception {
		int result;
		Connection conn = connClass.open();
		String sql;
		if (isBF) {
			sql = "SELECT COUNT(1) cnt FROM BOARD_FREE WHERE USER_NO = ?";
		}
		else {
			sql = "SELECT COUNT(1) cnt FROM BOARD_TRAVEL WHERE USER_NO = ?";
		}
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, lUserNum);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		result = rs.getInt("cnt");
		connClass.close(conn, pstmt, rs);
		
		return result;
	}
	@Override
	public LinkedList<board> getMyInfoWrites(int pageNum, int lUserNum, int startNum, boolean isBF) throws Exception {
		LinkedList<board> list = new LinkedList<board>();
		String sql;
		if (isBF) {
			sql = "SELECT BRD_NO, (SELECT ID FROM USERS WHERE USER_NO = C.USER_NO) bUserId, SORT, TITLE, CONTENT, IMG_ADD, COUNT, TO_CHAR(CREATE_DATE, 'YY/MM/DD hh:mm') CDate, MODIFY_DATE, bRcm "
					+ "    FROM (SELECT ROWNUM RN, B.* "
					+ "            FROM (SELECT A.*, (SELECT COUNT(1) FROM RCM_BRD_FREE WHERE BRD_NO = A.BRD_NO) bRcm FROM BOARD_FREE A WHERE USER_NO = ? ORDER BY BRD_NO DESC) B) C"
					+ "    WHERE RN BETWEEN ? AND ?";
		}
		else {
			sql = "SELECT BRD_NO, (SELECT ID FROM USERS WHERE USER_NO = C.USER_NO) bUserId, TITLE, CONTENT, IMG_ADD, COUNT, TO_CHAR(CREATE_DATE, 'YY/MM/DD hh:mm') CDate, MODIFY_DATE, bRcm "
					+ "    FROM (SELECT ROWNUM RN, B.* "
					+ "            FROM (SELECT A.*, (SELECT COUNT(1) FROM RCM_BRD_TRV WHERE BRD_NO = A.BRD_NO) bRcm FROM BOARD_TRAVEL A WHERE USER_NO = ? ORDER BY BRD_NO DESC) B) C"
					+ "    WHERE RN BETWEEN ? AND ?";
		}
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, lUserNum);
		pstmt.setInt(2, (startNum -1) * pageNum +1);
		pstmt.setInt(3, startNum * pageNum);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			board mpw = new board();
			String subNum = (String.valueOf(rs.getInt("BRD_NO"))).substring(3);
			mpw.setBNum(rs.getInt("BRD_NO"));
			mpw.setBSubNum(Integer.parseInt(subNum));
			mpw.setBUserId(rs.getString("bUserId"));
			mpw.setBTitle(rs.getString("TITLE"));
			if (isBF ) {
				mpw.setBSort(rs.getString("SORT"));
			}
			mpw.setBContent(rs.getString("CONTENT"));
			mpw.setBImgAdd(rs.getString("IMG_ADD"));
			mpw.setBCount(rs.getInt("COUNT"));
			mpw.setBCDate(rs.getString("CDate"));
			mpw.setBRcm(rs.getInt("bRcm"));
			
			list.add(mpw);
		}
		connClass.close(conn, pstmt, rs);
		
		return list;
	}
	
	@Override
	public LinkedList<boardComment> getMyAllComments(int lUserNum, int startNum, int pageNum)  throws Exception {
		LinkedList<boardComment> list = new LinkedList<boardComment>();
		Connection conn = connClass.open();
		String sql;
		sql = "SELECT ISBF, COM_NO, BRD_NO, USER_NO, cUserId, CONTENT, G_COUNT, cCDate, cMDate "
				+ "    FROM (SELECT ROWNUM RN, B.* "
				+ "            FROM (SELECT * "
				+ "                    FROM (SELECT 0 ISBF, COM_NO, BRD_NO, USER_NO, (SELECT ID FROM USERS WHERE USER_NO = A.USER_NO) cUserId, CONTENT, G_COUNT, TO_CHAR(CREATE_DATE, 'YY/MM/DD hh:mm') cCDate, TO_CHAR(MODIFY_DATE, 'YY/MM/DD hh:mm') cMDate FROM COMMENTS_BRD_TRV A WHERE USER_NO = ? "
				+ "                    UNION ALL SELECT 1 ISBF, COM_NO, BRD_NO, USER_NO, (SELECT ID FROM USERS WHERE USER_NO = A.USER_NO) cUserId, CONTENT, G_COUNT, TO_CHAR(CREATE_DATE, 'YY/MM/DD hh:mm') cCDate, TO_CHAR(MODIFY_DATE, 'YY/MM/DD hh:mm') cMDate FROM COMMENTS_BRD_FREE A WHERE USER_NO = ?) "
				+ "            ORDER BY COM_NO DESC) B) "
				+ "WHERE RN BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, lUserNum);
		pstmt.setInt(2, lUserNum);
		pstmt.setInt(3, (startNum -1) * pageNum);
		pstmt.setInt(4, startNum * pageNum);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			boardComment bdc = new boardComment();
			bdc.setCIsBF(rs.getInt("ISBF"));
			bdc.setCNum(rs.getLong("COM_NO"));
			bdc.setCBNum(rs.getInt("BRD_NO"));
			String subNum = (String.valueOf(rs.getInt("BRD_NO"))).substring(3);
			bdc.setCBSubNum(Integer.parseInt(subNum));
			bdc.setCUserNum(rs.getInt("USER_NO"));
			bdc.setCUserId(rs.getString("cUserId"));
			bdc.setCContent(rs.getString("CONTENT"));
			bdc.setCGCount(rs.getInt("G_COUNT"));
			bdc.setCCDate(rs.getString("cCDate"));
			if (rs.getString("cMDate") == null) {
				bdc.setCMDate(null);
			}
			else {
				bdc.setCMDate(rs.getString("cMDate"));
			}
			list.add(bdc);
		}
		connClass.close(conn, pstmt, rs);
		return list;
	}
	@Override
	public int getComsPageNum(int lUserNum) throws Exception {
		int result;
		Connection conn = connClass.open();
		String sql = "SELECT COUNT(1) cnt "
							+ "FROM (SELECT * FROM COMMENTS_BRD_TRV WHERE USER_NO = ? "
							+ "UNION ALL "
							+ "SELECT * FROM COMMENTS_BRD_FREE WHERE USER_NO = ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, lUserNum);
		pstmt.setInt(2, lUserNum);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		result = rs.getInt("cnt");
		connClass.close(conn, pstmt, rs);
		return result;
	}
	
	@Override
	public LinkedList<reservation> getMyAllReservation(int rsvPageNum, int startNum, int lUserNum) throws Exception {
		LinkedList<reservation> list = new LinkedList<reservation>();
		Connection conn = connClass.open();
		String sql = "SELECT rRsvNum, rPackageNum, rPeopleNum, rRDate, rSDate, rEDate, rPayCheck, rRegionName, rTitle, rSort, rPrice, rContent "
					+ "    FROM (SELECT ROWNUM RN, rRsvNum, rPackageNum, rPeopleNum, rRDate, rSDate, rEDate, rPayCheck, rRegionName, rTitle, rSort, rPrice, rContent "
					+ "            FROM (SELECT A.RSV_NO rRsvNum, A.PKG_NO rPackageNum, A.PEOPLE_NUM rPeopleNum, TO_CHAR(A.RSV_DATE, 'YY/MM/DD hh:mm') rRDate,"
					+ "                        TO_CHAR(A.START_DATE, 'YY/MM/DD') rSDate, TO_CHAR(A.END_DATE, 'YY/MM/DD') rEDate, A.PAY_CHK rPayCheck,"
					+ "                        (SELECT NAME FROM LIST_REGION WHERE REGION_ID = B.REGION_ID) rRegionName,"
					+ "								 B.TITLE rTitle, B.SORT rSort, B.PRICE rPrice, B.CONTENT rContent "
					+ "                        FROM RESERVATIONS A, PACKAGES B "
					+ "                        WHERE B.PKG_NO = A.PKG_NO "
					+ "                        AND A.USER_NO = ? "
					+ "                        ORDER BY RSV_NO DESC)) "
					+ "    WHERE RN BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, lUserNum);
		pstmt.setInt(2, (startNum -1) * rsvPageNum +1);
		pstmt.setInt(3, startNum * rsvPageNum);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			reservation rsv = new reservation();
			rsv.setRRsvNum(rs.getInt("rRsvNum"));
			rsv.setRUserNum(lUserNum);
			rsv.setRPayCheck(rs.getInt("rPackageNum"));
			rsv.setRPeopleNum(rs.getInt("rPeopleNum"));
			rsv.setRRDate(rs.getString("rRDate"));
			rsv.setRSDate(rs.getString("rSDate"));
			rsv.setREDate(rs.getString("rEDate"));
			rsv.setRPayCheck(rs.getInt("rPayCheck"));
			rsv.setRRegionName(rs.getString("rRegionName"));
			rsv.setRTitle(rs.getString("rTitle"));
			rsv.setRSort(rs.getString("rSort"));
			rsv.setRPrice(rs.getInt("rPrice"));
			rsv.setRContent(rs.getString("rContent"));
			list.add(rsv);
		}
		connClass.close(conn, pstmt, rs);
		return list;
	}
	@Override
	public int getRsvPageNum(int lUserNum) throws Exception {
		int result;
		Connection conn = connClass.open();
		String sql = "SELECT COUNT(1) cnt "
							+ "FROM (SELECT * FROM RESERVATIONS WHERE USER_NO = ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, lUserNum);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		result = rs.getInt("cnt");
		connClass.close(conn, pstmt, rs);
		return result;
	}
	@Override
	public boolean checkMyWritesUserNum(boolean isBf, int bUserNum) throws Exception {
		String sql;
		if (isBf)
			sql = "SELECT USER_NO FROM BOARD_FREE WHERE BRD_NO = ?";
		else
			sql = "SELECT USER_NO FROM BOARD_FREE WHERE BRD_NO = ?";
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bUserNum);
		ResultSet rs = pstmt.executeQuery();
		if (!rs.next()) {
			connClass.close(conn, pstmt, rs);
			return false;
		}
		connClass.close(conn, pstmt, rs);
		return true;
	}
	@Override
	public void updateChangeableInfo(CHANGEABLEUSERS user) throws Exception {
		ArrayList<String> sqlList = new ArrayList<String>();
		int i = 0;
		boolean plusComma = false;
		Connection conn = connClass.open();
		String plusSql = "";
		if (user.getEMAIL() != null && !user.getEMAIL().isEmpty()) {
			plusSql += "EMAIL = ?";
			i++;
			plusComma = true;
			sqlList.add(user.getEMAIL());
		}
		if (user.getPHONE() != null && !user.getPHONE().isEmpty()) {
			if (plusComma) {
				plusSql += ",";
			}
			plusSql += "PHONE = ?";
			i++;
			plusComma = true;
			sqlList.add(user.getPHONE());
		}
		if (user.getMBTI() != null && !user.getMBTI().isEmpty()) {
			if (plusComma) {
				plusSql += ",";
			}
			plusSql += "MBTI = ?";
			i++;
			plusComma = true;
			sqlList.add(user.getMBTI());
		}
		if (user.getIMG_ADD() != null && !user.getIMG_ADD().isEmpty()) {
			if (plusComma) {
				plusSql += ",";
			}
			plusSql += "IMG_ADD = ?";
			i++;
			sqlList.add(user.getIMG_ADD());
		}
		String sql = "UPDATE USERS SET "+plusSql+" WHERE ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		for (int j = 0; j < i; j++) {
			pstmt.setString(j, sqlList.get(j));
		}
		pstmt.setString(i, user.getID());
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	@Override
	public CHANGEABLEUSERS getMyChangeableInfo(String id) throws Exception {
		CHANGEABLEUSERS cUser = new CHANGEABLEUSERS();
		String sql = "SELECT EMAIL, PHONE, MBTI, IMG_ADD FROM USERS WHERE ID = ?";
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		cUser.setEMAIL(rs.getString("EMAIL"));
		cUser.setPHONE(rs.getString("PHONE"));
		cUser.setMBTI(rs.getString("MBTI"));
		cUser.setIMG_ADD(rs.getString("IMG_ADD"));
		connClass.close(conn, pstmt, rs);
		return cUser;
	}
}
