<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.Music.Song" %>
<%@ page import="com.melosphere.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>

<meta charset="UTF-8">
	<link rel="apple-touch-icon" sizes="180x180" href="./resources/apple-touch-icon.png" />
	<link rel="icon" type="image/png" sizes="32x32" href="./resources/favicon-32x32.png" />
	<link rel="icon" type="image/png" sizes="16x16" href="./resources/favicon-16x16.png" />
	<link rel="manifest" href="./resources/site.webmanifest" />
	<link rel="mask-icon" href="./resources/safari-pinned-tab.svg" color="#5bbad5" />
	<meta name="msapplication-TileColor" content="#da532c" />
	<meta name="theme-color" content="#ffffff" />
<title>Following Artist</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous" />
	<link rel="stylesheet" href="css/styles.css" />
	<script src="https://kit.fontawesome.com/36effec9ae.js" crossorigin="anonymous"></script>
	<script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
</head>
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
		
		
		<main class="container-md py-4" style="padding-left: 15em;">
									<h2 class="fs-1 fw-bold">Following</h2>
						
		
		<section class="artists">
				<hr>
			
 <%
    List<Artist> artistList = (ArrayList<Artist>)request.getAttribute("FollowingArtist");
    if(artistList != null && artistList.size() > 0) 
    {
        for(Artist artist : artistList) 
        {
 	 	%>
 	 	
<div class="col-md-9">
	<div class="card myBg-Light" style="width: 66rem;">
		<div class="card-body">
			<h5 class="card-title fw-bold"><%= artist.getName() %></h5>
			<p><%= artist.getBio() %></p>
			<form action="ListenerArtistViewDummy.jsp" method="post">
				<input type="hidden" name="AName" id="AName" value="<%= artist.getName() %>" style: align:c>
				<input type="hidden" name="ABio" id="ABio" value="<%= artist.getBio() %>">
				<button type="submit" class="myBtn px-5" style="border: none; width: 100%;">More</button>
			</form>
		</div>
	</div>
</div>
 	 	
<br>
 	 	
			
  		<%
  		
        }
    } 
    else 
    {
  		%>
    		You are not following anyone 
		  <%
    }
    %>
    </section>
    </main>
    </div>
   

<script>
function playSong(songName) {
	  var xmlhttp = new XMLHttpRequest();
	  xmlhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      var songBlob = this.response;
	      var audioUrl = URL.createObjectURL(songBlob);
	      var audioPlayer = document.getElementById("audioPlayer");
	      var audioSource = document.getElementById("audioSource");
	      audioSource.src = audioUrl;
	      audioPlayer.style.display = "block";
	      audioPlayer.load();
	      audioPlayer.play();
	    }
	  };

	  if (songName) { // called from row click
	    xmlhttp.open("GET", "ListenerPlaySongFileServlet?SongName=" + songName, true);
	  } else { // called from search box
	    var songName = document.getElementById("songName").value;
	    xmlhttp.open("GET", "ListenerPlaySongFileServlet?SongName=" + songName, true);
	  }

	  xmlhttp.responseType = "blob";
	  xmlhttp.send();
	}

</script>
<script>

  function showSearch() {

    var searchBar = document.getElementById("searchBar");

    if (searchBar.style.display === "none") {

      searchBar.style.display = "block";

    } else {

      searchBar.style.display = "none";

    }

  }

</script>



<form action="ListenerLogOut.jsp">
  <input type="submit" value="Log Out">
</form>

<audio id="audioPlayer" controls style="display:none">
  <source id="audioSource" type="audio/mpeg">
</audio>

<form action="ListenerDelete.jsp">
  <input type="submit" value="Delete Listener Account">
</form>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
		integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js"
		integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy"
		crossorigin="anonymous	"></script>
		</div>
</body>
</html>