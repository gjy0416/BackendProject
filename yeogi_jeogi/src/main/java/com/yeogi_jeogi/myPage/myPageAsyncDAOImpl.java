package com.yeogi_jeogi.myPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.yeogi_jeogi.jdbc.connClass;

@Repository
public class myPageAsyncDAOImpl implements myPageAsyncDAO {
	@Override
	public void deleteMyWrites(boolean IsBF, int bNum) throws Exception {
		String sql;
		if (IsBF)
			sql = "DELETE FROM BOARD_FREE WHERE BRD_NO = ?";
		else
			sql = "DELETE FROM BOARD_TRAVEL WHERE BRD_NO = ?";
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bNum);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	@Override
	public void deleteMyComs(boolean IsBF, long cNum) throws Exception {
		String sql;
		if (IsBF) {
			sql = "DELETE FROM COMMENTS_BRD_FREE WHERE COM_NO = ?";
		}
		else {
			sql = "DELETE FROM COMMENTS_BRD_TRV WHERE COM_NO = ?";
		}
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, cNum);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
	@Override
	public void deleteMyReservation(int rRsvNum) throws Exception {
		String sql = "DELETE FROM RESERVATIONS WHERE RSV_NO = ?";

		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, rRsvNum);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	}
}
