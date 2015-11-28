package DTO;

import java.util.Date;

public class MemberDTO {
	
	private int memberno;/* 회원번호 Auto_increment(시퀀스) */
	private String email;/* 이메일 */
	private String password;/* 비밀번호 */
	private String name;/* 이름 */
	private String phone;/* 휴대전화 */
	private Date regdate;/* 가입일 */
	private int active;/* 활성화 DEFAULT 0 */
	private Date regpwd;/* 비밀번호변경일 */
	private int point;/* 포인트 DEFAULT 0 */
	private int emailconfirm;/* 메일인증 DEFAULT 0 */
	private String photo;/* 사진 */
	private String message;/* 인사말 DEFAULT '안녕하세요.' */
	private int typeno;/* 타입번호 비인증0, 인증1, 경찰2, 어드민3 추가시 수정 */
	
	public int getMemberno() {
		return memberno;
	}
	public void setMemberno(int memberno) {
		this.memberno = memberno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Date getRegpwd() {
		return regpwd;
	}
	public void setRegpwd(Date regpwd) {
		this.regpwd = regpwd;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getEmailconfirm() {
		return emailconfirm;
	}
	public void setEmailconfirm(int emailconfirm) {
		this.emailconfirm = emailconfirm;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getTypeno() {
		return typeno;
	}
	public void setTypeno(int typeno) {
		this.typeno = typeno;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [memberno=" + memberno + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", phone=" + phone + ", regdate=" + regdate + ", active=" + active + ", regpwd=" + regpwd + ", point="
				+ point + ", emailconfirm=" + emailconfirm + ", photo=" + photo + ", message=" + message + ", typeno="
				+ typeno + "]";
	}
}
