package com.xc.autotest.testcode;

import java.io.InputStream;

public class test3 {
	
	public static void test (String[] args)
	{
		
	String path = test3.class.getResource("/").toString();   
	  System.out.println("path = " + path);  
	  InputStream  in=test3.class.getClassLoader().getResourceAsStream( "config.properties");
	  System.out.println("in:"+in);
	
	}
	

}
