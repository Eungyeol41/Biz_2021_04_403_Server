package com.callor.book.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * 싱글톤(Single Tone) 패턴
 * 프로젝트를 수행하는 데 필요한 자원(Resource)를 만드는 방법으로 같은 자원을 계속해서 만들지 않고 한 번만 만들어두고 여러 곳에서 활용하는 방법
 */
public class DBContract {
	
	private static Connection dbConn;
	static {
		String jdbcDriver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "bookuser";
		String password = "bookuser";
		
		try {
			Class.forName(jdbcDriver);
			dbConn = DriverManager.getConnection(url, username, password);
			System.out.println("DB Connection OK!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Oracle DB Driver 없음");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Oracle DB 연결 오류!!");
		}
	} // end static
	
	public static Connection getDBConnection() {
		
		return dbConn;
	}
}
