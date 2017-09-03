<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@page import="com.publication.impl.LoginIMPL"%>
<%@page import="com.publication.model.Login"%>
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
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<title>Edit Profile</title>
<style>
body {
	background-color: #fcfcfc;
}

.navbar-default .navbar-brand {
	color: #9e433d !important;
}

.navbar-default {
	background-color: #dddcdb !important;
}

.navbar-fixed-top {
	min-height: 80px !important;
}

.navbar-nav>li>a {
	padding-top: 0px !important;
	padding-bottom: 0px !important;
	line-height: 80px !important;
}

@media ( max-width : 767px) {
	.navbar-nav>li>a {
		line-height: 20px !important;
		padding-top: 10px !important;
		padding-bottom: 10px !important;
	}
}

.blank_row {
	height: 10px !important; /* overwrites any other rules */
	background-color: rgba(255, 255, 255, 0.6);
}

td {
	text-align: left;
	padding: 20px;
	padding-left: 15px;
	padding-right: 15px;
	padding-top: 15px;
	padding-bottom: 15px;
	vertical-align: middle;
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 15px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: 23px;
	vertical-align: middle;
	padding-top: 15px;
	padding-bottom: 15px;
	vertical-align: middle;
}

th {
	text-align: center;
	vertical-align: middle;
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 17px;
	font-style: italic;
	font-variant: normal;
	font-weight: bold;
	line-height: 23px;
}

h3 {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 20px;
	font-style: normal;
	font-variant: normal;
	font-weight: bolder;
	line-height: 23px;
}

table {
	overflow: hidden;
	text-overflow: ellipsis;
	word-wrap: break-word;
}

a {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 17px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: 23px;
}

.container {
	width: 100%;
}

li.borderless {
	border-bottom: 0 none;
	border-top: none;
}

ul {
	list-style: none;
}

.content2:before {
	content: "";
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	z-index: -1;
	display: block;
	background-image: url('../resources/images/DSCN7348.jpg');
	-webkit-filter: brightness(0.8);
	filter: brightness(0.8);
	background-size: cover;
	width: 100%;
	height: 100%;
	-webkit-filter: blur(10px);
	-moz-filter: blur(10px);
	-o-filter: blur(10px);
	-ms-filter: blur(10px);
	filter: blur(10px);
}

.content2 {
	overflow: visible;
	position: relative;
}

div.transbox {
	margin: 30px;
	background-color: rgba(255, 255, 255, 0.6);
	border: 0px solid;
	width: auto;
	/* For IE8 and earlier */
}

.content2 p {
	margin: 15px;
	background: rgba(255, 255, 255, 0.3);
	padding: 5px;
	box-shadow: 0 0 5px gray;
}

.ui-content {
	width: auto;
}

.notificationTable {
	padding: 10px;
}
</style>
</head>
<input type="hidden" id="refreshed" value="no">
<script type="text/javascript">
	onload = function() {
		var e = document.getElementById("refreshed");
		if (e.value == "no")
			e.value = "yes";
		else {
			e.value = "no";
			location.reload();
		}
	}
