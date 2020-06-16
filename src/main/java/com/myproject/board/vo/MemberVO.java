package com.myproject.board.vo;

import java.sql.Date;

public class MemberVO{
	
	private int member_no;
	private String member_id;
	private String password;
	private String name;
	private int gender;
	private String birth;
	private String mail;
	private String phone;
	private String address;
	private String secession;
	private Date regdate;
	private String profilepic;
	
	public MemberVO() {	}

	public MemberVO(int member_no, String member_id, String password, String name, int gender, String birth,
			String mail, String phone, String address, String secession, Date regdate, String profilepic) {
		super();
		this.member_no = member_no;
		this.member_id = member_id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.mail = mail;
		this.phone = phone;
		this.address = address;
		this.secession = secession;
		this.regdate = regdate;
		this.profilepic = profilepic;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSecession() {
		return secession;
	}

	public void setSecession(String secession) {
		this.secession = secession;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}	
	
	public String getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}

	@Override
	public String toString() {
		return "MemberVO [member_no=" + member_no + ", member_id=" + member_id + ", password=" + password + ", name="
				+ name + ", gender=" + gender + ", birth=" + birth + ", mail=" + mail + ", phone=" + phone
				+ ", address=" + address + ", secession=" + secession + ", regdate=" + regdate + ", profilepic="+ profilepic +"]";
	}
	
	
}