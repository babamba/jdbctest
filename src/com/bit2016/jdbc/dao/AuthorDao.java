package com.bit2016.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bit2016.jdbc.vo.AuthorVO;

public class AuthorDao {

	public void insert(AuthorVO vo){
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
			String sql = 
					"insert into book values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			 
			
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getName());
			

			// 5. SQL문 실행
			int count = pstmt.executeUpdate();
			System.out.println(count);
			
			//System.out.println("연결성공");
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
					conn.close();  //close 되면서 커밋
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}

	}
	}
