﻿package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.media.jfxmedia.logging.Logger;

import DAO.MemberDAO;
import DTO.MemberDTO;

@Controller
public class LoginController {

	PrintWriter out = null;

	@Autowired
	private SqlSession sqlSession;
	@RequestMapping(value="login.go" , method=RequestMethod.GET)
	public String Login(){
		System.out.println("로그인 페이지로 이동");
		return "join.login";
	}
	// 로그인 정보 DB 확인
	/*
	@PreAuthorize("hasRole('ROLE_USER'")
	*/
	@RequestMapping(value = "login.go", method=RequestMethod.POST)
	public String Login(
			MemberDTO memberDTO,
			HttpServletResponse response, 
			HttpServletRequest request, 
			HttpSession session,
			Model data ) throws Exception {
		System.out.println("로그인 실행");
		//스크립트 구문을 쓰기위한 준비
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//로그 남기기
		System.out.println("login.go POST");
		System.out.println(memberDTO.toString());

		//로그인 폼 정보를 마이바티스로 넘김
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		MemberDTO result = memberDAO.getMember(memberDTO.getEmail());
		
		System.out.println("result : " + result);
		if(result == null){//아이디가 없음
			System.out.println("해당 이메일은 가입되어 있지 않습니다");
			out.print("<script type='text/javascript'>alert('해당 이메일은 가입되어 있지 않습니다')</script>");
			out.close();
			return "main.index";
		
		}else {//아이디가 있음
			if(result.getPassword().equals(memberDTO.getPassword())){//비밀번호가 같음
				System.out.println("로그인 값 저장");
				System.out.println("로그인 세션 설정");
				
				//memberInfo 속성에 로그인 세션값을 넣기, 즉 로그인하기 
				session.setAttribute("memberInfo", result);
				session.setMaxInactiveInterval(60*60*24) ;

			}else{//비밀번호가 틀림  //내가 입력한 값과 DB에 값이 틀리면
 				//경고창 띄우기
				System.out.println("비밀번호 땡");
				out.print("<script type='text/javascript'>alert('비밀번호 땡')</script>");
				out.flush();
				return "main.index";
			}
		}		
		return "main.index";
	}
	
	@RequestMapping(value="/ERROR.go" , method=RequestMethod.GET)
	public void Denied(){
		
		System.out.println("접근 거부");
	}
	
	// 로그아웃
	@RequestMapping(value = "logout.go")
	public String Logout(HttpServletResponse response, HttpSession session) throws IOException {
		// 세션 삭제
		session.invalidate();
		
		//스크립트 구문을 쓰기위한 준비
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		//경고창 띄우기
		out.print("<script type='text/javascript'>alert('로그아웃 되었습니다.')</script>");
		return "main.index";
	}
}