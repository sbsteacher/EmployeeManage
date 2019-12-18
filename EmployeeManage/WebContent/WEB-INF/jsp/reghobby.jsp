<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<style>
	#container {
		height: 100%;
		display: flex;		
	}
	
	#left {		
		width: 200px;
		height: 100%;
	}
	
	#right {		
		flex-grow: 1;
	}
</style>    
<div id="container">
	<div id="left">
		<c:forEach var="item" items="${hobbyList}">
			<div>
				${item.no} - ${item.hobby} - 
				<a href="#" onclick="clkDel(${item.no})">X</a>
			</div>
		</c:forEach>		
	</div>
	<div id="right">
		<c:if test="${msg != null}">
			<div class="warning">${msg}</div>
		</c:if>
		<form id="frm" action="reghobby" method="post">
			<input type="text" name="no">
			<div>취미: <input type="text" name="hobby"></div>
			<div><input type="submit" value="등록"></div> 
		</form>
	</div>
</div>
<script>
	function clkDel(no) {
		console.log('no : ' + no)
		frm.no.value = no
		frm.hobby.value = ''
		frm.submit()
	}
</script>







