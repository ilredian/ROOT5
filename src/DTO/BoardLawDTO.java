package DTO;

import java.sql.Date;

public class BoardLawDTO {

	private int boardno; //게시판번호
	private String title;/* 제목 */
	private String content; /* 내용 */
	private Date regdate; /* 작성일 */
	private int countno;/* 조회수 */
	private int active; /* 삭제여부 */
	private String name;	//작성자 이름	
	private String company;// 소속
	private String phone; //전화번호
	private String tel;//회사번호
	private String career;//경력
	private String message;//한줄 소개 ex 과거 뭐했던 어디 소속 뭐 전문 변호사
	private String edu;// 학력
	private String fee;//요금
	private String place;//번호사 위치
	private int memberno; // 멤버 등록 번호
	
	//12월 16일 추가++
	private String photo;
	private int boardReplyCount;//게시글 당 리플 갯수
	private int categoryno; /* 카테고리번호 */
	private int groupno; //게시글 그룹 	 
	private int step; //그룹 내 게시글 순서
	private int depth; //댓글 들여쓰기
	
	
	public int getCategoryno() {
		return categoryno;
	}
	public void setCategoryno(int categoryno) {
		this.categoryno = categoryno;
	}
	public int getGroupno() {
		return groupno;
	}
	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getBoardReplyCount() {
		return boardReplyCount;
	}
	
	public void setBoardReplyCount(int boardReplyCount) {
		this.boardReplyCount = boardReplyCount;
	}


	public int getBoardno() {
		return boardno;
	}


	public int getMemberno() {
		return memberno;
	}


	public BoardLawDTO(int boardno, String title, String content, Date regdate, int countno, int active, String name,
			String company, String phone, String tel, String career, String message, String edu, String fee,
			String place, int memberno) {
		super();
		this.boardno = boardno;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.countno = countno;
		this.active = active;
		this.name = name;
		this.company = company;
		this.phone = phone;
		this.tel = tel;
		this.career = career;
		this.message = message;
		this.edu = edu;
		this.fee = fee;
		this.place = place;
		this.memberno = memberno;
	}


	public void setMemberno(int memberno) {
		this.memberno = memberno;
	}


	public void setBoardno(int boardno) {
		this.boardno = boardno;
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


	public Date getRegdate() {
		return regdate;
	}


	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}


	public int getCountno() {
		return countno;
	}


	public void setCountno(int countno) {
		this.countno = countno;
	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getCareer() {
		return career;
	}


	public void setCareer(String career) {
		this.career = career;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getEdu() {
		return edu;
	}


	public void setEdu(String edu) {
		this.edu = edu;
	}


	public String getFee() {
		return fee;
	}


	public void setFee(String fee) {
		this.fee = fee;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public BoardLawDTO(){};
	
	
	public BoardLawDTO(int boardno, String title, String content, Date regdate, int countno, int active, int categoryno,
			String name, String company, String phone, String tel, String career, String message, String edu,
			String fee, String memberno, String place) {
		super();
		this.boardno = boardno;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.countno = countno;
		this.active = active;
		this.name = name;
		this.company = company;
		this.phone = phone;
		this.tel = tel;
		this.career = career;
		this.message = message;
		this.edu = edu;
		this.fee = fee;
		this.place = place;
	}



	@Override
	public String toString() {
		return "BoardLawDTO [boardno=" + boardno + ", title=" + title + ", content=" + content + ", regdate=" + regdate
				+ ", countno=" + countno + ", active=" + active + ", name=" + name + ", company=" + company + ", phone="
				+ phone + ", tel=" + tel + ", career=" + career + ", message=" + message + ", edu=" + edu + ", fee="
				+ fee + ", place=" + place + ", memberno=" + memberno + "]";
	}
	
	
}
