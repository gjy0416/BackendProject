package com.yeogi_jeogi.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.yeogi_jeogi.jdbc.connClass;

@Repository
public class loginDAOImpl implements loginDAO {
	@Override
	public USERS getUser(String username) throws SQLException {
		String sql = "SELECT * FROM USERS WHERE ID = ?";
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		if (!rs.next()) {
			return null;
		}
		
		USERS lg = new USERS();
		lg.setUSER_NO(rs.getInt("USER_NO"));
		lg.setGRADE_NO(rs.getInt("GRADE_NO"));
		lg.setID(rs.getString("ID"));
		lg.setNAME(rs.getString("NAME"));
		lg.setPASSWORD(rs.getString("PASSWORD"));
		lg.setEMAIL(rs.getString("EMAIL"));
		lg.setPHONE(rs.getString("PHONE"));
		lg.setMBTI(rs.getString("MBTI"));
		lg.setIMG_ADD(rs.getString("IMG_ADD"));
		
		connClass.close(conn, pstmt, rs);
		return lg; 
	}
}