</script>
<body>

	<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"></jsp:useBean>
	<jsp:useBean id="d1ao" class="com.publication.impl.JournalIMPL" />
	<jsp:useBean id="d2ao" class="com.publication.impl.BooksIMPL" />
	<jsp:useBean id="d3ao" class="com.publication.impl.BookChapterIMPL" />
	<jsp:useBean id="d4ao"
		class="com.publication.impl.ConferencePresentationIMPL" />
	<jsp:useBean id="d5ao"
		class="com.publication.impl.ConferenceProceedingIMPL" />
	<jsp:useBean id="d6ao" class="com.publication.impl.TechnicalReportIMPL" />
	<jsp:useBean id="d7ao" class="com.publication.impl.PatentIMPL" />


	<%
		String sid = (String) request.getSession(false).getAttribute("sid");
		System.out.println("AT FACULTY" + sid);
		if (null == sid) {
			response.sendRedirect("../account/access_denied.jsp");
			return;
		}
		Login login = lao.getLogin(lao.getUsernameBySessionID(sid));
		if (null == login) {
			response.sendRedirect("../account/access_denied.jsp");
			return;
		}
		if (!lao.getRoleBySessionID(sid).equals("ROLE_FACULTY")) {
			response.sendRedirect("../account/access_denied.jsp");
			return;
		}
		
	%>


	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid clearfix">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a
				style="background-color: transparent !important; color: #9e433d !important;"
				class="navbar-brand"><strong> <img
					src="../resources/images/ncu logo.png" width="150px" height="50px"
					id="logo" /> The NorthCap University
			</strong></a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">


			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown pull-left"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false"><span
						class="glyphicon glyphicon-user"></span>&nbsp;Profile<span
						class="caret"></span></a>
					<ul class="dropdown-menu" id="profile-menu">
						<li><a href="../account/change_password.jsp">Change
								Password</a></li>
						<li><a href="../account/logout.jsp">Logout</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown pull-left"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown" role="button"
					aria-haspopup="true" aria-expanded="false"> <%
 	if (lao.checkRejectedSum(lao.getUsernameBySessionID(sid)) > 0) {
 		out.print("&nbsp;Notifications&nbsp;(" + lao.checkRejectedSum(lao.getUsernameBySessionID(sid)) + ")*");
 	} else {
 		out.print("&nbsp;Notifications&nbsp;(0)");
 	}
 %><span class="caret"></span>
				</a>
					<ul class="dropdown-menu" id="profile-menu" style="width: 270px;">
						<%
							if (d1ao.notificationRejectedJournal(lao.getUsernameBySessionID(sid)) > 0) {
						%>
						<li>
							<table>
								<tr>
									<th style="padding: 10px;">Journals Rejected</th>
									<th style="padding: 10px;"><%=d1ao.notificationRejectedJournal(lao.getUsernameBySessionID(sid))%></th>

								</tr>
							</table>
							<hr>
						</li>
						<%
							}
						%>
						<%
							if (d2ao.notificationRejectedBooks(lao.getUsernameBySessionID(sid)) > 0) {
						%>
						<li>
							<table>
								<tr>
									<th style="padding: 10px;">Books Rejected</th>
									<th style="padding: 10px;"><%=d2ao.notificationRejectedBooks(lao.getUsernameBySessionID(sid))%></th>
								</tr>
							</table>
							<hr>
						</li>
						<%
							}
						%>
						<%
							if (d3ao.notificationRejectedBookChapters(lao.getUsernameBySessionID(sid)) > 0) {
						%>
						<li>
							<table>
								<tr>
									<th style="padding: 10px;">Book Chapters Rejected</th>
									<th style="padding: 10px;"><%=d3ao.notificationRejectedBookChapters(lao.getUsernameBySessionID(sid))%></th>
								</tr>
							</table>
							<hr>
						</li>
						<%
							}
						%>
						<%
							if (d4ao.notificationRejectedConferencePresentations(lao.getUsernameBySessionID(sid)) > 0) {
						%>
						<li>
							<table>
								<tr>
									<th style="padding: 10px;">Presentations Rejected</th>
									<th style="padding: 10px;"><%=d4ao.notificationRejectedConferencePresentations(lao.getUsernameBySessionID(sid))%></th>
								</tr>
							</table>
							<hr>
						</li>
						<%
							}
						%>
						<%
							if (d5ao.notificationRejectedConferenceProceedingss(lao.getUsernameBySessionID(sid)) > 0) {
						%>
						<li>
							<table>
								<tr>
									<th style="padding: 10px;">Proceedings Rejected</th>
									<th style="padding: 10px;"><%=d5ao.notificationRejectedConferenceProceedingss(lao.getUsernameBySessionID(sid))%></th>
								</tr>
							</table>
							<hr>
						</li>
						<%
							}
						%>
						<%
							if (d6ao.notificationRejectedTechnicalReports(lao.getUsernameBySessionID(sid)) > 0) {
						%>
						<li>
							<table>
								<tr>
									<th style="padding: 10px;">Reports Rejected</th>
									<th style="padding: 10px;"><%=d6ao.notificationRejectedTechnicalReports(lao.getUsernameBySessionID(sid))%></th>
								</tr>
							</table>
							<hr>
						</li>
						<%
							}
						%>
						<%
							if (d7ao.notificationRejectedPatents(lao.getUsernameBySessionID(sid)) > 0) {
						%>
						<li>
							<table>
								<tr>
									<th style="padding: 10px;">Patent Rejected</th>
									<th style="padding: 10px;"><%=d7ao.notificationRejectedPatents(lao.getUsernameBySessionID(sid))%></th>
								</tr>
							</table>

						</li>
						<%
							}
						%>


					</ul></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid content2">
		<div class="row">
			<div style="width: 300px;" class="col-md-3 transbox">
				<jsp:include page="../sidebars/faculty_home_sidebar.jsp"></jsp:include>
			</div>
			<div style="width: 400px;" class="col-md-9 transbox">
				<h2>Edit Profile</h2>
				<c:if test="${not empty param.edit}">
					<p>
						<c:if test="${param.edit == 'success'}">
							<c:out value="Adding Record Successful"></c:out>
						</c:if>
						<c:if test="${param.edit == 'failed'}">
							<c:out value="Adding Record Unsuccessful"></c:out>
						</c:if>
						<c:if test="${param.edit == 'invalid'}">
							<c:out value="Invalid Data Found"></c:out>
						</c:if>
					</p>
				</c:if>
				<form action="save_edited.jsp" method="post">
				<input type="hidden" class="form-control"  name="id" id="id"
							value="<%=login.getUsername()%>" >
					<table>
						<tr>
							<td style="padding: 10px;"><label for="name">Faculty
									Name</label></td>
							<td style="padding: 10px;"><input type="text"
								class="form-control" required="on" autocomplete="off"
								name="name" id="name" value="<%=login.getName()%>"></td>
						</tr>
						<tr>
							<td style="padding: 10px;"><label for="email">Email</label></td>
							<td style="padding: 10px;"><input type="text"
								class="form-control" required="on" autocomplete="off"
								name="email" id="email" value="<%=login.getEmail()%>"></td>
						</tr>
						<tr>
							<td style="padding: 10px;"><a href="faculty_home.jsp"class="btn btn-success">Go Back</a></td>
							<td style="padding: 10px;"><button type="submit"
									class="btn btn-success">Submit</button></td>
						</tr>
					</table>
				</form>

			</div>
		</div>
	</div>
</body>
</html>