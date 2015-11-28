package controllers;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.MemberDAO;
import DTO.MemberDTO;

@Controller
public class JoinController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="signin.go" , method=RequestMethod.GET)
	public String Singin(){
		return "join.signin";
	}
	
	@RequestMapping(value="signin.go" , method=RequestMethod.POST)
	public String Singin(MemberDTO memberDTO) throws ClassNotFoundException, SQLException{
		//가입처리 (DAO 단)
		System.out.println(memberDTO.toString());
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int row = memberDAO.insert(memberDTO);
		System.out.println(row);

		return "redirect:index.go";
	}
	
	@RequestMapping("pwSearch.go")
	public String pwSearch(){
		return "join.pwSearch";
	}
	
	/*
	@RequestMapping("member.go")
	public String SingOK(MemberDTO member) throws ClassNotFoundException, SQLException{
		System.out.println("회원가입완료");
		memberDAO.insert(member);
		return "redirect:../index.htm";
	}
*/

}
