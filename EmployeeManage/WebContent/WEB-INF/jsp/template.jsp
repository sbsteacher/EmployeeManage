<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%   
	if(session.getAttribute("loginUser") == null) {
		response.sendRedirect("login");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link type="text/css" rel="stylesheet" href="res/css/common.css">
</head>
<body>
	<div id="parentContainer">
		<header>
			<nav>
				<ul>
					<li><a href="main">Main</a></li>
					<li><a href="reghobby">취미등록</a></li>
					<li><a href="regmember">멤버등록</a></li>
					<li><a href="regmemberhobby">멤버취미등록</a></li>
					<li><a href="viewmemberhobby">멤버취미보기</a></li>
					<li><a href="logout">로그아웃</a></li>
				</ul>
			</nav>
		</header>
		<section>
			<jsp:include page="${page}.jsp"></jsp:include>
		</section>
	</div>
</body>
</html>