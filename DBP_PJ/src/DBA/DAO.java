package DBA;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	
	Connection con = null;
	ResultSet rs;
	Statement stmt;
	PreparedStatement pstmt;
	CallableStatement callstmt;
	
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String id = "ABD";
	String pw = "1234";
	
	public DAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.print("드라이브 적재 성공");
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("데이터 베이스 연결 성공");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
	}
	
	public static void main(String[] args) {
		new DAO();
	}
	

}
