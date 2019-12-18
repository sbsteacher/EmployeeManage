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
				<th>no</th>
				<th>이름</th>
				<th>나이</th>
				<th>ID</th>				
				<th>삭제</th>
			</tr>
		<c:forEach var="item" items="${memberList}">
			<tr>
				<td>${item.no}</td>
				<td>${item.name}</td>
				<td>${item.age }</td>
				<td>${item.id }</td>
				<td>
					<a href="#" onclick="clkDel(${item.no})">X</a>
				</td>
			</tr>
		</c:forEach>
		</table>		
	</div>
	<div id="right">
		<c:if test="${msg != null}">
			<div class="warning">${msg}</div>
		</c:if>
		<form id="frm" action="regmember" method="post">
			<input type="hidden" name="no" value="0">
			<div>이름: <input type="text" name="name"></div>
			<div>나이: <input type="number" name="age"></div>
			<div>아이디: <input type="text" name="id"></div>
			<div>비밀번호: <input type="password" name="pw"></div>
			<div>비밀번호확인: <input type="password" name="repw"></div>
			<div><input type="submit" value="등록"></div> 
		</form>
	</div>
</div>
<script>
	function clkDel(no) {
		console.log('no : ' + no)
		frm.no.value = no		
		frm.submit()
	}
</script>
    