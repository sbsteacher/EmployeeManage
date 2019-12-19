<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	#container {
		height: 100%;
		display: flex;		
	}
	
	#left {		
		flex-grow: 1;
		height: 100%;
	}
	
	#right {		
		flex-grow: 1;
	}
	
	table {
		width: 100%;
	}
</style> 
<div id="container">
	<div id="left">
		<table>
			<tr>
				<th>이름</th>
				<th>취미</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="item" items="${memberHobbyList}">
				<tr>
					<td>${item.name}</td>
					<td>${item.hobby}</td>
					<td><button onclick="del(${item.member_no}, ${item.hobby_no })">삭제</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="right">
		<c:if test="${msg != null }">
			<div>${msg }</div>
		</c:if>
		<form  id="frm" action="regmemberhobby" method="post" 
					onsubmit="return chk()">
			<input type="hidden" name="m_no">
			<input type="hidden" name="h_no">					
			멤버 : 
			<select name="member_no">
				<option value="0">--선택--</option>
				<c:forEach var="item" items="${memberList}">
					<option value="${item.no }">${item.name}</option>
				</c:forEach>
			</select> 
			취미 : 
			<select name="hobby_no">
				<option value="0">--선택--</option>
				<c:forEach var="item" items="${hobbyList}">
					<option value="${item.no }">${item.hobby}</option>
				</c:forEach>
			</select>
			<div>
				<input type="submit" value="등록">
			</div>
		</form>
	</div>
</div>
<script>
	function chk() {
		if(frm.member_no.value == '0') {
			alert('멤버를 선택해 주세요')			
			return false
		} else if(frm.hobby_no.value == '0') {
			alert('취미를 선택해 주세요')			
			return false
		}
	}
	
	function del(m_no, h_no) {
		frm.m_no.value = m_no;
		frm.h_no.value = h_no;
		frm.submit()
	}
</script>