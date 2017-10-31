package edu.wit.desn.comp2000.queueapp;
import  edu.wit.dcsn.rosenbergd.queueapp.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Wes Brimeyer
 * 
 */
public class TrainSimulation
{
	public static int tick;
	private int maxTicks; //number of ticks the simulation will run
	private static Random r;
	private int minRateOfPassengers;
	private int maxRateOfPassengers;
	private int initialPassengers;
	
	
	public TrainSimulation()
	{
		System.out.println("New simulation has started");
		try 
		{
			Configuration config = new Configuration();
			maxTicks = config.getTicks();
			minRateOfPassengers = config.getPassengers()[1].minimum;
			maxRateOfPassengers = config.getPassengers()[1].maximum;
			initialPassengers = getRandom(config.getPassengers()[0].minimum, config.getPassengers()[0].minimum);
			r = new Random(config.getSeed());
			
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
			return r.nextInt(max+1-min)+min;
		}
		
	}
	/**
	 * Randomly selects 2 stations from a TrainRoute
	 * @param the TrainRoute with stations in it
	 * @return a Station[] of size 2 with two distinct stations
	 */
	private static Station[] get2RandomStations(TrainRoute tr)
	{
		ArrayList<Station> stations = tr.getStations();
		Station[] tempStations = new Station[2];
		tempStations[0] = stations.get(getRandom(0,stations.size()-1));
		tempStations[1] = stations.get(getRandom(0,stations.size()-1));
		while(tempStations[0] == tempStations[1])
		{
			tempStations[1] = stations.get(getRandom(0,stations.size()-1));
		}
		
		return tempStations;
	}
	
	public static void main(String[] args)
	{	
		TrainSimulation sim = new TrainSimulation();		
		
		Logger.Create();
		
		TrainRoute trainRoute = new TrainRoute();
		System.out.println(sim.maxTicks);
		for(int i = 0; i < sim.initialPassengers; i++)
		{
			//constructs a new passenger with a random arrival and destination station
			Station[] randomStations = get2RandomStations(trainRoute);
			Passenger temp = new Passenger(randomStations[0].getStationID(),randomStations[1].getStationID());
			randomStations[1].enter(temp);	
		}
		for(tick = 1; tick <= sim.maxTicks; tick++)//simulate for the designated number of ticks
		{
			System.out.println("\nTick: "+tick);
			//generate a random number of passengers into the simulation
			for(int i = 0; i < getRandom(sim.minRateOfPassengers,sim.maxRateOfPassengers); i++)
			{
				//constructs a new passenger with a random arrival and destination station
				Station[] randomStations = get2RandomStations(trainRoute);
				Passenger temp = new Passenger(randomStations[0].getStationID(),randomStations[1].getStationID());
				randomStations[1].enter(temp);	
			}
			//System.out.println("moving trains... along track of length"+trainRoute.getTrackLength());
			trainRoute.moveTrains();
			
		}

		Logger.close();
	}
	
	
	
}
