package com.BAS.controller;

public class Test {


	   public static void main(String args[]) {
	      String Str = new String("TX_111111111");
	      long b = Long.parseLong(Str.substring(3));
	      System.out.println(b);
	      b=b+1;
	     String c =Str.substring(0,3) + b;
	     System.out.println(c);
	   }
	}


