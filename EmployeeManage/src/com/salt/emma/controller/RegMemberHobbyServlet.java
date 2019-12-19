package com.salt.emma.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.salt.emma.MyUtil;
import com.salt.emma.api.Api;
import com.salt.emma.vo.MemberHobbyVO;

@WebServlet("/regmemberhobby")
public class RegMemberHobbyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("memberHobbyList", Api.getMemberHobbyList());
		request.setAttribute("memberList", Api.getMemberList());
		request.setAttribute("hobbyList", Api.getHobbyList());
		
		request.setAttribute("title", "멤버 취미 등록");
		request.setAttribute("page", "regmemberhobby");
		
		RequestDispatcher rd = 
				request.getRequestDispatcher("WEB-INF/jsp/template.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m_no = request.getParameter("m_no");
		String h_no = request.getParameter("h_no");
		
		String member_no = request.getParameter("member_no");
		String hobby_no = request.getParameter("hobby_no");
		
		MemberHobbyVO param = new MemberHobbyVO();
		
		if(!"".equals(m_no) && !"".equals(h_no)) { //삭제
			
			String sql = " DELETE FROM member_hobby "
					+ " WHERE member_no = ? AND hobby_no = ? ";
			
			param.setMember_no(MyUtil.parseStringToInt(m_no, 0));
			param.setHobby_no(MyUtil.parseStringToInt(h_no, 0));
			
			int result = Api.regdelMemberHobby(sql, param);
			if(result == 1) {
				response.sendRedirect("regmemberhobby");
			} else {
				request.setAttribute("msg", "삭제할 수 없습니다");
				doGet(request, response);
			}
		} else if(!"".equals(member_no) && !"".equals(hobby_no)) { //등록
			
			String sql = " INSERT INTO member_hobby "
					+ " (member_no, hobby_no) "
					+ " VALUES "
					+ " (?, ?) ";
			
			param.setMember_no(MyUtil.parseStringToInt(member_no, 0));
			param.setHobby_no(MyUtil.parseStringToInt(hobby_no, 0));
			
			int result = Api.regdelMemberHobby(sql, param);
			if(result == 1) {
				response.sendRedirect("regmemberhobby");
			} else {
				request.setAttribute("msg", "등록할 수 없습니다");
				doGet(request, response);
			}
		}
	}

}
