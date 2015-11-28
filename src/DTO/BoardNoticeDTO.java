package DTO;

import java.util.Date;

public class BoardNoticeDTO {

	private int boardno; //게시판번호
	private String title;/* 제목 */
	private String content; /* 내용 */
	private Date regdate; /* 작성일 */
	private int countno;/* 조회수 */
	private int active; /* 삭제여부 */
	private int critical; /* 강조여부 */
	private int categoryno; /* 카테고리번호 */
	
	


	/*
	 * 자유게시판 글번호 SEQ N/A NULL 제목 TITLE N/A NULL 글내용 CONTENT N/A NULL 작성일 REGDATE
	 * N/A NULL 조회수 HIT N/A NULL
	 * 
	 * 그룹번호 GROUPNO N/A NULL 답글 STEP N/A NULL 새 컬럼4 DEPTH N/A NULL
	 */

}
