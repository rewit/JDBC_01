package com.biz.jdbc.exec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleExec_01 {

	public static void main(String[] args) {

		/*
		 * 1. ojdbc.jar를 프로젝트에 설치 2. tbl_student와 tbl_score 테이블 구조와 같은 VO를 생성 3. 오라클
		 * DBMS로부터 두 테이블에 담긴 값을 가져와서 콘솔에 보여주는 코드 작성
		 */

		// 오라클에서 제공해주는 JDBC Driver 이름을 변수에 설정
		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "user4";
		String passWord = "1234";

		try {
			Class.forName(jdbcDriver);
			// Connection con = new Connection();
			// new = 새로운 무언가를 만든(초기화 선언)

			// Driver를 사용할 수 있도록 Java에게 준비해달라 요청
			Connection con = DriverManager.getConnection(url, userName, passWord); // Driver의 도움을 받아서 연결

			// Java(JVM) 입장에서 외부장치(DBMS)와 연결하는 통로가
			// 여러개가 존재하면 어떤 통로를 통해서 데이터를 주고 받아야할지 상당히 불편하다
			// Java는 DriverManager에게 DBMS와 데이터를 주고받을 통로를 하나 만들고
			// 그 정보를 달라 요청하고 그 정보를 con이라는 객체변수에 담아놓는다.

			// 여기까지 실행이 된다면 "오라클과 연결할 통로가 설정(생성)되었다" 라고 볼 수 있다
			System.out.println("오라클 연결 성공");

			// 오라클에게 명령을 보내기

			// 표준 JDBC 명령(오라클 )
			PreparedStatement pStr = null;
			// PreparedStatement = JDBC를 통해서 SQL을 전달할 때 사용할 클래스
			String sql = " SELECT * FROM tbl_student ";

			// sql문자열을 오라클 고유의 명령으로 변환하라
			pStr = con.prepareStatement(sql);

			// 오라클에게 명령을 보내고 그 결과를 나(rs)에게 달라
			ResultSet rs = pStr.executeQuery();
			// Query를 실행하여 rs에 담기 (받을때는 ResultSet)

			while (rs.next()) {
				// rs.next를 실행시킬떄 읽을 데이터가 있으면 true 없으면 false
				String strNum = rs.getString(1);
				// 문자열 첫번째 칼럼의 값을 읽어라
				String strName = rs.getString(2);
				// 문자열 두번째 칼럼의 값을 읽어라
				System.out.println(strNum + " : " + strName);
			}

		} catch (ClassNotFoundException e) {
			// JDBC Driver Class 가 어떤 이유로 사라졌을 경우
			// e.printStackTrace();
			System.out.println("JDBC Driver 실행 오류");
		} catch (SQLException e) {
			// DriverManager가 OJDBC를 통해서 오라클에 연결신호를 보냈으나
			// 어떤 이유인지 연결이 불가능할때
			// e.printStackTrace();
			System.out.println("오라클에 연결 할 수 없음");
		}
	}

}
