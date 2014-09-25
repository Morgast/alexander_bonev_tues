package org.elsysbg.alexander.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoweToUpperCase {

	public static void main (String [] args) throws IOException {
		
		final InputStream ChangeCase = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(ChangeCase);
		final BufferedReader reader = new  BufferedReader(inputStreamReader);
		
		System.out.println(transformSymbols(reader.readLine()));
	}
	
	private  static String transformSymbols(String line)  {
			return line.toUpperCase();
		}
}
