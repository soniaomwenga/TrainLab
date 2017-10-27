package edu.wit.desn.comp2000.queueapp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;



public class Logger
{
// all variable here will be private static 
//methods here will take the strings passed to it and log them into the file called logger. 
	private static PrintWriter pw;
	
	//a//
	public static void write(String message){
		pw.println(message);
	
	}
		
	public static void Create(){
		try {
			File file = new File(("TrainSimulation.log"));

			pw = new PrintWriter(file);
		} catch(FileNotFoundException e) {
		e.printStackTrace();
		}
	}
		
	public static void close(){
	
	pw.close();
	
	}
		

		
		

		
	
	}
	
