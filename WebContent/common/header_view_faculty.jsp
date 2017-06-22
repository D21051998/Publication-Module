<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

	
<title>Insert title here</title>
<style>
.container {
	width: 100%;
}
</style>
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">The NorthCap University</a>

		</div>
		<div id="navbar" class="navbar-collapse collapse">
			
			<ul class="nav navbar-nav navbar-right">
				<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"></jsp:useBean>
				<%
					try {
						String sid = request.getSession(false).getAttribute("sid").toString();

						if (null == sid) {
							out.println("<li><a href=\"#\">-</a></li>");

						} else {
							out.println("<li><a href=\"#\">" + lao.getUsernameBySessionID(sid) + "</a></li>");
						}

					} catch (NullPointerException e) {
						out.println("<li><a href=\"#\">-</a></li>");
					}
				%>

			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav>
</body>
</html>