package com.elsys.generator;

import java.util.Random;

import com.elsys.generator.constants.Constants;


public class Main
{

	public static void main(String[] args)
	{
		Random randomService = new Random();

		 int firstNum = randomService.nextInt();
		 int secondNum = randomService.nextInt();

		boolean isShiftLeft = isShiftLeft();

		shiftNumbers(firstNum, secondNum, isShiftLeft);

		int bitwisedResult = bitwiseOperation(firstNum, secondNum);

		System.out.println(Integer.toHexString(bitwisedResult));
	}

	/**
	 * True - right False - left
	 * 
	 * @return
	 */
	private static boolean isShiftLeft()
	{
		Random randomService = new Random();

		return randomService.nextBoolean();
	}

	private static void shiftNumbers(int firstNum, int secondNum, boolean isShiftLeft)
	{
		if (isShiftLeft)
		{
			// TODO: CHange 4 to random number
			firstNum = firstNum << 2;
			secondNum = secondNum << 2;
		}
		else
		{
			// TODO: CHange 4 to random number
			firstNum = firstNum >> 4;
			secondNum = secondNum >> 4;
		}
	}

	private static int bitwiseOperation(int a, int b)
	{
		// TODO: Random 1-3;
		Random randomService = new Random();
		int operationType = 1;// randomService.nextInt();
		int result = 0;
		switch (operationType)
		{
		// &
			case 1:
				System.out.println(Constants.AND_OPERATION_MESSAGE);
				result = a & b;
				break;
			// ^
			case 2:
				System.out.println(Constants.XOR_OPERATION_MESSAGE);
				result = a ^ b;
				break;
			// |
			case 3:
				System.out.println(Constants.OR_OPERATION_MESSAGE);
				result = a | b;
				break;

			default:
				System.out.println("Invalid argument passed.");
				break;
		}
		return result;
	}
}

