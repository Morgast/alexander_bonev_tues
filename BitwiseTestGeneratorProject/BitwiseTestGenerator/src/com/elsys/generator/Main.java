

package com.elsys.generator;

import java.util.Random;

import com.elsys.generator.constants.Constants;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;



public class Main extends Generator
{
	
	  private static String FILE = "C:\\Users\\Alexander\\Desktop\\NewPDF.pdf";
	  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	      Font.BOLD);
	  private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	      Font.NORMAL, BaseColor.RED);
	  private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	      Font.BOLD);
	  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	      Font.BOLD);
	NewPdfGenerator pdfgen = new NewPdfGenerator();
	static Paragraph preface = new Paragraph();
	
	public static void main(String[] args)
	{
		
		//add to pdf
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
		preface.add(new Paragraph("a= " + firstNum,
		              	        smallBold));
		System.out.println("b= " + secondNum);
		preface.add(new Paragraph("b= " + secondNum,
			              	        smallBold));

		boolean isShiftLeft = isShiftLeft();

		shiftNumbers(isShiftLeft, 0);

		int bitwisedResult = bitwiseOperation();

		System.out.println("result: " + "0x" + Integer.toHexString(bitwisedResult));
		preface.add(new Paragraph("result: " + "0x" + Integer.toHexString(bitwisedResult),
			              	        smallBold));
		//.addEmptyLine(preface, 8);
		System.out.println("__________________________________________");
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
				int firstNumBitShift = generator.randomShiftBitsGenerator(shifter);
				firstNum = firstNum << firstNumBitShift;
				int secondNumBitShift = generator.randomShiftBitsGenerator(shifter);
				secondNum = secondNum >> secondNumBitShift;
				System.out.println("a <<" + firstNumBitShift );
				System.out.println("b >> " + secondNumBitShift);
			}
			else if (generator.shifter() == 2)
			{
				int firstNumBitShift = generator.randomShiftBitsGenerator(shifter);
				firstNum = firstNum >> firstNumBitShift;
				int secondNumBitShift = generator.randomShiftBitsGenerator(shifter);
				secondNum = secondNum << secondNumBitShift;
				System.out.println("a >>" + firstNumBitShift );
				System.out.println("b << " + secondNumBitShift);
			}
			else
			{
				int firstNumBitShift = generator.randomShiftBitsGenerator(shifter);
				firstNum = firstNum << firstNumBitShift;
				int secondNumBitShift = generator.randomShiftBitsGenerator(shifter);
				secondNum = secondNum << secondNumBitShift;
				System.out.println("a <<" + firstNumBitShift );
				System.out.println("b << " + secondNumBitShift);
			}
		}
		else
		{
			if ((generator.shifter() == 1))
			{
				// TODO: CHange 4 to random number
				int firstNumBitShift = generator.randomShiftBitsGenerator(shifter);
				firstNum = firstNum >> firstNumBitShift;
				int secondNumBitShift = generator.randomShiftBitsGenerator(shifter);
				secondNum = secondNum << secondNumBitShift;
				System.out.println("a >>" + firstNumBitShift );
				System.out.println("b << " + secondNumBitShift);
			}
			else if (generator.shifter() == 2)
			{
				int firstNumBitShift = generator.randomShiftBitsGenerator(shifter);
				firstNum = firstNum << firstNumBitShift;
				int secondNumBitShift = generator.randomShiftBitsGenerator(shifter);
				secondNum = secondNum >> secondNumBitShift;
				System.out.println("a <<" + firstNumBitShift );
				System.out.println("b >> " + secondNumBitShift);
			}
			else
			{
				int firstNumBitShift = generator.randomShiftBitsGenerator(shifter);
				firstNum = firstNum >> firstNumBitShift;
				int secondNumBitShift = generator.randomShiftBitsGenerator(shifter);
				secondNum = secondNum >> secondNumBitShift;
				System.out.println("a >>" + firstNumBitShift );
				System.out.println("b >> " + secondNumBitShift);
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
