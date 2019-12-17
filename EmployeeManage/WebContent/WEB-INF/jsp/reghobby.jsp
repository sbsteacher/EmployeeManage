<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	#container {
		height: 100%;
		display: flex;		
	}
	
	#left {
		background-color: red;
		width: 200px;
		height: 100%;
	}
	
	#right {
		background-color: blue;
		flex-grow: 1;
	}
</style>    
<div id="container">
	<div id="left">리스트</div>
	<div id="right">등록폼</div>
</div>
