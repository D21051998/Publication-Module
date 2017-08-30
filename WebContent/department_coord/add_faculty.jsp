<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
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



<style>
body { 1
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
	vertical-align: middle;
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 15px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: 23px;
	padding: 10px;
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
	<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"
		scope="page"></jsp:useBean>
	<%
		String sid = (String) request.getSession(false).getAttribute("sid");
		if (null == sid) {
			response.sendRedirect("../account/access_denied.jsp");
			return;
		}
		if (!lao.getRoleBySessionID(sid).contains("ROLE_DC")) {
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
			</li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="container-fluid content2">
		<div class="row">
			<div class="col-md-2 transbox">
				<jsp:include page="../sidebars/rdil_home_sidebar.jsp"></jsp:include>
			</div>
			<div class="col-md-10  transbox">
				<h2>Add Faculty</h2>
				<form action="../AddFaculty" method="post">
					<style>
.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>thead>tr>td, .table-borderless>thead>tr>th {
	border: none;
}
</style>
					<table class="table table-borderless">
						<tr>
							<td>New ID</td>
							<td><input type="text" class="form-control" name="username"></td>
						</tr>
						<tr>
							<td>Name</td>
							<td><input type="text" class="form-control" name="name"></td>
						</tr>
						<tr>
							<td>Email</td>
							<td><input type="text" class="form-control" name="email"></td>
						</tr>
						<tr>
							<td>Contact</td>
							<td><input type="text" class="form-control" name="contact"></td>
						</tr>
						<tr>
							<td>
								<button type="reset" class="btn">Reset</button>
							</td>
							<td>
								<button type="submit" class="btn">Submit</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>