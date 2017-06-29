package com.publication.test;

import com.publication.constants.BCrypt;

public class GeneratePassword {
	
	   public static void main(String[] args) {
	        String pas = "faculty";
	        String base = BCrypt.gensalt();
	        String password = BCrypt.hashpw(pas, base);
	        System.out.println(base + "\n" + password);
	    }

}
