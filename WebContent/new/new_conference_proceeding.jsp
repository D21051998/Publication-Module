<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Conference Proceedings</title>
<link rel="stylesheet" href="../resources/styles/css/bootstrap.css">
</head>
<input type="hidden" id="refreshed" value="no">
<link rel="stylesheet" href="../resources/styles/css/bootstrap.css">
<style>
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
	background-color: #ffffff;
	border: 1px solid;
	opacity: 0.6;
	filter: alpha(opacity = 60);
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
	<jsp:include page="../headers/new_pages_header.jsp"></jsp:include>
	<jsp:useBean id="fao" class="com.publication.impl.FacultyIMPL"></jsp:useBean>
	<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"></jsp:useBean>
	<%
	String sid = (String) request.getSession(false).getAttribute("sid");
	if (null == sid) {
		response.sendRedirect("../account/access_denied.jsp");
		return;
	}
	if (!lao.getRoleBySessionID(sid).equals("ROLE_FACULTY")) {
		response.sendRedirect("../account/access_denied.jsp");
		return;
	}
	%>
	<div class="container-fluid content">
		<div class="row">
			<div class="col-md-2 transbox">
				<jsp:include page="../sidebars/new_pages_sidebar.jsp"></jsp:include>
			</div>
			<div class="col-md-10 transbox">
				<style>
h3 {
	font-family: "Century Gothic", CenturyGothic, AppleGothic, sans-serif;
	font-size: 20px;
	font-style: normal;
	font-variant: normal;
	font-weight: bolder;
	line-height: 23px;
}
</style>
				<h3>Add Conference Proceedings</h3>
				<style>
.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>thead>tr>td, .table-borderless>thead>tr>th {
	border: none !important;
}
</style>
				<form method="POST" action="../AddPublicationService"
					enctype="multipart/form-data">

					<table class="table table-borderless">

						<tr>
							<td>Name of Authors<br>(in the seq. as mentioned in the
								Conference Proceedings)
							</td>
							<td><input type="text" name="nameOauthors"
								placeholder="Seperate all names with commas" required="on"
								autocomplete="off" class="form-control"></td>
						</tr>
						<tr>
						<tr>
							<td>Deptt.</td>
							<td><select class="form-control" name="deptt">
									<option value="aps">APS</option>
									<option value="cse">CSE</option>
									<option value="ece">ECE</option>
									<option value="med">MED</option>
									<option value="cee">CEE</option>
									<option value="som">SOM</option>
									<option value="sol">SOL</option>
									<option value="cll">CLL</option>
							</select></td>
						</tr>
						<tr>
							<td>Title of Paper</td>
							<td><input type="text" name="title" class="form-control"
								required="on" autocomplete="off"></td>
						</tr>
						<tr>
							<td>Proceedings of</td>
							<td><input type="text" name="proceedingsOf" required="on"
								autocomplete="off" class="form-control"></td>
						</tr>
						<tr>
							<td>International/National</td>
							<td><select name="nationality" id="nationality"
								class="form-control">
									<option value="International">International</option>
									<option value="National">National</option>
							</select></td>
						</tr>
						<tr>
							<td>Venue Details for Conference</td>
							<td><input type="text" name="venue" required="on"
								autocomplete="off" class="form-control"></td>
						</tr>

						<tr>
							<td>Year</td>
							<td><select class="form-control" name="year">
									<%
										for (int i = Calendar.getInstance().get(Calendar.YEAR); i >= 1980; i--) {
									%>
									<option value="<%=i%>"><%=i%></option>
									<%
										}
									%>
							</select></td>
						</tr>
						<tr>
							<td>Month in which published</td>
							<td><select class="form-control" name="monthPublished">
									<%
										String[] months = new String[] { "January", "Feburary", "March", "April", "May", "June", "July", "August",
												"September", "October", "November", "December" };
										for (int i = 0; i < months.length; i++) {
									%>
									<option value="<%=months[i]%>"><%=months[i]%></option>
									<%
										}
									%>
							</select></td>
						</tr>
						<tr>
							<td>Page No.</td>
							<td><input type="text" name="pageNo" required="on"
								autocomplete="off" class="form-control"></td>
						</tr>
						<tr>
							<td>Publishers</td>
							<td><input type="text" name="publisher" required="on"
								autocomplete="off" class="form-control"></td>
						</tr>

						<tr>
							<td>Hyper Link</td>
							<td><input type="text" name="hyperLink" required="on"
								autocomplete="off" class="form-control"></td>
						</tr>
						<tr>
							<td>Mention if indexed in:</td>
							<td>
								<div align="left">
									<input type="checkbox" name="index" value="WOS">WOS<br>
									<input type="checkbox" name="index" value="Scopus">Scopus<br>
									<input type="checkbox" name="index" value="Google Scholar">Google
									Scholar<br> <input type="checkbox" name="indexFlag"
										value="Indian Citation Index">Indian Citation Index<br>
									<input type="text" class="form-control" name="indexFlag"
										placeholder="Any Other"> <input type="checkbox"
										name="index" value="none">Not Indexed at all..<br>
								</div>
							<td>
						</tr>
						<tr>
							<td>Link for Indexing</td>
							<td><input type="text" name="link" required="on"
								autocomplete="off" class="form-control"></td>
						</tr>
						<tr>
							<td>Publication</td>
							<td><input type=file name=publication required="on" /></td>
						</tr>
						<tr>
							<td>Plagiarism Report</td>
							<td><input type=file name=plagReport required="on" /></td>
						</tr>
						<tr>
							<td>Plagiarism Copy</td>
							<td><input type=file name=plagCopy required="on" /></td>
						</tr>
						<tr>
							<td>Certificate</td>
							<td><input type=file name=certificate required="on" /></td>
						</tr>

						<tr>
							<input type="hidden" name="writtenBy"
								value="<%=lao.getUsernameBySessionID(sid)%>" />
							<input type="hidden" name="status" value="0">
							<input type="hidden" name="publicationType" value="P" />
						<tr>
							<td><button type="reset">Reset</button></td>
							<td><button type="submit">Submit</button></td>
						</tr>
					</table>
				</form>
			</div>

		</div>
	</div>

</body>
</html>