package DAO;

import java.util.List;
import DTO.MemberDTO;

import DTO.BoardFreeDTO;
import DTO.CheatDTO;
import DTO.ReplyDTO;

public interface MemberInfoDAO {
	
		//모든 게시글 정보
//		public String insert(MemberDTO member) throws Exception;
	
		//회원이 쓴 모든 댓글
		public List<ReplyDTO> getAllReply(MemberDTO memberDTO) throws Exception;

		//자신의 정보
		public MemberDTO getMember(String email) throws Exception;
		
		//자기정보 수정하기
		public int update(MemberDTO memberDTO) throws Exception;
		
		//회원탈퇴
		public MemberDTO delete(int memberno) throws Exception;
		
		//비번변경
		public MemberDTO changepassword(String email) throws Exception;
}
