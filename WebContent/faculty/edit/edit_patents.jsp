<%@page import="com.publication.model.Patent"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Patent</title>

<link rel="stylesheet" href="../resources/styles/css/bootstrap.css">
<style>
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
	background-color: rgba(255,255,255,0.6);

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
<body>
	<jsp:useBean id="dao" class="com.publication.impl.PatentIMPL"></jsp:useBean>
	<%
		Patent patent = dao.getPatentByID(request.getParameter("id"));
	if(null==patent){
		return;
	}
	pageContext.setAttribute("patent",patent );
	%>

	<jsp:include page="../../headers/new_pages_header.jsp"></jsp:include>
	<div class="container-fluid content">
		<div class="row">
			<div class="col-md-2 transbox">
				<jsp:include page="../../sidebars/new_pages_sidebar.jsp"></jsp:include>
			</div>
			<div class="col-md-10 transbox">
				<h3>Edit New Patents</h3>
				<style>
.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>thead>tr>td, .table-borderless>thead>tr>th {
	border: none !important;
}
</style>
				<form method="post" action="../../EditPublicationService"
					enctype="multipart/form-data">
					<input type="hidden" name="publicationType" value="T">
					<input type="hidden" name="id" value="${patent.id}">
						<style>
.table-borderless>tbody>tr>td, .table-borderless>tbody>tr>th,
	.table-borderless>tfoot>tr>td, .table-borderless>tfoot>tr>th,
	.table-borderless>thead>tr>td, .table-borderless>thead>tr>th {
	border: none !important;
}
</style>
					<table class="table table-borderless">
						<tr>
							<td>Faculty</td>
							<td><input type="text" class="form-control" required="on"
								name="faculty" value="${patent.faculty}"></td>
						</tr>
						<tr>
							<td>Deptt.</td>
							<td><select class="form-control" name="deptt">
							<option value="${patent.deptt}">${patent.deptt}</option>
									<option value="cse">CSE</option>	
									<option value="ece">ECE</option>
									<option value="me">ME</option>
									<option value="cvu">CVU</option>
							</select></td>
						</tr>

						<tr>
							<td>Title of Patent</td>
							<td><input type="text" class="form-control" required="on"
								name="title" value="${patent.title}"></td>
						</tr>

						<tr>
							<td>International/National</td>
							<td><select name="nationality" onclick="getNation()"
								id="nationality" class="form-control">
									<c:if test="${patent.nationality == 'International' }">
										<option value="International">International</option>
										<option value="National">National</option>
									</c:if>
									<c:if test="${patent.nationality == 'National' }">
										<option value="National">National</option>
										<option value="International">International</option>
									</c:if>
							</select></td>
						</tr>

						<tr>
							<td>Country</td>
							<td><input type="text" class="form-control" id="country"
								name="country" value="${patent.country}"></td>
						</tr>

						<tr>
							<td>Patent Application Number</td>
							<td><input type="text" name="applicationNo"
								class="form-control" value="${patent.applicationNo}"></td>
						</tr>
						<tr>
							<td>Patent Application Year</td>
							<td><select class="form-control" name="applicationYear">
							<option value="${patent.applicationYear}">${patent.applicationYear}</option>
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
							<td>Patent Application Date</td>
							<td><select class="form-control" name="applicationDate">
							<option value="${patent.applicationDate}">${patent.applicationDate}</option>
									<%
										String[] months = new String[] { "January", "Feburary", "March", "April", "May", "June", "July", "August",
												"September", "October", "November", "December" };
										for (int i = 1; i <= 31; i++) {
									%>
									<option value="<%=i%>"><%=i%></option>
									<%
										}
									%>
							</select></td>
						</tr>
						<tr>
							<td>Patent Award Year</td>
							
							<td><select class="form-control" name="patentYear">
							<option value="${patent.patentYear}">${patent.patentYear}</option>
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
							<td>Patent Award Date</td>
							<td><select class="form-control" name="awardDate">
							<option value="${patent.awardDate}">${patent.awardDate}</option>
									<%
										months = new String[] { "January", "Feburary", "March", "April", "May", "June", "July", "August",
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
							<td>Patent No.</td>
							<td><input type='text' class="form-control" name="patentNo" value='${patent.patentNo}'></td>
						</tr>
						<tr>
							<td>Publication</td>
							<td>${patent.publicationFileName}<br>
							<input type="file" name="publication" /></td>
						</tr>
						<tr>
							<td>Plag. Report</td>
							<td>${patent.plagReportFileName}<br>
							<input type="file" name="plagReport" /></td>
						</tr>
						<tr>

							<td><button type="reset" class="form-control">Reset</button></td>
							<td><button type="submit" class="form-control">Submit</button></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
<script>
	function getNation() {
		var form = document.getElementById("nationality");
		var val = form.options[form.selectedIndex].value;
		if (val == "National") {
			document.getElementById("country").value = "India";
		}
	}
</script>

</body>
</html>