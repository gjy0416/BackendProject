package com.yeogi_jeogi.travel.region;

import java.sql.Connection;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.yeogi_jeogi.jdbc.connClass;
import com.yeogi_jeogi.travel.region.region;

@Component
public class regionDAO {
	
	public ArrayList<region> getRegionTravelList(int cityNum, int startNum, int pageNum) throws Exception {
		ArrayList<region> list = new ArrayList<region>();
		String sql = "SELECT tTravelNum, tRegionName, tName, tImgAdd, tRcm, tInfo, tCount "
				+ "    FROM (SELECT ROWNUM RN, tTravelNum, tRegionName, tName, tImgAdd, tRcm, tInfo, tCount "
				+ "        FROM (SELECT A.TRAVEL_NO tTravelNum, (SELECT NAME FROM LIST_REGION WHERE REGION_ID = A.REGION_ID) tRegionName, A.NAME tName, A.IMG_ADD tImgAdd,"
				+ "					(SELECT COUNT(1) FROM RCM_TRAVEL WHERE TRAVEL_NO = A.TRAVEL_NO) tRcm, INFO tInfo, COUNT tCount "	
				+ "                                    FROM LIST_TRAVEL A, LIST_REGION B "
				+ "                                    WHERE A.REGION_ID = B.REGION_ID "
				+ "                                    AND B.CITY_ID = ? "
				+ "                                    ORDER BY tTravelNum) "
				+ "        ORDER BY RN) "
				+ "    WHERE RN BETWEEN ? AND ?";
		
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, cityNum);
		pstmt.setInt(2, (startNum -1) * pageNum +1);
		pstmt.setInt(3, startNum * pageNum);
		ResultSet rs = pstmt.executeQuery();

		try {
			while (rs.next()) {
				region tvl = new region();
				tvl.settTravelNum(rs.getInt("tTravelNum"));
				tvl.settRegionName(rs.getString("tRegionName"));
				tvl.settName(rs.getString("tName"));
				tvl.settInfo(rs.getString("tInfo"));
				tvl.settCount(rs.getInt("tCount"));
				tvl.settImgAdd(rs.getString("tImgAdd"));
				tvl.settRcm(rs.getInt("tRcm"));
				list.add(tvl);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		connClass.close(conn, pstmt, rs);
		return list; 
	}
	public int getRegionPageNum(int cityNum) throws SQLException {
		int allPageNum;
		String sql = "SELECT COUNT(1) cnt FROM (SELECT REGION_ID FROM LIST_REGION WHERE CITY_ID = ?) A, LIST_TRAVEL B WHERE A.REGION_ID = B.REGION_ID";
		Connection conn = connClass.open();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, cityNum);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		allPageNum = rs.getInt("cnt");
		
		connClass.close(conn, pstmt, rs);
		return allPageNum;
	}
}
