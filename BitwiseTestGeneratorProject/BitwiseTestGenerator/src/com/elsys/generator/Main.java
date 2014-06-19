
package com.elsys.generator;

import java.util.Random;

import com.elsys.generator.constants.Constants;



public class Main extends Generator
{
	public static int operation;

	public static void main(String[] args)
	{
		Random randomService = new Random();

		 int firstNum = randomService.nextInt(Integer.MAX_VALUE - 1 + 1) + 1;
		 int secondNum = randomService.nextInt(Integer.MAX_VALUE - 1 + 1) + 1;

		boolean isShiftLeft = isShiftLeft();

		shiftNumbers(firstNum, secondNum, isShiftLeft, 0);

		int bitwisedResult = bitwiseOperation(firstNum, secondNum, operation);

		System.out.println("0x" + Integer.toHexString(bitwisedResult));
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

	private static void shiftNumbers(int firstNum, int secondNum, boolean isShiftLeft, int shifter)
	{
		Generator generator = new Generator();
		if (isShiftLeft)
		{
			// TODO: CHange 4 to random number
			firstNum = firstNum << generator.randomNumbederGenerator(shifter);
			secondNum = secondNum << generator.randomNumbederGenerator(shifter);
		}
		else
			//razbrah. Te trqqshe da sa random
			//da
		{
			// TODO: CHange 4 to random number
			firstNum = firstNum >> generator.randomNumbederGenerator(shifter);
			secondNum = secondNum >> generator.randomNumbederGenerator(shifter);
		}
	}

	private static int bitwiseOperation(int a, int b, int operation)
	{
		Generator generator = new Generator();
		// TODO: Random 1-3;
		Random randomService = new Random();
		int operationType = generator.randomOperationType(operation) ;
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
				System.out.println(Constants.BAD_ARGUMENT_MESSAGE);
				System.out.println("OperationType" + operationType);
//				System.out.println(generator.randomOperationType(operation));
				break;
		}
		return result; 
	}
}

