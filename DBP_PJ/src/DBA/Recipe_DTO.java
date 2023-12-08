package DBA;

import java.sql.Date;

public class Recipe_DTO {
	
	private int RECIPE_NUMBER;	//레시피번호
	private String TITLE;		//제목
	private String ID;			//작성자ID
	private String CONTENT;		//작성내용
	private Date DATE;			//작성날짜
	private String CATEGORY;	//카테고리
	private String DESCRIPTION;	//레시피설명
	private int VIEW_COUNT;		//조회수
	private int RECOMMEND_COUNT;		//추천수
	private int LEVEL;			//난이도
	
	//생성자
	public Recipe_DTO() {
		super();
	}
	
	public Recipe_DTO(int RECIPE_NUMBER, String TITLE, String ID, String CONTENT, Date DATE,
						String CATEGORY, String DESCRIPTION, int VIEW_COUNT, int RECOMMEND_COUNT,
						int LEVEL) {
		
		this.RECIPE_NUMBER = RECIPE_NUMBER;
		this.TITLE = TITLE;
		this.ID = ID;
		this.CONTENT = CONTENT;
		this.DATE = DATE;
		this.CATEGORY = CATEGORY;
		this.DESCRIPTION = DESCRIPTION;
		this.VIEW_COUNT = VIEW_COUNT;
		this.RECOMMEND_COUNT = RECOMMEND_COUNT;
		this.LEVEL = LEVEL;
	}
	
	//아래는 설정자
	public void setDATE(Date DATE) {					this.DATE = DATE;}
	public void setRECIPE_NUMBER(int recipe_number) {	this.RECIPE_NUMBER = recipe_number;}
	public void setTITLE(String tITLE) {				this.TITLE = tITLE;}
	public void setID(String iD) {						this.ID = iD;}
	public void setCONTENT(String cONTENT) {			this.CONTENT = cONTENT;}
	public void setCATEGORY(String cATEGORY) {			this.CATEGORY = cATEGORY;}
	public void setDESCRIPTION(String dESCRIPTION) {	this.DESCRIPTION = dESCRIPTION;	}
	public void setVIEW_COUNT(int vIEW_COUNT) {			this.VIEW_COUNT = vIEW_COUNT;}
	public void setRECOMMEND_COUNT(int RECOMMEND_COUNT) {this.RECOMMEND_COUNT = RECOMMEND_COUNT;}
	public void setLEVEL(int lEVEL) {					this.LEVEL = lEVEL;}

	//아래는 접근자
	public Date getDATE() {				return DATE;}
	public String getTITLE() {			return TITLE;}
	public String getID() {				return ID;}
	public String getCONTENT() {		return CONTENT;	}
	public String getCATEGORY() {		return CATEGORY;}
	public String getDESCRIPTION() {	return DESCRIPTION;	}
	public int getRECIPE_NUMBER() {		return RECIPE_NUMBER;}
	public int getVIEW_COUNT() {		return VIEW_COUNT;	}
	public int getRECOMMEND_COUNT() {	return RECOMMEND_COUNT;}
	public int getLEVEL() {				return LEVEL;}
	
	

}
