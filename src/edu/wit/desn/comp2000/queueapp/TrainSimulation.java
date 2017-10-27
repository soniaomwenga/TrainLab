package edu.wit.desn.comp2000.queueapp;
import  edu.wit.dcsn.rosenbergd.queueapp.*;

import java.io.FileNotFoundException;
import java.util.Random;

public class TrainSimulation
{
	public static int tick;
	private int maxTicks; //number of ticks the simulation will run
	private static Random r = new Random();
	private int minRateOfPassengers;
	private int maxRateOfPassengers;
	private int initialPassengers;
	
	
	public TrainSimulation()
	{
		try 
		{
			Configuration config = new Configuration();
			maxTicks = config.getTicks();
			
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Config file was not found. /nSimulation cannot run.");
		}
	}
	
	
	private static int getRandom(int min, int max)
	{
		if(min == max)
		{
			return max;
		}
		else
		{
			return r.nextInt(max-min)+min;
		}
		
	}
	
	public static void main(String args)
	{
		TrainSimulation sim = new TrainSimulation();
		Logger.Create();
		TrainRoute trainRoute = new TrainRoute();
		for(tick = 0; tick < sim.maxTicks; tick++)
		{
			for(int i = 0; i < getRandom(sim.minRateOfPassengers,sim.maxRateOfPassengers); i++)
			{
				//Passenger temp = new Passenger(dest,arr)
			}
			
		}
		
		
	}
	
}
