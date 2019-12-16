<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String msg = (String)request.getAttribute("msg");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>	
	<div>
		<form id="frm" action="login" method="post" onsubmit="return chk()">
			<div>
				아이디 : <input type="text" name="id">
			</div>
			<div>
				비밀번호 : <input type="password" name="pw">
			</div>
			<div>
				<input type="submit" value="로그인">
			</div>
		</form>
	</div>
	
	<script>
	<% if(msg != null) { %>
				alert('<%=msg%>');
	<% } %>
	
	/*
		function chk() {			
			if(chkEle(frm.id, '아이디')) {				
				return false
			} else if(chkEle(frm.pw, '비밀번호')) {
				return false
			}
		}
		
		function chkEle(ele, nm) {
			if(ele.value.length == 0) {
				alert(nm + '을(를) 입력해주세요.')
				ele.focus()
				return true
			}
			return false
		}
		*/
	</script>
</body>
</html>