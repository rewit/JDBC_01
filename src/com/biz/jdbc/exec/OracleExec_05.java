package com.biz.jdbc.exec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.model.ioListVO;

public class OracleExec_05 {

	public static void main(String[] args) {

		/*
		 * user4 schema에 있는 tbl_iolist 테이블을 읽어서 리스트를 콘솔에 보이시오
		 */

		List<ioListVO> iList = new ArrayList<ioListVO>();

		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "user4";
		String passWord = "1234";

		try {
			Class.forName(jdbcDriver);
			Connection con = DriverManager.getConnection(url, userName, passWord);
			System.out.println("연결성공");

			PreparedStatement Istr = null;
			String spl = "SELECT * FROM tbl_iolist";

			Istr = con.prepareStatement(spl);
			ResultSet is = Istr.executeQuery();

			while (is.next()) {

				ioListVO io = new ioListVO();

				io.setIo_seq(is.getLong(1));
				io.setIo_date(is.getString(2));
				io.setIo_inout(is.getString(3));
				io.setIo_amt(is.getInt(4));
				io.setIo_price(is.getInt(5));
				io.setIo_total(is.getInt(6));
				io.setIo_pcode(is.getString(7));
				io.setIo_ccode(is.getString(8));

				iList.add(io);

			}

			for (ioListVO io : iList) {
				System.out.println(io);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
