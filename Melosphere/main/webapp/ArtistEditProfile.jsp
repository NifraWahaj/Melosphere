<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profile</title>
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
</head>
<body>


	<div class="d-flex customLinearGradient">
		<% if(session.getAttribute("AName") == null) {
			response.sendRedirect("ArtistSignUp.jsp");
		} else {
			String Aname = (String) session.getAttribute("AName");
		%>
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
		
		<main class="container-md py-4" style="padding-left: 15em;">
			<h2 class="fs-1 fw-bold">Edit Profile</h2>
            <hr>

			<section class="detialsAboutSong mt-5">
				<h3 class="fs-3 mb-3">Fill the Details</h3>
				
				<form action="ArtistEditProfileServlet" method="post">
                    <div class="mb-3">
   					   <label for="exampleInputEmail1" class="form-label">Password </label>
                         <input type="text" name="newAPassword" id="newAPassword"/>
                        </div>
                    
                      <div class="mb-3">
 				<input class="myBtn" type="submit" value="Save" />
                
</form>
				<hr>
			
                  
                  
                  
			</section>
		</main>
	
  

<audio id="audioPlayer" controls style="display:none; position: fixed; left: 5%; width: 30%; height:5% ;">
    <source id="audioSource" type="audio/mpeg">
  </audio>



<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
		integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js"
		integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy"
		crossorigin="anonymous	"></script>
		

</body>
</html>