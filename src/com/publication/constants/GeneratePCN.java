package com.publication.constants;

import java.util.Calendar;

public class GeneratePCN {
	
	
	public static String generatePCN(String branch, String type, int sno ){
		return String.format("%s%d%s%03d", branch, Calendar.getInstance().get(Calendar.YEAR),type,sno);
	}
	
}
