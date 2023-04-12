package com.yeogi_jeogi.festival;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import org.springframework.stereotype.Component;

import com.yeogi_jeogi.jdbc.connClass;


@Component
public class festivalDAO {
	
	public LinkedList<festival> getSearchFestival(String lcName) throws Exception {
		LinkedList<festival> list = new LinkedList<festival>();
		Connection conn = connClass.open();
		PreparedStatement pstmt;
		String sql;
		
		if (lcName.isEmpty()) {
			sql = "SELECT FESTIVALS.*, LR.*, LC.*, EXTRACT(MONTH FROM START_DATE) AS START_MONTH, EXTRACT(MONTH FROM END_DATE) AS END_MONTH, TO_CHAR(START_DATE, 'YYYY-MM-DD') AS SDATE, TO_CHAR(END_DATE, 'YYYY-MM-DD') AS EDATE, F_MODAL.* FROM FESTIVALS LEFT JOIN LIST_REGION LR ON LR.REGION_ID = FESTIVALS.REGION_ID LEFT JOIN LIST_CITY LC ON LC.CITY_ID = LR.CITY_ID LEFT JOIN F_MODAL ON FESTIVALS.FEST_NO = F_MODAL.FEST_NO";
			pstmt = conn.prepareStatement(sql);
		} else {
			sql = "SELECT FESTIVALS.*, LR.*, LC.*, EXTRACT(MONTH FROM START_DATE) AS START_MONTH, EXTRACT(MONTH FROM END_DATE) AS END_MONTH, TO_CHAR(START_DATE, 'YYYY-MM-DD') AS SDATE, TO_CHAR(END_DATE, 'YYYY-MM-DD') AS EDATE, F_MODAL.* FROM FESTIVALS LEFT JOIN LIST_REGION LR ON LR.REGION_ID = FESTIVALS.REGION_ID LEFT JOIN LIST_CITY LC ON LC.CITY_ID = LR.CITY_ID LEFT JOIN F_MODAL ON FESTIVALS.FEST_NO = F_MODAL.FEST_NO WHERE LC.NAME like '%" + lcName + "%'";
			pstmt = conn.prepareStatement(sql);
		}
		ResultSet rs = pstmt.executeQuery();

		try {
			while (rs.next()) {
				festival fs = new festival();
				fs.setfNo(rs.getInt("FEST_NO"));
				fs.setgId(rs.getInt("REGION_ID"));
				fs.setTitle(rs.getString("TITLE"));
				fs.setContent(rs.getString("CONTENT"));
				fs.setImg(rs.getString("IMG_ADD"));
				fs.setSdate(rs.getString("SDATE"));
				fs.setEdate(rs.getString("EDATE"));
				fs.setUdate(rs.getString("UPDATE_DATE"));
				fs.setStart_month(rs.getString("START_MONTH"));
				fs.setEnd_month(rs.getString("END_MONTH"));
				fs.setPhone(rs.getString("PHONE"));
				fs.setTime(rs.getString("F_TIME"));
				fs.setPay(rs.getInt("PAY"));
				fs.setAddr1(rs.getString("ADDRESS"));
				fs.setAddr2(rs.getString("F_ADDRESS"));
				fs.setImg1(rs.getString("F_IMG1"));
				fs.setImg2(rs.getString("F_IMG2"));
				list.add(fs);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		connClass.close(conn, pstmt, rs);
		return list; 
	}

}