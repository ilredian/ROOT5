﻿package DAO;

import java.util.List;
import DTO.MemberDTO;
<<<<<<< HEAD

import DTO.BoardFreeDTO;
import DTO.BoardLawDTO;
import DTO.CheatDTO;
import DTO.ReplyDTO;

public interface MemberInfoDAO {
	
		//자신이 작성한 자유게시판 게시물 
		public List<BoardFreeDTO> getFreeNotice(int memberno) throws Exception;
		
		//자신이 작성한 자유게시판 게시물 수 
		public int getFreeCount(int memberno) throws Exception;
		
		//자신이 작성한 변호사게시판 게시물 
		public  List<BoardLawDTO> getLawNotice(int memberno) throws Exception;

		//자신이 작성한 변호사게시판 게시물 수 
		public int getLawCount(int memberno) throws Exception;
		
	
		//회원이 쓴 모든 댓글
		public List<ReplyDTO> getAllReply(int memberno) throws Exception;

		//자신의 정보
		public MemberDTO getMember(String email) throws Exception;
		
		//자기정보 수정하기
		public int update(MemberDTO memberDTO) throws Exception;
		
		//회원탈퇴
		public MemberDTO delete(int memberno) throws Exception;
		
		//비번변경
		public int changepassword(MemberDTO memberDTO) throws Exception;
		
}
