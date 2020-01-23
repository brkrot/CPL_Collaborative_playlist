
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@page import="Model.Event"%>
<!DOCTYPE html>
<html>
<head><title>Join event</title>
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
	height: 40px;
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
	width: 50%;
	height: 60%;
	padding: 16px;
}

button:hover {
	opacity: 0.8;
}
</style>
</head>
<p>
	<body>
		<%
			if (request.getSession().getAttribute("user") == null) {
				//out.print("You are not allowd to this page!");
				response.sendRedirect("/cpl/AccesDenied");
				return;
			}
		%>
		<center>
			<a href="http://localhost:8080/cpl/Login" title="HOMEPAGE"> <img alt="home"
				src="pics/logo.png" height="180px" width="250px"></a>
			<div class="container" style="background-color: #f1f1f1">
				<font color=green size=5px>Event ${event.name}<br></font> <font
					color=blue size=5px> Hello ${user.first} ${user.last}</font> <br>

			<font  size=4px>click on the Youtube logo in order to open Youtube and copy links to
				here <br> paste each one in each input box</font>

				<form action="JoinEvent" method="POST">
					<div class="imgcontainer">
						<a target="_blank" rel="noopener noreferrer"
							href="https://www.youtube.com"> <img
							src="pics/youtubelogo.png" height="100px" width="250px" alt="Avatar" class="avatar">
						</a>
					</div>

					<%
						Event e1 = (Event) request.getSession(true).getAttribute("event");
						int numberOfSongs;
						String invalid = (String) request.getSession().getAttribute("invalid");
						if (invalid != null) {
							numberOfSongs = Integer.parseInt(invalid);
							out.println("<br><font color=red size=4px>" + "some of your links are invalid please insert the rest<br>If you do not insert the songs now, you will lose your ability to insert them at all "
									+ "</font><br>");
						} else
							numberOfSongs = e1.getSongsNumberPerUser();

						for (int i = 1; i <= numberOfSongs; i++) {
					%>

					<b>song<%=i%></b> <input type="text"
						placeholder="Paste youtube link here" name="song<%=i%>" required>
					<b>name<%=i%></b> <input type="text" placeholder="Song name"
						name="name<%=i%>" required><br>

					<%
						}
					%>
					<button type="submit">send</button>
			</div>
			</form>
	</body>
</html>