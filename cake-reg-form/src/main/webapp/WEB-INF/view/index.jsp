<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vk" uri="http://www.tomcat-demo.com/testing"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>home</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div>
		<ul id="navbar">
			<li><a href="home" class="entry active">Home</a></li>
            <li><a href="#" class="entry" id="prompt-modal">Sign up</a></li>
            <li><a href="#" class="entry">Sign in</a></li>
		</ul>
	</div>
	<div id="id01" class="modal">
		<span id="closeModal" class="close" title="Close Modal">&times;</span>
		<form action="register" method="post" class="modal-content">
			<div class="container">
				<h1>Sign Up</h1>
      			<p>Please fill in this form to create an account.</p>
      			<hr>
				<label for="firstName"><b>First name</b></label>
				<span id="name_error" class="error">${userRegErrors["firstName"]}</span>
				<input type="text" name="firstName" placeholder="First name" value="${userRegDto.firstName}">

				<label for="lastName"><b>Last name</b></label>
				<span id="lastname_error" class="error">${userRegErrors["lastName"]}</span>
				<input type="text" name="lastName" placeholder="Last name" value="${userRegDto.lastName}">

				<label for="email"><b>Email</b></label>
				<span id="email_error" class="error">${userRegErrors["email"]}</span>
				<input type="text" name="email" placeholder="Email" value="${userRegDto.email}">

				<label for="password"><b>Password</b></label>
				<span id="pass_error" class="error">${userRegErrors["password"]}</span>
				<input type="password" name="password" placeholder="Enter password">

				<label for="passwordRepeat"><b>Repeat password</b></label>
				<span id="passconfirm_error" class="error">${userRegErrors["passwordRepeat"]}</span>
				<input type="password" name="passwordRepeat" placeholder="Repeat password">

				<label>
					<input type="checkbox" name="remember" value="yes">Remember me
				</label>
				<div>
					<p>Verify you are a human</p>
					<input type="text" name="verificationInput" autocomplete="off" id="ver-input" placeholder="Enter verificaion code">
				</div>
				<div><vk:captcha/></div>
				<p>By creating an account you agree to our <a href="#">Terms & Privacy</a></p>
				<div class="clearfix">
					<button type="button" class="cancelbtn" id="cancelSignupBtn">Cancel</button>
					<button type="submit" class="signupbtn">Sign up</button>	
				</div>
			</div>	
		</form>
	</div>
	<script src="js/script.js"></script>
</body>
</html>
