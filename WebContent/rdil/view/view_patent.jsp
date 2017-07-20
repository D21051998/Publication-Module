<%@page import="com.publication.model.Patent"%>
<%@page import="com.publication.constants.FetchDepptCode"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Patent</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
	background-color: rgba(255,255,255,0.6);
	border: 1px solid;
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
	
	<jsp:useBean id="dao" class="com.publication.impl.PatentIMPL"
		scope="page"></jsp:useBean>
	<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"
		scope="page"></jsp:useBean>
	<%
		List<Patent> list = dao.getAllPatents();
		for (Patent j : list) {
			System.out.println(j);
		}
		String sid = (String) request.getSession(false).getAttribute("sid");
		if (null == sid) {
			response.sendRedirect("../account/access_denied.jsp");
			return;
		}
		pageContext.setAttribute("principal", lao.getUsernameBySessionID(sid));
		System.out.println(pageContext.getAttribute("principal"));
		request.setAttribute("eList", list);
	%>
	<jsp:include page="../../headers/view_page_header.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<div class="container-fluid content">

		<div class="row">

			<div class="col-md-12 transbox">
				<h3>View Patents</h3>
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
				<br>
				<div style="width: 400px;">
					<input type="text" class="form-control" id="search"
						placeholder="Type to search">
				</div>

				<table class="table table-bordered" id="table">

					<thead>
						<th>PCN & Date Assigned</th>
						<th>faculty</th>
						<th>Department</th>
						<th>Title</th>
						<th>Nationality</th>
						<th>Country</th>
						<th>Application No</th>
						<th>Application Year</th>
						<th>Application Date</th>
						<th>Patent Year</th>
						<th>Award Date</th>
						<th>Patent No</th>
						<th>Resource</th>
						<th>Plag. Report</th>
						<th>Status</th>
						<th>Edit</th>
						<th>Delete</th>
					</thead>
					<c:forEach items="${eList}" var="patent">
						<c:if test="${patent.status>0}">
							<tr>

								<td><c:if test="${empty patent.pcn}">
										<c:out value="Not Generated" />
									</c:if> 
									<c:if test="${not empty patent.pcn}">
										<c:out value="${patent.pcn}" /><br> <br> <c:out
										value="${patent.monthAssigned}" />
									</c:if></td>
								<td><c:out value="${patent.faculty}" /></td>
								<td><c:out value="${patent.deptt}" /></td>
								<td><c:out value="${patent.title}" /></td>
								<td><c:out value="${patent.nationality}" /></td>
								<td><c:out value="${patent.country}" /></td>
								<td><c:out value="${patent.applicationNo}" /></td>
								<td><c:out value="${patent.applicationYear}" /></td>
								<td><c:out value="${patent.applicationDate}" /></td>
								<td><c:out value="${patent.patentYear}" /></td>
								<td><c:out value="${patent.awardDate}" /></td>
								<td><c:out value="${patent.patentNo}" /></td>
								<c:url value="../../DownloadResource" var="download">
									<c:param name="id" value="${patent.id}"></c:param>
									<c:param name="type" value="T"></c:param>
								</c:url>

								<td><a href="${download}&index=0" class="btn btn-info">
										<span class="glyphicon glyphicon-download"></span>
								</a></td>
								<td><a href="${download}&index=1" class="btn btn-info">
										<span class="glyphicon glyphicon-download"></span>
								</a></td>

								<c:url value="../../action/approve.jsp" var="action">
									<c:param name="id" value="${patent.id}" />
									<c:param name="level" value="2"></c:param>
									<c:param name="type" value="T"></c:param>
								</c:url>
								<c:url value="../../action/reject.jsp" var="reject">
								</c:url>
								<c:choose>

									<c:when test="${patent.status==1}">
										<td><a class="btn btn-info disabled">Approved by Deptt. Coordinator</a><br> <a
											href="${action}&status=2" class="btn btn-success">Approve</a>
											<button type="button" class="btn btn-danger" style="width:90px;"
												data-name="${patent.id}" data-toggle="modal"
												data-target="#myModal" onclick="setModalValue(this)">Reject</button>
											<div class="modal fade" id="myModal" role="dialog">
												<div class="modal-dialog">

													<!-- Modal content-->
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal">&times;</button>
															<h4 class="modal-title">
																Reason to Reject
															</h4>
														</div>
														<div class="modal-body">
															<form action="${reject}" method="get">
																<input type="text" class="form-control" name="reason">
																<input type="hidden" class="form-control" name="id"
																	id="reject_id">
																<input type="hidden" class="form-control" name="level"
																	value="2">
																<input type="hidden" class="form-control" name="status"
																	value="-2">	
																<input type="hidden" class="form-control" name="type"
																	value="T">	
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
									<c:when test="${patent.status==2}">
										<td><a class="btn btn-info">Approved By RDIL</a></td>
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
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src=".https://getbootstrap.com/dist/js/bootstrap.min.js"></script>
</body>
</html>