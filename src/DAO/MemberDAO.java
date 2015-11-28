package DAO;

import java.sql.SQLException;

import DTO.MemberDTO;


public interface MemberDAO {
	
	public MemberDTO getMember(String userid) throws ClassNotFoundException, SQLException;

	public int insert(MemberDTO member) throws ClassNotFoundException, SQLException;
	
	
	
}

