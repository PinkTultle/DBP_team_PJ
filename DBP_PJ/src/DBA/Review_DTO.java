package DBA;

import java.sql.Date;

public class Review_DTO {
	
	private int REVIEW_NUMBER;
	private int RECIPE_NUMVER;
	private String CONTENT;
	private Date DATE;
	private int GRADE;
	private String ID;
	
	//생성자
	public Review_DTO() {
		super();
	}
	
	public Review_DTO(int Review_number, int Recipe_number, String Content,
					Date Date, int Grade, String Id) {
		this.REVIEW_NUMBER = Review_number;
		this.RECIPE_NUMVER = Recipe_number;
		this.CONTENT = Content;
		this.DATE = Date;
		this.GRADE = Grade;
		this.ID = Id;
	}
	
	//아래는 접근자
	public int getREVIEW_NUMBER() {	return REVIEW_NUMBER;}
	public int getRECIPE_NUMVER() { return RECIPE_NUMVER;}
	public String getCONTENT() {	return CONTENT;}
	public Date getDATE() {			return DATE;}
	public int getGRADE() {			return GRADE;}
	public String getID() { 		return ID;}

	//아래는 설정자
	public void setREVIEW_NUMBER(int REVIEW_NUMBER) {	this.REVIEW_NUMBER = REVIEW_NUMBER;	}
	public void setRECIPE_NUMVER(int RECIPE_NUMVER) {	this.RECIPE_NUMVER = RECIPE_NUMVER;}
	public void setCONTENT(String CONTENT){				this.CONTENT = CONTENT; }
	public void setDATE(Date DATE) {					this.DATE = DATE;}
	public void setGRADE(int GRADE) {					this.GRADE = GRADE;}
	public void setID(String id) {						this.ID = id;}
	
	
}
