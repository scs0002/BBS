<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewprot" content="width=device-width",initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>STH 게시판 웹 사이트</title>
</head>
<body>
	<%
		session.invalidate();	
	%>
	<script>
		location.href = 'main.jsp';
	</script>
</body>
</html>