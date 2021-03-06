<%@page import="com.publication.model.Login"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@page import="com.publication.model.Books"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Book</title>
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
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
<
style>.container {
	width: 100%;
}

li.borderless {
	border-bottom: 0 none;
	border-top: none;
}

ul {
	list-style: none;
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
	background-color: rgba(255, 255, 255, 0.6);
	border: 0px solid;
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
<style>
.container {
	width: 100%;
}

.borderless {
	border-bottom: 0 none;
	border-top: none;
	border-left: 0;
	border-right: 0;
	background: #d9dfe0;
}

ul {
	list-style: none;
}

#sidebarStyle {
	background: #d9dfe0;
}
</style>
<body>
	<jsp:useBean id="jao" class="com.publication.impl.BooksIMPL"></jsp:useBean>
	<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"></jsp:useBean>
	<%
	String sid = (String) request.getSession(false).getAttribute("sid");
	if (null == sid) {
		response.sendRedirect("../../account/access_denied.jsp");
		return;
	}
	Login login = lao.getLogin(lao.getUsernameBySessionID(sid));
	if (null == login) {
		response.sendRedirect("../../account/access_denied.jsp");
		return;
	}
	if (!lao.getRoleBySessionID(sid).equals("ROLE_FACULTY")) {
		response.sendRedirect("../../account/access_denied.jsp");
		return;
	}
		Books book = jao.getBookByID(request.getParameter("id"));
		if (null == book) {
			return;
		}
		pageContext.setAttribute("book", book);
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
						<li><a href="../../account/change_password.jsp">Change
								Password</a></li>
						<li><a href="../../account/logout.jsp">Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</nav>
	<br>
	<br>
	<br>
	<div class="container-fluid content">
		<div class="row">
			<div class="col-md-2 transbox">
				<jsp:include page="../../sidebars/new_pages_sidebar.jsp"></jsp:include>
			</div>
			<div class="col-md-10 transbox">
				<h3>Edit Book Chapter</h3>
				<form method="post" action="../../EditPublicationService"
					enctype="multipart/form-data">
					<style>
.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>thead>tr>td, .table-borderless>thead>tr>th {
	border: none;
}
</style>

					<table class="table table-borderless">

						<tr>
							<td>Name of Authors<br>(in seq. as mentioned in the
								Book Chapter)
							</td>
							<td><input type="text" class="form-control"
								name="nameOauthors" value="${book.nameOauthors}"
								placeholder="Seperate all names with commas"></td>
						</tr>
						<tr>
							<td>Deptt.</td>
							<td><select class="form-control" name="deptt">
									<option value="${book.deptt}">${book.deptt}</option>
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
							<td>Book Title</td>
							<td><input class="form-control" type="text" name="title"
								value="${book.title}"></td>
						</tr>
						<tr>
							<td>Publisher</td>
							<td><input class="form-control" type="text" name="publisher"
								value="${book.publisher}"></td>
						</tr>
						<tr>
							<td>International/National</td>
							<td><select name="nationality" id="nationality"
								class="form-control">
									<c:if test="${book.nationality == 'International' }">
										<option value="International">International</option>
										<option value="National">National</option>
									</c:if>
									<c:if test="${book.nationality == 'National' }">
										<option value="National">National</option>
										<option value="International">International</option>
									</c:if>

							</select></td>
						</tr>
						<tr>
							<td>Year</td>
							<td><select class="form-control" name="year">
									<option value="${book.year}">${book.year}</option>
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
									<option value="${book.monthPublished}">${book.monthPublished}</option>
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
							<td><input class="form-control" type="text" name="pageNo"
								value="${book.pageNo}"></td>
						</tr>
						<tr>
							<td>Book ISBN No.</td>
							<td><input class="form-control" type="text" name="isbn"
								value="${book.isbn}"></td>
						</tr>
						<tr>
							<td>Hyper Link</td>
							<td><input class="form-control" type="text" name="hyperLink"
								value="${book.hyperlink}"></td>
						</tr>
						<tr>
							<td>Mention if indexed in:</td>
							<td>Current Indices:${book.index} <br>
								<div align="left">
									<input type="checkbox" name="indexFlag" value="WOS">WOS<br>
									<input type="checkbox" name="indexFlag" value="Scopus">Scopus<br>
									<input type="checkbox" name="indexFlag" value="Google Scholar">Google
									Scholar<br> <input type="checkbox" name="indexFlag"
										value="Thomson Reuter">Thomson Reuter<br> <input
										type="checkbox" name="indexFlag" value="Elsevier">Elsevier<br>
									<input type="checkbox" name="indexFlag" value="none">Not
									Indexed at all..<br>
								</div>
							<td>
						</tr>
						<tr>
							<td>Link for Indexing</td>
							<td><input type="text" class="form-control" name="indexLink"
								value="${book.link}"></td>
						</tr>
						<tr>
							<td>Publication</td>
							<td>${book.publicationFileName}<br> <input type="file"
								name="publication" /></td>
						</tr>
						<tr>
							<td>Plagiarism Report</td>
							<td>${book.plagReportFileName}<br> <input type="file"
								name="plagReport" /></td>
						</tr>
						<tr>
							<td>Plagiarism Copy</td>
							<td>${book.plagCopyFileName}<br> <input type="file"
								name="plagCopy" /></td>
						</tr>
						<tr>
							<td>Certificate</td>
							<td>${book.certificateName}<br> <input type="file"
								name="certificate" /></td>
						</tr>
						<input type="hidden" name="publicationType" value="B">
						<input type="hidden" name="id" value="${book.id}">

						<tr>

							<td><button class="btn" class="form-control" type="reset">Reset</button></td>
							<td><button class="btn" class="form-control" type="submit">Submit</button></td>
						</tr>

					</table>
				</form>

			</div>


		</div>
	</div>



</body>
</html>