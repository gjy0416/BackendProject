package com.yeogi_jeogi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class connClass {
	final static String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final static String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:myoracle";
	
	public static Connection open() {
		Connection conn = null;
		
		try {
			Class.forName(JDBC_DRIVER);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("드라이버를 찾을 수 없습니다!");
		}
		try {
			conn = DriverManager.getConnection(JDBC_URL, "TEST", "1234");
		}
		catch (Exception e) {
			System.err.println("데이터 베이스 연결에 실패했습니다.");
		}
		return conn;
	}
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
