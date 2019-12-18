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
import com.salt.emma.vo.MemberInfo;

@WebServlet("/regmember")
public class RegMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("memberList", Api.getMemberList());
		
		request.setAttribute("title", "멤버 등록");
		request.setAttribute("page", "regmember");
		
		RequestDispatcher rd = 
				request.getRequestDispatcher("WEB-INF/jsp/template.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sNo = request.getParameter("no");
		
		MemberInfo param = new MemberInfo();
		
		if("0".equals(sNo)) { //등록
			String name = request.getParameter("name");
			String sAge = request.getParameter("age");
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String repw = request.getParameter("repw");
			
			if(name.equals("") || sAge.equals("") || id.equals("") || pw.equals("") || !pw.equals(repw)) {
				request.setAttribute("msg", "회원 등록을 할 수 없습니다.");
			} else { //회원 등록 시도
				param.setName(name);				
				param.setAge(MyUtil.parseStringToInt(sAge, 0));
				param.setId(id);
				param.setPw(pw);
				
				int result = Api.regMember(param);
				
				if(result == 0) {
					request.setAttribute("msg", "회원 등록을 할 수 없습니다.");
				}
			}
			
		} else { //삭제
			int no = MyUtil.parseStringToInt(sNo, 0);
			if(no == 0) {
				request.setAttribute("msg",  "삭제할 수 없습니다.");
			} else { //삭제 시도
				param.setNo(no);
				
				int result = Api.delMember(param);
				if(result == 0) {
					request.setAttribute("msg", "회원의 취미가 등록되어 있어 삭제할 수 없습니다.");
				}
			}
		}
		
		doGet(request, response);
	}

}
