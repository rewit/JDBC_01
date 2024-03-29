package com.biz.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.model.StudentVO;

public class StdService {

	List<StudentVO> stdList = null;
	Connection dbConn = null;
	
	public StdService() {
		stdList = new ArrayList<StudentVO>();
		this.dbconnection();
	}

	//dbConn을 초기화 하는 메서드
	//이 메서드는 Service 생성자에서 호출한다
	
	
	//DB 연결을 설정하는 Service
	
	private void dbconnection() {
		
		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "user4";
		String passWord = "1234";
		
		try {
			Class.forName(jdbcDriver);
			dbConn = DriverManager.getConnection(url,userName,passWord);
		} catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 없음");
			//e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("오라클 접속 오류");
			//e.printStackTrace();
		}
		
	}
	//전체 읽기
	public List<StudentVO> selecAll(){
		
		String sql = " SELECT * FROM tbl_student";
		PreparedStatement pStr = null;
		try {
			pStr = dbConn.prepareStatement(sql);
			ResultSet rs = pStr.executeQuery();
			
			while(rs.next()) {
				StudentVO vo = new StudentVO();
				
				vo.setSt_no(rs.getString(1));
				vo.setSt_name(rs.getString(2));
				vo.setSt_addr(rs.getString(3));
				vo.setSt_grade(rs.getInt(4));
				vo.setSt_height(rs.getInt(5));
				vo.setSt_weight(rs.getInt(6));
				vo.setSt_nick(rs.getString(7));
				vo.setSt_nick_rem(rs.getString(8));
				vo.setSt_dept_no(rs.getString(9));
				
				stdList.add(vo);
			}
			return stdList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return null;
	}
	
	//PK로 조회하여 한개씩 읽기
	//학번을 기준으로 학생정보를 조회하는 메서드
	public StudentVO findByNum(String num) {
		
		String sql = " SELECT * FROM tbl_student ";
		sql += " WHERE st_no = " + num;

		PreparedStatement pStr = null;
		try {
			pStr = dbConn.prepareStatement(sql);
			
			//PK를 조건으로 검색을 했기 때문에
			//rs에는 한명 이하의 학생 정보가 들어있을 것이다
			ResultSet rs = pStr.executeQuery();
			//while문으로 반복하지 않고
			//한번만 next를 실행한다
			rs.next();
			
			StudentVO vo = new StudentVO();
			
			vo.setSt_no(rs.getString(1));
			vo.setSt_name(rs.getString(2));
			vo.setSt_addr(rs.getString(3));
			vo.setSt_grade(rs.getInt(4));
			vo.setSt_height(rs.getInt(5));
			vo.setSt_weight(rs.getInt(6));
			vo.setSt_nick(rs.getString(7));
			vo.setSt_nick_rem(rs.getString(8));
			vo.setSt_dept_no(rs.getString(9));
			
			return vo;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		
		return null;
	}
	//Create, Insert 를 수행 할 method
	public void insert(StudentVO vo) {
		
	}
	
	public void Update(StudentVO vo) {
		
	}
	
	public void Delete(String num) {
		
	}
}
