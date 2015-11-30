package common;

//boardStatement를 제외한 나머지 페이징 처리
public class HomePager {

	private int pageSize;// 한 페이지당 데이터 개수
	private int pagerSize;// 번호로 보여주는 페이지 Link 개수
	private int dataCount;// 총 데이터 수
	private int currentPage;// 현재 페이지 번호
	private int pageCount;// 총 페이지 수
	private int next;// 다음 버튼
	private int previous;// 이전 버튼

	private String linkUrl;// 페이저가 포함되는 페이지의 주소

	public HomePager(int dataCount, int currentPage, int pageSize, int pagerSize, String linkUrl) {

		this.linkUrl = linkUrl;

		this.dataCount = dataCount;
		this.pageSize = pageSize;
		this.pagerSize = pagerSize;
		this.currentPage = currentPage;
		this.pageCount = (dataCount / pageSize) + ((dataCount % pageSize) > 0 ? 1 : 0);
		this.next = (((this.currentPage - 1) / this.pagerSize) + 1) * this.pagerSize + 1;
		this.previous = ((this.currentPage - 1) / this.pagerSize) * this.pagerSize;
	}

	public String toString() {
		StringBuffer linkString = new StringBuffer();

		// 1. 처음, 이전 항목 만들기
		if (currentPage > 1) {
			linkString.append(String.format("[<a href='%s?pg=1'>처음</a>]", linkUrl));
			linkString.append("&nbsp;");
			linkString.append("&nbsp;");
			if (currentPage > 10) {
				linkString.append(String.format("[<a href='%s?pg=%d'>이전</a>]", linkUrl, previous));
			}
			linkString.append("&nbsp;");
		}

		// 2. 페이지 번호 Link 만들기
		int pagerBlock = (currentPage - 1) / pagerSize;
		int start = (pagerBlock * pagerSize) + 1;
		int end = start + pagerSize;
		for (int i = start; i < end; i++) {
			if (i > pageCount)
				break;
			linkString.append("&nbsp;");
			if (i == currentPage) {
				linkString.append(String.format("[%d]", i));
			} else {
				linkString.append(String.format("<a href='%s?pg=%d'>%d</a>", linkUrl, i, i));
			}
			linkString.append("&nbsp;");
		}

		// 3. 다음, 마지막 항목 만들기
		if (currentPage < pageCount) {
			linkString.append("&nbsp;");
			if (next <= pageCount) {
				linkString.append(String.format("[<a href='%s?pg=%d'>다음</a>]", linkUrl, next));
			}
			linkString.append("&nbsp;");
			linkString.append("&nbsp;");
			linkString.append(String.format("[<a href='%s?pg=%d'>마지막</a>]", linkUrl, pageCount));
		}
		return linkString.toString();
	}
}
