<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Journal</title>
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
</style>

<script>
	function disable_unpaid() {
		var form = document.getElementById("paid_unpaid");
		var val = form.options[form.selectedIndex].value;
		if (val == "Unpaid") {
			document.getElementById("payment_flag").disabled = true;
		} else if (val == "Paid") {
			document.getElementById("payment_flag").disabled = false;
		}
	}
</script>
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
	<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"></jsp:useBean>
	<jsp:useBean id="fao" class="com.publication.impl.FacultyIMPL"></jsp:useBean>
	<%
		String sid = (String) request.getSession(false).getAttribute("sid");
		if (null == sid) {
			response.sendRedirect("../account/access_denied.jsp");
		}
		System.out.println(sid);
	%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<jsp:include page="../sidebars/new_pages_sidebar.jsp"></jsp:include></div>
			<div class="col-md-7">


				<h2>
					Journal Add Form<%=fao.getFaculties().size()%></h2>
					../add/add_journal.jsp
				<form method="POST" action="../AddJournal">
					<table class="form-group">
						<tr>
							<td>Name of authors</td>
							<td><select class="form-control" id='createTextboxes'>

									<%
										if (fao.getFaculties().size() > 10) {
									%>
									<option>0</option>
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
									<option>6</option>
									<option>7</option>
									<option>8</option>
									<option>9</option>
									<option>10</option>
									<%
										}else{
											for(int i=0;i<fao.getFaculties().size()+1;i++){
												%>
												<option><%=i%></option>
												<%
											}
										}
									%>

							</select>
								<td><div id=screens></div></td> 
								<!-- <input type="text" class="form-control" name="nameOauthors"
					placeholder="Name of Authors.."> --></td>
						</tr>
						<tr>
							<td>Deptt.</td>
							<td><select class="form-control" name="deptt">
									<option value="cse">CSE</option>
									<option value="ece">ECE</option>
									<option value="me">ME</option>
									<option value="cvu">CVU</option>
							</select></td>
						</tr>
						<tr>
							<td>Title Of Paper</td>
							<td><input type="text" class="form-control" name="title"
								placeholder="Title goes here.."></td>
						</tr>
						<tr>
							<td>Journal</td>
							<td><input type="text" class="form-control" name="journal"
								placeholder="Journal.."></td>
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
								placeholder="Volume.."></td>
						</tr>
						<tr>
							<td>Number/Issue</td>
							<td><input type="text" class="form-control" name="issue"
								placeholder="Which volume.."></td>
						</tr>
						<tr>
							<td>Page No.</td>
							<td><input type="text" class="form-control" name="pageNo"
								placeholder="Page No.."></td>
						</tr>
						<tr>
							<td>DOI No.</td>
							<td><input type="text" class="form-control" name="doiNo"
								required="on" placeholder="DOI No.."></td>
						</tr>
						<tr>
							<td>Impact Factor</td>
							<td><input type="text" class="form-control"
								name="impactFactor" placeholder="Impact Factor"></td>
						</tr>
						<tr>
							<td>Specify which impact factor</td>
							<td><input type="text" class="form-control"
								name="whatImpactFactor" placeholder="Which"></td>
						</tr>
						<tr>
							<td>Link for Impact factor</td>
							<td><input type="text" class="form-control"
								name="linkImpFactor" placeholder="Link goes here.."></td>
						</tr>
						<tr>
							<td>Paid/Unpaid</td>
							<td><select class="form-control" id="paidOrUnpaid"
								onclick="disable_unpaid()" name="paidOrUnpaid">
									<option value="Paid" selected="selected">Paid</option>
									<option value="Unpaid">Unpaid</option>
							</select></td>
						</tr>

						<tr>
							<td>Payment done or not</td>
							<td><select class="form-control" id="paymentFlag"
								name="payment_flag">
									<option value="Yes">Yes</option>
									<option value="No">No</option>
							</select></td>
						</tr>
						<tr>
							<td>PW: Publication reported in Web of Science</td>
							<td><select class="form-control" name="pwFlag">
									<option value="Yes">Yes</option>
									<option value="No">No</option>
							</select></td>
						</tr>
						<tr>
							<td>PS: Publication reported in Scopus</td>
							<td><select class="form-control" name="psFlag">
									<option value="Yes">Yes</option>
									<option value="No">No</option>
							</select></td>
						</tr>
						<tr>
							<td>PG: Publication reported in Google Scholar</td>
							<td><select class="form-control" name="pgFlag">
									<option value="Yes">Yes</option>
									<option value="No">No</option>
							</select></td>
						</tr>
						<tr>
							<td>PI: Publication reported in Indian Citation Index</td>
							<td><select class="form-control" name="piFlag">
									<option value="Yes">Yes</option>
									<option value="No">No</option>
							</select></td>
						</tr>
						<tr>
							<input type="hidden" name="writtenBy" value="<%=lao.getUsernameBySessionID(sid)%>" />
							<input type = "hidden" name="status" value = "0">
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
	<script type="text/javascript">
					function create(param) {
						'use strict';
						var i, target = document.getElementById('screens');
						target.innerHTML = '';

						for (i = 0; i < param; i += 1) {
							target.innerHTML += '<select name=nameOauthors class=form-control><%for (int i = 0; i < fao.getFaculties().size(); i++) {%><option><%out.print(fao.getFaculties().get(i)[1]);%></option><%}%></select>';
			}
		}
		document.getElementById('createTextboxes').addEventListener('change',
				function() {
					create(this.value);
				}, false);
		
	</script>
	<script type="text/javascript">
	$(document).on('change', 'select[name="nameOauthors"]', function(){
	    var total = 0;
	    $('select[name="nameOauthors"]').each(function(){
	        total += parseInt($(this).val());
	    });
	});
	</script>
</body>
</html>