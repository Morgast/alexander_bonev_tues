package com.elsys.generator;

import java.util.Random;

public class Generator
{
	/**
	 * 1- AND <br> 2 - XOR <br> 3 - OR
	 * 
	 * @return Number between 1-3 representing a AND, XOR, OR operation
	 */
	public int randomOperationType()
	{
		int min = 1;

		Random r = new Random();
		//TODO: Return ENUM Constant.
		int operationType  = r.nextInt(3) + min;

		return operationType;
	}

	public int randomNumbederGenerator(int num)
	{
		int min = 1;
		int max = 5000;

		Random r = new Random();
		num = r.nextInt(max - min + 1) + min;
		return num;

	}

	public int randomShiftBitsGenerator(int randShift)
	{
		int min = 1;

		Random r = new Random();
		// 9 - 1 + 1 :D
		randShift = r.nextInt(9) + min;// Shte se nauchish!

		return randShift;
	}

	public int shifter()
	{
		Random r = new Random();
		int randShift = r.nextInt(2) + 1;

		return randShift;
	}
}
