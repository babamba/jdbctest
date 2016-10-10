package com.bit2016.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class updateTest {
public static void main(String[] args) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	
	try {
		//1.JDBC 드라이버(oracle) 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");  //오라클폴더에 있는 jdbc 파일(필요한 파일 정리해서 폴더만들어서 정리하면 편함)
		//프로젝트 -> 프로퍼티 -> 자바 빌드 패스 -> add lib -> user lib -> new -> jdbc 파일 불러오기
		
		//2. connection 얻어오기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url, "bitdb", "bitdb");
		
		
		//3. Statement 준비
		String sql = " update book set title = ?, state = ? where no = ? ";  //값을 치환하는게 아니라(바꾸는게 아니라) 값을 바인딩하는 것
		pstmt = conn.prepareStatement(sql);
		
		//4. 값 바인딩
		Long no = 12L;
		String title = "토지100";
		String state = "대여중";
		
		pstmt.setString(1, title);
		pstmt.setString(2, state);
		pstmt.setLong(3, no);
		
		// 5. SQL실행
		int count = pstmt.executeUpdate(); // 완성된 sql문을 보낸게 아니기 때문에 sql적으면 안됨 prepareStatement 
		System.out.println(count);
		
		
	} catch (ClassNotFoundException e) {
		System.out.println("드라이버 로딩 실패 : " + e);
		
	} catch (SQLException e) {
		System.out.println("error : " + e);
		
	} finally{
		
		try {
			//3. 자원정리
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null)
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
