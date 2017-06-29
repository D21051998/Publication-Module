package com.publication.test;

public class StringTest {
	
//	public static void main(String[] args) {
//		String ac = "ROLE_DC_CSE";
//		System.out.println(ac.contains("ROLE_DC"));
//	}

	
	public static void main(String[] args) {
		String nameOauthors = ",,,,,,,,,Faculty1,,,Faculty2,,,,Faculty3,,,";
		nameOauthors = nameOauthors.replaceAll("[,]{1,}", ",");
		while(nameOauthors.charAt(0) == ','){
			nameOauthors = nameOauthors.substring(1);
		}
		while(nameOauthors.charAt(nameOauthors.length()-1) == ','){
			nameOauthors = nameOauthors.substring(0, nameOauthors.length()-1);
		}
		System.out.println(nameOauthors);
	}
}
