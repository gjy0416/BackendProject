package com.yeogi_jeogi.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import org.springframework.stereotype.Component;

import com.yeogi_jeogi.jdbc.connClass;

@Component
public class productDAO {
	
	public LinkedList<product> getSearchProduct(int lcId, String sdate, String edate, int i) throws Exception {
		LinkedList<product> list = new LinkedList<product>();
		String sql = "";
		PreparedStatement pstmt;
		Connection conn = connClass.open();
		if (i == 1) {
			sql = "SELECT ROWNUM id, PACKAGES.*, lr.*, lc.*, m.* FROM PACKAGES LEFT JOIN LIST_REGION LR ON LR.REGION_ID = PACKAGES.REGION_ID LEFT JOIN LIST_CITY LC ON LC.CITY_ID = LR.CITY_ID left outer JOIN P_MODAL m ON PACKAGES.PKG_NO = m.PKG_NO WHERE lc.CITY_ID = ? AND START_DATE <= ? AND END_DATE >= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lcId);
			pstmt.setString(2, sdate);
			pstmt.setString(3, sdate);
		} else if (i == 2) {
			sql = "SELECT ROWNUM id, PACKAGES.*, lr.*, lc.*, m.* FROM PACKAGES LEFT JOIN LIST_REGION LR ON LR.REGION_ID = PACKAGES.REGION_ID LEFT JOIN LIST_CITY LC ON LC.CITY_ID = LR.CITY_ID JOIN P_MODAL m ON PACKAGES.PKG_NO = m.PKG_NO where num = 2";
			pstmt = conn.prepareStatement(sql);
		} else {
			sql = "SELECT ROWNUM id, PACKAGES.*, lr.*, lc.*, m.* FROM PACKAGES LEFT JOIN LIST_REGION LR ON LR.REGION_ID = PACKAGES.REGION_ID LEFT JOIN LIST_CITY LC ON LC.CITY_ID = LR.CITY_ID JOIN P_MODAL m ON PACKAGES.PKG_NO = m.PKG_NO where num = 3";
			pstmt = conn.prepareStatement(sql);
		}
		
		ResultSet rs = pstmt.executeQuery();

		try {
			while (rs.next()) {
				product pd = new product();
				pd.setId(rs.getInt("id"));
				pd.setpNo(rs.getInt("PKG_NO"));
				pd.setrId(rs.getInt("REGION_ID"));
				pd.setTitle(rs.getString("TITLE"));
				pd.setSort(rs.getString("SORT"));
				pd.setPrice(String.format("%,d", rs.getInt("PRICE")));
				pd.setContent(rs.getString("CONTENT"));
				pd.setImg(rs.getString("IMG_ADD"));
				pd.setCount(rs.getInt("COUNT"));
				pd.setSdate(rs.getString("START_DATE"));
				pd.setEdate(rs.getString("END_DATE"));
				pd.setUdate(rs.getString("UPDATE_DATE"));
				pd.setlpId(rs.getInt("PROVINCE_ID"));
				pd.setTime(rs.getString("P_TIME"));
				pd.setLocal(rs.getString("P_LOCAL"));
				pd.setImg1(rs.getString("P_IMG1"));
				pd.setImg2(rs.getString("P_IMG2"));
				list.add(pd);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		connClass.close(conn, pstmt, rs);
		return list; 
	}
	
}
