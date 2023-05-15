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
<% String encodedAName = URLEncoder.encode(AName, "UTF-8"); %>
<% String encodedSName = URLEncoder.encode(SName, "UTF-8"); %>

<title>Insert title here</title>
</head>
<body>
	<jsp:forward page="/ListenerAddToFavSongServlet?aName=<%= encodedAName %>&sName=<%= encodedSName %>"/>
	
</body>
</html>