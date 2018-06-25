package com.BAS.util;


public class GenerateOTP {
	
	
		
		public String geek_Password(int len)
		{
			System.out.println("Generating password using random() : ");
			System.out.print("Your new password is : ");
			int randomPin   =(int)(Math.random()*9000)+100000;
			String password  =String.valueOf(randomPin);
			System.out.println(password);
			return password;
		}
	}

	

