package DTO;

import DAO.MemberDAO;

public class AuthService {
	
	private MemberDAO memberDAO;

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	/*
	public AuthInfo authenticate(String email, String pwd){
		MemberDTO member = memberDAO.
	}
*/
}
