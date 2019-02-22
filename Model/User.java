package Model;

import java.sql.Date;

public class User {

	private String userid;
	private String name;
	private String birthYear;
	private String addr;
	private String mobile1;
	private String mobile2;
	private String height;
	private String mdate;
	public User(String userid, String name, String birthYear, String addr, String mobile1, String mobile2,
			String height, String mdate) {
		super();
		this.userid = userid;
		this.name = name;
		this.birthYear = birthYear;
		this.addr = addr;
		this.mobile1 = mobile1;
		this.mobile2 = mobile2;
		this.height = height;
		this.mdate = mdate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	
	
	

	
	
}






