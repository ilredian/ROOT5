package common;

//boardStatement 전용 페이징 처리
public class StatementPager {

	private int pageSize;// 한 페이지당 데이터 개수
	private int pagerSize;// 번호로 보여주는 페이지 Link 개수
	private int dataCount;// 총 데이터 수
	private int currentPage;// 현재 페이지 번호
	private int pageCount;// 총 페이지 수
	private int cheatno;// cheater 카테고리 번호
	private int next;// 다음 버튼
	private int previous;// 이전 버튼

	private String linkUrl;// 페이저가 포함되는 페이지의 주소

	private String field;// 검색 필드(ex:제목, 내용)
	private String query;// 검색 값

	public StatementPager(int dataCount, int currentPage, int pageSize, int pagerSize, String linkUrl, int cheatno) {

		this.linkUrl = linkUrl;

		this.dataCount = dataCount;
		this.pageSize = pageSize;
		this.pagerSize = pagerSize;
		this.currentPage = currentPage;
		this.cheatno = cheatno;
		this.pageCount = (dataCount / pageSize) + ((dataCount % pageSize) > 0 ? 1 : 0);
		this.next = (((this.currentPage - 1) / this.pagerSize) + 1) * this.pagerSize + 1;
		this.previous = ((this.currentPage - 1) / this.pagerSize) * this.pagerSize;
	}

	public StatementPager(int dataCount, int currentPage, int pageSize, int pagerSize, String linkUrl, int cheatno,
			String field, String query) {

		this.linkUrl = linkUrl;

		this.dataCount = dataCount;
		this.pageSize = pageSize;
		this.pagerSize = pagerSize;
		this.currentPage = currentPage;
		this.cheatno = cheatno;
		this.pageCount = (dataCount / pageSize) + ((dataCount % pageSize) > 0 ? 1 : 0);
		this.next = (((this.currentPage - 1) / this.pagerSize) + 1) * this.pagerSize + 1;
		this.previous = ((this.currentPage - 1) / this.pagerSize) * this.pagerSize;

		this.field = field;
		this.query = query;
	}

	public String toString() {
		StringBuffer linkString = new StringBuffer();

		if (query != null) {
			// 1. 처음, 이전 항목 만들기
			if (currentPage > 1) {
				linkString.append(String.format("<li><a href='%s?pg=1&cno=%d&f=%s&q=%s'>처음</a></li>", linkUrl, cheatno, field, query));
				linkString.append("&nbsp;");
				linkString.append("&nbsp;");
				if (currentPage > 10) {
					linkString.append(String.format("<li><a href='%s?pg=%d&cno=%d&f=%s&q=%s'>이전</a></li>", linkUrl, previous, cheatno, field, query));
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
					linkString.append(String.format("<li class='active'>%d</li>", i));
				} else {
					linkString.append(String.format("<li><a href='%s?pg=%d&cno=%d&f=%s&q=%s'>%d</a></li>", linkUrl, i, cheatno, field, query, i));
				}
				linkString.append("&nbsp;");
			}

			// 3. 다음, 마지막 항목 만들기
			if (currentPage < pageCount) {
				linkString.append("&nbsp;");
				if (next <= pageCount) {
					linkString.append(String.format("<li><a href='%s?pg=%d&cno=%d&f=%s&q=%s'>다음</a></li>", linkUrl, next, cheatno, field, query));
				}
				linkString.append("&nbsp;");
				linkString.append("&nbsp;");
				linkString.append(String.format("<li><a href='%s?pg=%d&cno=%d&f=%s&q=%s'>마지막</a></li>", linkUrl, pageCount, cheatno, field, query));
			}
		} else {
			// 1. 처음, 이전 항목 만들기
			if (currentPage > 1) {
				linkString.append(String.format("<li><a href='%s?pg=1&cno=%d'>처음</a></li>", linkUrl, cheatno));
				linkString.append("&nbsp;");
				linkString.append("&nbsp;");
				if (currentPage > 10) {
					linkString.append(String.format("<li><a href='%s?pg=%d&cno=%d'>이전</a></li>", linkUrl, previous, cheatno));
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
					linkString.append(String.format("<li class='active'>%d</li>", i));
				} else {
					linkString.append(String.format("<li><a href='%s?pg=%d&cno=%d'>%d</a></li>", linkUrl, i, cheatno, i));
				}
				linkString.append("&nbsp;");
			}

			// 3. 다음, 마지막 항목 만들기
			if (currentPage < pageCount) {
				linkString.append("&nbsp;");
				if (next <= pageCount) {
					linkString.append(String.format("<li><a href='%s?pg=%d&cno=%d'>다음</a></li>", linkUrl, next, cheatno));
				}
				linkString.append("&nbsp;");
				linkString.append("&nbsp;");
				linkString.append(String.format("<li><a href='%s?pg=%d&cno=%d'>마지막</a></li>", linkUrl, pageCount, cheatno));
			}
		}
		return linkString.toString();
	}
}