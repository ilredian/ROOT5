package DTO;

public class MessageDTO {
	private int messageno; // 쪽지 고유 번호
	private int frommemberno; // 보낸이
	private String title; // 제목
	private String content; // 내용
	private String regdate; // 보낸 일시
	private int isopen; // 읽음 여부 0안읽음 1읽음
	private int memberno; // 받는이
	
	public int getMessageno() {
		return messageno;
	}
	public void setMessageno(int messageno) {
		this.messageno = messageno;
	}
	public int getFrommemberno() {
		return frommemberno;
	}
	public void setFrommemberno(int frommemberno) {
		this.frommemberno = frommemberno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getIsopen() {
		return isopen;
	}
	public void setIsopen(int isopen) {
		this.isopen = isopen;
	}
	public int getMemberno() {
		return memberno;
	}
	public void setMemberno(int memberno) {
		this.memberno = memberno;
	}
}
