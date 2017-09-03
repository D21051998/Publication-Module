package com.publication.dao;

import java.util.List;

public interface FacultyDAO {
	
	public boolean updateFaculty(String[] str);
	public List<String[]> getFaculties();

}
