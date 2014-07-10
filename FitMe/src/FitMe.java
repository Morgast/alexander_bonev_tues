import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FitMe
{
	private Map<String, String> map = new HashMap<String, String>();

	private static double bodyfat;

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		FitMe o = new FitMe();
		System.out.println("vuvedete hansh, taliq i viso4ina");

		o.parseConsoleInput(args);
		o.calculateBodyFat();
		System.out.println(bodyfat);
		if (bodyfat < 15)
		{
			o.copyFileUsingFileStreams("/resources/programa_kachvane.txt", "../trenirovka_pokachvane.txt");
			o.copyFileUsingFileStreams("/resources/rejim_gain.txt", "../rejim_pokachvane.txt");
		}

		if ((bodyfat >= 16) || (bodyfat <= 23))
		{

			System.out.println("Vuvedete 1 za povishavane na muskulnata masa i sila ili 2 za redukciq na tegloto i mazninite");
			int userChoice = sc.nextInt();
			if (userChoice == 1)
			{
				o.copyFileUsingFileStreams("/resources/programa_kachvane.txt", "../gain.txt");
				o.copyFileUsingFileStreams("/resources/rejim_gain.txt", "../rejim_pokachvane.txt");
			}
			else if (userChoice == 2)
			{
				o.copyFileUsingFileStreams("/resources/programa_svalqne.txt", "../programa_svalqne.txt");
				o.copyFileUsingFileStreams("/resources/rejim_redukciq.txt", "../rejim_svalqne.txt");
			}

			if (bodyfat > 24)
			{
				o.copyFileUsingFileStreams("/resources/programa_svalqne.txt", "../programa_svalqne.txt");
				o.copyFileUsingFileStreams("/resources/rejim_redukciq.txt", "../rejim_svalqne.txt");
			}
		}

		sc.close();
	}

	private void parseConsoleInput(String[] args)
	{
		for (int i = 0; i < args.length; i++)
		{
			// direktoriq=C:\
			String currentArgument = args[i];
			String[] splittedArgument = currentArgument.split("=");
			String measurement = splittedArgument[0];
			String measurementValue = splittedArgument[1];
			map.put(measurement, measurementValue);
		}
	}

	private double calculateBodyFat()
	{
		double A;
		double B;
		double C;
		double D;

		Integer hip = Integer.parseInt(map.get("hansh"));
		Integer waist = Integer.parseInt(map.get("taliq"));
		Integer height = Integer.parseInt(map.get("visochina"));

		A = hip * 0.55 - 2;
		B = waist * 0.29 - 4;
		C = A + B;
		D = height * 0.24;
		bodyfat = (C - D) - 10;

		return bodyfat;
	}

	private void copyFileUsingFileStreams(String source, String dest)
	{
		InputStream input = null;
		OutputStream output = null;
		try
		{
			URL resource = FitMe.class.getResource(source);
			String filePath = resource.getPath();
			input = new FileInputStream(filePath);
			output = new FileOutputStream(map.get("direktoriq"));
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0)
			{

				output.write(buf, 0, bytesRead);
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				input.close();
				output.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}
