<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<% String AName = request.getParameter("AName");%>
<% String SName = request.getParameter("SName");%>
<title>Insert title here</title>
</head>
<body>
<% String encodedAName = URLEncoder.encode(AName, "UTF-8"); %>
<% String encodedSName = URLEncoder.encode(SName, "UTF-8"); %>
<jsp:forward page="/ListenerRemoveFromFavServlet?AName=<%= encodedAName %>&SName=<%= encodedSName %>" />
</body>
</html>
