package com.auditor.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LeitorCSV {

	public List<Registro> listaRegistros;
	
	public LeitorCSV(File file) {  
		listaRegistros = new ArrayList<Registro>();  
		  
	    File arquivoCSV = file;  
	    FileReader fr;
		try {
		fr = new FileReader(arquivoCSV);
		BufferedReader br = new BufferedReader(fr);  
	                     
		String linha;  
	    while ((linha = br.readLine()) != null) {
	        String registro[] = linha.split(";");  
	        listaRegistros.add(new Registro(registro));
		
	    }
	    
	    /*StringBuilder linha = new StringBuilder();  
	    while (!linha.append(br.readLine()).toString().equals("null")) {
	    	System.out.println("LINHA: "+linha.toString()+" "+linha);
	        String registro[] = linha.toString().split(";");  
	        listaRegistros.add(new Registro(registro));
	        linha.delete(0, linha.length());
	        
	    }  */
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
      
		//Registro r = listaRegistros.get(1);
		//System.out.print(r.toString());
    }
}