package DBA;

public class User_DTO {
	
	private String ID;
	private String PW;
	private String Name;
	private String Email;
	private String Number;
	private String Rating;
	private int Write_count;
	
	//생성자
	public User_DTO() {
		super();
	}
	
	public User_DTO(String id, String pw, String name, String email, String number, String rating, int count) {
		this.ID = id;
		this.PW = pw;
		this.Name = name;
		this.Email = email;
		this.Number = number;
		this.Rating = rating;
		this.Write_count = count;
	}
	
	//아래는 설정자
	public void setID(String id) {this.ID = id;}
	public void setPW(String pw) {this.PW = pw;}
	public void setNAME(String name) {this.Name = name;}
	public void setEMAIL(String email) {this.Email = email;}
	public void setNUMBER(String number) {this.Number = number;}
	public void setRATING(String rating) {this.Rating = rating;}
	public void setWRITE_COUNT(int count) {this.Write_count = count;}
	
	//아래는 접근자
	public String getID() {return ID;}
	public String getPW() {return PW;}
	public String getNAME() {return Name;}
	public String getEMAIL() {return Email;}
	public String getNUMBER() {return Number;}
	public String getRATING() {return Rating;}
	public int getWRITE_COUNT() {return Write_count;}
	
}
