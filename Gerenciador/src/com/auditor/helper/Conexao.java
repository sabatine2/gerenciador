package com.auditor.helper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Conexao {

	public static boolean valida(){
    	
    	try{  
    	    URL mandarMail = new URL("http://www.google.com");  
    	    URLConnection conn = mandarMail.openConnection();  
    	  
    	    HttpURLConnection httpConn = (HttpURLConnection) conn;  
    	    httpConn.connect();  
    	    int x = httpConn.getResponseCode();  
    	    if(x == 200){  
    	           System.out.println("Conectado");
    	           return true;  
    	    }
    	    else {
    	    	System.out.println("Sem Conex�o");
    	    	return false;
			}
    	}  
    	catch(java.net.MalformedURLException urlmal){  
    		
    	}  
    	catch(java.io.IOException ioexcp){  
    		return false;
    	}
    	
		return false;  
	}
}
