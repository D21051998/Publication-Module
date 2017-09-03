<%@page import="com.publication.model.Login"%>
<%@page import="com.publication.model.Books"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@page import="com.publication.model.Books"%>
<%@page import="com.publication.constants.FetchDepptCode"%>
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Book</title>
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
	border: 0px solid;
	background-color: rgba(255, 255, 255, 0.6);
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

	<jsp:useBean id="dao" class="com.publication.impl.BooksIMPL"
		scope="page"></jsp:useBean>
	<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"
		scope="page"></jsp:useBean>
	<%
		List<Books> list = dao.getAllBooks();
		
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
		if (!lao.getRoleBySessionID(sid).contains("ROLE_DC")) {
			response.sendRedirect("../../account/access_denied.jsp");
			return;
		}
		pageContext.setAttribute("sorter", FetchDepptCode.getDepttCode(lao.getRoleBySessionID(sid)));
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
						<li><a href="../coordinator_home.jsp">Home</a></li>
						<li><a href="view_book_chapter.jsp">View Book Chapter</a></li>
						<li><a href="view_conference_presentation.jsp">View
								Conference Presentation</a></li>
						<li><a href="view_conference_proceeding.jsp">View
								Conference Proceeding</a></li>
						<li><a href="view_journal.jsp">View Journal</a></li>
						<li><a href="view_patent.jsp">View Patents</a></li>
						<li><a href="view_tech_rep.jsp">View Technical Reports</a></li>
						<li><a href="view_book.jsp">View Books</a></li>
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
	<div class="container-fluid content">
		<br> <br> <br>
		<div class="row">
			<div class="col-md-12 transbox">
				<h3>View Book</h3>
				<div>
					<c:if test="${not empty param.approve}">
						<p>
							<c:if test="${param.approve == 'success'}">
								<c:out value="Approving Record Successful"></c:out>
							</c:if>
							<c:if test="${param.approve == 'failed'}">
								<c:out value="Approving Record Unsuccessful"></c:out>
							</c:if>
						</p>
					</c:if>
					<c:if test="${not empty param.reject}">
						<p>
							<c:if test="${param.reject == 'success'}">
								<c:out value="Rejecting Record Successful"></c:out>
							</c:if>
							<c:if test="${param.reject == 'failed'}">
								<c:out value="Rejecting Record Unsuccessful"></c:out>
							</c:if>
						</p>
					</c:if>
				</div>
				<input type="text" class="form-control" id="search"
						placeholder="Type to search">
						
				<table class="table table-bordered">
					<thead>
						<th>PCN & Date Assigned</th>
						<th>Name Of Authors</th>
						<th>Department</th>
						<th>Book Title</th>
						<th>Publisher</th>
						<th>Nationality</th>
						<th>Year</th>
						<th>Month Published</th>
						<th>Page No</th>
						<th>ISBN</th>
						<th>HyperLink</th>
						<th>Indices</th>
						<th>Index Link</th>
						<th>Resource</th>
						<th>Plagiarism Report</th>
						<th>Plagiarism Copy</th>
						<th>Certificate</th>
						<th>Status</th>
					</thead>
					<c:forEach items="${eList}" var="book">
						<c:if test="${sorter == book.deptt}">
							<tr>
								<td><c:if test="${empty book.pcn}">
										<c:out value="Not Generated" />
									</c:if> <c:if test="${not empty book.pcn}">
										<c:out value="${book.pcn}" />
										<br>
										<br>
										<c:out value="${book.monthAssigned}" />
									</c:if></td>
								<td><c:out value="${book.nameOauthors}" /></td>
								<td><c:out value="${book.deptt}" /></td>
								<td><c:out value="${book.title}" /></td>
								<td><c:out value="${book.publisher}" /></td>
								<td><c:out value="${book.nationality}" /></td>
								<td><c:out value="${book.year}" /></td>
								<td><c:out value="${book.monthPublished}" /></td>
								<td><c:out value="${book.pageNo}" /></td>
								<td><c:out value="${book.isbn}" /></td>
								<td><c:out value="${book.hyperlink}" /></td>
								<td><c:out value="${book.index}" /></td>
								<td><c:out value="${book.link}" /></td>
								<c:url value="../../DownloadResource" var="download">
									<c:param name="id" value="${book.id}"></c:param>
									<c:param name="type" value="B"></c:param>
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
								<c:url value="../../action/approve.jsp" var="action">
									<c:param name="id" value="${book.id}" />
									<c:param name="level" value="1"></c:param>
									<c:param name="type" value="B"></c:param>
								</c:url>
								<c:url value="../../action/reject.jsp" var="reject">
								</c:url>
								<c:choose>
									<c:when test="${book.status==0}">
										<td><a class="btn btn-info disabled">Pending</a> <br>
											<a href="${action}&status=1" class="btn btn-success">Approve</a>
											<!-- Reject Button -->
											<button type="button" class="btn btn-danger"
												style="width: 90px;" data-name="${book.id}"
												data-toggle="modal" data-target="#myModal"
												onclick="setModalValue(this)">Reject</button> <!-- Reject Modal -->
											<div class="modal fade" id="myModal" role="dialog">
												<div class="modal-dialog">

													<!-- Modal content-->
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal">&times;</button>
															<h4 class="modal-title">Reason to Reject</h4>
														</div>
														<div class="modal-body">
															<form action="${reject}" method="get">
																<input type="text" class="form-control" name="reason">
																<input type="hidden" class="form-control" name="id"
																	id="reject_id"> <input type="hidden"
																	class="form-control" name="level" value="1"> <input
																	type="hidden" class="form-control" name="status"
																	value="-1"> <input type="hidden"
																	class="form-control" name="type" value="B">
																<button type="submit" class="btn btn-default"
																	name="Submit">Submit</button>
															</form>

														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-default"
																data-dismiss="modal">Close</button>
														</div>
													</div>

												</div>
											</div></td>

									</c:when>
									<c:when test="${book.status==1}">
										<td><a class="btn btn-info disabled">Approved by
												Deptt. Coordinator</a></td>
									</c:when>
									<c:when test="${book.status==-1}">
										<td><a class="btn btn-info disabled">Rejected</a></td>
									</c:when>
									<c:when test="${book.status==2}">
										<td><a class="btn btn-info disabled"> Approved By
												RDIL</a></td>
									</c:when>
									<c:when test="${book.status==-2}">
										<td><a class="btn btn-info disabled">Rejected By RDIL</a></td>
									</c:when>
									<c:otherwise>
										<td>Invalid</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>

		</div>
	</div>
	<script>
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
	<script type="text/javascript">
		function setModalValue(button) {
			var att = button.getAttribute("data-name");
			document.getElementById('reject_id').value = att;
		}
	</script>
</body>
</html>