<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head><title>AccesDenied</title>
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

input[type=number]{
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

input[type=text]{
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

input[type=radio]{
	border-radius: 22px;
	width: 100px;
	height: 30px;
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
<%	if(request.getSession().getAttribute("user") == null)
{
	//out.print("You are not allowd to this page!");
	   response.sendRedirect("/cpl/AccesDenied");
	    return;
}
%>
<center>
<a href="http://localhost:8080/cpl/Login"  title="HOMEPAGE"> <img alt="home"
			src="pics/logo.png" height="180px" width="250px"></a>
<div class="container" style="background-color:#f1f1f1">
<font color=blue size=6px><br> Hello  ${user.first} ${user.last}</font>
<h2>Create Event</h2>
<form action="CreateEvent" method="POST">
<h4>
	
	
	 <label for="eventname"><b>Event Name</b></label>
	 <input type="text" placeholder="Enter your event's name" name="event name" required><br>
	 
	 <label for="participantsnum"><b>Participants Number</b></label>
	 <input type="number" placeholder="Enter number of participants" min="2" step="1" name="participants num" required><br>
	 
	 <label for="duration"><b>Duration</b></label>
	 <input type="number" min="3" step="1" placeholder="Enter duration of event" name="duration" required><br>
	 
	 	 <br><label for="type"><b>choose the type of the playlist algoritm</b></label><br>
	   <input type="radio" name="type" value="A" required> Popularity<br>
  		<input type="radio" name="type" value="B" required> Equality<br>  
	 <button type="submit">Create</button></h4>
	 
	 
	</div>
</form>
</body>
</html>