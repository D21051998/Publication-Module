package com.publication.constants;

public class Redirect {
	
	public static String redirect(String role, boolean success){
		String url = "index.jsp";
		if(!success){
			return url;
		}
		if(role.contains("ROLE_DC")){
			return "department_coord/coordinator_home.jsp";
		}
		switch(role){
		case "ROLE_FACULTY": url =  "faculty/faculty_home.jsp";
		break;
		case "ROLE_ADMIN": url =  "admin/admin_home.jsp";
		break;
		case "ROLE_RDIL": url =  "rdil/rdil_home.jsp";
		break;
		default:
		}
		return url;
	}

}
