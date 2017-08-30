package com.publication.constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class RoleMap {

	static Map<Integer, String> map = new LinkedHashMap<>();

	static {
		map.put(1, "ROLE_FACULTY");
		map.put(2, "ROLE_RDIL");
		map.put(3, "ROLE_DC_APS");
		map.put(4, "ROLE_DC_CSE");
		map.put(5, "ROLE_DC_CEE");
		map.put(6, "ROLE_DC_ECE");
		map.put(7, "ROLE_DC_MED");
		map.put(8, "ROLE_DC_SOM");
		map.put(9, "ROLE_DC_SOL");
		map.put(10, "ROLE_DC_CLL");
	}

	public static Integer RoleStringToInteger(String role) {

		for (Integer key : map.keySet()) {
			if (role.equals(map.get(key))) {
				return key;
			}
		}

		return null;
	}

}
