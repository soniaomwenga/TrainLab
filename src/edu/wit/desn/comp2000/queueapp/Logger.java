package edu.wit.desn.comp2000.queueapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * 
 * @author Jonathan Garciamathurin
 *
 */

public class Logger
{

	/**
	 * this method will take the strings passed to it 
	 * and print it in the log file in the format specified. 
	 * 
	 */
	private static PrintWriter pw;
	public static void write(String message)
	{
		pw.println("At time " + TrainSimulation.tick + "," + message);

	}
	/**
	 * this method writes to a file
	 * called TrainSimulation
	 */
	public static void Create()
	{
		try
		{
			File file = new File(("TrainSimulation.log"));

			pw = new PrintWriter(file);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * this method closes the PrintWriter
	 */

	public static void close()
	{

		pw.close();

	}

}
