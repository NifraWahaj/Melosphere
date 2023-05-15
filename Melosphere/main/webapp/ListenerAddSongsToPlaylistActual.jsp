<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<% String PName = request.getParameter("PName");%>
<% String songName = request.getParameter("songName");%>
<% String AName = request.getParameter("AName");%>
<% String encodedPName = URLEncoder.encode(PName, "UTF-8"); %>
<% String encodedsongName = URLEncoder.encode(songName, "UTF-8"); %>
<% String encodedAName = URLEncoder.encode(AName, "UTF-8"); %>
<title>Insert title here</title>
</head>
<body>
<jsp:forward page="/ListenerAddSongToPlaylistServlet?PName=<%= encodedPName %>&songName=<%= encodedsongName %>&AName=<%= encodedAName%>"/>
</body>
</html>


