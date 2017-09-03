<%@page import="com.publication.model.Login"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="lao" class="com.publication.impl.LoginIMPL"></jsp:useBean>
	<jsp:useBean id="fao" class="com.publication.impl.FacultyIMPL"></jsp:useBean>

	<%
		String sid = (String) request.getSession(false).getAttribute("sid");
		Login login = lao.getLogin(lao.getUsernameBySessionID(sid));
		if (null == login) {
			response.sendRedirect("../account/access_denied.jsp");
			return;
		}
		if ("POST".equals(request.getMethod())) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			System.out.println(id + " " + name + " " + email);
			if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {
				response.sendRedirect("edit_faculty.jsp?edit=invalid");
				return;
			} else {
				if (id.equals(login.getUsername())) {
					if (fao.updateFaculty(new String[] { id, name, email })) {
						response.sendRedirect("edit_faculty.jsp?edit=success");
						return;
					} else {
						response.sendRedirect("edit_faculty.jsp?edit=failed");
						return;
					}
				} else {
					response.sendRedirect("edit_faculty.jsp?edit=invalid");
					return;
				}
			}

		} else {
			out.println("Invalid Request!");
		}
	%>

</body>
</html>