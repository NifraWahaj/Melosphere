<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.Music.Song" %>
<%@ page import="com.melosphere.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="apple-touch-icon" sizes="180x180" href="./resources/apple-touch-icon.png" />
	<link rel="icon" type="image/png" sizes="32x32" href="./resources/favicon-32x32.png" />
	<link rel="icon" type="image/png" sizes="16x16" href="./resources/favicon-16x16.png" />
	<link rel="manifest" href="./resources/site.webmanifest" />
	<link rel="mask-icon" href="./resources/safari-pinned-tab.svg" color="#5bbad5" />
	<meta name="msapplication-TileColor" content="#da532c" />
	<meta name="theme-color" content="#ffffff" />
    <meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous" />
	<link rel="stylesheet" href="css/styles.css" />
	<script src="https://kit.fontawesome.com/36effec9ae.js" crossorigin="anonymous"></script>
	<script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
	<title>Home</title>
	<style>
	audio {
  background-color: var(--clr-medium);
  
}
audio::-webkit-media-controls-panel {
  background-color: var(--clr-medium);
}
audio::-webkit-media-controls-current-time-display {
  color: var(--clr-dark);
}
audio::-webkit-media-controls-time-remaining-display {
  color: var(--clr-dark);
}
audio::-webkit-media-controls-timeline {
	color: #fff;
}</style>

</head>
<body>


<div class="d-flex customLinearGradient">
<% if(session.getAttribute("AName") == null) {
    response.sendRedirect("ArtistSignUp.jsp");
} else {
    String AName = (String) session.getAttribute("AName");
%>
    <p>WELCOME <%= AName %></p>
<% } %>
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
					<li class="nav-item mb-3">
						<a href="ArtistUploadSongFile.jsp" class="d-flex align-items-center text-white text-decoration-none L-Affiliate-Tagged L-Affiliate-Tagged">
							<box-icon name='upload' type='solid' color='#ffffff'></box-icon>
							Upload Music
						</a>
					</li>
					
					<li class="nav-item mb-3">
						<a href="./ArtistDisplayAllSongsDummy.jsp" class="d-flex align-items-center text-white text-decoration-none L-Affiliate-Tagged L-Affiliate-Tagged" aria-current="page">
							<box-icon name='music' type='solid' color='#ffffff'></box-icon>
							All Songs
						</a>
					</li>
				</ul>
			</div>
			
			<div class="dropdown">
				<hr>
				<a href="#"
					class="d-flex align-items-center text-white text-decoration-none dropdown-toggle L-Affiliate-Tagged"
					data-bs-toggle="dropdown" aria-expanded="false">
					<strong>${AName}</strong>
				</a>
				<ul class="dropdown-menu dropdown-menu-dark text-small shadow">
					<li><a class="dropdown-item L-Affiliate-Tagged" href="ListenerLogIn.jsp">Switch to Listener</a></li>
					<li><a class="dropdown-item L-Affiliate-Tagged" href="./ArtistProfileDummy.jsp">Profile</a></li>
					<li>
						<hr class="dropdown-divider">
					</li>
					<li><a class="dropdown-item L-Affiliate-Tagged" href="./ArtistLogOut.jsp">Log Out</a></li>					
				</ul>
			</div>
		</nav>
		
		
		   <main class="container-md py-4" style="padding-left: 7em;">
				 <h2 class="fs-1 fw-bold">WELCOME ${AName}</h2>
			<hr>

				<section class="mostPlayed mt-5">
				<h3 class="fs-3 mb-3">Your Most Popular Tracks!</h3>
				<hr>
				
			
  <%
  List<Song> songList = (ArrayList<Song>)request.getAttribute("songs");
  if(songList != null && songList.size() > 0) {%>
    	<div class="row">
   				 	<div class="col-3"> Song    </div>	
    				<div class="col-4"> Genre   </div>
   					<div class="col-2"> Date    </div>
   					<div class="col-2"> Streams </div>
      		</div>
    
	 <%
  
	   for(Song song : songList) {
  %>
  	

    
      	
		<div class="p-3 row text-whi te-50 shadow myBg-gray rounded my-3" >
				
				   <div class="col-3" onclick="playSong('<%= song.getSName() %>')" style="cursor:pointer;"> <%= song.getSName() %>  </div>	 	
				  <div class="col-4"><%= song.getGenre() %></div>
					<div class="col-2"><%= song.getRDate() %></div>
					<div class="col-2"><%= song.getStreams() %></div>
						

					<div class="dropdown col-1">
  					<a href="#" class="d-flex align-items-center text-white text-decoration-none L-Affiliate-Tagged" data-bs-toggle="dropdown" aria-expanded="false">
   					<box-icon name='dots-horizontal-rounded' color='#ffffff'></box-icon>
 					</a>
  					<ul class="dropdown-menu dropdown-menu-dark text-small shadow">
    				<li><a class="dropdown-item L-Affiliate-Tagged" href="#" onclick="copyToClipboard()">  Share  </a></li>
  					</ul>
  					<audio id="audioPlayer_<%= song.getSName() %>" controls style=" display:none; position: fixed; left: 5%; width: 30%; height:5% ;">
                    <source id="audioSource_<%= song.getSName() %>" type="audio/mpeg">
  </audio>	

			</div>
	
	</div>
  <%
  
        }
    } 
    else {
  %>
 <div class="col">Please Upload Songs you don't have any yet...</div> 
  <%
    }
  %>
		
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
	      audioPlayer.style.width = "73.5%";
	      audioPlayer.style.borderRadius = "10px";
	      audioPlayer.style.backgroundColor = "darak-blue";
	      audioPlayer.load();
	      audioPlayer.play();

	    }
	  };

	  if (songName) { // called from row click
	    xmlhttp.open("GET", "ArtistPlaySongFileServlet?SongName=" + songName, true);
	  } else { // called from search box
	    var songName = document.getElementById("songName").value;
	    xmlhttp.open("GET", "ArtistPlaySongFileServlet?SongName=" + songName, true);
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

<script>
  function copyToClipboard() {
    // Create a temporary element for copying the text
    const tempElement = document.createElement("textarea");
    tempElement.value = "Text to copy to clipboard";
    document.body.appendChild(tempElement);

    // Select the text and copy to clipsboard
    tempElement.select();
    document.execCommand("copy");
    document.body.removeChild(tempElement);

    // Show the "copied to clipboard" message
    const messageElement = document.createElement("div");
    messageElement.innerHTML = "Link copied to clipboard!";
    messageElement.style.position = "fixed";
    messageElement.style.top = "20%";
    messageElement.style.left = "85%";
    messageElement.style.transform = "translate(-50%, -50%)";
    messageElement.style.padding = "10px";
    messageElement.style.background = "transparent";
    messageElement.style.color = "#FADA5E";
    messageElement.style.borderRadius = "0px";
    messageElement.style.zIndex = "9999";
    document.body.appendChild(messageElement);

    // Remove the message after a few seconds
    setTimeout(() => {
      document.body.removeChild(messageElement);
    }, 3000);
  }
</script>

<form action="ArtistLogOut.jsp">
  <input type="submit" value="Log Out">
</form>

<div class="audio-container" >
			<audio   id="audioPlayer" controls style="display:none; margin-left: 21%;" >
  <source id="audioSource" type="audio/mpeg">
</audio>
		</div>

<form action="ArtistDeleteProfileDummy.jsp">
  <input type="submit" value="Delete Artist Account">
</form>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
		integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js"
		integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy"
		crossorigin="anonymous	"></script>
</body>
</html>