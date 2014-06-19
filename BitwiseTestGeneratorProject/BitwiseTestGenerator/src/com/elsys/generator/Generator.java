package com.elsys.generator;

import java.util.Random;

public class Generator// kakto ti e kef
{
	// Stiga se uchi s tiq statichni metodi k
	public int randomOperationType(int op)
	{
		int min = 1;
		int max = 4;

		Random r = new Random();
//		Da ako se menqt tiq max i min
//		op = r.nextInt(max - min + 1) + min;
		op = r.nextInt(3) + min; 

		// System.out.println(i1);
		return op;
	}

	public int randomNumbederGenerator(int num)
	{
		int min = 1;
		int max = 50000;

		Random r = new Random();
		num = r.nextInt(max - min + 1) + min;
 //Kazvam ti samo che nqma smisyl da go pishesh s min i max. Mojesh da slojish prosto chuisloto vutre, ako nqma da se smenqt
		return num;

	}

	public int randomShiftBitsGenerator(int randShift)
	{
		int min = 1;
		int max = 9;

		Random r = new Random();
		//9 - 1 + 1 :D
		randShift = r.nextInt(9) + min;// Shte se nauchish!

		return randShift;
	}
}
