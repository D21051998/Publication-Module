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

body{
	background-color: #ffa830;
	background-image: url("resources/images/DSCN7348.jpg");
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
	<%
		HttpSession sess = request.getSession(false);
	System.out.println(sess);
	System.out.println(request.getSession(false).getAttribute("sid"));

		if (sess != null) {
			String sid =(String) request.getSession(false).getAttribute("sid");
			if (sid != null) {
				System.out.println(lao.getUsernameBySessionID(sid));
				response.sendRedirect(Redirect.redirect(lao.getRoleBySessionID(sid), true));
				return;
			}

		}
	%>


	<div class="container">
		<div class="row">
			<div class="col-md-6" align="center">
				<h3><strong>The NorthCap University</strong></h3>
				<p>Sector-23A, Gurugram</p>
				<h4><strong>Publication Module</strong></h4>
			</div>
			<div class="col-md-6 form-group" align="center">
				
				<form method="POST" action="LoginService">
					<table>
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
			</div>
		</div>
	</div>

</body>
</html>