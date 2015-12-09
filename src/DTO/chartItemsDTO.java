package DTO;

public class chartItemsDTO {
	private String goodskind;
	private String count;
	public String getGoodskind() {
		return goodskind;
	}
	public void setGoodskind(String goodskind) {
		this.goodskind = goodskind;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "['" + goodskind + "', " + count + "]";
	}
}
