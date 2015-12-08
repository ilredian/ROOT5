package DTO;

public class chartDTO {
	private String regdate;
	private int count;
	public String getDay() {
		return regdate;
	}
	public void setDay(String regdate) {
		this.regdate = regdate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "['" + regdate + "', " + count + "]";
	}
}
