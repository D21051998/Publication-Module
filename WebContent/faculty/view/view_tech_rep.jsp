<%@page import="com.publication.model.Login"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@page import="java.util.List"%>
<%@page import="com.publication.model.TechnicalReport"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Technical Reports</title>
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
body {
	background-color: #fcfcfc;
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
	background-color: #ffffff;
	border: 1px solid;
	opacity: 0.6;
	filter: alpha(opacity = 60);
	width: auto;
	/* For IE8 and earlier */
}

.content p {
	margin: 15px;
	background: rgba(255, 255, 255, 0.3);
	padding: 5px;
	box-shadow: 0 0 5px gray;
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
	<jsp:useBean id="dao" class="com.publication.impl.TechnicalReportIMPL"
		scope="page"></jsp:useBean>
	<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"
		scope="page"></jsp:useBean>
	<%
		List<TechnicalReport> list = dao.getAllTechnicalReports();
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
		pageContext.setAttribute("principal", lao.getUsernameBySessionID(sid));
		System.out.println(pageContext.getAttribute("principal"));
		request.setAttribute("eList", list);
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

			<ul class="nav navbar-nav navbar-left">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false" style="color: #9e433d !important;">Navigate
						To<span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="../faculty_home.jsp">Home</a></li>
						<li><a href="view_book_chapter.jsp">View Book Chapter</a></li>
						<li><a href="view_conference_presentation.jsp">View
								Conference Presentation</a></li>
						<li><a href="view_conference_proceeding.jsp">View
								Conference Proceeding</a></li>
						<li><a href="view_journal.jsp">View Journal</a></li>
						<li><a href="view_patent.jsp">View Patents</a></li>
						<li><a href="view_tech_rep.jsp">View Technical Reports</a></li>
						<li><a href="view_book.jsp">View Books</a></li>
						<li><a href="view_rejected.jsp">View Rejected</a></li>
					</ul></li>
			</ul>
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

			<div class="col-md-12 transbox">
				<h3>View Technical Reports</h3>
				<div>
					<c:if test="${not empty param.add}">
						<p>
							<c:if test="${param.add == 'success'}">
								<c:out value="Adding Record Successful"></c:out>
							</c:if>
							<c:if test="${param.add == 'failed'}">
								<c:out value="Adding Record Unsuccessful"></c:out>
							</c:if>
						</p>
					</c:if>
					<c:if test="${not empty param.update}">
						<p>
							<c:if test="${param.update == 'success'}">
								<c:out value="Updating Record Successful"></c:out>
							</c:if>
							<c:if test="${param.update == 'failed'}">
								<c:out value="Updating Record Unsuccessful"></c:out>
							</c:if>
						</p>
					</c:if>
				</div>
				<input type="text" class="form-control" id="search"
						placeholder="Type to search">
				<table class="table table-bordered">

					<thead>
						<th>PCN & Date Assigned</th>
						<th>faculty</th>
						<th>Department</th>
						<th>Title</th>
						<th>Year</th>
						<th>Date</th>
						<th>Remarks</th>
						<th>Month Published</th>
						<th>Resource</th>
						<th>Plagiarism Report</th>
						<th>Plagiarism Copy</th>
						<th>Certificate</th>
						<th>Status</th>
						<th>Edit</th>
						<th>Delete</th>
					</thead>
					<c:forEach items="${eList}" var="report">
						<c:if test="${principal == report.writtenBy}">
							<tr>

								<td><c:if test="${empty report.pcn}">
										<c:out value="Not Generated" />
									</c:if> <c:if test="${not empty report.pcn}">
										<c:out value="${report.pcn}" />
										<br>
										<br>
										<c:out value="${report.monthAssigned}" />
									</c:if></td>
								<td><c:out value="${report.faculty}" /></td>
								<td><c:out value="${report.deptt}" /></td>
								<td><c:out value="${report.title}" /></td>
								<td><c:out value="${report.year}" /></td>
								<td><c:out value="${report.date}" /></td>
								<td><c:out value="${report.remarks}" /></td>
								<td><c:out value="${report.monthPublished}" /></td>
								<c:url value="../../DownloadResource" var="download">
									<c:param name="id" value="${report.id}"></c:param>
									<c:param name="type" value="R"></c:param>
								</c:url>
								<td><a href="${download}&index=0" class="btn btn-info">
										<span class="glyphicon glyphicon-download"></span>
								</a></td>
								<td><a href="${download}&index=1" class="btn btn-info">
										<span class="glyphicon glyphicon-download"></span>
								</a></td>
								<td><a href="${download}&index=2" class="btn btn-info">
										<span class="glyphicon glyphicon-download"></span>
								</a></td>
								<td><a href="${download}&index=3" class="btn btn-info">
										<span class="glyphicon glyphicon-download"></span>
								</a></td>

								<c:choose>
									<c:when test="${report.status==0}">
										<td>Pending</td>
									</c:when>
									<c:when test="${report.status==1}">
										<td><a>Approved by Deptt. Coordinator</a>
									</c:when>
									<c:when test="${report.status==-1}">
										<td><a>Rejected</a></td>
									</c:when>
									<c:when test="${report.status==2}">
										<td><a>Approved By RDIL</a></td>
									</c:when>
									<c:when test="${report.status==-2}">
										<td><a>Rejected By RDIL</a></td>
									</c:when>
									<c:otherwise>
										<td>Invalid</td>
									</c:otherwise>
								</c:choose>
								<c:url value="../edit/edit_tech_rep.jsp" var="edit">
									<c:param name="id" value="${report.id}"></c:param>
								</c:url>
								<c:url value="../../action/delete.jsp" var="delete">
									<c:param name="id" value="${report.id}"></c:param>
									<c:param name="type" value="R"></c:param>
								</c:url>

								<c:if test="${report.status <= 0 }">
									<td><a href='<c:out value="${edit}"/>'>Edit</a></td>
									<td><a href='<c:out value="${delete}"/>'>Delete</a></td>
								</c:if>
								<c:if test="${ report.status > 0}">
									<td>Cannot be edited</td>
									<td>Cannot be deleted</td>
								</c:if>

							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>

		</div>
	</div>
	<script type="text/javascript">
		var $rows = $('#table tr');
		$('#search').keyup(function() {
			var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();

			$rows.show().filter(function() {
				var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
				return !~text.indexOf(val);
			}).hide();
		});
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src=".https://getbootstrap.com/dist/js/bootstrap.min.js"></script>


</body>
</html>