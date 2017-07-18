<%@page import="java.util.Calendar"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@page import="com.publication.model.Journal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	width: 100%;
}

li.borderless {
	border-bottom: 0 none;
	border-top: none;
}

.not-active {
	pointer-events: none;
	cursor: default;
}

ul {
	list-style: none;
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
	<jsp:useBean id="jao" class="com.publication.impl.JournalIMPL"></jsp:useBean>
	<%
		Journal journal = jao.getJournalByID(request.getParameter("id"));
		if (null == journal) {
			return;
		}
		pageContext.setAttribute("journal", journal);
	%>
	
	<jsp:include page="../../headers/new_pages_header.jsp"></jsp:include>

	<div class="container-fluid">
		<br> <br> <br>
		<div class="row">

			<div class="col-md-2">
				<jsp:include page="../../sidebars/view_pages_sidebar.jsp"></jsp:include>
			</div>
			<div class="col-md-10">
				<h3>Edit Journal</h3>
				<form method="POST" action="../../EditPublicationService"
					enctype="multipart/form-data">
					
					<input type="hidden" name="publicationType" value="J">
					<input type="hidden" name="id" value="${journal.id}">
					<table class="form-group">
						<tr>
							<td>Name of authors</td>
							<td><input type="text" class="form-control"
								name="nameOauthors" value="${journal.nameOauthors}"></td>
						</tr>
						<tr>
							<td>Deptt.</td>
							<td><select class="form-control" name="deptt">
									<option value="${journal.deptt}" disabled>${journal.deptt}</option>

									<option value="CSE">CSE</option>
									<option value="ECE">ECE</option>
									<option value="ME">ME</option>
									<option value="CVU">CVU</option>
							</select></td>
						</tr>
						<tr>
							<td>Title Of Paper</td>
							<td><input type="text" class="form-control" name="title"
								value="${journal.title}"></td>
						</tr>
						<tr>
							<td>Journal</td>
							<td><input type="text" class="form-control" name="journal"
								value="${journal.journal}"></td>
						</tr>
						<tr>
							<td>International/National</td>
							<td><select name="nationality" id="nationality"
								class="form-control">
									<option value="${journal.nationality}">${journal.nationality}</option>
									<option value="International">International</option>
									<option value="National">National</option>
							</select></td>
						</tr>
						<tr>
							<td>Year</td>
							<td><select class="form-control" name="year">
									<option value="${journal.year}">${journal.year}</option>
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
									<option value="${journal.monthPublished}">${journal.monthPublished}</option>
									<%
										String[] months = new String[]{"January", "Feburary", "March", "April", "May", "June", "July", "August",
												"September", "October", "November", "December"};
										for (int i = 0; i < months.length; i++) {
									%>
									<option value="<%=months[i]%>"><%=months[i]%></option>
									<%
										}
									%>
							</select></td>
						</tr>
						<tr>
							<td>Volume</td>
							<td><input type="text" class="form-control" name="volume"
								value="${journal.volume}"></td>
						</tr>
						<tr>
							<td>Number/Issue</td>
							<td><input type="text" class="form-control" name="issue"
								value="${journal.issue}"></td>
						</tr>
						<tr>
							<td>Page No.</td>
							<td><input type="text" class="form-control" name="pageNo"
								value="${journal.pageNo}"></td>
						</tr>
						<tr>
							<td>DOI No.</td>
							<td><input type="text" class="form-control" name="doiNo"
								required="on" value="${journal.doiNo}"></td>
						</tr>
						<tr>
							<td>Impact Factor</td>
							<td><input type="text" class="form-control"
								name="impactFactor" value="${journal.impactFactor}"></td>
						</tr>
						<tr>
							<td>Specify which impact factor</td>
							<td><input type="text" class="form-control"
								name="whatImpactFactor" value="${journal.whatImpactFactor}"></td>
						</tr>
						<tr>
							<td>Link for Impact factor</td>
							<td><input type="text" class="form-control"
								name="linkImpFactor" value="${journal.linkImpFactor}"></td>
						</tr>
						<tr>
							<td>Paid/Unpaid</td>
							<td><select class="form-control" id="paidOrUnpaid"
								onclick="disable_unpaid()" name="paidOrUnpaid">
									<option value="${journal.paidOrUnpaid}">${journal.paidOrUnpaid}</option>
									<option value="Paid" selected="selected">Paid</option>
									<option value="Unpaid">Unpaid</option>
							</select></td>
						</tr>

						<tr>
							<td>Payment done or not</td>
							<td><select class="form-control" id="paymentFlag"
								name="paymentFlag">
									<option value="${journal.paymentFlag}">${journal.paymentFlag}</option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
							</select></td>
						</tr>
						<tr>
							<td>PW: Publication reported in Web of Science</td>
							<td><select class="form-control" name="pwFlag">
									<option value="${journal.pwFlag}">${journal.pwFlag}</option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
							</select></td>
						</tr>
						<tr>
							<td>PS: Publication reported in Scopus</td>
							<td><select class="form-control" name="psFlag">
									<option value="${journal.psFlag}">${journal.psFlag}</option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
							</select></td>
						</tr>
						<tr>
							<td>PG: Publication reported in Google Scholar</td>
							<td><select class="form-control" name="pgFlag">
									<option value="${journal.pgFlag}">${journal.pgFlag}</option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
							</select></td>
						</tr>
						<tr>
							<td>PI: Publication reported in Indian Citation Index</td>
							<td><select class="form-control" name="piFlag">
									<option value="${journal.piFlag}">${journal.piFlag}</option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
							</select></td>
						</tr>
						<tr>
							<td>Publication</td>
							<td>${journal.publicationFileName}<br> <input
								type="file" name="publication" /></td>
						</tr>
						<tr>
							<td>Plag. Report</td>
							<td>${journal.plagReportFileName}<br> <input
								type="file" name="plagReport" /></td>
						</tr>
						<tr>
							<td>Plag. Copy</td>
							<td>${journal.plagCopyFileName}<br> <input type="file"
								name="plagCopy" /></td>
						</tr>
						<tr>

							<td>
								<button class="form-control" type="reset">Reset</button>
							</td>
							<td>
								<button class="form-control" type="submit" name="submit">Submit</button>
							</td>
						</tr>
					</table>
				</form>
			</div>

		</div>
	</div>
	<script>
		function disable_unpaid() {
			var form = document.getElementById("paidOrUnpaid");
			var val = form.options[form.selectedIndex].value;
			if (val == "Unpaid") {
				document.getElementById("paymentFlag").value = 'No';
			}
		}
	</script>

</body>
</html>