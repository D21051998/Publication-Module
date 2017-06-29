package com.publication.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.publication.dao.FacultyDAO;
import com.publication.database.ConnectionFactory;

public class FacultyIMPL  implements FacultyDAO{

	@Override
	public List<String[]> getFaculties() {
		List<String[]> list = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement("select * from login where role = 'ROLE_FACULTY'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				list.add(new String[]{rs.getString("username"), rs.getString("name"), rs.getString("email")});
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(connection);
		}
		return list;
	}

	public static void main(String[] args) {
		FacultyIMPL fao = new FacultyIMPL();
		System.out.println(fao.getFaculties().size());
	}
	
	
}
