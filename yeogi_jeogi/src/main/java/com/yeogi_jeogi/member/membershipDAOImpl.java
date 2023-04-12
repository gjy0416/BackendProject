package com.yeogi_jeogi.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.yeogi_jeogi.jdbc.connClass;

@Repository
public class membershipDAOImpl implements membershipDAO {
	
	@Override
	public void addmember(membership mem) throws Exception {
		String sql = "INSERT INTO USERS VALUES (SEQ_USERS.NEXTVAL, DEFAULT, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try (conn; pstmt) {
			pstmt.setString(1, mem.getmUserId());
			pstmt.setString(2, mem.getmName());
			pstmt.setString(3, mem.getmPassword());
			pstmt.setString(4, mem.getmEmail());
			pstmt.setString(5, mem.getmPhone());
			pstmt.setString(6, mem.getmMbti());
			pstmt.executeUpdate();
		}
		pstmt.close();
		conn.close();
	}

	@Override
	public boolean checkUserIdDup(String userId) throws Exception {
		String sql = "SELECT ID FROM USERS WHERE ID = ?";
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			connClass.close(conn, pstmt, rs);
			return false;
		}
		else {
			connClass.close(conn, pstmt, rs);
			return true;
		}
	}
}
