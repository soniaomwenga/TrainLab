package edu.wit.desn.comp2000.queueapp;

import com.pearson.carrano.ArrayQueue;
import  edu.wit.dcsn.rosenbergd.queueapp.*;

/**
 * @author Wes Brimeyer
 * @version	1.0.0	first pass
 */
public class Station
{
	
	private int stationID; 
	private int location; 
	private TrainRoute trainRoute;
	private static int nextID = 1;
	
	private ArrayQueue<Passenger> inboundPlatform;
	private ArrayQueue<Passenger> outboundPlatform;
	
	/**
	 * Sets instance variables
	 * initialize platforms to inital capacity of 50. 
	 * @param ID
	 * @param location1
	 */
	public Station(int location, TrainRoute route)
	{
		stationID = nextID++;
		this.location = location;
		trainRoute = route;
		inboundPlatform = new ArrayQueue<Passenger>(50);
		outboundPlatform = new ArrayQueue<Passenger>(50);
	}
	
	
	/**
	 * add passenger to the correct platform. (a queue) 
	 * @param a Passenger
	 */
	private void addToPlatform(Passenger pass)
	{
		//which platform to add it to (inbound or outbound)
		Direction platformNeeded = trainRoute.whichDirection(pass.getDestinationID(), pass.getArrivalID());
		if(platformNeeded == Direction.INBOUND)
		{
			inboundPlatform.enqueue(pass);
		}
		else
		{
			outboundPlatform.enqueue(pass);
		}
	}
	
	/**
	 * Passenger enters the station from outside
	 * 
	 * @param pass
	 */
	public void enter(Passenger pass)
	{
		Logger.write(pass + " has entered " + this);
		addToPlatform(pass);
	}
	
	/**
	 * Passenger exits the station and goes outside into the real world
	 * 
	 * @param pass
	 */
	public void exit(Passenger pass)
	{
		Logger.write(pass + " has left "+this+" and gone outside");
	}
	
	//When a Train arrives at a Station, two sets of interactions occur: 
	//Passengers disembark() the Train and simultaneously/immediately arrive() at the Station 
	/**
	 * Passenger arrives at station after getting off a train
	 * 
	 * @param pass
	 */
	public void arrive(Passenger pass)
	{
		if(pass.getDestinationID() == stationID)
		{
			int travelTime = TrainSimulation.tick - pass.getArrivalTime();
			Logger.write(pass + " has arrived at his/her destination, "+ this + " after "+travelTime+" ticks.");
			
			if(travelTime < TrainSimulation.fastestTravelTime) TrainSimulation.fastestTravelTime = travelTime;
			if(travelTime > TrainSimulation.slowestTravelTime) TrainSimulation.slowestTravelTime = travelTime;
			
			exit(pass);
		}
		else
		{
			Logger.write(pass + " has arrived at " + this + ", but this is not the desired destination!");
			addToPlatform(pass);
		}
		 
	}
	
	//while there are Passengers waiting on the platform 
	//and the Train has room for more Passengers, a Passenger leave()s the Station (platform) and board()s the Train
	/**
	 * Passenger departs the station and enters a train,
	 * removes passenger from the platform queue
	 * @param a Passenger, the Direction of the platform 
	 */
	public Passenger depart(Direction platformDeparted)
	{
		Passenger pass;
		if(platformDeparted == Direction.INBOUND)
		{
			if(inboundPlatform.isEmpty())
			{
				System.out.println("Attempted to dequeue an empty platform");
				return null;
			}
				
			pass = inboundPlatform.dequeue();
			Logger.write(pass+" has departed "+this+" from the inbound platform. Destination: Station #"+pass.getDestinationID());
			return pass;
		}
		else
		{
			if(outboundPlatform.isEmpty())
			{
				System.out.println("Attempted to dequeue an empty platform");
				return null;
			}
			pass = outboundPlatform.dequeue();
			Logger.write(pass+" has departed "+this+" from the outbound platform. Destination: Station #"+pass.getDestinationID());
			return pass;
		}
		
	}
	
	@Override
	public String toString()
	{
		return "Station #" + stationID;
	}
	
//Accessor Methods below---
	/**
	 * @return the stationID
	 */
	public int getStationID() 
	{
		return stationID;
	}


	/**
	 * @return the location
	 */
	public int getLocation() 
	{
		return location;
	}


	/**
	 * @return either inboundPlatform or outboundPlatform Queue
	 */
	public ArrayQueue<Passenger> getPlatform(Direction direction) 
	{
		if(direction == Direction.INBOUND)
			return inboundPlatform;
		else 
		{
			return outboundPlatform;
		}
	}
	
	
	//main method for testing purposes only
	public static void main(String[] args)
	{
		testConstructorAndGetters();
		testAddToPlatform();
		testEnterExitArrive();
		testDepart();
	}
	

	//Below are private tester methods
	private static void testConstructorAndGetters()
	{
		TrainRoute t = new TrainRoute();
		Station test = new Station(3,t);
		if(test.getLocation() == 3 && test.trainRoute == t
				&& test.getPlatform(Direction.INBOUND).isEmpty() && test.getStationID() == 6)//after stations already in TrainRoute	
		{
			System.out.println("Constructors and Getters test SUCCEEDED\n");					
		}
		else System.out.println("Constructors and Getters test FAILED\n");		
		
	}
	private static void testAddToPlatform()
	{
		TrainRoute t = new TrainRoute();
		Station test = new Station(3,t);
		
		test.addToPlatform(new Passenger(4,3));//from location 4 to location 15 should be INBOUND
		System.out.print("Testing addToPlatform()... Test ");
		if(!test.getPlatform(Direction.INBOUND).isEmpty())
				System.out.println("SUCCEEDED");
		else System.out.println("FAILED");
		
		
	}
	
	private static void testEnterExitArrive()
	{
		Logger.Create();
		System.out.println("\nTesting enter(), exit(), & arrived()...\n");
		TrainRoute t = new TrainRoute();
		Station test = new Station(3,t);
		
		test.enter(new Passenger(1,6));
		System.out.print("Test for enter() ");
		if(test.getPlatform(Direction.OUTBOUND).isEmpty() && test.getPlatform(Direction.INBOUND).isEmpty())
			System.out.println("FAILED");
		else System.out.println("SUCCEEDED");
		
		test.exit(new Passenger(1,5));//should print something in .log
		System.out.println("Check log to make sure exit() worked");
		
		test.arrive(new Passenger(test.stationID,2));
		test.arrive(new Passenger(2,4));
		System.out.println("Check log to make sure arrive() worked. Passenger #4 should arrive successfully, but not 5");
		Logger.close();
		
		
	}
	private static void testDepart()
	{
		Passenger bob = new Passenger(1,2);
		TrainRoute t = new TrainRoute();
		Station test = new Station(6,t);
		
		test.addToPlatform(bob);
		if(bob == test.depart(Direction.INBOUND))
		{
			System.out.println("\nTesting depart()... SUCCEEDED");
		}
		else System.out.println("\nTesting depart()... FAILED");
		
	}

}

