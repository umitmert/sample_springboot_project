package com.ibss.util;


import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class Util {

	
	
	public static String encryptPassword(String username,String password){
		 ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();
		 return shaPasswordEncoder.encodePassword(password,new StringBuilder("DEMOKEY").append(username).toString());
	}
	
}