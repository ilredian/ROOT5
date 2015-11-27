package DTO;

import java.util.Date;

public class MemberDTO {

	private String email; //이메일
	private String pwd; //비밀번호 
	private String name; //이름(실명)
	private String cPhone; //휴대폰

	
	public MemberDTO(String email, String pwd, String name, String cPhone) {
		this.email = email;
		this.pwd = pwd;
		this.name = name;
		this.cPhone = cPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getcPhone() {
		return cPhone;
	}
	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}
	
	
	
	
	@Override
	public String toString() {
		return "MemberDTO [email=" + email + ", pwd=" + pwd + ", name=" + name + ", cPhone=" + cPhone + "]";
	}

	
	
	
}
