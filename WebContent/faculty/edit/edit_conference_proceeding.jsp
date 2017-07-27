<%@page import="com.publication.model.ConferenceProceedings"%>
<%@page import="com.publication.model.ConferencePresentation"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Conference Proceedings</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
    <link href="../../resources/styles_header/navbar_addition.css"
	rel="stylesheet" type="text/css">
    <script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script><style>
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
	background-image: url('../../resources/images/DSCN7348.jpg');
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
	background-color: rgba(255,255,255,0.6);
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
					src="../../resources/images/ncu logo.png" width="150px"
					height="50px" id="logo" /> The NorthCap University
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
						<li><a href="">Edit Profile</a></li>
						<li><a href="../../account/logout.jsp">Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</nav>
		<jsp:useBean id="dao" class="com.publication.impl.ConferenceProceedingIMPL"></jsp:useBean>
	<%
		ConferenceProceedings cp =dao.getConferenceProceedingsByID(request.getParameter("id"));
	if(null==cp){
		return;
	}
	pageContext.setAttribute("cp", cp);
	%>
	<br><br><br>
	<div class="container-fluid content">
		<div class="row">
			<div class="col-md-2 transbox">
				<jsp:include page="../../sidebars/new_pages_sidebar.jsp"></jsp:include>
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
				<h3>Edit Conference Proceedings</h3>
				<style>
.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>thead>tr>td, .table-borderless>thead>tr>th {
	border: none !important;
}
</style>
				<form method="POST" action="../../EditPublicationService"
					enctype="multipart/form-data">
					<input type="hidden" name="publicationType" value="P">
					<input type="hidden" name="id" value="${cp.id}">

					<table class="table table-borderless">

						<tr>
							<td>Name of Authors<br>(in the seq. as mentioned in the
								Conference Proceedings)</td>
							<td><input type="text" name="nameOauthors"
								placeholder="Seperate all names with commas" 
								value="${cp.nameOauthors}" class="form-control"></td>
						</tr>
						<tr>
						<tr>
							<td>Deptt.</td>
							<td><select class="form-control" name="deptt">
							<option value="${cp.deptt}">${cp.deptt}</option>
									<option value="cse">CSE</option>
									<option value="ece">ECE</option>
									<option value="me">ME</option>
									<option value="cvu">CVU</option>
							</select></td>
						</tr>
						<tr>
							<td>Title of Paper</td>
							<td><input type="text" name="title" class="form-control" value="${cp.title}"></td>
						</tr>
						<tr>
							<td>Proceedings of</td>
							<td><input type="text" name="proceedingsOf" class="form-control" value="${cp.proceedingsOf}"></td>
						</tr>
						<tr>
							<td>International/National</td>
							<td><select name="nationality" id="nationality"
								class="form-control">
									<c:if test="${cp.nationality == 'International' }">
										<option value="International">International</option>
										<option value="National">National</option>
									</c:if>
									<c:if test="${cp.nationality == 'National' }">
										<option value="National">National</option>
										<option value="International">International</option>
									</c:if>

							</select></td>
						</tr>
						<tr>
							<td>Venue Details for Conference</td>
							<td><input type="text" name="venue" class="form-control" value="${cp.venue}"></td>
						</tr>

						<tr>
							<td>Year</td>
							<td><select class="form-control" name="year">
							<option value="${cp.year}">${cp.year}</option>
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
							<option value="${cp.monthPublished}">${cp.monthPublished}</option>
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
							<td><input type="text" name="pageNo" class="form-control" value="${cp.pageNo}"></td>
						</tr>
						<tr>
							<td>Publishers</td>
							<td><input type="text" name="publisher" class="form-control" value="${cp.publisher}"></td>
						</tr>

						<tr>
							<td>Hyper Link</td>
							<td><input type="text" name="hyperLink" class="form-control" value="${cp.hyperlink}"></td>
						</tr>
						<tr>
							<td>Mention if indexed in:</td>
							<td>
							Current Indices:${cp.index}
							<br>
							<div align="left"><input type="checkbox" name="index" value="WOS">WOS<br>
								<input type="checkbox" name="index" value="Scopus">Scopus<br>
								<input type="checkbox" name="index" value="Google Scholar">Google
								Scholar<br> <input type="checkbox" name="index"
								value="Thomson Reuter">Thomson Reuter<br> <input
								type="checkbox" name="index" value="Elsevier">Elsevier<br>
								<input type="checkbox" name="index" value="none">Not
								Indexed at all..<br></div>
							<td>
						</tr>
						<tr>
							<td>Link for Indexing</td>
							<td><input type="text" name="link" class="form-control"  value="${cp.link}"></td>
						</tr>
						<tr>
							<td>Publication</td>
							<td>${cp.publicationFileName}<br>
							<input type="file" name="publication" /></td>
						</tr>
						<tr>
							<td>Plag. Report</td>
							<td>${cp.plagReportFileName}<br>
							<input type="file" name="plagReport" /></td>
						</tr>
						<tr>
							<td>Plag. Copy</td>
							<td>${cp.plagCopyFileName}<br>
							<input type="file" name="plagCopy" /></td>
						</tr>
						<tr>
						
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