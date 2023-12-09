package DBA;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
	private ResultSet rs = null;
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
			if(!con.isClosed()) con.close();
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(!pstmt.isClosed()) pstmt.close();
			if(callstmt != null) callstmt.close();
			sql = null;
			ex_num = 0;
		} catch (SQLException e) {
			return ;
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
	public boolean Delete_comment(int recipe_num, int comment_num) {
		
		DB_Connect();
		sql = "delete from 댓글 where 레시피번호 = "+ recipe_num + " and 댓글번호 = "+ comment_num;
		
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
	public boolean Delete_review(int recipe_num, int review_num) {
		
		DB_Connect();
		sql = "delete from 댓글 where 레시피번호 = "+ recipe_num + " and 리뷰번호 = "+ review_num;
		
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
	public boolean Insert_user(User_DTO dto) {
		
		DB_Connect();
		
		sql = "INSERT INTO 사용자 (사용자ID, 이름, 이메일, 전화번호, 비밀번호, 총작성수, 등급),"
				+ " VALUES (?, ?, ?, ?, ?, 0, null)";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getID());
			pstmt.setString(2, dto.getNAME());
			pstmt.setString(3, dto.getEMAIL());
			pstmt.setString(4, dto.getNUMBER());
			pstmt.setString(5, dto.getPW());
			
			ex_num = pstmt.executeUpdate();
			
			//성공시 true 반환
			if(ex_num == 1) {
				return true;
			}else throw(null);
			
		} 
		//실패시 에러 발생 false 반환
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			//마지막에 연결 끊기 및 객체 초기화
			End_of_use();
		}		
	}
	
    //나. 레시피 등록 시 사용자가 입력한 정보를 레시피 테이블에 추가한다. 
	public boolean Insert_recipe(Recipe_DTO dto) {
		
		DB_Connect();
		
		sql = "INSERT INTO 레시피 (제목, 작성자ID, 작성내용, 작성시간, 카테고리, 레시피설명, 조회수, 추천수, 난이도),"
				+ " VALUES (?, ?, ?, ?, ?, ?, 0, 0, ?)";
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
			
			//성공시 true 반환
			if(ex_num == 1) {
				return true;
			}else throw(null);
			
		} 
		//실패시 에러 발생 false 반환
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			//마지막에 연결 끊기 및 객체 초기화
			End_of_use();
		}		
	}
	
	//다. 리뷰등록 시 사용자가 입력한 정보와 레시피 번호를 리뷰 테이블에 추가한다.
	public boolean Insert_review(Review_DTO dto) {
		
		DB_Connect();
		
		sql = "INSERT INTO 리뷰 (리뷰번호, 레시피번호, 리뷰내용, 작성시간, 평점, 작성자ID),"
				+ " VALUES ( ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getREVIEW_NUMBER());
			pstmt.setInt(2, dto.getRECIPE_NUMVER());
			pstmt.setString(3, dto.getCONTENT());
			pstmt.setDate(4, dto.getDATE());
			pstmt.setInt(5, dto.getGRADE());
			pstmt.setString(6, dto.getID());
			
			ex_num = pstmt.executeUpdate();
			
			//성공시 true 반환
			if(ex_num == 1) {
				return true;
			}else throw(null);
			
		} 
		//실패시 에러 발생 false 반환
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			//마지막에 연결 끊기 및 객체 초기화
			End_of_use();
		}		
	}

	//라. 댓글 등록 시 사용자가 입력한 정보와 레시피 번호를 댓글 테이블에 추가한다. 
	public boolean Insert_comment(Comments_DTO dto) {
		
		DB_Connect();
		
		sql = "INSERT INTO 댓글 (레시피번호, 작성시간, 댓글내용, 작성자ID),"
				+ " VALUES (?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getRECIPE_NUMBER());
			pstmt.setDate(2, dto.getDATE());
			pstmt.setString(3, dto.getCONTENT());
			pstmt.setString(4, dto.getID());
			
			ex_num = pstmt.executeUpdate();
			
			//성공시 true 반환
			if(ex_num == 1) {
				return true;
			}else throw(null);
			
		} 
		//실패시 에러 발생 false 반환
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			//마지막에 연결 끊기 및 객체 초기화
			End_of_use();
		}		
	}
	
	//SELECT
	//. 로그인 시 사용자의 ID와 비밀번호로 사용자 테이블에 사용자 존재 여부를 검색한다. 
	public boolean Login_check(String id,String pw) {
		DB_Connect();
		
		sql = "SELECT * from 사용자 where 사용자ID = ? and 비밀번호 = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			//검색 결과가 있다 -> 해당 계정 및 비밀번호 존재
			//true 반환 : 로그인 성공
			ResultSet result = pstmt.executeQuery();
			if(result.isBeforeFirst()) {
				return true;
			}else throw(null);
			
		}
		//해당 id, pw일치 계정 없음!
		//false 반환 : 로그인실패
		catch(Exception e) {
			
			return false;
		}finally {
			//마지막에 연결 끊기 및 객체 초기화
			
			End_of_use();
		}
	}
	
	//나. 회원가입 시 사용자가 입력한 ID가 이미 존재하는 ID인지 검색한다.
	public boolean overlap_check(String id) {
		DB_Connect();
		
		sql = "SELECT * from 사용자 where 사용자ID = '?';";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			//중복 아이디가 없을 경우 true반환
			if(!pstmt.execute()) {
				return true;
			}else throw(null);
			
		}
		//중복 아이디가 존재하면 false반환
		catch(Exception e) {
			return false;
		}finally {
			End_of_use();
		}
	}	
	
	//다. 사용자 프로필 조회 시 사용자 테이블에서 ID로 사용자 정보를 검색한다. 
	public User_DTO Query_user_profile(String id) {
		
		DB_Connect();
		User_DTO dto = new User_DTO();
		sql = "SELECT * from 사용자 where 사용자ID = ? ";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			dto.setID(rs.getString("사용자ID"));
			dto.setNAME(rs.getString("이름"));
			dto.setEMAIL(rs.getString("이메일"));
			dto.setNUMBER(rs.getString("전화번호"));
			dto.setPW(rs.getString("비밀번호"));
			dto.setWRITE_COUNT(rs.getInt("총작성수"));
			dto.setRATING(rs.getString("등급"));	
			
			return dto;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			End_of_use();
		}	
	}
	
	//라. 레시피 검색 시 카테고리로 해당 카테고리의 레시피를 검색한다.
	public Vector<Recipe_DTO> Search_by_recipe_category(String category) {
		
		DB_Connect();
		Vector<Recipe_DTO> list = new Vector<Recipe_DTO>();
		
		category += "%";
		sql = "SELECT * from 레시피 where 카테고리 = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			
			rs = pstmt.executeQuery();
						
			while(rs.next()){
				Recipe_DTO dto = new Recipe_DTO();
				
				dto.setRECIPE_NUMBER(rs.getInt("레시피번호"));
				dto.setTITLE(rs.getString("제목"));
				dto.setID(rs.getString("작성자ID"));
				dto.setCONTENT(rs.getString("작성내용"));
				dto.setDATE(rs.getDate("작성시간"));
				dto.setCATEGORY(rs.getString("카테고리"));
				dto.setDESCRIPTION(rs.getString("레시피설명"));
				dto.setVIEW_COUNT(rs.getInt("조회수"));
				dto.setRECOMMEND_COUNT(rs.getInt("추천수"));
				dto.setLEVEL(rs.getInt("난이도"));
				
				list.add(dto);
				
			}
			return list;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			End_of_use();
		}	
	}
	
	
	
	//마. 레시피 검색 시 레시피 이름으로 해당 이름의 레시피를 검색한다.
	public Vector<Recipe_DTO> Search_by_recipe_title(String Title) {
		
		DB_Connect();
		Vector<Recipe_DTO> list = new Vector<Recipe_DTO>();
	
		Title = "%"+Title+"%";
		sql = "SELECT * from 레시피 where 제목 Like ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Title);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				Recipe_DTO dto = new Recipe_DTO();
				
				dto.setRECIPE_NUMBER(rs.getInt("레시피번호"));
				dto.setTITLE(rs.getString("제목"));
				dto.setID(rs.getString("작성자ID"));
				dto.setCONTENT(rs.getString("작성내용"));
				dto.setDATE(rs.getDate("작성시간"));
				dto.setCATEGORY(rs.getString("카테고리"));
				dto.setDESCRIPTION(rs.getString("레시피설명"));
				dto.setVIEW_COUNT(rs.getInt("조회수"));
				dto.setRECOMMEND_COUNT(rs.getInt("추천수"));
				dto.setLEVEL(rs.getInt("난이도"));
				
				list.add(dto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			End_of_use();
		}	
	}
	
	//바. 레시피 검색 시 카테고리와 레시피 이름으로 카테고리와 이름이 일치하는 레시피를 검색한다.
	public Vector<Recipe_DTO> Search_by_recipe_cate_title(String category, String title) {
		
		DB_Connect();
		Vector<Recipe_DTO> list = new Vector<Recipe_DTO>();
	
		title = "%"+title+"%";
		category += "%";
		sql = "SELECT * from \"레시피\" where 카테고리 = ? and 제목 LIKE ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, title);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				Recipe_DTO dto = new Recipe_DTO();
				
				dto.setRECIPE_NUMBER(rs.getInt("레시피번호"));
				dto.setTITLE(rs.getString("제목"));
				dto.setID(rs.getString("작성자ID"));
				dto.setCONTENT(rs.getString("작성내용"));
				dto.setDATE(rs.getDate("작성시간"));
				dto.setCATEGORY(rs.getString("카테고리"));
				dto.setDESCRIPTION(rs.getString("레시피설명"));
				dto.setVIEW_COUNT(rs.getInt("조회수"));
				dto.setRECOMMEND_COUNT(rs.getInt("추천수"));
				dto.setLEVEL(rs.getInt("난이도"));
								
				list.add(dto);
				
			}
			
			
			return list;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			End_of_use();
		}	
	}
	
	//사. 레시피 열람 시 레시피 번호로 해당 레시피를 검색하여 레시피 정보를 반환한다.
	public Recipe_DTO Look_up_recipes(int recipe_num) {
		
		DB_Connect();
		Recipe_DTO dto = new Recipe_DTO();
		sql = "SELECT * from 레시피 where 레시피번호 = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, recipe_num);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			dto.setRECIPE_NUMBER(rs.getInt("레시피번호"));
			dto.setTITLE(rs.getString("제목"));
			dto.setID(rs.getString("작성자ID"));
			dto.setCONTENT(rs.getString("작성내용"));
			dto.setDATE(rs.getDate("작성시간"));
			dto.setCATEGORY(rs.getString("카테고리"));
			dto.setDESCRIPTION(rs.getString("레시피설명"));
			dto.setVIEW_COUNT(rs.getInt("조회수"));
			dto.setRECOMMEND_COUNT(rs.getInt("추천수"));
			dto.setLEVEL(rs.getInt("난이도"));
			
			return dto;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			End_of_use();
		}	
	}
	
	//아. 레시피 열람 시 레시피 번호로 해당 레시피의 리뷰를 검색한다.
	public Vector<Review_DTO> Search_for_reviews(int recipe_num) {
		
		DB_Connect();
		Vector<Review_DTO> list = new Vector<Review_DTO>();
	
		sql = "SELECT * from 리뷰 where 레시피번호 = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, recipe_num);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				Review_DTO dto = new Review_DTO();
				
				dto.setREVIEW_NUMBER(rs.getInt("리뷰번호"));
				dto.setRECIPE_NUMVER(rs.getInt("레시피번호"));
				dto.setCONTENT(rs.getString("리뷰내용"));
				dto.setDATE(rs.getDate("작성시간"));
				dto.setGRADE(rs.getInt("평점"));
				dto.setID(rs.getString("작성자ID"));				
				
				list.add(dto);
				
			}
				
			return list;
					
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			End_of_use();
		}	
	}
	
	//자. 레시피 열람 시 레시피 번호로 해당 레시피의 댓글을 검색하여 반환한다.
	public Vector<Comments_DTO> Search_for_comments(int recipe_num) {
		
		DB_Connect();
		Vector<Comments_DTO> list = new Vector<Comments_DTO>();
	
		sql = "SELECT * from 댓글 where 레시피번호 = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, recipe_num);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				Comments_DTO dto = new Comments_DTO();
				
				dto.setCOMMENT_NUMBER(rs.getInt("댓글번호"));
				dto.setRECIPE_NUMBER(rs.getInt("레시피번호"));
				dto.setDATE(rs.getDate("작성시간"));
				dto.setCONTENT(rs.getString("댓글내용"));
				dto.setID(rs.getString("작성자ID"));
				
				
				list.add(dto);
				
			}
				
			return list;
					
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			End_of_use();
		}	
	}
	
	//차. 리뷰검색 시 리뷰 이름으로 해당 이름의 리뷰를 검색한다.
	public Vector<Review_DTO> Search_by_review_title(String Title) {
		
		DB_Connect();
		Vector<Review_DTO> list = new Vector<Review_DTO>();
	
		Title = "%"+Title+"%";
		sql = "SELECT * from 레시피 where 제목 Like ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Title);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				Review_DTO dto = new Review_DTO();
				
				dto.setREVIEW_NUMBER(rs.getInt("리뷰번호"));
				dto.setRECIPE_NUMVER(rs.getInt("레시피번호"));
				dto.setCONTENT(rs.getString("리뷰내용"));
				dto.setDATE(rs.getDate("작성시간"));
				dto.setGRADE(rs.getInt("평점"));
				dto.setID(rs.getString("작성자ID"));	
				
				list.add(dto);
				
			}
			
			return list;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			End_of_use();
		}	
	}
	
	//카. 리뷰 열람 시 리뷰번호와 레시피 번호로 해당 리뷰를 검색하여 데이터를 반환한다.
	public Review_DTO Look_up_review(int review_num, int recipe_num) {
		
		DB_Connect();
		Review_DTO dto = new Review_DTO();
		sql = "SELECT * from 리뷰 where 리뷰번호 = ? and 레시피번호 = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			pstmt.setInt(2, recipe_num);			
			
			rs = pstmt.executeQuery();
			
			rs.next();

			dto.setREVIEW_NUMBER(rs.getInt("리뷰번호"));
			dto.setRECIPE_NUMVER(rs.getInt("레시피번호"));
			dto.setCONTENT(rs.getString("리뷰내용"));
			dto.setDATE(rs.getDate("작성시간"));
			dto.setGRADE(rs.getInt("평점"));
			dto.setID(rs.getString("작성자ID"));	
			
			return dto;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			End_of_use();
		}	
	}
	
	
	//DELETE
	//가. 레시피 삭제 시, 레시피 번호로 레시피 테이블에서 해당 레시피를 삭제한다.
	public boolean Delete_recipe(int Recipe_num) {
		
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
	
	
	
	
	public String toKR(String str) {
		  if (str == null || str.trim().equals("")) return "";
		  try{
		    return new String(str.getBytes("ISO-8859-1"), "AL32UTF8");
		  }catch(UnsupportedEncodingException ex){
		    return null;
		  }
		}
	
	private void test() {
		Review_DTO dto = new Review_DTO();
		dto = Look_up_review(1, 1);
		System.out.println(dto.getDATE().toString());
		System.out.print(dto);
		
		
		return ;
	}
	
	
	public static void main(String[] args) {
		DAO dao = new DAO();
		dao.test();
	}


}
