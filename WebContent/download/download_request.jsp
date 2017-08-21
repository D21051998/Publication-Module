<%@page import="com.publication.constants.Roles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
<link href="../resources/styles_header/bootstrap.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="../resources/styles_header/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<!-- Custom styles for this template -->
<link href="../resources/styles_header/custom.css" rel="stylesheet">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../resources/styles_header/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
td {
	vertical-align: middle;
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 15px;
	font-style: normal;
	font-variant: normal;
	font-weight: bold;
	line-height: 23px;
	width: 220px;
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

.content:before {
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

.content {
	overflow: visible;
	position: relative;
}

div.transbox {
	margin: 30px;
	background-color:rgba(255,255,255,0.6);
	border: 1px solid;
	width: auto;
	border-radius: 5px;
	/* For IE8 and earlier */
}

.content p {
	margin: 15px;
	background: rgba(255, 255, 255, 0.3);
	padding: 5px;
	box-shadow: 0 0 5px gray;
}

.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>thead>tr>td, .table-borderless>thead>tr>th {
	border: none;
}
</style>

</head>
<body>
	<jsp:useBean id="dao" class="com.publication.impl.LoginIMPL"></jsp:useBean>
	<%
		String sid = (String) request.getSession(false).getAttribute("sid");
		System.out.println(sid);
		if (null == sid) {
			response.sendRedirect("../");
			return;
		}
		String role = dao.getRoleBySessionID(sid);
	%>
	<div class="container">
		<div class="row content">
			<div class="col-md-4"></div>
			<div class="col-md-4 transbox">
				<form action="../DownloadResponse" method="get">
					<style>
					.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>thead>tr>td, .table-borderless>thead>tr>th {
	border: none;
}
					</style>
					<table class="table table-borderless">
						<tr>
							<td>From</td>
							<td><input type="date" id="datePicker2" class="form-control" required="true"
								name="from"></td>
						</tr>
						<tr>
							<td>TO</td>
							<td><input type="date" id="datePicker" class="form-control" required="true"
								name="to"></td>
						</tr>
						<tr>
							<td>Source</td>
							<td><input type="checkbox" name="source" value="journal">Journal<br>
								<input type="checkbox" name="source" value="books">Books<br>
								<input type="checkbox" name="source" value="bookChapter">Book
								Chapter<br> 
								<input type="checkbox" name="source"
								value="confProceeding">Conference Proceedings<br> 
								<input
								type="checkbox" name="source" value="confPresentation">Conference
								Presentation<br> 
								<input type="checkbox" name="source"
								value="patent">Patents<br> 
								<input type="checkbox"
								name="source" value="techRep">Technical Report</td>
						</tr>
						<tr>
							<td>Branch</td>

							<td>
								<%
									switch (role) {
										case "ROLE_FACULTY" :
											break;
										case "ROLE_RDIL" :
											out.print("<input type=\"checkbox\" name=\"branch\" value=\"APS\">APS<br>");
											out.print("<input type=\"checkbox\" name=\"branch\" value=\"CSE\">CSE<br>");
											out.print("<input type=\"checkbox\" name=\"branch\" value=\"CEE\">CEE<br>");
											out.print("<input type=\"checkbox\" name=\"branch\" value=\"ECE\">ECE<br>");
											out.print("<input type=\"checkbox\" name=\"branch\" value=\"MED\">MED<br>");
											out.print("<input type=\"checkbox\" name=\"branch\" value=\"SOM\">SOM<br>");
											out.print("<input type=\"checkbox\" name=\"branch\" value=\"SOL\">SOL<br>");
											out.print("<input type=\"checkbox\" name=\"branch\" value=\"CLL\">CLL");
											break;
										case "ROLE_DC_APS" :
											out.print("<input type=\"checkbox\" name=\"branch\" selected value=\"APS\">APS");
											break;
										case "ROLE_DC_CSE" :
											out.print("<input type=\"checkbox\" name=\"branch\" selected  value=\"CSE\">CSE");
											break;
										case "ROLE_DC_CEE" :
											out.print("<input type=\"checkbox\" name=\"branch\" selected  value=\"CEE\">CEE");
											break;
										case "ROLE_DC_ECE" :
											out.print("<input type=\"checkbox\" name=\"branch\" selected  value=\"ECE\">ECE");
											break;
										case "ROLE_DC_MED" :
											out.print("<input type=\"checkbox\" name=\"branch\" selected  value=\"MED\">MED");
											break;
										case "ROLE_DC_SOM" :
											out.print("<input type=\"checkbox\" name=\"branch\" selected  value=\"SOM\">SOM");
											break;
										case "ROLE_DC_SOL" :
											out.print("<input type=\"checkbox\" name=\"branch\" selected  value=\"SOL\">SOL");
											break;
										case "ROLE_DC_CLL" :
											out.print("<input type=\"checkbox\" name=\"branch\" selected  value=\"CLL\">CLL");
											break;
									}
								%>
							</td>
						</tr>
						<tr>
							<td>
								<button class="form-control" type="reset">Reset</button>
							</td>
							<td>
								<button class="form-control" type="submit">Download</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>

	</div>

	<script>
		$(document).ready(function() {
			var now = new Date();
			var month = (now.getMonth() + 1);
			var day = now.getDate();
			if (month < 10)
				month = "0" + month;
			if (day < 10)
				day = "0" + day;
			var today = now.getFullYear() + '-' + month + '-' + day;
			$('#datePicker').val(today);
		});
	</script>
</body>
</html>