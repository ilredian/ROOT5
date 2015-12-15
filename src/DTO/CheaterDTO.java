package DTO;

import java.sql.Date;

public class CheaterDTO {
	
	private int stateno; /* 진술서번호 */
	private String bankname; /* 은행명 */
	private String cheatername; /* 명의자 성명 */
	private String account; /* 계좌번호 */
	private int deposit; /* 입금 금액 */
	private String depositdate; /* 입금일 */
	private String phone; /* 연락처 */
	private String sex; /* 성별 */
	private String feature; /* 용의자특징 */
	private String domain; /* 사이트명 */
	private String goodskind; /* 거래물품종류 */
	private String goodsname; /* 물품명 */
	private String cheaterid; /* 용의자아이디 */
	private String link; /* 사기게시물링크 */
	private Date regdate; /* 등록일 */
	private String content; /* 내용 */
	private int cheatno; /* cheatDTO에 있는 사기 번호 */
	private int memberno; /* memberDTO에 있는 회원번호 */
	private int count;//통계 수
	
	private int police; //담당 형사 memberno
	private String trace; //추적 정보
	private String complete; //완료 정보
	
	public int getPolice() {
		return police;
	}

	public void setPolice(int police) {
		this.police = police;
	}

	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public CheaterDTO(String bankname, String cheatername, String account, int deposit, String depositdate,
			String phone, String sex, String feature, String domain, String goodskind, String goodsname,
			String cheaterid, String link, String content, int cheatno, int memberno) {
		super();
		this.bankname = bankname;
		this.cheatername = cheatername;
		this.account = account;
		this.deposit = deposit;
		this.depositdate = depositdate;
		this.phone = phone;
		this.sex = sex;
		this.feature = feature;
		this.domain = domain;
		this.goodskind = goodskind;
		this.goodsname = goodsname;
		this.cheaterid = cheaterid;
		this.link = link;
		this.content = content;
		this.cheatno = cheatno;
		this.memberno = memberno;
	}
	
	public CheaterDTO() {
		super();
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getStateno() {
		return stateno;
	}
	public void setStateno(int stateno) {
		this.stateno = stateno;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getCheatername() {
		return cheatername;
	}
	public void setCheatername(String cheatername) {
		this.cheatername = cheatername;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public String getDepositdate() {
		return depositdate;
	}
	public void setDepositdate(String depositdate) {
		this.depositdate = depositdate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getGoodskind() {
		return goodskind;
	}
	public void setGoodskind(String goodskind) {
		this.goodskind = goodskind;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getCheaterid() {
		return cheaterid;
	}
	public void setCheaterid(String cheaterid) {
		this.cheaterid = cheaterid;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCheatno() {
		return cheatno;
	}
	public void setCheatno(int cheatno) {
		this.cheatno = cheatno;
	}
	public int getMemberno() {
		return memberno;
	}
	public void setMemberno(int memberno) {
		this.memberno = memberno;
	}
	
	@Override
	public String toString() {
		return "CheaterDTO [stateno=" + stateno + ", bankname=" + bankname + ", cheatername=" + cheatername
				+ ", account=" + account + ", deposit=" + deposit + ", depositdate=" + depositdate + ", phone=" + phone
				+ ", sex=" + sex + ", feature=" + feature + ", domain=" + domain + ", goodskind=" + goodskind
				+ ", goodsname=" + goodsname + ", cheaterid=" + cheaterid + ", link=" + link + ", regdate=" + regdate
				+ ", content=" + content + ", cheatno=" + cheatno + ", memberno=" + memberno + "]";
	}
}
