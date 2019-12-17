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
			<div>${item.no} - ${item.hobby}</div>
		</c:forEach>
	</div>
	<div id="right">
		
	</div>
</div>
