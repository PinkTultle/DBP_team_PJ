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
	String id = "RECIPE";
	String pw = "1234";
	
	public DAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이브 적재 성공");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버를 찾을 수 없습니다.");
		}
	}
	
	private void DB_Connect() {
		try {
			con = DriverManager.getConnection(url, id, pw);	
			System.out.println("데이터베이스 연결 성공");
		}
		catch(SQLException e) {
			System.out.println("데이터베이스 연결 실패");
			System.exit(0);
		}
	}
	
	private void End_of_use() {
		try {
			con.close();
			rs.close();
			stmt.close();
			pstmt.close();
			callstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	/*
	 * 아래 영역에 sql문 전송을 구현 작성 전 열어서 확인
	 * 호출하는 기능에따라 메서드에서 해당하는 DTO를 생성
	 * DTO는 테이블의 속성을 모두 가진 클래스
	 * 접근자와 설정자를 적절히 활용하여 sql문을 구성하고 
	 * 사용값을 설저한다.
	 *  
	 *  현재 구현된 DTO : 사용자, 레시피
	 *  
	 *  구현시 순서
	 *  DB_Connect()로 DB와 선연결 
	 *  try문 내에서 로직 수행
	 *  final문을 통해 마지막에 End_of_use() 실행
	 *  접속을 끊는다.
	 */
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new DAO();
	}


}
