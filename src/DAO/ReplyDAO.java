package DAO;

import java.util.List;

import DTO.ReplyDTO;

public interface ReplyDAO {

	// 검색된 모든 댓글 갯수 및 active 값이 0
	public int getAllReplyCount(String searchTitle, String searchContent) throws Exception;
	
	// 검색된 카테고리별 댓글 갯수 및 active 값이 0
	public int getCategoryReplyCount(String searchTitle, String searchContent, int categoryno) throws Exception;
	
	// 검색된 회원별 댓글 갯수 및 active 값이 0
	public int getMemberReplyCount(String searchTitle, String searchContent, int memberno) throws Exception;
	
	// 검색된 게시판별 댓글 갯수 및 active 값이 0
	public int getBoardReplyCount(String searchTitle, String searchContent, int boardno) throws Exception;
	
	// 검색된 모든 댓글 정보
	public List<ReplyDTO> getAllReply(String searchTitle, String searchContent) throws Exception;
	
	// 검색된 카테고리별 댓글 정보
	public List<ReplyDTO> getCategoryReply(String searchTitle, String searchContent, int categoryno) throws Exception;
	
	// 검색된 회원별 댓글 정보
	public List<ReplyDTO> getMemberReply(String searchTitle, String searchContent, int memberno) throws Exception;
	
	// 검색된 게시판별 댓글 정보
	public List<ReplyDTO> getBoardReply(String searchTitle, String searchContent, int boardno, int rstart) throws Exception;
	
	// 댓글 추가
	public int insertReply(ReplyDTO replyDTO) throws Exception;
	
	// 댓글 내용 수정
	public int updateReply(ReplyDTO replyDTO) throws Exception;
	
	// 댓글 삭제(active값 업데이트)
	public int updateReplyActive(int replyno) throws Exception;
	
	// 댓글 완전 삭제(delete)
	public int deleteReply() throws Exception;
	
}
