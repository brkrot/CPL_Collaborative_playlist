<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@page import="Model.Event"%>
<!DOCTYPE html>
<html>
<head>
<title>launch Event</title>
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
<body>
	<center>
		<a href="http://localhost:8080/cpl/Login" title="HOMEPAGE"> <img alt="home"
			src="pics/logo.png" height="180px" width="250px"></a>
		<%
			if (request.getSession().getAttribute("admin") == null) {
				response.sendRedirect("/cpl/AccesDenied");
				return;
			}
		%>

		<h4>
			<div class="container" style="background-color: #f1f1f1">
				<font color=blue size=5px> Hello ${admin.first} ${admin.last}</font>


				<form action="launchEvent" method="POST">
					<%
						// opennig doPost in TheEvent page
					%>
					<br> <font color=blue size=8px> ${event.name}</font><br>

					<font size=5px> <b> PIN:${admin.eventPin}<br> <br>
							<br> Duration: ${event.eventDuration} mins<br> <br>
							<%
								Event e1 = (Event) request.getSession(true).getAttribute("event");
								String algo = e1.getaltype();
								if (algo.equals("A")) {
							%> Popularity Algorithm <%
								} else {
							%> Equality Algorithm <%
								}
							%><br> <br> Paticipators: ${event.usersConfirmed} /
							${event.max_Paticipating} <br> <br> songs Per User:
							${event.songsNumberPerUser}<br> <br>
					</b></label>
						<p></font>
					<%
						String linkYT = (String) request.getAttribute("linkyt");
						if (linkYT == null)

							out.println("<button type=\"submit\"\r\n"
									+ "							onclick=\"return confirm('Notice that if you create a playlist all the data from the DB will be deleted, are you sure?');\">Create\r\n"
									+ "							PlayList</button>");
					%>


					<%
						linkYT = (String) request.getAttribute("linkyt");
						if (linkYT != null) {
							out.println("<br><font color=blue size=4px><a title=\"Open Playlist in new window\" target=\"_blank\" href=" + linkYT
									+ "><img alt=\"home\"\r\n"
									+ "			src=\"pics/playlist.png\" height=\"100px\" width=\"150px\"> </a></font><br>");

							out.println(
									"<br><font color=red size=4px>Dont leave the page, the link to youtube is only your playlist</font>");
						}
					%>
					<%
						String error = (String) request.getAttribute("Error");
						if (error != null)
							out.println("<br><font color=red size=4px>" + error + "</font>");
					%>

				</form>
			</div>
			<br>
			</h1>
	</center>
</body>
</html>