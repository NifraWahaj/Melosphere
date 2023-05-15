<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<% 
String ABio = "";
if( request.getParameter("ABio") == "Dummy")
{
	ABio = "Dummy";
}
else
{
	ABio = request.getParameter("ABio");
}
%>

<% String AName = request.getParameter("AName");%>
<% String encodedAName = URLEncoder.encode(AName, "UTF-8"); %>
<% String encodedABio = URLEncoder.encode(ABio, "UTF-8"); %>

<title>Insert title here</title>
</head>
<body>
<jsp:forward page="/ListenerArtistProfileViewServlet?AName=<%= encodedAName %>&ABio=<%= ABio %>"/>
</body>
</html>

	