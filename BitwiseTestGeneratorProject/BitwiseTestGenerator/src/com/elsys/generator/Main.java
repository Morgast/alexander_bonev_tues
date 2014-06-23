

package com.elsys.generator;
 
import java.awt.print.PageFormat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
 
import com.elsys.generator.constants.Constants;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
 
 
 
public class Main extends Generator
{
         static Paragraph preface = new Paragraph();
        private static String task1 = "";
        private static String task2 = "";
        private static String task3 = "";
        private static String task4 = "";
        private static String task = "";
 
       
//        private static String FILE = "C:\\Users\\Alexander\\Desktop\\NewPDF.pdf";
//        private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
//            Font.BOLD);
//        private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
//            Font.NORMAL, BaseColor.RED);
//        private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
//            Font.BOLD);
//        private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
//            Font.BOLD);
//      //NewPdfGenerator pdfgen = new NewPdfGenerator();
//      static Paragraph preface = new Paragraph();
       
        /**
         * @param args
         */
        public static void main(String[] args)
        {
               
               
                try {
            FileOutputStream file = new FileOutputStream(new File("C:\\Users\\Alexander\\Desktop\\tests.pdf"));
 
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            document.add(new Paragraph("hi"));
            preface.add(new Paragraph("cuasdfl"));
           
 
            document.close();
            file.close();
 
        } catch (Exception e) {
 
            e.printStackTrace();
        }
                //Document pdfDocument = new Document(PageSize.A4, 50, 50, 50, 50);
//              try {
//                      PdfWriter.getInstance(pdfDocument, new FileOutputStream("CurrentFile.pdf"));
//              } catch (DocumentException e) {
//                      // TODO Auto-generated catch block
//                      e.printStackTrace();
//              } catch (IOException e) {
//                      e.printStackTrace();
//              }
               
//              //pdfDocument.open();
//              PdfPTable tasks = new PdfPTable(12);
//              tasks.setWidthPercentage(400);
//              //eto tuk trqbva da pravish slednoto
//              tasks.addCell(new Paragraph(task));
//              //seshtash li se? e kak da vzema ot vsi4ki mesta printovete i da gi sloja tuka  " "
////            try
////            {
////                    pdfDocument.add(tasks);
////            }
////            catch (DocumentException e)
////            {
////                    e.printStackTrace();
////            }
////            pdfDocument.close();
////            //add to pdf
////           
//             
               
               
                for(int i=0;i<12;i++)
                {
                        System.out.println(stringReturn());
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
               
                task1 = "a = " + firstNum + "\n" + "b = " + secondNum + "\n";
               
                //System.out.println("a= " + firstNum);
                //System.out.println("b= " + secondNum);
 
                boolean isShiftLeft = isShiftLeft();
 
                shiftNumbers(isShiftLeft, 0);
 
                int bitwisedResult = bitwiseOperation();
 
                //System.out.println("result: " + "0x" + Integer.toHexString(bitwisedResult));
                //System.out.println("__________________________________________");
                task2 = "result: " + "0x" + Integer.toHexString(bitwisedResult) + "\n";
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
                                //System.out.println("a <<" + firstNumBitShift );
                                //System.out.println("b >> " + secondNumBitShift);
                                task3 = "a << " + firstNumBitShift + "\n" + "b >> " + secondNumBitShift + "\n";
                               
                        }
                        else if (generator.shifter() == 2)
                        {
                                int firstNumBitShift = generator.randomShiftBitsGenerator(shifter);
                                firstNum = firstNum >> firstNumBitShift;
                                int secondNumBitShift = generator.randomShiftBitsGenerator(shifter);
                                secondNum = secondNum << secondNumBitShift;
                                //System.out.println("a >>" + firstNumBitShift );
                                //System.out.println("b << " + secondNumBitShift);
                                task3 = "a >> " + firstNumBitShift + "\n" + "b << " + secondNumBitShift + "\n";
                        }
                        else
                        {
                                int firstNumBitShift = generator.randomShiftBitsGenerator(shifter);
                                firstNum = firstNum << firstNumBitShift;
                                int secondNumBitShift = generator.randomShiftBitsGenerator(shifter);
                                secondNum = secondNum << secondNumBitShift;
                                //System.out.println("a <<" + firstNumBitShift );
                                //System.out.println("b << " + secondNumBitShift);
                                task3 = "a << " + firstNumBitShift + "\n" + "b << " + secondNumBitShift + "\n";
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
                                //System.out.println("a >>" + firstNumBitShift );
                                //System.out.println("b << " + secondNumBitShift);
                                task3 = "a >> " + firstNumBitShift + "\n" +"b << " + secondNumBitShift + "\n";
                        }
                        else if (generator.shifter() == 2)
                        {
                                int firstNumBitShift = generator.randomShiftBitsGenerator(shifter);
                                firstNum = firstNum << firstNumBitShift;
                                int secondNumBitShift = generator.randomShiftBitsGenerator(shifter);
                                secondNum = secondNum >> secondNumBitShift;
                                //System.out.println("a <<" + firstNumBitShift );
                                //System.out.println("b >> " + secondNumBitShift);
                                task3 = "a << " + firstNumBitShift + "\n" + "b >> " + secondNumBitShift + "\n";
                        }
                        else
                        {
                                int firstNumBitShift = generator.randomShiftBitsGenerator(shifter);
                                firstNum = firstNum >> firstNumBitShift;
                                int secondNumBitShift = generator.randomShiftBitsGenerator(shifter);
                                secondNum = secondNum >> secondNumBitShift;
                                //System.out.println("a >>" + firstNumBitShift );
                                //System.out.println("b >> " + secondNumBitShift);
                                task3 = "a >> " + firstNumBitShift + "\n" + "b >> " + secondNumBitShift + "\n";
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
                                //System.out.println("a&b=?\n");
                                result = firstNum & secondNum;
                                task4 = "a&b=?" + "\n";
                                break;
                        // ^
                        case 2:
                                //System.out.println("a^b=?\n");
                                result = firstNum ^ secondNum;
                                task4 = "a^b=?" + "\n";
                                break;
                        // |
                        case 3:
                                //System.out.println("a|b=?\n");
                                result = firstNum | secondNum;
                                task4 = "a|b=?" + "\n";
                                break;
 
                        default:
                                System.out.println(Constants.BAD_ARGUMENT_MESSAGE);
                                break;
                }
                return result;
        }
       
        private static String stringReturn() {
               
                task = task1 + task4 + task3 + task2;
                return task;
        }
}
