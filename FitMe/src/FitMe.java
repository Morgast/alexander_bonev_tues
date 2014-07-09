import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;


public class FitMe
{
	public static int hansh;
	public static int talia;
	public static int height;
	public static double A;
	public static double B;
	public static double C;
	public static double D;
	public static double bodyfat;
	

	public static void main(String[] args)
	{
		System.out.println("vuvedete hansh, taliq i viso4ina");
		
		hansh = Integer.parseInt(args[0]);
		talia = Integer.parseInt(args[1]);
		height = Integer.parseInt(args[2]);
		
		A = hansh*0.55 - 2;
		B = talia*0.29 - 4;		
		C = A+B;
		D = height*0.24;
		bodyfat = (C-D) - 10;
		
		System.out.println(bodyfat);
		
		if(bodyfat<15) {
			
			
		}
		
		if((bodyfat>=16) && (bodyfat<=23)) {
			
			
		}
		
		if(bodyfat>24) {
			
			
		}
		
		
	}
	
	void writeInTxtFile() {
		throws Exception 
		      BufferedWriter out1 = new BufferedWriter(new FileWriter("srcfile"));
		      out1.write("string to be copied\n");
		      
		      out1.close();
		      InputStream in = new FileInputStream(new File("srcfile"));
		      OutputStream out = new FileOutputStream(new File("destnfile"));
		      byte[] buf = new byte[1024];
		      int len;
		      while ((len = in.read(buf)) > 0) {
		         out.write(buf, 0, len);
		      }
		      in.close();
		      out.close();
		      BufferedReader in1 = new BufferedReader
		      (new FileReader("destnfile"));
		      String str;
		      while ((str = in1.readLine()) != null) {
		         System.out.println(str);
		      }
		      in.close();
		
	}

}
