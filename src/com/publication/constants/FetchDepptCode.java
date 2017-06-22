package com.publication.constants;

public class FetchDepptCode {

	public static String getDepttCode(String role) {
		return role.split("_")[2];
	}
}
