package com.principal.helper;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

	public static String md5Senha(String senha) throws NoSuchAlgorithmException {  
		  MessageDigest md = MessageDigest.getInstance("MD5");  
	      BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));  
	      return  hash.toString(16);           
	   }  

	public static String md5Codigo(String senha) throws NoSuchAlgorithmException {  
		  senha.concat("gerenciadorpersys");
		  MessageDigest md = MessageDigest.getInstance("MD5");  
	      BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));  
	      return  hash.toString(16);           
	   }  

	public static String shaCodigo(String senha) throws NoSuchAlgorithmException {  
		   senha += "gerenciadorpersys";
	
			MessageDigest md = MessageDigest.getInstance("SHA-256");
		    md.update(senha.getBytes());
		
		    byte byteData[] = md.digest();
		
		    //convert the byte to hex format method 1
		    StringBuffer sb = new StringBuffer();
		    for (int i = 0; i < byteData.length; i++) {
		     sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		    }

		    return sb.toString();
	}
}
