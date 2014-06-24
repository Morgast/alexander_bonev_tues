package com.elsys.generator;

import java.util.Random;

public class ClassToGenerateRandoms
{
	
	private static int randomOperationType() {
		int min = 1;
		int max = 4;

		Random r = new Random();
		int op = r.nextInt(max - min + 1) + min;
		
		//System.out.println(i1);
		return op;
		
	}
	
	private static int randomNumberGenerator(int i1) {
		int min = 1;
		int max = 50000;

		Random r = new Random();
		i1 = r.nextInt(max - min + 1) + min;
		
		return i1;
		
	}

}
