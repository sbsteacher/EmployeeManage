package com.salt.emma.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salt.emma.api.Api;
import com.salt.emma.vo.MemberInfo;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = 
				request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		rd.forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println("id :" + id);
		System.out.println("pw :" + pw);
		
		if(pw == null  || pw.equals("") || id == null || id.equals("")) {
			request.setAttribute("msg", "로그인을 할 수 없습니다");
			doGet(request, response);
			return;
		}
		MemberInfo param = new MemberInfo();
		param.setId(id);
		param.setPw(pw);
		
		System.out.println("b age : " + param.getAge());
		System.out.println("b name : " + param.getName());
		
		int result = Api.login(param);
		if(result == 1) { //로그인 성공!
			//세션에 로그인 정보 세팅하기
			
			System.out.println("a age : " + param.getAge());
			System.out.println("a name : " + param.getName());
			
			//아이디, 이름, 나이 정보만 담긴 MemberInfo 객체 주소값을 담는다.
			HttpSession hs = request.getSession();
			hs.setAttribute("loginUser", param);
			
			//페이지 이동!!
			response.sendRedirect("main");
		} else { //로그인 실패
			
		}				
	}

}








