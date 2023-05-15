<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.melosphere.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="apple-touch-icon" sizes="180x180" href="../../resources/apple-touch-icon.png" />
    <link rel="icon" type="image/png" sizes="32x32" href="../../resources/favicon-32x32.png" />
    <link rel="icon" type="image/png" sizes="16x16" href="../../resources/favicon-16x16.png" />
    <link rel="manifest" href="../../resources/site.webmanifest" />
    <link rel="mask-icon" href="../../resources/safari-pinned-tab.svg" color="#5bbad5" />
    <meta name="msapplication-TileColor" content="#da532c" />
    <meta name="theme-color" content="#ffffff" />
    <title>Profile</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous" />
    <link rel="stylesheet" href="css/styles.css" />
    <script src="https://kit.fontawesome.com/36effec9ae.js" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script></head>
<body>
  <div class="d-flex customLinearGradient">
  
  <nav class="d-flex flex-column justify-content-between flex-shrink-0 p-3 vh-100 myBg-Light position-fixed"
                
                
                    style="width: 280px;">
                    <div>
                        <a class="navbar-brand" href="#">
            <img src="./resources/headphones.svg" class="mb-1" alt="headphones" width="30" height="24" />
            <span class="fs-4 mt-4 myText-dark">Melosphere</span>
        </a>
        
                        <hr>
                        <ul class="nav nav-pills flex-column mb-auto">
                              <li class="nav-item mb-3 mt-3">
        
             <form class="nav-item mb-3" action="./search" method="get">
                            <div class="input-group">
                            <input type = "search" id="songName"  class="form-control" placeholder="Search Song">
                            <br>
                            <input type = "button" value = "Play" onclick="playSong()" class="btn btn-outline-light">
                            </form>
                            <li class="nav-item mb-3">
                                <a href="./ListenerFavDummy.jsp" class="d-flex align-items-center text-white text-decoration-none L-Affiliate-Tagged L-Affiliate-Tagged">
                                    <box-icon name='heart' type='solid' color='#ffffff'></box-icon>
                                    Favorites
                                </a>
                            </li>
                            
                            <li class="nav-item mb-3">
                                <a href="./ListenerGetPlaylistDummy.jsp" class="d-flex align-items-center text-white text-decoration-none L-Affiliate-Tagged L-Affiliate-Tagged" aria-current="page">
                                    <box-icon name='playlist' type='solid' color='#ffffff'></box-icon>
                                    Playlists
                                </a>
                            </li>
                            
                            <li class="nav-item mb-3">
                                <a href="./ListenerGetFollowingArtistDummy.jsp" class="d-flex align-items-center text-white text-decoration-none L-Affiliate-Tagged L-Affiliate-Tagged" aria-current="page">
                                    <box-icon name='circle' type='solid' color='#ffffff'></box-icon>
                                    Following Artist
                                </a>
                            </li>
                            
                        </ul>
                    </div>
                    
                    <div class="dropdown">
                        <hr>
                        <a href="#"
                            class="d-flex align-items-center text-white text-decoration-none dropdown-toggle L-Affiliate-Tagged"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            <strong>${LName}</strong>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
                            <li><a class="dropdown-item L-Affiliate-Tagged" href="ArtistLogIn.jsp">Switch to Artist</a></li>
                            <li><a class="dropdown-item L-Affiliate-Tagged" href="./ListenerProfileDummay.jsp">Profile</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item L-Affiliate-Tagged" href="ListenerLogOut.jsp">Log Out</a></li>					
                        </ul>
                    </div>
                </nav>
		
		  <%
  String Name = (String) request.getAttribute("Name");
  String Email = (String)request.getAttribute("Email");
  String Password = (String)request.getAttribute("Password");
  %>
		      <main class="container-md py-4" style="padding-left: 15em;">
            <h2 class="fs-1 fw-bold">Profile</h2>
            <section class="artists">
                <hr>
                <p>Display Name:    <%=  Name %></p>
                <p>Email:            <%=  Email %></p>
                <p>Password:         *****</p>
                <a href="./ListenerEditProfile.jsp"><button class="myBtn px-5">Edit</button></a>
                <a href="./ListenerDeleteProfileDummy.jsp"><button class="myBtn-red px-5">Delete Profile</button></a>
            </section>
        </main>
		
    
  </div>
</body>
</html>