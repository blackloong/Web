package com.fq.util;

public class Snippet {
	public static String removeNonBmpUnicode(String str) {    
		   if (str == null) {    
		       return null;    
		   }    
		   str = str.replaceAll("[^\\u0000-\\uFFFF]", "");    
		  return str;    
		}    
}

