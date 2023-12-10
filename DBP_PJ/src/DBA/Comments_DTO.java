package DBA;

import java.sql.Date;

public class Comments_DTO {
	
	private int COMMENT_NUMBER;
	private int RECIPE_NUMBER;
	private Date DATE;
	private String CONTENT;
	private String ID;
	
	
	//생성자
	public Comments_DTO() {
		super();
	}
	
	public Comments_DTO(int Comment_number, int Recipe_number, Date Date,
						String Content, String Id) {
		this.COMMENT_NUMBER = Comment_number;
		this.RECIPE_NUMBER = Recipe_number;
		this.DATE = Date;
		this.CONTENT = Content;
		this.ID = Id;
	}
	
	//아래는 접근자
	public int getCOMMENT_NUMBER() {	return COMMENT_NUMBER;}
	public int getRECIPE_NUMBER() {		return RECIPE_NUMBER;}
	public Date getDATE() {				return DATE;}
	public String getCONTENT() {		return CONTENT;}
	public String getID() {				return ID;}

	//아래는 설정자
	public void setCOMMENT_NUMBER(int Comment_number) {	this.COMMENT_NUMBER = Comment_number;}
	public void setRECIPE_NUMBER(int Recipe_number) {	this.RECIPE_NUMBER = Recipe_number;}
	public void setDATE(Date Date) {					this.DATE = Date;}
	public void setCONTENT(String Content) {			this.CONTENT = Content;}
	public void setID(String Id) {						this.ID = Id;}
	
}