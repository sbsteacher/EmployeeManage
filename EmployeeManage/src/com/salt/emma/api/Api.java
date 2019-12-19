package com.salt.emma.api;

import static com.salt.emma.api.DBCon.close;
import static com.salt.emma.api.DBCon.getCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.salt.emma.vo.HobbyVO;
import com.salt.emma.vo.MemberHobbyVO;
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
	
	public static List<HobbyVO> getHobbyList(int member_no) {
		List<HobbyVO> list = new ArrayList();
			
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT no, hobby FROM hobby"
				+ " WHERE no not in ( "
				+ " SELECT hobby_no FROM hobby A "
				+ " INNER JOIN member_hobby B " + 
				"    ON A.no = B.hobby_no " + 
				"    AND B.member_no = ? " + 
				"   ) ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, member_no);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String hobby = rs.getString("hobby");
				HobbyVO vo = new HobbyVO();
				vo.setNo(no);
				vo.setHobby(hobby);
				
				list.add(vo);
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return list;
	}
	
	public static int regHobby(HobbyVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO hobby "
				+ " (no, hobby) "
				+ " VALUES "
				+ " (seq_hobby.nextval, ?) ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getHobby());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		
		return result;
	}
	
	public static int delHobby(HobbyVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " DELETE FROM hobby WHERE no = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getNo());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		
		return result;
	}
	
	public static List<MemberInfo> getMemberList() {		
		List<MemberInfo> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM member_info ORDER BY no ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String id = rs.getString("id");
				
				MemberInfo vo = new MemberInfo();
				vo.setNo(no);
				vo.setName(name);
				vo.setAge(age);
				vo.setId(id);
				
				list.add(vo);
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return list;
	}
	
	
	//0: 에러, 1:등록완료
	public static int regMember(MemberInfo param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO member_info"
				+ " (no, name, age, id, pw)"
				+ " VALUES "
				+ " (seq_member.nextval, ?, ?, ?, ?) ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getName());
			ps.setInt(2, param.getAge());
			ps.setString(3, param.getId());
			ps.setString(4,  param.getPw());
			result = ps.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		
		return result;
	}
	
	public static int delMember(MemberInfo param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " DELETE FROM member_info WHERE no = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getNo());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		
		return result;
	}
	
	public static List<MemberHobbyVO> getMemberHobbyList() {
		List<MemberHobbyVO> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT A.member_no, A.hobby_no " + 
				" , B.name, C.hobby " + 
				" FROM member_hobby A " + 
				" INNER JOIN member_info B " + 
				" ON A.member_no = B.no " + 
				" INNER JOIN hobby C " + 
				" ON A.hobby_no = C.no" +
				" ORDER BY A.member_no, A.hobby_no ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int member_no = rs.getInt("member_no");
				int hobby_no = rs.getInt("hobby_no");
				String name = rs.getString("name");
				String hobby = rs.getString("hobby");
				
				MemberHobbyVO vo = new MemberHobbyVO();
				vo.setMember_no(member_no);
				vo.setHobby_no(hobby_no);
				vo.setName(name);
				vo.setHobby(hobby);
				
				list.add(vo);
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return list;
	}
	
	public static int regdelMemberHobby(String sql, MemberHobbyVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getMember_no());
			ps.setInt(2, param.getHobby_no());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		
		return result;
	}
	

}












