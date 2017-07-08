<%@page import="com.publication.constants.Redirect"%>
<html>
<head>
<title>Home Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
.container {
	max-width: 700px;
	position: relative;
	top: 50%;
	transform: translateY(-50%);
}

td {
	text-align: center;
	vertical-align: middle;
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 15px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: 23px;
}

h3 {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 25px;
	font-style: normal;
	font-variant: normal;
	font-weight: bolder;
	line-height: 23px;
}

h4 {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 25px;
	font-style: normal;
	font-variant: normal;
	font-weight: bolder;
	line-height: 23px;
}

p {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 18px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
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

.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>thead>tr>td, .table-borderless>thead>tr>th {
	border: none;
}

.content:before {
	content: "";
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	z-index: -1;
	display: block;
	background-image: url('resources/images/DSCN7348.jpg');
	-webkit-filter: brightness(0.8);
	filter: brightness(0.8);
	background-size: cover;
	width: 100%;
	height: 100%;
	-webkit-filter: blur(5px);
	-moz-filter: blur(5px);
	-o-filter: blur(5px);
	-ms-filter: blur(5px);
	filter: blur(5px);
}

.content {
	overflow: visible;
	position: relative;
}

div.transbox {
	margin: 30px;
	background-color: #ffffff;
	border: 1px solid;
	opacity: 0.6;
	filter: alpha(opacity = 60);
	width: auto;
	border-radius: 5px;
	/* For IE8 and earlier */
}

a {
	color: #000000;
}
body{
-webkit-touch-callout: none;
-webkit-user-select: none;
-khtml-user-select: none;
-moz-user-select: none;
-ms-user-select: none;
user-select: none;}
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
<body class="content">
	<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"></jsp:useBean>
	<%
		HttpSession sess = request.getSession(false);
		System.out.println(sess);
		System.out.println(request.getSession(false).getAttribute("sid"));

		if (sess != null) {
			String sid = (String) request.getSession(false).getAttribute("sid");
			if (sid != null) {
				System.out.println(lao.getUsernameBySessionID(sid));
				response.sendRedirect(Redirect.redirect(lao.getRoleBySessionID(sid), true));
				return;
			}

		}
	%>


	<div class="container">
		<div class="row transbox">
			<div class="col-md-6" align="center">
				<br>
				<!-- <h3><strong>The NorthCap University</strong></h3>
				 
				 
				<p>Sector-23A, Gurugram</p><br>
				-->
				<br> <img src="resources/images/ncu logo.png"
					class="img-responsive" width="300" height="200"> <br>
				<h4>
					<strong>Publication Module</strong>
				</h4>
			</div>
			<div class="col-md-6 form-group" align="center">

				<form method="POST" action="LoginService">
					<table class="table table-borderless">
						<tr>
							<td><label for="role">Role</label></td>
							<td>&nbsp;</td>
							<td><select class="form-control" id="role" name="role">
									<option value="ROLE_FACULTY">Faculty</option>
									<option value="ROLE_DC_CSE">Deptt. Coordinator (CSU)</option>
									<option value="ROLE_DC_ECE">Deptt. Coordinator (ECE)</option>
									<option value="ROLE_DC_ME">Deptt. Coordinator (ME)</option>
									<option value="ROLE_DC_CVU">Deptt. Coordinator (CVU)</option>
									<option value="ROLE_RDIL">RDIL</option>
									<option value="ROLE_ADMIN">Admin</option>
							</select></td>
						</tr>
						<tr>
							<td><label for="username">Username</label></td>
							<td>&nbsp;</td>
							<td><input class="form-control" type="text" name="username"
								id="username"></td>
						</tr>
						<tr>
							<td><label for="password">Password</label></td>
							<td>&nbsp;</td>
							<td><input class="form-control" type="password"
								name="password" id="password"></td>
						</tr>
						<tr>
							<td>
								<button class="form-control" type="reset">Reset</button>
							</td>
							<td>&nbsp;</td>
							<td>
								<button class="form-control" type="submit">Submit</button>
							</td>
						</tr>
					</table>

				</form>
				<h5>
					<strong><a href="account/forgot_password_request.jsp">Forgot
							Password(Click Here..)</a></strong>
				</h5>
			</div>
		</div>
	</div>

</body>
</html>