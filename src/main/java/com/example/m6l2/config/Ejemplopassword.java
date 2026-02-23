package com.example.m6l2.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Ejemplopassword {

	public static void main(String[] args) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin123"));
		System.out.println(encoder.encode("user123"));
		
	}

}
