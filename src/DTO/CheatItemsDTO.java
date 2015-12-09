package DTO;

public class CheatItemsDTO {
	private String goodskind; /* 물품명영어 */
	private String goodsname; /* 물품명한글 */
	private String goodsspan; /* 물품태그 */
	
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
	public String getGoodsspan() {
		return goodsspan;
	}
	public void setGoodsspan(String goodsspan) {
		this.goodsspan = goodsspan;
	}
	@Override
	public String toString() {
		return "CheatItemsDTO [goodskind=" + goodskind + ", goodsname=" + goodsname + ", goodsspan=" + goodsspan + "]";
	}
	
}
