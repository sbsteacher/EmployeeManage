package com.salt.emma.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.salt.emma.api.Api;
import com.salt.emma.vo.HobbyVO;

@WebServlet("/reghobby")
public class RegHobbyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//DB에서 리스트 가져오기	
		request.setAttribute("hobbyList",  Api.getHobbyList());
		
		request.setAttribute("title", "취미 등록");
		request.setAttribute("page", "reghobby");
		
		RequestDispatcher rd = 
				request.getRequestDispatcher("WEB-INF/jsp/template.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String hobby = request.getParameter("hobby");
		
		if(!hobby.equals("")) { //등록
			HobbyVO param = new HobbyVO();
			param.setHobby(hobby);
			
			int result = Api.regHobby(param);
			if(result != 1) {
				request.setAttribute("msg", "취미를 등록할 때 오류가 발생하였습니다.");
			}
			
		} else if(!no.equals("")) { //삭제
			
		}
		
		doGet(request, response);
	}

}
