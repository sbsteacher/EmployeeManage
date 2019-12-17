package com.salt.emma.api;

import static com.salt.emma.api.DBCon.close;
import static com.salt.emma.api.DBCon.getCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.salt.emma.vo.HobbyVO;
import com.salt.emma.vo.MemberInfo;

public class Api {
	
	//1: 로그인 성공, 2:아이디 없음, 3:비번 틀림, 0:에러발생
	public static int login(MemberInfo param) {
		int result = 0;
		
		if(param == null) {
			return result;
		}
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//비번, 아이디 맞는 확인하는 거!
		String sql = " SELECT * FROM member_info WHERE id = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1,param.getId());
			rs=ps.executeQuery();
			
			if(rs.next()) {
				String dbPw = rs.getString("pw");
				
				
				if(param.getPw().equals(dbPw)) {
					result = 1;		
					
					int age = rs.getInt("age");
					String name = rs.getString("name");
					
					param.setAge(age);
					param.setName(name);					
					param.setPw(null);
					
				}else {
					result = 3;
				}
			}else {
				result = 2;
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return result;
	}
	
	public static List<HobbyVO> getHobbyList() {
		List<HobbyVO> list = new ArrayList();
			
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM hobby ORDER BY no ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return list;
	}
}
