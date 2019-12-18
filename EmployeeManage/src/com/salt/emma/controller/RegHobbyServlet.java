package com.salt.emma.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.salt.emma.MyUtil;
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
				
		HobbyVO param = new HobbyVO();		
		if(!"".equals(hobby)) { //등록
			
			param.setHobby(hobby);
			
			int result = Api.regHobby(param);
			if(result != 1) {
				request.setAttribute("msg", "취미를 등록할 때 오류가 발생하였습니다.");
			}
			
		} else if(!"".equals(no)) { //삭제
			int intNo = MyUtil.parseStringToInt(no, 0);
			if(intNo == 0) {
				request.setAttribute("msg", "잘 못 된 접근입니다.");
			} else {
				param.setNo(intNo);
				int result = Api.delHobby(param);
				if(result != 1) {
					request.setAttribute("msg", "삭제할 수 없습니다.");
				}
			}
		}
		
		doGet(request, response);
	}

}
