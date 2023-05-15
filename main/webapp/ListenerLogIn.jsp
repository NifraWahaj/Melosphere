<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<link
			rel="apple-touch-icon"
			sizes="180x180"
			href="../../resources/apple-touch-icon.png"
		/>
		<link
			rel="icon"
			type="image/png"
			sizes="32x32"
			href="../../resources/favicon-32x32.png"
		/>
		<link
			rel="icon"
			type="image/png"
			sizes="16x16"
			href="../../resources/favicon-16x16.png"
		/>
		<link rel="manifest" href="../../resources/site.webmanifest" />
		<link rel="mask-icon" href="../../resources/safari-pinned-tab.svg" color="#5bbad5" />
		<meta name="msapplication-TileColor" content="#da532c" />
		<meta name="theme-color" content="#ffffff" />
		<title>Login</title>
		<meta charset="utf-8" />
		<meta
			name="viewport"
			content="width=device-width, initial-scale=1, shrink-to-fit=no"
		/>

		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
			crossorigin="anonymous"
		/>
		<link rel="stylesheet" href="css/styles.css" />
		<script src="https://kit.fontawesome.com/36effec9ae.js" crossorigin="anonymous"></script>
		<script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
		<title>Login</title>
	</head>
	<body>
		<div class="customLinearGradient">
			<main class="loginMain d-flex justify-content-center align-items-lg-center">
			
					<form class="form-signin text-center" action = "ListenerLogInServlet">
					  <img class="mb-4" src="./resources/headphones.svg" alt="" width="72" height="72">
					  <h1 class="h3 mb-3 font-weight-normal myText-love">Please log in</h1>
					    <input type="email" id="inputEmail" class="form-control mb-3" placeholder="Email address" name="LEmail" required autofocus>
					   <label for="inputUsername" class="sr-only">Username</label>
					  <label for="inputEmail" class="sr-only">Email address</label>
					  <input type="text" id="inputUsername" class="form-control mb-3" placeholder="Username" name = "LName" required autofocus>
					  <label for="inputPassword" class="sr-only">Password</label>
					  <input type="password" id="inputPassword" class="form-control mb-3" placeholder="Password" name = "LPassword" required> 
					  <button class="myBtn-lg" type="submit">Log In</button>
					  <div class="checkColor mt-3">
						  <p>Not a member? <a href="ListenerSignUp.jsp" class="myText-love text-decoration-none customHover">Sign Up</a></p>
					  </div>
					</form>
			</main>
			<footer class="myText-love text-center">&copy; Melosphere, All Rights Reserved!</footer>	
		</div>
	</body>
</html>
