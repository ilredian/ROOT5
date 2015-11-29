package DTO;

public class CheatDTO {

	private int cheatno; /* 사기 번호 */
	private String cheatname; /* 사기 이름 */
	
	public int getCheatno() {
		return cheatno;
	}
	public void setCheatno(int cheatno) {
		this.cheatno = cheatno;
	}
	public String getCheatname() {
		return cheatname;
	}
	public void setCheatname(String cheatname) {
		this.cheatname = cheatname;
	}
	
	@Override
	public String toString() {
		return "CheatDTO [cheatno=" + cheatno + ", cheatname=" + cheatname + "]";
	}
}
