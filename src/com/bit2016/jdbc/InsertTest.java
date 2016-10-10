package com.bit2016.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest {
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		try {
			//1.JDBC 드라이버(oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");  //오라클폴더에 있는 jdbc 파일(필요한 파일 정리해서 폴더만들어서 정리하면 편함)
			//프로젝트 -> 프로퍼티 -> 자바 빌드 패스 -> add lib -> user lib -> new -> jdbc 파일 불러오기
			
			//2. connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "bitdb", "bitdb");
			
			//3. Statement 생성
			stmt = conn.createStatement();
			
			//4. SQL 실행
			//토드에서 쿼리 복사할때는 세미콜론 빼서 !!!!!
			Long no = 12L;
			String title = "토지12";
			//String date = "2016-10-10";
			String state = "대여가능";
			Long authorNo = 1L;
			
			//요부분이 어렵다 중요함!!
			String sql = 
					"insert into book values (" + 
					no +  ", '" + 
					title + "', sysdate, '" + 
					state + "'," + 
					authorNo + ")";
			
			int count = stmt.executeUpdate(sql);
			System.out.println(count);
			
			//System.out.println("연결성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
			
		} finally{
			
			try {
				//3. 자원정리
				
				if(stmt != null){
					stmt.close();
				}
				if(conn != null)
					conn.close();  //close 되면서 커밋
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
