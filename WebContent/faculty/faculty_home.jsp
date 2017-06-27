<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

	
<title>Faculty Home</title>
<style>
.container {
	width: 100%;
}


.borderless {
	border-bottom: 0 none;
	border-top: none;
	border-left:0;
	border-right:0;
	background: #f28430;
	font-size:medium;
	font-weight: bold;

}
.li{
	color: black;	
}
ul {
	list-style: none;
}
#sidebarStyle{
 background: #f28430;
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
<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"></jsp:useBean>
<jsp:include page="../headers/new_pages_header.jsp"></jsp:include>
<%
String sid  = (String) request.getSession(false).getAttribute("sid");
System.out.println("AT FACULTY"+sid);
if(null==sid){
	response.sendRedirect("../account/access_denied.jsp");
	return;
}
if(!lao.getRoleBySessionID(sid).equals("ROLE_FACULTY")){
	response.sendRedirect("../account/access_denied.jsp");
	return;
}
System.out.println(sid);
%>	
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2" >
			<jsp:include page="../sidebars/faculty_home_sidebar.jsp"></jsp:include>
			</div>
			<div class="col-md-10"></div>
			<h2>Faculty Home</h2>
		</div>
	</div>
</body>
</html>