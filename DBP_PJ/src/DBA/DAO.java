package DBA;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import oracle.jdbc.OracleTypes;

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
				+ " VALUES ('?', '?', '?', ?, '?', '?', 0, 0, ?);";
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
	private boolean Insert_review(Review_DTO dto) {
		
		DB_Connect();
		
		sql = "INSERT INTO 리뷰 (리뷰번호, 레시피번호, 리뷰내용, 작성시간, 평점, 작성자ID),"
				+ " VALUES ( ?, ?, '?', ?, ?, ?);";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getREVIEW_NUMBER());
			pstmt.setInt(2, dto.getRECIPE_NUMVER());
			pstmt.setString(3, dto.getCONTENT());
			pstmt.setDate(4, dto.getDATE());
			pstmt.setInt(5, dto.getGRADE());
			pstmt.setString(6, dto.getID());
			
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



	//라. 댓글 등록 시 사용자가 입력한 정보와 레시피 번호를 댓글 테이블에 추가한다. 
	private boolean Insert_comment(Comments_DTO dto) {
		
		DB_Connect();
		
		sql = "INSERT INTO 댓글 (레시피번호, 작성시간, 댓글내용, 작성자ID),"
				+ " VALUES (?, '?', '?', '?');";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getRECIPE_NUMBER());
			pstmt.setDate(2, dto.getDATE());
			pstmt.setString(3, dto.getCONTENT());
			pstmt.setString(4, dto.getID());
			
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
	
	
	//SELECT
	//. 로그인 시 사용자의 ID와 비밀번호로 사용자 테이블에 사용자 존재 여부를 검색한다. 
	
	
	//나. 회원가입 시 사용자가 입력한 ID가 이미 존재하는 ID인지 검색한다.
	
	
	//다. 회원가입 창의 아이디 중복조회 클릭 시 사용자 테이블에 중복되는 아이디를 검색한다.
	
	
	//라. 사용자 프로필 조회 시 사용자 테이블에서 ID로 사용자 정보를 검색한다. 
	
	
	//마. 레시피 검색 시 카테고리로 해당 카테고리의 레시피를 검색한다.
	
	
	//바. 레시피 검색 시 레시피 이름으로 해당 이름의 레시피를 검색한다.
	
	
	//사. 레시피 검색 시 카테고리와 레시피 이름으로 카테고리와 이름이 일치하는 레시피를 검색한다.
	
	
	//아. 레시피 열람 시 레시피 번호로 해당 레시피를 검색하여 레시피 정보를 반환한다.	
	
	
	//자. 레시피 열람 시 레시피 번호로 해당 레시피의 리뷰를 검색한다.
	
	
	//차. 레시피 열람 시 레시피 번호로 해당 레시피의 댓글을 검색하여 반환한다.
	
	
	//카. 리뷰검색 시 리뷰 이름으로 해당 이름의 리뷰를 검색한다.
	
	
	//타. 리뷰 열람 시 리뷰번호와 레시피 번호로 해당 리뷰를 검색하여 데이터를 반환한다.

	
	//DELETE
	//가. 레시피 삭제 시, 레시피 번호로 레시피 테이블에서 해당 레시피를 삭제한다.
	private boolean Delete_recipe(int Recipe_num) {
		
		DB_Connect();
		
		sql = "delete table 레시피 whrer 레시피번호 = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, Recipe_num);
			
			ex_num = pstmt.executeUpdate(sql);
			
			if(ex_num == 1) {
				return true;
			}
			else throw(null);
			
		}catch(Exception e) {
			return false;
		}
		finally {
			End_of_use();
		}	
	}
	
	//CallableStatement 구현--------------------------------------------------------
	
	//1. 사용자가 추천 레시피 버튼을 누르면 사용자의 등급을 입력값으로 받아 적절한 난이도의 레시피를 반환하는 
	// 추천 레시피 프로시저를 호출한다. 
	private void Recommend_recipe(String grade) {
		
		DB_Connect();
		
		rs = null;
		sql = "{call 추천_레시피(?,?)}";
		
		try {
			
			callstmt = con.prepareCall(sql);
			
			callstmt.registerOutParameter(1,OracleTypes.CURSOR);
			
			callstmt.setString(2,grade);
			callstmt.executeUpdate();
			
			rs = (ResultSet)callstmt.getObject(1);
						
			
			while(rs.next()) {
				System.out.printf("레시피번호: [%s] ",rs.getString("레시피번호"));
				System.out.printf("제목: [%s] ",rs.getString("제목"));
				System.out.printf("카테고리: [%s]\n ",rs.getString("카테고리"));
			}
			
			End_of_use();
		}catch(Exception e) {
			
		}	
	}
	
	 // 2. 사용자 탈퇴 시 사용자가 선택한 옵션을 입력값으로 받아 사용자가 작성했던 레시피와 리뷰, 댓글의 작성자를 
	 // 임시계정으로 변경 혹은 삭제하는 탈퇴 프로시저를 호출한다.
	private boolean User_Delete(String user_id, int option) {
		
		DB_Connect();
		
		sql = "{call 사용자_탈퇴(?,?)}";
		
		try {
			callstmt = con.prepareCall(sql);
			
			callstmt.setString(1,user_id);
			callstmt.setInt(2,option);
						
			ex_num = callstmt.executeUpdate();
			
			if(ex_num != 0) {
				return true;
			}
			else throw(null);
			
		}catch(Exception e) {
			return false;
		}
		finally {
			End_of_use();
		}	
	}
	
	private void test() {
		
		//메소드 테스트 함수 실행시킬 함수를 안에 넣고 시작
		DB_Connect();
		
		sql = "{call 사용자_탈퇴(?,?)}";
		
		try {
			callstmt = con.prepareCall(sql);
			
			callstmt.setString(1,"vf5ZiKm");
			callstmt.setInt(2,1);
						
			ex_num = callstmt.executeUpdate();
			
			if(ex_num != 0) {
				System.out.println("적용완료");
				
			}
			else {
				System.out.println("해당하는 데이터가 없거나 적용실패");
			}
			callstmt.close(); // End_of_use() 사용 시 rs 사용 안하고 close() 하면 오류
			con.close();
		}catch(Exception e) {
		}


	}
	
	
	public static void main(String[] args) {
		DAO dao = new DAO();
		dao.test();
	}


}
