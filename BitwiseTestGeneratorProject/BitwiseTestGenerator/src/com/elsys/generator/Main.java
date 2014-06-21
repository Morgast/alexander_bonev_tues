

package com.elsys.generator;

import java.util.Random;

import com.elsys.generator.constants.Constants;

public class Main extends Generator
{
	public static void main(String[] args)
	{
		for(int i=0;i<12;i++) 
		{
			testGenerator();
		}
	}
	
	private static final int MAX_NUMBER_SIZE = 20000;
	
	private static int firstNum;
	
	private static int secondNum;
	
	public static void testGenerator()
	{
		firstNum = 1 + (int) (Math.random() * ((MAX_NUMBER_SIZE - 1) + 1));
		secondNum = 1 + (int) (Math.random() * ((MAX_NUMBER_SIZE - 1) + 1));
		
		System.out.println("a= " + firstNum);
		System.out.println("b= " + secondNum);

		boolean isShiftLeft = isShiftLeft();

		shiftNumbers(isShiftLeft, 0);

		int bitwisedResult = bitwiseOperation();

		System.out.println("result: " + "0x" + Integer.toHexString(bitwisedResult) + "\n");
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

	private static void shiftNumbers(boolean isShiftLeft, int shifter)
	{
		Generator generator = new Generator();
		if (isShiftLeft)
		{
			if ((generator.shifter() == 1))
			{
				// TODO: CHange 4 to random number
				firstNum = firstNum << generator.randomShiftBitsGenerator(shifter);
				secondNum = secondNum >> generator.randomShiftBitsGenerator(shifter);
				System.out.println("a <<" );
				System.out.println("b >> ");
			}
			else if (generator.shifter() == 2)
			{
				firstNum = firstNum >> generator.randomShiftBitsGenerator(shifter);
				secondNum = secondNum << generator.randomShiftBitsGenerator(shifter);
				System.out.println("a >>" );
				System.out.println("b << ");
			}
			else
			{
				firstNum = firstNum << generator.randomShiftBitsGenerator(shifter);
				secondNum = secondNum << generator.randomShiftBitsGenerator(shifter);
				System.out.println("a <<" );
				System.out.println("b << ");
			}
		}
		else
		{
			if ((generator.shifter() == 1))
			{
				// TODO: CHange 4 to random number
				firstNum = firstNum >> generator.randomShiftBitsGenerator(shifter);
				secondNum = secondNum << generator.randomShiftBitsGenerator(shifter);
				System.out.println("a >>" );
				System.out.println("b << ");
			}
			else if (generator.shifter() == 2)
			{
				firstNum = firstNum << generator.randomShiftBitsGenerator(shifter);
				secondNum = secondNum >> generator.randomShiftBitsGenerator(shifter);
				System.out.println("a <<" );
				System.out.println("b >> ");
			}
			else
			{
				firstNum = firstNum >> generator.randomShiftBitsGenerator(shifter);
				secondNum = secondNum >> generator.randomShiftBitsGenerator(shifter);
				System.out.println("a >>" );
				System.out.println("b >> ");
			}
		}
	}

	private static int bitwiseOperation()
	{
		Generator generator = new Generator();
		// TODO: Random 1-3;
		int operationType = generator.randomOperationType();
		int result = 0;

		switch (operationType)
		{
		// &
			case 1:
				System.out.println("a&b=?\n");
				result = firstNum & secondNum;
				break;
			// ^
			case 2:
				System.out.println("a^b=?\n");
				result = firstNum ^ secondNum;
				break;
			// |
			case 3:
				System.out.println("a|b=?\n");
				result = firstNum | secondNum;
				break;

			default:
				System.out.println(Constants.BAD_ARGUMENT_MESSAGE);
				break;
		}
		return result;
	}
}
