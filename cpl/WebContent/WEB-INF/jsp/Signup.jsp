
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head><title>Signup</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body, html {
	height: 100%;
	font-family: arial;
	animation: fadeIn 1.0s ease forwards;
	/* The image used */
	background-image:
		url('http://www.pngimagesfree.com/Music/City-Music-Concert-Background.png');
	/* Full height */
	height: 65%;
	/* Create the parallax scrolling effect */
	font-family: 'Josefin Sans', sans-serif;
	font-size: 14px;
	font-weight: 400;
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
}

h1 {
	font-size: 48px;
}

h2 {
	font-size: 36px;
}

h3 {
	font-size: 24px;
}

h4 {
	font-size: 18px;
	text-align: center;
}

h5 {
	font-size: 14px;
}

h1, h2, h3, h4, h5, h6 {
	text-align: center;
	font-family: 'Josefin Sans', sans-serif;
	-webkit-font-smoothing: antialiased;
	-webkit-text-shadow: rgba(0, 0, 0, .01) 0 0 1px;
	text-shadow: rgba(0, 0, 0, .01) 0 0 1px;
	line-height: 1.2;
	color: #2a2a2a;
	font-weight: 700;
}

input {
border-radius: 22px;
	width: 300px;
	height: 55px;
	padding: 14px 20px;
	margin: 8px 0;
	display: inline-block;
	cursor: pointer;
	border: 1px solid #ccc;
	box-sizing: border-box;
	text-align: center;
	padding: 14px 20px;
}

button {
	background-color: #1e0592;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	position: relative;
	overflow: hidden;
	width: 300px;
	height: 55px;
	border-radius: 22px;
	outline: none;
	text-align: center;
}

.container {
 margin: auto;
  width: 50%;
	border-radius: 100px;
	border: 2px solid red;
	padding: 20px;
	width: 35%;
	height: 60%;
	padding: 16px;

}

button:hover {
	opacity: 0.8;
}
</style>
</head><p>
<body>

	<center>
		<a href="http://localhost:8080/cpl/Login" title="HOMEPAGE"> <img alt="home"
			src="pics/logo.png" height="180px" width="250px"></a>
    <div class="container" style="background-color:#f1f1f1">
  <%
					String msg = (String)request.getAttribute("error");
					if (msg != null)
						out.println("<br><font color=red size=5px>" + msg + "</font>");
				%>

<form action="Signup" method="POST">
<h4>

    <label for="firstname"><b>Firstname</b></label>
    <input type="text" placeholder="Enter your first name" name="first name" required><br>
    
     <label for="lastname"><b>Lastname</b></label>
    <input type="text" placeholder="Enter your last name" name="last name" required><br>
    
     <label for="email"><b>Email</b></label>
    <input type="email" placeholder="example@gmail.com" name="email" required><br>

  <label for="cellnum"><b>phoneNumber</b></label>
    <input type="text" placeholder="cellphone number" name="phone" required><br>
        
    <label for="password"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required> <br>
    
    <button type="submit">Signup</button>

  </div>
    </h4>
</form>  
</body>
</html>