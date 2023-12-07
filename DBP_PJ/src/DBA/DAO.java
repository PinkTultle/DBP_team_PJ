package DBA;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DAO {
	
	private Connection con = null;
	private ResultSet rs;
	private Statement stmt;
	private PreparedStatement pstmt;
	private CallableStatement callstmt;
	private String sql;
	private int ex_num;
	private String user_id;
	
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
	
	public DAO(String id) {
		super();
		user_id = id;
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
			sql = null;
			ex_num = 0;
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

	
	//Statement 구현-------------------------------
	
	// 1. 사용자가 댓글 삭제 버튼 클릭하면 댓글 번호와 레시피 번호로 해당 댓글을 삭제한다.
	private boolean Delete_comment(int recipe_num, int comment_num) {
		
		DB_Connect();
		sql = "delete from 댓글 where 레시피번호 = "+ recipe_num + " and 댓글번호 = "+ comment_num +";";
		
		try {
			stmt = con.createStatement();
			ex_num = stmt.executeUpdate(sql);
			
			//성공시 true 반환
			if(ex_num == 1) {
				return true;
			}else throw(null); 
			
		}
		//실패시 에러 발생 false 반환
		catch(Exception e) {
			return false;
		}
		finally {
			//마지막에 연결 끊기 및 객체 초기화
			End_of_use();
		}		
	}

	//   2. 리뷰 삭제 시, 리뷰 번호로 리뷰 테이블의 해당 리뷰를 삭제한다.
	private boolean Delete_review(int recipe_num, int review_num) {
		
		DB_Connect();
		sql = "delete from 댓글 where 레시피번호 = "+ recipe_num + " and 리뷰번호 = "+ review_num +";";
		
		try {
			stmt = con.createStatement();
			ex_num = stmt.executeUpdate(sql);
			
			//성공시 true 반환
			if(ex_num == 1) {
				return true;
			}else throw(null); 
			
		}
		//실패시 에러 발생 false 반환
		catch(Exception e) {
			return false;
		}
		finally {
			//마지막에 연결 끊기 및 객체 초기화
			End_of_use();
		}					
	}
	
	//PreparedStatement 구현-----------------------------------------------------
	
	//INSERT
    //가. 회원가입 시 사용자의 정보를 입력받아 사용자 테이블에 추가한다.
	private boolean Insert_user(User_DTO dto) {
		
		DB_Connect();
		
		sql = "INSERT INTO 사용자 (사용자ID, 이름, 이메일, 전화번호, 비밀번호, 총작성수, 등급),"
				+ " VALUES ('?', '?', '?', '?', '?', 0, null);";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getID());
			pstmt.setString(2, dto.getNAME());
			pstmt.setString(3, dto.getEMAIL());
			pstmt.setString(4, dto.getNUMBER());
			pstmt.setString(5, dto.getPW());
			
			ex_num = pstmt.executeUpdate();
			
			if(ex_num == 1) {
				return true;
			}else throw(null);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			End_of_use();
		}		
	}
	
    //나. 레시피 등록 시 사용자가 입력한 정보를 레시피 테이블에 추가한다. 
	private boolean Insert_recipe(Recipe_DTO dto) {
		
		DB_Connect();
		
		sql = "INSERT INTO 레시피 (제목, 작성자ID, 작성내용, 작성시간, 카테고리, 레시피설명, 조회수, 추천수, 난이도),"
				+ " VALUES ('?', '?', '?', '?', '?', '?', 0, 0, ?);";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTITLE());
			pstmt.setString(2, dto.getID());
			pstmt.setString(3, dto.getCONTENT());
			pstmt.setDate(4, dto.getDATE());
			pstmt.setString(5, dto.getCATEGORY());
			pstmt.setString(6, dto.getDESCRIPTION());
			pstmt.setInt(7, dto.getLEVEL());
			
			ex_num = pstmt.executeUpdate();
			
			if(ex_num == 1) {
				return true;
			}else throw(null);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			End_of_use();
		}		
	}
	
	//다. 리뷰등록 시 사용자가 입력한 정보와 레시피 번호를 리뷰 테이블에 추가한다.
    



	//라. 댓글 등록 시 사용자가 입력한 정보와 레시피 번호를 댓글 테이블에 추가한다. 
	
	
	
	//SELECT
	
	
	//DELETE
	
	
	//CallableStatement 구현--------------------------------------------------------
	
	
	
	
	private void test() {
		
		//메소드 테스트 함수 실행시킬 함수를 안에 넣고 시작
		
		
	}
	
	
	public static void main(String[] args) {
		DAO dao = new DAO();
		dao.test();
	}


}
