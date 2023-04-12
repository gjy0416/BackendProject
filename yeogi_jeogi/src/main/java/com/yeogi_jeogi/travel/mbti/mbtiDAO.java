package com.yeogi_jeogi.travel.mbti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.yeogi_jeogi.jdbc.connClass;
import com.yeogi_jeogi.travel.mbti.mbtiDAO;

@Component
public class mbtiDAO {
		
		public ArrayList<mbti> getmbtilist(int mbtiNum, int startNum, int pageNum) throws Exception {
			ArrayList<mbti> list = new ArrayList<mbti>();
			String sql = "SELECT mList_num, mTravel_num, MBTI "
					+ "    FROM (SELECT ROWNUM RN, mList_no, mTravel_no, MBTI "
					+ "        FROM (SELECT A.TRAVEL_NO mTravelNum, (SELECT NAME FROM LIST_MBTI WHERE REGION_ID = A.REGION_ID) mTravel_num, A.NAME tName, A.IMG_ADD tImgAdd,"
					+ "					(SELECT COUNT(1) FROM RCM_TRAVEL WHERE TRAVEL_NO = A.TRAVEL_NO)"	
					+ "                                    FROM LIST_MBTI A, LIST_TRAVEL B "
					+ "                                    WHERE A.MBTI = B.MBTI "
					+ "                                    AND B.TRAVEL_NO = ? "
					+ "                                    ORDER BY mTravelNum) "
					+ "        ORDER BY RN) "
					+ "    WHERE RN BETWEEN ? AND ?";
			
			Connection conn = connClass.open();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, mbtiNum);
			pstmt.setInt(2, (startNum -1) * pageNum +1);
			pstmt.setInt(3, startNum * pageNum);
			ResultSet rs = pstmt.executeQuery();

			try {
				while (rs.next()) {
					mbti mt = new mbti();
					mt.setmList_num(rs.getInt("mList_num"));
					mt.setmTravel_num(rs.getInt("mTravel_num"));
					mt.setMBTI(rs.getString("MBTI"));
					list.add(mt);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			connClass.close(conn, pstmt, rs);
			return list; 
		}
		public int getmbtiPageNum(int mbtiNum) throws SQLException {
			int allPageNum;
			String sql = "SELECT COUNT(1) cnt FROM (SELECT REGION_ID FROM LIST_REGION WHERE CITY_ID = ?) A, LIST_TRAVEL B WHERE A.REGION_ID = B.REGION_ID";
			Connection conn = connClass.open();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mbtiNum);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			allPageNum = rs.getInt("cnt");
			
			connClass.close(conn, pstmt, rs);
			return allPageNum;
		}
	}

