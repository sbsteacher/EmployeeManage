package com.salt.emma.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		//false, false
		
		if(pw == null  || pw.equals("") || id == null || id.equals("")) {
			request.setAttribute("msg", "로그인을 할 수 없습니다");
			doGet(request, response);
			return;
		}
		
		System.out.println("id :" + id);
		System.out.println("pw :" + pw);
				
	}

}








