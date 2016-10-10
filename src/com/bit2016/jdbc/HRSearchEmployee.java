package com.bit2016.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HRSearchEmployee {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	Scanner sc = null;
	
	
	try {
		//1.JDBC 드라이버(oracle) 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");  //오라클폴더에 있는 jdbc 파일(필요한 파일 정리해서 폴더만들어서 정리하면 편함)
		//프로젝트 -> 프로퍼티 -> 자바 빌드 패스 -> add lib -> user lib -> new -> jdbc 파일 불러오기
		
		//2. connection 얻어오기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url, "hr", "hr");
		
		//3. Statement(문장) 객체 생성
		stmt = conn.createStatement();
		
		//4.sql문 실행
		String sql = "select employee_id, first_name, salary from employees";
		rs = stmt.executeQuery(sql); //select 일때만 executeQuery
		
		//5.결과(Result Set)가져오기
		while(rs.next()){
			Long employeeId = rs.getLong(1);  // 컬럼명을 적어줘도 좋지만 인덱스 숫자로 하는것이 좋다.
			String firstName = rs.getString(2);
			Integer salary = rs.getInt(3);
		
			System.out.println(employeeId + ":" + firstName + ":" + salary);
		}
	} catch (ClassNotFoundException e) {
		System.out.println("드라이버 로딩 실패 : " + e);
	} catch (SQLException e2) {
		System.out.println("error : " + e);
		
	} finally{
		
		try {
			//3. 자원정리
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
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
