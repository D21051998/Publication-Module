package com.publication.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {

		String date = "22-06-2017";

		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyyy");
		try {
			Date d = f.parse(date);
			long m1 = d.getTime();
			long m2 = System.currentTimeMillis();

			java.sql.Date d1 = new java.sql.Date(m1);
			java.sql.Date d2 = new java.sql.Date(m2);
			System.out.println(d1 + " " + d2);
			System.out.println(m1 + " " + m2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
