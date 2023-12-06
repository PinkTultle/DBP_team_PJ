package DBA;

import javax.xml.crypto.Data;

public class Recipe_DTO {
	
	private int RECIPE_NUMBER;	//레시피번호
	private String TITLE;		//제목
	private String ID;			//작성자ID
	private String CONRENT;		//작성내용
	private Data DATE;			//작성날짜
	private String CATEGORY;	//카테고리
	private String DESCRIPTION;	//레시피설명
	private int VIEW_COUNT;		//조회수
	private int RECOMMEND;		//추천수
	private int LEVEL;			//난이도
	
	//아래는 설정자
	public void setDATE(Data dATE) {					this.DATE = dATE;}
	public void setRECIPE_NUMBER(int recipe_number) {	this.RECIPE_NUMBER = recipe_number;}
	public void setTITLE(String tITLE) {				this.TITLE = tITLE;}
	public void setID(String iD) {						this.ID = iD;}
	public void setCONRENT(String cONRENT) {			this.CONRENT = cONRENT;}
	public void setCATEGORY(String cATEGORY) {			this.CATEGORY = cATEGORY;}
	public void setDESCRIPTION(String dESCRIPTION) {	this.DESCRIPTION = dESCRIPTION;	}
	public void setVIEW_COUNT(int vIEW_COUNT) {			this.VIEW_COUNT = vIEW_COUNT;}
	public void setRECOMMEND(int rECOMMEND) {			this.RECOMMEND = rECOMMEND;}
	public void setLEVEL(int lEVEL) {					this.LEVEL = lEVEL;}

	//아래는 접근자
	public Data getDATE() {				return DATE;}
	public String getTITLE() {			return TITLE;}
	public String getID() {				return ID;}
	public String getCONRENT() {		return CONRENT;	}
	public String getCATEGORY() {		return CATEGORY;}
	public String getDESCRIPTION() {	return DESCRIPTION;	}
	public int getRECIPE_NUMBER() {		return RECIPE_NUMBER;}
	public int getVIEW_COUNT() {		return VIEW_COUNT;	}
	public int getRECOMMEND() {			return RECOMMEND;}
	public int getLEVEL() {				return LEVEL;}
	

}
