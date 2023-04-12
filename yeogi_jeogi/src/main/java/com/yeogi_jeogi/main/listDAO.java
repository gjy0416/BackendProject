package com.yeogi_jeogi.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import org.springframework.stereotype.Component;

import com.yeogi_jeogi.jdbc.connClass;

@Component
public class listDAO {
	
//	public int getPageNum() throws Exception {
//		int result = 0;
//		Connection conn = connClass.open();
//		String sql;
//		sql = "SELECT COUNT(1) cnt FROM BOARD_FREE";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		ResultSet rs = pstmt.executeQuery();
//		rs.next();
//		result = rs.getInt("cnt");
//		connClass.close(conn, pstmt, rs);
//		return result;
//	}
	
	public LinkedList<listProvince> getProvince() throws Exception {
		LinkedList<listProvince> list = new LinkedList<listProvince>();
		
		String sql = "SELECT * FROM LIST_PROVINCE";
		
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try {
			while (rs.next()) {
				listProvince lp = new listProvince();
				lp.setlpId(rs.getInt("PROVINCE_ID"));
				lp.setlpName(rs.getString("NAME"));
				list.add(lp);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		connClass.close(conn, pstmt, rs);
		return list; 
	}
	
	public LinkedList<listCity> getCity() throws Exception {
		LinkedList<listCity> list = new LinkedList<listCity>();
		
		String sql = "SELECT * FROM LIST_CITY";
		
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try {
			while (rs.next()) {
				listCity lc = new listCity();
				lc.setlcId(rs.getInt("CITY_ID"));
				lc.setlpId(rs.getInt("PROVINCE_ID"));
				lc.setlcName(rs.getString("NAME"));
				list.add(lc);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		connClass.close(conn, pstmt, rs);
		return list; 
	}

}
