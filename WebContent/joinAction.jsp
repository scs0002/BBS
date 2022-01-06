<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="user" class="user.User" scope="page"/>
<jsp:setProperty name="user" property="userID"/>
<jsp:setProperty name="user" property="userPassword"/>
<jsp:setProperty name="user" property="userName"/>
<jsp:setProperty name="user" property="userGender"/>
<jsp:setProperty name="user" property="userEmail"/>


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
		if (user.getUserID() == null || user.getUserPassword() == null || user.getUserName() == null || user.getUserGender() == null
		|| user.getUserEmail() == null){
		
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('필수항목을 입력해주세요')");
			script.println("history.back()");
			script.println("</script>");
		}
		else{
			UserDAO  userDAO = new UserDAO();
			int result = userDAO.join(user);
			if(result == -1){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('해당 아이디가 이미 존재 합니다.')");
				script.println("history.back()");
				script.println("</script>");
			}
			else{
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("location.href = 'main.jsp'");
				script.println("</script>");
				
			}
				
		}
	
		
		
	%>
</body>
</html>