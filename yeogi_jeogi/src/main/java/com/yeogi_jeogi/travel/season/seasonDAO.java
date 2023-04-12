package com.yeogi_jeogi.travel.season;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.yeogi_jeogi.jdbc.connClass;
import com.yeogi_jeogi.travel.season.season;

@Component
public class seasonDAO {

		public ArrayList<season> getSeasonTravelList(int seasonNum, int startNum, int pageNum) throws Exception {
			ArrayList<season> list = new ArrayList<season>();
			String sql = "SELECT sTravelNum, sRegionName, sName, sImgAdd, sRcm, sInfo, sCount "
					+ "    FROM (SELECT ROWNUM RN, sTravelNum, sRegionName, sName, sImgAdd, sRcm, sInfo, sCount "
					+ "        FROM (SELECT A.TRAVEL_NO sTravelNum, (SELECT NAME FROM TRAVEL_SEASON WHERE REGION_ID = A.REGION_ID) sRegionName, A.NAME sName, A.IMG_ADD sImgAdd,"
					+ "					(SELECT COUNT(1) FROM RCM_TRAVEL WHERE TRAVEL_NO = A.TRAVEL_NO) sRcm, INFO sInfo, COUNT sCount "	
					+ "                                    FROM LIST_TRAVEL A, LIST_REGION B "
					+ "                                    WHERE A.REGION_ID = B.REGION_ID "
					+ "                                    AND B.CITY_ID = ? "
					+ "                                    ORDER BY sTravelNum) "
					+ "        ORDER BY RN) "
					+ "    WHERE RN BETWEEN ? AND ?";
			
			Connection conn = connClass.open();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, seasonNum);
			pstmt.setInt(2, (startNum -1) * pageNum +1);
			pstmt.setInt(3, startNum * pageNum);
			ResultSet rs = pstmt.executeQuery();

			try {
				while (rs.next()) {
					season ss = new season();
					ss.setsTravelNum(rs.getInt("sTravelNum"));
					ss.setsRegionName(rs.getString("sRegionName"));
					ss.setsName(rs.getString("sName"));
					ss.setsInfo(rs.getString("sInfo"));
					ss.setsCount(rs.getInt("sCount"));
					ss.setsImgAdd(rs.getString("sImgAdd"));
					ss.setsRcm(rs.getInt("sRcm"));
					list.add(ss);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			connClass.close(conn, pstmt, rs);
			return list; 
		}
		public int getSeasonPageNum(int seasonNum) throws SQLException {
			int allPageNum;
			String sql = "SELECT COUNT(1) cnt FROM (SELECT REGION_ID FROM LIST_REGION WHERE CITY_ID = ?) A, LIST_TRAVEL B WHERE A.REGION_ID = B.REGION_ID";
			Connection conn = connClass.open();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seasonNum);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			allPageNum = rs.getInt("cnt");
			
			connClass.close(conn, pstmt, rs);
			return allPageNum;
		}
	}

