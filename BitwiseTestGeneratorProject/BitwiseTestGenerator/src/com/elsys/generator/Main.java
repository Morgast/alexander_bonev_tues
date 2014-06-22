

package com.elsys.generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import com.elsys.generator.constants.Constants;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class Main extends Generator
{
	
	private static String task = "";
	
	  private static String FILE = "C:\\Users\\Alexander\\Desktop\\NewPDF.pdf";
	  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	      Font.BOLD);
	  private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	      Font.NORMAL, BaseColor.RED);
	  private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	      Font.BOLD);
	  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	      Font.BOLD);
	//NewPdfGenerator pdfgen = new NewPdfGenerator();
	static Paragraph preface = new Paragraph();
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
//		Document pdfDocument = new Document(PageSize.A4, 50, 50, 50, 50);
//		try {
//			PdfWriter.getInstance(pdfDocument, new FileOutputStream("CurrentFile.pdf"));
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		//pdfDocument.open();
		PdfPTable tasks = new PdfPTable(12);
		tasks.setWidthPercentage(400);
		//eto tuk trqbva da pravish slednoto
		//tasks.addCell(new Paragraph("generiraniq task");
		//seshtash li se? e kak da vzema ot vsi4ki mesta printovete i da gi sloja tuka  " "
//		try
//		{
//			pdfDocument.add(tasks);
//		}
//		catch (DocumentException e)
//		{
//			e.printStackTrace();
//		}
//		pdfDocument.close();
		//add to pdf
		
		task = "a = " + firstNum + " " + "b = " + secondNum;
		System.out.println(task);
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

		System.out.println("result: " + "0x" + Integer.toHexString(bitwisedResult));
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
